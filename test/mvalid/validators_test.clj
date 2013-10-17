(ns mvalid.core-test
  (:require [clojure.test :refer :all]
            [mvalid.validators :refer :all]))

(deftest test-is-an-int?
  (testing "It is an int should be true"
    (is (= true (is-an-int? 2))))
  (testing "When not an int, I should get false"
    (is (= false (is-an-int? 2.2)))))
