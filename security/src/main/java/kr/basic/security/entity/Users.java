package kr.basic.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
   // private Timestamp createDate;

    @Builder
    public Users(String username, String password, String email, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.role=RoleUser.ROLE_USER;
    }

    //OAuth 를 위해 추가하는 필드
    private String provider;
    private String providerId;

}
