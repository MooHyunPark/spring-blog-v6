package com.example.blog.board;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@NoArgsConstructor // DB에서 조회해서 가져온 ResultSet을  디폴트 생성자를 호출해서 new하고 값을 채워준다.
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment 와 동일
    private int id;
    private String title;
    private String content;
    private Timestamp createdAt;
}
