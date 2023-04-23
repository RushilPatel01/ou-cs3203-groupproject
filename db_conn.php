<?php
$dbname = "sys";
$user = "root";
$pass = "password";
$port = 3306;
$conn = mysqli_connect("127.0.0.1", $user, $pass, $dbname);
if(!$conn){
    echo "Connection Failed";
}
?>