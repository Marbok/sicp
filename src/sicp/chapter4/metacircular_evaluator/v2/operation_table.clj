(ns sicp.chapter4.metacircular-evaluator.v2.operation-table
  (:import (java.util HashMap)))

(def operation-table (HashMap.))

(defn add-operation [name handler]
  (.put operation-table name handler))

(defn get-operation [name]
  (.get operation-table name))
