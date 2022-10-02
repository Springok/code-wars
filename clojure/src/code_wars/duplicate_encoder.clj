(ns code-wars.duplicate-encoder
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest testing is]]))

; (defn to-chars [text]
;   (->> (char-array text)
;        (map str/lower-case)))

(defn encode-map [chars]
  (->> chars
       (frequencies)
       (reduce-kv (fn [m k v]
                    (assoc m k (if (> v 1)
                                 \)
                                 \()))

                  {})))

(defn encode-dups [text]
  (let [chars (map str/lower-case (char-array text))
        encode-m (encode-map chars)]
    (->> chars
        (map #(get encode-m (str %)))
        (apply str))))

; (defn encode-dups2 [text]
;   [let [chars (->> (char-array text)
;                    (map str/lower-case))
;         encode-m (encode-map chars)]])

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




(comment
  (get (encode-map "dfd") (str \d))
  (encode-dups "dfd")
  (apply str (char-array "dfd"))
  (->> (frequencies (char-array "dfd"))
       (reduce-kv (fn [m k v]
                    (assoc m k (if (> v 1)
                                 \)
                                 \()))

                  {}))

  (map #(-> % val (> 1))))
