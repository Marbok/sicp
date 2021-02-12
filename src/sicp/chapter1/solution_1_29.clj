(ns sicp.chapter1.solution-1-29)

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn integral [f a b n]
  (let [h (/ (- b a) n)
        y (fn [k]
            (f (+ a (* h k))))
        term (fn [k]
               (cond
                 (or (= k n) (= k 0)) (y k)
                 (even? k) (* 2 (y k))
                 :else (* 4 (y k))))
        next (fn [k] (inc k))]
    (* (/ h 3) (sum term 0 next n))))