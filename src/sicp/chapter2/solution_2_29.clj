(ns sicp.chapter2.solution-2-29)

(defn make-mobile [left right]
  (list left right))

(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (last mobile))

(defn make-branch [length structure]
  (list length structure))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (last branch))

(defn total-weight [mobile]
  (cond
    (nil? mobile) 0
    (number? mobile) mobile
    :else (+ (total-weight (branch-structure (left-branch mobile)))
             (total-weight (branch-structure (right-branch mobile))))))

(defn balance? [mobile]
  (let [moment (fn [branch]
                 (* (branch-length branch)
                    (total-weight (branch-structure branch))))]
    (cond
      (nil? mobile) true
      (number? mobile) true
      :else (and (= (moment (left-branch mobile))
                    (moment (right-branch mobile)))
                 (balance? (branch-structure (left-branch mobile)))
                 (balance? (branch-structure (right-branch mobile)))))))