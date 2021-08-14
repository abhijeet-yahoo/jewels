<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp" %>

<link rel="stylesheet" href="<spring:url value='/css/main/login.css' />">



	<form class="form-signin" role="form" action="<spring:url value="/j_spring_security_check" />" method="POST" >
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
		<h2 class="form-signin-heading">Please sign in</h2>
		<input type="text" name="username" value=""  class="form-control" placeholder="username" required autofocus> 
		<input type="password" name="password" value="" class="form-control" placeholder="" required> 
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	</form>
	
	
	
	
	<script type="text/javascript">
	
/* 	$(document).ready(function(){
		
		if(window.location.href.indexOf("error") != -1){
			$('#errMsg').css('display','block');
		  }
	
	}); */
	
	
	
	</script>
