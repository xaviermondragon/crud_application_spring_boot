FROM openjdk:11
#COPY . /usr/src/myapp
COPY ./target/solution-0.0.1-SNAPSHOT.jar /opt/app.jar
#WORKDIR /usr/src/myapp
#RUN javac Main.java
#CMD ["java", "Main"]



#docker build -t spring-boot-crud-app