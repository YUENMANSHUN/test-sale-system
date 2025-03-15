# 使用官方 OpenJDK 作為基礎映像
FROM openjdk:17-jdk-slim

# 設置工作目錄
WORKDIR /app

# 複製 JAR 文件到容器中
COPY target/sale_system-0.0.1.jar app.jar

# 暴露應用程序的默認端口
EXPOSE 8080

# 啟動 Spring Boot 應用
CMD ["java", "-jar", "app.jar"]
