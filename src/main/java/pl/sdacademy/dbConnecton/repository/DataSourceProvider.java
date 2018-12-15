package pl.sdacademy.dbConnecton.repository;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceProvider {

    private static final DataSource DATA_SOURCE = createDataSource();

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    private static DataSource createDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
        dataSource.setUser("developer");
        dataSource.setPassword("developer");
        return dataSource;
    }
}
