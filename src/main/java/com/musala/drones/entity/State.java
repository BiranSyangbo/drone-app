package com.musala.drones.entity;

import lombok.Getter;

@Getter
public enum State {
    IDLE,
    LOADING,
    LOADED,
    DELIVERING,
    DELIVERED,
    RETURNING;

    public boolean isIdle() {
        return this.equals(IDLE);
    }
}
