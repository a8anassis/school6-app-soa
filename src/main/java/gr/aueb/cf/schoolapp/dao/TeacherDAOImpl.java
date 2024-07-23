package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO teachers (firstname, lastname) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // extract model info
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            int n = ps.executeUpdate();
            // logging
            return teacher; // TBD
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error in insert teacher: " + teacher);
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // extract model info
            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            int n = ps.executeUpdate();
            // logging
            return teacher; // TBD
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new TeacherDAOException("SQL error in insert teacher: " + teacher);
        }
    }

    @Override
    public void delete(Integer id) throws TeacherDAOException {

    }

    @Override
    public Teacher getById(Integer id) throws TeacherDAOException {
        return null;
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        return null;
    }
}
