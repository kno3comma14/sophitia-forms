(ns sophitia.test.data.validation-test
  (:require [cljs.test :refer-macros [deftest is run-tests testing]]
            [sophitia.data.validation :as v]))

(def valid-malli-field1
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
  {:fields [valid-malli-field1 valid-malli-field2]
   :validation-behavior "on-change"
   :schema [[:map []]]
   :validation-group-order ["g1" "g2"]})


(deftest validate-internal-form-schema-test
  (testing "Invalid schema for an empty map"
    (is (= false (v/validate-internal-form-schema {}))))
  (testing "Valid basic form schema"
    (is (= true (v/validate-internal-form-schema valid-malli-form)))))

(run-tests)


