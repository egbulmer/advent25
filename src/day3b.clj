(ns day3b
  (:require [util :refer [solve open-input-file]]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(defn parse-input [in]
  (for [line (line-seq in)]
    (map (comp parse-long str) (seq line))))

(defn pow10 [x]
  (reduce * (repeat x 10)))

(defn joltage [batteries indices]
  (->> (map (fn [x y]
              (* x (pow10 y)))
            (map #(nth batteries %) (reverse indices))
            (range))
       (reduce +)))

(defn find-indices [batteries n]
  (let [len (count batteries)]
    (loop [n n idx 0 indices []]
      (if (zero? n)
        indices
        (let [start    idx
              end      (- len (- n 1))
              next-idx (first (sort-by #(nth batteries %) > (range start end)))]
          (recur (dec n)
                 (inc next-idx)
                 (conj indices next-idx)))))))

(defn day3b [in]
  (->> (parse-input in)
       (map (fn [battery]
              (let [inds (find-indices battery 12)]
                (joltage battery inds))))
       (reduce +)))

(deftest test-day3b
  (is (= (solve day3b "day3-example.txt") 3121910778619))
  (is (= (solve day3b "day3-input.txt") 171435596092638)))
