# TWSPI
[![Build Status](https://travis-ci.org/rm-hull/twspi.svg?branch=master)](http://travis-ci.org/rm-hull/twspi) [![Coverage Status](https://coveralls.io/repos/rm-hull/twspi/badge.svg?branch=master)](https://coveralls.io/r/rm-hull/twspi?branch=master) [![Dependencies Status](https://jarkeeper.com/rm-hull/twspi/status.svg)](https://jarkeeper.com/rm-hull/twspi) [![Downloads](https://jarkeeper.com/rm-hull/twspi/downloads.svg)](https://jarkeeper.com/rm-hull/twspi) [![Clojars Project](https://img.shields.io/clojars/v/rm-hull/twspi.svg)](https://clojars.org/rm-hull/twspi)

A small prolog interpreter, implemented in Clojure.

> TODO: introduction

### Pre-requisites

You will need [Leiningen](https://github.com/technomancy/leiningen) 2.6.1 or above installed.

### Building

To build and install the library locally, run:

    $ cd clustering
    $ lein test
    $ lein install

### Including in your project

There _will be_ a version hosted at [Clojars](https://clojars.org/rm-hull/twspi).
For leiningen include a dependency:

```clojure
[rm-hull/twspi "0.1.0"]
```

For maven-based projects, add the following to your `pom.xml`:

```xml
<dependency>
  <groupId>rm-hull</groupId>
  <artifactId>twspi</artifactId>
  <version>0.1.0</version>
</dependency>
```

## API Documentation

See [www.destructuring-bind.org/twspi](http://www.destructuring-bind.org/twspi/) for API details.

## Basic Usage

> TODO

## References

* http://dl.acm.org/citation.cfm?id=537885#
* http://wiki.call-cc.org/eggref/4/tiny-prolog

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
