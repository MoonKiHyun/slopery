package com.moon.slopery.post.repository;

import com.moon.slopery.member.entity.Member;
import com.moon.slopery.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);
    List<Post> findAllByMember_UserId(String userId);
}
