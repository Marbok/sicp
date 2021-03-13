(ns sicp.chapter3.solution-3-21)

;; base level
(defn- front-pnt [queue] (first queue))
(defn- rear-pnt [queue] (last queue))
(defn- set-front-pnt! [queue item] (reset! (front-pnt queue) item))
(defn- set-rear-pnt! [queue item] (reset! (rear-pnt queue) item))

;; operation for work with queue
(defn make-queue []
  (list (atom '()) (atom '())))

(defn empty-queue? [queue]
  (empty? @(front-pnt queue)))

(defn front-queue [queue]
  (if (empty-queue? queue)
    (throw IllegalArgumentException)
    (first @(front-pnt queue))))

(defn insert-queue! [queue item]
  (let [new (list item (atom '()))]
    (if (empty-queue? queue)
      (do
        (set-front-pnt! queue new)
        (set-rear-pnt! queue new)
        queue)
      (do
        (reset! (last @(rear-pnt queue)) new)
        (set-rear-pnt! queue new)
        queue))))

(defn delete-queue! [queue]
  (if (empty-queue? queue)
    (throw IllegalArgumentException)
    (do
      (set-front-pnt! queue @(last @(front-pnt queue)))
      queue)))

(defn print-queue [queue]
  (let [queue->list (fn [queue]
                      (let [iter (fn [pnt acc]
                                   (if (empty? @pnt)
                                     acc
                                     (recur (last @pnt) (conj acc (first @pnt)))))]
                        (iter (front-pnt queue) '())))]
    (println (reverse (queue->list queue)))))


