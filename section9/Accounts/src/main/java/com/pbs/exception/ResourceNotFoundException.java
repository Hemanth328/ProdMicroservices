package com.pbs.exception;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, String valueName) {
        super(String.format("%s Not found with the given input data %s : '%s'", resourceName, fieldName, valueName));
    }
}
