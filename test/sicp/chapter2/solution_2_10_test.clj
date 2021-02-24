(ns sicp.chapter2.solution-2-10-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-7 :refer :all])
  (:require [sicp.chapter2.solution-2-10 :refer [div-interval]]))

(def x (make-interval -5 6))
(def y (make-interval -1 1))

(deftest div-interval-test
  (is (thrown? AssertionError (div-interval x y))))
