(ns sophitia.test
  (:require [sophitia.test.runner :as runner]))

(def success 0)

 (defn ^:export run []
   (.log js/console "Tests started.")
   (runner/run)
   success)
