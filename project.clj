(defproject sophitia "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/clojurescript "1.10.597"
                  :exclusions [org.apache.ant/ant]]
                 [metosin/malli "0.8.3"]
                 [org.babashka/sci "0.3.1"]
                 [reagent-utils "0.3.4"]]
  :plugins [[lein-cljsbuild "1.1.8"]]
  :cljsbuild {:repl-listen-port 9000
              :repl-launch-commands
              {"firefox" ["firefox"
                          :stdout ".repl-firefox-out"
                          :stderr ".repl-firefox-err"]
               "firefox-naked" ["firefox"
                                "resources/private/html/naked.html"
                                :stdout ".repl-firefox-naked-out"
                                :stderr ".repl-firefox-naked-err"]
               "phantom" ["phantomjs"
                         "phantom/repl.js"
                         :stdout ".repl-phantom-out"
                         :stderr ".repl-phantom-err"]
               "phantom-naked" ["phantomjs"
                                "phantom/repl.js"
                                "resources/private/html/naked.html"
                                :stdout ".repl-phantom-naked-out"
                                :stderr ".repl-phantom-naked-err"]}
              :test-commands {"unit" ["phantomjs"
                                      "phantom/unit-test.js"
                                      "resources/private/html/unit-test.html"]}
              :builds {:dev {:source-paths ["src-cljs"]
                             :jar true
                             :compiler {:output-to "resources/public/js/main.js"
                                        :optimizations :advanced
                                        :pretty-print true}}
                       :prod {:source-paths ["src-cljs"]
                              :compiler {:output-to "resources/public/js/main.js"
                                         :optimizations :advanced
                                         :pretty-print true}}
                       :test {:source-paths ["src-cljs" "test-cljs"]
                              :compiler {:output-to "resources/private/js/unit-test.js"
                                         :optimizations :whitespace
                                         :pretty-print true}}}
              :clean-targets ^{:protect false} ["resources/private/js"
                                                "resources/public/js"
                                                :target-path]}
  :repl-options {:init-ns sophitia.core})
