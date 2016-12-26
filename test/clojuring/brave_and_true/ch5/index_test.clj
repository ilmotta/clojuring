(ns clojuring.brave-and-true.ch5.index-test
  (:require [clojure.test :refer :all]
            [clojuring.brave-and-true.ch5.index :refer :all]))

(deftest fn-pipe3
  (testing "pipes three function calls"
    (is (= 9 ((pipe3 clojure.string/split first read-string) "9,..." #",")))))

(deftest fn-pipen
  (testing "pipes n-function calls"
    (is (= 3 ((pipen #(+ 5 %) inc #(/ % 2) dec dec) 4)))))

(deftest fn-compn
  (testing "composes n-function calls"
    (is (= 3 ((compn dec dec #(/ % 2) inc #(+ 5 %)) 4)))))

(deftest fn-mapset
  (testing "works just like map, but returns a set"
    (is (= '#{2 3 4} (mapset inc [1 1 2 3 3])))))

(deftest fn-attr
  (testing "returns a characters attribute"
    (is (= 10 ((attr :intelligence) {:attributes {:intelligence 10 :strength 5}})))))

(deftest fn-not-empty
  (testing "returns false if collection is empty"
    (is (= false (not-empty? []))))
  (testing "returns true if collection is not empty"
    (is (= true (not-empty? [1])))))
