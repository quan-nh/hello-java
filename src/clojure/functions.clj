(ns functions)

;; Variadic functions

; The beginning of the variable parameters is marked with &
; a variable number of parameters (0 or more) that will be collected in a list
(defn test [& opts]
  (prn opts))

(test "hello" "world")
;=> ("hello" "world")

; from clojure 1.11 it supports keyword arguments
(defn test2 [& {:keys [a b] :as opts}]
  (prn a b opts))

(test2 :a 1)
;=> 1 nil {:a 1}

(test2 {:a 1 :b 2})
;=> 1 2 {:a 1, :b 2}
