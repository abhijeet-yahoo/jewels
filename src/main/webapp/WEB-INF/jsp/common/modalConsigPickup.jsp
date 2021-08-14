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


  <div class="modal fade" id="myConsigPickupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Pickup</h4>
			</div>
			
			
				<div class="modal-body">
					<div class="row">
					
					
					<div class="col-md-6">
								
		<div class="form-group">
			<label for="shape" class="col-sm-3 control-label">Pickup From :</label>
			<div class="col-xs-7">
				<select id="pickupFrom" class="form-control" style="background:#CEF87A" onchange="javascript:popPickupFrom(this.value)">
									<option value="">Select Pickup</option>
									<option value="packingList">Packing List</option>
									<option value="consignmentIssue">Consignment Issue</option>
								</select>
			</div>
		</div>
					
					</div>
					
					<div class="col-md-6">
					
					</div>
					
					</div>
					
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					
						<div class="row">
				
					<table id="consigMtTblId" data-toggle="table" data-click-to-select="true"
						 data-side-pagination="server"	data-height="200">
						<thead>
							<tr>
								<th data-field="state" data-radio="true">Select</th>
								<th data-field="invNo" data-sortable="true">Invoice No</th>
								<th data-field="invDate" data-sortable="true">Invoice Date</th>
								<th data-field="party" data-sortable="true">party</th>
								<th data-field="location" data-sortable="true">Location</th>
								
							</tr>
						</thead>
					</table>
				
			
		</div>
	
		<div class="row">
		
				<div id="consigDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Details</span>
				</div>
			
					<table id="consigDtTblId" data-toggle="table" 
						 data-side-pagination="server"	data-height="350">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="barcode" data-sortable="true">Barcode</th>
								<th data-field="style" data-sortable="true">Style</th>
								<th data-field="ktColor" data-sortable="true">Kt-Color</th>
								<th data-field="pcs" data-sortable="true">Pcs</th>
								<th data-field="grossWt" data-sortable="true">Gross Wt</th>
								<th data-field="netWt" data-sortable="true">Net Wt</th>
								
							</tr>
						</thead>
					</table>
			</div>
		</div>
		

			
		<div class="modal-footer">
		
					<input type="button" value="Transfer" class="btn btn-primary" id="transferBtnId"  onClick="javascript:trfData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>	
								
			
			
			
				
				</div>
			
			</div>
			
			
	</div>
	
	</div>		
	
	
	
	
		
	
	<script type="text/javascript">


	var pickupOpt;
	 $(function() {
		    $('#consigMtTblId').bootstrapTable();
		    $('#consigDtTblId').bootstrapTable();
		    
		  });


	  function popPickupFrom(val){
			setTimeout(function(){
				
				$('#consigMtTblId').bootstrapTable('resetView');
				$('#consigDtTblId').bootstrapTable('resetView');
			}, 50);
		  
		  pickupOpt = val;
				if(val == "packingList"){

					popPackingPickList();
					}else if(val == "consignmentIssue"){
						popConsigPickList();
						
						}
				$('#consigDtDivId').css('display','none');
				
		  }


	 function popPackingPickList(){

			$("#consigMtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/packingPickup/listing.html?partyId="+$('#party\\.id').val(),
				});	
				
			}

	 function popConsigPickList(){

			$("#consigMtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/consignMentPickup/listing.html?partyId="+$('#party\\.id').val()+"&tranType=sale",
				});	
				
			}



	 var consigMtId = null;
		 $('#consigMtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					consigMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#consigDtDivId').css('display','block');

					
					if(pickupOpt == "packingList"){

						popPackingDtList(consigMtId);
						}else{
							
							popConsigDtList(consigMtId);
							
							}
					
					
				});
			

		function popPackingDtList(consigMtId){
				$("#consigDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/packdtList/listing.html?mtId="+consigMtId,
				});	
				
			}


		function popConsigDtList(consigMtId){
		
				$("#consigDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/consigDtList/listing.html?mtId="+consigMtId,
				});	
				
			}



		function trfData(e){

			if(pickupOpt == "packingList"){

				displayDlg(e,'javascript:transferPackingData();', 'Transfer','Do you want to transfer?', 'Continue');
				}else{
					displayDlg(e,'javascript:transferConsigData();', 'Transfer','Do you want to transfer?', 'Continue');		
				}
			
			
		}


		function transferPackingData(){
			
			$("#modalDialog").modal("hide");

			if(Number($("#consigDtTblId").bootstrapTable('getSelections').length) > 0){
				var tblData = JSON.stringify($("#consigDtTblId").bootstrapTable('getSelections'));
				
				$.ajax({
					url : "/jewels/marketing/transactions/saleMt/packingPickup/transfer.html",
					type : "POST",
					data : {
						pMtId : mtid,
						data : tblData,
					},
					success : function(data, textStatus, jqXHR) {
						
						if(data == "1"){
							popPackingDtList(consigMtId);
							popSaleDt(false);
							displayInfoMsg(this,null,"Data transfer successfully");
							}else{
								displayMsg(event, this, data);
								}
						
						
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
			}else{
				displayMsg(this,null,"Record Not Selected");
			}
			
			
		}


		function transferConsigData(){

			$("#modalDialog").modal("hide");

			if(Number($("#consigDtTblId").bootstrapTable('getSelections').length) > 0){

				 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
				 
				var tblData = JSON.stringify($("#consigDtTblId").bootstrapTable('getSelections'));
				
				$.ajax({
					url : "/jewels/marketing/transactions/saleMt/consigPickup/transfer.html",
					type : "POST",
					data : {
						pMtId : mtid,
						data : tblData,
					},
					success : function(data, textStatus, jqXHR) {

						$.unblockUI();
						
						if(data == "1"){
							popConsigDtList(consigMtId);
							popSaleDt();
							displayInfoMsg(this,null,"Data transfer successfully");
							}else{
								displayMsg(event, this, data);
								}
						
						
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
			}else{
				displayMsg(this,null,"Record Not Selected");
			}

			}


		

	
	</script>		
	
	
	
	