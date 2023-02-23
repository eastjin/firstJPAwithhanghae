package com.eastjin.firstboardproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsRequestDto {
    private String username;
    private String userpassword;
    private String contents;

}
