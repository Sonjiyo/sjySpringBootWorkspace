package kr.study.jpa1.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //jpa만 내 객체를 생성할 수 있게
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;
    private String loginId;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
}
