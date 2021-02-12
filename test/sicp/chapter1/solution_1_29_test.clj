(ns sicp.chapter1.solution-1-29-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-29 :refer [integral]]))

(defn cube [x]
  (* x x x))

(deftest integral-test
  (is (= 1/4 (integral cube 0 1 1000))))
