(ns sicp.chapter3.solution-3-1)

(defn make-accumulator [num]
  (let [acc (atom num)]
    (fn [plus]
      (do
        (swap! acc #(+ plus %))
        @acc))))
