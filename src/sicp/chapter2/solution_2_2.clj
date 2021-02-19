(ns sicp.chapter2.solution-2-2)


(defprotocol IPoint
  (x-point [this])
  (y-point [this]))

(defn make-point [x y]
  (reify IPoint
    (x-point [_] x)
    (y-point [_] y)))

(defprotocol ISegment
  (start-segment [this])
  (end-segment [this]))

(defn make-segment [start end]
  (reify ISegment
    (start-segment [_] start)
    (end-segment [_] end)))

(defn midpoint-segment [segment]
  (let [start (start-segment segment)
        end (end-segment segment)
        avg (fn [x y]
              (/ (+ x y) 2))]
    (make-point (avg (x-point start) (x-point end))
                (avg (y-point start) (y-point end)))))