(ns sicp.chapter1.solution-1-18-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-18 :refer [mult]]))

(deftest mult-test
  (is (= 0 (mult 5 0)))
  (is (= 5 (mult 5 1)))
  (is (= 24 (mult 6 4)))
  (is (= 35 (mult 7 5))))
