<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Repair Barcode Linking</span>
	</div>

<div class="form-group">
	<form:form commandName="tranMt" id="transferToTranMt"
			action="/jewels/marketing/transactions/barcodeAttachToStock.html"
		cssClass="form-horizontal tranMtForm">

		<c:if test="${success eq true}">
			<div class="col-xs-12">
				<div class="row">
					&nbsp;
					<div class="alert alert-success">Data Transfered  ${action}
						successfully!</div>
				</div>
			</div>
		</c:if>


		<div class="row">

			<div class="col-xs-12">&nbsp;</div>
		</div>
		



		<div class="form-group">
			<label class="col-lg-1 col-sm-2 text-right">Order No</label>
			<div class="col-lg-2 col-sm-2">
				<form:input path="orderMt.invNo" cssClass="form-control"
					 onBlur="javascript:popBagMt();" />
				<form:errors path="orderMt.invNo" />
			</div>
		</div>
		
		
		
		
<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="tranId" data-toggle="table" data-toolbar="#toolbarDt"
						 data-height="350">
						<thead>
							<tr class="btn-primary">							
								<th data-field="state"  data-checkbox="true"></th>
								<th data-field="name" data-sortable="true">Bag No.</th>
								<th data-field="barcode" data-sortable="true">Barcode</th>
								<th data-field="styleNo" data-sortable="true">Style No.</th>
								<th data-field="ktColor" data-sortable="true">Kt Color </th>
								<th data-field="qty" data-sortable="true">Qty.</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
		
		

		
		<div class="form-group">
				<div class="row">
			<div class="col-xs-12">
			<div class="col-lg-1 col-sm-1">
				<input  value="Attach" class="btn btn-primary" id="transferBtnId" onClick="javascript:repairBarcodeAttach()" />
				<input type ="hidden" name="trfTblData" id="trfTblData"/>				
				</div>
				</div>
				</div>
		</div>
				
		
	</form:form>
</div>




</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".tranMtForm").validate(
						{
							rules : {
								'department.id' : {
									required : true,
									
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
								'department.id' : {
									remote : "Department Not Selected "
								}
							}
						});
				
				$("#orderMt\\.invNo").autocomplete({
					source: "<spring:url value='/manufacturing/transactions/tran/bag/list.html' />",
					minLength : 2
				});

			});

	function popBagMt() {
		$("#tranId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/setOpening/listing.html?pInvNo="
									+ $("#orderMt\\.invNo").val()+"&opt=2"
						});
	}
	
	
	

	function repairBarcodeAttach(){
		
		
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
		
		$('#transferBtnId').attr('disabled', 'disabled');
		
		var data = $('#tranId').bootstrapTable('getSelections');
		
		var tblSeldata =JSON.stringify(data);
		
					
	$.ajax({
		url : "/jewels/marketing/transactions/barcodeAttachToStock.html",
		type : "POST",
		data : {
			bagTblData : tblSeldata
		},
		
		success : function(data, textStatus, jqXHR) {
			
			 if(data == "1"){
				 popBagMt();
				 displayInfoMsg(this, null, 'Repair Barcode Attach Successfully !');
				 
			}else{
				 displayMsg(this,null,"Barcode Not Found In Stock");	
			}
			 
			 
				$('#transferBtnId').removeAttr('disabled');
				
				$.unblockUI();
			
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			displayMsg(this,null,"Error Occoured Contact Admin");
			$.unblockUI();
			}
		
	});
		
		
		
		
	}

		
	
	
	
	
</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>


<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />	
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>