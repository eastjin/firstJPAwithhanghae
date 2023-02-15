package com.eastjin.firstboardproject.service;


import com.eastjin.firstboardproject.dto.BoardRequestDto;
import com.eastjin.firstboardproject.dto.BoardResponseDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//실제 db 동작방식이 진행되는 부분
@Service
@RequiredArgsConstructor
public class BoardService {

    // 레포지토리에 사용되어 연결이 가능하다.
    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(BoardRequestDto requestDto) {
        //값 자체가 없으니 생성자에 담을 인스턴스를 호출.
        Board board = new Board(requestDto);
        //인스턴스에 값 삽입.
        boardRepository.save(board);
        return board;
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoard() {
        List<Board> board_getAll = boardRepository.findAllByOrderByModifiedAtDesc();

        //ResponseDto에 담을 ArrayList 생성자 선언.
        List<BoardResponseDto> board_responseAll = new ArrayList<>();
        for(Board board : board_getAll){
            board_responseAll.add(new BoardResponseDto(board));
        }
        return board_responseAll;
    }

    public Board getBoard_Dtl(Long id) {
        return checkId(id);
    }

    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        checkId(id);
        checkPw(id,requestDto);
        checkId(id).update(requestDto);
        return checkId(id).getId();
    }



    @Transactional
    public String deleteBoard(Long id, BoardRequestDto requestDto) {
        checkId(id);
        checkPw(id,requestDto);
        boardRepository.deleteById(id);
        return "성공";
    }

    public Board checkId(long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return board;
    }

    public void checkPw(long id, BoardRequestDto requestDto){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("findPasswordthrow : 아이디가 존재하지 않습니다.")
        );
        if (!board.getUserpassword().equals(requestDto.getUserpassword())){
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
    }

}
