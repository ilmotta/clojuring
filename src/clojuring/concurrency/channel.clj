(ns clojuring.concurrency.channel
  (:require [clojure.core.async :refer (chan go >!! <!)]))

(defn go-print
  "Pull messages from channel and print them"
  [channel]
  (go
    (loop []
      (when-some [val (<! channel)]
        (println "Received message:" val)
        (recur)))))
