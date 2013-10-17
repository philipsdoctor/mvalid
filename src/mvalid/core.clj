(ns mvalid.core)

"
{:first \"first\"
 :second 2
 :thrid nil
 :fourth \"\"
 :fifth {:a :b}}

{:first  not-empty
 :second [{:gt 4} \"Second must be greater than 4\"]
 :thrid  [(fn [x] true) \"Third failed\"]}

{:first {:valid true :message \"\"}
 :second {:valid flase :message \"second must be greater than 4\"}}

 "
(defn get-messages
  [reply]
  (if (not (:valid reply)) (:message reply) ""))

(defn error-string
  [response]
  (clojure.string/join " " (filter not-empty (map get-messages (vals response)))))

(defn is-valid?
  [response]
  (every? #(= true (:valid %)) (vals response)))
