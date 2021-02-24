(ns sicp.chapter2.solution-2-8
  (:require [sicp.chapter2.solution-2-7 :refer :all]))

(defn sub-interval [i1 i2]
  (make-interval (- (lower-bound i1)
                    (lower-bound i2))
                 (- (upper-bound i1)
                    (upper-bound i2))))
