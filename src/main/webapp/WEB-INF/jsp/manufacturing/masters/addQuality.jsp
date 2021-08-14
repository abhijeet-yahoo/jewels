<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Quality Master</span>
	</div>

	<form:form commandName="quality"
		action="/jewels/manufacturing/masters/quality/add.html"
		cssClass="form-horizontal qualityForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Quality ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
			<div class="form-group">
			<label for="concept" class="col-sm-2 control-label">Party
				:</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="party.id" class="form-control">
					<form:option value="" label="--- Select Party ---" />
					<form:options items="${partyMap}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="concept" class="col-sm-2 control-label">Shape
				:</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="shape.id" class="form-control">
					<form:option value="" label="--- Select Shape ---" />
					<form:options items="${shapeList}" />
				</form:select>
			</div>
		</div>
		
				<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Group :</label>
			<div class="col-sm-9">
				<form:input path="qualityGroup" cssClass="form-control" />
				<form:errors path="qualityGroup" />
			</div>
		</div>
		

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Quality
				Name :</label>
			<div class="col-sm-9">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>

		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Quality
				Code :</label>
			<div class="col-sm-9">
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Int Quality :</label>
			<div class="col-sm-9">
				<form:input path="intQuality" cssClass="form-control" />
				<form:errors path="intQuality" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">Description :</label>
			<div class="col-sm-9">
				<form:input path="description" cssClass="form-control" />
				<form:errors path="description" />
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
					<input type="submit" value="Save" class="btn btn-primary" id="qualitySubmitBtn" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/quality.html">Quality
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
						$(".qualityForm")
								.validate(
										{
											rules : {
												'shape.id' : {
													required : true
												},
												name : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/masters/qualityAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															},
														
															shape : function() {
																return $("#shape\\.id").val();
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
													remote : "Quality already exists"
												}
											}
										});

						$("input:text:visible:first").focus();
					});


	 $(document)
		.on(
			'submit',
			'form#quality',
			 function(e){
				 $("#qualitySubmitBtn").prop("disabled", true).addClass("disabled");
			});

		
</script>
