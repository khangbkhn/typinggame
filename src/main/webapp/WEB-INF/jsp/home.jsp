<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
	<title>Typing Game</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.4.1/css/bootstrap.min.css" />
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:void(0)">TYPING GAME</a>
			</div>
		</div>
	</nav>

	<div class="container" style="min-height: 500px">

		<div class="starter-template">
			<div id="message" class="hidden alert alert-success col-sm-6">Well done!</div>
			<br>
			<br>
			<br>
			<h2>Level <span id="gameLevel" class="label label-success">${data.gameLevel}</span></h2>
			<form class="form-horizontal" id="gameForm">
				<div class="form-group form-group-lg">
					<label class="col-sm-2 control-label">Word</label>
					<div class="col-sm-4">
						<label id="shuffledWord" class="col-sm-2 form-control text-uppercase">${data.currentShuffledWord}</label>
					</div>
				</div>
				<div class="form-group form-group-lg">
					<label class="col-sm-2 control-label">Your answer</label>
					<div class="col-sm-4">
						<input type="text" placeholder="Enter your answer" aria-label="Enter your answer" class="form-control" id="answerText" required/>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="btnCheck" class="btnAction btn btn-success" data-url="/check"><span class="glyphicon glyphicon-ok"></span> Check</button>
						<button id="btnSurrender" class="btnAction btn btn-danger" data-url="/surrender"><span class="glyphicon glyphicon-remove"></span> Surrender</button>
						<button id="btnReset" class="btn btn-info"><span class="glyphicon glyphicon-repeat"></span> Reset</button>
					</div>
				</div>
				<div class="form-group form-group-lg">
					<label id="correctAnswer" class="col-sm-2 control-label"></label>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>