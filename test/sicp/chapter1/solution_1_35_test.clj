(ns sicp.chapter1.solution-1-35-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-35 :refer :all]))

(deftest fi-test
  (is (< (- fi 1.6180) 0.001)))
