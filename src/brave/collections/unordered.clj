(ns brave.collections.unordered)

(defn next-round
  [game]
  (update-in game [:round] inc))

(defn add-score
  [game player amount]
  (update-in game [:scores player] (fnil + 0) amount))

(defn find-player
  [game player-name]
  (first (filter
           #(= player-name (:name %))
           (:players game))))
