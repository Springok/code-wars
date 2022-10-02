(ns code-wars.two-sum
  (:require
    [clojure.test :refer [deftest is]]))
; https://www.codewars.com/kata/52c31f8e6605bcc646000082/clojure
; https://leetcode.com/problems/two-sum/

; thinking points: iteration, lazy?
; 1. find combinations
; 2. iterative over them to until found
;    2-1, end case, sum or no more combinations

(defn combinations [numbers]
  (let [last-idx (count numbers)]
    (for [x (range 0 last-idx)
          y (range 0 last-idx)
          :when (not= x y)]
      [x y])))

(defn twosum [numbers target]
  (reduce (fn [_result comb]
            (let [sum (apply + (mapv numbers comb))]
              (if (= target sum)
                (reduced comb)
                comb)))
          nil
          (combinations numbers)))

(defn twosum-loop [numbers target]
   (loop [combs (combinations numbers)]
     (if (or (empty? combs)
             (= target (->> (mapv numbers (first combs))
                            (apply +))))
       (first combs)
       (recur (rest combs)))))

(deftest twosum-example-tests
  (is (= (sort < (twosum '[1 2 3] 4)) '[0 2]))
  (is (= (sort < (twosum '[1234 5678 9012] 14690)) '[1 2]))
  (is (= (sort < (twosum '[2 2 3] 4)) '[0 1]))

  (is (= (sort < (twosum-loop '[1 2 3] 4)) '[0 2]))
  (is (= (sort < (twosum-loop '[1234 5678 9012] 14690)) '[1 2]))
  (is (= (sort < (twosum-loop '[2 2 3] 4)) '[0 1])))


(comment
  (pop (vec (combinations '[2 2 3])))
  (twosum-loop '[1 2 3] 4)
  (twosum '[2 2 3] 4)
  (apply + (mapv [2 2 3] [0 1])))

