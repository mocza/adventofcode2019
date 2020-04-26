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
      
(defn exec-program "Executes a program of intcode instructions till halt opcode" 
  [input pos]
  (if (= (get input pos) 99) 
    input
    (recur (exec-instruction input pos) (+ pos 4)))
)

(def input (vec (read-input "/media/mocha/massData1/dev/adventOfCode2019/inputs/day2-input.txt" #"," #(Integer. %))))

;test
(use 'clojure.test)
(use 'clojure.test.junit)

(deftest exec-instruction
  (is (= [2,0,0,0,99] (exec-instruction [1,0,0,0,99] 0)))
  )

(deftest abc
  (is (= 5 (+ 2 2)))
  )

(with-junit-output
  (run-tests 'user)
)

(exec-instruction [1,0,0,0,99] 0)

(exec-instruction [2,3,0,3,99] 0)

(exec-instruction [2,4,4,5,99,0] 0)

(exec-instruction [1,1,1,4,99,5,6,0,99] 0)

(exec-instruction [1,1,1,4,2,5,6,0,99] 4)

(exec-program [1,1,1,4,99,5,6,0,99] 0)

(exec-program input 0)

