package com.musala.drones.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource,Long id) {
        super(resource + " Not found with " + id);
    }
}
