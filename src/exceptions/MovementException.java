package exceptions;

public class MovementException extends GameActionException {

//-----------------------Constructor------------------------------------------------

    public MovementException() {
        super();
    }

    public MovementException(String m) {
        super(m);
    }

}
