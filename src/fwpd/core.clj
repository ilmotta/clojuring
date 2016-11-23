(ns fwpd.core
  (:gen-class))

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(def validators {:name (complement clojure.string/blank?)
                 :glitter-index #(>= % 0)})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records)))

(defn append
  [coll & args]
  (apply concat coll args))

(defn validate
  [validators record]
  (every? #((get validators %) (get record %))
          (keys validators)))

(assert (= false (validate validators {:name "Icaro" :glitter-index -1})))
(assert (= false (validate validators {:name "" :glitter-index 0})))
(assert (= true (validate validators {:name "Icaro" :glitter-index 0})))

(defn -main
  [& args])
