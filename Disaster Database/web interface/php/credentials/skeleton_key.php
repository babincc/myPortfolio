<?php

error_reporting (E_ALL ^ E_NOTICE);

$server = "disasterdb.cteilz5w6nyy.us-east-1.rds.amazonaws.com";
$user = "authUser";
$pass = "ZWRpdG9yNTA4";
$database = "DisasterDB";

$connect = mysqli_connect($server, $user, base64_decode($pass), $database);

if(!$connect) {
	die("ERROR: Connection failed!");
}

?>