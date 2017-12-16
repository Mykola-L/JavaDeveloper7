package com.system.management.project.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql implements IConnectionDB {

    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/projectmanagementdb?autoReconnect=true&useSSL=false";
    private static ConnectionMySql connectionMySql;

    private ConnectionMySql() {
    }

    public static ConnectionMySql getInstatce() {
        if (connectionMySql == null) {
            connectionMySql = new ConnectionMySql();
        }
        return connectionMySql;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}