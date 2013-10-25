(ns mvalid.core-test
  (:require [clojure.test :refer :all]
            [mvalid.validators :refer :all]))

(deftest test-not-nil?
  (testing "It should be false for nil"
    (is (= false (not-nil? nil)))
  (testing "It should be true for not nil"
    (is (= true (not-nil? "hello")))
    (is (= true (not-nil? ""))))))

(deftest test-not-nil-or-empty?
  (testing "It should be false for nil"
    (is (= false (not-nil-or-empty? nil)))
  (testing "It should be true for not nil"
    (is (= true (not-nil-or-empty? "hello"))))
  (testing "It should be false for empty"
    (is (= false (not-nil-or-empty? ""))))))
