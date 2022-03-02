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

(run-tests)