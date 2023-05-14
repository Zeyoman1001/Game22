package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Fighter extends Hero {

    //--------------------------Constructor--------------------------------------------------
    public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }

    public void attack() throws NotEnoughActionsException, InvalidTargetException {
        super.attack();
        if (this.isSpecialAction() == false) {
            this.setActionsAvailable(this.getActionsAvailable() - 1);
        }
    }

    public void useSpecial() throws NotEnoughActionsException, InvalidTargetException, NoAvailableResourcesException {
        super.useSpecial();
    }

}
