# Используем официальный образ Tomcat в качестве базового
FROM tomcat:10.1.24

# Устанавливаем curl, чтобы скачать wait-for-it
RUN apt-get update && apt-get install -y curl

# Скачиваем wait-for-it
RUN curl -o /usr/local/bin/wait-for-it https://github.com/vishnubob/wait-for-it/releases/download/v2.5.0/wait-for-it-linux-amd64 && chmod +x /usr/local/bin/wait-for-it

# Удаляем стандартное приложение, которое идет с образом Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Копируем наш WAR файл в папку с приложениями Tomcat
COPY target/TennisScoreboard-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Открываем порт 8080
EXPOSE 8080

# Запускаем Tomcat с ожиданием базы данных
CMD wait-for-it db:5432 -- catalina.sh run
