(ns sicp.chapter1.solution-1-27-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-27 :refer [ferma]]))

(deftest ferma-test
  (is (ferma 2.0 3))
  (is (ferma 4.0 7))
  (is (not (ferma 4.0 10))))
