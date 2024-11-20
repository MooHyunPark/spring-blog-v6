package com.example.blog.board;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@NoArgsConstructor // DB에서 조회해서 가져온 ResultSet을  디폴트 생성자를 호출해서 new하고 값을 채워준다.
@AllArgsConstructor
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment 와 동일
    private Integer id;
    private String title;
    private String content;
    @CreationTimestamp
    private Timestamp createdAt;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


