(ns day3-1)
(use 'clojure.test)
(use 'clojure.test.junit)

(defn collection-type [col] (let [listObject (type list)] {"class clojure.lang.PersistentList" :list} (str (type col))))

(with-test
  (defn direction-to-coordinates [from move-to]
    (let [move-to-direction (str (first (take 1  move-to)))
          move-to-length (Integer. (apply str (drop 1 move-to)))
          [x y] from
          [step start end f] ({"R" [1 (inc x) (inc (+ x move-to-length)) #(list % y)] "U" [1 (inc y) (inc (+ y move-to-length)) #(list x %)] "D" [-1 (dec y) (dec (- y move-to-length)) #(list x %)] "L" [-1 (dec x) (dec (- x move-to-length)) #(list % y)]} move-to-direction)
          ]
      (do
        ;(println (format "move-to-direction: %s, move-to-length: %s, start: %s, end: %s, step: %s" move-to-direction move-to-length start end step))
        (map f (range start end step))
        )))
  (is (= '((1, 0)) (direction-to-coordinates '(0, 0) "R1")) "go to right one step")
  (is (= '((0, 0)) (direction-to-coordinates '(1, 0) "L1")) "go left one step")
  (is (= '((0, 1)) (direction-to-coordinates '(0, 0) "U1")) "go up one step")
  (is (= '((1, 1)) (direction-to-coordinates '(1, 0) "U1")) "go up one step")
  (is (= '((0, 0)) (direction-to-coordinates '(0, 1) "D1")) "go down one step")
  (is (= '((1, 0) (2, 0) (3, 0) (4, 0) (5, 0) (6, 0) (7, 0) (8, 0) (9, 0) (10, 0)) (direction-to-coordinates '(0, 0) "R10")) "go to right 10 steps")
  (is (= '((9, 10) (8, 10) (7, 10)) (direction-to-coordinates '(10, 10) "L3")) "go to left 3 steps")
  (is (= (for [y (range 11 22)] (list 10 y)) (direction-to-coordinates '(10, 10) "U11")) "go up 11 steps")
  (is (= '((32,55) (32,54) (32,53) (32,52) (32,51) (32,50) (32,49) (32,48) (32,47)) (direction-to-coordinates '(32, 56) "D9")) "go down 9 steps")
  )

(with-test
  (defn directions-to-coordinates [start directions]
    (loop [from start moves directions result '()]
      (if (empty? moves)
        (cons start result)
        (let [coords (direction-to-coordinates from (first moves))]
          (recur (last coords) (rest moves) (concat result coords)))))
    )
  (is (= '((0,0) (1,0)) (directions-to-coordinates '(0,0) '("R1") )))
  (is (= '((0,0) (0,1)) (directions-to-coordinates '(0,0) '("U1") )))
  (is (= '((0,0) (1,0) (1,1)) (directions-to-coordinates '(0,0) '("R1" "U1") )))
  (is (= '((0,0) (1,0) (2,0) (2,1)) (directions-to-coordinates '(0,0) '("R2" "U1"))))
  )

(with-test
  (defn direction-to-endpoint [from move-to]
    (let [move-to-direction (str (first (take 1 move-to)))
          move-to-length (Integer. (apply str (drop 1 move-to)))
          [x y] from
          ;[step start end f] ({"R" [1 (inc x) (inc (+ x move-to-length)) #(list % y)] "U" [1 (inc y) (inc (+ y move-to-length)) #(list x %)] "D" [-1 (dec y) (dec (- y move-to-length)) #(list x %)] "L" [-1 (dec x) (dec (- x move-to-length)) #(list % y)]} move-to-direction)
          [end f] ({"R" [(+ x move-to-length) (fn [end] [end y])] "U" [(+ y move-to-length) (fn [end] [x end])] "D" [(- y move-to-length) (fn [end] [x end])] "L" [(- x move-to-length) (fn [end] [end y])]} move-to-direction)
          ]
        (f end)
      ))
  (is (= [1 0] (direction-to-endpoint [0 0] "R1")) "go right one step")
  (is (= [0 0] (direction-to-endpoint [1 0] "L1")) "go left one step")
  (is (= [0 1] (direction-to-endpoint [0 0] "U1")) "go up one step")
  (is (= [1 1] (direction-to-endpoint [1 0] "U1")) "go up one step")
  (is (= [0 0] (direction-to-endpoint [0 1] "D1")) "go down one step")
  (is (= [10 0] (direction-to-endpoint [0 0] "R10")) "go right 10 steps")
  (is (= [7 10] (direction-to-endpoint [10 10] "L3")) "go left 3 steps")
  (is (= [10 21] (direction-to-endpoint [10 10] "U11")) "go up 11 steps")
  (is (= [32 47] (direction-to-endpoint [32, 56] "D9")) "go down 9 steps")
  )

(with-test
  (defn directions-to-endpoints [start directions]
    (loop [from start moves directions result []]
      (if (empty? moves)
        (cons start result)
        (let [endpoint (direction-to-endpoint from (first moves))]
          (recur endpoint (rest moves) (conj result endpoint))))))
  (is (= [[0 0] [1 0]] (directions-to-endpoints [0 0] ["R1"])))
  (is (= [[0 0] [0 1]] (directions-to-endpoints [0 0] ["U1"])))
  (is (= [[0 0] [1 0] [1 1]] (directions-to-endpoints [0 0] ["R1" "U1"])))
  (is (= [[0 0] [2 0] [2 1]] (directions-to-endpoints [0 0] ["R2" "U1"])))
 )  

(with-test
  (defn intersections "Returns the points where two lines crosses except the start point if both lines start from same point" 
    [line1 line2] 
    (let [line1-start (first line1) line1-directions (second line1)
          line2-start (first line2) line2-directions (second line2)
          has-same-start? (= line1-start line2-start)
          line1-points (for [point (directions-to-coordinates line1-start line1-directions) :when (and has-same-start? (not (= point line1-start)))] point)
          line2-points (for [point (directions-to-coordinates line2-start line2-directions) :when (and has-same-start? (not (= point line2-start)))] point)]
      (do 
        ;(print (format "line1-points: %s, line2-points: %s" line1-points line2-points))
        (for [line1-point line1-points line2-point line2-points :when (= line1-point line2-point)] line1-point)      
        )
      )
    )
  (is (= '((1,0)) (intersections '((0,0) ("R1")) '((0,0) ("R1"))) )) 
  (is (= '((7,6) (4,4)) (intersections '((1,1) ("R8","U5","L5","D3")) '((1,1) ("U7","R6","D4","L4")))))
  )



(with-test 
  (defn line-range [coord1 coord2] (range (min coord1 coord2) (inc (max coord1 coord2)))
    )  
  (is (= (range 0 3) (line-range 0 2)))
  (is (= (range 0 3) (line-range 2 0)))
  (is (= '(3) (line-range 3 3)))
  )

(with-test 
  (defn horizontal-line? [line] 
    (let [[[point1-x point1-y] [point2-x point2-y]] line] 
      (if (= (- point1-y point2-y) 0) true false)))
  (is (= true (horizontal-line? [[0 1] [2 1]])))
  (is (= false (horizontal-line? [[2 2] [2 0]]))))

(with-test
  (defn vertical-line? [line] 
    (if (not (horizontal-line? line)) true false))
  (is (= false (vertical-line? [[0 1] [2 1]])))
  (is (= true (vertical-line? [[2 2] [2 0]]))))

(with-test
  (defn coord-on-line? [coord line] 
    (let [[[line-x1 line-y1] [line-x2 line-y2]] line] 
      (if (horizontal-line? line)
        (if (not (empty? (clojure.set/intersection (set [coord]) (set (line-range line-x1 line-x2))))) true false)
        (if (not (empty? (clojure.set/intersection (set [coord]) (set (line-range line-y1 line-y2))))) true false)
        )
      )
    )
  (is (= false (coord-on-line? 3 [[0 1] [2 1]])) "coordinate is not on horizontal line") 
  (is (= false (coord-on-line? 3 [[2 2] [2 0]])) "coordinate is not on vertical line")
  (is (= true (coord-on-line? 2 [[0 1] [2 1]])) "coordinate is on horizontal line")
  (is (= true (coord-on-line? 0 [[2 2] [2 0]])) "coordinate is on vertical line")
  )

(with-test 
  (defn fixed-coord [line] 
    (let [[[line-x1 line-y1] [line-x2 line-y2]] line]
         (if (horizontal-line? line) line-y1 line-x1))
    )
  (is (= 1 (fixed-coord [[0 1] [2 1]])) "horizontal line")
  (is (= 2 (fixed-coord [[2 2] [2 0]])) "vertical line")
  )

(with-test 
  (defn both-horizontal? [line1 line2] (if (and (horizontal-line? line1) (horizontal-line? line2)) true false)
    )
  (is (= false (both-horizontal? [[0 1] [2 1]] [[2 2] [2 0]])))
  (is (= true (both-horizontal? [[0 1] [2 1]] [[0 1] [2 1]])))
  )

(with-test
  (defn both-vertical? [line1 line2] (if (and (vertical-line? line1) (vertical-line? line2)) true false))
  (is (= false (both-vertical? [[0 1] [2 1]] [[2 2] [2 0]])))
  (is (= true (both-vertical? [[3 2] [3 0]] [[3 2] [3 0]])))
  )

(with-test 
  (defn one-horizontal-one-vertical? [line1 line2] (not (or (both-horizontal? line1 line2) (both-vertical? line1 line2))))
  (is (= true (one-horizontal-one-vertical? [[0 1] [2 1]] [[2 2] [2 0]])) "one horizontal one vertical")
  (is (= false (one-horizontal-one-vertical? [[3 2] [3 0]] [[3 2] [3 0]])) "two verticals")
  (is (= false (one-horizontal-one-vertical? [[0 1] [2 1]] [[0 1] [2 1]])) "two horizontals")
  )

(with-test (defn point-on-line? [point line] 
             (let [[point-x point-y] point [[line-x1 line-y1] [line-x2 line-y2]] line] 
               (if (and (not (empty? (clojure.set/intersection (set [point-x]) (set (line-range line-x1 line-x2)))))
                        (not (empty? (clojure.set/intersection (set [point-y]) (set (line-range line-y1 line-y2))))))
                 true false)
               )
             )
  (is (= true (point-on-line? [2 1] [[0 1] [2 1]])))
  (is (= false (point-on-line? [2 2] [[0 1] [2 1]])))
  )

(with-test 
  (defn horizontal-vertical-intersection [line1 line2]
    (let [[[line1-x1 line1-y1] [line1-x2 line1-y2]] line1 [[line2-x1 line2-y1] [line2-x2 line2-y2]] line2
          intersect-y (if (horizontal-line? line1) line1-y1 (if (horizontal-line? line2) line2-y1 nil))
          intersect-x (if (vertical-line? line1) line1-x1 (if (vertical-line? line2) line2-x1 nil))]
      (if (and (point-on-line? [intersect-x intersect-y] line1) (point-on-line? [intersect-x intersect-y] line2)) 
        [[intersect-x intersect-y]] []))
    )
  (is (= [[2 1]] (horizontal-vertical-intersection [[0 1] [2 1]] [[2 2] [2 0]])))
  (is (= [] (horizontal-vertical-intersection [[1 1] [9 1]] [[7 8] [7 4]])) "no intersection")  
  )

(with-test 
  (defn horizontal-intersections [line1 line2]
    (let [[[line1-x1 line1-y1] [line1-x2 line1-y2]] line1 [[line2-x1 line2-y1] [line2-x2 line2-y2]] line2 
          same-y? #(if (and (= line1-y1 line2-y1)) true false)]
      (if (same-y?) 
        (map (fn [x-coord] [x-coord line1-y1]) (clojure.set/intersection (set (line-range line1-x1 line1-x2)) (set (line-range line2-x1 line2-x2))))
        [])
      )
    )
  (is (= [[0 1] [1 1] [2 1]] (horizontal-intersections [[0 1] [2 1]] [[0 1] [2 1]])) "identical lines overlap at every point")
  (is (= [] (horizontal-intersections [[0 1] [2 1]] [[2 0] [7 0]])) "no overlap")  
  )

(with-test
  (defn vertical-intersections [line1 line2]
    (let [[[line1-x1 line1-y1] [line1-x2 line1-y2]] line1 [[line2-x1 line2-y1] [line2-x2 line2-y2]] line2
          same-x? #(if (and (= line1-x1 line2-x1)) true false)]
      (if (same-x?) 
        (map (fn [y-coord] [line1-x1 y-coord]) (clojure.set/intersection (set (line-range line1-y1 line1-y2)) (set (line-range line2-y1 line2-y2))))
        [])
      ))
  (is (= [[3 0] [3 1] [3 2]] (vertical-intersections [[3 2] [3 0]] [[3 2] [3 0]])) "identical lines overlap at every point")
  (is (= [] (vertical-intersections [[3 2] [3 0]] [[4 2] [4 0]])) "no overlap")
  )

(with-test 
  (defn line-intersections [line1 line2]
      (if (one-horizontal-one-vertical? line1 line2) (horizontal-vertical-intersection line1 line2)
          (if (both-horizontal? line1 line2) (horizontal-intersections line1 line2)
          (if (both-vertical? line1 line2) (vertical-intersections line1 line2) nil))
      )
    )
  (is (= [] (line-intersections [[0 1] [2 1]] [[3 2] [3 0]])) "horizontal and vertical lines does not intersect")
  (is (= [] (line-intersections [[3 2] [3 0]] [[0 1] [2 1]])) "vertical and horizontal lines does not intersect")
  (is (= [[2 1]] (line-intersections [[0 1] [2 1]] [[2 2] [2 0]])) "horizontal and vertical lines intersect")
  (is (= [[2 1]] (line-intersections [[2 2] [2 0]] [[0 1] [2 1]])) "vertical and horizontal lines intersect")
  (is (= [[0 1] [1 1] [2 1]] (line-intersections [[0 1] [2 1]] [[0 1] [2 1]])) "two identical horizontal lines intersect on every point")
  (is (= [[3 0] [3 1] [3 2]] (line-intersections [[3 2] [3 0]] [[3 2] [3 0]])) "two identical vertical lines intersect on every point")
  (is (= [] (line-intersections [[0 1] [2 1]] [[2 0] [7 0]])) "two horizontal lines does not intersect")  
  (is (= [] (line-intersections [[3 2] [3 0]] [[4 2] [4 0]])) "two vertical lines does not intersect")
  )

(with-test
  (defn intersections2 "Returns the points where two lines crosses except the start point if both lines start from same point"
    [line1 line2]
    (let [line1-start (first line1) line1-directions (second line1)
          line2-start (first line2) line2-directions (second line2)
          has-same-start? (= line1-start line2-start)
          lines1 (for [point (directions-to-endpoints line1-start line1-directions)] point)
          lines2 (for [point (directions-to-endpoints line2-start line2-directions)] point)]      
          (apply concat (for [line1 (partition 2 1 lines1) line2 (partition 2 1 lines2)] (line-intersections line1 line2))))
    )
  (is (= [[0 0] [1 0]] (intersections2 [[0 0] ["R1"]] [[0 0] ["R1"]])) "identical lines intersect at each point")
  (is (= [[2 1]] (intersections2 [[0 1] ["R2"]] [[2 2] ["D2"]])) "horizontal and vertical lines intersect at single point")
  (is (= [[2 1]] (intersections2 [[0 1] ["R2"]] [[2 2] ["D2" "R5"]])) "horizontal and vertical lines intersect at single point")
  (is (= [[1 1] [7 6] [4 4]] (intersections2 [[1 1] ["R8" "U5" "L5" "D3"]] [[1 1] ["U7" "R6" "D4" "L4"]])) "same start point ignored from intersections")
)

;; (def need-filter? true)

;; (filter #(if (need-filter?) (if (not (= % [0 0])) true false) false) (apply concat (for [point1 [[0 0]] point2 [[1 1]]] [point1 point2])))

(with-junit-output
  (clojure.test/test-vars [#'day3-1/intersections2]))


(with-test
  (defn manhattan-distance [point1 point2] 
    (let [[x1 y1] point1 [x2 y2] point2] (+ (java.lang.Math/abs (- x2 x1)) (java.lang.Math/abs (- y2 y1))))
    )
  (is (= 1 (manhattan-distance '(0,0) '(1,0))))
  (is (= 6 (manhattan-distance '(1,1) '(4,4))))
  )

(with-test
  (defn closest-intersection [from line1 line2]
    (let [cross-points (filter #(not (= from %)) (intersections2 line1 line2))
          cross-point-distances (map #(manhattan-distance from %) cross-points)] 
      (apply min cross-point-distances)
      )
    )
  (is (= 6 (closest-intersection [1 1] [[1 1] ["R8" "U5" "L5" "D3"]] [[1 1] ["U7","R6","D4","L4"]])))
  (is (= 159 (closest-intersection '(1,1) '((1,1) ("R75","D30","R83","U83","L12","D49","R71","U7","L72")) '((1,1) ("U62","R66","U55","R34","D71","R55","D58","R83")))))
  (is (= 135 (closest-intersection '(1,1) '((1,1) ("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51")) '((1,1) ("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7")))))
)  

(with-junit-output
  (clojure.test/test-vars [#'day3-1/manhattan-distance]))


(with-junit-output
  (clojure.test/test-vars [#'day3-1/closest-intersection]))

(with-junit-output
    (run-tests 'day3-1))


(defn read-input "Reads input from file" [filename delimiter str-converter]
  (map str-converter (clojure.string/split (slurp filename) delimiter)))

(def input (vec (read-input "/mnt/massData/dev/adventOfCode2019/inputs/day3-input.txt" #"\n" identity)))

(require '[clojure.string :as str])
(defn input-directions [line-number] (cons '(1,1) (list (str/split (input line-number) #","))))

(closest-intersection '(1,1) (input-directions 0) (input-directions 1))
