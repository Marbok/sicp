(ns sicp.chapter2.solution-2-42)

(def empty-board nil)

(defn adjoin-position [new-row k rest-of-queens]
  (cons (list new-row k) rest-of-queens))

(defn safe? [position]
  (let [new (first position)
        safe-in-row? (fn [position]
                       (empty? (filter (fn [queen]
                                         (= (first new) (first queen)))
                                       position)))
        safe-in-diagonal? (fn [position]
                            (let [queen (first position)]
                              (cond
                                (empty? position) true
                                (= (Math/abs (- (first new) (first queen)))
                                   (Math/abs (- (last new) (last queen)))) false
                                :else (recur (rest position)))))]
    (and
      (safe-in-row? (rest position))
      (safe-in-diagonal? (rest position)))))

(defn queen-cols [k board-size]
  (if (= k 0)
    (list empty-board)
    (filter
      (fn [position] (safe? position))
      (mapcat
        (fn [rest-of-queens]
          (map (fn [new-row]
                 (adjoin-position new-row k rest-of-queens))
               (range 1 (inc board-size))))
        (queen-cols (- k 1) board-size)))))

(defn queens [board-size]
  (queen-cols board-size board-size))
