(ns sicp.chapter1.solution-1-41-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-41 :refer [double-f]]))

(deftest double-f-test
  (is (= 3 ((double-f inc) 1))))
