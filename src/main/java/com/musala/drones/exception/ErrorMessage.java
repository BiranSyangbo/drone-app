package com.musala.drones.exception;

import java.util.Date;
import java.util.List;

public record ErrorMessage(int statusCode, Date timestamp, List<String> message,
                           String description) {
}
