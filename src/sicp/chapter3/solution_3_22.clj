(ns sicp.chapter3.solution-3-22)

(defn make-queue []
  (let [front-pnt (atom '())
        rear-pnt (atom '())
        empty? (fn []
                 (empty? @front-pnt))
        front (fn []
                (if (empty?)
                  (throw IllegalArgumentException)
                  (first @front-pnt)))
        insert! (fn [item]
                  (let [new (list item (atom '()))]
                    (if (empty?)
                      (do
                        (reset! front-pnt new)
                        (reset! rear-pnt new))
                      (do
                        (reset! (last @rear-pnt) new)
                        (reset! rear-pnt new)))))
        delete! (fn []
                  (if (empty?)
                    (throw IllegalArgumentException)
                    (reset! front-pnt @(last @front-pnt))))
        dispatch (fn [op]
                   (cond
                     (= op 'empty?) empty?
                     (= op 'front) front
                     (= op 'insert!) insert!
                     (= op 'delete!) delete!))]
    dispatch))