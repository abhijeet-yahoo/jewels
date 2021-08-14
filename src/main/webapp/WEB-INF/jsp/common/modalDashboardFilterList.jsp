<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.modal-footer1 {
    padding: 10px;
    text-align: left;
    border-top: 1px solid #e5e5e5;
}
</style>




	<!--------------------------------------------------- Client modal ---------------------------------------------------------------------------->

	<div class="modal fade" id="myClientModalDs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"onclick="javascript:popClientSaveDs()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Client</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchClient" class="search form-control" placeholder="Client Search " />
		       	</div>
	       </div>		       
	       
			<div id="clientTableDivId">
				<table  id="clientIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">CLIENT</th>
					 </tr>
					</thead>
				</table> 
			</div>
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popClientDs(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popClientSaveDs()">Close</button>
	      </div>
	      
	    </div>
	  </div>
	</div>


	<!--------------------------------------------------- OrderType modal ------------------------------------------------------------------------------->

	<div class="modal fade" id="myOrderTypeModalDs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popOrderTypeSaveDs()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Order Type</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchOrderType" class="search form-control" placeholder="Order Type Search " />
		       	</div>
	       </div>		       
	       
			<div id="orderTypeTableDivId">
				<table  id="orderTypeIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Order Type</th>		 
					 </tr>
					</thead>
				</table> 
			</div>	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popOrderTypeDs(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	       
	        <button type="button" class="btn btn-primary" onclick="javascript:popOrderTypeSaveDs()">Close</button>
	      </div>
	      
	    </div>
	  </div>
	</div>


	<!--------------------------------------------------- Order modal --------------------------------------------------------------------------->

	<div class="modal fade" id="myOrderModalDs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"  onclick="javascript:popOrderSaveDs()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Order</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchOrder" class="search form-control" placeholder="Order Search " />
		       	</div>
	       </div>		       
	       
			<div id="orderTableDivId">
				<table  id="orderIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 
						
						 <th data-field="invNo" data-align="left" data-sortable="true"style="font-weight: bolder;">Order No</th>
						<th data-field="refNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Ref No</th>
						<input type="checkbox" id="closeOrdFlg"	title="Close Order" /> <strong>Close Order</strong>
					 </tr>
					 
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popOrderDs(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popOrderSaveDs()">Close</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	
<!--------------------------------------------------- Location modal --------------------------------------------------------------------->

<div class="modal fade" id="myLocationModalDs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"   >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popLocationSaveDs()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Location</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchLocation" class="search form-control" placeholder="Location Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="locationTableDivIdDs">
				<table  id="locationIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Location</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popLocationDs(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popLocationSaveDs()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	

<!---------------------------------------------------Marketing Location modal --------------------------------------------------------------------->


<div class="modal fade" id="myMarketingLocationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"   >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popMarketingLocationSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Marketing Location</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchMarketingLocation" class="search form-control" placeholder="MarketingLocation Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="marketingLocationTableDivId">
				<table  id="marketingLocationIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Marketing Location</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popMarketingLocation(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popMarketingLocationSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>




<!--------------------------------------------------- Division Modal --------------------------------------------------------------------->


<div class="modal fade" id="myDivisionModalDs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popDivisionSaveDs()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Division</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchDivision" class="search form-control" placeholder="Division Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="divisionTableDivId">
				<table  id="divisionIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Division</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popDivisionDs(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popDivisionSaveDs()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>



<!--------------------------------------------------- Region Modal --------------------------------------------------------------------->


<div class="modal fade" id="myRegionModalDs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popRegionSaveDs()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Region</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchRegion" class="search form-control" placeholder="Region Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="regionTableDivId">
				<table  id="regionIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Region</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popRegionDs(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popRegionSaveDs()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>




	
<!--------------------------------------------------- customerType Modal --------------------------------------------------------------------->

 <div class="modal fade" id="myCustomerTypeModalDs" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popCustomerTypeSaveDs()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Customer Type</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchCustomerType" class="search form-control" placeholder="CustomerType Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="customerTypeTableDivId">
				<table  id="customerTypeIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Customer Type</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popCustomerTypeDs(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popCustomerTypeSaveDs()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	
	
	
	
		
<!--------------------------------------------------- BranchMaster Modal --------------------------------------------------------------------->

<div class="modal fade" id="myBranchMasterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popBranchMasterSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Branch</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchBranchMaster" class="search form-control" placeholder="Branch Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="branchMasterTableDivId">
				<table  id="branchMasterIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Branch</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popBranchMaster(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popBranchMasterSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	
<!--------------------------------------------------- End of modal's ---------------------------------------------------------------------------------------------------->


<script type="text/javascript">
    	
	/*  ========================================== Client Fn ===================================================================== */
	
	var $clientTable = $('#clientIdTbl');
    $(function () {
        $('#myClientModalDs').on('shown.bs.modal', function () {
            $clientTable.bootstrapTable('resetView');
        });
    }); 
    
	var clientStatus = "false";
	function popClientDs(val) {
		if(val === 0){
			if(clientStatus === 'false'){
				$("#clientIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/party/report/listing.html"
				});
				clientStatus = true;
			}
		}else{
			$("#clientIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/party/report/listing.html"
			});
			$('#clientTextBoxDs').val('');
		}
		
	}
	
	
	 function popClientSaveDs(){
		
		$("#myClientModalDs").modal("hide");
		var	data = $('#clientIdTbl').bootstrapTable('getSelections');
		var	clientNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = clientNm.toString().replace(/,/g, ", ");
		$('#clientTextBoxDs').val(tempRes);
		
	} 
	 
	/*  ========================================== Order Type Fn ===================================================================== */
		
	var $orderTypeTable = $('#orderTypeIdTbl');
    $(function () {
        $('#myOrderTypeModalDs').on('shown.bs.modal', function () {
            $orderTypeTable.bootstrapTable('resetView');
        });
    }); 
	
	
  
	var orderTypeStatus = "false";
	function popOrderTypeDs(val) {
		if(val === 0){
			if(orderTypeStatus === 'false'){
				$("#orderTypeIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/orderType/listing.html?opt=3"
				});
				orderTypeStatus = true;
			}
		}else{
			$("#orderTypeIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/orderType/listing.html?opt=3"
			});
			$('#orderTypeTextBoxDs').val('');
		}
		
	}
	
	 function popOrderTypeSaveDs(){
			
			$("#myOrderTypeModalDs").modal("hide");
			var	data = $('#orderTypeIdTbl').bootstrapTable('getSelections');
			var	orderTypeNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = orderTypeNm.toString().replace(/,/g, ", ");
			$('#orderTypeTextBoxDs').val(tempRes);
			
		}
	
		/*  ========================================== Order Fn ===================================================================== */
		
		var $orderTable = $('#orderIdTbl');
	    $(function () {
	        $('#myOrderModalDs').on('shown.bs.modal', function () {
	            $orderTable.bootstrapTable('resetView');
	           
	        });
	    }); 
		
		
		var orderStatus = "false";
		function popOrderDs(val) {
			var orderFlg = "1";
			if ($("#closeOrdFlg").prop("checked") == true) {

				orderFlg = "2";
			} else {
				orderFlg = "1";
			}

			var clientData = $('#clientIdTbl').bootstrapTable('getSelections');
			var clientIds = $.map(clientData, function(item) {
				return item.id;
			});

			var orderTypeData = $('#orderTypeIdTbl').bootstrapTable('getSelections');
			var orderTypeIds = $.map(orderTypeData, function(item) {
				return item.id;
			});

			var divisionData = $('#divisionIdTbl').bootstrapTable('getSelections');
			var divisionIds = $.map(divisionData, function(item) {
				return item.id;
			});

			var regionData = $('#regionIdTbl').bootstrapTable('getSelections');
			var regionIds = $.map(regionData, function(item) {
				return item.id;
			});

			var customerTypeData = $('#customerTypeIdTbl').bootstrapTable('getSelections');
			var customerTypeIds = $.map(customerTypeData, function(item) {
				return item.id;
			});


			
			
			if(val === 0){
				if(orderStatus === 'false'){
					$("#orderIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/order/report/listing.html?partyId="+clientIds+"&orderTypeId="
							+orderTypeIds+"&ordFlg="+orderFlg+"&fromDate="+$('#fromOrdDate').val()+"&toDate="+$('#toOrdDate').val()+"&divisionIds="+divisionIds
							+"&regionIds="+regionIds+"&customerTypeIds="+customerTypeIds,
					});
					orderStatus = true;
				}
				
			}else{
				$("#orderIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/order/report/listing.html?partyId="+clientIds+"&orderTypeId="
						+orderTypeIds+"&ordFlg="+orderFlg+"&fromDate="+$('#fromOrdDate').val()+"&toDate="+$('#toOrdDate').val()+"&divisionIds="+divisionIds
						+"&regionIds="+regionIds+"&customerTypeIds="+customerTypeIds,
				});
				$('#orderTextBoxDs').val('');
			}
			
			
			
			}
		
		 function popOrderSaveDs(){
				
				$("#myOrderModalDs").modal("hide");
				var	data = $('#orderIdTbl').bootstrapTable('getSelections');
				var	orderNm = $.map(data, function(item) {
						return item.invNo;
					});
				
				var tempRes = orderNm.toString().replace(/,/g, ", ");
				$('#orderTextBoxDs').val(tempRes);
				
			}
		 
		 
 /*================================================== Department Fn ===================================================================== */
		
		var $departmentTable = $('#departmentIdTbl');
	    $(function () {
	        $('#myDepartmentModal').on('shown.bs.modal', function () {
	            $departmentTable.bootstrapTable('resetView');
	        });
	    }); 
		
		/*  function popDepartment() {
		$("#departmentIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/department/listing.html?opt=4"
		});

		//$('#departmentBtn').css('display','none');
		$('#departmentTableDivId').css('display', 'block');
		$('#searchDepartment').val('');
	} */

	var departmentStatus = "false";
	function popDepartment(val) {
		if(val === 0){
			if(departmentStatus === 'false'){
				$("#departmentIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/department/listing.html?opt=4"
				});
				departmentStatus = true;
			}
		}else{
			$("#departmentIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/department/listing.html?opt=4"
			});
			$('#departmentTextBox').val('');
		}		
	}
	
	function popDepartmentSave(){
		
		$("#myDepartmentModal").modal("hide");
		var	data = $('#departmentIdTbl').bootstrapTable('getSelections');
		var	departmentNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = departmentNm.toString().replace(/,/g, ", ");
		$('#departmentTextBox').val(tempRes);
		
	}
  
	 /*================================================== Location Fn ===================================================================== */		 

	 	var $locationTable = $('#locationIdTbl');
	    $(function () {
	        $('#myLocationModalDs').on('shown.bs.modal', function () {
	            $locationTable.bootstrapTable('resetView');
	        });
	    });
	    
	    
	var locationStatus = "false";
		function popLocationDs(val) {
			if(val === 0){
				if(locationStatus === 'false'){
					$("#locationIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/department/listing.html?opt=3"
					});
					locationStatus = true;
				}
			}else{
				$("#locationIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/department/listing.html?opt=3"
				});
				$('#locationTextBoxDs').val('');
			}
			
		}
		function popLocationSaveDs(){
			
			$("#myLocationModalDs").modal("hide");
			var	data = $('#locationIdTbl').bootstrapTable('getSelections');
			var	locationNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = locationNm.toString().replace(/,/g, ", ");
			$('#locationTextBoxDs').val(tempRes);
			
		}	

	 /*================================================== Marketing Location Fn ===================================================================== */
		 
	 
	 	var $marketingLocationTable = $('#marketingLocationIdTbl');
	    $(function () {
	        $('#myMarketingLocationModal').on('shown.bs.modal', function () {
	            $locationTable.bootstrapTable('resetView');
	        });
	    });
	    
/* 	 	function popMarketingLocation() {

		$("#marketingLocationIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/department/listing.html?opt=5"
		});

		//$('#locationBtn').css('display','none');
		$('#marketingLocationTableDivId').css('display', 'block');
		$('#searchMarketingLocation').val('');
	} */
		
		var marketingLocationStatus = "false";
			function popMarketingLocation(val) {
				if(val === 0){
					if(marketingLocationStatus === 'false'){
						$("#marketingLocationIdTbl").bootstrapTable('refresh', {
							url : "/jewels/manufacturing/masters/department/listing.html?opt=5"
						});
						marketingLocationStatus = true;
					}
				}else{
					$("#marketingLocationIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/department/listing.html?opt=5"
					});
					$('#marketingLocationTextBox').val('');
				}
				
			}
			
			function popMarketingLocationSave(){
				$("#myMarketingLocationModal").modal("hide");
				var	data = $('#marketingLocationIdTbl').bootstrapTable('getSelections');
				var	marketingLocationNm = $.map(data, function(item) {
						return item.name;
					});
				
				var tempRes = marketingLocationNm.toString().replace(/,/g, ", ");
				$('#marketingLocationTextBox').val(tempRes);
				
			}	

	

	/*================================================== Division Fn ===================================================================== */
	
	var $divisionTable = $('#divisionIdTbl');
    $(function () {
        $('#myDivisionModalDs').on('shown.bs.modal', function () {
            $divisionTable.bootstrapTable('resetView');
        });
    }); 
	
	var divisionStatus = "false";
	function popDivisionDs(val) {
		if(val === 0){
			if(divisionStatus === 'false'){
				$("#divisionIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/lookup/typeWise/listing.html?type=Ord Division"
				});
				divisionStatus = true;
			}
		}else{
			$("#divisionIdTbl").bootstrapTable('refresh', {
				url :"/jewels/manufacturing/masters/lookup/typeWise/listing.html?type=Ord Division"
			});
			$('#divisionTextBoxDs').val('');
		}			
	}


		
		function popDivisionSaveDs(){
				
				$("#myDivisionModalDs").modal("hide");
				var	data = $('#divisionIdTbl').bootstrapTable('getSelections');
				var	divisionNm = $.map(data, function(item) {
						return item.name;
					});
				
				var tempRes = divisionNm.toString().replace(/,/g, ", ");
				$('#divisionTextBoxDs').val(tempRes);
			}
		
		
		/*================================================== Region Fn ===================================================================== */
		
		var $regionTable = $('#regionIdTbl');
		$(function () {
		    $('#myRegionModalDs').on('shown.bs.modal', function () {
		        $regionTable.bootstrapTable('resetView');
		    });
		}); 
		
		var regionStatus = "false";
		function popRegionDs(val) {
			if(val === 0){
				if(regionStatus === 'false'){
					$("#regionIdTbl").bootstrapTable('refresh', {
						url :"/jewels/manufacturing/masters/lookup/typeWise/listing.html?type=Party Region"
					});
					regionStatus = true;
				}
			}else{
				$("#regionIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/lookup/typeWise/listing.html?type=Party Region"
				});
				$('#regionTextBoxDs').val('');
			}			
		}
		
		
		
		function popRegionSaveDs(){
			
			$("#myRegionModalDs").modal("hide");
			var	data = $('#regionIdTbl').bootstrapTable('getSelections');
			var	regionNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = regionNm.toString().replace(/,/g, ", ");
			$('#regionTextBoxDs').val(tempRes);
		}


/*================================================== CustomerType Fn ===================================================================== */
		
		var $customerTypeTable = $('#customerTypeIdTbl');
		$(function () {
		    $('#myCustomerTypeModal').on('shown.bs.modal', function () {
		        $customerTypeTable.bootstrapTable('resetView');
		    });
		}); 
		
		var customerTypeStatus = "false";
		function popCustomerTypeDs(val) {
			if(val === 0){
				if(customerTypeStatus === 'false'){
					$("#customerTypeIdTbl").bootstrapTable('refresh', {
						url :"/jewels/manufacturing/masters/lookup/typeWise/listing.html?type=Customer Type"
					});
					customerTypeStatus = true;
				}
			}else{
				$("#customerTypeIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/lookup/typeWise/listing.html?type=Customer Type"
				});
				$('#customerTypeTextBox').val('');
			}			
		}
		
		
		
		function popCustomerTypeSaveDs(){
			
			$("#myCustomerTypeModalDs").modal("hide");
			var	data = $('#customerTypeIdTbl').bootstrapTable('getSelections');
			var	customerTypeNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = customerTypeNm.toString().replace(/,/g, ", ");
			$('#customerTypeTextBoxDs').val(tempRes);
		}




		
		
		/*================================================== END of Functions ===================================================================== */	
		
		
		
		
</script>