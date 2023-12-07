package com.moon.slopery.comment.service;

import com.moon.slopery.comment.dto.CommentRequestDto;
import com.moon.slopery.comment.dto.CommentResponseDto;
import com.moon.slopery.comment.entity.Comment;
import com.moon.slopery.comment.repository.CommentRepository;
import com.moon.slopery.member.entity.Member;
import com.moon.slopery.post.entity.Post;
import com.moon.slopery.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto addComment(Long postId, CommentRequestDto requestDto, Member member) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NullPointerException("해당 게시물을 찾을 수 없습니다."));

        Comment comment = new Comment(requestDto, member, post);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
