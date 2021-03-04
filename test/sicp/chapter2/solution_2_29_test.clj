(ns sicp.chapter2.solution-2-29-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter2.solution-2-29 :refer :all]))

(def m (make-mobile
         (make-branch 5 6)
         (make-branch 10
                      (make-mobile
                        (make-branch 3 8)
                        (make-branch 7 2)))))

(def m2 (make-mobile
          (make-branch 2
                       (make-mobile
                         (make-branch 4 30)
                         (make-branch 6 20)))
          (make-branch 5 20)))

(deftest total-weight-test
  (is (= 0 (total-weight nil)))
  (is (= 16 (total-weight m))))

(deftest balance?-test
  (is (false? (balance? m)))
  (is (true? (balance? m2))))
