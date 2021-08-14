<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Stock Receive Approval</span>
	</div>
	
	
	<div class="panel-body">
	
	
	<form:form commandName="stkTrfMt" id="stkTrfPendingApprovalDivId"
					action="/jewels/marketing/transactions/stkTrfPendingApproval.html"
				cssClass="transferForm">

				<div class="row">
				
				<div class="row">
				<div>&nbsp;</div>
			</div>
			
				<div class="col-sm-3">
					<label class="control-label" for="location">Location</label>
							<div class="form-group">

								 
								<form:select path="location.id"  class="form-control" onChange="javascript:popTransferPendingList();">
											<form:option value="" label="- Select Location -" />
												<form:options items="${locationMap}" />
										</form:select>

								</div>
								</div>	
								
								
				<div class="col-sm-offset-1 col-sm-2">
					<label class="control-label" for="trfDept"></label> 
					<div class="form-group">
							<div>
								<input type="submit" value="Approve" class="btn btn-primary" id= "pendingApprovalBtnId"/> 
					
					<input type="hidden" id="pDtIds" name="pDtIds" />
					
					
					
							</div>
						</div>
					
					</div>	
									
					</div>
					
		
			
				</form:form>
				
				
	<div class="row">
				<div>&nbsp;</div>
			</div>
			
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Pending Approval List</span>
		</div>
	</div>
	
	
			<div>
						<table id="stkTrfPendlingTbl" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-striped="true">
							<thead>
								<tr class="btn-primary">
										<th data-field="stateRd" data-checkbox="true"></th>  
									
										<th data-field="invNo">Inv No</th>
										<th data-field="invDate">Inv Date</th>
										<th data-field="barcode">Barcode</th>
										<th data-field="mainStyleNo" data-sortable="false" class="span5">Style No</th>
										<th data-field="ktCol" data-sortable="false">Kt-Col</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">Gross Wt</th>
										<th data-field="netWt" data-sortable="false">Net Wt</th>
										<th data-field="metalValue" data-sortable="false">Metal Value</th>
										<th data-field="stoneValue" data-sortable="false">Stn Value</th>
										<th data-field="compValue" data-sortable="false">Comp Value</th>
										<th data-field=labValue data-sortable="false">Lab Value</th>
										<th data-field="setValue" data-sortable="false">Set Value</th>
										<th data-field="handlingValue" data-sortable="false">Hdlg Cost</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="other" data-sortable="false">Other</th>
										<th data-field="discAmount" data-sortable="false">Disc Amt</th>
										<th data-field="finalPrice" data-sortable="false">Final Price</th>
										
									
								</tr>
							</thead>
						</table>
						
						
					</div>
	
	
	</div>
	
	</div>
	
	
	
	<script type="text/javascript">
	
	$('select').chosen();


	function popTransferPendingList(){

		if($('#location\\.id').val() != ''){

			$("#stkTrfPendlingTbl")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/marketing/transactions/stkTrf/pendingApprovalList.html?locationId="+$('#location\\.id').val()+"&trantype=STOCKTRF",
					});	
			}

		}


	$(document)
	.on(
			'submit',
			'form#stkTrfPendingApprovalDivId',
			function(e) {

				$
						.blockUI({
							message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>'
						});

				$('#pendingApprovalBtnId').attr("disabled", true);

				var data = $('#stkTrfPendlingTbl').bootstrapTable('getData');

				var ids = $.map(data, function(item) {
					return item.dtId;
				});

				$('#pDtIds').val(ids);
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

		 	$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,

							success : function(data, textStatus, jqXHR) {

								$.unblockUI();

								if(data === "1"){
									displayInfoMsg(event, this,'data transfered sucessfully !');
									$('#pendingApprovalBtnId').attr("disabled", false);
									popTransferPendingList();
									}else{
										displayMsg(event, this,'Error.. Please Contact Admin !');
										$('#pendingApprovalBtnId').attr("disabled", false);
										}	
							

							},
							error : function(jqXHR, textStatus,
									errorThrown) {

							}

						});

				e.preventDefault();

			});
	
	
	</script>
	
	<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	