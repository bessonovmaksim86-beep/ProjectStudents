 Убедитесь, что установлен Java 17+ и Maven.
 Поместите файл students.csv в src/main/resources/.
 Запустите проект командой:   mvn clean compile exec:java
 После запуска в консоли будет выведено количество импортированных записей.
 Для проверки можно открыть H2 Console или выполнить запрос:  SELECT COUNT(*) FROM students;
