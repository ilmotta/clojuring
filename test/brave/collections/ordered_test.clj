(ns brave.collections.ordered-test
  (:use expectations)
  (:require [brave.collections.ordered :refer :all]))

; Vectors can be accessed by index
(expect 99 (get ["abc" false 99] 2))

; Calling get with an invalid syntax returns nil
(expect nil (get [0 1 2] 3))

; Collections can be counted
(expect 2 (count ["a" "b"]))
(expect 2 (count {:name "Noob" :type "Cat"}))
(expect 2 (count #{"a" "b"}))

; vector function creates a collection
(expect [:a :b] (vector :a :b))
(expect [] (vector))
(expect [nil] (vector nil))

; conj adds to the end of vectors
(expect [:a :b] (conj [:a] :b))

; conj adds at the front of lists
(expect '(:a :b) (conj '(:b) :a))

; Lists must be quoted to prevent evaluation
(expect 10 (first '(10 :ace :jack 9)))
