package com.eastjin.firstboardproject.service;


import com.eastjin.firstboardproject.dto.BoardRequestDto;
import com.eastjin.firstboardproject.dto.BoardResponseDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.entity.Users;
import com.eastjin.firstboardproject.jwt.JwtUtil;
import com.eastjin.firstboardproject.repository.BoardRepository;
import com.eastjin.firstboardproject.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//실제 db 동작방식이 진행되는 부분
@Service
@RequiredArgsConstructor
public class BoardService {

    // 레포지토리에 사용되어 연결이 가능하다.
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public Board createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        //토큰 실패하면 return 반환
        userValidation(request);
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
    public Long update(Long id, BoardRequestDto requestDto,HttpServletRequest request) {
        //토큰 실패하면 return 반환
        userValidation(request);
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

    @Transactional
    public boolean userValidation(@RequestBody HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        boolean valid = false;
        // 토큰검증해서 유효한 경우만 게시물 관련 작업 가능하게 하고싶음
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) { // 토큰에서 사용자 정보 가져오기 - 사용자가 존재하는지도 이미 여기서 검증!
                // 근데 이건 사용자 존재여부 검증임. 이 사용자가 게시글 작성자랑 같은지 확인하는건 별개 문제.
                claims = jwtUtil.getUserInfoFromToken(token);
            } else throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Users user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            // token에서 검사한 사용자와 게시글 작성자가 같은지 알고싶다면- token subject에 username 들어감. 이걸 가져오고 싶음.
            valid = claims.getSubject().equals(user.getUsername());
        }
        return valid;
    }



}
