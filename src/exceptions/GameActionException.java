package exceptions;

public abstract class GameActionException extends Exception {

//-----------------------Constructor------------------------------------------------

    public GameActionException() {
        super();
    }

    public GameActionException(String m) {
        super(m);
    }

}
