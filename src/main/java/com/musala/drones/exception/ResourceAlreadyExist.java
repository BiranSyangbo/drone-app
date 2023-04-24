package com.musala.drones.exception;

import java.util.Arrays;

public class ResourceAlreadyExist extends RuntimeException {
    public ResourceAlreadyExist(String resource, Object... value) {
        super(resource + " already exist " + Arrays.toString(value));
    }
}
