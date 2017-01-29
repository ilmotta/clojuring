(ns clojuring.typing-simulator.core
  (:require [clojuring.typing-simulator.store :as store]))

(def config
  {:frame-interval 100
   :typing-interval 10})

(defrecord Key [value status])

(def home-row [\A \S \D \F \G \H \J \K \L])

(defn type-on-key [k]
  ((rand-nth [store/press-key store/release-key]) k))

(defn new-key [value]
  (map->Key {:value value :status :key/released}))

(defn render-frame [_]
  (send-off *agent* render-frame)
  (dorun
    (for [k (sort-by :value (store/fetch-keyboard))]
      (println "Value:" (:value k) "Status:" (:status k))))
  (println "------")
  (Thread/sleep (config :frame-interval))
  nil)

(defn do-type [_]
  (send-off *agent* do-type)
  (dorun (map type-on-key (store/fetch-keyboard)))
  (Thread/sleep (config :typing-interval))
  nil)

(defn setup [keyboard-keys]
  (dorun (map (comp store/add-key new-key) keyboard-keys))
  nil)

(defn -main []
  (setup home-row)
  (send-off (agent nil) render-frame)
  (send-off (agent nil) do-type)
  )
