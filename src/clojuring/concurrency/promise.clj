(ns clojuring.concurrency.promise)

(defn do-around
  [f & fargs]
  (fn [g & gargs]
    (let [begin (promise)
          end (promise)]
      (future
        (deliver begin (apply f fargs))
        (apply g gargs)
        (deliver end (apply f fargs)))
      [@begin @end])))

(defn benchmark-fn
  [task & args]
  (let [[begin end] ((do-around #(System/currentTimeMillis)) task)]
    {:duration (- end begin)}))

(defn print-benchmark [{:keys [duration]}]
  (println "Total:" duration "ms"))

(defn task []
  (Thread/sleep 1000)
  (println "Results: Super!"))

(comment
  (print-benchmark (benchmark-fn task))
  )
