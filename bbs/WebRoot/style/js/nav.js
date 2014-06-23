$(function() {
	$("#loginForm").submit(function() {
		if ($("#submitCase img").size() > 0)
			return false;
		var temp = $("#submitCase").html();
		$("#submitCase").html(" <img src='style/image/loading.gif'/> ");
		$.post("j_User_login.action", $("#loginForm").serialize(), function(json) {
			$("#submitCase").html(temp);
			if (json.success) {
				document.location.reload();
				return false;
			} else {
				alert(json.msg);
			}
		}, "json");
		return false;
	});

});
