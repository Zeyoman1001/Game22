package model.characters;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public class Zombie extends Character {
    //-----------------------Attributes-------------------------------------------------
    private static int ZOMBIES_COUNT = 1;

    //-----------------------Constructor------------------------------------------------
    public Zombie() {
        super("Zombie " + ZOMBIES_COUNT++, 40, 10);
    }

    public void attack() throws NotEnoughActionsException, InvalidTargetException {
        Point ZombieLocation = this.getLocation();
        int x = (int) ZombieLocation.getX();
        int y = (int) ZombieLocation.getY();
        if (this.getTarget() instanceof Zombie) {
            throw new InvalidTargetException("Can not attack your friend");
        }
        Point up = new Point(x + 1, y);
        Point down = new Point(x - 1, y);
        Point right = new Point(x, y + 1);
        Point left = new Point(x, y - 1);
        Point upRight = new Point(x + 1, y + 1);
        Point upLeft = new Point(x + 1, y - 1);
        Point downRight = new Point(x - 1, y + 1);
        Point downLeft = new Point(x - 1, y - 1);

        if (up.x >= 0 && up.x <= 14 && Game.getMap()[up.x][up.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[up.x][up.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[up.x][up.y]).getCharacter());
        }
        // ---------------------------------------------------------------------------------------
        else if (down.x >= 0 && down.x <= 14 && Game.getMap()[down.x][down.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[down.x][down.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[down.x][down.y]).getCharacter());
        }
        // ------------------------------------------------------------------------------------------
        else if (right.y >= 0 && right.y <= 14 && Game.getMap()[right.x][right.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[right.x][right.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[right.x][right.y]).getCharacter());
        }
        // ------------------------------------------------------------------------------------------
        else if (left.y >= 0 && left.y <= 14 && Game.getMap()[left.x][left.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[left.x][left.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[left.x][left.y]).getCharacter());
        }
        // -------------------------------------------------------------------------------------------
        else if (upRight.x >= 0 && upRight.x <= 14 && (upRight.y >= 0 && upRight.y <= 14)
                && Game.getMap()[upRight.x][upRight.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[upRight.x][upRight.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[upRight.x][upRight.y]).getCharacter());
        }
        // --------------------------------------------------------------------------------------------
        else if (upLeft.x >= 0 && upLeft.x <= 14 && ((upLeft.y >= 0 && upLeft.y <= 14))
                && Game.getMap()[upLeft.x][upLeft.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[upLeft.x][upLeft.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[upLeft.x][upLeft.y]).getCharacter());
        }
        // ------------------------------------------------------------------------------------------
        else if (downRight.x >= 0 && downRight.x <= 14 && ((downRight.y >= 0 && downRight.y <= 14))
                && Game.getMap()[downRight.x][downRight.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[downRight.x][downRight.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[downRight.x][downRight.y]).getCharacter());
        }
        // ------------------------------------------------------------------------------------------
        else if (downLeft.x >= 0 && downLeft.x <= 14 && ((downLeft.y >= 0 && downLeft.y <= 14))
                && Game.getMap()[downLeft.x][downLeft.y] instanceof CharacterCell
                && ((CharacterCell) Game.getMap()[downLeft.x][downLeft.y]).getCharacter() instanceof Hero) {
            this.setTarget(((CharacterCell) Game.getMap()[downLeft.x][downLeft.y]).getCharacter());
        }else {
            this.setTarget(null);
        }
        if (this.getTarget() != null) {
            super.attack();
        }
    }

}
