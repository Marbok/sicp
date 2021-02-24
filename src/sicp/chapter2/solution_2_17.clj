(ns sicp.chapter2.solution-2-17)

(defn last-pair [lst]
  (if (next lst)
    (recur (next lst))
    (first lst)))