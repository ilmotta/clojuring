(ns clojuring.partial-application
  (:require [clojure.string :as string]))

; Exercise 1
; ==========
; Refactor to remove all arguments by partially applying the function.
(defn words-original
  [str]
  (string/split str #" "))

(def words
  #(string/split % #" "))

; Exercise 1a
; ===========
; Use map to make a new sentences fn that works on an array of strings
(def sentences
  (partial map words))

; Exercise 2
; ==========
; Refactor to remove all arguments by partially applying the functions.
(defn filterQs-original
  [xs]
  (filter
    (fn [x] (re-find #"q" x))
    xs))

(def filterQs
  (partial filter (partial re-find #"q")))

; Exercise 3
; ==========
; Use the helper function keep-highest to refactor max to not reference any
; arguments.

; Leave be:
(defn keep-highest
  [x y]
  (if (>= x y) x y))

; Refactor this one
(defn find-max-original
  [xs]
  (reduce #(keep-highest %1 %2)
          -10000
          xs))

(def find-max
  (partial reduce keep-highest -10000))

; Bonus 1
; =======
; Wrap array's slice to be functional and curried
(def slice-original
  (partial subvec))

(defn slice
  [coll]
  (fn [start]
    (fn [end]
      (subvec coll start end))))

(defn slice-partial
  ([] (partial subvec))
  ([coll] (partial subvec coll))
  ([coll start] (partial subvec coll start))
  ([coll start end] (subvec coll start end)))
