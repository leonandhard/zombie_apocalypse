package com.example.zombie_apocalypse.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorDto {
    private String message;
    private List<String> details;
}
