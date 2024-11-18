package com.example.blog.board;

import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest // DB 관련된 자원들을 메모리(IOC)에 올린다.
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // 테스트 코드에서는 언더바를 쓰는 것이 약속이다.
    @Test
    public void findAll_test() {

        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        System.out.println();
        // eye
        for (Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("=====================");
        }
    }

    // @Transactional 어노테이션이 있는경우 rollback 을 한다.
    @Test
    public void save_test() {

        String title = "제목6";
        String content = "내용6";
        boardRepository.save(title, content);

        Board board = boardRepository.findById(6);
        System.out.println(board.getTitle());

    }

    @Test
    public void delete_test() {
        // given
        int id = 1;

        // when
        boardRepository.delete(1);

        //eye
        List<Board> boardList = boardRepository.findAll();
        System.out.println("사이즈 :" + boardList.size());

    }

    @Test
    public void update_test() {
        // given
        int id = 5;
        String title = "새로운 제목";
        String content = "새로운 내용";

        // when
        boardRepository.update(id, title, content);

        //eye
        List<Board> boardList = boardRepository.findAll();
        System.out.println("내용 체크 :" + boardList.get(0).getTitle());
        System.out.println("내용 체크 :" + boardList.get(0).getContent());

    }
}
