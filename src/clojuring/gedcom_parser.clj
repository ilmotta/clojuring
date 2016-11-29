(ns clojuring.gedcom-parser
  (:require [clojure.string :as string]))

(defn- surround-start
  [{:keys [type id]}]
   (if (nil? id)
     (str "<" type ">")
     (str "<" type " id=\"" id "\"" ">")))

(defn- surround-end
  [type]
  (str "</" type ">"))

(defn tag
  ([props] (tag props ""))
  ([props content]
   (str (surround-start props)
        content
        (surround-end (:type props)))))

(defn line-with-id?
  [input]
  (boolean (re-find #"\s@.+@\s" input)))

(defn parse-line
  [line]
  (if (line-with-id? line)
    (let [[level id type] (string/split line #" ")]
      (list 'clojuring.gedcom-parser/tag {:type (string/lower-case type) :id id}))
    line))
