

var emailRg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

		$(function() {
			$("#photo_yzm").on("click",function(){
				$("#photo_yzm").get(0).src = "Photo_yzm?"+new Date();
				$(this).animate({
					opacity:0.1
				},"fast").animate({
					opacity:1
				},"fast");				
			});

       /*    $(".team input").on("focus",function(){
			    $(this).parent().animate({width:400},"fast");
			});

			$(".team input").on("blur",function(){
			    $(this).parent().animate({width:380},"fast");
			});*/
			
			
			
			var setMsg = function(ele,msg){
				
				$(ele).parent().parent().children(".msg").html(msg);
			}
			
			var setSucc = function(ele){
					$(ele).parent().parent().children(".msg").html("√");
			}
			

			$("#pwd").on("blur",function(){
				var txt = $(this).val();
				if (txt.length == 0) {
					setMsg(this,"不能为空");
				}
				else if(txt.length>15||txt.length<4){
					setMsg(this,"密码长度在4-15之间");
				}
				else{
					setSucc(this);
				}
			});
			
			$("#pwd2").on("blur",function(){
				var txt = $(this).val();
				if (txt.length == 0) {
					setMsg(this,"不能为空");
				}
				else if(txt!=$("#pwd").val()){
					setMsg(this,"两次密码必须一样");	
				}
				else{
					setSucc(this);
				}
			});
			
			$("#email").on("blur",function(){
				var txt = $(this).val();
				if (txt.length == 0)
					setMsg(this,"不能为空");
				else if(!emailRg.test(txt))
					setMsg(this,"格式不正确");	
				else setSucc();
			});

		/*	$("#nickname").on("blur",function(){
				var txt = $(this).val();
				if (txt.length == 0)
					setMsg(this,"不能为空");
				else if(txt.length>10)
					setMsg(this,"长度需在1-10之间");
					else setSucc(this);
			});
			*/

	
			$("#username").on("blur", function() {
				var txt = $(this).val();
				if (txt.length == 0) {
					setMsg(this,"用户名不能为空");
					return;
				}
				$.post("j_User_check", {
					"username" : txt
				}, function(json) {
					if (json.exist) {
						setMsg($("#username"),"用户名已存在,请换一个");
					} else {
						setSucc($("#username"));
					}

				}, "json");

			});
			
			

			$("#mainForm").submit(function() {
				if ($("#submitCase img").size() > 0)
				return false;
				
				var data = serializeObject($("#mainForm"));
				var msg = "";
				if(data['userRegisterForm.username'].length==0){
						msg = "用户名不能为空";
				}
				else if(data['userRegisterForm.pwd'].length==0){
						msg = '密码不能为空';	
				}
				else if(data['pwd2']!=data['userRegisterForm.pwd']){
					msg = "两次密码必须一致";	
				}
	/*			else if(data['userRegisterForm.nickname'].length<1||data['userRegisterForm.nickname'].length>10){
					msg = "昵称长度必须为1-10";	
				}*/
				else if(data['userRegisterForm.email'].length==0){
					msg = "邮箱不能为空";
				}
				else if(!emailRg.test(data['userRegisterForm.email'])){
					msg = "输入的邮箱地址不规范";
				}
				else if(data['userRegisterForm.verityCode'].length==0){
					msg = "请输入验证码";	
				}
				else if(data['userRegisterForm.verityCode'].length!=4){
					msg = "验证码长度必须为4";	
				}
				
				if(msg.length!=0){
					alert(msg);
					return false;	
				}

				var temp = $("#submitCase").html();
				$("#submitCase").html("<img src='style/image/loading.gif'/> ");
				$.post("j_User_register", data, function(json){
					$("#submitCase").html(temp);
					if (json.success) {
						var s = 5;
						var f = function() {
							if (s <= 0) {
								$("#aimA").get(0).click();
								return;
							}
							$("#submitCase").html("<h3>注册成功," + s + "秒后跳转到<a href='' id='aimA'>首页</a></h3>");
							s--;
							window.setTimeout(f, 1000);
						};
						f();

						return false;
					} else {
						alert(json.msg);
					}
				}, "json");
				return false;
			});

		});