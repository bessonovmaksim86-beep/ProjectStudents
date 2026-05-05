package org.example;

import java.util.List;

public interface StudentDao {
    void createTable();
    void insert(Student student);
    void insertBatch(List<Student> students);
    int countAll();
}