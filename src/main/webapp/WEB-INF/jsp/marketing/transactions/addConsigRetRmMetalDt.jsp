<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

 <%@ include file="/WEB-INF/jsp/common/modalConsigRetRmMetalDt.jsp"%> 
 

  

<div class="panel-body">

 	
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar1">
						<!-- <a class="btn btn-info" style="font-size: 15px" type="button" id="consigRetAddRowMtlBtnId"
							onclick="javascript:popConsigRetRmMetalDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a> -->
						
						<a class="btn btn-primary" style="font-size: 15px" type="button" id="consigRetPickupRowMtlBtnId"
							onclick="javascript:popPickupConsigIssMetal();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Pickup
						</a>
						
					</div>
					<div class="table-responsive">
						<table id="consigRetRmMetalTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbar1"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
									<th data-field="department" data-sortable="true">Location</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="remark" data-sortable="true">Remark</th>
									<!-- <th data-field="action1" data-align="center">Edit</th> -->
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

		
		</div>
		
	</div>

	
	<script type="text/javascript">
	$(document).ready(
			function(e) {
							$('#consigRetRmMetalDtId').on('click', function() {
				popConsigRetRmMetalDt();
				
				});
		
				
			});
	
	function popConsigRetRmMetalDt() {
		$("#consigRetRmMetalTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRetRmMetalDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg, 															/* $('#consigRetMtId').val(), */
						});
	}
	
	function popConsigRetRmMetalDtEntry(){
		
		$('#myConsigRetRmMetalDtModal').modal('show');
		$('#vConsigRetMtId').val(mtid);		
													/* val($('#consigRetMtId').val()); */
	}



	

	function editConsigRetRmMetalDt(mtId,refTranId) {

		alert('refTranId   '+refTranId);
				
					$.ajax({
						url : "/jewels/marketing/transactions/consigRetRmMetalDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".consigRetRmMetalDtForm" ).validate();
							validator.resetForm();
							
							$('#myConsigRetRmMetalDtModal').modal('show');

							$.each(data,function(k,v){	
								$('#consigRetRmMetalDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
				
			
				
	
	
	}
	
	function deleteConsigRetRmMetalDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteConsigRetRmMetalDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteConsigRetRmMetalDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/consigRetRmMetalDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){

				if(data == "1"){
					popConsigRetRmMetalDt();
					
				}else{
					
					displayMsg(event, this, data);
					
					}
				
			}
			
		})
		
	}


	function popPickupConsigIssMetal(){
		$('#myRowMetalPackingListModal').modal('show');

		popConsigMtRowPickList();
		}
	

</script>