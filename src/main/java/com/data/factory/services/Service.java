package com.data.factory.services;

import com.data.factory.exceptions.ControllerException;
import com.data.factory.exceptions.RequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Service {
    private static final Logger log = LoggerFactory.getLogger(Service.class);

    private static String INVALID_ATTRIBUTE = "%s cannot be null or empty.";
    private static String INVALID_REQUEST = "Invalid request format.";

    public Service() {
        log.info("Creating object service.");
    }

    public ResponseEntity<String> invoke(String body){
        try {
            if (body == null || body.isEmpty()) throw new RequestException(String.format(INVALID_ATTRIBUTE, "body"));
            log.info("Processing PayLoad.");
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (ControllerException e){
            String errorMessage = String.format("%s %s", e.getClass(), e.getMessage());
            log.error(errorMessage);
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
