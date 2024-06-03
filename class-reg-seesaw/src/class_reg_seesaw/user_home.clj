(ns class-reg-seesaw.user-home
  (:use seesaw.core)
  (:use seesaw.dev)
  (:require [clojure.java.io :as io]
            [seesaw.font :as f]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;                Routing              ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn logout [frame]
  (seesaw/content! frame (login-page/-main)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;               Resources             ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def background-image (io/resource "field.jpg"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;                   UI                ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn to-be-determined []
  (let [login-form
        (vertical-panel
         :items [(button :text "Log out?"
                         :id :submit-button
                         :listen [:action #(logout frame)])
                 (button :text "I don't do anything"
                         :id :create-account-button)]
         :background :goldenrod)]
    login-form))

(defn user-home-container []
  (let [conjoined-panel
        (border-panel
         :vgap 20
         :north (label "This is the user home page.")
         :center (to-be-determined)
         :background :gray)]
    conjoined-panel))

(defn load-page []
  (let [content-panel
        (flow-panel
         :items [(user-home-container)]
         :background :purple)]
    content-panel))

(defn user-home-main-frame[]
  (let [frame (frame
             :id :m-frame
             :title "U Class-Reg-Clj"
             :content (load-page)
             :minimum-size [800 :by 600]
             :on-close :exit)]
  frame) )

(defn user-home-page []
  (let [frame (frame
               :id :m-frame
               :title "U Class-Reg-Clj"
               :content (user-home-main-frame)
               :minimum-size [800 :by 600]
               :on-close :exit)]
    frame))