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



    <div class="modal fade" id="myRowMetalPackingListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Metal Pickup</h4>
			</div>

			<div class="modal-body">
			
			
		<div class="row">
				
					<table id="consigRowMetalTblId" data-toggle="table" data-unique-id="id" 
					data-maintain-selected="true" data-search="true"	data-height="400">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="invNo" data-sortable="true">Invoice No</th>
								<th data-field="invDate" data-sortable="true">Invoice Date</th>
								<th data-field="party" data-sortable="true">party</th>
								<th data-field="invLocation" data-sortable="true">Inv Location</th>
								<th data-field="location" data-sortable="true">Location</th>
								<th data-field="metal" data-sortable="true">Metal</th>
								<th data-field="ktColor" data-sortable="true">Kt-Color</th>
								<th data-field="metalWt" data-sortable="true">Metal Wt</th>
								<th data-field="balanceWt" data-sortable="true">Balance Wt</th>
								<th data-field="issueWt" data-formatter="issueWtRetFormatterMetal" data-sortable="true">Issue Wt</th>
							</tr>
						</thead>
					</table>
				
			
		</div>
	
	<!-- 	<div class="row">
		
				<div id="consigRowMetalDivId" style="display: none">
				
					 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Metal Details</span>
				</div>
			
					<table id="consigRowMetalTblId" data-toggle="table" 
						 data-side-pagination="server"	data-height="350">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="department" data-sortable="true">Location</th>
								<th data-field="metal" data-sortable="true">Metal</th>
								<th data-field="purity" data-sortable="true">Kt</th>
								<th data-field="color" data-sortable="true">Color</th>
								<th data-field="metalWt" data-sortable="true">Metal Wt</th>
								<th data-field="balanceWt" data-sortable="true">Balance Wt</th>
								<th data-field="issueWt" data-formatter="issueWtFormatterMetal" data-sortable="true">Issue Wt</th>
								
							</tr>
						</thead>
					</table>
			</div>
		</div> -->
		

			
		<div class="modal-footer">
		
					<input type="button" value="Transfer" class="btn btn-primary" id="transferBtnId"  onClick="javascript:trfConsigMetalRowData(event);" />
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
		<script type="text/javascript">

		 /* $(function() {
			    $('#consigMtRowTblId').bootstrapTable();
			    $('#consigRowMetalTblId').bootstrapTable();
			    
			  }); */



		 function popConsigMtRowPickList(){

				$("#consigRowMetalTblId").bootstrapTable('refresh',{ 
						url : "/jewels/marketing/transactions/consigMt/rowMetalPickup/listing.html?partyId="+$('#party\\.id').val(),
					});	
					
				}

/* 
		 var consigIssMtId = null;
		 $('#consigMtRowTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					consigIssMtId = jQuery.parseJSON(JSON.stringify(row)).id;
					
					$('#consigRowMetalDivId').css('display','block');

					popConsigDtRowPickList(consigIssMtId);
					
				}); */

		 $('#myConsigRetRmMetalDtModal').on('hidden.bs.modal', function () {
				  // do something…
				$('#consigRowMetalDivId').css('display','none');
				  
				})


		/*  function popConsigDtRowPickList(consigIssMtId){
				$("#consigRowMetalTblId").bootstrapTable('refresh',{ 
					url : "/jewels/marketing/transactions/consigMt/rowMetalDetails/listing.html?mtId="+consigIssMtId,
				});	
				
			} */


		 function issueWtRetFormatterMetal(value,row,index){
			 
			 var tempId = 'issueWtMetal' + Number(index);
			 var balwt = row.balanceWt;
			 var vId = row.id;

				return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateMetalReturn(this,'+vId+','+balwt+')"/>';
		 		
			}

		 function updateMetalReturn(param,vId,balwt){

			 if(param.value > balwt){
				 
					displayMsg(this,null,"IssueWt greater than balanceWt");

					 $('#consigRowMetalTblId').bootstrapTable('updateByUniqueId', {
							id : vId,
							row : {
								issueWt : 0.0,
							}
						});
					
					}else{	
			 

						 $('#consigRowMetalTblId').bootstrapTable('updateByUniqueId', {
								id : vId,
								row : {
									issueWt : param.value,
								}
							});
		 		
				}
			}


		 function trfConsigMetalRowData(e){
				displayDlg(e,'javascript:transferMetalRowData();', 'Transfer','Do you want to transfer?', 'Continue');
				
			}


			function transferMetalRowData(){
				
				$("#modalDialog").modal("hide");
				
				if(Number($("#consigRowMetalTblId").bootstrapTable('getSelections').length) > 0){
					var tblData = JSON.stringify($("#consigRowMetalTblId").bootstrapTable('getSelections'));
					
					$.ajax({
						url : "/jewels/marketing/transactions/consigMetalRowDataPickup/transfer.html",
						type : "POST",
						data : {
							pMtId : mtid,
							data : tblData,
						},
						success : function(data, textStatus, jqXHR) {
							
							if(data == "1"){
								popConsigMtRowPickList();
								popConsigRetRmMetalDt();
								displayInfoMsg(this,null,"Data transfer successfully");
								}else{
									displayMsg(this,null,data);
									}
							
							
						},
						error : function(jqXHR, textStatus, errorThrown) {
						}
					});
				}else{
					displayMsg(this,null,"Record Not Selected");
				}
				
				
			}





			$('#consigRowMetalTblId').on('check.bs.table check-all.bs.table', function () {
				
				 var data =JSON.stringify($('#consigRowMetalTblId').bootstrapTable('getData'));
				 $.each(JSON.parse(data),function(idx,obj){
					 if(obj.state==true){
						 			
							if(obj.issueWt > 0 ){
								
								
							}else{
								 $("#consigRowMetalTblId").bootstrapTable('updateRow', {
										index : idx,
										row : {
											issueWt : obj.balanceWt,
											
											
										}
									}); 
							}
							
						 
					 }
					 
			
					 
				 });
				
			});
				
	


		</script>	
			
			
			<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>			
			
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />
			
			
	