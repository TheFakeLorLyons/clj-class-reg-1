(ns class-reg-seesaw.core
  (:use seesaw.core)
  (:use seesaw.dev)
  (:require [clojure.java.io :as _io]
            [seesaw.font :as _f]))

(defn make-content []
  (border-panel
   :north (label :text "Class-Reg" :font (_f/font :size 24) :h-text-position :center)
   :center "Login Here"))

(defn main-frame []
  (let [frame (frame
               :id :m-frame
               :title "U Class-Reg-Clj"
               :content (make-content)
               :minimum-size [600 :by 800]
               :on-close :exit)
        background-image (label :icon (_io/resource "field.jpg"))]
   ;(config! :m-frame :position )
   frame))

(defn -main [& args]
  (invoke-later
   (-> (main-frame)
       pack!
       show!)))