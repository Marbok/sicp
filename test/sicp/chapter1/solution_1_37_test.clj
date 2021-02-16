(ns sicp.chapter1.solution-1-37-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-37 :refer [cont-frac-recur cont-frac]]))

(deftest cont-frac-recur-test
  (is (< (- (cont-frac-recur (constantly 1.0) (constantly 1.0) 1000) 1.6180) 0.001)))

(deftest cont-frac-test
  (is (< (- (cont-frac (constantly 1.0) (constantly 1.0) 1000) 1.6180) 0.001)))
