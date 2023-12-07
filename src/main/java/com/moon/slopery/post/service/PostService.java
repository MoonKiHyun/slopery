package com.moon.slopery.post.service;

import com.moon.slopery.CommonResponseDto;
import com.moon.slopery.member.entity.Member;
import com.moon.slopery.post.dto.PostRequestDto;
import com.moon.slopery.post.dto.PostResponseDto;
import com.moon.slopery.post.entity.Post;
import com.moon.slopery.post.repository.PostRepository;
import com.moon.slopery.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto writePost(PostRequestDto requestDto, Member member) {
        Post post = new Post(requestDto);
        post.setMember(member);
        postRepository.save(post);

        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getPostsByKeyword(String keyword) {

        if (!StringUtils.hasText(keyword)) {
            throw new NullPointerException("검색어를 입력해 주세요.");
        }

        return postRepository.findAllByTitleContainingOrContentContaining(keyword, keyword).stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getMyPosts(String userId) {
        return postRepository.findAllByMember_UserId(userId).stream().map(PostResponseDto::new).toList();
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, Member member) {
        Post post = verifyPostIdAndMember(postId, member);
        post.updatePost(requestDto);

        return new PostResponseDto(post);
    }

    public void deletePost(Long postId, Member member) {
        Post post = verifyPostIdAndMember(postId, member);
        postRepository.delete(post);
    }

    private Post verifyPostIdAndMember(Long postId, Member member) {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시물을 찾을 수 없습니다"));

        if (!member.getUserId().equals(post.getMember().getUserId())) {
            throw new IllegalArgumentException("해당 게시글 작성자가 아닙니다.");
        }

        return post;
    }
}
