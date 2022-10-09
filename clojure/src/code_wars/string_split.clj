(ns code-wars.string-split
  (:require
    [clojure.string :as str]
    [clojure.test :refer [deftest is]]))

; ref:  https://www.codewars.com/kata/515de9ae9dcfc28eb6000001

; Complete the solution so that it splits the string into pairs of two characters. If the string contains an odd number of characters then it should replace the missing second character of the final pair with an underscore ('_').

; Examples:

; * 'abc' =>  ['ab', 'c_']
; * 'abcdef' => ['ab', 'cd', 'ef']

; initial attempt
; (defn string-split [string]
;   (cond-> string
;     (odd? (count string)) (str "_")
;     true  (->> (partition 2)
;                (map str/join))))
;
; mimic other's solution, use the pad in partition function
(defn string-split [string]
  (->> string
     (partition 2 2 "_")
     (map str/join)))


(deftest twosum-example-tests
  (is (= ["cd" "ab" "ef" "g_"] (string-split "cdabefg")))
  (is (= ["cd" "ab" "ef" "gh"] (string-split "cdabefgh")))
  (is (= ["ab" "cd"] (string-split "abcd"))))

; (def ^:private sample-tests
;   [{:tested-str "cdabefg"
;     :result ["cd" "ab" "ef" "g_"]}
;    {:tested-str "cdabefgh"
;     :result ["cd" "ab" "ef" "gh"]}
;    {:tested-str "abcd"
;     :result ["ab" "cd"]}])
;
; (doseq [{:keys [tested-str result]} sample-tests]
;   (eval
;     `(deftest ~(symbol (str "sample-test-" tested-str))
;       (is (~'= ~result (~'solution ~tested-str))))))


(comment
  (let [string "cdabefg"]
    (cond-> string
      (odd? (count string)) (str "_")
      true  (->> (partition 2)
                 (map str/join)))))

