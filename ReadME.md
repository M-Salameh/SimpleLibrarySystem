# Project Title
This is a Simple Library System for Books, Patrons and Patrons borrowing Books with rental price.

## Description
This is Java Spring Boot Application for simple library system.
you can add books and patrons (persons) to the system with basic CRUD Operations
then patrons can borrow books and on returning the book it will set rental price in the records.
we used spring boot with Dependency Injection, Transactions, Relational in Memory Database (H2).
we also used GlobalExceptionsHandler to handle exceptions in all the components of spring boot application.
we used JPA Repositories to handle database queries.
Application built with logical segregation into Entities, Controllers and Services.
We used Aspect Oriented Programming Logging to log all system's methods calls, exceptions, returns.

Also Junit Tests are Added to test Controllers and Services Logic

## Getting Started
### How to Run:
All you need is to run the jar file with java -jar application.jar and test using Postman
make sure your java veresion is 17 or later.

### Using the Application:
Test using Postman:
#### Books:
##### Get Books:
using Get Method.
url: http:localhost:8080/api/books

##### Get by ISBN
using Get Method
url: http:localhost:8080/api/books/isbn/{desired isbp}
##### Get by ID
using Get Method
url: http:localhost:8080/api/books/{desired id}
##### Create Book
using POST method
url: http:localhost:8080/api/books/

Body must be like:
{
"isbn": "1111",
"title": "titl2e",
"author": "auth2or",
"dateOfPublish": "2023-11-11",
"rentPricePerDay": 10
}

replace anything by any value you want , but for date please keep it in the form of string "YYYY-MM-DD"
if you want to set a day or month of one digit (1 -> 9) please write them in the form of (0digit)
examples:
1901-03-07
2021-11-18
1384-08-16

##### Update Book
using PUT method
url: http:localhost:8080/api/books/{Id of book to update}

Body must be like:
{
"isbn": "1111",
"title": "titl2e",
"author": "auth2or",
"dateOfPublish": "2023-11-11",
"rentPricePerDay": 10
}

replace anything by any value you want , but for date please keep it in the form of string "YYYY-MM-DD"
if you want to set a day or month of one digit (1 -> 9) please write them in the form of (0digit)
examples:
1901-03-07
2021-11-18
1384-08-16

##### Delete Book
using Delete method
url: http:localhost:8080/api/books/{Id of book to delete}


Body of Post When Creating or Updating Book:

{
  "isbn": "1111",
  "title": "titl2e",
  "author": "auth2or",
  "dateOfPublish": "2023-11-11",
  "rentPricePerDay": 10
}


#### Patrons:

##### Get Patrons:
using Get Method.
url: http:localhost:8080/api/patrons

##### Get by ID
using Get Method
url: http:localhost:8080/api/patrons/{desired id}
##### Create Patrons
using POST method
url: http:localhost:8080/api/patrons/

Body :
{
"name": "Patron1",
"email": "patron1@gmail.com",
"address": "NY,USA"
}


##### Update Patrons
using PUT method
url: http:localhost:8080/api/patrons/{Id of patron to update}

Body of Post When Creating or Updating Patron:
{
"name": "Patron1",
"email": "patron1@gmail.com",
"address": "NY,USA"
}


##### Delete Patrons
using Delete method
url: http:localhost:8080/api/books/{Id of patron to delete}



#### Borrow Records:


##### Create Borrow Record (when borrowing a book)
using POST method
url: http:localhost:8080/api/borrow/{patronId}/book/{bookId}


##### Update Borrow Records (when returning a book)
using PUT method
url: http:localhost:8080/api/return/{bookId}/patron/{patronId}



## Authors
### Mohammed Salameh
* Name: Mohammed Salameh
* email : mohammedsalameh37693@gmail.com
* LinkedIn : www.linkedin.com/in/mohammed-salameh-8b4811313