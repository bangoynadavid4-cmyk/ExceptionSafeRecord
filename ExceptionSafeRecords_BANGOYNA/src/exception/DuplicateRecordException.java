package exception;

/**
 * Custom checked exception for duplicate record IDs.
 * @author BANGOYNA
 */
public class DuplicateRecordException extends Exception {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
