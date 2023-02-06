package com.eastjin.firstboardproject.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String username;
    private String userpassword;
    private String contents;
}
