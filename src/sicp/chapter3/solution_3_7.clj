(ns sicp.chapter3.solution-3-7
  (:require [sicp.chapter3.solution-3-3-4 :refer [make-account make-security]]))

(defn make-join [account acc-pass new-pass]
  (let [dispatch (fn [op]
                   (account op acc-pass))]
    (if (= ((account 'check-pass acc-pass) 'stub) "Correct pass")
      (make-security new-pass dispatch)
      "Not correct pass")))
