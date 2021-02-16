(ns sicp.chapter1.solution-1-36)

(def tolerance 0.00001)

(defn fixed-point [f first-guess]
  (let [close-enough? (fn [guess last]
                        (< (Math/abs (- guess last)) tolerance))
        try-calc (fn [guess]
                   (let [next (f guess)]
                     (do
                       (println next)
                       (if (close-enough? guess next)
                         next
                         (recur next)))))]
    (try-calc first-guess)))

(defn f [x]
  (/ (Math/log 1000) (Math/log x)))

(defn average-damp [f]
  (fn [x]
    (/ (+ x (f x)) 2)))

(def f-avg (average-damp f))