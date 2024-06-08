(ns class-reg-seesaw.core
  (:use seesaw.core)
  (:use seesaw.dev)
  (:require [clojure.java.io :as io]
            [seesaw.font :as f]
            #_[class-reg-seesaw.frame :refer [main-frame]]
            #_[class-reg-seesaw.navigation :as navigation]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;               Resources             ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def background-image (io/resource "field.jpg"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;              UI for 'login'         ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(declare update-frame)
(declare make-content)
(declare main-frame)

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

(def main-frame
  (let [home-frame (frame
                    :id :m-frame
                    :title "U Class-Reg-Clj"
                    :content (make-content)
                    :minimum-size [800 :by 600]
                    :on-close :exit)]
    home-frame))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;              User-Home              ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn to-be-determined []
  (let [login-form
        (vertical-panel
         :items [(button :text "Log out?"
                         :id :submit-button
                         :listen [:action (fn [_] (config! main-frame :content (update-frame :login)))])
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


(defn update-frame [page-id]
  (if (= :home page-id)
    (load-page)
    (make-content)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
                                        ;                 Main                ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main [& args]
  (invoke-later
   (-> main-frame
       pack!
       show!)))


;todo
;change the function names to be more descriptive
;comment my code more clearly
;see if I can use def instead of defn
;connect home page to login page