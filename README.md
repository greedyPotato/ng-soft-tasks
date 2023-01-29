# ng-soft-tasks
tasks for users with comments in spring boot and security

Hi,

there is an ADMIN user already in database on server startup, with 
username: admin and password : admin
use it to make changes in the API.

admin have many previeliges 
he can also create users(with role USER) that has limited previliges.

in order to make a REST call, use the localhost:8080/authenticate rest.
and provide:

{
    "userName": "a",
    "password": "a"
    
}

you will get an accessToken:

{
    "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhIiwiZXhwIjoxNjc1MDI1MDAzfQ.OyxuPiiw_jbwexbc4bLUbY3A0SUPSvSmNvi40-I5QpBIlxLPTfu5OyB1QTeN7JVRBb8-px9fbuY_OoAMlYc6hw"
}

that will be needed to make other calls

use that at the header section

example:
key: Authorization value: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NTAyNDc5Nn0.GeUBAOK457wAtqITqreGed7OfHSJWhbrDxrHqHSvd39HEFDDT010Sp1oDYSRxCkGAN1nI_FtMiB1z6LwmWGCzQ

make sure you put a space between Bearer and the accesstoken, and use capital B.


the rest of the app is just like the task itself, but ive changed some things:

user has a role:ADMIN OR USER
to assign a task to user, (only admin can do that):

GET : http://localhost:8080/user/2/task/1

to assign comment to a task by a user:

http://localhost:8080/comment/user/2/task/3

with body :
{
    "timeStamp": "2022-01-01",
    "comment": "please commit "
    
}

rest of the app is very easy to understand.
thank you
idan

