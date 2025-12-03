(ns day3a
  (:require [util :refer [solve open-input-file]]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(defn parse-input [in]
  (for [line (line-seq in)]
    (map (comp parse-long str) (seq line))))

(defn index [s]
  (map vector s (range)))

(defn max-joltage [batteries]
  (let [s (index batteries)
        [x xi] (first (sort-by first > (drop-last 1 s)))
        [y  _] (first (sort-by first > (drop (inc xi) s)))]
    (+ (* x 10) y)))

(defn day3a [in]
  (->> (parse-input in)
       (map max-joltage)
       (reduce +)))

(deftest test-day3a
  (is (= (solve day3a "day3-example.txt") 357))
  (is (= (solve day3a "day3-input.txt") 17244)))
