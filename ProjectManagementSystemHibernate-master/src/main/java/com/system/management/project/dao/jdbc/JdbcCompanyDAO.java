package com.system.management.project.dao.jdbc;

import com.system.management.project.connections.IConnectionDB;
import com.system.management.project.dao.ICompanyDAO;
import com.system.management.project.models.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCompanyDAO implements ICompanyDAO {

    private final static String SAVE = "INSERT INTO companies (name) VALUES(?)";
    private final static String FIND_BY_ID = "SELECT * FROM companies WHERE id = ?";
    private final static String UPDATE = "UPDATE companies SET name = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM companies WHERE id = ?";
    private final static String FIND_ALL = "SELECT * FROM  companies";
    private final static String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";

    private IConnectionDB connectionDB;

    public JdbcCompanyDAO(IConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public Long create(Company obj) {
        Long id;
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
             Statement statement = connection.createStatement()) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED);
            resultSet.next();
            id = resultSet.getLong(1);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company findById(Long aLong) {
        Company foundedCompany = new Company(aLong, "");
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundedCompany = new Company(resultSet.getLong("id"),
                        resultSet.getString("name"));
            }
            return foundedCompany;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Company company) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setLong(2, company.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Company obj) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Company> read() {
        List<Company> allCompanies = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allCompanies.add(new Company(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
            return allCompanies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}