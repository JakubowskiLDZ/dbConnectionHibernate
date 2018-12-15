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
            PreparedStatement preparedStatement = con.prepareStatement(
                    "SELECT id AS mojeId, firstName, lastName FROM Author " +
                    "WHERE firstName = ? AND lastName = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long authorId = rs.getLong("mojeId");
                String authorName = rs.getString("firstName");
                String authorLastName = rs.getString(3); // only to see how index is working
                foundAuthor = new Author(authorId, authorName, authorLastName);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundAuthor);
    }

    @Override
    public Optional<Author> findById(Long id) {
        Author foundAuthor = null;
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id, firstname, lastname FROM Author WHERE id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
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
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO Author(firstName, lastName) VALUES (?, ?)");
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.execute();

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
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement statement = con.prepareStatement(
                    "UPDATE Author SET firstName = ?, lastName = ? WHERE id = ?");
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setLong(3, entity.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
