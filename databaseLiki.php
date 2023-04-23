<?php
$dbname = "sys";
$user = "root";
$pass = "password";
$port = 3306;
$conn = mysqli_connect("localhost", $user, $pass, $dbname, $port)
    or die("Could not connect: " . mysqli_error($con));
?>