(ns brave.chapter5.index)

(def not-empty? (complement empty?))

(defn print-each
  [& values]
  (when (not-empty? values)
    (println (first values))
    (recur (rest values))))

(defn print-each-with-loop
  [& values]
  (loop [remaining-values values]
    (when (not-empty? remaining-values)
      (println (first remaining-values))
      (recur (rest remaining-values)))))

(defn pipe3
  [f g h]
  (fn [& args]
    (h (g (apply f args)))))

(defn pipen
  [& fns]
  (fn [& args]
    (reduce #(%2 %1)
            (apply (first fns) args)
            (rest fns))))

(defn compn
  [& fns]
  (fn [& args]
    (apply (apply pipen (reverse fns)) args)))

(defn mapset
  [& args]
  (set (apply map args)))

(defn attr
  [property]
  (comp property :attributes))
