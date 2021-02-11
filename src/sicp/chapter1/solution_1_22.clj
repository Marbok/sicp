(ns sicp.chapter1.solution-1-22
  (:import (java.time Instant Duration)))

(defn divides? [a b]
  (= (rem b a) 0))

(defn find-divisor [n test-divisor]
  (cond
    (> (* test-divisor test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn report-time [n elapsed-time]
  (println n " *** " elapsed-time))

(defn get-time []
  (Instant/now))

(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-time n (.toNanos (Duration/between start-time (get-time))))))

(defn timed-prime-test [n]
  (start-prime-test n (get-time)))

(defn search-for-primes [n1 n2]
  (cond
    (even? n1) (recur (+ n1 1) n2)
    (< n1 n2) (do
                (timed-prime-test n1)
                (recur (+ n1 2) n2))))