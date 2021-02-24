(ns sicp.chapter2.solution-2-17-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-17 :refer [last-pair]]))

(deftest last-pair-test
  (is (= 6 (last-pair '(1 2 3 4 5 6)))))
