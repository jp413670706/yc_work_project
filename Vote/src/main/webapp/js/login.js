$(function() {
	$("#loginBox input").focus(function() {
		$(this).addClass("input-text-over");
	});

	$("#loginBox input").blur(function() {
		$(this).removeClass("input-text-over");
	});

	$("#loginBox button").hover(function() {
		$(this).addClass("input-button-over");
	}, function() {
		$(this).removeClass("input-button-over");
	});
});