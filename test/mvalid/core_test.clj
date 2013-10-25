(ns mvalid.core-test
  (:require [clojure.test :refer :all]
            [mvalid.validators :refer :all]
            [mvalid.core :refer :all]))

(def all-valid {:first {:valid true :message "Ignore"}
                :second {:valid true :message "Second must be valid."}
                :third {:valid true}})

(def not-all-valid {:first {:valid true}
                    :second {:valid false :message "Second must be valid."}
                    :third {:valid true}})

(def none-valid {:first {:valid false :message "First must be valid."}
                 :second {:valid false :message "Second must be valid."}
                 :third {:valid false :message "Third must be valid."}})


(deftest test-is-valid?
  (testing "When all are valid, I should get true"
    (is (= true (is-valid? all-valid))))
  (testing "When some are not valid, I should get false"
    (is (= false (is-valid? not-all-valid))))
  (testing "When all are valid, I should get true"
    (is (= false (is-valid? none-valid)))))


(deftest test-error-string
  (testing "When all are valid, I should get an empty string"
    (is (= "" (error-string all-valid))))
  (testing "When one is not valid, I should get the message"
    (is (= "Second must be valid." (error-string not-all-valid))))
  (testing "When many are not valid, I should get concatenated strings, but order is not promised"
    (is (= true
         (and
           (.contains (error-string none-valid) "First must be valid." )
           (.contains (error-string none-valid) "Second must be valid." )
           (.contains (error-string none-valid) "Third must be valid." ))))))
;not-nil?
;(def test-dsl-1
;  {:field1 [not-nil-or-empty?]
;   :field2 [number?]
;   :field3 [#(= 2 %)]})

;(def test-data-1-valid
;  {:field1 "Name"
;   :field2 2
;   :field3 2})

;(def test-data-2-not-valid
;  {:field1 "Name"
;   :field2 2
;   :field3 3})

; no longer used
;(deftest simple-test-1
;  (testing "will apply merge function on match"
;    (is (= {:a 2}
;           (validate {:a [#(+ % 1)]} {:a 1})))))

;(deftest simple-test-2
;  (testing "will validate simple maps"
;    (is (= true
;         (is-valid? (validate test-dsl-1 test-data-1-valid))))
;    (is (= false
;         (is-valid? (validate test-dsl-1 test-data-2-not-valid))))))

(def test-dsl-2
  {:field1 {:funcs [not-nil-or-empty?] :message "The Field #1 is required!"}
   :field2 {:funcs [number? #(= 2 %)] :message "This field must be an int!"}
   :field3 {:funcs [#(= 2 %)] :message "You failed the robot check!"}})

(def test-data-2-valid
  {:field1 "Name"
   :field2 2
   :field3 2})

(def test-data-3-invalid
  {:field1 "Name"
   :field2 2
   :field3 3})

(deftest simple-test-2-valid-map
  (testing "will validate simple maps, all values are true"
    (is (= {:field1 {:valid true :message ""} :field2 {:valid true :message ""} :field3 {:valid true :message ""}}
         (validate test-dsl-2 test-data-2-valid)))))

(deftest simple-test-2-valid-bool
  (testing "will validate simple maps, all values are true"
    (is (= true
          (is-valid? (validate test-dsl-2 test-data-2-valid))))))

(deftest simple-test-3-invalid-map
  (testing "will validate simple maps, with some false"
    (is (= {:field1 {:valid true :message ""}
            :field2 {:valid true :message ""}
            :field3 {:valid false :message "You failed the robot check!"}}
         (validate test-dsl-2 test-data-3-invalid)))))

