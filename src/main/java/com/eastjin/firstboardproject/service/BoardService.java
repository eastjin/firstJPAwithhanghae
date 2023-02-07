package com.eastjin.firstboardproject.service;


import com.eastjin.firstboardproject.dto.BoardRequestDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    public Board getBoard_Dtl(long n) {
        Board board = boardRepository.findById(n).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return board;
    }

    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (!board.getUserpassword().equals(requestDto.getUserpassword())){
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        board.update(requestDto);
        return board.getId();
    }



    @Transactional
    public String deleteBoard(Long id, BoardRequestDto requestDto) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (!board.getUserpassword().equals(requestDto.getUserpassword())){
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        boardRepository.deleteById(id);
        return "성공";
    }
}
