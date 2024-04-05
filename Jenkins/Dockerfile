# 使用OpenJDK 17
FROM openjdk:17-alpine
# 设置工作目录 (容器内路径)
WORKDIR /root
# 相对于工作目录
ADD demo*.jar /root/app.jar
# 指定默认执行程序和参数
ENTRYPOINT ["java","-jar","/root/app.jar"]