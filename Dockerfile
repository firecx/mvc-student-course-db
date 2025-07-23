# Этап сборки (Builder)
FROM maven:latest AS builder

# Установка рабочей директории
WORKDIR /app

# Копируем POM файл
COPY pom.xml .

# Скачиваем зависимости
RUN mvn dependency:go-offline

# Копируем исходный код
COPY src ./src

# Собираем проект и создаем WAR-файл
RUN mvn clean package -DskipTests

# Финальный этап с Tomcat
FROM tomcat:latest

# Удаляем стандартные приложения Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/docs
RUN rm -rf /usr/local/tomcat/webapps/examples
RUN rm -rf /usr/local/tomcat/webapps/host-manager
RUN rm -rf /usr/local/tomcat/webapps/manager

# Копируем собранный WAR-файл из этапа builder
COPY --from=builder /app/target/ROOT-0.0.1.war /usr/local/tomcat/webapps/ROOT.war

# Опционально: копируем конфигурации
# COPY config/tomcat/ /usr/local/tomcat/conf/

# # Открываем порт Tomcat
# EXPOSE 8080

# # Команда запуска Tomcat
# CMD ["catalina.sh", "run"]

# FROM tomcat:latest
# COPY target/ROOT-0.0.1 /usr/local/tomcat/webapps/ROOT