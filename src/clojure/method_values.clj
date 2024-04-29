(ns method-values)

(set! *warn-on-reflection* true)

;; static method
; old
(map #(Long/toBinaryString %) (range 8))
; new
(map Long/toBinaryString (range 8))


;; instance method
; old
(map #(.toUpperCase %) ["hi" "there"])
; new
(map ^[] String/.toUpperCase ["hi" "there"])
; Multiple matches for method toUpperCase in class java.lang.String
; use param-tags to specify -> prevent reflection
; without param-tags -> reflection in this case
(map String/.toUpperCase ["hi" "there"])


;; constructor
(map #(java.util.Date. %) [1707771694522 1707771780922])
(map ^[long] java.util.Date/new [1707771694522 1707771780922])


;; :param-tags metadata to specific which method in case overloaded methods.
; :param-tags metadata is a vector of zero or more tags
; Each tag corresponds to a parameter in the desired signature (arity should match the number of tags).
; Parameters with non-overloaded types can use the placeholder _ in lieu of the tag.
(^[] String/.toUpperCase "hi")
(^[java.util.Locale] String/.toUpperCase "hi" java.util.Locale/ENGLISH)