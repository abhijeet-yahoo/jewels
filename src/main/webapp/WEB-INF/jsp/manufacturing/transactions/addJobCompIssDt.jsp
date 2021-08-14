<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalJobCompIssDt.jsp"%>

<div class="panel-body" id="jobCompIssDivId">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarCompIss">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="jobCompIssBtnId"
							onclick="javascript:popJobCompIssDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div class="table-responsive">
						<table id="jobCompIssTblId" data-toggle="table" data-height="400" data-search="true" 
						data-toolbar="#toolbarCompIss" data-side-pagination="server" data-click-to-select="true"
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
							$('#jobCompIssDtId').on('click', function() {
						
							popJobCompIssDt();
						
				});
		
				
			});
	
	function popJobCompIssDt() {
		
		$("#jobCompIssTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/jobCompIssDt/listing.html?mtId="+ $('#jobIssMtId').val()+"&disableFlg="+disableFlg,
						});
	}
	
	function popJobCompIssDtEntry(){
		
		$('#myJobCompIssDtModal').modal('show');
		$('#vJobCompIssMtId').val($('#jobIssMtId').val());
	}
	

	function editJobCompIssDt(mtId) {
		
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/jobCompIssDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".jobCompIssDtForm" ).validate();
							validator.resetForm();
							
							$('#myJobCompIssDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#jobCompIssDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
	
	}
	
	function deleteJobCompIssDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteJobCompIssDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteJobCompIssDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/jobCompIssDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
				
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					popJobCompIssDt();
					}
			}
			
		})
		
	}
	

</script>