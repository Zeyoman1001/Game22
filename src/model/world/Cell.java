package model.world;



public abstract class Cell {
    //---------------------------Attributes-------------------------------------
    private boolean isVisible;

    //---------------------------constructor------------------------------------
    public Cell() {
        this.isVisible = false;
    }
    //-------------------------getters and setters--------------------------------
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }



}
