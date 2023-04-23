<?php
$dbname = "sys";
$user = "root";
$pass = "password";
$port = 3306;
$conn = mysqli_connect("172.17.0.1", $user, $pass, $dbname, $port);
if(!$conn){
    echo "Connection Failed";
}
else{
    echo "Connection worked";
}
?>