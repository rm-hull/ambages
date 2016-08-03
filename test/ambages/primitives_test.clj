;; The MIT License (MIT)
;;
;; Copyright (c) 2016 Richard Hull
;;
;; Permission is hereby granted, free of charge, to any person obtaining a copy
;; of this software and associated documentation files (the "Software"), to deal
;; in the Software without restriction, including without limitation the rights
;; to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;; copies of the Software, and to permit persons to whom the Software is
;; furnished to do so, subject to the following conditions:
;;
;; The above copyright notice and this permission notice shall be included in all
;; copies or substantial portions of the Software.
;;
;; THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;; IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;; FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;; AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;; LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;; OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;; SOFTWARE.

(ns ambages.primitives-test
  (:refer-clojure :exclude [assoc])
  (:require
    [clojure.test :refer :all]
    [ambages.primitives :refer :all]))

(def test-data (list 1 2 3 4 5))

(deftest check-primitives
  (is (nil? (car nil)))
  (is (nil? (cadr nil)))
  (is (nil? (cdar nil)))
  (is (nil? (next nil)))
  (is (= 1 (car test-data)))
  (is (= 2 (cadr test-data)))
  (is (= (list 2) (cdar (list [1 2]))))
  (is (= (list 2 3 4 5) (cdr test-data))))

(deftest check-member?
  (is (true? (member? 3 test-data)))
  (is (false? (member? 11 test-data)))
  (is (false? (member? 4 nil))))

(deftest check-assoc
  (let [lst [[:a 1] [:b 2] [:c 3] [:c 11]]]
    (is (= [:b 2] (assoc :b lst)))
    (is (= [:c 3] (assoc :c lst)))
    (is (nil? (assoc :d lst)))))

(deftest check-setq
  (let [x 5]
    (setq x 19)
    (is (= 5 x)))
  (is (= 19 x)))

(deftest check-atom?
  (is (true? (atom? 3)))
  (is (true? (atom? :fred)))
  (is (true? (atom? 'fred)))
  (is (false? (atom? nil)))
  (is (false? (atom? [1 2 3])))
  (is (false? (atom? (cons 1 (cons 2 nil))))))
