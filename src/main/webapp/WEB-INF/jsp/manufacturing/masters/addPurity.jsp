<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Purity Master</span>
	</div>

	<form:form commandName="purity"
		action="/jewels/manufacturing/masters/purity/add.html"
		cssClass="form-horizontal purityForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Purity ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="concept" class="col-sm-2 control-label">Metal :</label>
			<div class="col-xs-3 selectContainer">
				<form:select path="metal.id" class="form-control">
					<form:option value="" label="--- Select Metal ---" />
					<form:options items="${metalList}" />
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name :</label>
			<div class="col-sm-9">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>

		<div class="form-group">
			<label for="waxWtConv" class="col-sm-2 control-label">Wax Wt
				Conv :</label>
			<div class="col-sm-9">
				<form:input path="waxWtConv" cssClass="form-control" />
				<form:errors path="waxWtConv" />
			</div>
		</div>


		<div class="form-group">
			<label for="purityConv" class="col-sm-2 control-label">Purity
				Conv :</label>
			<div class="col-sm-9">
				<form:input path="purityConv" cssClass="form-control" />
				<form:errors path="purityConv" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="vPurity" class="col-sm-2 control-label">Purity :</label>
			<div class="col-sm-9">
				<form:input path="vPurity" cssClass="form-control" />
				<form:errors path="vPurity" />
			</div>
		</div>

		<div class="form-group">
			<label for="Status" class="col-sm-2 control-label">De-Active:</label>

			<div class="col-sm-10">
				<form:checkbox path="deactive" value="0" />
			</div>
		</div>

		<div class="form-group">
			<label for="defPurity" class="col-sm-2 control-label">Default
				Purity :</label>
			<div class="col-sm-10">
				<form:checkbox path="defPurity" value="0" />
			</div>
		</div>

		<div class="form-group">
			<label for="pure" class="col-sm-2 control-label">Pure :</label>
			<div class="col-sm-10">
				<form:checkbox path="pure" value="0" />
			</div>
		</div>

		
		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/purity.html">Purity Listing</a>
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
						$(".purityForm")
								.validate(
										{
											rules : {
												'metal.id' : {
													required : true
												},
												name : {
													required : true,
												
												},
												pure : {
													required : false,
													remote : {
														url : "<spring:url value='/manufacturing/masters/pure/chk.html' />",
														type : "get",
														data : {
															metal : function() {
																return $(
																		"#metal\\.id")
																		.val();
															},
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},

												defPurity : {
													required : false,
													remote : {
														url : "<spring:url value='/manufacturing/masters/defaultPurity/chk.html' />",
														type : "get",
														data : {
															metal : function() {
																return $(
																		"#metal\\.id")
																		.val();
															},
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
													remote : " Purity already exists"
												},
												pure : {
													remote : " Pure already exists"
												},
												defPurity : {
													remote : " Default Purity already exists"
												}
											}
										});

						function setFocus() {
							$("#name").focus();
						}

						setFocus();
					});
</script>
