(ns sicp.chapter2.solution-2-7)

(defprotocol Interval
  (lower-bound [this])
  (upper-bound [this]))

(defn make-interval [a b]
  (reify Interval
    (lower-bound [_] a)
    (upper-bound [_] b)))