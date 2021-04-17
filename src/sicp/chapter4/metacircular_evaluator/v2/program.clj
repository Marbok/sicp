(ns sicp.chapter4.metacircular-evaluator.v2.program
  (:require [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [inter-eval]]
            [sicp.chapter4.metacircular-evaluator.v2.environment :refer [extend-environment
                                                                         the-empty-environment]]
            [sicp.chapter4.metacircular-evaluator.v2.variable :refer [define-variable!]]
            [sicp.chapter4.metacircular-evaluator.v2.procedure :refer [primitive-procedure-names
                                                                       primitive-procedure-objects
                                                                       compound-procedure?
                                                                       procedure-parameters
                                                                       procedure-body]]
            [sicp.chapter4.metacircular-evaluator.v2.standard-operation :refer [install-soperation]]))

(install-soperation)

(defn setup-environment []
  (let [initial-env (extend-environment (primitive-procedure-names)
                                        (primitive-procedure-objects)
                                        the-empty-environment)]
    (do
      (define-variable! 'true true initial-env)
      (define-variable! 'false false initial-env)
      initial-env)))

(def the-global-environment (setup-environment))

(def input-prompt ";;; M-Eval input:")
(def output-prompt ";;; M-Eval value")
(defn prompt-for-input [string]
  (do
    (println)
    (println)
    (println string)
    (println)))
(defn announce-output [string]
  (do
    (println)
    (println string)
    (println)))
(defn user-print [object]
  (if (compound-procedure? object)
    (println 'compound-procedure
             (procedure-parameters object)
             (procedure-body object)
             '<procedure-env>)
    (println object)))
(defn driver-loop []
  (do
    (prompt-for-input input-prompt)
    (let [input (read)]
      (let [output (inter-eval input the-global-environment)]
        (do
          (announce-output output-prompt)
          (user-print output))))
    (driver-loop)))

(driver-loop)