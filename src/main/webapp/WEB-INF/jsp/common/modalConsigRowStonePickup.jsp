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



    <div class="modal fade" id="myRowStonePackingListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-xl" style="width: 1300px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Stone Pickup</h4>
			</div>

			<div class="modal-body">
			
			
		<div class="row">
				
					<table id="consigRowStoneDtTblId" data-toggle="table"  data-unique-id="id" 
					data-maintain-selected="true" data-search="true"
						 	data-height="400">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="invNo" data-sortable="true">Invoice No</th>
								<th data-field="invDate" data-sortable="true">Invoice Date</th>
								<th data-field="party" data-sortable="true">party</th>
								
								<th data-field="location" data-sortable="true">Location</th>
								<th data-field="stoneType" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
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
	
	<!-- 	<div class="row">
		
				<div id="consigRowStoneDtDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Stone Details</span>
				</div>
			
					<table id="consigRowStoneDtTblId" data-toggle="table" 
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
		</div> -->
		

			
		<div class="modal-footer">
		
					<input type="button" value="Transfer" class="btn btn-primary" id="transferStoneBtnId"  onClick="javascript:trfConsigStoneRowData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
		<script type="text/javascript">

		 $(function() {
			    $('#consigRowStoneDtTblId').bootstrapTable();
			  //  $('#consigRowStoneDtTblId').bootstrapTable();
			    
			  });



		 function popConsigMtStoneRowPickList(){

				$("#consigRowStoneDtTblId").bootstrapTable('refresh',{ 
						url : "/jewels/marketing/transactions/consigMt/rowStonePickup/listing.html?partyId="+$('#party\\.id').val(),
					});	
					
				}


	/* 	 var cMtId = null;
		 $('#consigStoneMtRowTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					cMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#consigRowStoneDtDivId').css('display','block');

					popConsigDtRowStonePickList(cMtId);
					
				});


		 function popConsigDtRowStonePickList(cMtId){
				$("#consigRowStoneDtTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/rowStoneDtDetails/listing.html?mtId="+cMtId,
				});	
				
			} */


		 function issueStoneFormatter(value,row,index){
			 var tempId = 'issueStoneVal' + Number(index);
			 var balstone = row.balStone;
			 var vId = row.id;
				return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateIssueStone(this,'+vId+','+balstone+')"/>';
		 		
			}

		 function updateIssueStone(param,vId,balstone){
				if(param.value > balstone){
					displayMsg(this,null,"IssueStone greater than balanceStone");
					 $('#consigRowStoneDtTblId').bootstrapTable('updateByUniqueId', {
							id : vId,
							row : {
								issueStone : 0.0,
							}
						});
					}else{
			
					 $('#consigRowStoneDtTblId').bootstrapTable('updateByUniqueId', {
							id : vId,
							row : {
								issueStone : param.value,
							}
						});
				
					}
		 	}


		 function issueCaratFormatter(value,row,index){
			 var tempId = 'issueCaratVal' + Number(index);
			 var balcarat = row.balCarat;
			 var vId = row.id;
				return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateIssueCarat(this,'+vId+','+balcarat+')"/>';
		 		
			}

		 function updateIssueCarat(param,vId,balcarat){

			 if(param.value > balcarat){
					displayMsg(this,null,"IssueCarat greater than balanceCarat");

					 $('#consigRowStoneDtTblId').bootstrapTable('updateByUniqueId', {
							id : vId,
							row : {
								issueCarat : 0.0,
							}
						});
					
					
					}else{	
						 $('#consigRowStoneDtTblId').bootstrapTable('updateByUniqueId', {
								id : vId,
								row : {
									issueCarat : param.value,
								}
							});
							
			
					}
		 	}


		 function trfConsigStoneRowData(e){
				displayDlg(e,'javascript:transferStoneRowData();', 'Transfer','Do you want to transfer?', 'Continue');
				
			}


			function transferStoneRowData(){
				
				$("#modalDialog").modal("hide");
			
				if(Number($("#consigRowStoneDtTblId").bootstrapTable('getSelections').length) > 0){
					var tblData = JSON.stringify($("#consigRowStoneDtTblId").bootstrapTable('getSelections'));
					
					$.ajax({
						url : "/jewels/marketing/transactions/consigStoneRowDataPickup/transfer.html",
						type : "POST",
						data : {
							pMtId : mtid,
							data : tblData,
						},
						success : function(data, textStatus, jqXHR) {
							
							if(data == "1"){
								popConsigMtStoneRowPickList();
								popConsigRetRmStnDt();
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
				




			
			$('#consigRowStoneDtTblId').on('check.bs.table check-all.bs.table', function () {
				
				 var data =JSON.stringify($('#consigRowStoneDtTblId').bootstrapTable('getData'));
				 $.each(JSON.parse(data),function(idx,obj){
					 if(obj.state==true){
						 			
							if(obj.issueStone>0 && obj.issueCarat>0 ){
								
								
							}else{
								 $("#consigRowStoneDtTblId").bootstrapTable('updateRow', {
										index : idx,
										row : {
											issueStone : obj.balStone,
											issueCarat : obj.balCarat,
											
										}
									}); 
							}
							
						 
					 }
					 
			
					 
				 });
				
			});


		</script>	
			
			
	