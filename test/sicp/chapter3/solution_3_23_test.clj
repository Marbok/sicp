(ns sicp.chapter3.solution-3-23-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter3.solution-3-23 :refer :all]))

(deftest empty-deque?-test
  (let [q (make-deque)]
    (is (empty-deque? q))
    (is (not (empty-deque? (front-insert-deque! q 1))))))


(deftest front-deque-test
  (let [q (make-deque)]
    (is (= 5 (front-deque (front-insert-deque! q 5))))
    (is (= 5 (front-deque (rear-insert-deque! q 6))))
    ))

(deftest rear-deque-test
  (let [q (make-deque)]
    (is (= 5 (rear-deque (rear-insert-deque! q 5))))
    (is (= 5 (rear-deque (front-insert-deque! q 6))))
    ))

(deftest front-delete-deque!-test
  (let [q (make-deque)]
    (is (= 5 (front-deque (front-delete-deque! (front-insert-deque!
                                                  (front-insert-deque! q 5)
                                                  10)))))))

(deftest rear-delete-deque!-test
  (let [q (make-deque)]
    (is (= 10 (rear-deque (rear-delete-deque! (front-insert-deque!
                                                 (front-insert-deque! q 5)
                                                 10)))))))
