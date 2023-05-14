package model.characters;

import java.awt.Point;
import java.util.*;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public abstract class Hero extends Character {
    //-------------------------Attributes---------------------------------------------------
    private int actionsAvailable;
    private int maxActions;
    private boolean specialAction;
    private ArrayList<Vaccine> vaccineInventory;
    private ArrayList<Supply> supplyInventory;

//--------------------------Constructor--------------------------------------------------

    public Hero(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg);
        this.actionsAvailable = maxActions;
        this.maxActions = maxActions;
        this.specialAction = false;
        vaccineInventory = new ArrayList<>();
        supplyInventory = new ArrayList<>();

    }

    // ----------------------Setters and Getters-----------------------------------------------
    public int getActionsAvailable() {
        return actionsAvailable;
    }

    public void setActionsAvailable(int actionsAvailable) {
        this.actionsAvailable = actionsAvailable;
    }

    public boolean isSpecialAction() {
        return specialAction;
    }

    public void setSpecialAction(boolean specialAction) {
        this.specialAction = specialAction;
    }

    public int getMaxActions() {
        return maxActions;
    }

    public ArrayList<Vaccine> getVaccineInventory() {
        return vaccineInventory;
    }

    public ArrayList<Supply> getSupplyInventory() {
        return supplyInventory;
    }

//-------------------------------------------------------------------------------------------

    public void attack() throws NotEnoughActionsException, InvalidTargetException {

        if (this.getActionsAvailable() == 0) {
            throw new NotEnoughActionsException("No Actions Available to attack");
        }
        if (this.getTarget() instanceof Hero) {
            throw new InvalidTargetException("You Can not attack Your Frind");
        }
        super.attack();
    }

    public void move(Direction d) throws MovementException, NotEnoughActionsException, InvalidTargetException {
        if (this.getCurrentHp() == 0) {
            this.onCharacterDeath();
            return ;
        }
        if (this.actionsAvailable > 0) {
            Point currentLocation = this.getLocation();
            int x = (int) currentLocation.getX();
            int y = (int) currentLocation.getY();
            Point up = new Point(x + 1, y);
            Point down = new Point(x - 1, y);
            Point right = new Point(x, y + 1);
            Point left = new Point(x, y - 1);

            if (d == Direction.UP) {
                if (!(up.x >= 0 && up.x <= 14)) {
                    throw new MovementException("You Cant Move Up");
                } else {
                    if (Game.map[up.x][up.y] instanceof TrapCell) {
                        TrapCell trap = ((TrapCell) Game.map[up.x][up.y]);
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[up.x][up.y] = new CharacterCell(this);
                        this.setAllVisible();
                        this.setCurrentHp(this.getCurrentHp() - trap.getTrapDamage());
                        if (this.getCurrentHp() == 0) {
                            Game.map[up.x][up.y] = new CharacterCell(null);
                        }
                        // -------------------------------------------------------------------
                    } else if (Game.map[up.x][up.y] instanceof CollectibleCell) {
                        Collectible collectible = ((CollectibleCell) Game.map[up.x][up.y]).getCollectible();
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[up.x][up.y] = new CharacterCell(this);
                        this.setAllVisible();
                        collectible.pickUp(this);
                        // -------------------------------------------------------------------
                    } else {
                        if (((CharacterCell) Game.map[up.x][up.y]).getCharacter() == null) {
                            Game.map[x][y] = new CharacterCell(null);
                            Game.map[up.x][up.y] = new CharacterCell(this);
                            this.setAllVisible();
                        } else {
                            throw new MovementException("You Can Move To This Cell , There is Character Here");
                        }
                    }
                }
                this.setLocation(new Point(++this.getLocation().x , this.getLocation().y));
            } else if (d == Direction.DOWN) {
                if (!(down.x >= 0 && down.x <= 14)) {
                    throw new MovementException("You Cant Move Down");
                } else {
                    if (Game.map[down.x][down.y] instanceof TrapCell) {
                        TrapCell trap = ((TrapCell) Game.map[down.x][down.y]);
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[down.x][down.y] = new CharacterCell(this);
                        this.setAllVisible();
                        this.setCurrentHp(this.getCurrentHp() - trap.getTrapDamage());
                        if (this.getCurrentHp() == 0) {
                            Game.map[down.x][down.y] = new CharacterCell(null);
                        }
                        // -------------------------------------------------------------------
                    } else if (Game.map[down.x][down.y] instanceof CollectibleCell) {
                        Collectible collectible = ((CollectibleCell) Game.map[down.x][down.y]).getCollectible();
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[down.x][down.y] = new CharacterCell(this);
                        this.setAllVisible();
                        collectible.pickUp(this);
                        // -------------------------------------------------------------------
                    } else {
                        if (((CharacterCell) Game.map[down.x][down.y]).getCharacter() == null) {
                            Game.map[x][y] = new CharacterCell(null);
                            Game.map[down.x][down.y] = new CharacterCell(this);
                            this.setAllVisible();
                        } else {
                            throw new MovementException("You Can Move To This Cell , There is Character Here");
                        }
                    }
                }
                this.setLocation(new Point(--this.getLocation().x , this.getLocation().y));
            } else if (d == Direction.RIGHT) {
                if (!(right.y >= 0 && right.y <= 14)) {
                    throw new MovementException("You Cant Move Right");
                } else {
                    if (Game.map[right.x][right.y] instanceof TrapCell) {
                        TrapCell trap = ((TrapCell) Game.map[right.x][right.y]);
                        this.setCurrentHp(this.getCurrentHp() - trap.getTrapDamage());
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[right.x][right.y] = new CharacterCell(this);
                        this.setAllVisible();
                        if (this.getCurrentHp() == 0) {
                            Game.map[right.x][right.y] = new CharacterCell(null);
                        }
                        // -------------------------------------------------------------------
                    } else if (Game.map[right.x][right.y] instanceof CollectibleCell) {
                        Collectible collectible = ((CollectibleCell) Game.map[right.x][right.y]).getCollectible();
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[right.x][right.y] = new CharacterCell(this);
                        collectible.pickUp(this);
                        // -------------------------------------------------------------------
                    } else {
                        if (((CharacterCell) Game.map[right.x][right.y]).getCharacter() == null) {
                            Game.map[x][y] = new CharacterCell(null);
                            Game.map[right.x][right.y] = new CharacterCell(this);
                        } else {
                            throw new MovementException("You Can Move To This Cell , There is Character Here");
                        }
                    }

                }
                this.setLocation(new Point(this.getLocation().x , ++this.getLocation().y));
            } else if (d == Direction.LEFT) {
                if (!(left.y >= 0 && left.y <= 14)) {
                    throw new MovementException("You Cant Move Left");
                } else {
                    if (Game.map[left.x][left.y] instanceof TrapCell) {
                        TrapCell trap = ((TrapCell) Game.map[left.x][left.y]);
                        this.setCurrentHp(this.getCurrentHp() - trap.getTrapDamage());
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[left.x][left.y] = new CharacterCell(this);
                        this.setAllVisible();
                        if (this.getCurrentHp() == 0) {
                            Game.map[left.x][left.y] = new CharacterCell(null);
                        }
                        // -------------------------------------------------------------------
                    } else if (Game.map[left.x][left.y] instanceof CollectibleCell) {
                        Collectible collectible = ((CollectibleCell) Game.map[left.x][left.y]).getCollectible();
                        Game.map[x][y] = new CharacterCell(null);
                        Game.map[left.x][left.y] = new CharacterCell(this);
                        collectible.pickUp(this);
                        // -------------------------------------------------------------------
                    } else {
                        if (((CharacterCell) Game.map[left.x][left.y]).getCharacter() == null) {
                            Game.map[x][y] = new CharacterCell(null);
                            Game.map[left.x][left.y] = new CharacterCell(this);
                        } else {
                            throw new MovementException("You Can Move To This Cell , There is Character Here");
                        }
                    }
                }
                this.setLocation(new Point(this.getLocation().x , --this.getLocation().y));
            }
            this.setActionsAvailable(this.getActionsAvailable() - 1);
                this.setAllVisible();
        } else {
            throw new NotEnoughActionsException("No Actions Available ,You Can Not Move");
        }
    }

    public void useSpecial() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException {
        if (this.getSupplyInventory().size() == 0) {
            throw new NoAvailableResourcesException("No Supplyies available");
        } else {
            Collectible c = this.getSupplyInventory().get(0);
            c.use(this);
//            this.setActionsAvailable(this.getActionsAvailable() - 1);
            this.setSpecialAction(true);
        }
    }

    public void cure() throws Exception {
        if (this.actionsAvailable == 0) {
            throw new NotEnoughActionsException("No Actions to Cure");
        } else if (this.vaccineInventory.size() == 0) {
            throw new NoAvailableResourcesException("No Vaccines Available");
        } else if (Game.availableHeroes.size() == 0) {
            throw new Exception("No Heroes to be added");
        } else {
            Character target = this.getTarget();
            if (target == null) {
                throw new InvalidTargetException("No Target Selected To be Cured");
            }
            if (target instanceof Hero) {
                throw new InvalidTargetException("You can not cure Hero");
            } else {
                Point targetLocation = target.getLocation();
                int x = this.getLocation().x;
                int y = this.getLocation().y;
                int i = targetLocation.x;
                int j = targetLocation.y;
                if ((Math.abs(x - i) == 0 && Math.abs(y - j) == 0)) {
                    throw new InvalidTargetException("This Target not adjacent to your self");
                }
                if (!(Math.abs(x - i) < 2 && Math.abs(y - j) < 2)) {
                    throw new InvalidTargetException("This Target not adjacent to you to Cure");
                } else {
                    this.setTarget(target);
                    Collectible vacciene = this.vaccineInventory.get(0);
                    vacciene.use(this);
                }
            }
        }

    }
}
