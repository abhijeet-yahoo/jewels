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



    <div class="modal fade" id="myRowCompDtPackingListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Component Pickup</h4>
			</div>

			<div class="modal-body">
			
			
		<div class="row">
				
					<table id="consigRowCompDtTblId" data-toggle="table" data-click-to-select="true"
						data-unique-id="id"  data-maintain-selected="true" data-search="true" data-height="200">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
							
								<th data-field="invNo" data-sortable="true">Invoice No</th>
								<th data-field="invDate" data-sortable="true">Invoice Date</th>
								<th data-field="party" data-sortable="true">party</th>
								<th data-field="invLocation" data-sortable="true">Inv Location</th>
								
								<th data-field="location" data-sortable="true">Location</th>
							<!-- 	<th data-field="metal" data-sortable="true">Metal</th> -->
								<th data-field="component" data-sortable="true">Component</th>
								<th data-field="ktColor" data-sortable="true">Kt-Color</th>
								<!-- <th data-field="qty" data-sortable="true">Qty</th> -->
								<!-- <th data-field="metalWt" data-sortable="true">Metal Wt</th> -->
								<th data-field="balanceQty" data-sortable="true">Bal Qty</th>
								<th data-field="balanceWt" data-sortable="true">Bal Wt</th>
								<th data-field="issueQty" data-formatter="issueQtyFormatter" data-sortable="true">Iss Qty</th>
								<th data-field="issueWt" data-formatter="issueWtFormatterMetal" data-sortable="true">Iss Wt</th>
								
							</tr>
						</thead>
					</table>
				
			
		</div>
	
		<!-- <div class="row">
		
				<div id="consigRowCompDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Component Details</span>
				</div>
			
					<table id="consigRowCompDtTblId" data-toggle="table" 
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
		</div> -->
		

			
		<div class="modal-footer">
		
					<input type="button" value="Transfer" class="btn btn-primary" id="transferCompDtBtnId"  onClick="javascript:trfConsigCompDtRowData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
		<script type="text/javascript">

		 $(function() {
			//    $('#consigRowCompDtTblId').bootstrapTable();
			    $('#consigRowCompDtTblId').bootstrapTable();
			    
			  });



		 function popConsigMtRowCompPickList(){

				$("#consigRowCompDtTblId").bootstrapTable('refresh',{ 
						url : "/jewels/marketing/transactions/consigMt/rowCompPickup/listing.html?partyId="+$('#party\\.id').val(),
					});	
					
				}


		/*  var compMtId = null;
		 $('#consigMtRowCompTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					compMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#consigRowCompDtDivId').css('display','block');

					popConsigDtRowCompDtPickList(compMtId);
					
				});


		 function popConsigDtRowCompDtPickList(compMtId){
				$("#consigRowCompDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/rowCompDtDetails/listing.html?mtId="+compMtId,
				});	
				
			}
 */

		 function issueWtFormatterMetal(value,row,index){
			 var tempId = 'issueWtVal' + Number(index);
			 var balwt = row.balanceWt;
			 var vId = row.id;
				return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateIssue(this,'+vId+','+balwt+')"/>';
		 		
			}

		 function updateIssue(param,vId,balwt){
			
			if(param.value > balwt){
				displayMsg(this,null,"IssueWt greater than balanceWt");

				 $('#consigRowCompDtTblId').bootstrapTable('updateByUniqueId', {
						id : vId,
						row : {
							issueWt : 0.0,
						}
					});
				
				}else{	

					 $('#consigRowCompDtTblId').bootstrapTable('updateByUniqueId', {
							id : vId,
							row : {
								issueWt : param.value,
							}
						});
		 		
				}
			}



			function issueQtyFormatter(value,row,index){
				 var tempId = 'issueQtyVal' + Number(index);
				 var balqty = row.balanceQty;
				 var vId = row.id;

		
				 
					return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateIssueQty(this,'+vId+','+balqty+')"/>';
			 		
				}


			 function updateIssueQty(param,vId,balqty){
	
					if(param.value > balqty){
						displayMsg(this,null,"IssueQty greater than balanceQty");

						 $('#consigRowCompDtTblId').bootstrapTable('updateByUniqueId', {
								id : vId,
								row : {
									issueQty : 0.0,
								}
							});
						
						}else{	

							 $('#consigRowCompDtTblId').bootstrapTable('updateByUniqueId', {
									id : vId,
									row : {
										issueQty : param.value,
									}
								});
				 		
						}
					}


		 function trfConsigCompDtRowData(e){
				displayDlg(e,'javascript:transferCompRowData();', 'Transfer','Do you want to transfer?', 'Continue');
				
			}


			function transferCompRowData(){
				
				$("#modalDialog").modal("hide");
			
				if(Number($("#consigRowCompDtTblId").bootstrapTable('getSelections').length) > 0){
					var tblData = JSON.stringify($("#consigRowCompDtTblId").bootstrapTable('getSelections'));
					
					$.ajax({
						url : "/jewels/marketing/transactions/consigCompRowDataPickup/transfer.html",
						type : "POST",
						data : {
							pMtId : mtid,
							data : tblData,
						},
						success : function(data, textStatus, jqXHR) {
							
							if(data == "1"){
								popConsigMtRowCompPickList();
								popConsigRetRmCompDt();
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
			
			
	