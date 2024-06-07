(ns class-reg-seesaw.frame
  (:require [seesaw.core :as seesaw]))

(def main-frame
  (let [home-frame (seesaw/frame
                    :id :m-frame
                    :title "U Class-Reg-Clj"
                    #_#_:content (make-content)
                    :minimum-size [800 :by 600]
                    :on-close :exit)]
    home-frame))
