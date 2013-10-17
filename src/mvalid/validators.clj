(ns mvalid.validators)

(defn is-a-number?
  [numb?]
  number? numb?)

(defn is-an-int?
  [int?]
   (= (type int?) java.lang.Integer)) ;todo work for cljs

