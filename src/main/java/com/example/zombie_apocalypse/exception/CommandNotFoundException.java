package com.example.zombie_apocalypse.exception;

public class CommandNotFoundException extends RuntimeException{
    public CommandNotFoundException(String command) {
        super("Expect command are RULD, but get " + command);
    }
}
