(ns sicp.chapter4.metacircular-evaluator.v2.variable
  (:require [sicp.chapter4.metacircular-evaluator.v2.environment :refer [enclosing-environment the-empty-environment
                                                                         first-frame frame-variables frame-values
                                                                         add-binding-to-frame!]]))

(defn lookup-variable-value [var env]
  (letfn [(env-loop [env]
            (letfn [(scan [vars vals]
                      (cond
                        (empty? vars) (env-loop (enclosing-environment env))
                        (= var @(first vars)) @(first vals)
                        :else (scan (rest vars) (rest vals))))]
              (if (= env the-empty-environment)
                (throw (IllegalStateException. (str "Unbound variable" var)))
                (let [frame (first-frame env)]
                  (scan (frame-variables frame)
                        (frame-values frame))))))]
    (env-loop env)))

(defn set-variable-value! [var val env]
  (letfn [(env-loop [env]
            (letfn [(scan [vars vals]
                      (cond
                        (empty? vars) (env-loop (enclosing-environment env))
                        (= var @(first vars)) (reset! (first vals) val)
                        :else (scan (rest vars) (rest vals))))]
              (if (= env the-empty-environment)
                (throw (IllegalStateException. (str "Unbound variable: SET!" var)))
                (let [frame (first-frame env)]
                  (scan (frame-variables frame)
                        (frame-values frame))))))]
    (env-loop env)))

(defn define-variable! [var val env]
  (let [frame (first-frame env)]
    (letfn [(scan [vars vals]
              (cond
                (empty? vars) (add-binding-to-frame! var val frame)
                (= var @(first vars)) (reset! (first vals) val)
                :else (scan (rest vars) (rest vals))))]
      (scan (frame-variables frame) (frame-values frame)))))

(defn variable? [exp]
  (symbol? exp))