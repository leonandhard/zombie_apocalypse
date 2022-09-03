package com.example.zombie_apocalypse.controller;

import com.alibaba.fastjson.JSON;
import com.example.zombie_apocalypse.model.Creature;
import com.example.zombie_apocalypse.model.Position;
import com.example.zombie_apocalypse.model.World;
import com.example.zombie_apocalypse.model.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class ApocalypseControllerTest {
    @Resource
    private MockMvc mock;

    @Autowired
    private WebApplicationContext wac;

    Logger logger = LoggerFactory.getLogger(ApocalypseControllerTest.class);
    @Test
    void infection() throws Exception {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
        World param = new World();
        param.setDimensions(10);
        Zombie zombie = new Zombie(new Position(2,1));
        param.setZombie(zombie);
        List<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature(new Position(3, 1)));
        creatures.add(new Creature(new Position(4, 1)));
        creatures.add(new Creature(new Position(5, 1)));
        creatures.add(new Creature(new Position(6, 1)));
        creatures.add(new Creature(new Position(7, 1)));
        creatures.add(new Creature(new Position(8, 1)));
        creatures.add(new Creature(new Position(9, 1)));
        creatures.add(new Creature(new Position(0, 1)));
        creatures.add(new Creature(new Position(9, 9)));
        param.setCreatures(creatures);
        param.setCommands("RD");
        System.out.println("param = " + param);
        System.out.println("param = " + JSON.toJSONString(param));
        RequestBuilder request = MockMvcRequestBuilders.post("/zombie/infection")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(param));
        MvcResult mvcResult = mock.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
//        logger.info(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals("{\"success\":true,\"code\":200,\"msg\":\"success\",\"data\":{\"zombies\":[{\"x\":3,\"y\":2},{\"x\":4,\"y\":2},{\"x\":5,\"y\":2},{\"x\":6,\"y\":2},{\"x\":7,\"y\":2},{\"x\":8,\"y\":2},{\"x\":9,\"y\":2},{\"x\":0,\"y\":2},{\"x\":1,\"y\":2}],\"creatures\":[{\"x\":9,\"y\":9}]}}",
                mvcResult.getResponse().getContentAsString());
    }
}