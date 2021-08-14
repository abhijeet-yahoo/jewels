<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalJobMtlIssDt.jsp"%>  

<div class="panel-body" id="jobMtlIssDivId">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"  id="jobMtlIssBtnId"
							onclick="javascript:popJobMtlIssDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div class="table-responsive">
						<table id="jobMtlIssTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbar"
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
							$('#jobMtlIssDtId').on('click', function() {
				popJobMtlIssDt();
				
				});
		
				
			});
	
	function popJobMtlIssDt() {
		$("#jobMtlIssTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/jobMtlIssDt/listing.html?mtId="
									+ $('#jobIssMtId').val()+"&disableFlg="+disableFlg,
						});
	}
	
	function popJobMtlIssDtEntry(){
		
		$('#myJobMtlIssDtModal').modal('show');
	
		$('#vJobIssMtPkId').val($('#jobIssMtId').val());
	}
	

	function editJobMtlIssDt(mtId) {
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/jobMtlIssDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".jobMtlIssDtForm" ).validate();
							validator.resetForm();
							
							$('#myJobMtlIssDtModal').modal('show');

							$.each(data,function(k,v){
								$('#jobMtlIssDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
				
			
				
	
	
	}
	
	function deleteJobMtlIssDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteJobMtlIssDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteJobMtlIssDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/jobMtlIssDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
				
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					
					popJobMtlIssDt();
					
					}
				
			}
			
		})
		
	}

	

</script>