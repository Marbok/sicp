(ns sicp.chapter1.solution-1-41)

(defn double-f [f]
  (fn [x]
    (f (f x))))