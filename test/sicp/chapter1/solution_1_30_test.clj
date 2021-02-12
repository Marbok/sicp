(ns sicp.chapter1.solution-1-30-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-30 :refer [sum]]))

(defn indentity [x] x)

(defn cube [x]
  (* x x x))

(deftest sum-test
  (is (= 55 (sum identity 0 inc 10)))
  (is (= 3025 (sum cube 1 inc 10))))
