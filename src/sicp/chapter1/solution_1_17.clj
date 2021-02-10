(ns sicp.chapter1.solution-1-17)

(defn mult [a b]
  (let [halve (fn [n]
                (/ n 2))
        double (fn [n]
                 (+ n n))
        mult-iter (fn [a b acc]
                    (cond
                      (= b 0) acc
                      (even? b) (recur (double a) (halve b) acc)
                      :else (recur a (dec b) (+ acc a))))]
    (mult-iter a b 0)))