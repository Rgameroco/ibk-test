FROM openjdk:17-alpine
LABEL authors="Beto"
# Usar una base de imagen oficial de Java
# Variable de entorno para almacenar el puerto por defecto
ENV PORT 8080

# Exponer el puerto donde corre la aplicación
EXPOSE $PORT

# Copiar el archivo jar de la aplicación al contenedor
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# Configura la JVM para permitir debugging remoto
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
ENTRYPOINT ["java", "-jar", "app.jar"]