<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Category Master</span>
	</div>

	<form:form commandName="category"
		action="/jewels/manufacturing/masters/category/add.html"
		cssClass="form-horizontal categoryForm">

		<c:if test="${success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="alert alert-success">Category ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name :</label>
			<div class="col-sm-8">
				<form:input path="name" cssClass="form-control" autocomplete="off"/>
				<form:errors path="name" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Code :</label>
			<div class="col-sm-8">
				<form:input path="categCode" cssClass="form-control" autocomplete="off"/>
				<form:errors path="categCode" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="labourRate" class="col-sm-2 control-label">Labour Rate :</label>
			<div class="col-sm-8">
				<form:input path="labourRate" cssClass="form-control" autocomplete="off"/>
				<form:errors path="labourRate" />
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
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/masters/category.html">Category Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(
		function(e) {
			$(".categoryForm").validate(
			{
				rules : {
					name : {
						required : true,
						minlength : 1,
						remote : {
							url : "<spring:url value='/manufacturing/masters/categoryAvailable.html' />",
							type : "get",
							data : {
								id : function() {
									return $("#id")
											.val();
								}
							}
						}
					},
					categCode : {
						required : true,
						minlength : 1,
						remote : {
							url : "<spring:url value='/manufacturing/masters/categoryCodeAvailable.html' />",
							type : "get",
							data : {
								id : function() {
									return $("#id").val();
								}
							}
						}
					}
				},
				highlight : function(element) {
					$(element).closest('.form-group').removeClass(
							'has-success').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass(
							'has-error').addClass('has-success');
				},
				messages : {
					name : {
						remote : "Category already exists"
					},
					categCode : {
						remote : "Category Code already exists"
					}
				}
			});



			if (window.location.href.indexOf('edit') != -1) {

				if('${adminRightsMap}' === 'false'){
				$('#labourRate').attr('readonly','readonly');
				}	
			}
			else{
				
			}

			$("input:text:visible:first").focus();
		});
</script>