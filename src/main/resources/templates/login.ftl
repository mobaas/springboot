<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SpringBoot 开发框架</title>
</head>
<body>
登录页
<br/>
<br/>

<form id="form1" action="/login" method="post">

用户：<input type="text" name="username" />
<br/>
密码：<input type="password" name="password" />
<br/>
<a href="javascript:;" id="submit"> 登录 </a>

</form>

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

<!-- jQuery 3 -->
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery Form -->
<script src="/bower_components/jquery-form/dist/jquery.form.min.js"></script>
<!-- Layer 3.1.1 -->
<script src="/layer/layer.js"></script>

<script>
  $(function () {
   
    $('#submit').click(function() {
    	
    	var username = $('#username').val();
    	if ('' == username) {
	         layer.msg("请输入用户名。");
	         return false;
    	}
    	
    	var password = $('#password').val();
    	if ('' == password) {
	         layer.msg("请输入密码。");
	         return false;
    	}
    	
	   $('#form1').ajaxSubmit(function(json) {   
	      if (json.errCode == 0) {
	      	 location.href = '/main';
	      } else {
	         layer.msg(json.errMsg);
	      }
	   });
	});

  });
</script>

</body>
</html>  