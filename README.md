# JWT Authentication and Authorization with Spring Boot 3 and Spring Security 6

1) signup : POST
========
URL = http://localhost:8080/api/v1/auth/signup
Header = Authorization-Owner : myrequest

request josn body
==================
{
    "firstName":"Saravanakumar",
    "lastName":"Ramasamy",
    "email":"rskzoniac+admin@gmail.com",
    "password":"12345",
    "role": "ADMIN"
}

respose
==========
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


2) signin : POST
=============
URL : http://localhost:8080/api/v1/auth/signin
Header = Authorization-Owner : myrequest

request josn body
==================
{
    "email":"rskzoniac+admin@gmail.com",
    "password":"12345"
}

respose
==========
{
    "status": 1,
    "message": "success",
    "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs",
        "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTc2MDk1ODV9.oKnNkSgqrgvVJYENWxSP9yoX27DLXuB68HKPAf6uOLw"
    },
    "responseTime": "11-10-2023 06:13:05"
}


3) GET ALL : GET
==========
URL :  http://localhost:8080/api/v1/users
Header = Authorization-Owner : myrequest
authorization type : bearer type 
	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs

respose
========
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

4) UPDATE : PUT
===============
URL :  http://localhost:8080/api/v1/users/1
Header = Authorization-Owner : myrequest
authorization type : bearer type 
	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs

request josn body
==================
    {
        "firstName":"Saravanakumar",
        "lastName":"R"
    }

respose
==========
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


5) get user : GET
================
URL :  http://localhost:8080/api/v1/users/1
Header = Authorization-Owner : myrequest
authorization type : bearer type 
	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs

respose
==========
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

6) delete : DELETE
==================
URL :  http://localhost:8080/api/v1/users/2
Header = Authorization-Owner : myrequest
authorization type : bearer type 
	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs

respose
========
{
    "status": 1,
    "message": "User successfully deleted!",
    "data": {
        "payload": true
    },
    "responseTime": "11-10-2023 09:52:02"
}

7) Refresh-token : POST
======================
Header = Authorization-Owner : myrequest
URL :  http://localhost:8080/api/v1/auth/refresh-token
authorization type : bearer type 
	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs
respose
========
{
    "status": 1,
    "message": "success",
    "data": {
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDE3OTg2LCJleHAiOjE2OTcxMDQzODZ9.oyZCoaL4qcIUSvOy_w_WLW22jSEb2GIh2BLy1rIxhdY",
        "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyc2t6b25pYWNAZ21haWwuY29tIiwiaWF0IjoxNjk3MDA0Nzg1LCJleHAiOjE2OTcwOTExODV9.7WI4cKU8qHnjNhDDM5Ze8f7k_hf_0za3HoswJEKylXs"
    },
    "responseTime": "11-10-2023 09:53:06"
}

8) Error respose
================
{
    "apierror": {
        "errorCode": "Expired JWT token",
        "status": 500,
        "message": "Expired JWT token",
        "data": null,
        "responseTime": "11-10-2023 06:33:11"
    }
}
