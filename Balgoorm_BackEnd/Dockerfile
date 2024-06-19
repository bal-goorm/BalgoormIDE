# Stage 1: Build the application
FROM gradle:7.3.3-jdk17 as build

# 작업 디렉토리를 설정합니다.
WORKDIR /home/gradle/app

# Gradle 캐시를 활용하기 위해 필요한 파일들을 복사합니다.
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# 필요한 Gradle 의존성을 다운로드합니다.
RUN gradle build -x test --stacktrace

# 나머지 소스 코드들을 복사합니다.
COPY src ./src

# 애플리케이션을 빌드합니다.
RUN gradle bootJar --stacktrace

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# 작업 디렉토리를 설정합니다.
WORKDIR /app

# 빌드 결과물을 복사합니다.
COPY --from=build /home/gradle/app/build/libs/*.jar app.jar

# 컨테이너를 시작할 때 JAR 파일을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]

# 애플리케이션이 사용하는 포트를 노출합니다.
EXPOSE 8080
