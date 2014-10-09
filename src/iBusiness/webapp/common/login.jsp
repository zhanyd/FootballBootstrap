<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
	<%@include file="/common/meta.jsp"%>
	<title><spring:message code="core.login.title" text="登录" /></title>
	<%@include file="/common/center.jsp"%>
	<script type="text/javascript">
  		function checkcode(){
  			$.ajax({
					'url':'captchaAjax',
					'type':'post',
					'data':'inputcode=' + $('#codeinput').val(),
					'dataType':'text',
					'success':function(data){
							var result=data;
							if(result==1){
									$('#checkcodemsg').css({color:"green"});
									$('#checkcodemsg').text('验证码正确');
									// 提交按钮可用
									$("#input_commit").removeAttr('disabled');
							}else{
									$('#checkcodemsg').css({color:"red"});
									$('#checkcodemsg').text('验证码错误，请重新输入');
									// 提交按钮不可用
									$("#input_commit").attr('disabled',' true');
							}
					}
				});
  		}
  </script>

</head>

<body onload='document.f.j_username.focus();'>
	<!-- start of header bar -->
	<div class="navbar navbar-inverse">
		<div class="navbar-header">
		    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-responsive-collapse">
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		    <a class="navbar-brand" href="${scopePrefix}/">iBusiness</a>
	    </div>
	</div>
	<!-- end of header bar -->
	<!--  -->
		<div class="col-lg-3"></div>

		<!-- start of main -->
		<div class="panel panel-default col-lg-6"> 
	        <div class="panel-heading"><h4 class="panel-title">登录</h4></div>
	        <div class="panel-body">
		        <div class="alert alert-dismissable  alert-warning"${param.error==true ? '' : 'style="display:none"'}>
					<strong><spring:message code="core.failure.save" text="登陆失败" /></strong> &nbsp;
					${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
				</div>
				<br/>
				
				<!-- 并监听默认的URL  j_spring_security_check 提交登陆信息的URL地址-->
				<form id="userForm" name="f" method="post" action="${scopePrefix}/j_spring_security_check" class="form-horizontal">
				    <div class="form-group">
					    <label class="col-lg-2 control-label" for="username"><spring:message code="core.login.username" text="账号" />:</label>
					    <div class="col-lg-4">
					       <input type='text' id="username" name='j_username' class="form-control" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}">
					    </div>
					</div>
					<div class="form-group">
					    <label class="col-lg-2 control-label" for="password"><spring:message code="core.login.password" text="密码" />:</label>
					    <div class="col-lg-4">
						    <input type='password' id="password" name='j_password' class="form-control" value=''>
					    </div>
					</div>
					<!-- 验证码  -->
					<div class="form-group">
					     <label class="col-lg-2 control-label" for="codeinput"><spring:message code="core.login.password" text="验证码"/></label>
					     <div class="col-lg-4">
					         <input name="number" class="form-control" id="codeinput" onkeyup='checkcode()'/><span STYLE="color: red" id="checkcodemsg"></span><br/>
					     </div>
					     <img src="checkcode" id="img1" onclick="document.getElementById('img1').src='${scopePrefix}/common/checkcode?' + Math.random();" />
					     <a href="javascript:;"  onclick="document.getElementById('img1').src='${scopePrefix}/common/checkcode?' + Math.random();">看不清</a><br/>
				    </div>
					<div class="col-lg-10 col-lg-offset-2">
						<input id="input_commit" class=" btn btn-primary" name="submit" type="submit" value="<spring:message code='core.login.submit' text='提交'/>"  disabled="disable" />
					</div>
			    </form>
	        </div>
        </div>
		<!-- end of main -->
		<div class="col-lg-3"></div>
</body>
</html>
