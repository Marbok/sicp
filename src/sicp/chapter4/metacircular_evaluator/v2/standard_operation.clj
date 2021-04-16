(ns sicp.chapter4.metacircular-evaluator.v2.standard-operation
  (:require [sicp.chapter4.metacircular-evaluator.v2.quote :refer [install-quote]]
            [sicp.chapter4.metacircular-evaluator.v2.assignment :refer [install-assignment]]
            [sicp.chapter4.metacircular-evaluator.v2.definition :refer [install-definition]]
            [sicp.chapter4.metacircular-evaluator.v2.if :refer [install-if]]
            [sicp.chapter4.metacircular-evaluator.v2.lambda :refer [install-lambda]]
            [sicp.chapter4.metacircular-evaluator.v2.sequence :refer [install-begin]]
            [sicp.chapter4.metacircular-evaluator.v2.cond :refer [install-cond]]))

(defn install-soperation []
  (do
    (install-quote)
    (install-assignment)
    (install-definition)
    (install-if)
    (install-lambda)
    (install-begin)
    (install-cond)))