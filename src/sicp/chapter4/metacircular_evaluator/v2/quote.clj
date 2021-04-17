(ns sicp.chapter4.metacircular-evaluator.v2.quote
  (:require [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))

(defn text-of-quotation [exp]
  (second exp))

(defn analyze-quoted [exp]
  (let [qval (text-of-quotation exp)]
    (fn [_] qval)))

(defn install-quote []
  (add-operation 'quote analyze-quoted))