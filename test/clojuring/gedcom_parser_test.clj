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

(expect true (line-with-id? "0 @ID@ type"))
(expect true (line-with-id? "0   @ID@    type"))
(expect false (line-with-id? "0 ID@ type"))
(expect false (line-with-id? "0 ID type"))
(expect false (line-with-id? "0 @ID@type"))

; Parse line with ID
(expect "<indi id=\"@I1@\"></indi>"
        (eval (parse-line "0 @I1@ INDI")))
