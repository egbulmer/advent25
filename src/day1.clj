(ns day1
  (:require [clojure.test :as test :refer [deftest is run-tests]]
            [clojure.java.io :as io]
            [nextjournal.clerk :as clerk]
            [util :as util :refer [solve]]))

(defn parse-instruction
  "Parse instruction of the format '(L|R)X' into a positive or negative step count."
  [s]
  (let [dir (first s)
        n   (parse-long (subs s 1))]
    (case dir
      \L (* -1 n)
      \R n)))

(defn rotate-dial-1
  "Rotate dial n clicks. L = negative, R = positive."
  [dial n]
  (let [x (+ dial (mod n 100))]
    (cond
      (> x 99) (- x 100)
      (< x  0) (+ x 100)
      :else    x)))

(defn rotate-dial-2
  "Rotate dial n clicks. L = negative, R = positive. Record number of times the dial was at position 0
  and return it."
  [dial n]
  (letfn [(step [n dirn]
            (let [x (+ n dirn)]
              (cond
                (> x 99) 0
                (< x  0) 99
                :else    x)))]
    ;; TODO - This seems slow ... Must be a faster way?
    (let [dirn  (if (pos? n) 1 -1)
          steps (take (abs n) (drop 1 (iterate #(step % dirn) dial)))]
      [(last steps) (count (filter zero? steps))])))

(defn day1a [in]
  (loop [instructions (map parse-instruction (line-seq in))
         dial         50
         result       0]
    (if-let [n (first instructions)]
      (let [new-dial  (rotate-dial-1 dial n)]
        (recur (rest instructions)
               new-dial
               (if (= new-dial 0) (inc result) result)))
      result)))

(defn day1b [in]
  (loop [instructions (map parse-instruction (line-seq in))
         dial         50
         result       0]
    (if-let [n (first instructions)]
      (let [[x z]  (rotate-dial-2 dial n)]
        (do
          (recur (rest instructions)
                 x
                 (+ result z))))
      result)))

(deftest test-day1
  (is (= (solve day1a "day1-example.txt") 3))
  (is (= (solve day1a "day1-input.txt") 1172))
  (is (= (solve day1b "day1-example.txt") 6))
  (is (= (solve day1b "day1-input.txt") 6932)))
