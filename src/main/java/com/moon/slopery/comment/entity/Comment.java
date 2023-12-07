package com.moon.slopery.comment.entity;

import com.moon.slopery.Timestamped;
import com.moon.slopery.comment.dto.CommentRequestDto;
import com.moon.slopery.member.entity.Member;
import com.moon.slopery.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto requestDto, Member member, Post post) {
        this.text = requestDto.getText();
        this.member = member;
        this.post = post;
    }
}
