(ns sicp.chapter1.solution-1-11-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-11 :refer :all]))


;; f(6) = f(5) + 2*f(4) + 3*f(3) = 59
;; f(5) = f(4) + 2*f(3) + 3*f(2) = 25
;; f(4) = f(3) + 2*f(2) + 3*f(1) = 11
;; f(3) = f(2) + 2*f(1) + 3*f(0) = 4

(deftest f-recur-test
  (is (= 1 (f-iter 1)))
  (is (= 4 (f-iter 3)))
  (is (= 11 (f-iter 4)))
  (is (= 25 (f-iter 5)))
  (is (= 59 (f-iter 6))))

(deftest f-iter-test
  (is (= 1 (f-iter 1)))
  (is (= 4 (f-iter 3)))
  (is (= 11 (f-iter 4)))
  (is (= 25 (f-iter 5)))
  (is (= 59 (f-iter 6))))