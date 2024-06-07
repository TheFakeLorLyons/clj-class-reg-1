(ns class-reg-seesaw.core
  (:use seesaw.core)
  (:use seesaw.dev)
  (:require [clojure.java.io :as io]
            [seesaw.font :as f]
            [class-reg-seesaw.frame :refer [main-frame]]
            [class-reg-seesaw.navigation :refer [update-frame]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;               Resources             ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def background-image (io/resource "field.jpg"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;                   UI                ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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
                                              (println "Username:" username "Password:" password)))])
                 (button :text "Create Account" 
                         :id :create-account-button
                         :listen [:action (fn [_] (config! main-frame :content (update-frame :home)))])]
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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;                 Main                ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main [& args]
  (invoke-later 
   (-> main-frame
       (config! :content (make-content))
       pack!
       show!)))


;todo
;change the function names to be more descriptive
;comment my code more clearly
;see if I can use def instead of defn
;connect home page to login page