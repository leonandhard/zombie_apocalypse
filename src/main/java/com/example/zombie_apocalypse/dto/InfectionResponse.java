package com.example.zombie_apocalypse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfectionResponse {
    private boolean success;
    private int code;
    private String msg;
    private ZombiesAndCreatures data;

}
