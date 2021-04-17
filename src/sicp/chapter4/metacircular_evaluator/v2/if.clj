(ns sicp.chapter4.metacircular-evaluator.v2.if
  (:require [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [analyze]]
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

(defn analyze-if [exp]
  (let [pproc (analyze (if-predicate exp))
        cproc (analyze (if-consequent exp))
        aproc (analyze (if-alternative exp))]
    (fn [env]
      (if (true? (pproc env))
        (cproc env)
        (aproc env)))))

(defn install-if []
  (add-operation 'if analyze-if))