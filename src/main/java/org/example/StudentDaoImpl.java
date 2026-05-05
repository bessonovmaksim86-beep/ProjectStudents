package org.example;

import java.sql.*;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    gender VARCHAR(50),
                    race_ethnicity VARCHAR(100),
                    parental_level_of_education VARCHAR(100),
                    lunch VARCHAR(50),
                    test_preparation_course VARCHAR(50),
                    math_score INT,
                    reading_score INT,
                    writing_score INT
                )
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания таблицы students", e);
        }
    }

    @Override
    public void insert(Student student) {
        String sql = """
                INSERT INTO students (
                    gender,
                    race_ethnicity,
                    parental_level_of_education,
                    lunch,
                    test_preparation_course,
                    math_score,
                    reading_score,
                    writing_score
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getGender());
            stmt.setString(2, student.getRaceEthnicity());
            stmt.setString(3, student.getParentalLevelOfEducation());
            stmt.setString(4, student.getLunch());
            stmt.setString(5, student.getTestPreparationCourse());
            stmt.setInt(6, student.getMathScore());
            stmt.setInt(7, student.getReadingScore());
            stmt.setInt(8, student.getWritingScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка вставки студента", e);
        }
    }

    @Override
    public void insertBatch(List<Student> students) {
        String sql = """
                INSERT INTO students (
                    gender,
                    race_ethnicity,
                    parental_level_of_education,
                    lunch,
                    test_preparation_course,
                    math_score,
                    reading_score,
                    writing_score
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Student student : students) {
                stmt.setString(1, student.getGender());
                stmt.setString(2, student.getRaceEthnicity());
                stmt.setString(3, student.getParentalLevelOfEducation());
                stmt.setString(4, student.getLunch());
                stmt.setString(5, student.getTestPreparationCourse());
                stmt.setInt(6, student.getMathScore());
                stmt.setInt(7, student.getReadingScore());
                stmt.setInt(8, student.getWritingScore());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка batch-вставки студентов", e);
        }
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM students";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подсчета записей", e);
        }
    }
}