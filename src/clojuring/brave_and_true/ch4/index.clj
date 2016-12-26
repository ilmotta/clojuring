(ns clojuring.brave-and-true.ch4.index)

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(def vamp-keys [:name :glitter-index])

(defn append
  [coll & args]
  (apply concat coll args))

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn glitter-filter
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records)))

(defn mapify
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn parse
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

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

(defn validate
  [validators record]
  (every? #((get validators %) (get record %))
          (keys validators)))
