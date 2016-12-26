(ns clojuring.mostly-adequate.ch4.partial-application-test
  (:use expectations clojuring.mostly-adequate.ch4.partial-application)
  (:require [clojure.string :as string]))

(expect ["a" "b"] (words-original "a b"))
(expect ["a" "b"] (words "a b"))
(expect [["a" "b"] ["c" "d"]] (sentences ["a b" "c d"]))
(expect ["quit"] (filterQs-original ["hello" "quit" "world"]))
(expect ["quit"] (filterQs ["hello" "quit" "world"]))

(expect 1 (keep-highest 0 1))
(expect 58 (find-max-original [-100 4 -1 9 58]))
(expect 58 (find-max [-100 4 -1 9 58]))

(let [coll [1 2 3 4 5]]
  (expect [3 4] (slice-original coll 2 4))
  (expect [3 4] (((slice coll) 2) 4))
  (expect [3 4] (slice-partial coll 2 4))
  (expect [3 4] ((slice-partial coll) 2 4))
  (expect [3 4] ((slice-partial coll 2) 4)))
