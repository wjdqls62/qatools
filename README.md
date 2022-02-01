# QA-Tools

QA-Tools는 SpringBoot, JPA기반의 웹애플리케이션이다.
IntelliJ에서 아래와 같이 Jar를 생성하도록 한다.
Gradle → Tasks → build → bootJar 

/** Dockerfile 작성 **/

FROM openjdk:14
ADD qatools-0.0.1-SNAPSHOT.jar qatoos-0.1.jar
ENTRYPOINT ["java", "-jar", "qatoos-0.1.jar"]

/** build CMD **/
#docker build --tag qa-tools-0.1 .
#docker run
#docker run -p 8080:8080 -d qa-tools-0.1

MySQL, MS-SQL 연동가능
![qa-tools_cap1](https://user-images.githubusercontent.com/8287502/151955080-75bf100c-d84e-4799-b8e5-8914554118c4.png)
![grnerate_dummyData](https://user-images.githubusercontent.com/8287502/151955449-cfa775a5-1b9f-47dc-8cf6-6ed0d01f337d.png)
![modify_grade](https://user-images.githubusercontent.com/8287502/151955326-0c043d6c-9049-4998-b2b4-dc6b466dda23.png)
![modify_dept](https://user-images.githubusercontent.com/8287502/151955330-9a3f9583-19e9-4b79-a921-c5a212bc228e.png)
