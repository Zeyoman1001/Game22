package model.collectibles;


import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Supply implements Collectible {

    //-------------------------------Constructor------------------------------
    public Supply() {

    }

    public void pickUp(Hero h) {

        h.getSupplyInventory().add(this);
    }

    public void use(Hero h) throws NoAvailableResourcesException {
        if (h.getSupplyInventory().size() == 0) {
            throw new NoAvailableResourcesException("No Supplyies Available");
        }
        h.getSupplyInventory().remove(this);
    }

}
