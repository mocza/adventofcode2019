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
  (defn intersections [line1 line2] "Returns the points where two lines crosses except the start point if both lines start from same point"
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
  (defn manhattan-distance [point1 point2] 
    (let [[x1 y1] point1 [x2 y2] point2] (+ (java.lang.Math/abs (- x2 x1)) (java.lang.Math/abs (- y2 y1))))
    )
  (is (= 1 (manhattan-distance '(0,0) '(1,0))))
  (is (= 6 (manhattan-distance '(1,1) '(4,4))))
  )

(with-test
  (defn closest-intersection [from line1 line2]
    (let [cross-points (intersections line1 line2)
          cross-point-distances (map #(manhattan-distance from %) cross-points)] 
      (apply min cross-point-distances)
      )
    )
  (is (= 6 (closest-intersection '(1,1) '((1,1) ("R8","U5","L5","D3")) '((1,1) ("U7","R6","D4","L4")))))  
  (is (= 159 (closest-intersection '(1,1) '((1,1) ("R75","D30","R83","U83","L12","D49","R71","U7","L72")) '((1,1) ("U62","R66","U55","R34","D71","R55","D58","R83")))))
  (is (= 135 (closest-intersection '(1,1) '((1,1) ("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51")) '((1,1) ("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7")))))
  )

(with-junit-output
  (clojure.test/test-vars [#'day3-1/manhattan-distance]))

(with-junit-output
    (run-tests 'day3-1))


(defn read-input "Reads input from file" [filename delimiter str-converter]
  (map str-converter (clojure.string/split (slurp filename) delimiter)))

(def input (vec (read-input "/mnt/massData/dev/adventOfCode2019/inputs/day3-input.txt" #"\n" identity)))

(require '[clojure.string :as str])
(defn input-directions [line-number] (cons '(1,1) (list (str/split (input line-number) #","))))

(closest-intersection '(1,1) (input-directions 0) (input-directions 1))
