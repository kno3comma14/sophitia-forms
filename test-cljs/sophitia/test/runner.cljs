(ns sophitia.test.runner
  (:require [cljs.test :refer-macros [run-all-tests]]))

(defn run []
  (run-all-tests #"sophitia.test.*"))

