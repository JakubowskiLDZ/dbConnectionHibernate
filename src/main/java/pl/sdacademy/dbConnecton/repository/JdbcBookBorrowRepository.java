package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.BookBorrow;
import pl.sdacademy.dbConnecton.service.repository.BookBorrowRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookBorrowRepository implements BookBorrowRepository {
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
        List<BookBorrow> borrows = new ArrayList<>();
        try (Connection con = DataSourceProvider.getConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id, userId, bookId, borrowDate " +
                       "WHERE userId = ? AND returnDate IS NULL");
            statement.setLong(1, readerNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                BookBorrow borrowment = new BookBorrow(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getLong("bookId"),
                        rs.getTimestamp("borrowDate").toLocalDateTime(),
                        null
                );
                borrows.add(borrowment);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
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
