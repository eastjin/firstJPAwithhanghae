package com.eastjin.firstboardproject.controller;

import com.eastjin.firstboardproject.dto.BoardRequestDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/elmnt")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    //데이터 검색.
    @GetMapping("/board/elmnt")
    public List<Board> getBoard() {
        return boardService.getBoard();
    }


}
