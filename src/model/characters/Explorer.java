package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Explorer extends Hero {

    //--------------------------Constructor--------------------------------------------------
    public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }

    public void attack() throws NotEnoughActionsException, InvalidTargetException {
        super.attack();
        this.setActionsAvailable(this.getActionsAvailable() - 1);
    }

    public void useSpecial() throws NotEnoughActionsException, InvalidTargetException, NoAvailableResourcesException {
        super.useSpecial();
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                Game.map[x][y].setVisible(true);
            }
        }
    }

}