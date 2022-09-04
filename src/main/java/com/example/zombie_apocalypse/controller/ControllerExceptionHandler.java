package com.example.zombie_apocalypse.controller;

import com.example.zombie_apocalypse.dto.ErrorDto;
import com.example.zombie_apocalypse.exception.ZombieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> details = new ArrayList<>();
        details.add(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        ErrorDto error = new ErrorDto("error", details);
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


}
