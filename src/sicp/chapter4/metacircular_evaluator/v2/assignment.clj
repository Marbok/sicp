(ns sicp.chapter4.metacircular-evaluator.v2.assignment
  (:require [sicp.chapter4.metacircular-evaluator.v2.variable :refer [set-variable-value!]]
            [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [inter-eval]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))

(defn assignment-variable [exp] (second exp))
(defn assignment-value [exp] (last exp))

(defn eval-assignment [exp env]
  (set-variable-value! (assignment-variable exp)
                       (inter-eval (assignment-value exp) env)
                       env)
  'ok)

(defn install-assignment []
  (add-operation 'set! eval-assignment))