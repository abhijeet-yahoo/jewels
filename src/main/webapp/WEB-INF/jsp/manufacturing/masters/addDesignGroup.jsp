<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Design Group Master</span>
	</div>

	<form:form commandName="designGroup"
		action="/jewels/manufacturing/masters/designGroup/add.html"
		cssClass="form-horizontal designGroupForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Design Group ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>


		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Design Group Name :</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>

		<c:if test="${canEdit && canDelete}">
		<div class="form-group">
			<label for="Status" class="col-sm-2 control-label">De-Active
				:</label>
			<div class="col-sm-10">
				<form:checkbox path="deactive" value="0" />
			</div>
		</div>
		</c:if>
		
		<c:if test="${canAdd || canEdit}">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Save" class="btn btn-primary" /> 
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/designGroup.html">Design Group Listing</a>
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
						$(".designGroupForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													 minlength : 1, 
													remote : {
														url : "<spring:url value='/manufacturing/masters/designGroupAvailable.html' />",
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
													remote : "Design Group already exists"
												}
											}
										});
					});
</script>