(ns brave.collections.unordered-test
  (:use expectations)
  (:require [brave.collections.unordered :refer :all]))

(def players #{"Alice" "Bob"})

; conj adds to sets
(expect #{"Alice" "Bob" "Kelly"} (conj players "Kelly"))

; collections can be combined
(expect #{"Alice" "Bob" "Kelly"} (into players #{"Kelly"}))
(expect ["Alice" "Bob" "Kelly"] (sort (into [] (into players #{"Kelly"}))))

; disj ("disjoin") removes one or more elements from a set
(expect #{:a} (disj #{:a :b} :b))
(expect #{:a} (disj #{:a :b :c} :b :c))

; assoc is used to add elements to maps
(expect {:name "Noob" :type "Cat"} (assoc {:type "Cat"} :name "Noob"))

; assoc can add multiple kvs
(expect {:name "Noob" :type "Cat"} (assoc {} :name "Noob" :type "Cat"))

; assoc-in allows modifications on nested maps
(let [company {:name "WidgetCo"
               :address {:street "123 Main St"
                         :city "Springfield"
                         :state "IL"}}
      new-company (assoc-in company [:address :state] "RS")]
  (expect "IL" (get-in company [:address :state]))
  (expect "RS" (get-in new-company [:address :state])))

; Nested maps can be accessed using get-in
(expect "banana" (get-in {:monkey {:likes "banana"}} [:monkey :likes]))

; Maps can be accessed using get and get-in functions, but also with field
; accessors
(expect "Noob" (:name {:name "Noob"}))
(expect "cat-with-default-name" (:name {:age 15} "cat-with-default-name"))

(def game
  {:round 2
   :players #{{:name "Una" :ranking 43}
              {:name "Bob" :ranking 77}
              {:name "Cid" :ranking 33}}
   :scores {"Una" 1400
            "Bob" 1240
            "Cid" 1024}})

; Advance the game round
(expect 3 (get (next-round game) :round))

; Add score amount to the given player name
(expect 1524 (get-in (add-score game "Cid" 500) [:scores "Cid"]))

; Creates new player with given score if none is found
(expect 100 (get-in (add-score game "Moke" 100) [:scores "Moke"]))

; Find a player by name
(expect {:name "Una" :ranking 43}
        (find-player game "Una"))

; Returns nil if player cannot be found
(expect nil (find-player game "Moke"))
