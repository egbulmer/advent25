(ns day1
  (:require [clojure.test :refer [deftest is run-tests]]
            [clojure.java.io :as io]
            [nextjournal.clerk :as clerk]
            [util :refer [solve]]))

(defn day1a [in]
  ;;; TODO - Solve day 1 part 1 here.
  nil)

(defn day1b [in]
  ;;; TODO - Solve day 1 part 2 here.
  nil)

(deftest day1a-example
  (is (= 42 (solve day1a "day1-example.txt"))))

(deftest day1a-real
  (is (= 42 (solve day1a "day1-input.txt"))))

(deftest day1b-example
  (is (= 42 (solve day1b "day1-example.txt"))))

(deftest day1b-real
  (is (= 42 (solve day1b "day1-input.txt"))))
