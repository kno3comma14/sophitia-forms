(ns sophitia.data.schema
  (:require [malli.core :as m]
            [goog.string :as gstring]
            [goog.string.format]))

(def MalliField
  [:map {:closed true}
   [:id  [:string {:min 1}]]
   [:name [:string {:min 1}]]
   [:class [:string {:min 1}]]
   [:description [:string]]
   [:error-message [:or :string :nil]]
   [:default-error-mesage [:or :string :nil]]
   [:type [:enum
           "text"
           "password"
           "checkbox"
           "email"
           "number"
           "file"
           "radio"
           "button"
           "color"
           "image"]]
   [:validation-group [:string]]])

(defn valid-malli-field?
  [field]
  (let [result {:validity (m/validate MalliField field)
                :error-message (gstring/format (or (:default-error-mesage field)
                                                   (:error-message field))
                                               (:name field))}]
    result))

(def MalliForm
  [:and
   [:map {:closed true}
    [:fields [:vector :any]]
    [:validation-behavior [:enum "on-change" "grouped" "on-submit"]]
    [:schema [:vector :any]]
    [:validation-group-order [:vector :any]]]
   [:fn (fn [{:keys [fields]}]
          (every? #(:validity (valid-malli-field? %)) fields))]])

(defn provide-malli-field-schema
  []
  MalliField)

(defn provide-malli-form-schema
  []
  MalliForm)

(def SpecField
  [])

(def SpecForm
  [])
