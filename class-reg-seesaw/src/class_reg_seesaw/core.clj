(ns class-reg-seesaw.core
  (:use seesaw.core)
  (:use seesaw.dev)
  (:require [clojure.java.io :as io]
            [seesaw.font :as _f]))

(defn make-content []
  (vertical-panel
   :items [(label :text "Class-Reg" :font (_f/font :size 24) :h-text-position :center)
           "Welcome to my class registration application"]))

(defn main-frame []
  (let [frame (frame :title "U Class-Reg-Clj"
                    :minimum-size [600 :by 800]
                    :on-close :exit)
       content (make-content)]
   (config! frame :content content)
   frame))

(defn -main [& args]
  (invoke-later
   (-> (main-frame)
       pack!
       show!)))