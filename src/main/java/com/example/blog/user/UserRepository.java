package com.example.blog.user;

import com.example.blog._core.util.error.ex.Exception401;
import com.example.blog.board.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;


    public User findByUsername(String username) {
        Query q = em.createQuery("select u from User u where u.username = :username", User.class);
        q.setParameter("username", username);

        try {
            return (User) q.getSingleResult();
        } catch (RuntimeException e) {
            throw new Exception401("아이디 혹은 패스워드가 일치하지 않습니다.");
        }
    }

    public User save(User user) {
        em.persist(user);
        return user;
    }
}
