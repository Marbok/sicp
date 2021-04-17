(ns sicp.chapter4.metacircular-evaluator.v2.definition
  (:require [sicp.chapter4.metacircular-evaluator.v2.variable :refer [define-variable!]]
            [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [analyze]]
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

(defn analyze-definition [exp]
  (let [var (definition-variable exp)
        vproc (analyze (definition-value exp))]
    (fn [env]
      (define-variable! var (vproc env) env))))

(defn install-definition []
  (add-operation 'define analyze-definition))
