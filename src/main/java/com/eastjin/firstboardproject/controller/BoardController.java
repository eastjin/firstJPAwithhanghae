package com.eastjin.firstboardproject.controller;

import com.eastjin.firstboardproject.dto.BoardRequestDto;
import com.eastjin.firstboardproject.dto.BoardResponseDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/elmnt")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        //Board Entity에 requestdto 안의 값들을 받아서 createBoard 작업을 한다.
        Board board_post =boardService.createBoard(requestDto);
        BoardResponseDto board_response = new BoardResponseDto(board_post);
        return board_response;
    }

    //데이터 전체조회
    @GetMapping("/board/elmnt")
    public List<BoardResponseDto> getBoard() {
        List<Board> board_getAll =boardService.getBoard();
        List<BoardResponseDto> board_responseAll = new ArrayList<>();
        for(Board board : board_getAll){
            board_responseAll.add(new BoardResponseDto(board));
        }
        return board_responseAll;
    }

    @GetMapping("/board/elmnt/{id}")
    public BoardResponseDto getBoard_Dtl(@PathVariable Board id) {
        BoardResponseDto getBoard_Dtl = new BoardResponseDto(id);
        return getBoard_Dtl;
    }


}
