<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">

	<div class="panel-body">
	
		
		
				<div class="container-fluid">
					<div class="row">

							<div>
								<table id="costCompFindingId" data-toggle="table" data-toolbar="#toolbar"
									data-pagination="false" data-side-pagination="server"
									data-click-to-select="false" data-select-item-name="checkbox"
									data-page-list="[5, 10, 20, 50, 100, 200]" data-height="400">
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="invNo" data-sortable="false">Inv No</th>
											<th data-field="invDate" data-sortable="false">Inv Date</th>
											<th data-field="compName" data-sortable="false">Comp Name</th>
											<th data-field="purity" data-sortable="false">Purity</th>
											<th data-field="color" data-sortable="false">Color</th>
											<th data-field="balance" data-sortable="false">Balance</th>
											<th data-field="rate" data-sortable="false">Rate</th>
										</tr>
									</thead>
								</table>
							</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				
				<div id="rateUpdate" style="display: none">
					<div class="row">
						<div class="form-group">
							<div class="col-xs-12">
							<form:form commandName="costCompDt" >
								<table class="table table-bordered">
									<tbody>
										<tr>
											<td class="col-xs-10"></td>
											<th class="col-xs-1">Rate :</th>
											<td class="col-xs-1"><input type="text" id="fldRate" name="fldRate" maxlength="7" size="7" /></td>
										</tr>
									</tbody>
								</table>
							</form:form>
							</div>
						</div>
					</div>
				</div>
	
	
	
	
			<div class="row" >
				<div class="form-group">
					<div class="col-xs-1" >
						<form:form commandName="costCompDt" id="setFinding"
							action="/jewels/manufacturing/transactions/finding/setRate.html"
							cssClass="form-horizontal setFindings">
		
							<table class="table">
								<tbody>
									<tr>
										<td class="col-xs-1" >
											<input type="submit" value="Save" class="btn btn-primary" style="width: 2.5cm"/> 
											<form:input type="hidden" path="id" />
											<input type="hidden" id="compInwDtPk" name="compInwDtPk" />
											<input type="hidden" id="vRate" name="vRate" />
											<input type="hidden" id="costingMtId" name="costingMtId" />
										</td>
										<td class="col-xs-1">
											<a class="btn btn-info"
												style="font-size: 15px;width: 2cm" type="button" onClick="goToMainPage()">Back
											</a> 
											<input type="hidden" id="costingMtId" name="costingMtId" />
										</td>
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</div>
		   </div>
				
				
				
				
				
				
				
				

	</div> <!-- ending the panel body -->

</div> <!-- ending the main panel -->



<script type="text/javascript">


$(document).ready(function(){
	
	popTable();
	
	var abcx = window.location.href;
	var tempCostDtId = abcx.substring(window.location.href.indexOf('dtId') + 5);
	var costDtId = tempCostDtId.substring(0,tempCostDtId.indexOf('&'));
	$('#costingDtId').val(costDtId);
	
	var costMtId = abcx.substring(window.location.href.indexOf('mtId') + 5);
	$('#costingMtId').val(costMtId);
	
})




  function popTable(){
	
	$('#costCompFindingId').bootstrapTable(
				'refresh',{
					
					url : "/jewels/manufacturing/transactions/compFinding/listing.html"
					
				});
	}
	
	
	
		
	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}
	
		return dt;
	}
	
	
	
	var inwTblRow = -1;
	$('#costCompFindingId').bootstrapTable({})
			.on(
					'click-row.bs.table',
					function(e, row, $element) {
						inwTblRow = $element.attr('data-index');
						
							$('#rateUpdate').css('display','block');
							$('#fldRate').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).rate));
							$('#fldRate').focus();
							$('#fldRate').select();
					});
	
	
	
	
	$('#fldRate').on('blur', function(e) {
		$("#costCompFindingId").bootstrapTable('updateRow', {
			index : inwTblRow,
			row : {
				state : true,
				rate : $('#fldRate').val(),
			}
		});
		
		$('#fldRate').val('');
		$('#rateUpdate').css('display','none');
	});
		
 
	function goToMainPage(){
		window.location.href = "/jewels/manufacturing/transactions/costingMt/edit/"+$('#costingMtId').val()+".html";
	}

	
	$(document).
	on('submit',
		'form#setFinding',
		function(e){
		
		var data = $("#costCompFindingId").bootstrapTable("getSelections");
		
		ids = $.map(data, function(item) {
			return item.id;
		});
		
		tempRate = $.map(data, function(item) {
			return item.rate;
		});
		
		$("#compInwDtPk").val(ids);
		$("#vRate").val(tempRate);
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
	
		 $.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR){
					
					if(data === '-1'){
						displayMsg(this, null, 'Wrong Selection');
					}else if(data === "2"){
						displayMsg(this, null, 'Record Not Selected');
					}else{
						window.location.reload(true);
						//displayMsg(this, null, 'Rate Updated sucessfully'); // bcoz of reloading page , message is not visible
					}
				
			},
			error : function(data, textStatus, jqXHR){
				
			}
			
		})
		
		e.preventDefault(); 
		
	})




</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />




