(ns sicp.chapter3.solution-3-2)

(defn make-monitor [f]
  (let [counter (atom 0)]
    (fn [arg]
      (cond
        (= arg 'how-many-calls?) @counter
        (= arg 'reset-count) (reset! counter 0)
        :else (do
                (swap! counter inc)
                (f arg))))))