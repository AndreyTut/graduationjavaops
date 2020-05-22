#My topjava graduation project
***
Given task description:

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
    * If it is before 11:00 we asume that he changed his mind.
    * If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides new menu each day.

---

##API

###AdminRestController


####rest/admin/users

Method GET: Returns all users  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/users' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  

Method GET with path variable "/{id}": Returns one user with given id  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/users/100012' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \

Method POST: Adds new user  
Example:
curl --location --request POST 'http://localhost:8080/graduation/rest/admin/users/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"name": "Sigizmund",
"email": "sigizmund@users.net",
"password": "12345",
"roles": [
"ROLE_USER"
],
"enabled": true
}'  

Method PUT: Updates existing user  
Example:  
curl --location --request PUT 'http://localhost:8080/graduation/rest/admin/users/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"id": 100012,
"name": "Sigizmund-young",
"email": "sigizyoung@users.net",
"password": "12345",
"roles": [
"ROLE_USER"
],
"enabled": true
}'

Method DELETE with path variable "/{id}": deletes user with given id  
Example:  
curl --location --request DELETE 'http://localhost:8080/graduation/rest/admin/users/100012' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \  

####rest/admin/users/by

Method GET with parameter email: Returns user by email  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/users/by?email=user@yandex.ru' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \

###ProfileRestController

####rest/profile

Method GET: Returns authorized user  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/profile' \
--header 'Authorization: Basic ZmVkb3JAdXNlcnMubmV0Ontub29wfXBhc3N3b3Jk'  

Method PUT: updates authorized user  
Example:  
curl --location --request PUT 'http://localhost:8080/graduation/rest/profile' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic ZmVkb3JAdXNlcnMubmV0Ontub29wfXBhc3N3b3Jk' \
--data-raw '{
"id": 100014,
"name": "Feofan",
"email": "feof@users.net",
"password": "12345",
"roles": [
"ROLE_USER"
],
"enabled": true
}'  

Method DELETE: Deletes authorized user  
Example:  
curl --location --request DELETE 'http://localhost:8080/graduation/rest/profile' \  

###RestaurantRestController

####rest/admin/restaurants   
Method GET: Returns all restaurants  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/restaurants' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  

Method GET with path variable "/{id}": Returns one restaurant with given id  
Example:
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/restaurants/100002' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'   

Method POST: Adds new restaurant  
Example:  
curl --location --request POST 'http://localhost:8080/graduation/rest/admin/restaurants' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
    "name": "McDonalds"
}'  

Method PUT: Updates existing restaurant  
Example:   
curl --location --request PUT 'http://localhost:8080/graduation/rest/admin/restaurants' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"id": 100015,
"name": "McDonald'\''s"
}'  

Method DELETE with path variable "/{id}": deletes user with given id  
Example:  
curl --location --request DELETE 'http://localhost:8080/graduation/rest/admin/restaurants/100015' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'

###AdminMenuController

####rest/admin/menus

Method POST: Adds new menu with current date  
Example:  
curl --location --request POST 'http://localhost:8080/graduation/rest/admin/menus' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"restaurant": {
"id": 100002,
"name": "Restor_1"
},
"dishes": [
{
"name": "banana",
"price": 1000
},
{
"name": "potato",
"price": 1000
},
{
"name": "wine",
"price": 1050
}
]
}  

Method GET with path variable "/{id}": Returns one menu with given id
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/menus/100012' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  

###MenuRestController  

####rest/menus/today  

Method GET: Returns today's menus  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/menus/today' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  

####rest/menus/bydate  

Method GET: Returns menus for the date given as parameter 'date'  
Example:
curl --location --request GET 'http://localhost:8080/graduation/rest/menus/bydate?date=2015-05-30' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  

###VoteRestController  

####rest/votes  

Method GET: Returns voting results for date given as parameter 'date'  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/votes?date=2015-05-30' \
--header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='  

####rest/votes/today  

Method GET: Returns current voting results  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/votes/today' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  

####rest/votes/vote

Method POST: Gives authorized user's vote for menu which id was given as parameter 'menuId'  
Example:  
curl --location --request POST 'http://localhost:8080/graduation/rest/votes/vote?menuId=100012' \
--header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='  

####rest/votes/uservote  

Method GET: Returns menu which authorized user voted on date given as parameter 'date'  
Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/votes/uservote?date=2015-05-30' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  
 
 ---
 ##Day scenario example  
 
 Admin adds menu of the day for few restaurants, one by one:

curl --location --request POST 'http://localhost:8080/graduation/rest/admin/menus' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"restaurant": {
"id": 100002,
"name": "Restor_1"
},
"dishes": [
{
"name": "banana",
"price": 1000
},
{
"name": "potato",
"price": 1000
},
{
"name": "wine",
"price": 1050
}
]
}  '  

curl --location --request POST 'http://localhost:8080/graduation/rest/admin/menus' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"restaurant": {
"id": 100003,
"name": "Restor_2"
},
"dishes": [
{
"name": "fish&chips",
"price": 2000
},
{
"name": "coffee",
"price": 100
}
]
}  '  

curl --location --request POST 'http://localhost:8080/graduation/rest/admin/menus' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"restaurant": {
"id": 100004,
"name": "Restor_3"
},
"dishes": [
{
"name": "steak",
"price": 20000
},
{
"name": "rice",
"price": 1000
},
{
"name": "beer",
"price": 1000
}
]
}  '

User requests list of menus to make his decision:

 curl --location --request GET 'http://localhost:8080/graduation/rest/menus/today' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  
 
 The answer:  
 
 [
     {
         "restaurant": "Restor_1",
         "dishes": {
             "banana": "10,00 $",
             "potato": "10,00 $",
             "wine": "10,50 $"
         },
         "id": 100013
     },
     {
         "restaurant": "Restor_2",
         "dishes": {
             "fish&chips": "20,00 $",
             "coffee": "1,00 $"
         },
         "id": 100017
     },
     {
         "restaurant": "Restor_3",
         "dishes": {
             "steak": "200,00 $",
             "rice": "10,00 $",
             "beer": "10,00 $"
         },
         "id": 100020
     }
 ]  
 
 User chooses Restor_3 and votes for it:  
 
 curl --location --request POST 'http://localhost:8080/graduation/rest/votes/vote?menuId=100020' \
 --header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='
 
 Admin or user can check results of vote:
 
 curl --location --request GET 'http://localhost:8080/graduation/rest/votes/today' \
 --header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'  
 
 The answer is:  
 
 [
     {
         "name": "Restor_3",
         "voises": 1
     },
     {
         "name": "Restor_1",
         "voises": 0
     },
     {
         "name": "Restor_2",
         "voises": 0
     }
 ]
 
 

