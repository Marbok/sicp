(ns sicp.chapter3.simulator-digit-circuits.agenda
  (:require [sicp.chapter3.solution-3-21 :refer [make-queue insert-queue! delete-queue! empty-queue?
                                                 front-queue]]))

(defrecord Agenda [time actions])

(def agenda (Agenda. (atom 0) (atom (sorted-map))))

(defn empty-agenda? []
  (empty? @(:actions agenda)))

(defn set-current-time! [time]
  (reset! (:time agenda) time))

(defn current-time []
  @(:time agenda))

(defn first-agenda-item []
  (let [actions (:actions agenda)]
    (if (empty-agenda?)
      (throw IllegalArgumentException)
      (do
        (set-current-time! (first (first @actions)))
        (front-queue (second (first @actions)))))))

(defn remove-first-agenda-item! []
  (let [actions (:actions agenda)
        queue (second (first @actions))]
    (if (empty-queue? (delete-queue! queue))
      (reset! actions (dissoc @actions (current-time))))))

(defn add-to-agenda! [time action]
  (let [actions (:actions agenda)]
    (if (nil? (get @actions time))
      (reset! actions (assoc @actions time (insert-queue! (make-queue) action)))
      (insert-queue! (get @actions time) action))))


(defn after-delay [delay action]
  (add-to-agenda! (+ delay (current-time))
                  action))