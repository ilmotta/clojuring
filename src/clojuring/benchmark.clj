(ns clojuring.benchmark
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.core.reducers :as reducers]))

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

(defn task-duration [task]
  (let [[begin end] ((do-around #(System/currentTimeMillis)) task)]
    {:duration (- end begin)}))

(defn benchmark
  ([task] (benchmark 1 task))
  ([n task]
   (sort-by :duration (repeatedly n #(task-duration task)))))

(defn report-benchmark [title results]
  (println title (quot (reduce + 0 (map :duration results)) (count results)) "ms"))

(defn read-catalog [file]
  (with-open [in-file (io/reader file)]
    (doall (csv/read-csv in-file))))

(defn import-slow [data]
  (reduce #(conj %1 %2) [] data))

(defn import-with-transient [data]
  (persistent!
    (reduce #(conj! %1 %2) (transient []) data)))

(defn import-with-reducers [data]
  (reducers/fold concat #(conj %1 %2) data))

;; Process using naive approach
(defn process-slow [catalog]
  (->> catalog
       (map first)
       (map bigint)
       (reduce + 0)))

;; Processing using reducers
(defn process-with-reducers [catalog]
  (->> catalog
       (reducers/map first)
       (reducers/map bigint)
       (reducers/fold +)))

;; Processing with reducers and function composition
(defn process-with-reducers-and-comp [catalog]
  (->> catalog
       (reducers/map (comp bigint first))
       (reducers/fold +)))

;; Processing with function composition
(defn process-with-comp [catalog]
  (->> catalog
       (map (comp bigint first))
       (reduce + 0)))

(defn benchmark-imports []
  (let [data (read-catalog "resources/catalog.csv")]
    (report-benchmark "Import with naive approach:" (benchmark 20 #(import-slow data)))
    (report-benchmark "Import with transient collection:" (benchmark 20 #(import-with-transient data)))
    (report-benchmark "Import with reducers:" (benchmark 20 #(import-with-reducers data)))
    (println "Finished benchmark.")))

(defn benchmark-processing []
  (let [data (read-catalog "resources/catalog.csv")
        catalog (import-slow data)]
    (report-benchmark "Naive approach:" (benchmark 10 #(process-slow catalog)))
    (report-benchmark "With reducers:" (benchmark 10 #(process-with-reducers catalog)))
    (report-benchmark "With composition" (benchmark 10 #(process-with-comp catalog)))
    (report-benchmark "With reducers and composition" (benchmark 10 #(process-with-reducers-and-comp catalog)))
    (println "Finished benchmark.")))
