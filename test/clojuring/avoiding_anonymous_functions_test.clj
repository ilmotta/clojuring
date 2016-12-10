; Examples based on Jay Fields' Thoughts blog post:
; http://blog.jayfields.com/2012/10/clojure-avoiding-anonymous-functions.html

(ns clojuring.avoiding-anonymous-functions-test
  (:use expectations clojuring.avoiding-anonymous-functions))

(def cats
  [{:name "coca", :current-city "porto alegre", :employer "pulga"}
   {:name "noob", :current-city "porto alegre", :employer "pulga"}
   {:name "moke", :current-city "cat heaven", :employer "pulga"}
   {:name "biju", :current-city "porto alegre", :employer "icaro"}])

(expect (sort '("coca" "noob" "moke"))
        (sort (get (process-with-reduce cats) "pulga")))

(expect (sort '("coca" "noob" "moke"))
        (sort (process-uniformely-with-thread-macros cats)))

(expect '("biju")
        (get (process-with-core-functions cats) "icaro"))

(expect '({:name "moke" :current-city "cat heaven" :employer "pulga"})
        (filter-using-set cats))
