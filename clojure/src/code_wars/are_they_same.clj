(ns code-wars.are-they-same
  (:require
    [clojure.test :refer [deftest is testing]]))

; https://www.codewars.com/kata/550498447451fbbd7600041c/train/clojure

(defn compSame [a b]
  (let [set-a (sort (map * a a))
        set-b (sort b)]
    (and (some? a) (some? b)
         (= set-a set-b))))

(defn compSame2 [a b]
  (let [a' (frequencies (map * a a))
        b' (frequencies b)]
    (and (some? a) (some? b)
         (= a' b'))))

(deftest a-test1
  (testing "Test 1"
    (let [ur [121, 144, 19, 161, 19, 144, 19, 11]
          vr [121, 14641, 20736, 361, 25921, 361, 20736, 361]]
      (is (= (compSame ur vr) true)))))

(comment
  (set (map square [121, 144, 19, 161, 19, 144, 19, 11]))
  (apply or (map empty? [#{} #{}]))
  (set [121, 14641, 20736, 361, 25921, 361, 20736, 361])
  (= nil []))
