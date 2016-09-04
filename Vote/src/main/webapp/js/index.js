$(function() {

	$.get("subject/listAll", function(data) {
		alert(data);
		var listStr = "";
		for (var i = 0; i < data.length; i++) {
			listStr += '<li ' + (i % 2 == 0 ? 'class="odd"' : '') + '>';
			listStr += '<h4>';
			listStr += '<a href="subject/view?vsId='+ data[i].vsId +'">' + data[i].vsTitle + '</a>';
			listStr += '</h4>';
			listStr += '<div class="join"><a href="#">我要参与</a></div>';
			listStr += '<p class="info">共有' + data[i].optionCount + '个选项，已有'
					+ data[i].voteCount + '个网友参与了投票。</p>';
			listStr += '</li>';
		}
		$("#subjectList").html(listStr);
	}, "json");

});