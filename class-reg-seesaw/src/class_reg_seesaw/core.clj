(ns class-reg-seesaw.core
  (:use seesaw.core)
  (:use seesaw.dev)
  (:require [clojure.java.io :as io]
            [seesaw.font :as f]))

(def background-image (io/resource "field.jpg"))

(defn center-the-heading []
  (let [center-panel
        (border-panel
         :north (label :text "Class-Reg\n"
                       :font (f/font :size 24)
                       :halign :center)
         :center (label :text "Welcome to the app"
                        :font (f/font :size 18)
                        :halign :center)
         :background :cyan)]
    center-panel))

(defn login-screen-header []
  (let [header-content   
        (flow-panel 
         :items [(center-the-heading)]
         :background :green
         :align :center)]
    header-content))

(defn login-screen-form []
  (let [login-form
        (vertical-panel
         :items [(label :text "Login Here"
                        :halign :center)]
         :background :goldenrod)]
    login-form))

(defn main-screen-contains []
  (let [conjoined-panel
        (border-panel
         :vgap 20
         :north (login-screen-header)
         :center (login-screen-form)
         :background :gray)]
    conjoined-panel))

(defn make-content []
  (let [content-panel
        (flow-panel
         :items [(main-screen-contains)]
         :background :black)]
    content-panel))

(defn main-frame []
  (let [frame (frame
               :id :m-frame
               :title "U Class-Reg-Clj"
               :content (make-content)
               :minimum-size [800 :by 600]
               :on-close :exit)]
   frame))

(defn -main [& args]
  (invoke-later
   (-> (main-frame)
       pack!
       show!)))