<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">User Master</span>
	</div>

	<form:form commandName="user" action="/jewels/admin/user/register.html"
		cssClass="form-horizontal userForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">User ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name :</label>
			<div class="col-sm-8">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
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
				<label for="supervisor" class="col-sm-2 control-label"></label>
				<div class="col-sm-8">
					<label> <form:checkbox path="supervisor" /> <b>Supervisor</b></label>
				</div>
			</div>
		

		

		<%-- <c:if test="${canEdit && canDelete}"> --%>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label"></label>
				<div class="col-sm-8">
					<label> <form:checkbox path="deactive" /> <b>De-active</b></label>
				</div>
			</div>
		<%-- </c:if> --%>

		<%-- <c:if test="${canAdd || canEdit}"> --%>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/admin/users.html">User Listing</a>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="roleMast.id" />
				</div>
			</div>
		<%-- </c:if> --%>
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
												name : {
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
												},
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
											},
											messages : {
												name : {
													remote : "Username already exists"
												}
											}
										});

						$("input:text:visible:first").focus();

					});
</script>