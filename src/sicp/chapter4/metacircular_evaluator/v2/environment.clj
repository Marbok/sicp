(ns sicp.chapter4.metacircular-evaluator.v2.environment))

(defn enclosing-environment [env] (rest env))
(defn first-frame [env] (first env))
(def the-empty-environment '())

(defn list-of-atom [lst]
  (map atom lst))

(defn make-frame [variables values]
  (list (atom (list-of-atom variables)) (atom (list-of-atom values))))
(defn frame-variables [frame] @(first frame))
(defn frame-values [frame] @(second frame))
(defn add-binding-to-frame! [var val frame]
  (do
    (reset! (first frame) (cons (atom var) @(first frame)))
    (reset! (second frame) (cons (atom val) @(second frame)))))

(defn extend-environment [vars vals base-env]
  (if (= (count vars) (count vals))
    (cons (make-frame vars vals) base-env)
    (throw (IllegalArgumentException. (str "Different arguments supplied" vars vals)))))