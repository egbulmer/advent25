(ns util
  (:require [clojure.java.io :as io]))

(defn open-input-file [filename]
  (if-let [res (io/resource filename)]
    (io/input-stream res)
    (io/input-stream (io/file (str "resources/" filename)))))

(defn solve [f filename]
  (with-open [in (open-input-file filename)]
    (let [answer (f (io/reader in))]
      (println "answer:" answer)
      answer)))
