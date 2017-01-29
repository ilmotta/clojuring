(ns clojuring.debounce)

(defn debounce
  "Takes a function f and optional max-delay in milliseconds and returns a new
  function which will be executed only once every max-delay. If called more
  than once within the max-delay window returns the cached return value of
  first call. max-delay defaults to 300ms."
  ([f] (debounce f 300))
  ([f max-delay]
   (let [state (atom {:called-at 0 :return-value nil})]
     (fn [& args]
       (let [called-at (:called-at @state)
             current-time (System/currentTimeMillis)]
         (if (> (- current-time called-at) max-delay)
           (let [return-value (apply f args)]
             (reset! state {:called-at current-time :return-value return-value})
             return-value)
           (do
             (reset! state (assoc @state :called-at current-time))
             (:return-value @state))))))))

(comment
  (def solve-life
    (debounce
      (fn [n]
        (Thread/sleep 50)
        n)))

  (map solve-life (range 3))
  )
