(ns day3-1)
(use 'clojure.test)
(use 'clojure.test.junit)

;(ns-unmap 'day3-1 'to-coordinates)

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

(with-junit-output
  (clojure.test/test-vars [#'day3-1/directions-to-coordinates]))


(with-test
   (defn closest-intersection [central-port, wire1, wire2]
     ;(let [wire1-coords (take 1 (first wire1))] ())
     (Integer. 3)
     )
   (is (= 1 (closest-intersection '(0, 0) '("R1") '("R1"))))  
  ;(is (= 3 (closest-intersection (1, 1) ("R8","U5","L5","D3") ("U7","R6","D4","L4"))))  
   )


(with-junit-output
    (run-tests 'day3-1))

;(closest-intersection '(1, 1) '("R8","U5","L5","D3") '("U7","R6","D4","L4")) 
