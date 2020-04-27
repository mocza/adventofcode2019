;part 1
(defn read-input [filename delimiter str-converter]
  (map str-converter (clojure.string/split (slurp filename) delimiter)))

(defn exec-instruction "Executes a single 4 element intcode instruction consisting of operation code, 2 operand indeces and result index" 
  [input, pos] 
      (let [instruction (subvec input pos (+ pos 4))
        operation (if (= (first instruction) 1) + *)
        first-operand (get input (first (next instruction)))
        second-operand (get input (first (next (next instruction))))
        result (operation first-operand second-operand)
        result-pos (first (next (next (next instruction)))) 
        before-sub (subvec input 0 result-pos)
        after-sub (subvec input (inc result-pos))] 
        (into (conj before-sub result) after-sub) )
    )
      
(defn exec-program "Executes intcode program of 4 lenght instructions till halt opcode" 
  [input pos]
  (if (= (get input pos) 99) 
    input
    (recur (exec-instruction input pos) (+ pos 4)))
)

(def input (vec (read-input "/media/mocha/massData1/dev/adventOfCode2019/inputs/day2-input.txt" #"," #(Integer. %))))

(defn reset-input [noun verb]
  (into (conj (subvec input 0 1) noun verb) (subvec input 3))
  )

;test
(use 'clojure.test)
(use 'clojure.test.junit)

(deftest test-exec-instruction  
  (testing "single instruction"
    (testing "addition"
      (is (= [2,0,0,0,99] (exec-instruction [1,0,0,0,99] 0))))
    (testing "multiplication"
      (is (= [2,3,0,6,99] (exec-instruction [2,3,0,3,99] 0)) "write result inside instruction index")
      (is (= [2,4,4,5,99,9801] (exec-instruction [2,4,4,5,99,0] 0)) "write result after instruction index")
      (is (= [9801,3,0,3,2,8,8,0,99,0] (exec-instruction [2,3,0,3,2,8,8,0,99,0] 4)) "write result before instruction index")
      )
    )
  (testing "two instructions"
    (testing "addition"
      (is (= [1,1,1,4,2,5,6,0,99] (exec-instruction [1,1,1,4,99,5,6,0,99] 0)))
      (is (= [30,1,1,4,2,5,6,0,99] (exec-instruction [1,1,1,4,2,5,6,0,99] 4)))
      )
      )
  )

(deftest test-exec-program
  (is (= [30,1,1,4,2,5,6,0,99] (exec-program [1,1,1,4,99,5,6,0,99] 0)) "add then multiply")
  (is (= [11,1,1,4,1,5,6,0,99] (exec-program [2,1,1,4,99,5,6,0,99] 0)) "multiply then add")
  (is (= 12490719 (first (exec-program (reset-input 12 2) 0))) "sample input")
  )

;part2
(require '[clojure.math.combinatorics :as combo])

(defn solver [intcode-program-output]
  (let [formatter #(format "%02d%02d" (first %) (first (next %)))
        result (get (first (filter
                            #(= intcode-program-output (first %))
                            (map
                             #(let [noun (first %) verb (first (rest %))]
                                (vector (first (exec-program (reset-input noun verb) 0)) [noun verb]))
                             (combo/permuted-combinations (range 0 100) 2)))) 1)] 
    (formatter result))
  )

(deftest test-solver
  (is (= "2003" (solver 19690720)))
  )


(with-junit-output
  (run-tests 'user))

