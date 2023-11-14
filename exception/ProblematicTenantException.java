package exception;

/**
 * Custom exception to throw
 */
public class ProblematicTenantException extends Exception{
    public ProblematicTenantException(String message) {
        super(message);
    }
}
