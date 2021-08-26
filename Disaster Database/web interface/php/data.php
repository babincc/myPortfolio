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
	<style>
		.errorMsg {
			color: red;
		}
		table.resultTbl {
			border: 4px solid #79081C;
			background-color: #FF7001;
			width: 100%;
			text-align: center;
			border-collapse: collapse;
		}
		table.resultTbl td, table.resultTbl th {
			border: 1px solid #79081C;
			padding: 5px 2px;
			color: #FFFFFF;
		}
		table.resultTbl tr:nth-child(even) {
			background: #C80000;
		}
		table.resultTbl thead {
			background: #79081C;
		}
		table.resultTbl thead th {
			color: #FFFFFF;
			text-align: center;
		}
		#dataOptions {
			width:100%;
			display:inline-block;
			overflow:hidden;
			margin:0px 0px 10px 0px;
		}
		div.dOBtn {
			display:inline-block;
			margin-right:15px;
			float:left;
		}
	</style>
</head>
<body>

	<header>
		<img id="logo" src="../images/logo.png" alt="Logo">
	</header>

	<nav class="main-nav">
		<a href="../html/index.html">Home</a>
    	<a href="data.php"><active>Data</active></a>
    	<a href="../html/about.html">About</a>
  	</nav>

	<div class="main">

		<h1>Database Commander</h1>
		<p>This is where you can input commands or queries to talk to the database. These must be written in SQL, and you can only submit one at a time.</p>

		<div id="dataOptions">
			<div class="dOBtn">
				<a href="../images/ER_diagram.jpg" target="_blank" class="btn">ER Diagram</a>
			</div>

			<div class="dOBtn">
				<a href="table_layout.php" class="btn">Database Layout</a>
			</div>

			<div class="dOBtn">
				<a href="queries.php" class="btn">Sample Queries</a>
			</div>
		</div>

		<form id="cmd" action="data_controller.php" method="post">
			
			<?php
				if(isset($_SESSION['command']) && !empty($_SESSION['command'])) {
					echo "<textarea name=\"command\">{$_SESSION['command']}</textarea><br>";
					unset($_SESSION['command']);
				} else {
					echo "<textarea name=\"command\" placeholder=\"Enter your command/query here\"></textarea><br>";
				}

				if(isset($_SESSION['pin']) && !empty($_SESSION['pin'])) {
					echo "<input id=\"pin\" name=\"pin\" type=\"number\" value={$_SESSION['pin']}><br>";
				} else {
					echo "<input id=\"pin\" name=\"pin\" type=\"number\" placeholder=\"PIN*\"><br>";
				}
			?>
			
			<input class="btn" type="submit" name="send" value="Send">
			
			<p class="note">*PIN is for retricted commands. The site will let you know if you need it or not. It is not needed for all commands/queries. If you do not have a PIN and you are an approved user, you can find your PIN in the <a href="https://github.com/babincc/myPortfolio/tree/main/Disaster%20Database/web%20interface/security.md#pin" target="_blank">GitHub security write-up</a> for this site.</p>

		</form>

		<a name="resultsArea"></a>

		<br><br><br>

		<h2>You're Results:</h2>
		<style>
			.errorMsg {
				color: red;
			}
		</style>
		<?php
			if(isset($_SESSION['qResult']) && !empty($_SESSION['qResult'])) {

				$result = $_SESSION['qResult'];

				if(is_string($result) && stripos($result, 'Done') !== false) {
					echo "Done";
				} else {
					echo "<table class=\"resultTbl\"><thead class=\"resTblHead\">";

					foreach($_SESSION['columnHeads'] as $colHead) {
						echo "<th>{$colHead}</th>";
					}

					echo "</thead>";

					foreach($result as $row) {
						echo "<tr>";

							foreach($row as $col) {
								echo "<td>{$col}</td>";
							}

						echo "</tr>";
					}

					echo "</table>";
				}
			} else if(isset($_SESSION['errMsg']) && !empty($_SESSION['errMsg'])) {
				echo "<div class=\"errorMsg\">{$_SESSION['errMsg']}</div>";
			}
		?>
  	
  	</div>

<footer>
  Copyright © 2020 Christian Babin. All rights reserved. Designed, developed, and updated by&nbsp;&nbsp;<img src="../images/logo.png"> Christian Babin.
</footer>

<script src='../js/jquery-3.5.1.js'></script>
<script src="../js/index.js"></script>
</body>
</html>

<?php
	session_destroy();
?>