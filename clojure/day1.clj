;part 1
(defn fuel "Fuel required for mass" [mass] (- (quot mass 3) 2))

(defn read-input [filename str-conv]
  (map str-conv (clojure.string/split (slurp filename) #"\n")))

(reduce + (map fuel (read-input "/media/mocha/massData1/dev/adventOfCode2019/inputs/day1-input.txt" #(Integer. %))))

;part 2
(defn real-fuel "Add fuel for the required mass of fuel as well" [mass] 
  (reduce + 0 (take-while #(> % 0) (iterate fuel (fuel mass)))))

(reduce + (map real-fuel (read-input "/media/mocha/massData1/dev/adventOfCode2019/inputs/day1-input.txt" #(Integer. %))))

; explore
(fuel 12)

(real-fuel 12)

(defn read-input2 [filename]
  (with-open [rdr (clojure.java.io/reader filename)]
  (reduce conj [] (line-seq rdr))))






