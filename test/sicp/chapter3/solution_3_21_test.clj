(ns sicp.chapter3.solution-3-21-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter3.solution-3-21 :refer :all]))

(deftest empty-queue?-test
  (let [q (make-queue)]
    (is (empty-queue? q))
    (is (not (empty-queue? (insert-queue! q 1))))))


(deftest front-queue-test
  (let [q (make-queue)]
    (is (= 5 (front-queue (insert-queue! q 5))))
    (is (= 5 (front-queue (insert-queue! q 6))))
    ))

(deftest delete-queue!-test
  (let [q (make-queue)]
    (is (= 10 (front-queue (delete-queue! (insert-queue!
                                            (insert-queue! q 5)
                                            10)))))))
