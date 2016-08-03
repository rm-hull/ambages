# Ambages [am-BEY-jeez] noun, Archaic. _pl (plural only)_

> 1. Indirect or roundabount way of talking; circumlocution.
> 2. Indirect or roundabout routes or directions.

[![Build Status](https://travis-ci.org/rm-hull/ambages.svg?branch=master)](http://travis-ci.org/rm-hull/ambages) [![Coverage Status](https://coveralls.io/repos/rm-hull/ambages/badge.svg?branch=master)](https://coveralls.io/r/rm-hull/ambages?branch=master) [![Dependencies Status](https://jarkeeper.com/rm-hull/ambages/status.svg)](https://jarkeeper.com/rm-hull/ambages) [![Downloads](https://jarkeeper.com/rm-hull/ambages/downloads.svg)](https://jarkeeper.com/rm-hull/ambages) [![Clojars Project](https://img.shields.io/clojars/v/rm-hull/ambages.svg)](https://clojars.org/rm-hull/ambages)

A small prolog interpreter, based on a paper by **M. Nilsson**, _Uppsala University_,
where the aim is to faithfully reproduce (at least initially) the same demonstrable
functionality in Clojure.

> ### The world's shortest Prolog interpreter?
> **M. Nilsson**, _Uppsala University_
>
> The aim of this article is to describe a small structure-sharing Prolog interpreter
> which is implemented in LISP. It is easy to type in, fairly readable, and efficient
> enough to execute programs which are small and simple, yet not totally trivial.
>
> #### IMPLEMENTATION
> Admittedly, the title of this paper is a bit provocative. In fact, the interpreter
> can be made even smaller by replacing the selector and constuctor functions
> (which are there only for readability) with their respective CARs, CDRs, and
> CONSes, etc.
>
> To improve on the time/memory efficiency, the best suggestion is to optimise
> the variable binding scheme. In the version presented, linear list search is used
> for finding bindings of variables. The interpreter can be sped up by a factor of
> about 100 if instead some indexed data structure like an array is used (Nilsson,
> 1993). Unfortunately, in LISP such structures are often very implementation-dependent,
> so they have been avoided here.
>
> The interpreter is written in MACLISP dialect (Moon, 1974). Only the most basic
> functions are used, with the exceptions of LET and LOOP. LET is a convenient
> way of writing LAMBDAs...

The full text of the article can be found in the [included PDF](https://github.com/rm-hull/ambages/blob/master/doc/twspi.pdf).

### Pre-requisites

You will need [Leiningen](https://github.com/technomancy/leiningen) 2.6.1 or above installed.

### Building

To build and install the library locally, run:

    $ cd ambages
    $ lein test
    $ lein install

### Including in your project

There _will be_ a version hosted at [Clojars](https://clojars.org/rm-hull/ambages).
For leiningen include a dependency:

```clojure
[rm-hull/ambages "0.1.0"]
```

For maven-based projects, add the following to your `pom.xml`:

```xml
<dependency>
  <groupId>rm-hull</groupId>
  <artifactId>ambages</artifactId>
  <version>0.1.0</version>
</dependency>
```

## API Documentation

See [www.destructuring-bind.org/ambages](http://www.destructuring-bind.org/ambages/) for API details.

## Basic Usage

> TODO

## References

* http://dl.acm.org/citation.cfm?id=537885#
* http://wiki.call-cc.org/eggref/4/tiny-prolog
* http://www.lurklurk.org/clj_repl/clj_repl.html
* http://strlen.com/bla/prolog.bla
* http://cs.stackexchange.com/questions/6618/how-to-implement-a-prolog-interpreter-in-a-purely-functional-language

## License

The MIT License (MIT)

Copyright (c) 2016 Richard Hull

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
