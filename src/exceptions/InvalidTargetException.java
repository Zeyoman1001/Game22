package exceptions;

public class InvalidTargetException extends GameActionException {

//-----------------------Constructor------------------------------------------------

    public InvalidTargetException() {
        super();
    }

    public InvalidTargetException(String m) {
        super(m);
    }

}
