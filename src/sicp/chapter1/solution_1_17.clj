(ns sicp.chapter1.solution-1-17)

(defn mult [a b]
  (let [halve (fn [n]
                (/ n 2))
        double (fn [n]
                 (+ n n))]
    (cond
      (= b 0) 0
      (even? b) (recur (double a) (halve b))
      :else (+ a (mult a (dec b))))))