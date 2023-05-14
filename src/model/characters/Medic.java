package model.characters;

import java.awt.Point;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {

//--------------------------Constructor--------------------------------------------------
    public Medic(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }

    public void attack() throws NotEnoughActionsException, InvalidTargetException {
        super.attack();
        this.setActionsAvailable(this.getActionsAvailable() - 1);
    }

    public void useSpecial() throws NotEnoughActionsException, InvalidTargetException, NoAvailableResourcesException {
        Character target = this.getTarget();
        if (target instanceof Zombie)
            throw new InvalidTargetException("You Can Not Heal Zombie");
        Point targetLocation = target.getLocation();
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        int i = targetLocation.x;
        int j = targetLocation.y;
        if (((Math.abs(x - i) < 2 && Math.abs(y - j) < 2))) {

            super.useSpecial();
            this.setTarget(target);
            target.setCurrentHp(getMaxHp());
            this.setSpecialAction(false);
        }
        else {
            throw new InvalidTargetException("you should Heal adjacent cells");
        }

    }
}
