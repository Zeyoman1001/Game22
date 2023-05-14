package exceptions;

public class NoAvailableResourcesException extends GameActionException {

//-----------------------Constructor------------------------------------------------

    public NoAvailableResourcesException() {
        super();
    }

    public NoAvailableResourcesException(String m) {
        super(m);
    }

}
