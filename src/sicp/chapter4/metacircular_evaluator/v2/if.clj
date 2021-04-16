(ns sicp.chapter4.metacircular-evaluator.v2.if
  (:require [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [inter-eval]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))


(defn true? [x] (not (= x false)))
(defn false? [x] (= x false))

(defn if-predicate [exp] (second exp))
(defn if-consequent [exp] (first (rest (rest exp))))
(defn if-alternative [exp]
  (if (not (nil? (rest (rest (rest exp)))))
    (first (rest (rest (rest exp))))
    'false))

(defn make-if [predicate consequent alternative]
  (list 'if predicate consequent alternative))

(defn eval-if [exp env]
  (if (true? (inter-eval (if-predicate exp) env))
    (inter-eval (if-consequent exp) env)
    (inter-eval (if-alternative exp) env)))

(defn install-if []
  (add-operation 'if eval-if))