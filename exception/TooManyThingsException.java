package exception;

/**
 * Custom exception to throw
 */
public class TooManyThingsException extends Exception{
    public TooManyThingsException(String message) {
        super(message);
    }
}
