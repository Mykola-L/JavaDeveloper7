package com.system.management.project.dao.jdbc;

import com.system.management.project.dao.ICustomerDAO;
import com.system.management.project.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDAO implements ICustomerDAO {

    private final static String SAVE = "INSERT INTO customers (name) VALUES(?)";
    private final static String FIND_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private final static String UPDATE = "UPDATE customers SET name = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM customers WHERE id = ?";
    private final static String FIND_ALL = "SELECT * FROM  customers";
    private static final String GET_LAST_ID = "SELECT LAST_INSERT_ID()";

    private Connection connection;

    public JdbcCustomerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(Customer obj) {
        Long id;
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(SAVE);
             PreparedStatement preparedStatement2 = connection.prepareStatement(GET_LAST_ID)) {
            preparedStatement1.setString(1, obj.getName());
            preparedStatement1.execute();
            ResultSet resultSet = preparedStatement2.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = new Customer(id, "");
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = buildCustomersFromResultSet(resultSet);
            if (customers.size() > 0) {
                customer = customers.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public void update(Customer obj) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Customer obj) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> read() {
        List<Customer> customers;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            customers = buildCustomersFromResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    private static List<Customer> buildCustomersFromResultSet(ResultSet resultSet) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Customer customer;
        while (resultSet.next()) {
            customer = new Customer(resultSet.getLong("id"), resultSet.getString("name"));
            customers.add(customer);
        }
        return customers;
    }
}