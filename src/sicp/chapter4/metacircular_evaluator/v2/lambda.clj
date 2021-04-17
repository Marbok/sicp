(ns sicp.chapter4.metacircular-evaluator.v2.lambda
  (:require [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]
            [sicp.chapter4.metacircular-evaluator.v2.procedure :refer [make-procedure]]
            [sicp.chapter4.metacircular-evaluator.v2.sequence :refer [analyze-sequence]]))

(defn lambda-parameters [exp] (second exp))
(defn lambda-body [exp] (rest (rest exp)))
(defn make-lambda [parameters body]
  (cons 'lambda (cons parameters body)))

(defn analyze-lambda [exp]
  (let [vars (lambda-parameters exp)
        bproc (analyze-sequence (lambda-body exp))]
    (fn [env]
      (make-procedure vars bproc env))))

(defn install-lambda []
  (add-operation 'lambda analyze-lambda))