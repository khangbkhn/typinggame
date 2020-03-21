$(document).ready(function() {
	$(".btnAction").click(function(event) {
		event.preventDefault();
		var $current = $(this);
		var isCheckButton = $(this).attr('id') == 'btnCheck';
		if (isCheckButton) {
			$current.prop("disabled", true);
		}
		var answer = $('#answerText').val();
		var url = $current.data('url');
		fireAjaxSubmit(url, '{"answer":"' + answer + '"}', isCheckButton ? showNextWord : surrenderFunction);
	});

	$("#btnReset").click(function(event) {
		location.reload(true);
	});
	
	var okMessage = "Well done!";
	var errorMessage = "Oh snap! Let try again.";
	
	var showNextWord = function(data) {
		$("#btnCheck").prop("disabled", false);
		if (data.correct) {
			$("#answerText").val("").focus();
			$("#gameLevel").text(data.gameLevel);
			$("#shuffledWord").text(data.nextWord);
			$("#message").text(okMessage).removeClass('hidden alert-danger').addClass('alert-success');
		} else {
			$("#message").text(errorMessage).removeClass('hidden alert-success').addClass('alert-danger');
		}
	}
	
	var surrenderFunction = function(data) {
		$("#correctAnswer").text("Correct answer: " + data.nextWord);
		$("#btnCheck").prop("disabled", true);
		$("#btnSurrender").prop("disabled", true);
	}
});

function fireAjaxSubmit(url, data, callBack) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : url,
		data : data,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			console.log("SUCCESS : ", data);
			callBack(data);
		},
		error : function(e) {
			console.log("ERROR : ", e.responseText);
		}
	});
}

