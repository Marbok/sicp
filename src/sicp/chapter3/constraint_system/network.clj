(ns sicp.chapter3.constraint-system.network
  (:require [sicp.chapter3.constraint-system.connector :refer [make-connector set-value! forget-value!]]
            [sicp.chapter3.constraint-system.celsius-fahrenheit-converter :refer :all]
            [sicp.chapter3.constraint-system.probe :refer :all]))

(def C (make-connector))
(def F (make-connector))
(celsius-fahrenheit-converter C F)

(probe "Celsius temp" C)
(probe "Fahrenheit temp" F)

(set-value! C 25 'user)

(println "-----------------------------------")

(set-value! F 212 'user)

(println "-----------------------------------")

(forget-value! C 'user)

(println "-----------------------------------")

(set-value! F 212 'user)

(println "-----------------------------------")