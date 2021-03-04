(ns sicp.chapter2.solution-2-31)

(defn tree-map [f tree]
  (cond
    (number? tree) (f tree)
    :else (list
            (tree-map f (first tree))
            (tree-map f (second tree)))))