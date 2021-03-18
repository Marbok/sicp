(ns sicp.chapter3.solution-3-23)

;; Node part
(defrecord Node [value last next])
(defn make-node [value] (Node. (atom value) (atom nil) (atom nil)))
(defn value-node [node] @(:value node))
(defn last-node [node] @(:last node))
(defn next-node [node] @(:next node))
(defn set-last-node! [node value] (reset! (:last node) value))
(defn set-next-node! [node value] (reset! (:next node) value))

;; Deque
(defrecord Deque [front rear])
(defn- front-pnt [deque] @(:front deque))
(defn- rear-pnt [deque] @(:rear deque))
(defn- set-front-pnt! [deque item] (reset! (:front deque) item))
(defn- set-rear-pnt! [deque item] (reset! (:rear deque) item))

;; interface
(defn make-deque []
  (Deque. (atom nil) (atom nil)))

(defn empty-deque? [deque]
  (nil? (front-pnt deque)))

(defn front-deque [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (value-node (front-pnt deque))))

(defn rear-deque [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (value-node (rear-pnt deque))))

(defn- insert-in-empty-deque! [deque new]
  (do
    (set-front-pnt! deque new)
    (set-rear-pnt! deque new)
    deque))

(defn front-insert-deque! [deque item]
  (let [new (make-node item)]
    (if (empty-deque? deque)
      (insert-in-empty-deque! deque new)
      (do
        (set-last-node! (front-pnt deque) new)
        (set-next-node! new (front-pnt deque))
        (set-front-pnt! deque new)
        deque))))

(defn rear-insert-deque! [deque item]
  (let [new (make-node item)]
    (if (empty-deque? deque)
      (insert-in-empty-deque! deque new)
      (do
        (set-next-node! (rear-pnt deque) new)
        (set-last-node! new (rear-pnt deque))
        (set-rear-pnt! deque new)
        deque))))

(defn front-delete-deque! [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (do
      (let [next-node (next-node (front-pnt deque))]
        (if (nil? next-node)
          (do
            (set-front-pnt! deque nil)
            (set-rear-pnt! deque nil)
            deque)
          (do
            (set-last-node! next-node nil)
            (set-front-pnt! deque next-node)
            deque))))))


(defn rear-delete-deque! [deque]
  (if (empty-deque? deque)
    (throw IllegalArgumentException)
    (do
      (let [last-node (last-node (rear-pnt deque))]
        (if (nil? last-node)
          (do
            (set-front-pnt! deque nil)
            (set-rear-pnt! deque nil)
            deque)
          (do
            (set-next-node! last-node nil)
            (set-rear-pnt! deque last-node)
            deque))))))

(defn print-deque [deque]
  (let [deque->list (fn [deque]
                      (let [iter (fn [node acc]
                                   (if (nil? node)
                                     acc
                                     (recur (next-node node) (conj acc (value-node node)))))]
                        (iter (front-pnt deque) '())))]
    (println (reverse (deque->list deque)))))