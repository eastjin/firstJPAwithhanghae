package com.eastjin.firstboardproject.entity;


import com.eastjin.firstboardproject.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {
    //제목, 작성자명, 작성 내용, 작성 날짜를 조회하기. 내림차순 정렬
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userpassword;

    @Column(nullable = false)
    private String contents;

    //생성자 만들기 requestDto에 연결. id는 Auto여서 필요없음.
    public Board(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.userpassword = requestDto.getUserpassword();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
    public Board(String title, String username, String userpassword, String contents) {
        this.title = title;
        this.username = username;
        this.userpassword = userpassword;
        this.contents = contents;
    }

    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.userpassword = requestDto.getUserpassword();
        this.contents = requestDto.getContents();
    }


}