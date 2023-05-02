(ns foo.core)

;; STATE
(defonce state (atom 0))

;; HELPERS
(defn root []
  (.getElementById js/document "app"))

(defn counter []
  (.getElementById js/document "counter"))

(defn replaceChild [root new-child old-child]
  (.replaceChild root new-child old-child))

;; COMPONENTS
(defn new-counter []
  (doto (.createElement js/document "p")
    (.setAttribute "id" "counter")
    (aset "innerHTML" @state)))

(defn new-button-inc []
  (doto (.createElement js/document "button")
    (aset "innerHTML" "UNASSSS AINASSS")
    (aset "onclick" (fn [_event]
                      (swap! state inc)
                      (replaceChild (root) (new-counter) (counter))))))

(defn new-button-reset []
  (doto (.createElement js/document "button")
    (aset "innerHTML" "reset")
    (aset "onclick" (fn [_event]
                      (reset! state 0)
                      (replaceChild (root) (new-counter) (counter))))))

(defn new-label []
  (doto (.createElement js/document "label")
    (aset "innerHTML" (js/Date.))))

(comment

  (macroexpand-1 (doto (.createElement js/document "label")
    (aset "innerHTML" (js/Date.))))
  ,)

;; RENDER
(defn render [root & components]
  (doseq [c components]
    (.append root c)))

(defn ^:export init
  []
  (aset (root) "innerHTML" "")
  (render (root) (new-counter) (new-button-inc) (new-button-reset) (new-label)))

(comment
  (+ 1 1)
  (reset! state 42)
(init)
  )
