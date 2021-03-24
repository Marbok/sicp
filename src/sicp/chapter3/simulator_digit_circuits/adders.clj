(ns sicp.chapter3.simulator-digit-circuits.adders
  (:require [sicp.chapter3.simulator-digit-circuits.wire :refer [make-wire]]
            [sicp.chapter3.simulator-digit-circuits.logical_primitives :refer :all]))

(defn half-adder [input1 input2 sum carry]
  (let [d (make-wire)
        e (make-wire)]
    (do
      (or-gate input1 input2 d)
      (and-gate input1 input2 carry)
      (inverter carry e)
      (and-gate d e sum))))