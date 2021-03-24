(ns sicp.chapter3.simulator-digit-circuits.probe
  (:require [sicp.chapter3.simulator-digit-circuits.wire :refer [add-action! get-signal]]
            [sicp.chapter3.simulator-digit-circuits.agenda :refer [current-time]]))

(defn probe [name wire]
  (add-action! wire
               #(println name (current-time) "New-value =" (get-signal wire))))