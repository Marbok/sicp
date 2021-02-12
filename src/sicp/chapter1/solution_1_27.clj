(ns sicp.chapter1.solution-1-27)

(defn ferma [a n]
  (= (rem (Math/pow a n) n)
     (rem a n)))