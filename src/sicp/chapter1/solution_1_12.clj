(ns sicp.chapter1.solution-1-12)

(defn pascal [r c]
  (cond
    (= c 1) 1
    (= r c) 1
    :else (+ (pascal (- r 1) (- c 1))
             (pascal (- r 1) c))))