(ns mvalid.validators)

(defn not-nil?
  [is-nil?]
  (not (nil? is-nil?)))

(defn not-nil-or-empty?
  [is-nil-or-empty?]
  (and (not-nil? is-nil-or-empty?)
       (not (empty? is-nil-or-empty?))))
