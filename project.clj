(defproject hello-java "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.12.0-alpha11"]
                 [exoscale/interceptor "0.1.12"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :javac-options ["-source" "21" "-target" "21" "--enable-preview"]
  :repl-options {:init-ns hello-java.core})
