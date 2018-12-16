package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.LibraryUser;
import pl.sdacademy.dbConnecton.service.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class IplLibraryUserRepository implements UserRepository {
    @Override
    public Optional<LibraryUser> findByUsernameAndPassword(String username, String password) {

        EntityManager em = HibernateEntityManagerFactory.get().createEntityManager();
        em.getTransaction().begin();

        TypedQuery<LibraryUser> selectQuery = em.createQuery("SELECT lu FROM LibraryUser lu "+
                        "WHERE lu.username =:un AND lu.password =: pass", LibraryUser.class);

        selectQuery.setParameter("un", username);
        selectQuery.setParameter("pass", password);
        selectQuery.setMaxResults(1);
        LibraryUser foundUser = selectQuery.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(foundUser);
    }

    @Override
    public Optional<LibraryUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public LibraryUser save(LibraryUser entity) {
        return null;
    }

    @Override
    public LibraryUser update(LibraryUser entity) {
        return null;
    }
}
