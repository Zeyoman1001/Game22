package model.world;

import model.collectibles.Collectible;

public class CollectibleCell extends Cell {
    // ---------------------------Attributes-------------------------------------
    private Collectible collectible;

    // ---------------------------constructor------------------------------------
    public CollectibleCell(Collectible collectible) {
        this.collectible = collectible;
    }

    // -------------------------getters and setters--------------------------------
    public Collectible getCollectible() {
        return collectible;
    }

}
