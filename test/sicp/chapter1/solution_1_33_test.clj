(ns sicp.chapter1.solution-1-33-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-33 :refer [sum-sqr-prime product-of-relative-prime]]))

(deftest sum-sqr-prime-test
  (is (= 87 (sum-sqr-prime 2 10))))

(deftest product-of-relative-prime-test
  (is (= 189 (product-of-relative-prime 10))))
