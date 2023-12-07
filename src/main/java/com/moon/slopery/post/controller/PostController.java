package com.moon.slopery.post.controller;

import com.moon.slopery.post.dto.PostRequestDto;
import com.moon.slopery.post.dto.PostResponseDto;
import com.moon.slopery.post.service.PostService;
import com.moon.slopery.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> writePost(@Valid @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.writePost(requestDto, userDetails.getMember());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/my-post")
    public ResponseEntity<List<PostResponseDto>> getMyPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<PostResponseDto> responseDtoList = postService.getMyPosts(userDetails.getMember().getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/search")
    ResponseEntity<List<PostResponseDto>> getPostsByKeyword(@RequestParam String keyword) {
        List<PostResponseDto> responseDtoList = postService.getPostsByKeyword(keyword);

        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }
}
