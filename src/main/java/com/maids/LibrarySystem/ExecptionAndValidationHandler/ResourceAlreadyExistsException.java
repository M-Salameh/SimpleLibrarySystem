package com.maids.LibrarySystem.ExecptionAndValidationHandler;

/**
 * Exception thrown when a resource already exists.
 */
public class ResourceAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new ResourceAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
