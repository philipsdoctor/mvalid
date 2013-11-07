(ns mvalid.core)

"Desired use case, specify a map specification and then
 validate map data against that spec, allow for simple
 cases of 'are all of them valid?' as well as returning error
 message maps or simple concatenated strings.

 Sample spec

 {:first [[not-empty] \"The First must not be empty!\"]
 :second [[#(> % 4)]] \"Second must be greater than 4.\"]
 :thrid  [[number? #(< % 10)] \"Third must be a number and less than 10.\"]}

 Sample data
 {:first \"first\"
 :second 2
 :thrid 7}

 Sample Output

{:first {:valid true :message \"\"}
 :second {:valid flase :message \"second must be greater than 4\"}
 :third {:valid true :message \"\"}}
 "

(defn composite-function
  "A simple thrush"
  [func-seq args]
  ((apply comp (reverse func-seq)) args))

;todo short-circuit evaluation, would allow for constructs like [int? #(< 2 %)] without throwing exceptions
(defn composite-and
  "Applies every function, to args, ensures they are all true"
  [func-seq args]
  (every? true? (map #(% args) func-seq)))

(defn format-reply
  "Applies the return format to the truth-func"
  [truth-func spec form]
  (let [valid (truth-func (:funcs spec) form)]
    (if valid
      {:valid true :message ""}
      {:valid false :message (:message spec)})))

(defn validate
  "Validates that the map abides by the rules in the spec."
  [spec form]
  (merge-with (partial format-reply composite-and) spec form))

(defn get-messages
  "Returns the error message if not valid."
  [reply]
  (if (:valid reply) "" (:message reply)))

(defn error-string
  "Returns a single string of all error messages joined."
  [response]
  (clojure.string/join " " (filter not-empty (map get-messages (vals response)))))

(defn is-valid?
  "True/False if all members of the spec are true or false"
  [response]
  (every? #(= true (:valid %)) (vals response)))
