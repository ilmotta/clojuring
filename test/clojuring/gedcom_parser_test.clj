(ns clojuring.gedcom-parser-test
  (:use expectations)
  (:require [clojuring.gedcom-parser :refer :all]))

(expect (tag {:type "indi"})
        "<indi></indi>")

(expect (tag {:type "indi"} "content")
        "<indi>content</indi>")

(expect (tag {:type "indi"}
             (tag {:type "name"} "Jamis"))
        "<indi><name>Jamis</name></indi>")

(expect (tag {:type "indi" :id "@ID@"})
        "<indi id=\"@ID@\"></indi>")
