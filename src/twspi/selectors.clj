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

(ns twspi.selectors
  "Selectors and constructor functions (which are only there for readability)
  built from CARs, CDRs and CONSes."
  (:refer-clojure :exclude [assoc])
  (:require
    [twspi.primitives :refer :all]))

(defn lvl
  "selector: extract level from molec"
  [x]
  (if (seq? x)
    (car x)))

(defn xpr
  "selector: extract prolog expression"
  [x]
  (if (seq? x)
    (cadr x)))

(defn molec
  "constructor: a `molec` is a tuple of (level,prolog_exp), where level
  is used to discriminate variables at different levels in the proof tree."
  [x y]
  (list x y))

; Clojure doesn't properly implement the semantics of cons-cells
; so just use a list. The knock-on effect is that bond must
; call cadr rather than cdr
(defn bind [x y e] (cons (list x y) e))
(defn bond [x e] (cadr (or (assoc x e) '(nil))))
(defn but-first-goal [x] (cons (cdar x) (cdr x)))
