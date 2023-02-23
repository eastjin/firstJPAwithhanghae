package com.eastjin.firstboardproject.service;

import com.eastjin.firstboardproject.dto.CommentsRequestDto;
import com.eastjin.firstboardproject.entity.Board;
import com.eastjin.firstboardproject.entity.Comments;
import com.eastjin.firstboardproject.entity.Users;
import com.eastjin.firstboardproject.exception.CommentNotFoundException;
import com.eastjin.firstboardproject.jwt.JwtUtil;
import com.eastjin.firstboardproject.repository.BoardRepository;
import com.eastjin.firstboardproject.repository.CommentsRepository;
import com.eastjin.firstboardproject.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    public Comments createComment(Long boardId, CommentsRequestDto requestDto, HttpServletRequest request) {
        userValidation(request);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CommentNotFoundException("게시물을 찾을 수 없습니다."));
        Comments comment = new Comments(requestDto.getUsername(), requestDto.getUserpassword(), requestDto.getContents(), board);
        return commentsRepository.save(comment);
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
                // 사용자 존재여부 검증임. 이 사용자가 게시글 작성자랑 같은지 확인하는건 별개 문제.
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
