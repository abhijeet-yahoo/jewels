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



    <div class="modal fade" id="myPackingListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Packing List</h4>
			</div>

			<div class="modal-body">
			
			
		<div class="row">
				
					<table id="packingMtTblId" data-toggle="table" data-click-to-select="true"
						 data-side-pagination="server"	data-height="200">
						<thead>
							<tr>
								<th data-field="state" data-radio="true">Select</th>
								<th data-field="invNo" data-sortable="true">Invoice No</th>
								<th data-field="invDate" data-sortable="true">Invoice Date</th>
								<th data-field="party" data-sortable="true">party</th>
								
							</tr>
						</thead>
					</table>
				
			
		</div>
	
		<div class="row">
		
				<div id="packDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Packing Details</span>
				</div>
			
					<table id="packingListDtTblId" data-toggle="table" 
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



	 $(function() {
		    $('#packingMtTblId').bootstrapTable();
		    $('#packingListDtTblId').bootstrapTable();
		    
		  });


	 function popPackingPickList(){

			$("#packingMtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/packingPickup/listing.html?partyId="+$('#party\\.id').val(),
				});	
				
			}



	 var packMtId = null;
		 $('#packingMtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					packMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#packDtDivId').css('display','block');

					popPackingDtList(packMtId);
					
				});
			

		function popPackingDtList(packMtId){
				$("#packingListDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/packdtList/listing.html?mtId="+packMtId,
				});	
				
			}



		function trfData(e){
			displayDlg(e,'javascript:transferData();', 'Transfer','Do you want to transfer?', 'Continue');
			
		}


		function transferData(){
			
			$("#modalDialog").modal("hide");

			if(Number($("#packingListDtTblId").bootstrapTable('getSelections').length) > 0){
				var tblData = JSON.stringify($("#packingListDtTblId").bootstrapTable('getSelections'));
				
				$.ajax({
					url : "/jewels/marketing/transactions/packingPickup/transfer.html",
					type : "POST",
					data : {
						pMtId : mtid,
						data : tblData,
						packMtId : packMtId
					},
					success : function(data, textStatus, jqXHR) {
						
						if(data == "1"){
							popPackingDtList(packMtId);
							 popConsigDt(false);
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
			