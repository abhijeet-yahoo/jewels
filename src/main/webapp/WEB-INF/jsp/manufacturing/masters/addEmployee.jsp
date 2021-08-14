<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Employee Master</span>
	</div>

	<form:form commandName="employee"
		action="/jewels/manufacturing/masters/employee/add.html"
		cssClass="form-horizontal employeeForm">

		<c:if test="${success eq true}">
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Employee ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="department" class="col-sm-2 control-label">Department
				:</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="department.id" class="form-control" >
					<form:option value="" label="--- Select Department ---" />
					<form:options items="${departmentList}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Employee
				Name :</label>
			<div class="col-sm-3">
				<form:input path="name" cssClass="form-control" autocomplete="off" />
				<form:errors path="name" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Code :</label>
			<div class="col-sm-3">
				<form:input path="code" cssClass="form-control" autocomplete="off" />
				<form:errors path="code" />
			</div>
		</div>

		<div class="form-group">
			<label for="department" class="col-sm-2 control-label">Designation
				:</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="lookUpMast.id" class="form-control" >
					<form:option value="" label="--- Select Designation ---" />
					<form:options items="${designationMap}" />
				</form:select>
			</div>
		</div>

		<c:if test="${canEdit && canDelete}">
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
						href="/jewels/manufacturing/masters/employee.html">Employee Listing</a>
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
						$(".employeeForm")
								.validate(
										{
											rules : {
												'department.id' : {
													required : true
												},
												name : {
													required : true,
													minlength : 1,
													maxlength : 25,
													remote : {
														url : "<spring:url value='/manufacturing/masters/employeeAvailable.html' />",
														type : "get",
														data : {
															id : function() { 
																return $("#id").val();
															},
															deptId : function() { 
																return $("#department\\.id").val();	
															}
														}
													}
												}
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
													remote : "Employee already exists"
												}
											}
										});

						$("input:text:visible:first").focus();
					});
</script>
