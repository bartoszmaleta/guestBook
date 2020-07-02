package com.company.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConnection {
    private static BasicDataSource basicDataSource;

    public DataSourceConnection() {
        basicDataSource = new BasicDataSource();

        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/guestbook");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(10);
        basicDataSource.setMaxOpenPreparedStatements(100);
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = basicDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
