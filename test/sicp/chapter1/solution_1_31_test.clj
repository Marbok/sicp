(ns sicp.chapter1.solution-1-31-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-31 :refer [factorial-iter factorial-recur]]))

(deftest factorial-iter-test
  (is (= 1 (factorial-iter 0)))
  (is (= 120 (factorial-iter 5))))

(deftest factorial-recur-test
  (is (= 1 (factorial-recur 0)))
  (is (= 120 (factorial-recur 5))))
