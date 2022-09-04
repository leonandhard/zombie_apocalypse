package com.example.zombie_apocalypse.controller;

import com.example.zombie_apocalypse.dto.InitialInfo;
import com.example.zombie_apocalypse.exception.ZombieNotFoundException;
import com.example.zombie_apocalypse.service.ApocalypseService;
import com.example.zombie_apocalypse.dto.InfectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/zombie")
public class ApocalypseController {

    private final ApocalypseService apocalypseService;

    @Autowired
    public ApocalypseController(ApocalypseService apocalypseService) {
        this.apocalypseService = apocalypseService;
    }

    @PostMapping("/infection")
    public InfectionResponse infection(@Valid @RequestBody InitialInfo world) {
        if (world.getZombie().getPosition()==null){
            throw new ZombieNotFoundException();
        }
        System.out.println("world = " + world);
        return apocalypseService.infection(world);
    }

}
