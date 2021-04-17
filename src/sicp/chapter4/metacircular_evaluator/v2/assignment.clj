(ns sicp.chapter4.metacircular-evaluator.v2.assignment
  (:require [sicp.chapter4.metacircular-evaluator.v2.variable :refer [set-variable-value!]]
            [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [analyze]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))

(defn assignment-variable [exp] (second exp))
(defn assignment-value [exp] (last exp))

(defn analyze-assignment [exp]
  (let [var (assignment-variable exp)
        vproc (analyze (assignment-value exp))]
    (fn [env]
      (set-variable-value! var (vproc env) env)
      'ok)))

(defn install-assignment []
  (add-operation 'set! analyze-assignment))