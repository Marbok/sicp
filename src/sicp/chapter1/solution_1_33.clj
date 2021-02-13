(ns sicp.chapter1.solution-1-33
  (:require [sicp.chapter1.solution-1-22 :refer [prime?]]))

(defn filtered-accumulate [combiner pred? null-value term a next b]
  (let [iter (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (if (pred? a)
                                   (combiner (term a) result)
                                   result))))]
    (iter a null-value)))

(defn sum-sqr-prime [a b]
  (filtered-accumulate + prime? 0 #(* % %) a inc b))

(defn gcd [m n]
  (cond (< m n) (gcd n m)
        (= n 0) m
        :else (gcd n (rem m n))))

(defn relative-prime? [m n]
  (= (gcd m n) 1))

(defn product-of-relative-prime [n]
  (let [filter? (fn [x]
                  (relative-prime? x n))]
    (filtered-accumulate * filter? 1 identity 1 inc n)))