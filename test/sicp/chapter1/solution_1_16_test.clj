(ns sicp.chapter1.solution-1-16-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-16 :refer [expt]]))

(deftest expt-test
  (is (= 0 (expt 0 2)))
  (is (= 1 (expt 3 0)))
  (is (= 6 (expt 6 1)))
  (is (= 4 (expt 2 2)))
  (is (= 64 (expt 4 3)))
  (is (= 1220703125 (expt 5 13)))
  (is (= 81 (expt -3 4)))
  (is (= -243 (expt -3 5))))
