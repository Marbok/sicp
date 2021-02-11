(ns sicp.chapter1.solution-1-23)

(defn divides? [a b]
  (= (rem b a) 0))

(defn next [test-divisor]
  (if (= test-divisor 2)
    3
    (+ test-divisor 2)))

(defn find-divisor [n test-divisor]
  (cond
    (> (* test-divisor test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (recur n (next test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))