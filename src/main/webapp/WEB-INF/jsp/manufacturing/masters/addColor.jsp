 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Color Master</span>
	</div>

	<form:form commandName="color"
		action="/jewels/manufacturing/masters/color/add.html"
		cssClass="form-horizontal colorForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Color ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>


		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Color Name :</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<label for="desc" class="col-sm-2 control-label">Description
				:</label>
			<div class="col-sm-10">
				<form:input path="desc" cssClass="form-control" />
				<form:errors path="desc" />
			</div>
		</div>
		<div class="form-group">
			<label for="twoTone" class="col-sm-2 control-label">Two Tone
				:</label>
			<div class="col-sm-10">
				<form:input path="twoTone" cssClass="form-control" />
				<form:errors path="twoTone" />
			</div>
		</div>
		<div class="form-group">
			<label for="colorTone" class="col-sm-2 control-label">Color
				Tone :</label>
			<div class="col-sm-10">
				<form:input path="colorTone" cssClass="form-control" />
				<form:errors path="colorTone" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="defColor" class="col-sm-2 control-label">Default
				Color :</label>
			<div class="col-sm-10">
				<form:checkbox path="defColor" value="0" />
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
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="<spring:url value='/manufacturing/masters/color.html' />">Color Listing</a>
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
						$(".colorForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													 minlength : 1, 
													remote : {
														url : "<spring:url value='/manufacturing/masters/colorAvailable.html' />",
														type : "get",
														data : {
															
															id : function() {
																return $("#id").val();
															}
														}
													}
												},
												defColor : {
													required : false,
													remote : {
														url : "<spring:url value='/manufacturing/masters/defColorAvailable.html' />",
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
													remote : "Color already exists"
												},
												defColor :{
													remote :"Default Color Already Exists"
												}
											}
										});
						
					});
</script>

