(ns sicp.chapter1.solution-1-31)

(defn product-recur [term a next b]
  (if (> a b)
    1
    (* (term a)
       (product-recur term (next a) next b))))

(defn product-iter [term a next b]
  (let [iter (fn [a result]
               (if (> a b)
                 result
                 (recur (next a) (* (term a) result))))]
    (iter a 1)))

(defn factorial-iter [n]
  (product-iter identity 1 inc n))

(defn factorial-recur [n]
  (product-recur identity 1 inc n))

(defn pi4 [n]
  (let [term (fn [x]
               (if (even? x)
                 (double (/ (+ x 2) (inc x)))
                 (double (/ (inc x) (+ x 2)))))]
    (product-iter term 1 inc n)))