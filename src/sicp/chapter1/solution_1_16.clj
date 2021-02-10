(ns sicp.chapter1.solution-1-16)

(defn expt [b n]
  (let [expt-iter (fn [b n a]
                    (cond
                      (= n 0) a
                      (even? n) (recur (* b b) (/ n 2) a)
                      :else (recur b (- n 1) (* a b))))]
    (expt-iter b n 1)))
