(ns class-reg-seesaw.navigation
  (:require [class-reg-seesaw.user-home :as home]
            [class-reg-seesaw.core :as login]))

(defn update-frame [page-id] 
  (if (= :home page-id)
    (home/load-page)
    (login/make-content)))