# =========================
# Build stage
# =========================
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

COPY src ./src
RUN ./mvnw clean package -DskipTests


# =========================
# Runtime stage
# =========================
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

# Cria usuário não-root
RUN groupadd spring && useradd -g spring spring

COPY --from=builder /app/target/*.jar app.jar
RUN chown spring:spring app.jar

USER spring:spring

EXPOSE 8080

ENV JAVA_OPTS="-Xms256m -Xmx512m \
               -XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75 \
               --enable-native-access=ALL-UNNAMED"

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
