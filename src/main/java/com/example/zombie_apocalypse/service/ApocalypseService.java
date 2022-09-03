package com.example.zombie_apocalypse.service;

import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.model.World;
import com.example.zombie_apocalypse.dto.Result;
import com.example.zombie_apocalypse.dto.ZombiesAndCreatures;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApocalypseService {
    public Result infection(World world) {
        System.out.println("world = " + world);
        return new InfectionSimulation(world).infection();
    }

}
