(ns sophitia.test.data.error-collector-test
  (:require [cljs.test :refer-macros [deftest is run-tests testing]]
            [sophitia.data.error-collector :as ec]))

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
   :schema [:map 
            [:email [:string]]
            [:password [:string]]]
   :validation-group-order ["g1" "g2"]})

(deftest extract-errors-test
  (testing "extract-errors function is working properly"
    (let [sophitia-form valid-malli-form
          target {:email "" :password nil}
          actual-errors (ec/extract-errors sophitia-form target)
          expected-errors {:password ["should be a string"]}]
      (is (= actual-errors expected-errors)))))

(run-tests)