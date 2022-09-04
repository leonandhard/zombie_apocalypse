package com.example.zombie_apocalypse.controller;

import com.example.zombie_apocalypse.model.World;
import com.example.zombie_apocalypse.service.ApocalypseService;
import com.example.zombie_apocalypse.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public Result infection(@Valid @RequestBody World world) {
        return apocalypseService.infection(world);
    }

}
