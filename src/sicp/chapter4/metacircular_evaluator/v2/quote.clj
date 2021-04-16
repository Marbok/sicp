(ns sicp.chapter4.metacircular-evaluator.v2.quote
  (:require [sicp.chapter4.metacircular-evaluator.v2.operation-table :refer [add-operation]]))

(defn text-of-quotation [exp _]
  (second exp))

(defn install-quote []
  (add-operation 'quote text-of-quotation))