(ns sicp.chapter3.constraint-system.connector)

(defn has-value? [connector]
  (connector 'has-value?))

(defn get-value [connector]
  (connector 'value))

(defn set-value! [connector value informant]
  ((connector 'set-value!) value informant))

(defn forget-value! [connector retractor]
  ((connector 'forget) retractor))

(defn connect [connector constraint]
  ((connector 'connect) constraint))

(defn inform-about-value [constraint]
  (constraint 'I-have-a-value))

(defn inform-about-no-value [constraint]
  (constraint 'I-lost-my-value))

(defn for-each-except [exception procedure lst]
  (letfn [(iter [items]
            (cond
              (empty? items) 'done
              (= (first items) exception) (iter (rest items))
              :else (do
                      (procedure (first items))
                      (iter (rest items)))))]
    (iter lst)))

(defn in? [coll item]
  (some #(= % item) coll))

(defn make-connector []
  (let [value (atom false)
        informant (atom false)
        constraints (atom (list))]
    (letfn [(set-my-value [new-value setter]
              (cond
                (not (has-value? me))
                (do
                  (reset! value new-value)
                  (reset! informant setter)
                  (for-each-except setter inform-about-value @constraints))
                (not (= @value new-value)) (println "Contradiction (" new-value ", " @value ")")
                :else 'ignored))
            (forget-my-value [retractor]
              (if (= retractor @informant)
                (do
                  (reset! informant false)
                  (for-each-except retractor inform-about-no-value @constraints))
                'ignored))
            (connect [new-constraint]
              (do
                (if (not (in? @constraints new-constraint))
                  (reset! constraints (cons new-constraint @constraints)))
                (if (has-value? me)
                  (inform-about-value new-constraint))
                'done))
            (me [request]
              (cond
                (= request 'value) @value
                (= request 'has-value?) (if @informant true false)
                (= request 'set-value!) set-my-value
                (= request 'forget) forget-my-value
                (= request 'connect) connect
                :else (throw (IllegalArgumentException. "Unknown operation: CONNECTOR"))))]
      me)))