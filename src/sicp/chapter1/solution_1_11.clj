(ns sicp.chapter1.solution-1-11)

(defn f-recur [n]
  (if (< n 3)
    n
    (+ (f-recur (- n 1))
       (* 2 (f-recur (- n 2)))
       (* 3 (f-recur (- n 3))))))

(defn f-iter [n]
  (let [iter (fn [a b c i]
               (if (= i n)
                 a
                 (recur (+ a (* 2 b) (* 3 c)) a b (inc i))))]
    (if (< n 3)
      n
      (iter 2 1 0 2))))