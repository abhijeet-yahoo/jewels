<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
	
</style>


    <div class="modal fade" id="myJobIssModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Job Work Pickup</h4>
			</div>

			<div class="modal-body">
			
			
	<div class="col-xs-12">
		<div class="row" >
				
					<table id="jobIssMtPickupTblId" data-toggle="table" data-click-to-select="true"
						 data-side-pagination="server"	data-height="150">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="invNo" data-sortable="true">Voucher No</th>
								<th data-field="invDate" data-sortable="true">Voucher Date</th>
								<th data-field="party" data-sortable="true">Party</th>
								
							</tr>
						</thead>
					</table>
				</div>
				
				<h4 align="center">Bag Details</h4>
				<div class="row" style="display: none"  id="jobIssDtPickupDivId">
				
				<table id="jobIssDtPickupTblId" data-toggle="table" 
						 data-side-pagination="server"	data-height="320">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="orderNo" data-sortable="true">Order No</th>
								<th data-field="bagNm" data-sortable="true">Bag No</th>
								<th data-field="mainStyleno" data-sortable="true">Style No</th>
								<th data-field="pcs" data-sortable="true">Pcs</th>
								<th data-field="grossWt" data-sortable="true">Gross Wt</th>
							</tr>
						</thead>
					</table>
				
				</div>
			
		</div>
		
		
		
		
		

			
		<div class="modal-footer">
		
			<input type="button" value="Save" id="jobIssTransferBtnId" class="btn btn-primary" onclick="javascript:jobIssTransfer()"/>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
					<input type="hidden" id="jobRechiddenMtId" name="jobRechiddenMtId" />
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
<script type="text/javascript">

$(document).ready(
		function(e) {
			
		
			
			
		});
		
		
		var jobIssTableRow;
		var mtid;
		$('#jobIssMtPickupTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					InwardStnTableRow = $element.attr('data-index');
					mtid = jQuery.parseJSON(JSON.stringify(row)).mtId;
					
					$('#jobIssDtPickupDivId').css('display','block');
					getJobIssDtPickupList(mtid);
				})

	
			
	
	function getJobIssDtPickupList(mtid){		
		$("#jobIssDtPickupTblId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/jobIssDt/pickupListing.html?mtId="
							+mtid
				});
	}
		
		
		function jobIssTransfer(){
			
			
			
			$('#jobIssTransferBtnId').attr('disabled', 'disabled');
			
			
			
			if(Number($("#jobIssDtPickupTblId").bootstrapTable('getSelections').length) > 0){
				
				 var data = JSON.stringify($("#jobIssDtPickupTblId").bootstrapTable('getSelections'));
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/jobIssDt/saveJobRecPickup.html",
						type : "POST",
						data : {
							pMtid :  $('#jobRechiddenMtId').val(),
							data        : data,
						},
						success : function(data, textStatus, jqXHR) {
							
							if(data === "1"){
								displayInfoMsg(this,null,"Data Transfered Successfully");
								getJobIssDtPickupList(mtid);
								//popJobIssDetails();
								popJobRecDetails()
								
								 $('#jobIssTransferBtnId').removeAttr('disabled');
							}
							
							
						//	$('#myStnInwOrderStnModal').modal('hide');
							
						
							
						},
						error : function(jqXHR, textStatus, errorThrown) {
							$('#jobIssTransferBtnId').removeAttr('disabled');
						}
					});
				 
				
			
			
			}else{
				displayMsg(this,null,"Record Not Selected");
				$('#jobIssTransferBtnId').removeAttr('disabled');
			}
			
			
			
		}
		
		
		


</script>			
			

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>			
			
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />