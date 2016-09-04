$.post("option/view?vsId=" + $("#vsId").val(), function(data) {
	alert(data);
}, "json");
