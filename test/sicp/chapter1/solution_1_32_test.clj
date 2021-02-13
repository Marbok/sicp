(ns sicp.chapter1.solution-1-32-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter1.solution-1-32 :refer [sum product]]))

(deftest sum-test
  (is (= 55 (sum identity 0 inc 10)))
  (is (= 3025 (sum #(* % % %) 1 inc 10))))

(deftest product-test
  (is (= 3628800 (product identity 1 inc 10))))
