package com.system.management.project.dao.jdbc;

import com.system.management.project.connections.IConnectionDB;
import com.system.management.project.dao.ICompanyDAO;
import com.system.management.project.dao.IDeveloperDAO;
import com.system.management.project.dao.IProjectDAO;
import com.system.management.project.factory.FactoryDao;
import com.system.management.project.models.Developer;
import com.system.management.project.models.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class JdbcDeveloperDAO implements IDeveloperDAO {

    private final static String SAVE = "INSERT INTO developers(name, company_id, project_id, salary) VALUES(?, ?, ?, ?)";
    private final static String SAVE_SKILLS = "INSERT developers_skills VALUES (?, ?)";
    private final static String FIND_BY_ID = "SELECT * FROM developers WHERE id = ?";
    private final static String UPDATE = "UPDATE developers SET name = ?, company_id = ?, project_id = ?, salary = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM developers WHERE id = ?";
    private final static String DELETE_SKILLS = "DELETE FROM developers_skills WHERE developer_id = ?";
    private final static String FIND_ALL = "SELECT * FROM developers";
    private final static String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";
    private final static String GET_SKILLS = "SELECT * FROM skills JOIN developers_skills  ON skills.id = developers_skills.skill_id " +
            "JOIN developers ON  developers_skills.developer_id = developers.id WHERE developers.id = ?";

    private ICompanyDAO companyDAO;
    private IProjectDAO projectDAO;
    private IConnectionDB connectionDB;

    public JdbcDeveloperDAO(IConnectionDB connectionDB) throws SQLException {
        this.connectionDB = connectionDB;
        companyDAO = FactoryDao.getCompanyDAO();
        projectDAO = FactoryDao.getProjectDAO();
    }

    @Override
    public Long create(Developer obj) {
        Connection connection = null;
        try {
            connection = connectionDB.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(SAVE)) {
                statement.setString(1, obj.getName());
                statement.setLong(2, obj.getCompany().getId());
                statement.setLong(3, obj.getProject().getId());
                statement.setInt(4, obj.getSalary());
                statement.executeUpdate();
            }
            long id;
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED);
                resultSet.next();
                id = resultSet.getLong(1);
            }
            try (PreparedStatement statement = connection.prepareStatement(SAVE_SKILLS)) {
                for (Skill skill : obj.getSkills()) {
                    statement.setLong(1, id);
                    statement.setLong(2, skill.getId());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
            connection.commit();
            return id;
        } catch (Exception ex) {
            rollbackTransaction(connection);
            throw new RuntimeException(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Developer findById(Long aLong) {
        try (Connection connection = connectionDB.getConnection()) {
            Developer developer = createNewEmptyDeveloper();
            developer.setId(aLong);
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
                preparedStatement.setLong(1, aLong);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    return developer;
                }
                developer = createDeveloper(resultSet);
            }
            HashSet<Skill> skills = createSkills(connection, developer);
            developer.setSkills(skills);
            return developer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Developer obj) {
        Connection connection = null;
        try {
            connection = connectionDB.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                statement.setString(1, obj.getName());
                statement.setLong(2, obj.getCompany().getId());
                statement.setLong(3, obj.getProject().getId());
                statement.setInt(4, obj.getSalary());
                statement.setLong(5, obj.getId());
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement(DELETE_SKILLS)) {
                statement.setLong(1, obj.getId());
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement(SAVE_SKILLS)) {
                for (Skill skill : obj.getSkills()) {
                    statement.setLong(1, obj.getId());
                    statement.setLong(2, skill.getId());
                    statement.addBatch();
                }
                statement.executeBatch();
            }
            connection.commit();
        } catch (Exception ex) {
            rollbackTransaction(connection);
            throw new RuntimeException(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Developer obj) {
        Connection connection = null;
        try {
            connection = connectionDB.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(DELETE_SKILLS)) {
                statement.setLong(1, obj.getId());
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
                statement.setLong(1, obj.getId());
                statement.executeUpdate();
            }
            connection.commit();
        } catch (Exception ex) {
            rollbackTransaction(connection);
            throw new RuntimeException(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Developer> read() {
        List<Developer> developers = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                Developer developer = createDeveloper(resultSet);
                HashSet<Skill> skills = createSkills(connection, developer);
                developer.setSkills(skills);
                developers.add(developer);
            }
            return developers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void rollbackTransaction(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Developer createDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer;
        developer = new Developer();
        developer.setId(resultSet.getLong("id"));
        developer.setName(resultSet.getString("name"));
        developer.setSalary(resultSet.getInt("salary"));
        developer.setCompany(companyDAO.findById(resultSet.getLong("company_id")));
        developer.setProject(projectDAO.findById(resultSet.getLong("project_id")));
        return developer;
    }

    private HashSet<Skill> createSkills(Connection connection, Developer developer) throws SQLException {
        HashSet<Skill> skills;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SKILLS)) {
            preparedStatement.setLong(1, developer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            skills = new HashSet<>();
            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getLong("id"));
                skill.setName(resultSet.getString("name"));
                skills.add(skill);
            }
        }
        return skills;
    }
}