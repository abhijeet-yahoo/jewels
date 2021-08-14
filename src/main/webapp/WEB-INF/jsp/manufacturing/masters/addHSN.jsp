 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">HSN</span>
	</div>

	<form:form commandName="hSNMast" id="hSNMastFormId"
		action="/jewels/manufacturing/masters/hSNMast/add.html"
		cssClass="form-horizontal hSNMastForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">HSN Master ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		 		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Code :</label>
			<div class="col-sm-3">
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Description
				:</label>
			<div class="col-sm-3">
				<form:input path="desc" cssClass="form-control" />
				<form:errors path="desc" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="hsnNo" class="col-sm-2 control-label">HSN No.
				:</label>
			<div class="col-sm-3">
				<form:input path="hsnNo" cssClass="form-control" />
				<form:errors path="hsnNo" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="cGST" class="col-sm-2 control-label">C GST%</label>
			<div class="col-sm-3">
				<form:input path="cGST" cssClass="form-control" tyep="number" autocomplete="off"/>
				<form:errors path="cGST" />
			</div>
		</div>
	
	
	<div class="form-group">
			<label for="sGST" class="col-sm-2 control-label">S GST%</label>
			<div class="col-sm-3">
				<form:input path="sGST" cssClass="form-control" tyep="number" autocomplete="off"/>
				<form:errors path="sGST" />
			</div>
		</div>
	
	
	<div class="form-group">
			<label for="iGST" class="col-sm-2 control-label">I GST%</label>
			<div class="col-sm-3">
				<form:input path="iGST" cssClass="form-control" tyep="number" autocomplete="off"/>
				<form:errors path="iGST" />
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
						href="<spring:url value='/manufacturing/masters/hSNMast.html' />">Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
		
	</form:form>
	
	
				
				
				
	
</div>
<script type="text/javascript">
$(document).ready(
		function(e) {
			$(".hSNMastForm").validate(
				{
					
				rules : {
					code : {
						required : true,
						
						remote : {
							url : "<spring:url value='/manufacturing/masters/hSNMastCodeAvailable.html' />",
							type : "get",
							data : {
								id : function() {
									return $("#id")
											.val();
								}
							}
						}
					},
				
					desc : {
						required : true
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
							
								code : {
									remote : "HSN Code already exists"
								}
							}
						});


							
				
				});
					
			
	
</script>