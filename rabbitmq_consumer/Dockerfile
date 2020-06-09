FROM jdk1.8
LABEL auther=wwh
# 指定时区
# RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
EXPOSE 9999
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
