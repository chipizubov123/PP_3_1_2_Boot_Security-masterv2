package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.Models.Role;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private EntityManager em;

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(em.createQuery("from Role", Role.class).getResultList());
    }

    @Override
    public Role getRoleByUsername(String username) {
        return em.createQuery("from Role where name = :username", Role.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public Set<Role> getSetOfRoles(String[] roles) {
        return getAllRoles();
    }

    @Override
    public void add(Role role) {
        em.persist(role);
    }

    @Override
    public void edit(Role role) {
        em.merge(role);
    }

    @Override
    public Role getById(Long id) {
        return em.find(Role.class, id);
    }
}
