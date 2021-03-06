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

(ns ambages.interpreter-test
  (:refer-clojure :exclude [var?])
  (:require
    [clojure.test :refer :all]
    [ambages.selectors :refer [bind xpr molec]]
    [ambages.interpreter :refer :all]))

(deftest check-var?
  (is (true? (var? '?u)))
  (is (false? (var? '?g)))
  (is (false? (var? nil)))
  (is (false? (var? '(?a))))
  (is (true? (var? (xpr (molec 3 '?a))))))

(deftest check-lookup
  (let [p (molec 3 '?a)
        x (molec 0 '?x)
        y (molec 0 '?y)
        z (molec 1 '?z)
        env (->>
              []
              (bind x 3)
              (bind y 4)
              (bind z y))]
    (is (nil? (lookup nil env)))
    (is (nil? (lookup nil nil)))
    (is (= p (lookup p nil)))
    (is (= p (lookup p env)))
    (is (= 3 (lookup x env)))
    (is (= 4 (lookup y env)))))

(deftest check-unify
  (is (= '(((17 ?x) (4711 b))   ; TODO Are these the right way round?
           ((4711 ?y) (17 a))   ; Order shouldn't matter, actually
           ((3 ?z) (2 c))
           (bottom-of-env))
         (unify
          '(17 (a ?x))
          '(4711 (?y b))
          '(((3 ?z) (2 c))
            (bottom-of-env))))))


