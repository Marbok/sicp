(ns sicp.chapter3.solution-3-1-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter3.solution-3-1 :refer [make-accumulator]]))

(def acc (make-accumulator 10))

(deftest make-accumulator-test
  (is (= 15 (acc 5)))
  (is (= 20 (acc 5))))
