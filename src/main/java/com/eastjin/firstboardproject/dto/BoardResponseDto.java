package com.eastjin.firstboardproject.dto;

import com.eastjin.firstboardproject.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String username;

    private String userpassword;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public BoardResponseDto(Board b) {
        this.id = b.getId();
        this.title = b.getTitle();
        this.contents = b.getContents();
        this.username = b.getUsername();
        this.userpassword = b.getUserpassword();
        this.createdAt = b.getCreatedAt();
        this.modifiedAt = b.getModifiedAt();
    }

}
