(ns sophitia.core
  (:require [sophitia.data.validation :as validation]
            [sophitia.data.transformation :as transformation]))

(defn generate-form
  [sophitia-form state] ;; TODO implement binding with the state
  (when (validation/validate-form-schema sophitia-form)
    (transformation/s-form->reagent-form sophitia-form)))

(defn validate-data
  [schema state]
  nil) ;; TODO implement the validation