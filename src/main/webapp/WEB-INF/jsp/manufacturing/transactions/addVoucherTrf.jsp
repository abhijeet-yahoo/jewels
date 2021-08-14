<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Voucher Wise Transfer Entry</span>
	</div>

	<div class="panel-body">
		
		<form:form commandName="voucherTrfMt" id="voucherTrfMtId"
					action="/jewels/manufacturing/transactions/voucherTrfMt/add.html"
				cssClass="voucherTrfMtForm">



<div class="row">
	<!-- <div class="col-sm-12"> -->

	<div class="col-sm-12">
	
		<div class="col-md-1"></div>
					
					<div class="form-group col-md-5">
							 <label class="control-label" for="">Voucher No.</label>
								<form:input path="voucherno" cssClass="form-control"/>
								<form:errors path="voucherno" /> 
								 
								
					</div>	
							
				  
	
		
					<div class="col-md-1"></div>
								<div class="form-group col-md-5">
								<label class="control-label" for="">Voucher Date</label>
								<form:input path="voucherdate" cssClass="form-control" />
								<form:errors path="voucherdate" />
					</div>

					<div class="col-md-1"></div>
						<div class="form-group col-md-5">
							<label class="control-label" for="dept">Department From</label>
								<form:select path="deptFrom.id" id="deptFrom"
									class="form-control" onChange="javascript:popDeptFrom();">
									<form:option value="" label=" Select department " />
									<form:options items="${departmenttMap}" />
								</form:select>
					   </div>	
					   
				    
				    <div class="col-md-1"></div>
						<div class="form-group col-md-5">			
						<label class="control-label" for="dept">Department To</label>
							<form:select path="deptTo.id" id="deptToo" class="form-control" >
								<form:option value="" label=" Select department " />
								<form:options items="${departmenttMap}" />
							</form:select>
						</div>
						
						
						
					   <div class="col-md-1"></div>
									<div class="form-group col-md-5">
								<label class="control-label" for="bagNo">Bag No.</label>
								
								<form:input path="bagMt.name" Class="form-control"/>
								<form:errors path="bagMt.name" />
				     </div>
						
						</div>
					
					
					<div class="col-xs-12">
						   <div class="col-md-1"></div> 
						
						<input type="button"  class="btn btn-primary" value="Add">
					</div>	
						
			
	 </div>
					
		</form:form>				
		</div>		
	</div>				
	
		<script type="text/javascript">
		
		$(document)
		.ready(
				function(e) {
					
					$("#voucherdate").datepicker({
					dateFormat : 'dd/mm/yy'
				});
		
					$("#bagMt\\.name")
							.autocomplete(
									{
										source : "<spring:url value='/manufacturing/transactions/transfer/list.html' />",
										minLength : 2
									});
		
				});
		
		
		
			
		function popDeptFrom() {
			$.ajax({
						url : '<spring:url value='/manufacturing/transactions/transfer/deptTo.html' />?departmentId='
								+ $('#deptFrom').val(),
						type : 'GET',
						success : function(data) {
							$("#deptToo").html(data);
							  
				
							
		
						}
					});
		}
			
		function popTranDt() {
			if($('#deptFroom').val() !=''){
				
				$("#multiBagTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/multiBagTransfer/listing.html?departmentId="
								+ $('#deptFroom').val(),
						}); 
				}
				 }
			
	
	</script>



<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

		