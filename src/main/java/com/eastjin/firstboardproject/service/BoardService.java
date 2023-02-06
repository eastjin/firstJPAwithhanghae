package com.eastjin.firstboardproject.service;


import com.eastjin.firstboardproject.dto.BoardRequestDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//실제 db 동작방식이 진행되는 부분
@Service
@RequiredArgsConstructor
public class BoardService {

    // 레포지토리에 사용되어 연결이 가능하다.
    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;
    }

    @Transactional(readOnly = true)
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

}
