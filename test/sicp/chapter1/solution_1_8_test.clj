(ns sicp.chapter1.solution_1_8-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution_1_8 :refer [cube-root]]))

(deftest improve-test
  (is (< (Math/abs (- (cube-root 0) 0)) 0.0001))
  (is (< (Math/abs (- (cube-root 27) 3)) 0.0001))
  (is (< (Math/abs (- (cube-root 0.1) 0.4642)) 0.0001)))
