
<!-- PROJECT LOGO -->

<div align="center">

  <h1 align="center">Spring Boot 3 and Spring Security 6</h1>

  <p align="center">
   CRUD (Create, Read, Update, Delete), JWT Authentication and Authorization Maven Project!
    <br />
  </p>
</div>

<a name="readme-top"></a>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
          <a href="#getting-started">Code Started</a>
          <ul>
	    <li><a href="#signup">Signup</a></li>
	    <li><a href="#signin">Signin</a></li>
	    <li><a href="#getall">Get All</a></li>
	    <li><a href="#get">Get</a></li>
	    <li><a href="#update">Update</a></li>
	    <li><a href="#delete">Delete</a></li>
	    <li><a href="#refreshtoken">Refresh Token</a></li>
	    <li><a href="#errorresponse">Error Response</a></li>
          </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
<a name="about-the-project"></a>
## About The Project 

This is a sample CRUD application built using Spring Boot 3 and secured with Spring Security 6. The project's main goal is to demonstrate how to create a web application that allows users to perform CRUD operations on a set of entities (e.g., "Tasks," "Products," "Customers") while ensuring proper authentication and authorization through Spring Security.

### Key Features

1. User Registration and Authentication: Users can register for accounts, log in, and log out. Passwords are securely hashed and stored in the database.
2. Role-Based Access Control: The application supports two user roles, "User" and "Admin." Users have limited access, while Admins have full control over CRUD operations.
3. CRUD Operations: Users with appropriate permissions can perform Create, Read, Update, and Delete operations on the entities in the system.
4. Database Storage: Data is stored in a relational database (e.g., MySQL, PostgreSQL, H2) using Spring Data JPA.
5. RESTful API: The application exposes a RESTful API for interacting with the entities, making it easy to integrate with other systems or build front-end interfaces.

### Technologies Used

* Spring Boot
* Spring Security
* Spring Data JPA
* Java 17
* Database (e.g., MySQL, PostgreSQL, H2)
* RESTful API
* Maven or Gradle for dependency management
* Angular, React, or other front-end technologies (if using web-based views)

<a name="prerequisites"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- GETTING STARTED -->
### Prerequisites 

Creating a Maven project involves a few prerequisite steps to set up your development environment correctly. Here are the key steps you should follow before creating a Maven project.
* Maven is a Java-based build tool, so you need to have Java installed on your system. You can download and install the latest version of the Java Development Kit (JDK) from the Oracle website or use an open-source alternative like OpenJDK.
* Ensure that the JAVA_HOME environment variable is set to your JDK installation directory.
* Add the bin directory of the JDK to your system's PATH variable.
* Mmost developers prefer to use an Integrated Development Environment (IDE) like Eclipse, IntelliJ IDEA, or NetBeans for Java development.
* If you're using an IDE, make sure to install the necessary plugins or extensions for Maven support.

<a name="installation"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
### Installation 

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Clone the repo
   ```sh
   git clone https://github.com/saronila/springboot3-springsecurity6-rsk.git
   ```
2. Enable/disable header validation settings in `Application.properties` by default false
   ```js
   app.request.header.validation=false;
   ```

<a name="signup"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- SIGNUP CODE STARTED -->
## Signup 

1. Signup url like this http://localhost:xxxx/api/v1/auth/signup
2. Add Header "Authorization-Owner" value is "myrequest" optional 
3. Method type is POST

### Request Body
```sh
{
	"firstName":"Saravanakumar",
	"lastName":"Ramasamy",
	"email":"rskzoniac+admin@gmail.com",
	"password":"12345",
	"role": "ADMIN"
}
```

### Response Body
```sh
{
    "status": 1,
    "message": "success",
    "data": {
        "payload": {
            "id": 1,
            "firstName": "Saravanakumar",
            "lastName": "Ramasamy",
            "email": "rskzoniac+admin@gmail.com",
            "password": "$2a$10$9hXBsQh14kz7MadXYhr1qeaUfDT9hevG54Pf1DZulvlcURCl80U7a",
            "role": "ADMIN",
            "enabled": true,
            "accountNonLocked": true,
            "authorities": [
                {
                    "authority": "ADMIN"
                }
            ],
            "username": "rskzoniac+admin@gmail.com",
            "accountNonExpired": true,
            "credentialsNonExpired": true
        }
    },
    "responseTime": "11-10-2023 06:11:46"
}
```

<a name="signin"></a> <p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- SIGNIN CODE STARTED -->
## Signin 

1. Signup url like this http://localhost:xxxx/api/v1/auth/signin
2. Add Header "Authorization-Owner" value is "myrequest" optional 
3. Method type is POST

### Request Body
```sh
{
    "email":"rskzoniac+admin@gmail.com",
    "password":"12345"
}
```

### Response Body
```sh
{
    "status": 1,
    "message": "success",
    "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs",
        "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTc2MDk1ODV9.oKnNkSgqrgvVJYENWxSP9yoX27DLXuB68HKPAf6uOLw"
    },
    "responseTime": "11-10-2023 06:13:05"
}
```

<a name="getall"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- GET ALL CODE STARTED -->
## Get All

1. get all url like this "http://localhost:xxxx/api/v1/users"
2. Add Header "Authorization-Owner" value is "myrequest" optional 
3. Method type is GET
4. Add Header "Authorization" value is "bearer #ACCESSTOKEN#" and replace your accessToken here

### Response Body
```sh
{
    "status": 1,
    "message": "success",
    "data": {
        "list": [
            {
                "id": 1,
                "firstName": "Saravanakumar",
                "lastName": "Ramasamy",
                "email": "rskzoniac+admin@gmail.com",
                "password": "$2a$10$9hXBsQh14kz7MadXYhr1qeaUfDT9hevG54Pf1DZulvlcURCl80U7a",
                "role": "ADMIN",
                "enabled": true,
                "accountNonLocked": true,
                "authorities": [
                    {
                        "authority": "ADMIN"
                    }
                ],
                "username": "rskzoniac+admin@gmail.com",
                "accountNonExpired": true,
                "credentialsNonExpired": true
            },
            {
                "id": 2,
                "firstName": "user",
                "lastName": "G",
                "email": "rskzoniac+user@gmail.com",
                "password": "$2a$10$HbjngbUVFRc4urQfkTKSvuyE7nhO6vJMo8pzSopXyE4px8e/OjYXi",
                "role": "USER",
                "enabled": true,
                "accountNonLocked": true,
                "authorities": [
                    {
                        "authority": "USER"
                    }
                ],
                "username": "rskzoniac+user@gmail.com",
                "accountNonExpired": true,
                "credentialsNonExpired": true
            }
        ]
    },
    "responseTime": "11-10-2023 06:32:34"
}
```

<a name="get"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- GET CODE STARTED -->
## Get

1. get url like this "http://localhost:xxxx/api/v1/users/#ACCESSTOKEN#" and replace your primary key
2. Add Header "Authorization-Owner" value is "myrequest" optional 
3. Method type is GET
4. Add Header "Authorization" value is "bearer #ACCESSTOKEN#" and replace your accessToken here
 
### Response Body
```sh
{
    "status": 1,
    "message": "success",
    "data": {
        "payload": {
            "id": 1,
            "firstName": "Saravanakumar",
            "lastName": "Ramasamy",
            "email": "rskzoniac+admin@gmail.com",
            "password": "$2a$10$9hXBsQh14kz7MadXYhr1qeaUfDT9hevG54Pf1DZulvlcURCl80U7a",
            "role": "ADMIN",
            "enabled": true,
            "accountNonExpired": true,
            "credentialsNonExpired": true,
            "authorities": [
                {
                    "authority": "ADMIN"
                }
            ],
            "username": "rskzoniac+admin@gmail.com",
            "accountNonLocked": true
        }
    },
    "responseTime": "11-10-2023 09:54:20"
}
```

<a name="update"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- UPDATE CODE STARTED -->
## Update

1. update url like this "http://localhost:xxxx/api/v1/users/#ACCESSTOKEN#" and replace your primary key
2. Add Header "Authorization-Owner" value is "myrequest" optional 
3. Method type is PUT
4. Add Header "Authorization" value is "bearer #ACCESSTOKEN#" and replace your accessToken here

### Request Body
```sh
{
	"firstName":"Saravanakumar",
	"lastName":"R"
}
```

### Response Body
```sh
{
    "status": 1,
    "message": "success",
    "data": {
        "payload": {
            "id": 1,
            "firstName": "Saravanakumar",
            "lastName": "R",
            "email": "rskzoniac+admin@gmail.com",
            "password": "$2a$10$9hXBsQh14kz7MadXYhr1qeaUfDT9hevG54Pf1DZulvlcURCl80U7a",
            "role": "ADMIN",
            "enabled": true,
            "accountNonExpired": true,
            "credentialsNonExpired": true,
            "authorities": [
                {
                    "authority": "ADMIN"
                }
            ],
            "username": "rskzoniac+admin@gmail.com",
            "accountNonLocked": true
        }
    },
    "responseTime": "11-10-2023 09:55:03"
}
```

<a name="delete"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- DELETE CODE STARTED -->
## Delete

1. delete url like this "http://localhost:xxxx/api/v1/users/#ACCESSTOKEN#" and replace your primary key
2. Add Header "Authorization-Owner" value is "myrequest" optional 
3. Method type is DELETE
4. Add Header "Authorization" value is "bearer #ACCESSTOKEN#" and replace your accessToken here

### Response Body
```sh
{
    "status": 1,
    "message": "User successfully deleted!",
    "data": {
        "payload": true
    },
    "responseTime": "11-10-2023 09:52:02"
}
```

<a name="refreshtoken"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- REFRESH-TOKEN CODE STARTED -->
## Refresh Token

1. refresh-token url like this "http://localhost:xxxx/api/v1/auth/refresh-token"
2. Add Header "Authorization-Owner" value is "myrequest" optional 
3. Method type is POST
4. Add Header "Authorization" value is "bearer #ACCESSTOKEN#" and replace your accessToken here

### Response Body
```sh
{
    "status": 1,
    "message": "success",
    "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDE3OTg2LCJleHAiOjE2OTcxMDQzODZ9.oyZCoaL4qcIUSvOy_w_WLW22jSEb2GIh2BLy1rIxhdY",
        "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs"
    },
    "responseTime": "11-10-2023 09:53:06"
}
```

<a name="errorresponse"></a><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- ERROR RESPONSE CODE STARTED -->
## Error Response

1. if received the internal server error response

### Response Body
```sh
{
    "apierror": {
        "errorCode": "Expired JWT token",
        "status": 500,
        "message": "Expired JWT token",
        "data": null,
        "responseTime": "11-10-2023 06:33:11"
    }
}
```

<a name="contributing"><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!


<a name="contact"><p align="right">(<a href="#readme-top">back to top</a>)</p>
<!-- CONTACT -->
## Contact

My Name - [Saravanakumar Ramasamy](https://www.linkedin.com/in/rsaravanakumar)

Project : [springboot3-springsecurity6-rsk](https://github.com/saronila/springboot3-springsecurity6-rsk)

