package com.ltx.entity;

import lombok.Data;

import javax.annotation.Resource;


@Data
public class Bean2 {

    @Resource
    private Bean1 bean1;

    public Bean2(Bean1 bean1) {
        this.bean1 = bean1;
        System.out.println("有参构造Bean2");
    }

    public Bean2() {
        System.out.println("无参构造Bean2");
    }
}
