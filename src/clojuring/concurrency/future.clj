(ns clojuring.concurrency.future)

(def ^:private log (atom {:history []}))

(defn ^:private update-log-history
  [log-id]
  (swap! log update-in [:history]
         conj {:name log-id :timestamp (System/currentTimeMillis)}))

;; Slow and inefficient
(defn run-tasks
  "Run tasks sequentially"
  [tasks]
  (for [task tasks] (task)))

;; doall is necessary to run futures eagerly, not lazily.
(defn dispatch-tasks
  "Dispatch tasks in parallel (immediate execution)"
  [tasks]
  (doall (map #(future (%)) tasks)))

(defn dispatch-and-report-tasks
  "Dispatch tasks in parallel (immediate execution) and return list of task
  results."
  [tasks]
  (->> tasks
       (map #(future (%)))
       (doall)
       (map deref)))

(comment
  (def tasks
    [(fn [] (Thread/sleep 3000) (update-log-history "fetch-pages"))
     (fn [] (Thread/sleep 3000) (update-log-history "fetch-accounts"))])
  (dispatch-and-report-tasks tasks)
  )

