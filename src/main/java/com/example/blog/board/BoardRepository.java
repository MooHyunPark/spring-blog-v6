package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor // final이 붙어있는 변수의 생성자를 만들어준다
@Repository
public class BoardRepository {

    // JPA는 EntityManager로 DB에 접근한다. (자바에서 DBConnection)
    private final EntityManager em;

    public List<Board> findAll() {
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class); // Board 클래스에 매핑하여 전달

        return q.getResultList();
    }

    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        q.setParameter(1, id); // 물음표 완성하기 (물음표 순서, 물음표에 바인딩 될 변수값)

        return (Board) q.getSingleResult();
    }

    public void save(Board board) {
        em.persist(board);
    }

    public void delete(int id) {
        Query q = em.createNativeQuery("delete from board_tb where id = ?");
        q.setParameter(1, id);
        q.executeUpdate();
    }

    public void update(int id, String title, String content) {
        Query q = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?");
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.setParameter(3, id);
        q.executeUpdate();
    }
}
