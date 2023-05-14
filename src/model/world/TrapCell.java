package model.world;

public class TrapCell extends Cell {
    // ---------------------------Attributes-------------------------------------
    private int trapDamage;

    // ---------------------------constructor------------------------------------
    public TrapCell() {
        this.trapDamage = ((((int) ((Math.random() * (30 + 1 - 10)) + 10)) / 10) * 10);
    }

    // -------------------------getters and setters--------------------------------
    public int getTrapDamage() {
        return trapDamage;
    }

}
