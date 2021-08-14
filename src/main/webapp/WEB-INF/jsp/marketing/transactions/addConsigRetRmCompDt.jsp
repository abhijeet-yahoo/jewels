<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalConsigRetRmCompDt.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalConsigRowCompDtPickup.jsp"%>




<div class="panel-body">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarCompRec">
						<!-- <a class="btn btn-info" style="font-size: 15px" type="button" id="consigRetAddRowCompBtnId"
							onclick="javascript:popConsigRetRmCompDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a> -->
						
						<a class="btn btn-primary" style="font-size: 15px" type="button"  id="consigRetPickupRowCompBtnId"
							onclick="javascript:popPickupConsigIssComponent();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Pickup
						</a>
						
					</div>
					<div class="table-responsive">
						<table id="consigRetRmCompTblId" data-toggle="table" data-height="400" data-search="true" 
						data-toolbar="#toolbarCompRec" data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
									<th data-field="department" data-sortable="true">Department</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="component" data-sortable="true">Component</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="qty" data-sortable="true">Qty</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<!-- <th data-field="action1" data-align="center">Edit</th> -->
									<th data-field="action2" data-align="center">Delete</th></tr>
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
							$('#consigRetRmCompDtId').on('click', function() {
						
							popConsigRetRmCompDt();
						
				});
		
				
			});
	
	function popConsigRetRmCompDt() {
		
		$("#consigRetRmCompTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigRetRmCompDt/listing.html?mtId="+ mtid+"&disableFlg="+disableFlg,			/*  $('#consigRetMtId').val(), */
						});
	}
	
	function popConsigRetRmCompDtEntry(){
		
		$('#myConsigRetRmCompDtModal').modal('show');
		$('#vConsigRetRmCompMtId').val(mtid);											/* val($('#consigRetMtId').val()); */
	}
	

	function editConsigRetRmCompDt(mtId) {
		
				
					$.ajax({
						url : "/jewels/marketing/transactions/consigRetRmCompDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".consigRetRmCompDtForm" ).validate();
							validator.resetForm();
							
							$('#myConsigRetRmCompDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#consigRetRmCompDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
	
	}
	
	function deleteConsigRetRmCompDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteConsigRetRmCompDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteConsigRetRmCompDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/marketing/transactions/consigRetRmCompDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){

				if(data == "1"){
					popConsigRetRmCompDt();
					
				}else{
					
					displayMsg(event, this, data);
					
					}
				
			}
			
		})
		
	}


	function popPickupConsigIssComponent(){
		$('#myRowCompDtPackingListModal').modal('show');

		popConsigMtRowCompPickList();
		}
	

</script>