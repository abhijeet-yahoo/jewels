 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Design Mold Master</span>
	</div>
	
	<div class="panel-body">
	
		
		<form:form commandName="designMoldType"
		action="/jewels/manufacturing/masters/designMoldType/add.html"
		cssClass="form-horizontal designMoldTypeForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">DesignMoldType ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>


		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Design Mold Type Name :</label>
			<div class="col-sm-5">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>

		<div class="form-group">
			<label for="Status" class="col-sm-2 control-label">De-Active
				:</label>
			<div class="col-sm-5">
				<form:checkbox path="deactive" value="0" />
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Save" class="btn btn-primary" /> 
				<a class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/designMoldType.html">DesignMoldType Listing</a>
				<form:input type="hidden" path="id" />
			</div>
		</div>
		
	</form:form>
	
	</div>
	
</div>



<script type="text/javascript">

	$(document)
			.ready(function(e){
				
						$(".designMoldTypeForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													 minlength : 1, 
													remote : {
														url : "<spring:url value='/manufacturing/masters/designMoldTypeAvailable.html' />",
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
													remote : "Design Mold Type already exists"
												}
											}
										});
						
					});
	
	
</script>













