package com.example.blog.board;

import com.example.blog._core.util.error.ex.Exception400;
import com.example.blog._core.util.error.ex.Exception404;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor // final이 붙어있는 변수의 생성자를 만들어준다
@Repository
public class BoardRepository {

    // JPA는 EntityManager로 DB에 접근한다. (자바에서 DBConnection)
    private final EntityManager em;

    public List<Board> findAll() {
        return em.createQuery("select b from Board b order by b.id desc", Board.class)
                .getResultList();
    }

    // 고유키로 조회하는 경우 find 메소드만 사용해도 찾을 수 있다.
    public Optional<Board> findById(int id) {
        // null 처리를 하기 위해 Optional 처리를 하였음
        return Optional.ofNullable(em.find(Board.class, id));
    }

    public void save(Board board) {
        // 비영속
        em.persist(board);
        // 동기화 완료 (영속화됨)
    }

    public void delete(int id) {
        em.createQuery("delete from Board b where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    // update는 작성하지 않아도 된다.


    // 기존의 findById 메서드를 대신하는 내용
    public Optional<Board> findByIdJoinUser(int id) {
        String sql = """
                select b from Board b join fetch b.user where b.id = :id
                """;
        Query q = em.createQuery(sql, Board.class);
        q.setParameter("id", id);
        try {
            Board board = (Board) q.getSingleResult();
            return Optional.ofNullable(board);
        } catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }

    }

    // 기존의 findById 메서드를 대신하는 내용
    public Optional<Board> findByIdJoinUserAndReply(int id) {
        String sql = """
                select b from Board b join fetch b.user left join fetch b.replies r left join fetch r.user where b.id = :id
                """;
        Query q = em.createQuery(sql, Board.class);
        q.setParameter("id", id);
        try {
            Board board = (Board) q.getSingleResult();
            return Optional.ofNullable(board);
        } catch (RuntimeException e) {
            return Optional.ofNullable(null);
        }

    }
}
