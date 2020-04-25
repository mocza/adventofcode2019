;part 1
(defn exec-instruction [input] 
    (let [operation (if (= (first input) 1) + *)
      first-operand (get input (first (next input)))
      second-operand (get input (first (next (next input))))
      result (operation first-operand second-operand)
      result-pos (first (next (next (next input)))) 
      before-sub (subvec input 0 result-pos)
      after-sub (subvec input (inc result-pos))] 
      (into (conj before-sub result) after-sub) )
    )

(defn exec-instruction2 [input, pos] 
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
      

(defn exec-program [input]
  ( (exec-instruction input))
  )  


(exec-instruction2 [1,0,0,0,99] 0)

(exec-instruction2 [2,3,0,3,99] 0)

(exec-instruction2 [2,4,4,5,99,0] 0)

(exec-instruction2 [1,1,1,4,99,5,6,0,99] 0)

(exec-instruction2 [1,1,1,4,2,5,6,0,99] 4)

(println "Hello")

(inc 1 2)

(let [operation (if (= (first input) 1) + *)
  indeces (conj [] (get input (first (next input))) (get input (first (next input))) )
  indeces2 (conj [] (get input (first (next input))) (get input (first (next input))) )
]
  indeces2
)
