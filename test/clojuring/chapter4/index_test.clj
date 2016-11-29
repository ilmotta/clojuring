(ns clojuring.chapter4.index-test
  (:use expectations clojuring.chapter4.index))

; Validates any given suspect with validators map
(let [validators {:name (complement clojure.string/blank?)
                  :glitter-index #(>= % 0)}]
  (expect false (from-each [suspect [{:name "Trix" :glitter-index -1}
                                     {:name "" :glitter-index -1}
                                     {:name "   " :glitter-index 10}]]
                           (validate validators suspect)))
  (expect true (from-each [suspect [{:name "Icaro" :glitter-index 0}
                                    {:name "Noob" :glitter-index 100}]]
                          (validate validators suspect))))

; Converts suspects seq of maps back to CSV
(let [filename "test/clojuring/fixtures/suspects.csv"
      suspects (mapify (parse (slurp filename)))]
  (expect "Edward Cullen,10\nBella Swan,0"
          (to-csv (take 2 suspects))))

; :name converter returns itself
(expect "Julie" (convert :name "Julie"))

; :glitter-index converts to int
(expect -1 (convert :glitter-index "-1"))
(expect 2 (convert :glitter-index "2"))
(expect 0 (convert :glitter-index "0"))

; Converts CSV into rows of columns
(expect '(["col00" "col01"] ["col10" "col11"])
        (parse "col00,col01\ncol10,col11"))

; Returns a seq of maps"
(expect [{:name "Edward Cullen" :glitter-index 10}
         {:name "Bella Swan" :glitter-index 0}]
        (mapify [["Edward Cullen" "10"] ["Bella Swan" "0"]]))

; Throws if new element is not a collection
(expect IllegalArgumentException (doall (append [] 0)))

; Adds elements to the end of collection
(expect [0 1 2] (append [0 1] [2]))
