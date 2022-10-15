(ns interceptors
  (:require [exoscale.interceptor :as interceptor]))

(def odds {:name  ::odds
           :enter (fn [ctx] (assoc ctx :msg "I handle odd number"))})

(def evens {:name  ::evens
            :enter (fn [ctx] (assoc ctx :msg "Even numbers are my bag"))})

(def chooser
  {:name  ::chooser
   :enter (fn [ctx]
            (let [n (:n ctx)
                  nxt (if (even? n) evens odds)]
              (interceptor/enqueue ctx [nxt])))})


(interceptor/execute {:n 1} [chooser])

(def interceptor-A {:name  :A
                    :enter (fn [ctx] (update ctx :a inc))
                    :leave (fn [ctx] (assoc ctx :foo :bar))
                    :error (fn [ctx err] ctx)})

(def interceptor-B {:name  :B
                    :enter (fn [ctx] (update ctx :b #(Integer/parseInt %)))
                    :error (fn [ctx err]
                             (prn err)
                             (if (instance? java.lang.NumberFormatException err)
                               (assoc ctx :msg ":b isn't a number!")
                               (assoc ctx ::interceptor/error err)))})

(def interceptor-C {:name  :C
                    :enter (fn [ctx] (update ctx :c inc))})

;; enter & leave
(interceptor/execute {:a 0 :b "0" :c 0}
                     [interceptor-A
                      interceptor-B
                      interceptor-C])
;=>
{:a                          1,
 :b                          0,
 :c                          1,
 :foo                        :bar,
 :exoscale.interceptor/queue #object[clojure.lang.PersistentQueue 0x16c3151a "clojure.lang.PersistentQueue@1"],
 :exoscale.interceptor/stack ()}

;; handle error
; The :error function is a bit special.
; If an interceptor throws an exception, then Pedestal starts looking for an interceptor with an :error function to handle it.
; This goes from right-to-left like the :leave functions.
; The main difference is that an error-handling interceptor may indicate that the error is totally resolved and Pedestal will resume looking for :leave functions.

; in this case:
; interceptor-C is not executed
; :error on intercepter-B is executed & it can handle error & return context map. So next :leave on interceptor-A is call
(interceptor/execute {:a 0 :b 0 :c 0}
                     [interceptor-A
                      interceptor-B
                      interceptor-C])
"Not a number!"
;=>
{:a                          1,
 :b                          "x",
 :d                          0,
 :foo                        :bar,
 :exoscale.interceptor/queue #object[clojure.lang.PersistentQueue 0x7d8d8ee1 "clojure.lang.PersistentQueue@8b0e615e"],
 :exoscale.interceptor/stack ()}

; with interceptor-B2:
; interceptor-C is not executed
; :error on intercepter-B is executed & add ::error to context map. So next :error on interceptor-A is call & :leave is not run
(def interceptor-B2 {:name  :B
                     :enter (fn [ctx] (update ctx :b #(Integer/parseInt %)))
                     :error (fn [ctx err]
                              (assoc ctx ::interceptor/error err))})

(interceptor/execute {:a 0 :b "x" :d 0}
                     [interceptor-A
                      interceptor-B2
                      interceptor-C])
{:a                          1,
 :b                          "x",
 :d                          0,
 :exoscale.interceptor/queue #object[clojure.lang.PersistentQueue 0x510a0405 "clojure.lang.PersistentQueue@8b0e615e"],
 :exoscale.interceptor/stack ()}

;; dynamic

;; async
