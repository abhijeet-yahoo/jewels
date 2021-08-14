<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>



	<!--------- Order Pickup modal --------->
	
	<div class="modal fade" id="myOrderPickupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Order Pickup</h4>
	      </div>
	      
	      <div class="modal-body">
		
		
		<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
			<label class="control-label text-right">Party</label>
			
			<input type="text" id="partyNm" name="partyNm" class="form-control" readonly="readonly"  autocomplete="off">
			</div>
		</div>	

<div class="col-sm-4">
					
			<div class="form-group">
			    <label class="control-label text-right" >From Order </label>
				<input type="text" id="fromOrderNm" name="fromOrderNm" class="form-control" onchange="javascript:getOrderDtList()" autocomplete="off">
			</div>
		
		</div>
		</div>
		
		
		<div class="form-group">
		    <div class="container-fluid">
		 
						 <table id="pickUpDtId" data-toggle="table"
									data-toolbar="#toolbar" data-pagination="true"
									data-side-pagination="server" data-search="true" 
									data-page-list="[5, 10, 20, 50, 100, 200]"   data-height="400">
							  <thead>
								<tr class="btn-primary">
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="srNo"  data-sortable="false">Sr No.</th>
									<th data-field="style"  data-sortable="false">Style No</th>
									<th data-field="purity" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="pcs" data-sortable="false">Pcs</th>
									<th data-field="grossWt" data-sortable="false">GrossWt</th>
									<th data-field="productSize" data-sortable="false">Size</th>
									
								</tr>
							</thead>
						</table>
					
			</div>
	   </div>
	
		
		
				
		
		</div>
		
		
		<div class="modal-footer">
	        	<input type="button" value="Save" class="btn btn-default" id="orderPickupSaveBtnId" onclick="javascript:popOrderPickupSave()"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<input type="hidden" id="modalquotMtId" name="modalquotMtId">
	       </div>
	      
		
		
		
	</div>
	
	
	 
	
  </div>

 
</div>




<script type="text/javascript">

var partyId = "";
var mtId = "";
var orderTypeId="";



$(document).ready(
		function(e) {
			
			$('#partyNm').autocomplete({
				source: "/jewels/manufacturing/masters/party/autoFillList.html",
				minLength : 2,
				
		        change: function (event, ui) {
                    if (ui.item == null || ui.item == undefined) {
                        $("#partyNm").val("");
                    }
                }
				
			});
			
			
			
			
			
			 $('#fromOrderNm').autocomplete({
					source:"/jewels/manufacturing/masters/order/invNoList.html?partyNm="+$('#partyNm').val(),
							
							
		            change: function (event, ui) {
	                 if (ui.item == null || ui.item == undefined) {
	                     $("#fromOrderNm").val("");
	                    
	                 }
	               
	             }
							
							
				}); 
			
		});
		
		
		function getOrderDtList(){
			
	if($('#partyNm').val() != '' && $('#fromOrderNm').val() != ''){
			$("#pickUpDtId")
				.bootstrapTable(
					'refresh',{
						url : "/jewels/manufacturing/masters/orderDt/pickUpDt/listing.html?orderNm="+$('#fromOrderNm').val(),
					});
		}
		}
		
		
		
		function popOrderPickupSave(){
			
			var data = $('#pickUpDtId').bootstrapTable('getSelections');
			
			var ids = $.map(data, function (item) {
					return item.id;
			});
			
			var mtId = $('#modalquotMtId').val();
							
			$.ajax({ 
				url : "/jewels/manufacturing/masters/quaotDt/pickupFromOrder.html",
				type : "POST",
				data : {
					pODIds :ids+"",
					mtId   :mtId,
				
				},
				
				 success : function(data, textStatus, jqXHR) {	
				
					 if(data === '1'){
						 displayInfoMsg(this, null, 'Data Transfer Successfully');
						 popQuotationDetails();
						 $('#myOrderPickupModal').modal('hide');	
						
					 }else if(data ==="-1"){
						 displayMsg(this, null, 'Item Not Selected ');
					 }
					 
			
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
					
				} 
				
			});

		}
		
	
</script> 



<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/common/blockUserInterface.js"></script>



