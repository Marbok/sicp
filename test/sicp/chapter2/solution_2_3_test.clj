(ns sicp.chapter2.solution-2-3-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-3 :refer :all])
  (:require [sicp.chapter2.solution-2-2 :refer :all]))

(def p1 (make-point 5 5))
(def p2 (make-point 10 10))
(def rec (make-rect-point p1 p2))

(deftest perimeter-test
  (is (= 20.0 (perimeter rec))))

(deftest area-test
  (is (= 25.0 (area rec))))
