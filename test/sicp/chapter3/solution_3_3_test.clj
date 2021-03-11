(ns sicp.chapter3.solution-3-3-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter3.solution-3-3 :refer [make-account]]))

(def acc (make-account 100 'password))

(deftest make-account-test
  (is (= "Not correct pass" ((acc 'deposit 'pass) 50)))
  (is (= 150 ((acc 'deposit 'password) 50))))
