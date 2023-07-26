package com.data.factory.controllers;

import com.data.factory.exceptions.ControllerException;
import com.data.factory.services.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ResponseHeader;

import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {

    @ResponseBody
    @PostMapping(value = "/controller",produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "New enrolment created",
                responseHeaders = @ResponseHeader(name = "Location", description = "The resulting URI of the newly-created enrolment", response = String.class))})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> index(@RequestBody String payload) throws ControllerException {
        try {

            Service service = new Service();
            return service.invoke(payload);
        } catch (Exception e) {
            throw new ControllerException(e.getMessage());
        }

    }
}