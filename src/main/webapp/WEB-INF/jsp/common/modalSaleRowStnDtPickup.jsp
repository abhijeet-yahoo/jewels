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



    <div class="modal fade" id="myRowStoneSaleMtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Sale Stone Pickup</h4>
			</div>

			<div class="modal-body">
			
			
		<div class="row">
				
					<table id="saleStoneMtRowTblId" data-toggle="table" data-click-to-select="true"
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
		
				<div id="saleRowStoneDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Stone Details</span>
				</div>
			
					<table id="saleRowStoneDtTblId" data-toggle="table" 
						 data-side-pagination="server"	data-height="350">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="department" data-sortable="true">Location</th>
								<th data-field="stoneType" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="subShape" data-align="left">SubShape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="balStone" data-sortable="true">BalStone</th>
								    <th data-field="balCarat" data-sortable="true">BalCarat</th>
								    <th data-field="issueStone"  data-formatter="issueStoneFormatter" data-sortable="true">Issue Stone</th>
								    <th data-field="issueCarat" data-formatter="issueCaratFormatter" data-sortable="true">Issue Carat</th>
								    <th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
								
							</tr>
						</thead>
					</table>
			</div>
		</div>
		

			
		<div class="modal-footer">
		
					<input type="button" value="Transfer" class="btn btn-primary" id="saleTransferStoneBtnId"  onClick="javascript:trfSaleStoneRowData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
		<script type="text/javascript">

		 $(function() {
			    $('#saleStoneMtRowTblId').bootstrapTable();
			    $('#saleRowStoneDtTblId').bootstrapTable();
			    
			  });



		 function popSaleMtStoneRowPickList(){

				$("#saleStoneMtRowTblId").bootstrapTable('refresh',{ 
						url : "/jewels/marketing/transactions/saleMt/rowStonePickup/listing.html?partyId="+$('#party\\.id').val(),
					});	
					
				}


		 var cMtId = null;
		 $('#saleStoneMtRowTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					cMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#saleRowStoneDtDivId').css('display','block');

					popSaleDtRowStonePickList(cMtId);
					
				});


		 function popSaleDtRowStonePickList(cMtId){
				$("#saleRowStoneDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/saleMt/rowStoneDtDetails/listing.html?mtId="+cMtId,
				});	
				
			}


		 function issueStoneFormatter(value,row,index){
			 var tempId = 'issueStoneVal' + Number(index);
			 var balstone = row.balStone;
				return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateIssueStone(this,'+index+','+balstone+')"/>';
		 		
			}

		 function updateIssueStone(param,vidx,balstone){
				if(param.value > balstone){
					displayMsg(this,null,"IssueStone greater than balanceStone");
					$('#issueStoneVal'+vidx).val(0);
					}else{
				
			 $('#saleRowStoneDtTblId').bootstrapTable('updateRow', {
					index : Number(vidx),
					row : {
						issueStone : param.value,
					}
				});
					}
		 	}


		 function issueCaratFormatter(value,row,index){
			 var tempId = 'issueCaratVal' + Number(index);
			 var balcarat = row.balCarat;
				return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateIssueCarat(this,'+index+','+balcarat+')"/>';
		 		
			}

		 function updateIssueCarat(param,vidx,balcarat){

			 if(param.value > balcarat){
					displayMsg(this,null,"IssueCarat greater than balanceCarat");

					$('#issueCaratVal'+vidx).val(0.0);
					
					}else{	
			 $('#saleRowStoneDtTblId').bootstrapTable('updateRow', {
					index : Number(vidx),
					row : {
						issueCarat : param.value,
					}
				});
					}
		 	}


		 function trfSaleStoneRowData(e){
				displayDlg(e,'javascript:transferStoneRowData();', 'Transfer','Do you want to transfer?', 'Continue');
				
			}


			function transferStoneRowData(){
				
				$("#modalDialog").modal("hide");
			
				if(Number($("#saleRowStoneDtTblId").bootstrapTable('getSelections').length) > 0){
					var tblData = JSON.stringify($("#saleRowStoneDtTblId").bootstrapTable('getSelections'));
					
					$.ajax({
						url : "/jewels/marketing/transactions/saleStoneRowDataPickup/transfer.html",
						type : "POST",
						data : {
							pMtId : mtid,
							data : tblData,
						},
						success : function(data, textStatus, jqXHR) {
							
							if(data == "1"){
								popSaleDtRowStonePickList(cMtId);
								popSaleRetRmStnDt();
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
			
			
	