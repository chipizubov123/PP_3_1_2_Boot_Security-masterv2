package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User getUserByUsername(String username) {
        return em.find(User.class, username);
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void removeUserById(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    public List<User> listUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }
}
