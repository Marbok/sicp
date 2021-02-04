(ns sicp.chapter1.solution_1_7)

(defn good-enough? [guess last]
  (< (Math/abs (- guess last)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess last x]
  (if (good-enough? guess last)
    guess
    (recur (improve guess x) guess x)))

(defn root [x]
  (sqrt-iter 1.0 0 x))