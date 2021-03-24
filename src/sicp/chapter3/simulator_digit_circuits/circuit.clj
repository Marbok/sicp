(ns sicp.chapter3.simulator-digit-circuits.circuit
  (:require [sicp.chapter3.simulator-digit-circuits.wire :refer [make-wire set-signal!]]
            [sicp.chapter3.simulator-digit-circuits.logical_primitives :refer :all]
            [sicp.chapter3.simulator-digit-circuits.adders :refer :all]
            [sicp.chapter3.simulator-digit-circuits.probe :refer :all]
            [sicp.chapter3.simulator-digit-circuits.simulator :refer :all]))

(set-inverter-delay! 2)
(set-and-gate-delay! 3)
(set-or-gate-delay! 5)

(def input1 (make-wire))
(def input2 (make-wire))
(def sum (make-wire))
(def carry (make-wire))

(probe 'sum sum)
(probe 'carry carry)

(half-adder input1 input2 sum carry)

(set-signal! input1 1)

(propagate)

(set-signal! input2 1)

(propagate)