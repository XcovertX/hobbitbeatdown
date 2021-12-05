(ns hobbitbeatdown.core
  (:gen-class))

(def filename "creatures.csv")

(defn -main
  [& args])

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "chest" :size 8}
                             {:name "downstairs" :size 1}
                             {:name "left-leg" :size 3}
                             {:name "left-arm" :size 3}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(defn hit
  [asym-body-parts]
  (let [sym-parts (symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(defn addHundo
  [number]
  (+ number 100))

(defn dec-maker
  [numberToDec times]
  (loop [count 0 number numberToDec]
    (println (str "Dec " number))
    (if (> count times)
      number
      (recur (inc count) (dec number)))))

(def names ["bilbo" "gandalf" "zeb" "meg" "ruvy"])

(def weapon
  [{:type "sword" :damage 8}
   {:type "knife" :damage 7}
   {:type "club" :damage 3}
   {:type "hammer" :damage 4}
   {:type "throwing star" :damage 1}
   {:type "dagger" :damage 2}])


