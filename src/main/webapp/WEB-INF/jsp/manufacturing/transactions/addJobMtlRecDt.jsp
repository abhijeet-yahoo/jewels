<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalJobMtlRecDt.jsp"%>
  

<div class="panel-body" id="jobMtlRecDivId">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"  id="jobMtlRecBtnId"
							onclick="javascript:popJobMtlRecDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div class="table-responsive">
						<table id="jobMtlRecTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbar"
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
							$('#jobMtlRecDtId').on('click', function() {
				popJobMtlRecDt();
				
				});
		
				
			});
	
	function popJobMtlRecDt() {
		$("#jobMtlRecTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/jobMtlRecDt/listing.html?mtId="
									+ $('#jobRecMtId').val()+"&disableFlg="+disableFlg,
						});
	}
	
	function popJobMtlRecDtEntry(){
		$('#myJobMtlRecDtModal').modal('show');
		$('#jobMtlRecDtFormId #vJobRecMtId').val($('#jobRecMtId').val());
	}
	

	function editJobMtlRecDt(mtId) {
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/jobMtlRecDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".jobMtlRecDtForm" ).validate();
							validator.resetForm();
							
							$('#myJobMtlRecDtModal').modal('show');

							$.each(data,function(k,v){	
								$('#jobMtlRecDtModalDivId  #'+k).val(v);
								
							});
						}
					});	
				
			
				
	
	
	}
	
	function deleteJobMtlRecDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteJobMtlRecDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteJobMtlRecDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/jobMtlRecDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					
					popJobMtlRecDt();
					
					}
				
			}
			
		})
		
	}
	

</script>