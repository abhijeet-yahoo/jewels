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



    <div class="modal fade" id="myRowCompDtSaleIssModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Sale Component Pickup</h4>
			</div>

			<div class="modal-body">
			
			
		<div class="row">
				
					<table id="saleMtRowCompTblId" data-toggle="table" data-click-to-select="true"
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
		
				<div id="saleRowCompDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Component Details</span>
				</div>
			
					<table id="saleRowCompDtTblId" data-toggle="table" 
						 data-side-pagination="server"	data-height="350">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="department" data-sortable="true">Location</th>
								<th data-field="metal" data-sortable="true">Metal</th>
								<th data-field="component" data-sortable="true">Component</th>
								<th data-field="purity" data-sortable="true">Kt</th>
								<th data-field="color" data-sortable="true">Color</th>
								<th data-field="qty" data-sortable="true">Qty</th>
								<th data-field="metalWt" data-sortable="true">Metal Wt</th>
								<th data-field="balanceWt" data-sortable="true">Balance Wt</th>
								<th data-field="issueWt" data-formatter="issueWtFormatter" data-sortable="true">Issue Wt</th>
								
							</tr>
						</thead>
					</table>
			</div>
		</div>
		

			
		<div class="modal-footer">
		
					<input type="button" value="Transfer" class="btn btn-primary" id="saleRetTransferCompDtBtnId"  onClick="javascript:trfConsigCompDtRowData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
		<script type="text/javascript">

		 $(function() {
			    $('#saleMtRowCompTblId').bootstrapTable();
			    $('#saleRowCompDtTblId').bootstrapTable();
			    
			  });



		 function popSaleMtRowCompPickList(){

				$("#saleMtRowCompTblId").bootstrapTable('refresh',{ 
						url : "/jewels/marketing/transactions/saleMt/rowCompPickup/listing.html?partyId="+$('#party\\.id').val(),
					});	
					
				}


		 var compMtId = null;
		 $('#saleMtRowCompTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					compMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#saleRowCompDtDivId').css('display','block');

					popSaleDtRowCompDtPickList(compMtId);
					
				});


		 function popSaleDtRowCompDtPickList(compMtId){
				$("#saleRowCompDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/saleMt/rowCompDtDetails/listing.html?mtId="+compMtId,
				});	
				
			}


		 function issueWtFormatter(value,row,index){
			 var tempId = 'issueWtVal' + Number(index);
			 var balwt = row.balanceWt;
			
				return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateIssue(this,'+index+','+balwt+')"/>';
		 		
			}

		 function updateIssue(param,vidx,balwt){
			
			if(param.value > balwt){
				displayMsg(this,null,"IssueWt greater than balanceWt");

				$('#issueWtVal'+vidx).val(0.0);
				
				}else{	
			
			 $('#saleRowCompDtTblId').bootstrapTable('updateRow', {
					index : Number(vidx),
					row : {
						issueWt : param.value,
					}
				});
		 		
				}
			}


		 function trfConsigCompDtRowData(e){
				displayDlg(e,'javascript:saleTransferCompRowData();', 'Transfer','Do you want to transfer?', 'Continue');
				
			}


			function saleTransferCompRowData(){
				
				$("#modalDialog").modal("hide");
			
				if(Number($("#saleRowCompDtTblId").bootstrapTable('getSelections').length) > 0){
					var tblData = JSON.stringify($("#saleRowCompDtTblId").bootstrapTable('getSelections'));
					
					$.ajax({
						url : "/jewels/marketing/transactions/saleCompRowDataPickup/transfer.html",
						type : "POST",
						data : {
							pMtId : mtid,
							data : tblData,
						},
						success : function(data, textStatus, jqXHR) {
							
							if(data == "1"){
								popSaleDtRowCompDtPickList(compMtId);
								popSaleRetRmCompDt();
								displayInfoMsg(this,null,"Data transfer successfully");
								}else{
									displayMsg(this,null,"IssueWt greater than balanceWt");
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
			
			
	