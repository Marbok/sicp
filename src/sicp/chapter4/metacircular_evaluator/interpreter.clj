(ns sicp.chapter4.metacircular-evaluator.interpreter
  (:import (clojure.lang Cons)))

(declare inter-eval)
(declare tagged-list?)

(defn true? [x] (not (= x false)))
(defn false? [x] (= x false))

(defn cons? [exp]
  (= Cons (type exp)))

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

(def primitive-procedure
  (list
    (list 'car first)
    (list 'cdr rest)
    (list 'cons cons)
    (list 'null? nil?)
    (list 'empty? empty?)))
(defn primitive-procedure-names []
  (map first primitive-procedure))
(defn primitive-procedure-objects []
  (map #(list 'primitive (second %)) primitive-procedure))

(defn setup-environment []
  (let [initial-env (extend-environment (primitive-procedure-names)
                                        (primitive-procedure-objects)
                                        the-empty-environment)]
    (do
      (define-variable! 'true true initial-env)
      (define-variable! 'false false initial-env)
      initial-env)))
(def the-global-environment (setup-environment))

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

(defn begin? [exp] (tagged-list? exp 'begin))
(defn begin-actions [exp] (rest exp))
(defn last-exp? [seq] (empty? (rest seq)))
(defn first-exp [seq] (first seq))
(defn rest-exps [seq] (rest seq))
(defn make-begin [seq] (cons 'begin seq))
(defn sequence->exp [seq]
  (cond
    (empty? seq) seq
    (last-exp? seq) (first-exp seq)
    :else (make-begin seq)))

(defn if? [exp] (tagged-list? exp 'if))
(defn if-predicate [exp] (second exp))
(defn if-consequent [exp] (first (rest (rest exp))))
(defn if-alternative [exp]
  (if (not (nil? (rest (rest (rest exp)))))
    (first (rest (rest (rest exp))))
    'false))
(defn make-if [predicate consequent alternative]
  (list 'if predicate consequent alternative))

(defn cond? [exp] (tagged-list? exp 'cond))
(defn cond-clauses [exp] (rest exp))
(defn cond-predicate [clause] (first clause))
(defn cond-else-clause? [clause]
  (= (cond-predicate clause) 'else))
(defn cond-action [clause] (rest clause))
(defn expand-clauses [clauses]
  (if (empty? clauses)
    'false
    (let [first (first clauses)
          rest (rest clauses)]
      (if (cond-else-clause? first)
        (if (empty? rest)
          (sequence->exp (cond-action first))
          (throw (IllegalStateException. (str "ELSE clause isn't last: COND->IF" clauses))))
        (make-if (cond-predicate first)
                 (sequence->exp (cond-action first))
                 (expand-clauses rest))))))
(defn cond->if [exp] (expand-clauses (cond-clauses exp)))

(defn application? [exp] (or (cons? exp) (list? exp)))
(defn operator [exp] (first exp))
(defn operands [exp] (rest exp))
(defn no-operands? [ops] (empty? ops))
(defn first-operand [ops] (first ops))
(defn rest-operands [ops] (rest ops))

(defn lambda? [exp] (tagged-list? exp 'lambda))
(defn lambda-parameters [exp] (second exp))
(defn lambda-body [exp] (rest (rest exp)))
(defn make-lambda [parameters body]
  (cons 'lambda (cons parameters body)))


(defn definition? [exp] (tagged-list? exp 'define))
(defn definition-variable [exp]
  (if (symbol? (second exp))
    (second exp)
    (first (first (rest exp)))))
(defn definition-value [exp]
  (if (symbol? (second exp))
    (first (rest (rest exp)))
    (make-lambda (rest (first (rest exp)))
                 (rest (rest exp)))))

(defn assignment? [exp] (tagged-list? exp 'set!))
(defn assignment-variable [exp] (second exp))
(defn assignment-value [exp] (last exp))

(defn tagged-list? [exp tag]
  (if (or (cons? exp) (list? exp))
    (= (first exp) tag)
    false))

(defn quoted? [exp]
  (tagged-list? exp 'quote))

(defn text-of-quotation [exp]
  (second exp))

(defn variable? [exp]
  (symbol? exp))

(defn self-evaluating? [exp]
  (cond
    (number? exp) true
    (string? exp) true
    :else false))

(defn eval-assignment [exp env]
  (set-variable-value! (assignment-variable exp)
                       (inter-eval (assignment-value exp) env)
                       env)
  'ok)

(defn eval-definition [exp env]
  (define-variable! (definition-variable exp)
                    (inter-eval (definition-value exp) env)
                    env)
  'ok)

(defn eval-sequence [exps env]
  (cond
    (last-exp? exps) (inter-eval (first-exp exps) env)
    :else (do
            (inter-eval (first-exp exps) env)
            (eval-sequence (rest-exps exps) env))))

(defn eval-if [exp env]
  (if (true? (inter-eval (if-predicate exp) env))
    (inter-eval (if-consequent exp) env)
    (inter-eval (if-alternative exp) env)))

(defn list-of-values [exps env]
  (if (no-operands? exps)
    '()
    (cons (inter-eval (first-operand exps) env)
          (list-of-values (rest-operands exps) env))))

(defn apply-primitive-procedure [proc args]
  (apply (primitive-implementation proc) args))

(defn inter-apply [procedure arguments]
  (cond
    (primitive-procedure? procedure) (apply-primitive-procedure procedure arguments)
    (compound-procedure? procedure) (eval-sequence (procedure-body procedure)
                                                   (extend-environment (procedure-parameters procedure)
                                                                       arguments
                                                                       (procedure-environment procedure)))
    :else (throw (IllegalArgumentException. (str "Unknown procedure type: APPLY" procedure)))))

(defn inter-eval [exp env]
  (cond
    (self-evaluating? exp) exp
    (variable? exp) (lookup-variable-value exp env)
    (quoted? exp) (text-of-quotation exp)
    (assignment? exp) (eval-assignment exp env)
    (definition? exp) (eval-definition exp env)
    (if? exp) (eval-if exp env)
    (lambda? exp) (make-procedure (lambda-parameters exp)
                                  (lambda-body exp)
                                  env)
    (begin? exp) (eval-sequence (begin-actions exp) env)
    (cond? exp) (inter-eval (cond->if exp) env)
    (application? exp) (inter-apply (inter-eval (operator exp) env)
                                    (list-of-values (operands exp) env))
    :else (throw (IllegalArgumentException. (str "Unknown expression type: EVAL" exp)))))


