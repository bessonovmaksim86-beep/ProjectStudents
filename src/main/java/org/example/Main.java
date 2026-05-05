package org.example;



import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String csvFileName = "students_large.csv";
        String dbUrl = "jdbc:h2:./studentsdb";

        // 1. Генерируем данные (допустим, 1000 записей)
        CsvGenerator generator = new CsvGenerator();
        generator.generate(csvFileName, 1000);

        // 2. Подключаемся и импортируем
        try (Connection conn = DriverManager.getConnection(dbUrl, "sa", "")) {
            StudentDao dao = new StudentDaoImpl(conn);
            CsvReader reader = new CsvReader();

            dao.createTable();
            // Очистим старые данные, чтобы видеть чистый результат
            try (var stmt = conn.createStatement()) { stmt.execute("DELETE FROM students"); }

            // Читаем из свежесозданного файла
            List<Student> students = reader.readStudents(java.nio.file.Path.of(csvFileName));

            System.out.println("Начинаем импорт...");
            long start = System.currentTimeMillis();

            dao.insertBatch(students);

            long end = System.currentTimeMillis();
            System.out.println("Импорт завершен за " + (end - start) + " мс.");
            System.out.println("Всего записей в базе: " + dao.countAll());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Path getCsvPath() {
        try {
            URL resource = Main.class.getClassLoader().getResource("students.csv");
            if (resource == null) {
                throw new RuntimeException("Файл students.csv не найден в resources");
            }
            return Path.of(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Ошибка получения пути к students.csv", e);
        }
    }
}