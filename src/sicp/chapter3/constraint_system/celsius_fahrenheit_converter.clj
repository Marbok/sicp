(ns sicp.chapter3.constraint-system.celsius-fahrenheit-converter
  (:require [sicp.chapter3.constraint-system.connector :refer :all]
            [sicp.chapter3.constraint-system.multiplier :refer :all]
            [sicp.chapter3.constraint-system.adder :refer :all]
            [sicp.chapter3.constraint-system.constant :refer :all]))

(defn celsius-fahrenheit-converter [c f]
  (let [u (make-connector)
        v (make-connector)
        w (make-connector)
        x (make-connector)
        y (make-connector)]
    (do
      (multiplier c w u)
      (multiplier v x u)
      (adder v y f)
      (constant 9 w)
      (constant 5 x)
      (constant 32 y)
      'ok)))