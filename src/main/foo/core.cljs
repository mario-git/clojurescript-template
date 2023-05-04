(ns foo.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

;; STATE

(defonce state (r/atom 0))

;; COMPONENTS

(defn counter []
  [:p "Kounter: " @state])

(defn button-inc []
  [:input {:type "button"
           :value "+1"
           :on-click (fn [_event]
                       (swap! state inc))}])

(defn button-reset []
  [:input {:type "button"
           :value "reset"
           :on-click (fn [_event]
                       (reset! state 0))}])

(defn container []
  [:div
   [counter]
   [button-inc]
   [button-reset]])

;; RENDER

(defn ^:export init []
  (rdom/render [container] (.getElementById js/document "app")))
