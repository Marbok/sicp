(ns sicp.chapter3.constraint-system.adder
  (:require [sicp.chapter3.constraint-system.connector :refer [has-value? get-value forget-value! set-value! connect]]))

(defn adder [m1 m2 sum]
  (letfn [(process-new-value []
            (cond
              (and (has-value? m1) (has-value? m2)) (set-value! sum (+ (get-value m1) (get-value m2)) me)
              (and (has-value? m1) (has-value? sum)) (set-value! m2 (- (get-value sum) (get-value m1)) me)
              (and (has-value? m2) (has-value? sum)) (set-value! m1 (- (get-value sum) (get-value m2)) me)))
          (process-forget-value []
            (do
              (forget-value! sum me)
              (forget-value! m1 me)
              (forget-value! m2 me)
              (process-new-value)))
          (me [request]
            (cond
              (= request 'I-have-a-value) (process-new-value)
              (= request 'I-lost-my-value) (process-forget-value)
              :else (throw (IllegalArgumentException. "Unknown request: ADDER"))))]
    (do
      (connect m1 me)
      (connect m2 me)
      (connect sum me)
      me)))