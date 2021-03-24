(ns sicp.chapter3.simulator-digit-circuits.simulator
  (:require [sicp.chapter3.simulator-digit-circuits.agenda
             :refer
             [empty-agenda? first-agenda-item remove-first-agenda-item! current-time]]))

(defn propagate []
  (if (empty-agenda?)
    'done
    (do
      ((first-agenda-item))
      (remove-first-agenda-item!)
      (recur))))