(ns sicp.chapter2.solution-2-42-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-42 :refer [queens]]))

(deftest queens-test
  (is (= '(((3 4) (1 3) (4 2) (2 1)) ((2 4) (4 3) (1 2) (3 1))) (queens 4))))
