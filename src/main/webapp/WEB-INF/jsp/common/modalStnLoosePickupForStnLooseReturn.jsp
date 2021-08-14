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


    <div class="modal fade" id="myStnLoosePickupModalForReturn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Loose Stone Details</h4>
			</div>

			<div class="modal-body">
			
			
	
		<div class="row">
				
					<table id="stnLooseMtTblId" data-toggle="table" 
						 data-search="true"	data-height="450" data-unique-id="id">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
									<th data-field="invNo" data-sortable="true">Voucher No</th>
									<th data-field="supplier" data-sortable="true">Supplier</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="size" data-sortable="true">Size</th>
									<th data-field="sizeGroup" data-sortable="true">Size Group</th>
									<!-- <th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th> -->
									<th data-field="balstone" data-sortable="true">BalStone</th>
									<th data-field="balcarat" data-sortable="true">BalCarat</th>
									<th data-field="trfStone" data-formatter="trfStoneFormatter" data-align="right" >Trf Stone</th>
									<th data-field="trfCarat" data-align="right" data-formatter="trfCaratFormatter">Trf Carat</th>
									<th data-field="avgRate" data-sortable="true">Avg Rate</th>
									<th data-field="balAmount" data-sortable="true">Bal Amount</th>
									<th data-field="invDate" data-align="left">Voucher Date</th>
									
							</tr>
						</thead>
					</table>
				
			
		</div>
		
	
			
			
		<div class="modal-footer">
		
			<input type="button" value="Save" id="stnLooseTrfBtnId" class="btn btn-primary" onclick="javascript:stnLooseTransfer()"/>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
<script type="text/javascript">

$(document).ready(
		function(e) {
			
			
			
			
		});
		
		
		
		
		
		function stnLooseTransfer(){
			
			$('#stnLooseTrfBtnId').attr('disabled', 'disabled');
			
			if(Number($("#stnLooseMtTblId").bootstrapTable('getSelections').length) > 0){

				 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 

				 var data = JSON.stringify($("#stnLooseMtTblId").bootstrapTable('getSelections'));
			
				
					$.ajax({
						url : "/jewels/manufacturing/transactions/stnLooseRetDt/Transfer.html",
						type : "POST",
						data : {
							mtId :  mtid,
							data   : data,
						},
						success : function(data, textStatus, jqXHR) {
							
							
							$.unblockUI();
							
							if (Number(data) == 1) {

								displayInfoMsg(event, this,'data transfered sucessfully !');
								popStnLoosePickupPartyWiseList();
								popStnLooseRetDt();
								
								$('#stnLooseTrfBtnId').attr("disabled", false);
								
							}		
						
						},
						error : function(jqXHR, textStatus, errorThrown) {

						}

					});
				 
				 
			
			
			}else{
				displayMsg(this,null,"Record Not Selected");
				$('#stnLooseTrfBtnId').removeAttr('disabled');
			}
			
			
			
		}


	
	function trfStoneFormatter(value, row, index){
			
			var tempId = 'stoneval' + Number(index);
			
			var vId = row.dtId;
			var balstone = row.balstone;
			
		    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateStone(this,'+vId+','+balstone+')" />';
		    
		}



	function updateStone(param,dtId,balstone){

		 if(param.value > balstone){

			$('#stnLooseMtTblId').bootstrapTable('updateByUniqueId', {
	 	        id: dtId,
	 	        row: {
	 	        	trfStone : 0
	 	        }
	 	      });
	 	      
			}else{

				$('#stnLooseMtTblId').bootstrapTable('updateByUniqueId', {
		 	        id: dtId,
		 	        row: {
		 	        	trfStone : param.value,
		 	        }
		 	      });
				} 


			
 	}




	function trfCaratFormatter(value, row, index){
		
		var tempId = 'carat' + Number(index);
		
		var vId = row.dtId;
		var balcarat = row.balcarat;
		
	    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateCarat(this,'+vId+','+balcarat+')" />';
	    
	}



function updateCarat(param,val,balcarat){

	if(param.value > balcarat){

		$('#stnLooseMtTblId').bootstrapTable('updateByUniqueId', {
 	        id: val,
 	        row: {
 	        	trfCarat : 0
 	       	 }
 	      });
 	      
		}else{

			$('#stnLooseMtTblId').bootstrapTable('updateByUniqueId', {
	 	        id: val,
	 	        row: {
	 	        	trfCarat : param.value,
	 	        	state :true
	 	        }
	 	      });
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