package com.csrr.consul.csrrconsul.dto;


import lombok.Data;

@Data
public class RequestWithPageDTO<T> {

    private T data;
    private Integer pageSize;
    private Integer startPage;

}
