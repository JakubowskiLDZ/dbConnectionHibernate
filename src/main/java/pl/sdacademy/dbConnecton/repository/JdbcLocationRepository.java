package pl.sdacademy.dbConnecton.repository;

import pl.sdacademy.dbConnecton.model.Location;
import pl.sdacademy.dbConnecton.service.repository.LocationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcLocationRepository implements LocationRepository {
    @Override
    public Optional<Location> find(String rackSymbol, String shelfSymbol, Long position) {
        Location foundLocation = null;
        try (Connection con = DataSourceProvider.getConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT id, rackSymbol, shelfSymbol, position FROM Location " +
                    "WHERE rackSymbol = ? AND shelfSymbol = ? AND position = ?");
            statement.setString(1, rackSymbol);
            statement.setString(2, shelfSymbol);
            statement.setLong(3, position);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                foundLocation = new Location(
                        rs.getLong("id"),
                        rs.getString("rackSymbol"),
                        rs.getString("shelfSymbol"),
                        rs.getLong("position"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(foundLocation);
    }

    @Override
    public Optional<Location> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Location save(Location entity) {
        return null;
    }

    @Override
    public Location update(Location entity) {
        return null;
    }
}
