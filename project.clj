(defproject clojuring "0.1.0-SNAPSHOT"
  :description "My personal cave for learning Clojure"
  :url "https://github.com/ilmotta/clojuring"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot clojuring.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-kibit "0.1.3"]
                             [lein-expectations "0.0.7"]
                             [lein-auto "0.1.3"]]
                   :dependencies [[expectations "2.1.8"]]}})
