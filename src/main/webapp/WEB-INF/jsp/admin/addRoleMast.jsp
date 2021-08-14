<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Role Master</span>
	</div>

	<form:form commandName="roleMast"
		action="/jewels/admin/roleMast/add.html"
		cssClass="form-horizontal roleMastForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Role ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>


		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Role Name :</label>
			<div class="col-sm-4">
				<form:input path="roleNm" cssClass="form-control" />
				<form:errors path="roleNm" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Save" class="btn btn-primary" /> 
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/admin/roleMast.html">Role Mast Listing</a>
				<form:input type="hidden" path="id" />
			</div>
		</div>
		
	</form:form>
</div>


<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$(".roleMastForm")
								.validate(
										{
											rules : {
												roleNm : {
													required : true,
													 minlength : 1, 
													remote : {
														url : "<spring:url value='/admin/roleMast/available.html' />",
														type : "get",
														data : {
															
															id : function() {
																return $("#id").val();
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
													remote : "Role Mast already exists"
												}
											}
										});
					});
</script>