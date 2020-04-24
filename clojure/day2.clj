;part 1
(defn exec-intcodes [input] 
    (let [operation (if (= (first input) 1) + *)
      first-operand (get input (first (next input)))
      second-operand (get input (first (next (next input))))
      result (operation first-operand second-operand)
      result-pos (first (next (next (next input)))) 
      before-sub (subvec input 0 result-pos)
      after-sub (subvec input (inc result-pos))] 
      (into (conj before-sub result) after-sub) )
    )
  

(exec-intcodes [1,0,0,0,99])

(exec-intcodes [2,3,0,3,99])

(exec-intcodes [2,4,4,5,99,0])

(exec-intcodes [1,1,1,4,99,5,6,0,99])

(println "Hello")

(let [operation (if (= (first input) 1) + *)
  indeces (conj [] (get input (first (next input))) (get input (first (next input))) )
  indeces2 (conj [] (get input (first (next input))) (get input (first (next input))) )
]
  indeces2
)
