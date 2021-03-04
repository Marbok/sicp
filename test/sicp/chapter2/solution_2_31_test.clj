(ns sicp.chapter2.solution-2-31-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-31 :refer [tree-map]]))

(def tree (list 5
                (list (list 3 4)
                      (list 6 2))))

(def square-tree (tree-map #(* % %) tree))

(deftest tree-map-test
  (is (= 25 (first square-tree)))
  (is (= 9 (first (first (second square-tree)))))
  (is (= 16 (second (first (second square-tree)))))
  (is (= 36 (first (second (second square-tree)))))
  (is (= 4 (second (second (second square-tree))))))
