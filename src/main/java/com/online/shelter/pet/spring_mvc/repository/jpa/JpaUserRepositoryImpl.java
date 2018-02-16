package com.online.shelter.pet.spring_mvc.repository.jpa;

import com.online.shelter.pet.spring_mvc.model.User;
import com.online.shelter.pet.spring_mvc.repository.UserRepository;
import org.hibernate.jpa.QueryHints;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {
    /*
    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }
*/
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        if(user.isNew()){
            entityManager.persist(user);
            return user;
        }else
            return entityManager.merge(user);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
       /*      User ref = em.getReference(User.class, id);
        em.remove(ref);
*/
        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = entityManager.createNamedQuery(User.BY_EMAIL, User.class)
                .setParameter(1, email)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return (User) DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
