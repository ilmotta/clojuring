(ns clojuring.gedcom-parser)

(defn- surround-start
  [{:keys [type id]}]
   (if (nil? id)
     (str "<" type ">")
     (str "<" type " id=\"" id "\"" ">")))

(defn- surround-end
  [type]
  (str "</" type ">"))

(defn tag
  ([props] (tag props ""))
  ([props content]
   (str (surround-start props)
        content
        (surround-end (:type props)))))
