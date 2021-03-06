package edu.rseymour.advancedjava.services;

/**
 * Used to signal a problem with the StockService.
 */
public class StockServiceException extends Exception {

    /**
     * Constructs a new exception with the specified detail message. The
     * cause is not initialized, and might subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public StockServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause. <p>Note that the detail message associated with {@code cause}
     * is <i>not</i> automatically incorporated in this exception's detail
     * message.</p>
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method.
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     */
    public StockServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
