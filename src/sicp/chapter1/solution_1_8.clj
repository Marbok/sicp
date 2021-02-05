(ns sicp.chapter1.solution_1_8)

(defn good-enough? [guess last]
  (< (Math/abs (- guess last)) 0.00001))

(defn improve [guess x]
  (/ (+ (/ x (Math/pow guess 2)) (* 2 guess)) 3))

(defn cube-iter [guess last x]
  (if (good-enough? guess last)
    guess
    (recur (improve guess x) guess x)))

(defn cube-root [x]
  (cube-iter 1.0 0 x))
