<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">SettingType Master</span>
	</div>

	<form:form commandName="settingType"
		action="/jewels/manufacturing/masters/settingType/add.html"
		cssClass="form-horizontal settingTypeForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">SettingType ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">SettingType
				Name :</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>

		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Code :</label>
			<div class="col-sm-10">
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
			</div>
		</div>

	<c:if test="${canAdd && canDelete}">
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
				<input type="submit" value="Save" class="btn btn-primary" /> <a
					class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/settingType.html">SettingType
					Listing</a>
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
						$(".settingTypeForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													
													remote : {
														url : "<spring:url value='/manufacturing/masters/settingTypeAvailable.html' />",
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
													remote : "SettingType already exists"
												}
											}
										});
					});
</script>