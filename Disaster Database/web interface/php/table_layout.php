<?php
	session_start();
	$_SESSION['newsession'] = 'newsession';
?>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
	<title>DB Interface</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css" media="screen">
	<link rel="stylesheet" type="text/css" href="../css/responsive.css" media="screen and (max-width: 1039px)">
  	<link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
  	<link rel="icon" href="../images/fav-icon.ico" type="image/x-icon">
	<script src="../js/prefixfree.min.js"></script>
</head>
<body>

	<header>
		<img id="logo" src="../images/logo.png" alt="Logo">
	</header>

	<nav class="main-nav">
		<a href="../html/index.html">Home</a>
    	<a href="data.php">Data</a>
    	<a href="../html/about.html">About</a>
  	</nav>

	<div class="main">

		<h1>Database Layout</h1>
		<p>Here are the tables and attributes to help you know how to structure your SQL queries. By clicking the "SELECT" button beside one of the tables, you will be sent back to the Database Commander which will be filled in with the appropriate SELECT statement for all items in that table.</p>

		<br>

		<?php
			if(isset($_POST['Disaster'])) {
				$_SESSION['command'] = "SELECT * FROM Disaster;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['DisasterPath'])) { 
				$_SESSION['command'] = "SELECT * FROM DisasterPath;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['ExactLocation'])) { 
				$_SESSION['command'] = "SELECT * FROM ExactLocation;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['LocationPopulation'])) { 
				$_SESSION['command'] = "SELECT * FROM LocationPopulation;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['ImpactReport'])) { 
				$_SESSION['command'] = "SELECT * FROM ImpactReport;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['ReliefCampaign'])) { 
				$_SESSION['command'] = "SELECT * FROM ReliefCampaign;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['ReliefAgency'])) { 
				$_SESSION['command'] = "SELECT * FROM ReliefAgency;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['ReliefItem'])) { 
				$_SESSION['command'] = "SELECT * FROM ReliefItem;";
				header('Location: data.php');
				exit();
			} else if(isset($_POST['ItemsSupplied'])) { 
				$_SESSION['command'] = "SELECT * FROM ItemsSupplied;";
				header('Location: data.php');
				exit();
			}
		?> 

		<form method="post">

			<p><input type="submit" name="Disaster" value="SELECT" class="btn"/> Disaster(<b>ID</b>, type, start_time, end_time, time_of_warning, wind_speed, rainfall, flood_depth, magnitude, cause)</p>

			<p><input type="submit" name="DisasterPath" value="SELECT" class="btn"/> DisasterPath(<b>disaster_ID</b>, <b>longitude</b>, <b>latitude</b>, order)</p>

			<p><input type="submit" name="ExactLocation" value="SELECT" class="btn"/> ExactLocation(<b>longitude</b>, <b>latitude</b>, city, state)</p>

			<p><input type="submit" name="LocationPopulation" value="SELECT" class="btn"/> LocationPopulation(<b>city</b>, <b>state</b>, population_size)</p>

			<p><input type="submit" name="ImpactReport" value="SELECT" class="btn"/> ImpactReport(<b>report_ID</b>, buildings_destroyed, casualties, permanent_injuries, temporary_injuries, <b>disaster_ID</b>)</p>

			<p><input type="submit" name="ReliefCampaign" value="SELECT" class="btn"/> ReliefCampaign(<b>campaign_ID</b>, start_date, end_date, <b>agency_ID</b>, <b>disaster_ID</b>)</p>

			<p><input type="submit" name="ReliefAgency" value="SELECT" class="btn"/> ReliefAgency(<b>agency_ID</b>, name, founded, for_profit, government_run)</p>

			<p><input type="submit" name="ReliefItem" value="SELECT" class="btn"/> ReliefItem(<b>item_ID</b>, name, cost_per_unit)</p>

			<p><input type="submit" name="ItemsSupplied" value="SELECT" class="btn"/> ItemsSupplied(<b>campaign_ID</b>, <b>item_ID</b>, quantity)</p>

		</form>
  	
  	</div>

<footer>
  Copyright © 2020 Christian Babin. All rights reserved. Designed, developed, and updated by&nbsp;&nbsp;<img src="../images/logo.png"> Christian Babin.
</footer>

<script src='../js/jquery-3.5.1.js'></script>
<script src="../js/index.js"></script>
</body>
</html>
