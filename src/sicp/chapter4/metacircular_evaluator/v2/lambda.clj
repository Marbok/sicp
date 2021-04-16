(ns sicp.chapter4.metacircular-evaluator.v2.lambda
  (:require [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]
            [sicp.chapter4.metacircular-evaluator.v2.procedure :refer [make-procedure]]))

(defn lambda-parameters [exp] (second exp))
(defn lambda-body [exp] (rest (rest exp)))
(defn make-lambda [parameters body]
  (cons 'lambda (cons parameters body)))

(defn lambda->procedure [exp env]
  (make-procedure (lambda-parameters exp)
                  (lambda-body exp)
                  env))

(defn install-lambda []
  (add-operation 'lambda lambda->procedure))