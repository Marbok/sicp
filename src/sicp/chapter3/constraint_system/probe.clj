(ns sicp.chapter3.constraint-system.probe
  (:require [sicp.chapter3.constraint-system.connector :refer [get-value connect]]))

(defn probe [name connector]
  (letfn [(print-probe [value]
            (println "Probe: " name " = " value))
          (process-new-value []
            (print-probe (get-value connector)))
          (process-forget-value []
            (print-probe "?"))
          (me [request]
            (cond
              (= request 'I-have-a-value) (process-new-value)
              (= request 'I-lost-my-value) (process-forget-value)
              :else (throw (IllegalArgumentException. "Unknown request: PROBE"))))]
    (do
      (connect connector me)
      me)))