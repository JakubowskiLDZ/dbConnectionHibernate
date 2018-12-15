package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Author;
import pl.sdacademy.dbConnecton.service.repository.AuthorRepository;

import java.sql.*;
import java.util.Optional;

public class JdbcAuthorRepository implements AuthorRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "developer";
    private static final String DB_PASSWORD = "developer";

    @Override
    public Optional<Author> findByFirstAndLastName(String firstName, String lastName) {
        Author foundAuthor = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id AS mojeId, firstName, lastName " +
                    "FROM author " +
                    "WHERE firstName = '" + firstName + "' " +
                    "AND lastName = '" + lastName + "'");
            if (rs.next()) {
                Long authorId = rs.getLong("mojeId");
                String authorName = rs.getString("firstName");
                String authorLastName = rs.getString(3);
                foundAuthor = new Author(authorId, authorName, authorLastName);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundAuthor);
    }

    @Override
    public Optional<Author> findById(Long id) {
        Author foundAuthor = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, firstname, lastname FROM Author WHERE id = " + id);
            if (rs.next()) {
                Long authorId = rs.getLong("id");
                String authorName = rs.getString("firstname");
                String authorLastName = rs.getString("lastname");
                foundAuthor = new Author(authorId, authorName, authorLastName);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundAuthor);
    }

    @Override
    public Author save(Author entity) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO Author(firstName, lastName) " +
                    "VALUES ('" + entity.getLastName() + "', '" + entity.getLastName() + "')");
            ResultSet rs = statement.executeQuery("SELECT MAX(id) as maxId FROM Author");
            if (rs.next()) {
                Long authorId = rs.getLong("maxId");
                entity.setId(authorId);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Author update(Author entity) {
        return null;
    }
}
