(ns clojuring.seqs.lazy-test
  (:use expectations clojuring.seqs.lazy))

; A vector is not a sequence, but a list is.
(expect false (seq? []))
(expect true (seq? (list)))

; Sequences can be used with transducers...
(expect '(11 13 15 17 19)
        (->> (range 10)
             (filter odd?)
             (map #(+ % 10))
             (take 5)))

; Transducers run left-to-right when composed and don't care about input/output.
(def xform (comp
             (filter #(= (:color %) "Green"))
             (map #(update % :color clojure.string/upper-case))))

(def only-lower-positions
  (filter #(< (:position %) 10)))

(def colors [{:color "Brown" :position 4}
             {:color "Green" :position 8}
             {:color "Black" :position 98}
             {:color "Green" :position 100}])

(expect '({:color "GREEN" :position 8})
        (transduce (comp xform only-lower-positions) conj colors))

; How do you call map on each value of seq and concat it?
(expect '(0 0 1 0 1 2)
        (mapcat #(range %) (range 4)))
