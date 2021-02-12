(ns sicp.chapter1.solution-1-30)

(defn sum [term a next b]
  (let [iter (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (+ (term a) result))))]
    (iter a 0)))