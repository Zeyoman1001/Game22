package exceptions;

public class NotEnoughActionsException extends GameActionException {

//-----------------------Constructor------------------------------------------------

    public NotEnoughActionsException() {
        super();
    }

    public NotEnoughActionsException(String m) {
        super(m);
    }

}
