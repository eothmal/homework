
$ cd demo      
$ gradle build   
$ java -jar buid/libs/*.jar -Dserver.port:8080  
or  
$ gradle bootRun  

You will also be able to test the Spring Boot sample code with Docker

docker run -p 8080:8080 eothmal/springboot-demo-docker

Example: URL = http://localhost:8080  
         or     
         URL =  https://8080-cyan-jackal-v3zi2ptk.ws-us03.gitpod.io  

I.    Original Code         : Test with Postman: GET/POST URL/pre/  (original code, without H2).

II.   Add H2 database       : Test with Postman: GET/POST URL/students ...etc  (original code, with H2 - you can find iin the bottom about H2-console).

III.  Add UI with Thymeleaf : Test with Browser: URL (with frames) or URL/base/all (without frames).


I & II
POSTMAN Test:

The original code without H2 database by adding prefix in the url with /pre
    example: URL/pre/students

1. List of Students:

GET URL/students

2. List of Courses:

GET URL/courses

3. Retrieve by studentid

GET URL/students/1

4. Retrieve by course id

GET URL/courses/JSB102

5. Add Student

POST URL/students
  
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

POST URL/courses

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

GET URL/students/4/courses

8. Retrieve Students by course id

GET URL/courses/JSB102/students

9. List of Enroll 

GET URL/enroll

10. Add Enrollment

POST URL/enroll

Body(Json):
 {
        "studentId": "2",
        "courseId": "JSB102"
 }

`
`
Access H2 console:
URL/h2-console

setup:
JDBC URL: jdbc:h2:mem:mydb

