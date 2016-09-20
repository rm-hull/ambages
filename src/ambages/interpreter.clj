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
  (println "unify X=" x "  Y=" y "  ENV=" env)
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


(declare ^:dynamic *top-level*)
(declare ^:dynamic *database*)

(defn seek
  "Seek tries to prove a [[goal]]. nlist and n control level
  information. The return value is either `nil` for failure,
  or an integer level, whicht may be used by a cut predicate.

  Together with `unify`, this is the interpreter core."
  [to-prove nlist env n]
  (println \newline)
  (println "seek")
  (println "  to-prove" to-prove)
  (println "  nlist   " nlist)
  (println "  env     " env)
  (println "  n       " n)
  (cond
    (empty? to-prove)
    (*top-level* env)

    (empty? (car to-prove))
    (seek (cdr to-prove) (cdr nlist) env n)

    ;(and (atom? (car to-prove)) (not (var? (car to-prove))))
    ;(apply (car to-prove) (list (cdr to-prove) nlist env n))

    :else
    (let [goalmolec (molec (car nlist) (car to-prove))
          rst       (but-first-goal to-prove)
          backtrk   (fn [[head & tail]]
                      (println "> bktrk: H=" head " T=" tail)
                      (println "> unify: X=" goalmolec " Y=" (molec n head) " ENV=" env)
                      (if-let [env2 (unify goalmolec (molec n head) env)]
                        (do
                          (println "ENV2=" env2)
                          (let [n (seek (cons tail rst) (cons n nlist) env2 (inc n))]
                            (println "< bktrk: n=" n)
                            n))
                        (println "< bktrk: n= nil")))]
      (->>
        *database*
        (map backtrk)
        (remove nil?)
        (drop-while (partial = n))
        first))))

(def ^:dynamic *top-level*
  (fn [env]
    (println "\n\n************* Environment **************\n" env
               "\n****************************************")
    0))

(def ^:dynamic *database*
  '(;((true))
    ;((= ?x ?x))
    ((father richard henry))
    ((father michael richard))
    ((grandfather ?x ?y) (father ?x ?z) (father ?z ?y))
    ;((call ?x) ?x)

    ))

(defmacro prove [goals]
  `(seek (list '~goals) '(0) '((bottom-of-env)) 1))

(macroexpand-1 '(prove (grandfather ?x ?y)))

(prove (grandfather ?x ?y))
(prove (father michael ?y))

(prove (grandfather ?x ?y))
(prove (father ?x ?y))
(prove (father richard henry))


