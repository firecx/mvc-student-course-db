# Используем официальный образ OpenJDK
FROM eclipse-temurin:21-jdk-jammy

# Создаем рабочую директорию
WORKDIR /

# Копируем JAR-файл в контейнер
COPY target/mvc-0.1.0.jar app.jar

# Открываем порт, на котором работает приложение
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]