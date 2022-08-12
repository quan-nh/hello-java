(ns future-promise-delay)

;; future
; invoke the body in another thread,
; and will cache the result and return it on all subsequent calls to deref/@.
; If the computation has not yet finished, calls to deref/@ will block
(def f (future (Thread/sleep 10000) (println "done") 100))

(prn @f)


;; promise
; Create a promise
(def p (promise)) ; p is our promise

; Check if was delivered/realized
(realized? p)
false ; No yet

; Delivering the promise
(deliver p 42)
#<core$promise$reify__5727@47122d: 42>

; Check again if it was delivered
(realized? p)
true ; Yes!

; Deref to see what has been delivered
@p
42


;; delay
; invoke the body only the first time it is forced (with force or deref/@),
; and will cache the result
(def my-delay (delay (println "did some work") 100))
#'user/my-delay

@my-delay
;did some work
100

@my-delay
100