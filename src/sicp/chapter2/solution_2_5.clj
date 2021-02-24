(ns sicp.chapter2.solution-2-5)

(defn log [b a]
  (Math/round (/ (Math/log a) (Math/log b))))

(defn make-pair [a b]
  (* (Math/pow 2 a) (Math/pow 3 b)))

(defn first-pair [p]
  (if (= (rem p 3) 0.0)
    (first-pair (/ p 3))
    (log 2 p)))

(defn second-pair [p]
  (if (= (rem p 2) 0.0)
    (second-pair (/ p 2))
    (log 3 p)))