package com.system.management.project.dao.jdbc;

import com.system.management.project.connections.IConnectionDB;
import com.system.management.project.dao.ISkillDAO;
import com.system.management.project.models.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSkillDAO implements ISkillDAO {

    private final static String SAVE = "INSERT INTO skills (name) VALUES (?)";
    private final static String FIND_BY_ID = "SELECT * FROM skills WHERE id = ?";
    private final static String UPDATE = "UPDATE skills SET name = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM skills WHERE id = ?";
    private final static String FIND_ALL = "SELECT * FROM skills";
    private final static String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";

    private IConnectionDB connectionDB;

    public JdbcSkillDAO(IConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }

    @Override
    public Long create(Skill obj) {
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
    public Skill findById(Long aLong) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return new Skill(aLong, "");
            }
            return createSkill(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Skill obj) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setLong(2, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Skill obj) {
        try (Connection connection = connectionDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Skill> read() {
        List<Skill> skillList = new ArrayList<>();
        try (Connection connection = connectionDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            while (resultSet.next()) {
                Skill skill = createSkill(resultSet);
                skillList.add(skill);
            }
            return skillList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Skill createSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setName(resultSet.getString("name"));
        return skill;
    }
}