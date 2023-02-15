package com.eastjin.firstboardproject.controller;

import com.eastjin.firstboardproject.dto.BoardRequestDto;
import com.eastjin.firstboardproject.dto.BoardResponseDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/elmnt")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        //Board Entity에 requestdto 안의 값들을 받아서 createBoard 작업을 한다.
        Board board_post =boardService.createBoard(requestDto, request);
        BoardResponseDto board_response = new BoardResponseDto(board_post);
        return board_response;
    }

    //데이터 전체조회
    @GetMapping("/board/elmnt")
    public List<BoardResponseDto> getBoard() {
        List<BoardResponseDto> board_getAll =boardService.getBoard();
        return board_getAll;
    }

    @GetMapping("/board/elmnt/{id}")
    public BoardResponseDto getBoard_Dtl(@PathVariable Long id) {
        BoardResponseDto getBoard_Dtl = new BoardResponseDto(boardService.getBoard_Dtl(id));
        return getBoard_Dtl;
    }

    //데이터 업데이트.
    @PutMapping("/board/elmnt/updt/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.update(id, requestDto, request);
    }

    //데이터 삭제
    @DeleteMapping("/board/elmnt/dlt/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {

        return boardService.deleteBoard(id, requestDto, request);
    }


}
