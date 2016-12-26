(ns clojuring.mostly-adequate.ch5.composition)

; Exercise 1
; ==========
; Use comp to rewrite the function below.
(defn is-last-in-stock-original
  [cars]
  (let [last-car (last cars)]
    (get last-car :in-stock)))

(def is-last-in-stock
  (comp (partial :in-stock) last))

; Exercise 2
; ==========
; Use comp get and first to retrieve the name of the first car
(def name-of-first-car (comp (partial :name) first))

; Exercise 3
; ==========
; Use the helper function average to refactor average-dollar-value as
; a composition.
(defn- average
  [xs]
  (/ (reduce + 0 xs) (count xs)))

(defn average-dollar-value-original
  [cars]
  (let [dollar-values (map (fn [c] (:dollar-value c)) cars)]
    (average dollar-values)))

(def average-dollar-value
  (comp average (partial map :dollar-value)))

; Exercise 4
; ==========
; Write a function "sanitize-names" using comp that returns a list of
; lowercase and underscored car's names: e.g.:
; (sanitize-names [{:name "Ferrari FF"]) => ["ferrari-ff"]
(defn underscore
  [s]
  (clojure.string/replace s #"\W+" "_"))

(def sanitize-names
  (comp
    (partial map underscore)
    (partial map clojure.string/lower-case)
    (partial map :name)))

(def sanitize-names-2
  (partial map (comp underscore clojure.string/lower-case :name)))

(defn sanitize-names-with-thread-last-macro
  [cars]
  (->> cars
      (map :name)
      (map clojure.string/lower-case)
      (map underscore)))

; Bonus 1
; =======
; Refactor available-prices with comp
(def format-money #(str "money-" %))

(defn available-prices-original
  [cars]
  (let [available-cars (filter :in-stock cars)]
    (clojure.string/join
      ", "
      (map (fn [x] (format-money (:dollar-value x)))
         available-cars))))

(def available-prices
  (comp
    (partial clojure.string/join ", ")
    (partial map format-money)
    (partial map :dollar-value)
    (partial filter :in-stock)))

(defn available-prices-with-thread
  [cars]
  (->> cars
       (filter :in-stock)
       (map :dollar-value)
       (map format-money)
       ((partial clojure.string/join ", "))))
