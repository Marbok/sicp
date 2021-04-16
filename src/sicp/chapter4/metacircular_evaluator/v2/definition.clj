(ns sicp.chapter4.metacircular-evaluator.v2.definition
  (:require [sicp.chapter4.metacircular-evaluator.v2.variable :refer [define-variable!]]
            [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [inter-eval]]
            [sicp.chapter4.metacircular-evaluator.v2.lambda :refer [make-lambda]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))

(defn definition-variable [exp]
  (if (symbol? (second exp))
    (second exp)
    (first (first (rest exp)))))

(defn definition-value [exp]
  (if (symbol? (second exp))
    (first (rest (rest exp)))
    (make-lambda (rest (first (rest exp)))
                 (rest (rest exp)))))

(defn eval-definition [exp env]
  (define-variable! (definition-variable exp)
                    (inter-eval (definition-value exp) env)
                    env)
  'ok)

(defn install-definition []
  (add-operation 'define eval-definition))
