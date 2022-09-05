package com.example.zombie_apocalypse.controller;

import com.example.zombie_apocalypse.dto.InfectionResponse;
import com.example.zombie_apocalypse.dto.InitialInfo;
import com.example.zombie_apocalypse.exception.ZombieNotFoundException;
import com.example.zombie_apocalypse.service.ApocalypseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/zombie")
@RequiredArgsConstructor
public class ApocalypseController {

    private final ApocalypseService apocalypseService;

    @PostMapping("/infection")
    public InfectionResponse infection(@Valid @RequestBody InitialInfo world) {
        if (world.getZombie().getPosition() == null) {
            throw new ZombieNotFoundException();
        }
        return apocalypseService.infection(world);
    }

}
