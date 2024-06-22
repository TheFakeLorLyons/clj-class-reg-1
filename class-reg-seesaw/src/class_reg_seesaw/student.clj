(ns class-reg-seesaw.student)

(def sample-user{:id 000
                 :name "Lorelai"
                 :major "Computer Science"
                 :gpa 4.0
                 :schedule []})

(defn create-user [db new-user]
  (swap! db update-in [0 :students] conj new-user))

(println 
  (create-user (atom [{:students []}]) sample-user))