package com.moon.slopery.comment.controller;

import com.moon.slopery.comment.dto.CommentRequestDto;
import com.moon.slopery.comment.dto.CommentResponseDto;
import com.moon.slopery.comment.service.CommentService;
import com.moon.slopery.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto responseDto = commentService.addComment(postId, requestDto, userDetails.getMember());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
