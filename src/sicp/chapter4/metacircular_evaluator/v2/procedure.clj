(ns sicp.chapter4.metacircular-evaluator.v2.procedure)

(defn tagged-list? [exp tag]
  (if (seq? exp)
    (= (first exp) tag)
    false))

(def primitive-procedure
  (list
    (list 'car first)
    (list 'cdr rest)
    (list 'cons cons)
    (list 'null? nil?)
    (list 'empty? empty?)
    (list '+ +)))
(defn primitive-procedure-names []
  (map first primitive-procedure))
(defn primitive-procedure-objects []
  (map #(list 'primitive (second %)) primitive-procedure))

(defn make-procedure [parameters body env]
  (list 'procedure parameters body env))
(defn compound-procedure? [p]
  (tagged-list? p 'procedure))
(defn procedure-parameters [p] (second p))
(defn procedure-body [p] (first (rest (rest p))))
(defn procedure-environment [p] (first (rest (rest (rest p)))))

(defn primitive-procedure? [proc]
  (tagged-list? proc 'primitive))
(defn primitive-implementation [proc] (second proc))

(defn apply-primitive-procedure [proc args]
  (apply (primitive-implementation proc) args))