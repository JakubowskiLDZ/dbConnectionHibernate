package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Writer;
import pl.sdacademy.dbConnecton.service.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaWriterRepository implements AuthorRepository {
    @Override
    public Optional<Writer> findByFirstAndLastName(String firstName, String lastName) {
        EntityManager entityManager = HibernateEntityManagerFactory.get().createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Writer> writerquery = entityManager.createQuery("SELECT loc FROM Writer loc " +
                "WHERE loc.firstName =: firstName AND loc.lastName =: lastName", Writer.class);
        writerquery.setParameter("firstName", firstName);
        writerquery.setParameter("lastName", lastName);
        writerquery.setMaxResults(1);
        Writer foundWriter = writerquery.getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(foundWriter);
    }

    @Override
    public Optional<Writer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Writer save(Writer entity) {
        return null;
    }

    @Override
    public Writer update(Writer entity) {
        return null;
    }
}
