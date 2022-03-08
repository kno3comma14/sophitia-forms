(ns sophitia.data.error-collector
  (:require [malli.core :as m]
            [malli.error :as me]))

(defn extract-errors
  [sophitia-form target]
  (let [schema (:schema sophitia-form)
        malli-errors (when (not (m/validate schema target))
                      (m/explain schema target))]
    (me/humanize malli-errors)))