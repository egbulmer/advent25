(ns day4a
  (:require [util :refer [solve open-input-file]]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(defn parse-input [in]
  (let [lines  (line-seq in)
        width  (count (first lines))
        height (count lines)]
    {:width width
     :height height
     :data (into [] (mapcat vec lines))}))

(defn get-tile [g x y]
  (let [idx (+ (* (:width g) y) x)]
    (nth (:data g) idx)))

(defn in-grid? [g x y]
  (and (>= x 0) (< x (:width g))
       (>= y 0) (< y (:height g))))

(defn get-neighbours [g x y]
  (->>
   (for [dx [-1  0  1]
         dy [-1  0  1]]
     [(+ dx x) (+ dy y)])
   (filter #(in-grid? g (first %) (second %)))
   (filter #(not (= % [x y])))))

(defn paper? [g x y]
  (= (get-tile g x y) \@))

(defn accessible? [g x y]
  (< (->> (get-neighbours g x y)
               (filter (fn [[x y]] (paper? g x y)))
               (count))
     4))

(defn day4a [in]
  (let [g (parse-input in)]
    (->>
     (for [x (range 0 (:width g))
           y (range 0 (:height g))]
       (if (and (paper? g x y)
                (accessible? g x y))
         1
         0))
     (reduce +))))

(deftest test-day4a
  (is (= 13 (solve day4a "day4-example.txt")))
  (is (= 1505 (solve day4a "day4-input.txt"))))
