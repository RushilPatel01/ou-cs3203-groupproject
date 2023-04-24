yOUr Bank

This repository is for group X in Professor Nic's CS3203 course. What this repository contains is the code for a functional banking web application made using languages like PHP, HTML, and CSS. In this README.md I will go through the process of each file and explain how to deploy the mySQL database and web application.

Before set up make sure php, apache, docker, and mysql workbench (Not necessary but you need something to be able to connect to the mysql server) is installed in your computer

Install php and apache (Windows) (At this moment in time the newest version of apache is apache 2.4.57 and php is php 8.2)

(This is the website I used and it worked but I will explain it)
https://www.sitepoint.com/how-to-install-php-on-windows/

Apache 2.4.57

Go to this website to download the apache parameters: https://www.apachelounge.com/download/

Under Apache 2.4.57 Win64 download the zip file named httpd-2.4.57-win64-VS17.zip

Once downloaded extract all of it and open the extracted directory

In the extracted folder there is a directory named Apache24

Copy this directory and paste it in your C: drive

Next Open CMD (Command Prompt) and go into the Apache24 folder in your C: drive and go into the bin directory (Navigate CMD by using the command cd to enter a directory and dir to list a directory or type the command cd C:\Apache24\bin)

Once you are in the directory type in httpd (This will start the apache server (do it once for safe measure)(To stop the server press Ctrl + C))

PHP 8.2

Go to this website to download the PHP parameters: https://www.php.net/downloads.php

Under Current Stable PHP 8.2.5 go to windows downloads

Under VS16 x64 Thread Safe download the Zip file

Once downloaded extract all of it and open the extracted directory

Next create a folder in your C: drive named "php"

Copy all the files from the extracted zip and paste them to the C:\php folder

Once you accomplished this then find a file named php.ini-development in the C:\php directory

copy and paste this back into the C:\php directory

rename the file to say "php.ini"

Open this file in a text editor like Visual Studio Code

Once you accomplished this then go about half way down and there will be a long section like the following:

;extension=curl
;extension=ffi
;extension=ftp
;extension=fileinfo
;extension=gd
;extension=gettext
;extension=gmp
;extension=intl
;extension=imap
;extension=mbstring
;extension=exif      ; Must be after mbstring as it depends on it
;extension=mysqli
;extension=oci8_12c  ; Use with Oracle Database 12c Instant Client
;extension=oci8_19  ; Use with Oracle Database 19 Instant Client
;extension=odbc
;extension=openssl
;extension=pdo_firebird
;extension=pdo_mysql
;extension=pdo_oci
;extension=pdo_odbc
;extension=pdo_pgsql
;extension=pdo_sqlite
;extension=pgsql
;extension=shmop

delete the ";" on the extensions you would like to use. For this project delete the following:

;extension=curl
;extension=gd
;extension=mbstring
;extension=mysqli
;extension=pdo_mysql

if you did it right this portion of this file will look like the following:

extension=curl
;extension=ffi
;extension=ftp
;extension=fileinfo
extension=gd
;extension=gettext
;extension=gmp
;extension=intl
;extension=imap
extension=mbstring
;extension=exif      ; Must be after mbstring as it depends on it
extension=mysqli
;extension=oci8_12c  ; Use with Oracle Database 12c Instant Client
;extension=oci8_19  ; Use with Oracle Database 19 Instant Client
;extension=odbc
;extension=openssl
;extension=pdo_firebird
extension=pdo_mysql
;extension=pdo_oci
;extension=pdo_odbc
;extension=pdo_pgsql
;extension=pdo_sqlite
;extension=pgsql
;extension=shmop

Next we need to add a path to this folder for your PC to be able to access it

click the windows button and type "Environment"

Next click the button labeled "Environment Variables"

In the top portion of the Environment Variables locate the variable named "Path"

Select it and click "Edit"

After clicking this you will see a new windowm with different paths

Under the last path in a empty row click it to edit it

Once you begin editing it type in "C:\php" or whatever the directory from your C: drive is

Now you click okay till you are out of it

Docker Desktop

(This is the website I used)
https://docs.docker.com/desktop/install/windows-install/

mySQL Workbench

(This is the website I used)
https://dev.mysql.com/downloads/workbench/

Once on here you can click download for windows

It will ask to login to a orical account but you can click no thanks and it will install

Run the installer and you will have mySQL Workbench installed

There you go you have everything installed now open VSCODE and we will deal with extentions

In VSCODE there on the left side of the screen there are 4 blocks with one detached, click this to open extensions

Next type and install the following extensions in the search:

Code Runner by Jun Han
Docker by Microsoft
PHP by DEVSENSE
PHP Debug by Xdebug
PHP Extension Pack by Xdebug
PHP IntelliSense by Damjan Cventko
PHP Profiler by DEVSENSE

Once you have done this you have finished the preperation

Next download this github and enter that directory through the termianl in VSCODE (To navigate the terminal use cd to enter directory and ls to list items in directory) (BE SURE TO BE IN THE DIRECTORY THAT HAS ONLY THE DOCKERFILE AND THE SRC DIRECTORY)

next type in docker info to make sure docker works (If you see a bunch of docker stuff and no red text it works)

Next type in docker login to make sure you are logged in

make sure you are in the directory containing dockerfile (just to be sure you arent in the wrong directory. This can be navigated in the vscode terminal by using cd and ls)

Once you are in the correct directory run the command to build and run the mySQL db in a docker container (replace "database-name", "database-password", "name-of-docker-image", "tag-of-docker-image" with your own stuff)(for this project we used "database-name" = mysqlDB, "database-password" = hehe3000, "name-of-docker-image" = mysql, "tag-of-docker-image" = latest):
"docker run --name database-name -p 3306:3306 -e MYSQL_ROOT_PASSWORD=database-password -d name-of-docker-image:tag-of-docker-image"

Now you have a mySQL database dockerized in a docker container

Next we will access this database using mySQL Workbench

Open mySQL Workbench and next to MySQL Connections there will be a plus sign, click that

You will get a window with different parameters to connect to your db

In connection name type in the name of the connection (This can be anything you want)

Next in the parameters tab make sure host name is 127.0.0.1 and Port is 3306 and Username is root

Then click "Store in Vault ..." button

A new window will pop up. in this window there is a area to type in a password. Type in the password of your database (This is the database-password from the docker command from earlier)

Click okay once you have entered the correct command

Click Test Connection to see if you can successfully connect to the database

If it works then click the ok button and open your connection

Here you will see a area named "SCHEMAS" This is where your database is located where you can drop it down and create tables

Drop the menu down and drop down tables

right click tables and create your table

Here name the table in "table name" (We used bank_info)

In the area under comments you can create columns with different information in your table

The information I set up is id (Datatype INT)(selected PK, NN, UQ, AI), FName (Datatype VARCHAR(100))(selected NN), LName (Datatype VARCHAR(100))(selected NN), UName (Datatype VARCHAR(100))(selected NN), Pword (Datatype VARCHAR(100))(selected NN), Checking (Datatype INT)(selected NN)(Default/Expression '100'), Savings (Datatype INT)(selected NN)(Default/Expression '100')

Once this is entered click apply to apply to the table

On success your table will be propagated

*Warning* At this step do not delete dockercontainer or image it will most likely delete everything you have done if you stop the container thats fine but do not delete it

Now we are finished on the database side

Next go back to VSCODE to the terminal that you ran the docker command to launch the mySQL database

Here type in the build command (if it doesnt work make sure you are in the directory as the dockerfile):
docker build -t my-php-app .

Next type in the command to run the container (Make sure to include --link mysqlDB where mysqlDB being the database-name of the mysql docker command. If this part is not here your docker container will not be able to communicate to the docker mysql container. Docker containers can NOT talked to eachother without linking them):
docker run -p 80:80 --link mysqlDB my-php-app

If you get some errors here go to the end of the readme for some solutions I went through

If you got no errors well congratulations everything is pretty much done. Now go to a web browser and type into the search bar "localhost"

This will bring you to the login page and you can use the web application



This portion if for the errors that may occur for some of you:

If you are getting a error refuse prompt using mysqli_connect from the db_conn.php make sure all the connection parameters are right and that container is running. If they are then there is a chance that the apache server needs permission to access the firewall

To do this type in the following:
docker exec -it bd8 mysql -uroot -p
(where bd8 is the first 3 characters of the container and mysql is the name of the image - this can be located by doing docker images and docker ps)

Once you do this enter the password of the db (this will be the "database-password" you set up when calling the docker command to run the mySQL database)

You will see a mysql> prompt

Here type in:
CREATE USER 'user'@'host' IDENTIFIED BY 'password';
(Where username = root and host = 127.17.0.3 or whatever the apache server is stating in the terminal when it is running and password = to any password you want)
(I would also create another user with parameters being username = root and host = 127.17.0.1)

Next type:
GRANT PRIVILEGE ON database.table TO 'username'@'host';
(With username = username on previous command and host = host on previous command)

Next type:
flush privileges;

Finally type:
exit;
(to leave)

Resource that could help you if confused:
https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql
