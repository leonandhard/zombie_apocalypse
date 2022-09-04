package com.example.zombie_apocalypse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class infectionResponse {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public  infectionResponse success(Object data){
        return new infectionResponse(true,200,"success",data);
    }

//    public static Result fail(int code, String msg){
//        return new Result(false,code,msg,null);
//    }
}
