package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Student> readStudents(Path path) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length != 8) {
                    System.out.println("Пропущена некорректная строка: " + line);
                    continue;
                }

                try {
                    Student student = new Student(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            Integer.parseInt(parts[5].trim()),
                            Integer.parseInt(parts[6].trim()),
                            Integer.parseInt(parts[7].trim())
                    );

                    students.add(student);
                } catch (NumberFormatException e) {
                    System.out.println("Пропущена строка с ошибкой числа: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения CSV-файла", e);
        }

        return students;
    }
}