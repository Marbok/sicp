(ns sicp.chapter1.solution-1-38
  (:require [sicp.chapter1.solution-1-37 :refer [cont-frac]]))

(defn euler [k]
  (let [d (fn [k]
            (if (= (rem k 3) 2)
              (/ (+ k 1) 1.5)
              1))]
    (cont-frac (constantly 1.0) d k)))
