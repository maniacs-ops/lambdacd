(ns lambdacd.util-test
  (:use [lambdacd.util])
  (:require [clojure.test :refer :all]
            [clojure.core.async :as async]
            [clojure.java.io :as io]))

(deftest range-test
  (testing "that range produces a range from a value+1 with a defined length"
    ; TODO: the plus-one is like that because the user wants it, probably shouldn't be like this..
    (is (= '(6 7 8) (range-from 5 3)))))

(defn some-function [] {})


(deftest map-if-test
  (testing "that is applies a function to all elements that match a predicate"
    (is (= [] (map-if (identity true) inc [])))
    (is (= [4 3 5] (map-if #(< % 5) inc [3 2 4])))
    (is (= [3 2 5] (map-if #(= 4 %) inc [3 2 4])))))

(deftest create-temp-dir-test
  (testing "creating in default tmp folder"
    (testing "that we can create a temp-directory"
      (is (.exists (io/file (create-temp-dir)))))
    (testing "that it is writable"
      (is (.mkdir (io/file (create-temp-dir) "hello")))))
  (testing "creating in a defined parent directory"
    (testing "that it is a child of the parent directory"
      (let [parent (create-temp-dir)]
        (is (= parent (.getParent (io/file (create-temp-dir parent)))))))))