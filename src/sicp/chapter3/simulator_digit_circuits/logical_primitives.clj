(ns sicp.chapter3.simulator-digit-circuits.logical_primitives
  (:require [sicp.chapter3.simulator-digit-circuits.agenda :refer [after-delay]])
  (:require [sicp.chapter3.simulator-digit-circuits.wire :refer [get-signal set-signal! add-action!]]))

;;------- Parameters --------
(def inverter-delay (atom 1))
(def and-gate-delay (atom 1))
(def or-gate-delay (atom 1))

(defn set-inverter-delay! [delay]
  (reset! inverter-delay delay))

(defn set-and-gate-delay! [delay]
  (reset! and-gate-delay delay))

(defn set-or-gate-delay! [delay]
  (reset! or-gate-delay delay))

;; --------------- Components ------------------------
(defn inverter [input output]
  (let [invert-input (fn []
                       (let [new-value (bit-xor 1 (get-signal input))]
                         (after-delay @inverter-delay
                                      #(set-signal! output
                                                    new-value))))]
    (add-action! input invert-input)))

(defn and-gate [input1 input2 output]
  (let [and-action (fn []
                     (let [new-value (bit-and (get-signal input1) (get-signal input2))]
                       (after-delay @and-gate-delay
                                    #(set-signal! output new-value))))]
    (do
      (add-action! input1 and-action)
      (add-action! input2 and-action))))

(defn or-gate [input1 input2 output]
  (let [or-action (fn []
                    (let [new-value (bit-or (get-signal input1) (get-signal input2))]
                      (after-delay @or-gate-delay
                                   #(set-signal! output new-value))))]
    (do
      (add-action! input1 or-action)
      (add-action! input2 or-action))))