(ns sicp.chapter4.metacircular-evaluator.v2.sequence
  (:require [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [inter-eval]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))

(defn begin-actions [exp] (rest exp))
(defn last-exp? [seq] (empty? (rest seq)))
(defn first-exp [seq] (first seq))
(defn rest-exps [seq] (rest seq))
(defn make-begin [seq] (cons 'begin seq))
(defn sequence->exp [seq]
  (cond
    (empty? seq) seq
    (last-exp? seq) (first-exp seq)
    :else (make-begin seq)))

(defn eval-sequence [exps env]
  (cond
    (last-exp? exps) (inter-eval (first-exp exps) env)
    :else (do
            (inter-eval (first-exp exps) env)
            (eval-sequence (rest-exps exps) env))))

(defn eval-begin [exp env]
  (eval-sequence (begin-actions exp) env))

(defn install-begin []
  (add-operation 'begin eval-begin))