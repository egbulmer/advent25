(ns day2a
  (:require [clojure.string :as s]
            [clojure.test :as test :refer [deftest is run-tests]]
            [util :refer [solve]]))

(defn pow10 [x]
  (int (Math/pow 10 x)))

(defn order-of-magnitude [x]
  (int (Math/log10 x)))

(defn digit-count [x]
  (inc (order-of-magnitude x)))

(defn echo
  "Create an echo of x (e.g. 123 -> 123123)"
  [x]
  (if (zero? x)
    0
    (let [d (+ 1 (order-of-magnitude x))]
      (* x (int (+ (Math/pow 10 d) 1))))))

(defn echo-seq
  "Generate a sequence of echo numbers between [start, end] inclusive."
  [start end]
  (let [x (dec (int (/ (digit-count start) 2)))
        y (int (/ (digit-count end) 2))]
    (->> (range (pow10 x) (pow10 y))
         (map echo)
         (filter #(and (>= % start) (<= % end))))))

(defn parse-input [in]
  (->> (s/split (slurp in) #",")
       (map s/trim)
       (map (fn [part]
              (vec (map parse-long (s/split part #"-")))))))

(defn day1a [in]
  (->> (parse-input in)
       (mapcat #(apply echo-seq %))
       (reduce +)))

(deftest test-day1a
  (is (= (solve day1a "day2-example.txt") 1227775554))
  (is (= (solve day1a "day2-input.txt") 38310256125)))
