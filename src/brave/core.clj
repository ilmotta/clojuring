(ns brave.core
  (:require [brave.visualization.svg :as svg])
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

(defn to-col
  [coll]
  (clojure.string/join "," coll))

(defn to-rows
  [coll]
  (clojure.string/join "\n" coll))

(defn to-csv
  [coll]
  (to-rows (reduce #(conj %1 (to-col %2))
                   []
                   (map vals coll))))

(def suspects (mapify (parse (slurp filename))))

(assert (= "Edward Cullen,10\nBella Swan,0" (to-csv (take 2 suspects))))

(def heists [{:location "Cologne, Germany"
              :cheese-name "Archbishop Hildebold's Cheese Pretzel"
              :lat 50.95
              :lng 6.97}
             {:location "Zurich, Switzerland"
              :cheese-name "The Standard Emmental"
              :lat 47.37
              :lng 8.55}
             {:location "Marseille, France"
              :cheese-name "Le Fromage de Cosquer"
              :lat 43.30
              :lng 5.37}
             {:location "Zurich, Switzerland"
              :cheese-name "The Lesser Emmental"
              :lat 47.37
              :lng 8.55}
             {:location "Vatican City"
              :cheese-name "The Cheese of Turin"
              :lat 41.90
              :lng 12.45}])

(defn -main
  [& args]
  (println (svg/points heists)))
