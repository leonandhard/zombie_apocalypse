package com.example.zombie_apocalypse.exception;

public class ZombieNotFoundException extends RuntimeException{
    public ZombieNotFoundException(){
        super("CAN NOT FOUND FIRST ZOMBIE");
    }
}
