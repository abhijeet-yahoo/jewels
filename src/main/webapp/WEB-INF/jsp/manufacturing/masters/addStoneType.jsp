<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">StoneType Master</span>
	</div>

	<form:form commandName="stoneType"
		action="/jewels/manufacturing/masters/stoneType/add.html"
		cssClass="form-horizontal stoneTypeForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">StoneType ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">StoneType
				Name :</label>
			<div class="col-sm-8">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Code :</label>
			<div class="col-sm-8">
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
			</div>
		</div>
		
		<%-- <div class="form-group">
			<label for="transferRatePerc" class="col-sm-2 control-label">Transfer Rate Perc :</label>
			<div class="col-sm-8">
				<form:input path="transferRatePerc" cssClass="form-control" />
				<form:errors path="transferRatePerc" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="factoryRatePerc" class="col-sm-2 control-label">Factory Rate Perc :</label>
			<div class="col-sm-8">
				<form:input path="factoryRatePerc" cssClass="form-control" />
				<form:errors path="factoryRatePerc" />
			</div>
		</div> --%>
		
		
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
						href="/jewels/manufacturing/masters/stoneType.html">StoneType Listing</a>
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
						$(".stoneTypeForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
						
													remote : {
														url : "<spring:url value='/manufacturing/masters/stoneTypeAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
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
													remote : "StoneType already exists"
												}
											}
										});
					});
</script>