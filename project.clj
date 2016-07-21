
(defproject rm-hull/twspi "0.1.0"
  :description "A small prolog interpreter implemented in Clojure"
  :url "https://github.com/rm-hull/twspi"
  :license {
    :name "The MIT License (MIT)"
    :url "http://opensource.org/licenses/MIT"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]]
  :scm {:url "git@github.com:rm-hull/twspi.git"}
  :source-paths ["src"]
  :jar-exclusions [#"(?:^|/).git"]
  :codox {
    :source-paths ["src"]
    :output-path "doc/api"
    :source-uri "http://github.com/rm-hull/twspi/blob/master/{filepath}#L{line}"  }
  :min-lein-version "2.6.1"
  :profiles {
    :dev {
      :global-vars {*warn-on-reflection* true}
      :plugins [
        [lein-codox "0.9.5"]
        [lein-cloverage "1.0.6"]]}})