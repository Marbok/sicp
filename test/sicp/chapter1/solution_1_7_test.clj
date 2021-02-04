(ns sicp.chapter1.solution_1_7-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution_1_7 :refer [root]]))

(deftest root-test
  (testing
    (is (< (Math/abs (- (root 0) 0)) 0.001))
    (is (< (Math/abs (- (root 9) 3)) 0.001))
    (is (< (Math/abs (- (root 0.1) 0.31622)) 0.001))))
