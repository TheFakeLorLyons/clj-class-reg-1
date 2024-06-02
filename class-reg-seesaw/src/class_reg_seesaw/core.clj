(ns class-reg-seesaw.core
  (:use seesaw.core)
  (:use seesaw.dev)
  (:require [clojure.java.io :as _io]
            [seesaw.font :as _f]))

(defn login-screen-header []
  (let [header-content   
        (vertical-panel 
         :items [(label :text "Class-Reg"
                        :font (_f/font :size 24)
                        :h-text-position :center)
                 (label :text "Welcome to the app"
                        :font (_f/font :size 18)
                        :h-text-position :center)])]
    header-content))

(defn login-screen-form []
  (let [login-form
        (vertical-panel
         :items [(label :text "Login Here"
                        :h-text-position :center)])]
    login-form))

(defn main-screen-contains []
  (let [conjoined-panel
        (border-panel
         :north (login-screen-header)
         :center (login-screen-form))]
    conjoined-panel))

(defn make-content []
  (let [content-panel
        (flow-panel
         :items [(main-screen-contains)])]
    content-panel))

(defn main-frame []
  (let [frame (frame
               :id :m-frame
               :title "U Class-Reg-Clj"
               :content (make-content)
               :minimum-size [600 :by 800]
               :on-close :exit)
        background-image (label :icon (_io/resource "field.jpg"))] 
   frame))

(defn -main [& args]
  (invoke-later
   (-> (main-frame)
       pack!
       show!)))