(ns sicp.chapter3.solution-3-3-4)

(defn make-security [pass f]
  (let [police-count (atom 0)
        check-pass? (fn [word]
                      (= pass word))
        call-the-cops (fn [_]
                        "CALL POLICE!!!")
        not-correct-pass (fn [_]
                           "Not correct pass")
        correct-pass (fn [_]
                       "Correct pass")
        dispatch (fn [m user-pass]
                   (if (check-pass? user-pass)
                     (do
                       (reset! police-count 0)
                       (cond
                         (= m 'check-pass) correct-pass
                         :else (f m)))
                     (if (= (swap! police-count inc) 7)
                       call-the-cops
                       not-correct-pass)))]
    dispatch))

(defn make-account [balance pass]
  (let [mbalance (atom balance)
        withdraw (fn [amount]
                   (if (>= @mbalance amount)
                     (swap! mbalance #(- % amount))
                     "Insufficient funds"))
        deposit (fn [amount]
                  (swap! mbalance #(+ % amount)))
        dispatch (fn [m]
                   (cond
                     (= m 'withdraw) withdraw
                     (= m 'deposit) deposit
                     :else (throw IllegalArgumentException)))]
    (make-security pass dispatch)))