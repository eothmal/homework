if you have docker installed, you can try running with this command:

docker run -p 8080:8080 eothmal/springboot-demo

You can still try the old path without H2 database by adding prefix in the url with /pre
    example: http://localhost:8080/pre/students

1. List of Students:

GET http://localhost:8080/students

2. List of Courses:

GET http://localhost:8080/students

3. Retrieve by studentid

GET http://localhost:8080/students/1

4. Retrieve by course id

GET http://localhost:8080/courses/JSB102

5. Add Student

POST http://localhost:8080/students

Body(Json):
 {
        "firstName": "Edwin",
        "lastName": "Okay",
        "email": "eokay@outmail.com",
        "studentId": "5"
    }

6. Add Course

Body(Json):

    {
        "name": "Python",
        "offeredBy": "Irving",
        "courseId": "PYT101"
    }
`
7. Retrieve a list of courses by student id

GET http://localhost:8080/students/4/courses

8. Retrieve a list of students by course id

GET http://localhost:8080/courses/JSB102/students

9. List of Enroll 

GET http://localhost:8080/enroll

10. Add Enrollment

POST http://localhost:8080/enroll

Body(Json):
 {
     "id": 4,
        "studentId": "2",
        "courseId": "JSB102"
    }



H2 Console
http://localhost:8080/h2-console

setup:
JDBC URL: jdbc:h2:mem:mydb

