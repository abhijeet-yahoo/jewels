<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Reset Password</span>
	</div>

	<form:form commandName="user" action="/jewels/admin/user/passwordReset.html"
		cssClass="form-horizontal userForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Password ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

	
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password
				:</label>
			<div class="col-sm-8">
				<form:password path="password" cssClass="form-control" />
				<form:errors path="password" />
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Re-enter
				Password :</label>
			<div class="col-sm-8">
				<input type="password" name="re_password" id="re_password"
					class="form-control" />
			</div>
		</div>
		
		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> 
		
					<form:input type="hidden" path="id" value="${userId}"/>

				</div>
			</div>
	</form:form>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$(".userForm")
								.validate(
										{
											rules : {
										/* 		name : {
													required : true,
													minlength : 3,
													remote : {
														url : "<spring:url value='/admin/userAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												}, */
												password : {
													required : true,
													minlength : 5
												},
												re_password : {
													required : true,
													minlength : 5,
													equalTo : "#password"
												},
												
											},
											highlight : function(element) {
												$(element).closest(
														'.form-group')
														.removeClass(
																'has-success')
														.addClass('has-error');
											},
											unhighlight : function(element) {
												$(element)
														.closest('.form-group')
														.removeClass(
																'has-error')
														.addClass('has-success');
											}
										
										});

						$("input:text:visible:first").focus();

					});
</script>