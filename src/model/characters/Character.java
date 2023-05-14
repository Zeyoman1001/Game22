package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public abstract class Character {
    //------------------------Attributes---------------------------------------------
    private String name;
    private Point location;
    private int maxHp;
    private int currentHp;
    private int attackDmg;
    private Character target;

    //------------------------Constructor---------------------------------------------
    public Character() {
    }

    public Character(String name, int maxHp, int attackDmg) {
        this.name = name;
        this.maxHp = maxHp;
        this.attackDmg = attackDmg;
        this.currentHp = maxHp;
    }

    //---------------------Setters and Getters-------------------------------------------
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp > maxHp) {
            this.currentHp = maxHp;
        } else if (currentHp <= 0) {
            this.currentHp = 0;
            this.onCharacterDeath();
        } else {
            this.currentHp = currentHp;
        }

    }

    public Character getTarget() {
        return target;
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void attack() throws NotEnoughActionsException, InvalidTargetException {

        if (target == null) {
            throw new InvalidTargetException("No target to attack");
        } else {
            Character target = this.getTarget();
            Point targetLocation = target.getLocation();
            if (Math.abs(this.getLocation().x - targetLocation.x) < 2
                    && (Math.abs(this.getLocation().x - targetLocation.x) < 2)) {
                int health = target.currentHp;
                health = health - this.attackDmg;
                target.setCurrentHp(health);
                target.setTarget(this);
                defend(target);
            } else
                throw new InvalidTargetException();

        }

    }
    public void defend(Character c) {
        int health = c.getTarget().getCurrentHp();
        health = health - (c.getAttackDmg() / 2);
        c.getTarget().setCurrentHp(health);
    }

    public void onCharacterDeath() {
        if (this.currentHp == 0) {
            Point location = this.location;
            int x = location.x;
            int y = location.y;
            Game.getMap()[x][y] = new CharacterCell(null);  
            if (this instanceof Hero) {
                Game.heroes.remove(this);
            } else if (this instanceof Zombie) {
                ArrayList<Point> Locations = new ArrayList<Point>();
                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        if (Game.map[i][j] instanceof CharacterCell
                                && ((CharacterCell) (Game.map[i][j])).getCharacter() == null && (i != x && j != y))
                            Locations.add(new Point(i, j));
                    }
                }
                Game.zombies.remove(this);
                int point = (int) (Math.random() * Locations.size());
                Zombie z = new Zombie();
                // Edge Case if zombie returned to its Place
                Game.map[Locations.get(point).x][Locations.get(point).y] = new CharacterCell(z);
                z.setLocation(Locations.get(point));
                Game.zombies.add(z);
                Locations.remove(Locations.get(point));

            }
        }
    }

    public void setAllVisible() {
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        Point up = new Point(x + 1, y);
        Point down = new Point(x - 1, y);
        Point right = new Point(x, y + 1);
        Point left = new Point(x, y - 1);
        Point upRight = new Point(x + 1, y + 1);
        Point upLeft = new Point(x + 1, y - 1);
        Point downRight = new Point(x - 1, y + 1);
        Point downLeft = new Point(x - 1, y - 1);
        if (up.x >= 0 && up.x <= 14 && (up.y >= 0 && up.y <= 14)) {
            if (Game.getMap()[up.x][up.y] == null) {
                Game.getMap()[up.x][up.y] = new CharacterCell(null);
            }
            Game.getMap()[up.x][up.y].setVisible(true);
        }
        // ---------------------------------------------------------------------------------------
        if (down.x >= 0 && down.x <= 14 && (down.y >= 0 && down.y <= 14)) {
            if (Game.getMap()[down.x][down.y] == null) {
                Game.getMap()[down.x][down.y] = new CharacterCell(null);
            }
            Game.getMap()[down.x][down.y].setVisible(true);
        }
        // ------------------------------------------------------------------------------------------
        if (right.y >= 0 && right.y <= 14 && (right.y >= 0 && right.y <= 14)) {
            if (Game.getMap()[right.x][right.y] == null) {
                Game.getMap()[right.x][right.y] = new CharacterCell(null);
            }
            Game.getMap()[right.x][right.y].setVisible(true);
        }
        // ------------------------------------------------------------------------------------------
        if (left.y >= 0 && left.y <= 14 && (left.y >= 0 && left.y <= 14)) {
            if (Game.getMap()[left.x][left.y] == null) {
                Game.getMap()[left.x][left.y] = new CharacterCell(null);
            }
            Game.getMap()[left.x][left.y].setVisible(true);
        }
        // -------------------------------------------------------------------------------------------
        if (upRight.x >= 0 && upRight.x <= 14 && (upRight.y >= 0 && upRight.y <= 14)) {
            if (Game.getMap()[upRight.x][upRight.y] == null) {
                Game.getMap()[upRight.x][upRight.y] = new CharacterCell(null);
            }
            Game.getMap()[upRight.x][upRight.y].setVisible(true);
        }
        // --------------------------------------------------------------------------------------------
        if (upLeft.x >= 0 && upLeft.x <= 14 && ((upLeft.y >= 0 && upLeft.y <= 14))) {
            if (Game.getMap()[upLeft.x][upLeft.y] == null) {
                Game.getMap()[upLeft.x][upLeft.y] = new CharacterCell(null);
            }
            Game.getMap()[upLeft.x][upLeft.y].setVisible(true);
        }
        // ------------------------------------------------------------------------------------------
        if (downRight.x >= 0 && downRight.x <= 14 && ((downRight.y >= 0 && downRight.y <= 14))) {
            if (Game.getMap()[downRight.x][downRight.y] == null) {
                Game.getMap()[downRight.x][downRight.y] = new CharacterCell(null);
            }
            Game.getMap()[downRight.x][downRight.y].setVisible(true);
        }
        // ------------------------------------------------------------------------------------------
        if (downLeft.x >= 0 && downLeft.x <= 14 && ((downLeft.y >= 0 && downLeft.y <= 14))) {
            if (Game.getMap()[downLeft.x][downLeft.y] == null) {
                Game.getMap()[downLeft.x][downLeft.y] = new CharacterCell(null);
            }
            Game.getMap()[downLeft.x][downLeft.y].setVisible(true);
        }
        // ------------------------------------------------------------------
        Game.getMap()[x][y].setVisible(true);
    }

}
