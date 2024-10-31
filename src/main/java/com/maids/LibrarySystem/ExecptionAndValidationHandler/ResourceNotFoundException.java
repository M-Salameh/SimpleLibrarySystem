package com.maids.LibrarySystem.ExecptionAndValidationHandler;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
