<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Inward Type</span>
	</div>

	<form:form commandName="inwardType"
		action="/jewels/manufacturing/masters/inwardType/add.html"
		cssClass="form-horizontal inwardTypeForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Inward Type ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Inward Type
				Name :</label>
			<div class="col-sm-3">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Code :</label>
			<div class="col-sm-3">
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="handlingPercentage" class="col-sm-2 control-label">Handling % :</label>
			<div class="col-sm-3">
				<form:input path="handlingPercentage" cssClass="form-control" />
				<form:errors path="handlingPercentage" />
			</div>
		</div>

		<c:if test="${canAdd && canDelete}">
			<div class="form-group">
				<label for="Status" class="col-sm-2 control-label">De-Active
					:</label>
				<div class="col-sm-3">
					<form:checkbox path="deactive" value="0" />
				</div>
			</div>
		</c:if>
		
		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/inwardType.html">Inward Type
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
						$(".inwardTypeForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													minlength : 1,
													remote : {
														url : "<spring:url value='/manufacturing/masters/inwardTypeAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},
												
											handlingPercentage : {
													required : true,
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
													remote : "Inward Type already exists"
												}
											}
										});
					});
</script>