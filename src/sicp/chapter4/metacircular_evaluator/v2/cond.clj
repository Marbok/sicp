(ns sicp.chapter4.metacircular-evaluator.v2.cond
  (:require [sicp.chapter4.metacircular-evaluator.v2.if :refer [make-if]]
            [sicp.chapter4.metacircular-evaluator.v2.sequence :refer [sequence->exp]]
            [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [inter-eval]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))


(defn cond-clauses [exp] (rest exp))
(defn cond-predicate [clause] (first clause))
(defn cond-else-clause? [clause]
  (= (cond-predicate clause) 'else))
(defn cond-action [clause] (rest clause))
(defn expand-clauses [clauses]
  (if (empty? clauses)
    'false
    (let [first (first clauses)
          rest (rest clauses)]
      (if (cond-else-clause? first)
        (if (empty? rest)
          (sequence->exp (cond-action first))
          (throw (IllegalStateException. (str "ELSE clause isn't last: COND->IF" clauses))))
        (make-if (cond-predicate first)
                 (sequence->exp (cond-action first))
                 (expand-clauses rest))))))
(defn cond->if [exp] (expand-clauses (cond-clauses exp)))

(defn eval-cond [exp env]
  (inter-eval (cond->if exp) env))

(defn install-cond []
  (add-operation 'cond eval-cond))