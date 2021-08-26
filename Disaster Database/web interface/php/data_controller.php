<?php

session_start();

error_reporting (E_ALL ^ E_NOTICE);

// let the user know what is going on
echo("This could take a few minutes...<br><br>");

$secret = '$2y$10$TfXMUhWObUPuzt8hpg6bZOwBMU9uczEFZLiTsdM0SkgGbF.h1LXYi';

if(isset($_POST['send'])) { // check for legit button click

	$command = $_POST['command'];
	$_SESSION['command'] = $command;

	$PIN = $_POST['pin'];
	$_SESSION['pin'] = $PIN;

	// let the user know what is going on
	echo("Making sure command is not malicious...<br>");

	// check command string for forbidden commands
	if(hasForbiddenCommands($command)) {
		// TODO display error
		goBackWithError("ERROR: Used forbidden command!");
	}

	// let the user know what is going on
	echo("Command is not malicious<br><br>");

	// let the user know what is going on
	echo("Making sure you have the security clearence to make this command...<br>");

	if(password_verify($PIN, $secret)) { // check for PIN
		require 'credentials/skeleton_key.php'; // login to the database as an authorized user
	} else {
		// check command string for restricted words
		if(hasRestrictedCommands($command)) {
			// TODO display error
			goBackWithError("ERROR: Used restricted command!");
		}

		require 'credentials/key.php'; // login to the database as a normal user
	}

	// let the user know what is going on
	echo("Security clearence approved<br><br>");

	// let the user know what is going on
	echo("Connecting to database...<br>");

	// check the command and then send it to the database
	$stmt = mysqli_stmt_init($connect);

	// let the user know what is going on
	echo("Connected<br><br>");

	// let the user know what is going on
	echo("Making sure command is valid...<br>");

	if(!mysqli_stmt_prepare($stmt, $command)) { // see if this is a valid command for the DB
		goBackWithError("ERROR: Invalid command!");
	} else {
		
		// let the user know what is going on
		echo("Command is valid<br><br>");

		// let the user know what is going on
		echo("Sending command to database...<br>");

		// send the command to the database
		$result = mysqli_query($connect, $command);

		// let the user know what is going on
		echo("Command sent<br><br>");

		// let the user know what is going on
		echo("Processing result...<br>");

		// figure out what needs to be done with the result
		handleResult($result, $command);
	}

} else {
	goBackWithError("ERROR: Connection failed!");
}




//////////////////// FUNCTIONS ////////////////////

// Check to see if there are forbidden commands in the command string
function hasForbiddenCommands($command) {
	$counter = 0;

	if(stripos($command, 'DROP') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'TRUNCATE') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'DELETE FROM') !== false && stripos($command, 'WHERE') === false) {
		// TODO let the user know what command is forbidden
		$counter++;
	} 
	if(stripos($command, 'ALTER') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'RENAME') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'GRANT') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'REVOKE') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}

	if($counter == 0) {
		return false;
	} else {
		return true;
	}
}

// Check to see if there are restricted commands in the command string
function hasRestrictedCommands($command) {
	$counter = 0;

	if(stripos($command, 'CREATE') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'INSERT') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	} 
	if(stripos($command, 'UPDATE') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'DELETE') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	} 
	if(stripos($command, 'MERGE') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'COMMENT') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'EXECUTE') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'CALL') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}
	if(stripos($command, 'LOCK') !== false) {
		// TODO let the user know what command is forbidden
		$counter++;
	}

	if($counter == 0) {
		return false;
	} else {
		return true;
	}
}

// Send the user back with an error message
function goBackWithError($msg) {
	$_SESSION['errMsg'] = $msg;
	header('Location: data.php#resultsArea');
	exit();
}

// Send the user back with the result of their query
function goBackWithResult() {
	header('Location: data.php#resultsArea');
	exit();
}

// figure out what the user wants to be done with the result
function handleResult($result, $command) {
	
	// if they did something on the server side, just acknowledge it
	if(hasRestrictedCommands($command)) {

		// let the user know what is going on
		echo("Finished<br><br>");

		$_SESSION['qResult'] = "Done";

		goBackWithResult();
	} else {

		// let the user know what is going on
		echo("Finished<br><br>");

		$cols = $result->fetch_all(MYSQLI_ASSOC);
		$columnHeads = array();

		if(!empty($cols)){
			$columnHeads = array_keys($cols[0]);
		}

		$_SESSION['qResult'] = $cols;
		$_SESSION['columnHeads'] = $columnHeads;

		goBackWithResult();
	}
}

?>