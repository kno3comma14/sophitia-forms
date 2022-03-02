(ns sophitia.data.validation
  (:require [sophitia.data.schema :as schema]
            [malli.core :as m]))

;; TODO Spec implementation.

(def SCHEMA-TYPE "MALLI") ;; This will change for a function to take SCHEMA-TYPE from configuration layer

(defn validate-form-schema
  [form-schema]
  (if (= SCHEMA-TYPE "MALLI")
    (m/validate (schema/provide-malli-form-schema) form-schema)
    false)) ;; This branch is reserved for spec validation
