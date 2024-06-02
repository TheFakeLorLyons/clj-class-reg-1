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
                        :halign :center)
                 (horizontal-panel
                  :items [(label :text "Username:")
                          (text :id :username-field)])
                 (horizontal-panel
                  :items [(label :text "Password:")
                          (password :id :password-field)])
                 (button :text "Submit"
                  :id :submit-button
                  :listen [:action (fn [_]
                                     (let [username (value (text :id :username-field))
                                           password (value (password :id :password-field))]
                                       (println "Username:" username "Password:" password)))])]
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