(ns sicp.chapter4.metacircular-evaluator.v2.sequence
  (:require [sicp.chapter4.metacircular-evaluator.v2.interpreter :refer [analyze]]
            [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))

(defn last-exp? [seq] (empty? (rest seq)))
(defn first-exp [seq] (first seq))
(defn make-begin [seq] (cons 'begin seq))
(defn sequence->exp [seq]
  (cond
    (empty? seq) seq
    (last-exp? seq) (first-exp seq)
    :else (make-begin seq)))

(defn analyze-sequence [exps]
  (letfn [(sequentially [proc1 proc2]
            (fn [env] (proc1 env) (proc2 env)))
          (loop [first-proc rest-procs]
            (if (empty? rest-procs)
              first-proc
              (loop (sequentially first-proc (first rest-procs))
                    (rest rest-procs))))]
    (let [procs (map analyze exps)]
      (if (empty? procs)
        (throw (IllegalStateException. "Empty sequence: ANALYZE"))
        (loop (first procs) (rest procs))))))

(defn install-begin []
  (add-operation 'begin analyze-sequence))