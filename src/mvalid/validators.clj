(ns mvalid.validators)

(defn is-a-number?
  [numb?]
  number? numb?)

(defn is-an-int?
  [int?]
   (= (type int?) java.lang.Integer)) ;todo work for cljs

(defn not-nil?
  [is-nil?]
  (not (nil? is-nil?)))

(defn not-nil-or-empty?
  [is-nil-or-empty?]
  (and (not (nil? is-nil-or-empty?))
       (not (empty? is-nil-or-empty?))))
