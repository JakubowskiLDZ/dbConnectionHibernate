package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Book;
import pl.sdacademy.dbConnecton.service.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BookRepository implements pl.sdacademy.dbConnecton.service.repository.BookRepository {

    @Override
    public List<Book> findByBorrowingUserId(Long id) {

        return null;
    }

    @Override
    public List<Book> findByTitleAndAuthorLastName(String title, String author) {
        EntityManager em = HibernateEntityManagerFactory.get().createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Book> selectQuery = em.createQuery("SELECT b FROM Book b LEFT JOIN b.authors a " +
                "WHERE a.lastName LIKE :lastName AND b.name LIKE :bookName", Book.class);
        selectQuery.setParameter("lastName", "%" + author + "%");
        selectQuery.setParameter( "bookName","%" + title + "%");
        List<Book> foundBooks = selectQuery.getResultList();

        em.getTransaction().commit();
        em.close();
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Book save(Book entity) {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }
}
