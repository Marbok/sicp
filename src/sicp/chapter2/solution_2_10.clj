(ns sicp.chapter2.solution-2-10
  (:require [sicp.chapter2.solution-2-7 :refer :all]))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn div-interval ^{:pre [(not (and (<= (lower-bound y) 0)
                                     (>= (upper-bound y) 0)))]}
  [x y]
  (mul-interval
    x
    (make-interval (/ 1.0 (upper-bound y))
                   (/ 1.0 (lower-bound y)))))

;^{:pre [(and (<= (lower-bound y) 0)
;             (>= (upper-bound y) 0))]}