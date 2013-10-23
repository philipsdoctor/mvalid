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
 (defn composite-function
  "A simple thrush"
  [func-seq args]
  ((apply comp (reverse func-seq)) args))

(defn apply-fa [f a]
  ;(f a)
  (composite-function f a)
  )

(defn validate
  [spec form]
  (merge-with apply-fa spec form))

(defn get-messages
  [reply]
  (if (not (:valid reply)) (:message reply) ""))

(defn error-string
  [response]
  (clojure.string/join " " (filter not-empty (map get-messages (vals response)))))

(defn is-valid?
  [response]
  (every? #(= true (:valid %)) (vals response)))
