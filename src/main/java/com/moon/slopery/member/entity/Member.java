package com.moon.slopery.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt;

    public Member(String userId, String email, String password, String userName, String phoneNumber, MemberRoleEnum role) {
        this.email = email;
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }
}
