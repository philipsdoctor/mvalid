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
                 :third {:valid false :message "Third must be valid"}})


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
  (testing "When many are not valid, I should get concatenated strings"
    (is (= "Second must be valid. Third must be valid." (error-string none-valid)))))


(def test-dsl-1
  {:field1 [not-empty]
   :field2 [not-empty is-an-int?]
   :field3 [#(= 2 %)]})

(def test-data-1-valid
  {:field1 "Name"
   :field2 2
   :field3 2})
