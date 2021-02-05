package users.dao;

import org.springframework.stereotype.Repository;
import users.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("From User").getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
/*        entityManager.createQuery("delete from User where id = ?1")
                .setParameter(1, id).executeUpdate();*/
        User user = entityManager.find(User.class, new Long(id));
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class, new Long(id));
        entityManager.detach(user);
        return user;
    }
}
