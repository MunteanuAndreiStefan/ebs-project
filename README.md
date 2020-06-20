Representing a publish/subscribe content-based system, the project was done in about 2 days and it's not clean.
Simple hardcoded small project with RabbitMQ and Docker using apache flinq and a data generator in python. I've also used protocol buffer language to structure protocol buffer data with proto3

`docker run -d --hostname rabbit-ebs --name ebs-rabbit -p 5673:5672 -p 15672:15672 rabbitmq:3-management`
In order to see how RabbitMQ is running go to `http://localhost:15672/`. Use username "guest" and password "guest" to log in.
