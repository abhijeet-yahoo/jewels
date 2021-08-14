<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>


<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>


<div id="looseStnConversionDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
	
	<div>
	
	<div>
	<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Loose Stone Conversion Pickup</span>
			</label>
		
	
	</div>
		
	
			
		<div class="text-right">
		<a class="btn btn-xs btn-info" id="stnInwBackBtnId" style="font-size: 14px" type="button" onclick="javascript:stnInwBackBtn()"><b>Back</b></a>
		</div>	
	
	</div>		
	
	</div>
	
	
	
	<div class="form-group">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					
					<div>
						<table id="looseStnConvPickupTblDtId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="450" data-striped="true" data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-checkbox="true">Select</th>
									<th data-field="invNo" data-sortable="true">Voucher No</th>
									<th data-field="invDate" data-sortable="true">Voucher Date</th>
									<th data-field="supplier" data-sortable="true">Supplier</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="size" data-sortable="true">Size</th>
									<th data-field="sizeGroup" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
			</div>
			
				
				
			</div>
			</div>
			
			
			<div class="col-sm-12">
				<div class="form-group">
			<div>
				<input type="button" value="Transfer" class="btn btn-primary" id="transferBtnId"  onClick="javascript:looseStnConvTrf();" />
			</div>
		</div>
			
		
	</div>
			
	
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
			
			</div>
			
	
	</div>
	





		
<script type="text/javascript">

var stnMtid;
var partyId;
$(document).ready(
		function(e) {
			
		 	if (window.location.href.indexOf('mtid') != -1) {
						var vUrl = window.location.href;
				
						stnMtid = vUrl.substring(window.location.href.indexOf('mtid') + 5,window.location.href.indexOf('&supplierId'));
						partyId = vUrl.substring(window.location.href.indexOf('supplierId') + 11);
					
						looseStnConvList();
																	
					}
		
		});



function looseStnConvList(){
	
	$("#looseStnConvPickupTblDtId")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/stnLooseConversionPickup/listing.html?partyId="
						+ partyId
			});
	
	
	
}

	function stnInwBackBtn(){
			window.location.href = "/jewels/manufacturing/transactions/stoneInwardMt/edit/"+stnMtid+".html"
	} 
	

	function looseStnConvTrf(){

		
		var data = $('#looseStnConvPickupTblDtId').bootstrapTable('getAllSelections');
		var convDtId = $.map(data, function(item) {
			return item.conversionDtId;
		});

		
	 	if(convDtId !=''){
			
		 $('#transferBtnId').attr("disabled", true);

		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/looseStnConv/transfer.html?mtId="+stnMtid+"&convDtId="+convDtId,
		
			type : "POST",
			success : function(data, textStatus, jqXHR) {
				
				$.unblockUI();
				
				if (Number(data) == 1) {

					displayInfoMsg(event, this,'data transfered sucessfully !');
					$('#transferBtnId').attr("disabled", false);
					
				}else{
					
					displayMsg(event, this,data);
					
				
				}			
				looseStnConvList();
				$('#transferBtnId').attr("disabled", false);
				

			},
			error : function(jqXHR, textStatus, errorThrown) {

			}

		}); 
		
		}else{

			displayMsg(event, this,"Record Not Selected");
			} 

		}
		
		
		
</script>	



<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>			
			
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />


<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
