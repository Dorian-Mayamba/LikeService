FROM amazoncorretto:17 AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build -x test --no-daemon

FROM amazoncorretto:17

COPY --from=builder /app/build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]