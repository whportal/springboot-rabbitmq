FROM jdk1.8
LABEL auther=wwh
# 指定时区
# RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
# 若要根据参数动态切换要启动的环境可使用下面的配置构建镜像，在启动容器时通过环境变量将SPRING_PROFILES_ACTIVE传递过来
# 如 docker run -it -e SPRING_PROFILES_ACTIVE=test 镜像名称
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-jar","/app.jar"]