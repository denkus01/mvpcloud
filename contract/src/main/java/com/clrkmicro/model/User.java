package com.clrkmicro.model;

import lombok.Value;

@Value
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
}
