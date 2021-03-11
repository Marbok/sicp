(ns sicp.chapter3.solution-3-3)

(defn make-account [balance pass]
  (let [mbalance (atom balance)
        check-pass? (fn [word]
                      (= pass word))
        withdraw (fn [amount]
                   (if (>= @mbalance amount)
                     (swap! mbalance #(- % amount))
                     "Insufficient funds"))
        deposit (fn [amount]
                  (swap! mbalance #(+ % amount)))
        not-correct-pass (fn [amount]
                           "Not correct pass")
        dispatch (fn [m user-pass]
                   (cond
                     (not (check-pass? user-pass)) not-correct-pass
                     (= m 'withdraw) withdraw
                     (= m 'deposit) deposit
                     :else (throw IllegalArgumentException)))]
    dispatch))