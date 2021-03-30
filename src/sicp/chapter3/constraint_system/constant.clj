(ns sicp.chapter3.constraint-system.constant
  (:require [sicp.chapter3.constraint-system.connector :refer [connect set-value!]]))

(defn constant [value connector]
  (letfn [(me [_]
            (throw (IllegalArgumentException. "Unknown request: CONSTANT")))]
    (do
      (connect connector me)
      (set-value! connector value me)
      me)))