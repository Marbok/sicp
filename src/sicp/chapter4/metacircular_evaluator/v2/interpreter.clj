(ns sicp.chapter4.metacircular-evaluator.v2.interpreter
  (:require [sicp.chapter4.metacircular-evaluator.v2.variable :refer [variable? analyze-variable]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [get-operation]]
            [sicp.chapter4.metacircular-evaluator.v2.environment :refer [extend-environment]]
            [sicp.chapter4.metacircular-evaluator.v2.procedure :refer [primitive-procedure? apply-primitive-procedure
                                                                       compound-procedure? procedure-body procedure-parameters
                                                                       procedure-environment]]))

(declare analyze)

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

(defn application? [exp] (seq? exp))
(defn operator [exp] (first exp))
(defn operands [exp] (rest exp))

(defn analyze-self-evaluating [exp]
  (fn [_] exp))

(defn execute-analysis [exp]
  ((get-operation (get-tag exp)) exp))

(defn execute-application [proc args]
  (cond
    (primitive-procedure? proc) (apply-primitive-procedure proc args)
    (compound-procedure? proc) ((procedure-body proc) (extend-environment
                                                        (procedure-parameters proc)
                                                        args
                                                        (procedure-environment proc)))
    :else (throw (IllegalStateException. (str "Unknown procedure type: EXECUTE-APPLICATION" proc)))))

(defn analyze-application [exp]
  (let [fproc (analyze (operator exp))
        aprocs (map analyze (operands exp))]
    (fn [env]
      (execute-application (fproc env)
                           (map (fn [aproc] (aproc env)) aprocs)))))

(defn analyze [exp]
  (cond
    (self-evaluating? exp) (analyze-self-evaluating exp)
    (variable? exp) (analyze-variable exp)
    (has-operation? exp) (execute-analysis exp)
    (application? exp) (analyze-application exp)
    :else (throw (IllegalArgumentException. (str "Unknown expression type: EVAL" exp)))))

(defn inter-eval [exp env]
  ((analyze exp) env))

