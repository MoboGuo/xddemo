package com.mobo.xddemo.domain;

import lombok.Data;

/**
 * 用户类
 *
 * @author Mobo
 * @create 2020-12-08-18:23
 */
@Data
public class User {

    private String name;

    private String job;

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
