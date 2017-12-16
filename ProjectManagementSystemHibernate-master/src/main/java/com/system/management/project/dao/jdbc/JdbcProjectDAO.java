package com.system.management.project.dao.jdbc;

import com.system.management.project.dao.ICompanyDAO;
import com.system.management.project.dao.ICustomerDAO;
import com.system.management.project.dao.IProjectDAO;
import com.system.management.project.factory.FactoryDao;
import com.system.management.project.models.Company;
import com.system.management.project.models.Customer;
import com.system.management.project.models.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProjectDAO implements IProjectDAO {
    private final static String SAVE = "INSERT INTO projects (name, cost, company_id, customer_id) VALUES (?, ?, ?, ?) ";
    private final static String FIND_BY_ID = "SELECT * FROM projects WHERE id = ?";
    private final static String UPDATE = "UPDATE projects SET name = ?, cost = ?, company_id = ? , customer_id = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM projects WHERE id = ?";
    private final static String FIND_ALL = "SELECT * FROM projects";
    private final static String GET_LAST_ID = "SELECT LAST_INSERT_ID()";

    private ICompanyDAO companyDAO;
    private ICustomerDAO customerDAO;
    private Connection connection;

    public JdbcProjectDAO(Connection connection) throws SQLException {
        this.connection = connection;
        companyDAO = FactoryDao.getCompanyDAO();
        customerDAO = FactoryDao.getCustomerDAO();
    }

    @Override
    public Long create(Project project) {
        Long id = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
             PreparedStatement preparedStatementGetLastId = connection.prepareStatement(GET_LAST_ID)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCost());
            preparedStatement.setLong(3, project.getCompany().getId());
            preparedStatement.setLong(4, project.getCustomer().getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatementGetLastId.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred while trying to create Customer: "
                    + project.getName() + "\n" + e);
        }
        return id;
    }

    @Override
    public Project findById(Long id) {
        Project project =
                new Project(id, "", 0, new Company(0, ""), new Customer(0, ""));
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projects = buildProjectsFromResultSet(resultSet);
            if (projects.size() > 0) {
                project = projects.get(0);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred while  trying to retrieve " +
                    "Project with Name: " + id);
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public void update(Project project) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, project.getCost());
            preparedStatement.setLong(3, project.getCompany().getId());
            preparedStatement.setLong(4, project.getCustomer().getId());
            preparedStatement.setLong(5, project.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred while trying to update Project with ID: "
                    + project.getId() + "\n" + e);
        }
    }

    @Override
    public void delete(Project project) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, project.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred while trying to delete Project whith ID: "
                    + project.getId());
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> read() {
        List<Project> projects = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            projects = buildProjectsFromResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL exception occurred while trying to find all Projects:");
            e.printStackTrace();
        }
        return projects;
    }

    private List<Project> buildProjectsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Project> projects = new ArrayList<>();
        Company company;
        Customer customer;
        long id;
        String name;
        int cost;
        while (resultSet.next()) {
            id = resultSet.getLong("id");
            name = resultSet.getString("name");
            cost = resultSet.getInt("cost");
            company = companyDAO.findById(resultSet.getLong("company_id"));
            customer = customerDAO.findById(resultSet.getLong("customer_id"));
            projects.add(new Project(id, name, cost, company, customer));
        }
        return projects;
    }
}