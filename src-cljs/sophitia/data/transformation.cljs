(ns sophitia.data.transformation
  (:require [malli.transform :as mt])) ;; Implement the transformation using malli.transform

(defn create-field-title
  [field]
  [:label (:name field)])

(defn create-field-error
  [field]
  (let [error-text (or (:error-message field) (:default-error-mesage field))]
    [:label error-text]))

(defn collect-html-attrs
  [field]
  {:id (:id field)
   :class (:class field)
   :type (:type field)})

(defn create-input
  [field]
  (let [attrs (collect-html-attrs field)]
    [:input attrs]))

(defn s-field->reagent-field
  [field]
  [:div
   (create-field-title field)
   (create-input field)
   (create-field-error field)])

(defn s-form->reagent-form
  [sophitia-form]
  (reduce (fn [acc field] (conj acc (s-field->reagent-field field)))
          [:div]
          (:fields sophitia-form)))