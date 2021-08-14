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


  <div class="modal fade" id="myLocationWisePickupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Location Wise Pickup</h4>
			</div>

			<div class="modal-body">
			
						<div class=row">
						
							<div class="form-group">
									<label for="locationNm" class="col-sm-2 control-label">Location :</label>
									<div class="col-xs-3">
									
									<select id="locationnm" name="locationnm" class="form-control" onchange="javascript:popStockList(this.value)">
								<option value="">- Select Location -</option>
								
								
							</select>
									
								
									</div>
								</div>
						
					
						</div>
						
							<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
							
						<div class="row">
		
				<div id="stockDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Details</span>
				</div>
			
					<table id="stockDtTblId" data-toggle="table" data-click-to-select="true"
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
		
					<input type="button" value="Transfer" class="btn btn-primary" id="manualTransferBtnId"  onClick="javascript:manualTrfData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
	<script type="text/javascript">

	$(document)
	.ready(
			function(e) {

		

			});

		function popStockList(deptId){
				$('#stockDtDivId').css("display","block");

				stockList();
			}

		function stockList(){
			$("#stockDtTblId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/marketing/transactions/consigMt/stockList.html?locationId="+$('#locationnm').val(),
					});	

			}


		function manualTrfData(e){
			displayDlg(e,'javascript:manualTransferData();', 'Transfer','Do you want to transfer?', 'Continue');
			
		}


		function manualTransferData(){

			$("#modalDialog").modal("hide");

			if(Number($("#stockDtTblId").bootstrapTable('getSelections').length) > 0){
				var data = JSON.stringify($("#stockDtTblId").bootstrapTable('getSelections'));
				
				$.ajax({
					url : "/jewels/marketing/transactions/locationWiseStock/transfer.html",
					type : "POST",
					data : {
						pMtId : mtid,
						data : data,
						mode : mode,
					},
					success : function(data, textStatus, jqXHR) {
						
						if(data == "1"){
							stockList();
							if(mode == "Consig"){
								 popConsigDt();
								}else{
									popPackDt();
									}

							displayInfoMsg(this,null,"Data transfer successfully");
							}else{
								displayMsg(this,null,"Warning : Error Occoured Contact Support");
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
				