package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CsvGenerator {

    private static final String[] GENDERS = {"male", "female"};
    private static final String[] GROUPS = {"group A", "group B", "group C", "group D", "group E"};
    private static final String[] EDUCATION = {"some high school", "high school", "some college", "associate's degree", "bachelor's degree", "master's degree"};
    private static final String[] LUNCH = {"standard", "free/reduced"};
    private static final String[] COURSES = {"none", "completed"};

    public void generate(String filePath, int rowCount) {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Записываем заголовок
            writer.write("gender,race/ethnicity,parental level of education,lunch,test preparation course,math score,reading score,writing score");
            writer.newLine();

            for (int i = 0; i < rowCount; i++) {
                String line = String.format("%s,%s,%s,%s,%s,%d,%d,%d",
                        GENDERS[random.nextInt(GENDERS.length)],
                        GROUPS[random.nextInt(GROUPS.length)],
                        EDUCATION[random.nextInt(EDUCATION.length)],
                        LUNCH[random.nextInt(LUNCH.length)],
                        COURSES[random.nextInt(COURSES.length)],
                        random.nextInt(101), // Math score 0-100
                        random.nextInt(101), // Reading score 0-100
                        random.nextInt(101)  // Writing score 0-100
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Файл успешно сгенерирован: " + filePath + " (" + rowCount + " строк)");
        } catch (IOException e) {
            System.err.println("Ошибка при генерации CSV: " + e.getMessage());
        }
    }
}
