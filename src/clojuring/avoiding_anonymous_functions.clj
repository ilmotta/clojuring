(ns clojuring.avoiding-anonymous-functions)

(defn process-with-reduce
  [input]
  (reduce (fn [result {:keys [name employer]}]
            (update-in result [employer] conj name))
          {}
          input))

; Overly complicated
(defn process-with-core-functions
  [input]
  (->> input
       (map (juxt :employer (comp list :name)))
       (map (partial apply hash-map))
       (apply merge-with concat)))

(defn process-uniformely-with-thread-macros
  [input]
  (-> input
      (->> (group-by :employer))
      (get "pulga")
      (->> (map :name))))

(def filter-using-set
  (partial filter (comp #{"cat heaven"} :current-city)))
