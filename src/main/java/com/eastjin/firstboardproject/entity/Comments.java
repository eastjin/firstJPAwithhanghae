package com.eastjin.firstboardproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comments extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userpassword;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    @JsonIgnoreProperties("comments")
    private Board board;

    public Comments(String username, String userpassword, String contents, Board board) {
        this.username = username;
        this.userpassword = userpassword;
        this.contents = contents;
        this.board = board;
    }

}
