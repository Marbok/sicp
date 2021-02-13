(ns sicp.chapter1.solution-1-32)

(defn accumulate-r [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate-r combiner null-value term (next a) next b))))

(defn accumulate [combiner null-value term a next b]
  (let [iter (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (combiner (term a) result))))]
    (iter a null-value)))

(defn sum [term a next b]
  (accumulate + 0 term a next b))

(defn product [term a next b]
  (accumulate * 1 term a next b))