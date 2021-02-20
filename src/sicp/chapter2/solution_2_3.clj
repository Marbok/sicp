(ns sicp.chapter2.solution-2-3
  (:require [sicp.chapter2.solution-2-2 :refer :all]))

(defprotocol IRectangle
  (width [this])
  (height [this]))

(defn make-rect-point [p1 p2]
  (let [sqr (fn [x] (* x x))
        len (fn [x1 y1 x2 y2]
              (Math/sqrt (+ (sqr (- x1 x2))
                            (sqr (- y1 y2)))))]
    (reify IRectangle
      (width [_] (len (.x-point p1) 1 (.x-point p2) 1))
      (height [_] (len 1 (.y-point p1) 1 (.y-point p2))))))

(defn perimeter [rect]
  (* (+ (width rect) (height rect)) 2))

(defn area [rect]
  (* (width rect) (height rect)))