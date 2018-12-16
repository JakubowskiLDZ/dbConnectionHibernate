package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.BookBorrow;
import pl.sdacademy.dbConnecton.service.repository.BookBorrowRepository;

import java.util.List;
import java.util.Optional;

public class JpaBookBorrowRepository implements BookBorrowRepository {
    @Override
    public Optional<BookBorrow> findByUserIdAndBookId(Long id, Long bookId) {
        return Optional.empty();
    }

    @Override
    public boolean isBookBorrowed(Long bookId) {
        return false;
    }

    @Override
    public List<BookBorrow> findByUserId(Long readerNumber) {
        return null;
    }

    @Override
    public Optional<BookBorrow> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public BookBorrow save(BookBorrow entity) {
        return null;
    }

    @Override
    public BookBorrow update(BookBorrow entity) {
        return null;
    }
}
