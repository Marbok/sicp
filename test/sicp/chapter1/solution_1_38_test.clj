(ns sicp.chapter1.solution-1-38-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-38 :refer [euler]]))

(deftest euler-test
  (is (< (- (euler 10) 0.71828) 0.0001)))
