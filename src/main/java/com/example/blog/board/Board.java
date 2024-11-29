package com.example.blog.board;


import com.example.blog.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "board_tb")
@Entity // Board 객체를 테이블로 생성하며, 데이터베이스 테이블과 매핑하여 데이터베이스 작업(insert, update, delete 등)을 수행할 수 있습니다.
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment 와 동일
    private Integer id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


