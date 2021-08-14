<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalJobStnIssDt.jsp"%>  

<div class="panel-body" id="jobStnIssDivId">

 		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarStnIss">
						<a class="btn btn-info" style="font-size: 15px" type="button" id="jobStnIssBtnId"
							onclick="javascript:popJobStnIssDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					</div>
					<div class="table-responsive">
						<table id="jobStnIssTblId" data-toggle="table" data-height="400" data-search="true" data-toolbar="#toolbarStnIss"
								data-side-pagination="server" data-click-to-select="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								 data-pagination="true"	 >
							<thead>
								<tr class="btn-primary">
								<th data-field="action2" data-align="center">Delete</th>
								<th data-field="department" data-sortable="true">Location</th>
								<th data-field="stoneType" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="subShape" data-align="left">SubShape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="stoneChart" data-sortable="true">Size</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<th data-field="diffCarat" data-sortable="true">Diff Carat</th>
									<th data-field="balStone" data-sortable="true">BalStone</th>
									<th data-field="balCarat" data-sortable="true">BalCarat</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="packetNo" data-sortable="true">Packet No</th>
									<th data-field="remark" data-sortable="true">Remark</th>
									<!-- <th data-field="action1" data-align="center">Edit</th> -->
									
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
							$('#jobStnIssDtId').on('click', function() {
				popJobStnIssDt();
				
				});
		
				
			});
	
	function popJobStnIssDt() {
		$("#jobStnIssTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/jobStnIssDt/listing.html?mtId="+ $('#jobIssMtId').val()+"&disableFlg="+disableFlg,
						});
	}
	
	function popJobStnIssDtEntry(){
		
		$('#myJobStnIssDtModal').modal('show');
		$('#vJobStnIssMtId').val($('#jobIssMtId').val());
	}
	

	function editJobStnIssDt(mtId) {
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/jobStnIssDt/edit/"+ mtId + ".html",
						type : 'GET',
						dataType:"JSON",
						success : function(data) {
						
							var validator = $( ".jobStnIssDtForm" ).validate();
							validator.resetForm();
							
							$('#myJobStnIssDtModal').modal('show');

							$.each(data,function(k,v){
								
								$('#jobStnIssDtModalDivId  #'+k).val(v);
								
								if(k === 'quality'){
									$('#shape\\.id').trigger('onchange');
									setTimeout(function() {

										$('#quality\\.id').val(v);
									}, 500);

								}
								
								if(k === 'size'){
									
									$('#shape\\.id').trigger('onchange');
									setTimeout(function() {

										$('#size').val(v);
									}, 500);

								}
								
							
								
							});
						}
					});	
	
	}
	
	function deleteJobStnIssDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteJobStnIssDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteJobStnIssDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/jobStnIssDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
				
								
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
					
				}else{
					popJobStnIssDt();
					}
			}
			
		})
		
	}
	

</script>