<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>


<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading">
		
		<div class=col-sm-11>
		<span style="font-size: 18px;">Melting Stone Received</span>
		</div>
		
		
		<a class="btn btn-info btn-sm" style="font-size: 15px" type="button"
							href="/jewels/manufacturing/transactions/meltStnRec.html?opt=1">Listing</a>
	</div>
	<form:form commandName="meltingMt" id="meltMtForm"
			action="/jewels/manufacturing/transactions/meltingMt/add.html"
			cssClass="form-horizontal meltingMtForm">

			<c:if test="${success eq true}">
				<div class="alert alert-success">Melting Receive ${action}
					successfully!</div>
			</c:if>

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Inv
							No :</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Inv
							Date :</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Remark
							:</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Loss In Pure
							:</label>
					</div>
					
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Total Receive Stone
							:</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Total Receive Carat
							:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-sm-2">
						<form:input path="invNo" cssClass="form-control" disabled="true"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-sm-2">
						<form:input path="invDate" cssClass="form-control" autocomplete="off" />
						<form:errors path="invDate" />
					</div>
					<div class="col-sm-2">
						<form:input path="remark" cssClass="form-control" autocomplete="off" />
						<form:errors path="remark" />
					</div>
					<div class="col-sm-2">
						<form:input path="loss"  cssClass="form-control" disabled="true" />
						<form:errors path="loss" />
					</div>
					<div class="col-sm-2">
						<input type="text" id="totalStone" name="totalStone" class="form-control" disabled="disabled" />
					</div>
					<div class="col-sm-2">
						<input type="text" id="totalCarat" name="totalCarat" class="form-control" disabled="disabled" />
					</div>
					<div class="col-sm-4">
						
						<form:input type="hidden" path="id" id="meltingMtId"/>
						<form:input type="hidden" path="uniqueId" />
						<form:input type="hidden" path="srNo" />
						<form:input type="hidden" path="invNo" />
						<input type="hidden" id="metLoss" name="metLoss" />
					</div>
				</div>
			</div>
			
			
			<div class="row">
			<div class="col-xs-12">
			
			<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Location
							:</label>
					</div>
			
			</div>
			</div>
			
				<div class="row">
				<div class="col-xs-12">
							<div class="col-sm-2" id="locationDivId" >
					<select class="form-control">
							<option value="">Select Location</option>
							</select>
					</div>
			</div>
			</div>
			
		</form:form>
		
		<div class="row">
		<div class="col-xs-12">&nbsp;</div>
		
		</div>
		
		<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>
		
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Melting Diamond Detail</span>
			</div>
		</div>
	</div>
	
	
	<div class="form-group" id="dsPId">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="meltStnRecTblId" data-toggle="table"
							data-toolbar="#toolbarDt" data-click-to-select="false"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="srNo">Srno</th>
									<th data-field="stoneType">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality">Quality</th>
									<th data-field="size" data-align="right">Size/MM</th>
									<th data-field="sieve" data-align="right">Sieve</th>
									<th data-field="sizeGroup">Size Group</th>
									<th data-field="stones" data-align="right">Stone</th>
									<th data-field="carat" data-align="right">Carat</th>
									<th data-field="balStones" data-align="right">Bal Stone</th>
									<th data-field="balCarat" data-align="right">Bal Carat</th>
									<th data-field="rate" data-align="right">Rate</th>
									<th data-field="avgRate" data-align="right">Avg Rate</th>
									<th data-field="factoryRate" data-align="right">Factory Rate</th>
									<th data-field="trfRate" data-align="right">Transfer Rate</th>
									<th data-field="trfStone" data-formatter="trfStoneFormatter" data-align="center" >Add Stone</th>
									<th data-field="trfCarat" data-align="center" data-formatter="trfCaratFormatter">Add Carat</th>

									
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		
	
		
	</div>
	
	
	
		<div class="col-sm-12">
		
		<div class="col-sm-6">
		<input type="button" value="Add In Stock" class="btn btn-primary" id="transferBtnId"  onClick="javascript:trfData(event);" />
		</div>
		
		<div class="col-sm-6">
		
		<table class="line-items editable table">
				<tr class="panel-heading">
					<th>
						&nbsp;&nbsp;
					Issue Carat : <input type="text" id="vIssueCarat" name="vIssueCarat"   
									maxlength="7" size="7" disabled="disabled" />			
						&nbsp;&nbsp;								
					 Bal Carat : <input type="text" id="vBalCarat" name="vBalCarat"   
									maxlength="7" size="7" disabled="disabled" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
					Adding Carat : <input type="text" id="vAddingCarat" name="vAddingCarat"
							maxlength="7" size="7" disabled="disabled" />
						</th>
						
				</tr>
			</table>
		
		</div>
		
	</div>
		
		
		
	</div>
	
	
	
<script type="text/javascript">

$(document).ready(
		function(e) {

			 popLocationDropDown();

		$('#totalStone').val('${totalStone}');
			var carat = '${totalCarat}';
			$('#totalCarat').val(Math.round(Number(carat)*1000)/1000);
			
			
			popMeltRec();
				
		})
		
	function popMeltRec(){
	
	setIndexZeros();
	
	$("#meltStnRecTblId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/melting/stnRec/listing.html?meltingMtId="+$('#meltingMtId').val(),
					
			});		
	
	}
	
	

function trfStoneFormatter(value,row,index){

	return '<input class="form-control data-input"  style="width:80px" value="'+ value+ '" onchange="javascript:updateTrfStone(this,'+index+')"  />';
} 

	function updateTrfStone(param,vidx){
		$('#meltStnRecTblId').bootstrapTable('updateRow', {
		index : Number(vidx),
		row : {
			trfStone : param.value,
		}
	});
		
		
		
	}



function trfCaratFormatter(value,row,index){
	return '<input class="form-control data-input"  style="width:80px" value="'+ value+ '" onchange="javascript:updateTrfCarat(this,'+index+')"  />';
}

function updateTrfCarat(param,vidx){
		
		
		var data = $('#meltStnRecTblId').bootstrapTable('getData');
		var returnedData = $.grep(data, function(element, index) {
			var vSr = Number(element.srNo - 1);
			return vSr == vidx;
		});
		
				
		if(Number(param.value) > Number(returnedData[0].balCarat)){
			$('#meltStnRecTblId').bootstrapTable('updateRow', {
				index : Number(vidx),
				row : {
					trfCarat : '0.0',
				}
			});
			
			
			
			displayMsg(this,null,"Trf Carat Cannot Be Greater Than Bal Carat");
		
		}else{
			$('#meltStnRecTblId').bootstrapTable('updateRow', {
				index : Number(vidx),
				row : {
					trfCarat : param.value,
				}
			});
			
			 var data = JSON.stringify($("#meltStnRecTblId").bootstrapTable('getData'));
			 var addCarat =0.0;
				$.each(JSON.parse(data), function(idx, obj) {
					addCarat     += Number(obj.trfCarat);
				});
				$('#vAddingCarat').val(" " + parseFloat(addCarat).toFixed(3));
			
			
		}
		
		
		
	}
	

function trfData(e){
	displayDlg(e,'javascript:transferData();', 'Transfer Bags','Do you want to transfer?', 'Continue');
	
}


function transferData(){
	
	/* var len = $("#meltStnRecTblId").bootstrapTable('getData').length;
	for(i=0;i<len;i++){
		$('#meltStnRecTblId  tbody tr').eq(i).removeAttr('style','color:red');
	}
	 */
	$("#modalDialog").modal("hide");


		if($('#locationDropDownId').val() != ''){
	
	if(Number($("#meltStnRecTblId").bootstrapTable('getSelections').length) > 0){
		var data = JSON.stringify($("#meltStnRecTblId").bootstrapTable('getSelections'));
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/meltingStnRec/transfer.html",
			type : "POST",
			data : {
				data : data,
				meltingMtId : $('#meltingMtId').val(),
				locationId : $('#locationDropDownId').val(),
			},
			success : function(data, textStatus, jqXHR) {
				
				if(data != '-1'){
				
					displayInfoMsg(this,null,data);
					
				}else{
				
					displayMsg(this,null,'Record Not Save ,Contact Admin');
				}
				
				 popMeltRec();
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}else{
		displayMsg(this,null,"Record Not Selected");
	}
}else{
	displayMsg(this,null,"Please Select Location");
}	
}


function setIndexZeros(){
	trfCaratSrNo = 0;
	trfStoneSrNo = 0;
	
	}
	
	
$('#meltStnRecTblId').on('check.bs.table check-all.bs.table', function () {
	
	 var data =JSON.stringify($('#meltStnRecTblId').bootstrapTable('getData'));
	 $.each(JSON.parse(data),function(idx,obj){
		 if(obj.state==true){
			 
			
						
				if(obj.trfCarat>0 && obj.trfStone>0 ){
					
					
				}else{
					 $("#meltStnRecTblId").bootstrapTable('updateRow', {
							index : idx,
							row : {
								trfStone : obj.balStones,
								trfCarat : obj.balCarat,
								

							}
						}); 
					 
					 var data = JSON.stringify($("#meltStnRecTblId").bootstrapTable('getData'));
					 var addCarat =0.0;
						$.each(JSON.parse(data), function(idx, obj) {
							addCarat     += Number(obj.trfCarat);
						});
						$('#vAddingCarat').val(" " + parseFloat(addCarat).toFixed(3));
				}
				
		
			 
	
	
			 
		 }
		 

		 
	 });
	
	
	
});




$('#meltStnRecTblId').bootstrapTable({})
.on(
	'load-success.bs.table',
	function(e, data) {
		var data = JSON.stringify($("#meltStnRecTblId").bootstrapTable('getData'));
		var issueCarat = 0.0;
		var balCarat = 0.0;
		var addingCarat = 0.0;


		

		$.each(JSON.parse(data), function(idx, obj) {
			issueCarat		+= Number(obj.carat);
			balCarat		+= Number(obj.balCarat);
			addingCarat     += Number(obj.trfCarat);
			
		});
		
		$('#vIssueCarat').val(" " + parseFloat(issueCarat).toFixed(3));
		$('#vBalCarat').val(" " + parseFloat(balCarat).toFixed(3));
		$('#vAddingCarat').val(" " + parseFloat(addingCarat).toFixed(3));
	
		
	});

function popLocationDropDown() {
	

	$.ajax({
		url : '/jewels/manufacturing/masters/location/dropDownlist.html?mOpt=stone',
		type : 'GET',
		success : function(data) {
			$("#locationDivId").html(data);

		}
	});
}
			

</script>


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />
	