(ns sicp.chapter2.solution-2-2-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-2 :refer [make-point make-segment midpoint-segment]]))

(def p1 (make-point 3 5))
(def p2 (make-point 7 15))
(def s (make-segment p1 p2))
(def m (midpoint-segment s))

(deftest midpoint-segment-test
  (is (= (.x-point m) 5))
  (is (= (.y-point m) 10)))
