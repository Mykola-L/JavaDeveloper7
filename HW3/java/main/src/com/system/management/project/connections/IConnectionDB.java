package com.system.management.project.connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionDB {

    Connection getConnection() throws SQLException;
}