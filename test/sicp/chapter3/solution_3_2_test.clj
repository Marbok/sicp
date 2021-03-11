(ns sicp.chapter3.solution-3-2-test
  (:require [clojure.test :refer :all])
  (:require [sicp.chapter3.solution-3-2 :refer [make-monitor]]))

(def s (make-monitor #(Math/sqrt %)))

(deftest make-monitor-test
  (is (= 0 (s 'how-many-calls?)))
  (is (= 3.0 (s 9)))
  (is (= 1 (s 'how-many-calls?)))
  (is (= 0 (s 'reset-count)))
  (is (= 0 (s 'how-many-calls?))))
