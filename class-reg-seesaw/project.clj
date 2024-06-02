(defproject class-reg-seesaw "1.0.0-SNAPSHOT"
  :description "My first [hopefully nice] gui application."
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [seesaw "LATEST"]
                 [quil "4.3.1563"]]
  :main ^:skip-aot my-gui-project.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :java-source-paths ["src"])