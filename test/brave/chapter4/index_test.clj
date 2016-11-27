(ns brave.chapter4.index-test
  (:require [clojure.test :refer :all]
            [brave.chapter4.index :refer :all]))

(deftest fn-validate
  (testing "validates any given suspect with validators map"
    (let [validators {:name (complement clojure.string/blank?)
                      :glitter-index #(>= % 0)}]
      (are [name index]
           (= false (validate validators {:name name :glitter-index index}))
           "Trix" -1
           "" 0
           "   " 10)
      (are [name index]
           (= true (validate validators {:name name :glitter-index index}))
           "Icaro" 0))))

(deftest fn-to-csv
  (testing "converts suspects seq of maps back to CSV"
    (let [filename "test/brave/fixtures/suspects.csv"
          suspects (mapify (parse (slurp filename)))]
      (is (= "Edward Cullen,10\nBella Swan,0" (to-csv (take 2 suspects)))))))

(deftest fn-convert
  (testing ":name conversion returns itself"
    (is (= (convert :name "Julie") "Julie")))
  (testing ":glitter-index converts to int"
    (is (= (convert :glitter-index "-1") -1))
    (is (= (convert :glitter-index "2") 2))
    (is (= (convert :glitter-index "0") 0))))

(deftest fn-parse
  (testing "converts CSV into rows of columns"
    (is (= (parse "col00,col01\ncol10,col11")
           '(["col00" "col01"] ["col10" "col11"])))))

(deftest fn-mapify
  (testing "returns a seq of maps"
    (is (= (mapify [["Edward Cullen" "10"] ["Bella Swan" "0"]])
           [{:name "Edward Cullen" :glitter-index 10}
            {:name "Bella Swan" :glitter-index 0}]))))

(deftest fn-append
  (testing "throws if new element is not a collection"
    (is (thrown? IllegalArgumentException (doall (append [] 0)))))
  (testing "adds elements to the end of collection"
    (is (= (append [0 1] [2]) [0 1 2]))))
