<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<style type="text/css">
.msg{
	color:red;	
}

</style>


<div class="container">
  <legend>欢迎注册</legend>
  <form id="mainForm" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="username" class="col-sm-1 control-label">用户名:</label>
        <div class="col-sm-3">
            <input  class="form-control"   id="username" type="text" name="userRegisterForm.username"   placeholder="" >
        </div>
   		<span class="msg"></span>
    </div>

    <div class="form-group">
    <label for="pwd" class="col-sm-1 control-label">密码:</label>
    <div class="col-sm-3">
    <input  class="form-control"   id="pwd" type="password" name="userRegisterForm.pwd"   placeholder="" >
    </div>
    <span class="msg"></span>
    </div>


    <div class="form-group">
    <label for="pwd2" class="col-sm-1 control-label">确认密码:</label>
    <div class="col-sm-3">
    <input  class="form-control"   id="pwd2" type="password" name="pwd2"   placeholder="" >
    </div>
    <span class="msg"></span>
    </div>
<!-- 
    <div class="form-group">
    <label for="nickname" class="col-sm-1 control-label">昵称:</label>
    <div class="col-sm-3">
    <input  class="form-control"   id="nickname" type="text" name="userRegisterForm.nickname"   placeholder="" >
    </div>
    <span class="msg"></span>
    </div> -->
    
    <div class="form-group">
    	<label class="col-sm-1 control-label">性别:
        </label>
    <div class="col-sm-3">
        <label>
        <input class="radio-inline radio" checked="checked"  type="radio" name="userRegisterForm.sex" value="男" />男
    </label>
        <label>
        <input class="radio-inline radio" type="radio" name="userRegisterForm.sex" value="女" />女
    </label>

    </div>
    
    </div>
    
    


    <div class="form-group">
    <label for="email" class="col-sm-1 control-label">邮箱:</label>
    <div class="col-sm-3">
    <input  class="form-control"   id="email" type="text" name="userRegisterForm.email"   placeholder="" >
    </div>
    <span class="msg"></span>
    </div>
    
    
    
    

    <div class="form-group">
    <label for="verityCode" class="col-sm-1 control-label">验证码:</label>
    <div class="col-sm-1">
        <input  class="form-control"   id="verityCode" type="text" name="userRegisterForm.verityCode"   placeholder="" >
    </div>

    <span>
     <img id="photo_yzm" class="img-thumbnail" style="width:80px;height:38px; cursor:pointer;" alt="验证码" title="点击更换图片" src="Photo_yzm"/>
     </span>
          <span class="msg"></span>
    </div>

    <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10" id="submitCase">
    <button type="submit" class="btn btn-default">注册</button>
    </div>
    </div>


    </form>
</div>
<script src="/style/js/user/register.js"></script>
</body>
<%@include file="/inc/_footer.jsp"%>
