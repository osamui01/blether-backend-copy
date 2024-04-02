# CHATROOM API PROJECT - 'Big Blether' 👩🏽‍💻💬👨🏾‍💻

![Alt text](https://p1.hiclipart.com/preview/796/688/961/background-meeting-discussion-group-conversation-internet-forum-communication-text-orange-line-png-clipart.jpg "Big Blether Logo")

## Project Aims:
To create a functioning chatroom API onto to which a front-end can be seamlessly implemented. The primary intention was to facilitate chatrooms containing multiple users, rather than direct messages between two users *i.e. more akin to Reddit than Facebook Messenger.* In this project, we wanted to provide all users with the capability of creating a chatroom, whilst ensuring a space was reserved for more mature topics and content, hence we facilitated implementation of age restriction into indivudal chatrooms, where required. 

### Dependencies Used:
The project was built with Maven, using Java and Spring Boot Version 3.2.3. 
We also added the following dependencies:
- **Spring Web**
- **Spring Boot DevTools**
- **PostgreSQL Driver**
- **Spring Data JPA**


### Instructions on how to use Big Blether:
1) Create a database named chatroom_gp2 in the terminal using the createdb command.

2) Add the name of the database to application.properties and you should be able to see three tables names chatrooms, messages and users in Postico.

3) Use an @POST method to populate the users table by creating users in postman with the properties name, email and dateOfBirth to add to the database.

Example payload:
```JSON
{
    "name" : "Mr Chatter Box",
    "email" : "mrchatterbox@outlook.com",
    "dateOfBirth" : "1980-03-06"
}
```

4) Use an @POST method to populate the chatrooms table with the properties name, capacity and ageLimit.

Example payload:
``` JSON
{
    "name": "Crazy Chats",
    "capacity": 200,
    "ageLimit": 18
}
```

5) Use an @POST method to populate the messages table with the properties content, chatroomId and userId. We have chosen to limit content length to 200 characters to keep messages short and punchy   e.g. Twitter, Instagram, etc.

Example payload:
``` JSON
{
    "chatroomId" : 4,
    "userId" : 3,
    "content" : "Imagine his surprise when he discovered that the safe was full of pudding."
}
```

** Remember to uphold the many-to-many relationships by assigning many users to many chatrooms and many chatrooms to many users.**

6) Full CRUD functionality has been added to each of the tables along with the additional routes detailed below.

7)  Users that are under the age limit of the chatroom should not be able to see a message in that chatroom. We have created a method that calculates a user’s age from their date of birth to be determine if a message can be posted.
    --> **On the front end, a feature will need to be created that blurs messages in a chatroom for users that are below the age limit (so they cannot read messages in the chatroom prior to posting a message).**


### Relevant Diagrams:
- Class Diagram of MVP: https://github.com/KajananGit/Chatroom_Backend_Project/blob/main/ClassDiagram.png
- Class Diagram of MVP + Extensions *(in blue)*: https://github.com/KajananGit/Chatroom_Backend_Project/blob/main/Class%20Diagram%20including%20Extensions.png
- Entity Relationship Diagram: https://github.com/KajananGit/Chatroom_Backend_Project/blob/main/ERD_MVP.png

### Available Routes:
*For Chatroom:*
- **POST a chatroom:** localhost:8080/chatrooms ^^@FRONTEND TEAM: messages returns null so users cannot see messages until they have posted in a chatroom and their age has been verified. A @GetMapping will be required if wanting to return an array of messages.
- **GET a chatroom:** localhost:8080/chatrooms
- **GET a chatroom by id:*** localhost:8080/chatrooms/{id}
- **GET a list of chatrooms ordered by how recently they were used:** localhost:8080/chatrooms/recent
- **GET the most active chatroom *(defined as most messages sent within it)***: localhost:8080/chatrooms/mostActive
- **PATCH a chatroom by id:** localhost:8080/chatrooms/{id}
- **DELETE a chatroom by id:** localhost:8080/chatrooms/{id}

*For User:*
- **POST a user:** localhost:8080/users ^^@FRONTEND TEAM: messages returns null so users cannot see messages until they have posted in a chatroom and their age has been verified. A @GetMapping will be required if wanting to return an array of messages.
- **GET a list of all users:** localhost:8080/users
- **GET an individual user by id:** localhost:8080/users/{id}
- **PATCH a user by id:** localhost:8080/users/{id}
- **DELETE a user by id:** localhost:8080/users/{id}

*For Message:*
- **POST a message:** localhost:8080/messages
- **GET a list of all messages:** localhost:8080/messages
- **GET a message by it's id:** localhost:8080/messages/{id}
- **GET a message that contains a specific text:** localhost:8080/messages/content/{text}
- **GET a list of messages send by a specific user:** localhost:8080/messages/user/{id}
- **GET a list of messages posted in a specific chatroom:** localhost:8080/messages/chatroom/{id}
- **PATCH a specific message using it's id:** localhost:8080/messages/{id}
- **DELETE a specific message using it's id:** localhost:8080/messages/{id}


### MVP Functionality:
- Create a user. 
- Update user infomation. 
- Delete a user. 
- Allow a user to post a message in a chatroom.
- Allow users to read messages sent by other uses in a chatroom. 

### Extension Funtionality:
- Added **Timestamp to messages.**
- Created a **derived query to find a specifc chatroom by its id.**
- Created a **derived query to find a specific user by their id.**
- Created a **derived query to find all messages that contain a specific text.**
- Created a **custom query to display chatrooms by how recently messages were sent within them, ordered from most to least recent.**
- Created a **native query to find the most active chatroom (i.e. the chatroom with the most messages sent overall).**
- Added **age restriction functionality** by creating and implementing a method to check a user's age and compare it to the age limit of the chatroom, prior to them being able to post a message (and therefore to see a chatroom's content) in a chatroom.