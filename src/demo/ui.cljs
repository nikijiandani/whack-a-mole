(ns demo.ui
 (:require
    [reagent.dom :as rdom]
    [reagent.core :as r]))

;; All atoms can be swapped and reset
;; You can also listen to changes on an atom
(defonce state (r/atom {:x 0 :y 0 :points 0 :moves 0}))
(defonce interval 
  (js/setInterval
    (fn [] (swap! state assoc :x (rand-int 200) :y (rand-int 200) :moves (inc (:moves @state))))
    #_(fn [] (reset! state {:x (rand-int 200)
                          :y (rand-int 200)
                          :points (:points @state)}))
    750))

;; Hamster moving rather than teleporting
;; Multiple hamsters?
;; Introduce bombs?

;; Use #_ to comment out code iso semicolons

(defn app-view []
  [:div
    [:div
     {:style {:position "absolute"
              :left (:x @state)
              :top (:y @state)}
      :on-click (fn [] (swap! state update :points inc))
      #_#_:on-click (fn [] (swap! state (fn [state] (update state :points inc))))
      #_#_:on-click (fn [] (reset! state (update @state :points inc)))
      #_#_:on-click (fn [] (reset! state (update @state :points (fn [points] (inc points)))))
      #_#_:on-click (fn [] (reset! state (assoc @state :points (inc (:points @state)))))
      #_#_:on-click (fn [] (reset! state {:x (:x @state) :y (:y @state) :points (inc (:points @state))}))}
     "🐹"]
   [:div
     "Score:" (:points @state) "/" (:moves @state)
   ]
  ])

(defn render! []
  (rdom/render
    [app-view]
    (js/document.getElementById "app")))