(ns sicp.chapter2.symbol-diff)

(defn variable? [exp]
  (symbol? exp))

(defn same-variable? [v1 v2]
  (and (variable? v1) (variable? v2) (= v1 v2)))

(defn =number? [exp num]
  (and (number? exp) (= exp num)))

;; Sum

(defn make-sum [a1 a2]
  (cond
    (=number? a1 0) a2
    (=number? a2 0) a1
    (and (number? a1) (number? a2)) (+ a1 a2)
    :else (list '+ a1 a2)))

(defn addend [exp]
  (second exp))

(defn augend [exp]
  (last exp))

(defn sum? [exp]
  (and (list? exp) (= (first exp) '+)))

;; Product

(defn make-product [a1 a2]
  (cond
    (or (=number? a1 0) (=number? a2 0)) 0
    (=number? a1 1) a2
    (=number? a2 1) a1
    (and (number? a1) (number? a2)) (* a1 a2)
    :else (list '* a1 a2)))

(defn product? [exp]
  (and (list? exp) (= (first exp) '*)))

(defn multiplier [exp]
  (second exp))

(defn multiplicand [exp]
  (last exp))

;; derivation

(defn deriv [exp var]
  (cond
    (number? exp) 0
    (variable? exp) (if (same-variable? exp var) 1 0)
    (sum? exp) (make-sum (deriv (addend exp) var)
                         (deriv (augend exp) var))
    (product? exp) (make-sum
                     (make-product (multiplier exp)
                                   (deriv (multiplicand exp) var))
                     (make-product (deriv (multiplier exp) var)
                                   (multiplicand exp)))
    :else (throw IllegalArgumentException)))
