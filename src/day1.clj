(ns day1
  (:require [clojure.test :as test :refer [deftest is run-tests]]
            [clojure.java.io :as io]
            [nextjournal.clerk :as clerk]
            [util :as util :refer [solve]]))

(defn rotate-dial
  "Rotate dial n clicks. L = negative, R = positive."
  [dial n]
  (let [x (+ dial (mod n 100))]
    (cond
      (> x 99) (- x 100)
      (< x  0) (+ x 100)
      :else    x)))

(defn parse-instruction
  "Parse instruction of the format '(L|R)X' into a positive or negative step count."
  [s]
  (let [dir (first s)
        n   (parse-long (subs s 1))]
    (case dir
      \L (* -1 n)
      \R n)))

(defn day1a [in]
  (loop [instructions (map parse-instruction (line-seq in))
         dial         50
         result       0]
    (if-let [n (first instructions)]
      (let [new-dial  (rotate-dial dial n)]
        (recur (rest instructions)
               new-dial
               (if (= new-dial 0) (inc result) result)))
      result)))

(defn day1b [in]
  ;;; TODO - Solve day 1 part 2 here.
  nil)

(deftest test-day1
  (is (= (solve day1a "day1-example.txt") 3))
  (is (= (solve day1a "day1-input.txt") 1172)))
