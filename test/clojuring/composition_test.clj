(ns clojuring.composition-test
  (:use expectations clojuring.composition))

; Example data
(def cars
  [{:name "Ferrari FF"
    :horsepower 660
    :dollar-value 700000
    :in-stock true}
   {:name "Spyker C12 Zagato"
    :horsepower 650
    :dollar-value 648000
    :in-stock false}
   {:name "Jaguar XKR-S"
    :horsepower 550
    :dollar-value 132000
    :in-stock false}
   {:name "Audi R8"
    :horsepower 525
    :dollar-value 114200
    :in-stock false}
   {:name "Aston Martin One-77"
    :horsepower 750
    :dollar-value 1850000
    :in-stock true}
   {:name "Pagani Huayra"
    :horsepower 700
    :dollar-value 1300000
    :in-stock false}])

(expect false (is-last-in-stock-original cars))
(expect false (is-last-in-stock cars))

(expect "Ferrari FF" (name-of-first-car cars))

(expect 790700 (average-dollar-value-original cars))
(expect 790700 (average-dollar-value cars))

(expect ["ferrari_ff"] (sanitize-names (take 1 cars)))
(expect ["ferrari_ff"] (sanitize-names-2 (take 1 cars)))
(expect ["ferrari_ff"] (sanitize-names-with-thread-last-macro (take 1 cars)))

(expect "money-700000, money-1850000" (available-prices-original cars))
(expect "money-700000, money-1850000" (available-prices cars))
(expect "money-700000, money-1850000" (available-prices-with-thread cars))
