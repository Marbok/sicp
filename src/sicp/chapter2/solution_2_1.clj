(ns sicp.chapter2.solution-2-1)

(defrecord Rat [num den])

(defn make-rat [num den]
  (cond
    (and (neg? num) (neg? den)) (Rat. (- num) (- den))
    (neg? den) (Rat. (- num) (- den))
    :else (Rat. num den)))

(defn numer [rat]
  (:num rat))

(defn denom [rat]
  (:den rat))

(defn print-rat [rat]
  (println (numer rat) "/" (denom rat)))

(def one-half (make-rat 1 2))
(def one-third (make-rat 1 3))

(defn mult-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))