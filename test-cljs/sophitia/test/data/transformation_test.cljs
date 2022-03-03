(ns sophitia.test.data.transformation
  (:require [cljs.test :refer-macros [deftest is run-tests testing]]
            [sophitia.data.transformation :as t]))

(def valid-malli-field
  {:id "id1"
   :name "name1"
   :class "class1"
   :description "first class"
   :error-message nil
   :default-error-mesage "Field not valid."
   :type "text"
   :validation-group "g1"})

(def valid-malli-field2
  {:id "id2"
   :name "name2"
   :class "class2"
   :description "Second class"
   :error-message nil
   :default-error-mesage "Field not valid."
   :type "text"
   :validation-group "g2"})

(def valid-malli-form
  {:fields [valid-malli-field valid-malli-field2]
   :validation-behavior "on-change"
   :schema [[:map []]]
   :validation-group-order ["g1" "g2"]})

(deftest create-field-title-test
  (testing "create-field-title is working properly with a valid field"
    (let [input-field valid-malli-field
          actual-title (t/create-field-title input-field)
          expected-title [:label "name1"]]
      (is (= actual-title expected-title)))))

(deftest create-field-error-test
  (testing "create-field-error is working properly with a valid field"
    (let [input-field valid-malli-field
          actual-error (t/create-field-error input-field)
          expected-error [:label "Field not valid."]]
      (is (= actual-error expected-error)))))

(deftest collect-html-attrs-test
  (testing "collect-html-attrs is working properly with a valid field"
    (let [input-field valid-malli-field
          actual-attrs (t/collect-html-attrs input-field)
          expected-attrs {:id "id1", :class "class1", :type "text"}]
      (is (= actual-attrs expected-attrs)))))

(deftest create-input-test
  (testing "collect-html-attrs is working properly with a valid field"
    (let [input-field valid-malli-field
          actual-input (t/create-input input-field)
          expected-input [:input {:id "id1", :class "class1", :type "text"}]]
      (is (= actual-input expected-input)))))

(deftest s-field->reagent-field-test
  (testing "s-field->reagent-field is working properly with a valid field"
    (let [input-field valid-malli-field
          actual-reagent-field (t/s-field->reagent-field input-field)
          expected-reagent-field [:div [:label "name1"]
                                  [:input {:id "id1", :class "class1", :type "text"}]
                                  [:label "Field not valid."]]]
      (is (= actual-reagent-field expected-reagent-field)))))

(deftest s-form->reagent-form-test
  (testing "s-form->reagent-form is working properly with a valid field"
    (let [sophitia-form valid-malli-form
          actual-reagent-form (t/s-form->reagent-form sophitia-form)
          expected-reagent-form [:div
                                 [:div
                                  [:label "name1"]
                                  [:input {:id "id1", :class "class1", :type "text"}]
                                  [:label "Field not valid."]]
                                 [:div
                                  [:label "name2"]
                                  [:input {:id "id2", :class "class2", :type "text"}]
                                  [:label "Field not valid."]]]]
      (is (= actual-reagent-form expected-reagent-form)))))

(run-tests)
