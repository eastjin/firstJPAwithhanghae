package com.eastjin.firstboardproject.dto;

import com.eastjin.firstboardproject.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
//
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String username;
    private String userpassword;
    private String contents;


}
