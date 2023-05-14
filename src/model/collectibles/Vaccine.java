package model.collectibles;

import engine.Game;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;

public class Vaccine implements Collectible {

    //-------------------------------Constructor------------------------------
    public Vaccine() {

    }

    public void pickUp(Hero h) {
        h.getVaccineInventory().add(this);
    }

    public void use(Hero h) throws NoAvailableResourcesException {
        if (h.getVaccineInventory().size() == 0) {
            throw new NoAvailableResourcesException("No Vaccines Available");
        }
        h.getVaccineInventory().remove(this);
        Hero newHero = Game.availableHeroes.get(0);
        Game.availableHeroes.remove(newHero);
        Game.heroes.add(newHero);
        newHero.setLocation(h.getTarget().getLocation());
        Game.zombies.remove(h.getTarget());
        Game.map[h.getTarget().getLocation().x][h.getTarget().getLocation().y]= new CharacterCell(newHero);
        h.setActionsAvailable(h.getActionsAvailable() - 1);

    }

}
