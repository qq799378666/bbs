$(function() {
    var changeYzm = function(){
        $("#photo_yzm").get(0).src = "Photo_yzm?"+new Date();
        $("#photo_yzm").animate({
        opacity:0.1
        },"fast").animate({
        opacity:1
        },"fast");
    };

    $("#photo_yzm").on("click",function(){
        changeYzm();
    });

				$("#commentForm").submit(function() {
                    if ($("#submitCase img").size() > 0)
                        return false;


					var content = $("#content").val();
					if (content.length == 0) {
						alert("评论内容不能为空");
						return false;
					}
                    var verityCode = $("#verityCode").val();
                    if(verityCode.length==0){
                        alert("请输入验证码");
                        return false;
                    }
					var data = {};
					data['content'] = content;
                    var temp = $("#submitCase").html();
                    $("#submitCase").html("<img src='style/image/loading.gif'/> ");
					$.post("j_Comment_add", $("#commentForm").serialize(), function(json) {
                        $("#submitCase").html(temp);

						if (json.success) {
							alert("评论成功");
							document.location.reload();
						} else {
							alert(json.msg);
							changeYzm();
						}
					}, "json");

					return false;
				});
			});