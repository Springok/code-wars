(ns code-wars.duplicate-encoder
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest testing is]]))

(defn encode-dups [text]
  (let [lowered (str/lower-case text)
        freq (frequencies lowered)]
    (->> lowered
         (map #(if (= 1 (freq %)) \( \)))
         (apply str))))

(defn dotest [text expected]
  (is (= (encode-dups text) expected)))

(deftest sample
  (testing "basic"
    (dotest "din" "(((")
    (dotest "recede" "()()()")
    (dotest "(( @" "))((")
    (dotest "ABC" "(((")
    (dotest "AaBbC" "))))("))

  (testing "ignore-case" (dotest "Success" ")())())")))
