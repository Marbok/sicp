(ns sicp.chapter3.constraint-system.multiplier
  (:require [sicp.chapter3.constraint-system.connector :refer [has-value? get-value set-value! forget-value! connect]]))

(defn multiplier [m1 m2 product]
  (letfn [(process-new-value []
            (cond
              (or (and (has-value? m1) (= 0 (get-value m1)))
                  (and (has-value? m2) (= 0 (get-value m2))))
              (set-value! product 0 me)
              (and (has-value? m1) (has-value? m2)) (set-value! product (* (get-value m1) (get-value m2)) me)
              (and (has-value? product) (has-value? m1)) (set-value! m2 (/ (get-value product) (get-value m1)) me)
              (and (has-value? product) (has-value? m2)) (set-value! m1 (/ (get-value product) (get-value m2)) me)))
          (process-forget-value []
            (do
              (forget-value! product me)
              (forget-value! m1 me)
              (forget-value! m2 me)
              (process-new-value)))
          (me [request]
            (cond
              (= request 'I-have-a-value) (process-new-value)
              (= request 'I-lost-my-value) (process-forget-value)
              :else (throw (IllegalArgumentException. (str "Unknown request: MULTIPLIER - " request)))))]
    (do
      (connect m1 me)
      (connect m2 me)
      (connect product me)
      me)))