
$ cd demo      
$ gradle build   
$ java -jar buid/libs/*.jar -Dserver.port:8080  
or  
$ gradle bootRun  

You will also be able to test the Spring Boot sample code with Docker

docker run -p 8080:8080 eothmal/springboot-demo-docker

I.    Original Code         : Test with Postman: GET/POST http://localhost:8080/pre/  (original code, without H2).

II.   Add H2 database       : Test with Postman: GET/POST http://localhost:8080/students ...etc  (original code, with H2 - you can find iin the bottom about H2-console).

III.  Add UI with Thymeleaf : Test with Browser: http://localhost:8080 (with frames) or http://localhost:8080/base/all (without frames).


I & II
POSTMAN Test:

The original code without H2 database by adding prefix in the url with /pre
    example: http://localhost:8080/pre/students

1. List of Students:

GET http://localhost:8080/students

2. List of Courses:

GET http://localhost:8080/courses

3. Retrieve by studentid

GET http://localhost:8080/students/1

4. Retrieve by course id

GET http://localhost:8080/courses/JSB102

5. Add Student

POST http://localhost:8080/students
  
To make a request with JSON, the appropriate HTTP headers are:  
Content-Type: application/json  
Accept: application/json  
  
Body(Json):
 {
        "firstName": "Edwin",
        "lastName": "Okay",
        "email": "eokay@outmail.com",
        "studentId": "5"
    }

6. Add Course

POST http://localhost:8080/courses

To make a request with JSON, the appropriate HTTP headers are:  
Content-Type: application/json  
Accept: application/json  
  
Body(Json):

    {
        "name": "Python",
        "offeredBy": "Irving",
        "courseId": "PYT101"
    }
`
7. Retrieve Courses by student id

GET http://localhost:8080/students/4/courses

8. Retrieve Students by course id

GET http://localhost:8080/courses/JSB102/students

9. List of Enroll 

GET http://localhost:8080/enroll

10. Add Enrollment

POST http://localhost:8080/enroll

Body(Json):
 {
        "studentId": "2",
        "courseId": "JSB102"
 }

`
`
Access H2 console:
http://localhost:8080/h2-console

setup:
JDBC URL: jdbc:h2:mem:mydb

