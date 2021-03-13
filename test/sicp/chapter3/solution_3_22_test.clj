(ns sicp.chapter3.solution-3-22-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter3.solution-3-22 :refer [make-queue]]))

(deftest make-queue-empty?-test
  (let [q (make-queue)]
    (is ((q 'empty?)))
    (is (not (do
               ((q 'insert!) 1)
               ((q 'empty?)))))))

(deftest make-queue-front-test
  (let [q (make-queue)]
    (is (= 5 (do
               ((q 'insert!) 5)
               ((q 'front)))))
    (is (= 5 (do
               ((q 'insert!) 6)
               ((q 'front)))))))


(deftest make-queue-delete!-test
  (let [q (make-queue)]
    (is (= 10 (do
                ((q 'insert!) 5)
                ((q 'insert!) 10)
                ((q 'delete!))
                ((q 'front)))))))
