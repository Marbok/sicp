(ns sicp.chapter1.solution-1-35)

(def tolerance 0.00001)

(defn fixed-point [f first-guess]
  (let [close-enough? (fn [guess last]
                        (< (Math/abs (- guess last)) tolerance))
        try-calc (fn [guess]
                   (let [next (f guess)]
                     (if (close-enough? guess next)
                       next
                       (recur next))))]
    (try-calc first-guess)))

(def fi (fixed-point #(+ 1 (/ 1 %)) 1.0))