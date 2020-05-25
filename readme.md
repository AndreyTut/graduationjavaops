# My topjava graduation project
***
## Given task description:

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

## API

### AdminRestController
&nbsp;
&nbsp;
#### path: rest/admin/users
##### Method GET: Returns all users  
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/users' \
 &nbsp;
 &nbsp;
#### path: rest/admin/users/{id}
##### Method GET: Returns one user with given id 
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/users/100000' \
 &nbsp;
 &nbsp;
#### path: rest/admin/users
##### Method POST: Adds new user  
- Example:
curl --location --request POST 'http://localhost:8080/graduation/rest/admin/users/' 
--data-raw '{
"name": "Ivan",
"email": "ivanov@users.net",
"password": "12345",
"roles": [
"ROLE_USER"
],
"enabled": true
}'  
 &nbsp;
 &nbsp;
#### path: rest/admin/users
##### Method PUT: Updates existing user  
- Example:  
curl --location --request PUT 'http://localhost:8080/graduation/rest/admin/users/' \
--data-raw '{
"id": 100000,
"name": "User",
"email": "user@gmail.com",
"password": "12345",
"roles": [
"ROLE_USER"
],
"enabled": true
}'  
 &nbsp;
 &nbsp;
#### path: rest/admin/users/{id}
##### Method DELETE: deletes user with given id  
- Example:  
curl --location --request DELETE 'http://localhost:8080/graduation/rest/admin/users/100000' \
 &nbsp;
 &nbsp;
#### path: rest/admin/users/by
##### Method GET with parameter email: Returns user by email  
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/users/by?email=user@yandex.ru' \
---
### ProfileRestController
&nbsp;
&nbsp;
#### path: rest/profile/register
##### Method POST: Register new user
- Example:  
curl --location --request POST 'http://localhost:8080/graduation/rest/profile/register' \
--data-raw '{
"name": "Some user",
"email": "user@ukr.net",
"password": "12345"
}'  
&nbsp;
&nbsp;
#### path: rest/profile
##### Method GET: Returns authorized user  
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/profile' \
&nbsp;
&nbsp;
#### path: rest/profile
##### Method PUT: updates authorized user 
- Example:   
curl --location --request PUT 'http://localhost:8080/graduation/rest/profile/' \
--data-raw '{
"name": "Good user",
"email": "user@ukr.net",
"password": "12345"
}'  
&nbsp;
&nbsp;
#### path: rest/profile
##### Method DELETE: Deletes authorized user  
- Example:  
curl --location --request DELETE 'http://localhost:8080/graduation/rest/profile' \  
---
### RestaurantRestController
&nbsp;
&nbsp;
#### path: rest/admin/restaurants   
##### Method GET: Returns all restaurants  
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/restaurants' \  
&nbsp;
&nbsp;
#### path: rest/admin/restaurants/{id}
##### Method GET: Returns one restaurant with given id  
- Example:
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/restaurants/100002' \  
&nbsp;
&nbsp;
#### path: rest/admin/restaurants
##### Method POST: Adds new restaurant  
- Example:  
curl --location --request POST 'http://localhost:8080/graduation/rest/admin/restaurants' \
--data-raw '{
    "name": "McDonalds"
}'   
&nbsp;
&nbsp;
#### path: rest/admin/restaurants
##### Method PUT: Updates existing restaurant  
- Example:   
curl --location --request PUT 'http://localhost:8080/graduation/rest/admin/restaurants' \
--data-raw '{
            "id": 100021,
            "name": "McDonalds - restaurant"
            }'  
&nbsp;
&nbsp;
#### path: rest/admin/restaurants/{id}
##### Method DELETE: deletes user with given id  
- Example:  
curl --location --request DELETE 'http://localhost:8080/graduation/rest/admin/restaurants/100015' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
---
### AdminMenuController
&nbsp;
&nbsp;
#### path: rest/admin/menus
##### Method POST: Adds new menu with current date  
- Example:  
curl --location --request POST 'http://localhost:8080/graduation/rest/admin/menus/' \
--data-raw '{
"restaurant_id": 100003,
"dishes": {
"paltus": 10000,
"wine":1245
}
}'  
&nbsp;
&nbsp;
#### path: rest/admin/menus/future
##### Method POST with parameter date: Adds new menu with future date given as parameter  
- Example
curl --location --request POST 'http://localhost:8080/graduation/rest/admin/menus/future?date=2020-05-27' \
--data-raw '{
	"restaurant_id": 100003,
"dishes": {
"potato": 1000,
"fish": 2000,
"tea": 500
}
}'  
&nbsp;
&nbsp;
#### path: rest/admin/menus
##### Method PUT: Updates existing menu  
- Example
curl --location --request PUT 'http://localhost:8080/graduation/rest/admin/menus' \
--data-raw '{
"dishes": {
"dish4": 500,
"dish5": 700,
"new dish": 800 
},
"id": 100007
}'  
&nbsp;
&nbsp;
#### path: rest/admin/menus/{id}
Method GET: Returns one menu with given id
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/menus/100007' \  
---
### MenuRestController  
&nbsp;
&nbsp;
#### path: rest/menus/today  
##### Method GET: Returns today's menus  
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/menus/today' \
&nbsp;
&nbsp;
#### path: rest/menus/bydate  
##### Method GET: Returns menus for the date given as parameter 'date'  
- Example:
curl --location --request GET 'http://localhost:8080/graduation/rest/menus/bydate?date=2015-05-30' \
---
### AdminVoteController  
&nbsp;
&nbsp;
#### path: rest/admin/voteresult  
##### Method GET: Returns voting results for date given as parameter 'date'  
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/voteresult?date=2015-05-30' \
&nbsp;
&nbsp;
#### path: rest/admin/todayvoteresult  
##### Method GET: Returns current voting results  
- Example:  
curl --location --request GET 'http://localhost:8080/graduation/rest/admin/todayvoteresult' \
---
### VoteRestController
&nbsp;
&nbsp;
#### path: rest/votes
##### Method POST: Gives authorized user's vote for menu which id was given as parameter 'menuId'  
- Example:  
curl --location --request POST 'http://localhost:8080/graduation/rest/votes?menuId=100007' \  
&nbsp;
&nbsp;
#### path: rest/votes
##### Method PUT: Change authorized user's vote for menu which id was given as parameter 'menuId' (possible only till 11:00 AM)  
- Example:
curl --location --request PUT 'http://localhost:8080/graduation/rest/votes?menuId=100008' \  
&nbsp;
&nbsp;
#### path: rest/votes/votedmenu  
##### Method GET: Returns menu which authorized user voted on date given as parameter 'date'  
- Example:    
curl --location --request GET 'http://localhost:8080/graduation/rest/votedmenu?date=2020-05-25' \
 