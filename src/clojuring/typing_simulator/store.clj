(ns clojuring.typing-simulator.store)

(def data
  (atom {:keyboard []}))

(defn add-key [k]
  (swap! data update :keyboard conj k))

(defn fetch-keyboard []
  (:keyboard @data))

(defn update-key [new-key]
  (let [without-key (remove #(= (:value new-key) (:value %)) (fetch-keyboard))]
    (swap! data assoc :keyboard (conj without-key new-key))))

(defn press-key [k]
  (update-key (assoc k :status :key/pressed)))

(defn release-key [k]
  (update-key (assoc k :status :key/released)))
