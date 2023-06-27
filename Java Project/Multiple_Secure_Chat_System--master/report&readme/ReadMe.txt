Please note first you have to create a database and the tables of the database that is 
described in the file "database.txt" in this folder.

You should run the database server before running the server and clients. The default info 
of the database connection are hardcoded in "Server.java" at line 42. These info as follows:
Host = "localhost", username = "root", and password = "" (empty). Change it as needed.


To run for Mac OS using the command line in the terminal:

To compile all Java files and using the jar file for database connecter and another jar 
file for encoding:

javac -cp lib/commons-codec-1.9.jar:lib/mysql-connector-java-5.1.33-bin.jar *.java

Then, you should register the register for the RMI and register each interface 
(Server and Client):

rmiregistry &
rmic Server
rmic Client


To run the server, you need to use this command:

java -cp .:lib/mysql-connector-java-5.1.33-bin.jar:lib/commons-codec-1.9.jar ServerUI

Then, you can use this command to run a client from the terminal:

java -cp .:lib/mysql-connector-java-5.1.33-bin.jar:lib/commons-codec-1.9.jar Login

To run for Windows OS using the command line in the terminal:
To run this project follow the following steps:

Server
Server Running --> RMI Registry (Lookup for connectivity)

1.Open Command Prompt cd f:\chat
2.rmic Server
2.start rmiregistry
3.java -cp .;commons-codec-1.9.jar;mysql-connector-java-5.1.20-bin.jar ServerUI
Server is running now.

Client 

1. Open Command Prompt
2. cd F:\chat (Give the path where the source code is)
3. java -cp .;commons-codec-1.9.jar;mysql-connector-java-5.1.20-bin.jar Login

For each new client you need to run the above steps.
