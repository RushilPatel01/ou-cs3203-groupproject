<?php
function connect($link){
    if(!$link){
        return true;
    }
    else{
        return false;
    }
}

$dbname = "sys";
$user = "root";
$pass = "password";
$port = 3306;
$conn = mysqli_connect("172.17.0.1", $user, $pass, $dbname, $port);
if(connect($conn)){
    echo "Connection Failed\n";
}
?>