<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>

<div class="panel panel-primary" style="width: 100%;">
	<div class="panel-heading">

		
		<label class="col-lg-2 col-sm-2 text-left">
				<span style="font-size: 16px; color: white;">Department Master</span>
			</label>
		
		<div class="text-right">
				
					<!-- <input type="submit" value="Save" class="btn btn-primary" /> -->
	  			<c:choose>
					<c:when test="${canAdd || canEdit}">
						<input type="button"  value="Save"  class="btn btn btn-info btn-sm" onClick="javascript:DepartmentSave();" />
					</c:when>
					
					</c:choose> 
					
					<a class="btn btn-info btn-sm" style="font-size: 12px" type="button" href="/jewels/manufacturing/masters/department.html">Department Listing</a>
					
		
		</div>

	</div>

	<form:form commandName="department"	action="/jewels/manufacturing/masters/department/add.html"
		cssClass="form-horizontal departmentForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Department ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

<div class="raw">
<div class="col-sm-12">

<div class="col-sm-12">


		<div class="form-group col-md-5">
			<label for="name" class="control-label">Department	Name :</label>
			
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			

		</div>
		
		
	<div class="col-md-1"></div>	
		<div class="form-group col-md-5">
			<label for="code" class="control-label">Code :</label>
				<form:input path="code" cssClass="form-control" />
				<form:errors path="code" />
			

		</div>
		
	
	
		<div class="form-group col-md-5 ">
			<label for="processSeq" class="control-label">Process Seq :</label>
			
				<form:input path="processSeq" class="form-control" />
				<form:errors path="processSeq" />
				</div>
		
		<div class="col-md-1"></div>
		<div class="form-group col-md-5">
			<label for="lossPerc" class="control-label">Loss% :</label>
			
				<form:input path="lossPerc" class="form-control" />
				<form:errors path="lossPerc" />
					</div>

	
	<div class="form-group col-md-5">
			<label for="extraWt" class="control-label">Extra Wt% :</label>
			
				<form:input path="extraWt" class="form-control" />
				<form:errors path="extraWt" />
					</div>
	
	<div class="col-md-1"></div>
		<div class="form-group col-md-5">
			<label for="expectedRecovery" class="control-label">Expected Recovery :</label>
			
				<form:input path="expectedRecovery" class="form-control" />
				<form:errors path="expectedRecovery" />
			
		</div>

				
		<div class="form-group col-md-5">
			<label for="process" class="control-label">Process :</label>
			
				<form:input path="process" class="form-control" />
				<form:errors path="process" />
			

		</div>	
		
		<div class="col-md-1"></div>
		 <div class="form-group col-md-5">
		 
			<label for="branchMaster" class="control-label" id="branchMasterId" >Branch :</label>
				<form:select path="branchMaster.id" class="form-control" >
					<form:option value="" label="--- Select Branch---" />
					<form:options items="${branchMasterMap}" />
				</form:select>
			</div>
				





</div>

</div>

			
							
				<div class="col-xs-12">
				
				
				
				<div class="checkbox col-md-5">
					<label><form:checkbox path="looseMetalStk" value="0" /> Loose Metal Stock</label>
				</div>
				
				
				<div class="col-md-1"></div>	
				<div class="checkbox col-md-5">
					<label><form:checkbox path="pcsProd" value="0" /> Piece Production</label>
				</div>
				
				
					
				<div class="checkbox col-md-5">
					<label><form:checkbox path="stoneStk" value="0" /> Stone Stock</label>
				</div>
				
				<div class="col-md-1"></div>
					<div class="checkbox col-md-5">
					<label><form:checkbox path="stoneProd" value="0" /> Stone Production</label>
				</div>
				
				
				<div class="checkbox col-md-5">
					<label><form:checkbox path="componentStk" value="0" /> Component Stock</label>
				</div>
				
				<div class="col-md-1"></div>
				<div class="checkbox col-md-5">
					<label><form:checkbox path="diaIssueFlag" value="0" />  Dia Issue Flag</label>
				</div>
				
				
				<div class="checkbox col-md-5">
					<label><form:checkbox path="allowZeroWt" value="0" /> Allow Zero Weight</label>
				</div>
				
				<div class="col-md-1"></div>
				<div class="checkbox col-md-5">
					<label><form:checkbox path="costing" value="0" />  Costing</label>
				</div>
				
				<div class="checkbox col-md-5">
					<label><form:checkbox path="lossBookDept" value="0" /> Loss Book</label>
				</div>
				
				<div class="col-md-1"></div>
				<div class="checkbox col-md-5">
					<label><form:checkbox path="inv" value="0" />  Invoice</label>
				</div>
				
				<div class="checkbox col-md-5">
					<label><form:checkbox path="wip" value="0" /> Wip</label>
				</div>
				
				<div class="col-md-1"></div>
					<div class="checkbox col-md-5">
					<label><form:checkbox path="vouTypeFlg" value="0" /> Voucher Type Flg</label>
				</div>
				
				
				
				
			<div class="col-md-1"></div>
				<c:if test="${canAdd && canDelete}">
					<div class="checkbox col-md-5">
					<label><form:checkbox path="deactive" value="0" />  De-Active</label>
										
					</div>
				</c:if>
				
				<div class="col-md-1"></div>
				<div class="checkbox col-md-5">
					<label><form:checkbox path="castBal" value="0" /> Wip Casting Balance</label>
				</div>	
				

				<div class="col-md-1"></div>
				<div class="checkbox col-md-5">
					<label><form:checkbox path="barcodeFlg" value="0" /> Barcode</label>
				</div>	
</div>

			
			
			
		
			
			
			
			</div>



		

	
		
		
		
				 <div class="form-group">
				
					<form:input type="hidden" path="id" />
					</div>
		
	</form:form>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {

						$('select').chosen();

						// validation of chosen on change
						if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}
						
						$(".departmentForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													
													remote : {
														url : "<spring:url value='/manufacturing/masters/departmentAvailable.html' />",
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
														url : "<spring:url value='/manufacturing/masters/departmentCodeAvailable.html' />",
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
													remote : "Department already exists"
												},
												code : {
													remote : "Department Code already exists"
												}
											}
										});
						
						
						
						
						
						
						/* if(window.location.href.indexOf("edit") != -1){
							$('#name').prop('readonly', 'readonly');
						} */
						
						
						
						if('${department.nonEditable}' =='true'){
							$('#name').prop('readonly', 'readonly');
						}
						
						
						
						
					});
	
	
	
	function DepartmentSave(){
		
		$("form#department").submit();
	}
</script>