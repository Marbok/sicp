(ns sicp.chapter4.metacircular-evaluator.v2.interpreter
  (:require [sicp.chapter4.metacircular-evaluator.v2.variable :refer [lookup-variable-value variable? define-variable!]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [get-operation]]
            [sicp.chapter4.metacircular-evaluator.v2.environment :refer [the-empty-environment extend-environment]]
            [sicp.chapter4.metacircular-evaluator.v2.procedure :refer [inter-apply]]))

(declare inter-eval)
(declare tagged-list?)

(defn self-evaluating? [exp]
  (cond
    (number? exp) true
    (string? exp) true
    :else false))

(defn get-tag ^{:pre [(seq? exp)]}
  [exp]
  (first exp))

(defn has-operation? [exp]
  (not (nil? (get-operation (get-tag exp)))))

(defn execute [exp env]
  ((get-operation (get-tag exp)) exp env))

(defn application? [exp] (seq? exp))
(defn operator [exp] (first exp))
(defn operands [exp] (rest exp))
(defn no-operands? [ops] (empty? ops))
(defn first-operand [ops] (first ops))
(defn rest-operands [ops] (rest ops))

(defn list-of-values [exps env]
  (if (no-operands? exps)
    '()
    (cons (inter-eval (first-operand exps) env)
          (list-of-values (rest-operands exps) env))))

(defn inter-eval [exp env]
  (cond
    (self-evaluating? exp) exp
    (variable? exp) (lookup-variable-value exp env)
    (has-operation? exp) (execute exp env)
    (application? exp) (inter-apply (inter-eval (operator exp) env)
                                    (list-of-values (operands exp) env))
    :else (throw (IllegalArgumentException. (str "Unknown expression type: EVAL" exp)))))

