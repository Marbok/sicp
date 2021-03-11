(ns sicp.chapter3.solution-3-3-4)

(defn make-account [balance pass]
  (let [mbalance (atom balance)
        police-count (atom 0)
        check-pass? (fn [word]
                      (= pass word))
        withdraw (fn [amount]
                   (if (>= @mbalance amount)
                     (swap! mbalance #(- % amount))
                     "Insufficient funds"))
        deposit (fn [amount]
                  (swap! mbalance #(+ % amount)))
        call-the-cops (fn [amount]
                        "CALL POLICE!!!")
        not-correct-pass (fn [amount]
                           "Not correct pass")
        dispatch (fn [m user-pass]
                   (if (check-pass? user-pass)
                     (do
                       (reset! police-count 0)
                       (cond
                         (= m 'withdraw) withdraw
                         (= m 'deposit) deposit
                         :else (throw IllegalArgumentException)))
                     (if (= (swap! police-count inc) 7)
                       call-the-cops
                       not-correct-pass)))]
    dispatch))