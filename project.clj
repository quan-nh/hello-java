(defproject hello-java "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :javac-options ["-source" "19" "-target" "19" "--enable-preview"]
  :repl-options {:init-ns hello-java.core})
