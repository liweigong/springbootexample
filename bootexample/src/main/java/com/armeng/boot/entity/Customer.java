package com.armeng.boot.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Customer {

    private Long id;

    private String name;

    private Long phoneNum;

    private Integer gender;
}
