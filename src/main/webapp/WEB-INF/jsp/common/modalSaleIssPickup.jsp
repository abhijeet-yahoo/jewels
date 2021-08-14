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



    <div class="modal fade" id="mySaleIssModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Sale Issue List</h4>
			</div>

			<div class="modal-body">
			
			
		<div class="row">
				
					<table id="saleMtTblId" data-toggle="table" data-click-to-select="true"
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
		
				<div id="saleDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Sale Details</span>
				</div>
			
					<table id="saleDtTblId" data-toggle="table" 
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
		
					<input type="button" value="Transfer" class="btn btn-primary" id="transferBtnId"  onClick="javascript:trfSaleDtData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
			
			
	
	<script type="text/javascript">



	 $(function() {
		    $('#saleMtTblId').bootstrapTable();
		    $('#saleDtTblId').bootstrapTable();
		    
		  });


	 function popSalePickList(){

			$("#saleMtTblId").bootstrapTable('refresh',{ 
				url : "/jewels/marketing/transactions/saleMt/salePickup/listing.html?partyId="+$('#party\\.id').val(),
				});	
				
			}



	 var saleMtId = null;
		 $('#saleMtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					saleMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#saleDtDivId').css('display','block');

					popSaleDtList(saleMtId);
					
				});
			

		function popSaleDtList(saleMtId){
				$("#saleDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/saleMt/saleDtList/listing.html?mtId="+saleMtId,
					
				});	
				
			}



		function trfSaleDtData(e){
			displayDlg(e,'javascript:transferDtData();', 'Transfer','Do you want to transfer?', 'Continue');
			
		}


		function transferDtData(){
			
			$("#modalDialog").modal("hide");

			console.log('xxxx   '+Number($("#saleDtTblId").bootstrapTable('getSelections').length));

			if(Number($("#saleDtTblId").bootstrapTable('getSelections').length) > 0){
				var tblData = JSON.stringify($("#saleDtTblId").bootstrapTable('getSelections'));
				
				$.ajax({
					url : "/jewels/marketing/transactions/saleRetPickup/transfer.html",
					type : "POST",
					data : {
						pMtId : $('#id').val(),
						data : tblData,
					},
					success : function(data, textStatus, jqXHR) {
						
						if(data == "1"){
							popSaleDtList(saleMtId);
							popSaleRetDt(disableFlg);
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
			