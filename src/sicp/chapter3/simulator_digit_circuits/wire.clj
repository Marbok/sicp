(ns sicp.chapter3.simulator-digit-circuits.wire)

(defn call-each [actions]
  (if (not (empty? actions))
    (do
      ((first actions))
      (recur (rest actions)))))


(defn make-wire []
  (let [signal-value (atom 0)
        actions (atom '())
        set-my-signal! (fn [new-value]
                         (if (not (= new-value @signal-value))
                           (do
                             (reset! signal-value new-value)
                             (call-each @actions))))
        accept-action (fn [action]
                        (do
                          (reset! actions (conj @actions action))
                          (action)))
        dispatch (fn [m]
                   (cond
                     (= m 'get-signal) @signal-value
                     (= m 'set-signal!) set-my-signal!
                     (= m 'add-action!) accept-action
                     :else IllegalArgumentException))]
    dispatch))


(defn get-signal [wire]
  (wire 'get-signal))

(defn set-signal! ^{:pre [(or (= 0 value) (= 1 value))]}
  [wire value]
  ((wire 'set-signal!) value))

(defn add-action! [wire action]
  ((wire 'add-action!) action))
