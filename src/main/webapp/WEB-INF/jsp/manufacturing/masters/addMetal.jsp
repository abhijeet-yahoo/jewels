<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Metal Master</span>
	</div>

	<form:form commandName="metal"
		action="/jewels/manufacturing/masters/metal/add.html"
		cssClass="form-horizontal metalForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Metal ${action} successfully!</div>
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

		<c:if test="${canAdd && canDelete}">
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label"></label>
				<div class="col-sm-8">
					<label> <form:checkbox path="deactive" /> <b>De-active</b></label>
				</div>
			</div>
		</c:if>

		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/metal.html">Metal Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
	</form:form>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$(".metalForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													minlength : 1,
													remote : {
														url : "<spring:url value='/manufacturing/masters/metalAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
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
													remote : "Metal already exists"
												}
											}
										});

						function setFocus() {
							$("#name").focus();
						}

						setFocus();

					});

	</script>