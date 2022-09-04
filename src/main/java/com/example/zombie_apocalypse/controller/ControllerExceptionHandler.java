package com.example.zombie_apocalypse.controller;

import com.example.zombie_apocalypse.exception.CommandNotFoundException;
import com.example.zombie_apocalypse.dto.ErrorDto;
import com.example.zombie_apocalypse.exception.InputDimensionsUnexpectedException;
import com.example.zombie_apocalypse.exception.ZombieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {InputDimensionsUnexpectedException.class})
    public ResponseEntity<ErrorDto> handleInputDimensionsUnexpectedException(InputDimensionsUnexpectedException e) {
        log.info(e.getMessage());

        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorDto error = new ErrorDto("Expected valid dimension", details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(value = {ZombieNotFoundException.class})
    public ResponseEntity<ErrorDto> handleZombieNotFoundException(ZombieNotFoundException e) {
        log.info("NO ZOMBIE");

        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorDto error = new ErrorDto("Zombie not found", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {CommandNotFoundException.class})
    public ResponseEntity<ErrorDto> handleCommandNotFoundException(CommandNotFoundException e) {
        log.info("Wrong commands");

        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorDto error = new ErrorDto("Commands not found", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
