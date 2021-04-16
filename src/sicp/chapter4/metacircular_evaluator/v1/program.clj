(ns sicp.chapter4.metacircular-evaluator.v1.program
  (:require [sicp.chapter4.metacircular-evaluator.v1.interpreter :refer [inter-eval
                                                                      compound-procedure?
                                                                      procedure-parameters
                                                                      procedure-body
                                                                      setup-environment]]))

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
    (println)
    ))
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