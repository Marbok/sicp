(ns sicp.chapter2.solution-2-8-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-7 :refer :all])
  (:require [sicp.chapter2.solution-2-8 :refer [sub-interval]]))

(def i1 (make-interval 3 4))
(def i2 (make-interval 6 2))
(def result (sub-interval i1 i2))

(deftest sub-interval-test
  (is (= -2 (lower-bound result)))
  (is (= 1 (upper-bound result))))
