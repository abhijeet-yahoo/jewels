 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Branch </span>
	</div>

	<form:form commandName="branchMaster"
		action="/jewels/manufacturing/masters/branchMaster/add.html"
		cssClass="form-horizontal branchMasterForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Branch ${action} successfully!</div>
			
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>


		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Branch :</label>
			<div class="col-sm-3">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">Code
				:</label>
			<div class="col-sm-3">
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="officePhone" class="col-sm-2 control-label">Contact No
				:</label>
			<div class="col-sm-3">
				<form:input path="officePhone" cssClass="form-control" />
				<form:errors path="officePhone" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="faxNo" class="col-sm-2 control-label">Fax No
				:</label>
			<div class="col-sm-3">
				<form:input path="faxNo" cssClass="form-control" />
				<form:errors path="faxNo" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="address" class="col-sm-2 control-label">Address
				:</label>
			<div class="col-sm-3">
				<form:input path="address" cssClass="form-control" />
				<form:errors path="address" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="area" class="col-sm-2 control-label">Transport Area
				:</label>
			<div class="col-sm-3">
				<form:input path="area" cssClass="form-control" />
				<form:errors path="area" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="zone" class="col-sm-2 control-label">Zone
				:</label>
			<div class="col-sm-3">
				<form:input path="zone" cssClass="form-control" />
				<form:errors path="zone" />
			</div>
		</div>
		
			<div class="form-group">
			<label for="city" class="col-sm-2 control-label">City
				:</label>
			<div class="col-sm-3">
				<form:input path="city" cssClass="form-control" />
				<form:errors path="city" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="zip" class="col-sm-2 control-label">Zip
				:</label>
			<div class="col-sm-3">
				<form:input path="zip" cssClass="form-control" />
				<form:errors path="zip" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="country" class="col-sm-2 control-label">Country
				:</label>
			<div class="col-sm-3">
				<form:select path="country.id" class="form-control" 
				style="width: 100%;" onChange="javascript:popState(this.value);">
				<form:option value="" label="Select Country" />
				<form:options items="${countryMap}" />
				</form:select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="stateMast" class="col-sm-2 control-label">State
				:</label>
			<div class="col-sm-3">
				<form:select path="stateMast.id" class="form-control" 
				style="width: 100%;" >
				<form:option value="" label="Select State" />
				<form:options items="${stateMap}" />
				</form:select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="gst" class="col-sm-2 control-label">GST
				:</label>
			<div class="col-sm-3">
				<form:input path="gst" cssClass="form-control" />
				<form:errors path="gst" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="panNo" class="col-sm-2 control-label">Pan No
				:</label>
			<div class="col-sm-3">
				<form:input path="panNo" cssClass="form-control" />
				<form:errors path="panNo" />
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
						href="<spring:url value='/manufacturing/masters/branchMaster.html' />">Branch Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
		
	</form:form>
	
	
				
				
				
	
</div>


<script type="text/javascript">
$(document).ready(
		function(e) {
			$(".branchMasterForm").validate(
				{
					
				rules : {
					name : {
						required : true,
	
						remote : {
							url : "<spring:url value='/manufacturing/masters/branchMasterAvailable.html' />",
							type : "get",
							data : {
								id : function() {
									return $("#id")
											.val();
								}
							}
						}
					},
					
					code : {
						required : true,
						
						remote : {
							url : "<spring:url value='/manufacturing/masters/branchMasterCodeAvailable.html' />",
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
									remote : "Branch already exists"
								},
								code : {
									remote : "Branch Code already exists"
								}
							}
						});


							
				
				});
					


function popState(id){
	 
	 $
		.ajax({
			url : "/jewels/manufacturing/masters/state/list.html?countryId="
					+ id,
			type : 'GET',
			success : function(data) {
				$("#stateMast\\.id").html(data);
				
			}
		});
}
	
</script>

