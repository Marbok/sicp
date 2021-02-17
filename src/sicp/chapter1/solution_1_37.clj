(ns sicp.chapter1.solution-1-37)

(defn cont-frac-recur [n d k]
  (let [iter (fn [i]
               (if (= i k)
                 (/ (n i) (d i))
                 (/ (n i) (+ (d i) (cont-frac-recur n d (dec k))))))]
    (iter 1)))

(defn cont-frac [n d k]
  (let [iter (fn [i acc]
               (if (= i 0)
                 acc
                 (recur (dec i)
                        (/ (n i) (+ (d i) acc)))))]
    (iter k 0)))
