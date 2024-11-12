package org.example.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Koneksi {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/MesinKasir");
        config.setUsername("root");
        config.setPassword("");
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static void close() throws SQLException{
        if (dataSource != null){
            dataSource.close();
        }
    }
}
