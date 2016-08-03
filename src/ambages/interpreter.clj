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

(ns ambages.interpreter
  (:refer-clojure :exclude [assoc var?])
  (:require
    [ambages.primitives :refer :all]
    [ambages.selectors :refer :all]))

(defn var? [x]
  (member? x '(?a ?b ?c ?x ?y ?z ?u ?v ?w)))

(defn lookup
  "retrieves values through levels of variable bindings"
  [p env]
  (or
    (and
      p
      (var? (xpr p))
      (lookup (bond p env) env))
    p))

(defn unify
  "Unify is called with three arguments:

    (unify (level-x x) (level-y y) env)

  If x and y don't match, unify returns nil. Otherwise, it returns a
  new environment, which is the old one with possible new bindings
  pushed on top.

  The unification algorithm is a version of the algorithm described
  in detail in \"LOGLISP: Motivation, Design and Implementation\"
  by J.A.Robinson and E.E.Sibert (1984)."
  [x y env]
  (let [x (lookup x env)
        y (lookup y env)]

    (cond
      (= x y)
      env

      (and (nil? (xpr x)) (nil? (xpr y)))
      env

      (var? (xpr x))
      (bind x y env)

      (var? (xpr y))
      (bind y x env)

      (or (atom? (xpr x)) (atom? (xpr y)))
      (if (= (xpr x) (xpr y)) env)

      :else
      (let [env2 (unify
                   (molec (lvl x) (car (xpr x)))
                   (molec (lvl y) (car (xpr y)))
                   env)]
        (unify
          (molec (lvl x) (cdr (xpr x)))
          (molec (lvl y) (cdr (xpr y)))
          env2)))))
