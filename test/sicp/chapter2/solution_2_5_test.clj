(ns sicp.chapter2.solution-2-5-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-5 :refer :all]))

(def p1 (make-pair 4 5))
(def p2 (make-pair 3 6))

(deftest first-pair-test
  (is (= 4 (first-pair p1)))
  (is (= 3 (first-pair p2))))

(deftest second-pair-test
  (is (= 5 (second-pair p1)))
  (is (= 6 (second-pair p2))))
