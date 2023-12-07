package com.moon.slopery.post.service;

import com.moon.slopery.member.entity.Member;
import com.moon.slopery.post.dto.PostRequestDto;
import com.moon.slopery.post.dto.PostResponseDto;
import com.moon.slopery.post.entity.Post;
import com.moon.slopery.post.repository.PostRepository;
import com.moon.slopery.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return postRepository.findAllByTitleContainingOrContentContaining(keyword, keyword).stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getMyPosts(String userId) {
        return postRepository.findAllByMember_UserId(userId).stream().map(PostResponseDto::new).toList();
    }
}
