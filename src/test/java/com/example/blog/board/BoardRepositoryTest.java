package com.example.blog.board;

import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(BoardRepository.class)
@DataJpaTest // DB 관련된 자원들을 메모리(IOC)에 올린다.
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void findByIdJoinUser_test() {
        Board byIdJoinUser = boardRepository.findByIdJoinUser(1);

        System.out.println(byIdJoinUser.getUser().getUsername());
    }

    @Test
    public void findById_test() {
        // given
        Integer id = 1;
        
        // when
        Optional<Board> boardOP = boardRepository.findById(id);
        Board board = boardOP.get();

        // 현재 Board 객체 안의 User 클래스는 id만 가지고 있고 username은 없는 상태
        System.out.println("Lazy 로딩 직전");
        String title = board.getUser().getUsername();
        System.out.println("Lazy 로딩 직후");

        // eye
    }
    
    
    // 테스트 코드에서는 언더바를 쓰는 것이 약속이다.
//    @Test
//    public void findAll_test() {
//
//        // given
//
//        // when
//        List<Board> boardList = boardRepository.findAll();
//
//        System.out.println();
//        // eye
//        for (Board board : boardList) {
//            System.out.println(board.getId());
//            System.out.println(board.getTitle());
//            System.out.println(board.getContent());
//            System.out.println(board.getCreatedAt());
//            System.out.println("=====================");
//        }
//    }

}
