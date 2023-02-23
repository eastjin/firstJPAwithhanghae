package com.eastjin.firstboardproject.controller;

import com.eastjin.firstboardproject.dto.CommentsRequestDto;
import com.eastjin.firstboardproject.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards/{boardId}/comments")
public class CommentsController {
    private final CommentsService commentsService;

    @PostMapping("")
    public ResponseEntity<?> createComment(@PathVariable Long boardId, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request) {
        return ResponseEntity.ok(commentsService.createComment(boardId, requestDto,request));
    }

//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<?> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId, @RequestBody String password) {
//        commentsService.deleteComment(boardId, commentId, password);
//        return ResponseEntity.noContent().build();
//    }

}