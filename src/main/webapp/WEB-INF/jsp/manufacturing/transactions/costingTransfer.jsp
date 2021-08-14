<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel-body">

<div class="form-group">
<label class="col-sm-2 text-right control-label">From Invoice :</label>
<div class="col-sm-2">
	<input type="text" id="fromInv" name="fromInv" class="form-control" 
	onBlur="javascript:popCostingDt();popInvoice();" />
</div>

<div class="pull-right search">
<input type="search" id="searchCostingDtBags"  class="search form-control" placeholder="Type here to search">
</div>
			
			
</div>

<div>
	<table  class="table-responsive" id="costDtTabId"
								data-toggle="table" data-toolbar="#toolbarDt"
								data-side-pagination="server" data-click-to-select="false"
								data-select-item-name="radioNameCostDt"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="375">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-checkbox="true"></th>  
										<th data-field="itemNo" data-sortable="true">ItemNo</th>
										<th data-field="party" data-sortable="false">Client</th>
										<th data-field="ordNo" data-sortable="false">OrderNo</th>
										<th data-field="ordRefNo" data-sortable="false">RefNo</th>
										<th data-field="style" data-sortable="false" class="span5">Style</th>
										<th data-field="bag" data-sortable="false" class="span5">Bag</th>
										<th data-field="purity" data-sortable="false">Kt</th>
										<th data-field="color" data-sortable="false">Color</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">GrosWt</th>
										<th data-field="netWt" data-sortable="false">NetWt</th>
										<th data-field="metalRate" data-sortable="false">MetRate</th>
										<th data-field="metalValue" data-sortable="false">MetValue</th>
										<th data-field="stoneValue" data-sortable="false">StnValue</th>
										<th data-field="compValue" data-sortable="false">CompValue</th>
										<th data-field="labourValue" data-sortable="false">LabValue</th>
										<th data-field="setValue" data-sortable="false">SetValue</th>
										<th data-field="handlingCost" data-sortable="false">HdlgCost</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="other" data-sortable="false">Other</th>
										<th data-field="dispPercentDt" data-sortable="false">Disp%</th>
										<th data-field="finalPrice" data-sortable="false">FinalPrice</th>
										
									</tr>
								</thead>
							</table>
	</div>
	<div class="form-group">
		<label class="col-sm-2 text-right control-label">To Invoice :</label>
		<div class="col-sm-2">
			<input type="text" id="toInv" name="toInv" class="form-control" />
		</div>
		<div class="col-sm-2">
			<button id="transferBtn" class="btn btn-primary" onClick="javascript:popTransfer();">Transfer</button>
			<input type="hidden" id=fromInvNo name=fromInvNo />
		</div>

</div>
			
			




</div>

<script type="text/javascript">
var tindex='';
$(document).ready(function(){
	 $('#fromInv').autocomplete({
		source:"/jewels/manufacturing/transactions/costing/invNoList.html?fieldNm="+$('#fromInvNo').val(),
	}); 
		
	
	
		
	
	$('#searchCostingDtBags').on("keyup",function(){
		var value=$(this).val();
		tindex='';
		$("#costDtTabId tr").each(function(index){
			if(index !=0){
				$row=$(this);
				  var item = $row.find('td:eq(1)').text();
				  var clnt = $row.find('td:eq(2)').text();
		            var styNoId = $row.find('td:eq(5)').text();  
		            var bId = $row.find('td:eq(6)').text();
		            if (item.toLowerCase().indexOf(value.toLowerCase()) != 0 
		            	&& styNoId.toLowerCase().indexOf(value.toLowerCase()) != 0
		            	&& bId.toLowerCase().indexOf(value.toLowerCase()) !=0
		            	&& clnt.toLowerCase().indexOf(value.toLowerCase()) !=0){
		                $(this).hide();
		               
		            }
		            else {
		                $(this).show();
		                if(tindex !=''){
		            		tindex=tindex+','+index;
		            	}else{
		            		tindex=index;	
		            	}
		            }
				
			}
		});
	});
	
	
	
});



 /* $(document).on('blur', 'input[name="btSelectAll"]', function(e){
	 
	 var value=$('#searchCostingDtBags').val();
	 if(value !=''){
     var tindex='';
	 $("#costDtTabId tr").each(function(index){
			if(index !=0){
				$row=$(this);
				  var item = $row.find('td:eq(1)').text();
				  var clnt = $row.find('td:eq(2)').text();
		            var styNoId = $row.find('td:eq(5)').text();  
		            var bId = $row.find('td:eq(6)').text();
		            if (item.toLowerCase().indexOf(value.toLowerCase()) != 0 
		            	&& styNoId.toLowerCase().indexOf(value.toLowerCase()) != 0
		            	&& bId.toLowerCase().indexOf(value.toLowerCase()) !=0
		            	&& clnt.toLowerCase().indexOf(value.toLowerCase()) !=0){
		            	

		            }
		            else {
		            	if(tindex !=''){
		            		tindex=tindex+','+index;
		            	}else{
		            		tindex=index;	
		            	}
		            	

		            }
				
			}
		});
	 
	 $("#costDtTabId").bootstrapTable('uncheckAll');
	 uncheckAllFlg='false';
	 popselect(tindex);
	 }
	 
}); */
  
 
 
$('#costDtTabId').on('check-all.bs.table', function (e) {
	 var value=$('#searchCostingDtBags').val();
	 if(value !=''){
 	 popselect(tindex);
	 }
	 	 		
	});
 
 
 function popselect(val){
	 
	/*  var data =JSON.stringify($('#costDtTabId').bootstrapTable('getData'));
	 $.each(JSON.parse(data),function(idx,obj){
		 $("#costDtTabId").bootstrapTable('updateRow', {
				index : idx,
				row : {
					state : false,
					

				}
			}); 
		 
	 });
	  */
	  
	  val=val+'';
	  $("#costDtTabId").bootstrapTable('uncheckAll'); 
	 var tempindex = val.split(',');
	 for(var i=0;i<=tempindex.length;i++){
		 $("#costDtTabId").bootstrapTable('updateRow', {
				index : Number(tempindex[i])-1,
				row : {
					state : true,
					

				}
			}); 

	
		 
	 }
	  	 
	 searchList();
	 }
	 
 
 
 function searchList(){
	 var value=$('#searchCostingDtBags').val();
		$("#costDtTabId tr").each(function(index){
			if(index !=0){
				$row=$(this);
				  var item = $row.find('td:eq(1)').text();
				  var clnt = $row.find('td:eq(2)').text();
		            var styNoId = $row.find('td:eq(5)').text();  
		            var bId = $row.find('td:eq(6)').text();
		            if (item.toLowerCase().indexOf(value.toLowerCase()) != 0 
		            	&& styNoId.toLowerCase().indexOf(value.toLowerCase()) != 0
		            	&& bId.toLowerCase().indexOf(value.toLowerCase()) !=0
		            	&& clnt.toLowerCase().indexOf(value.toLowerCase()) !=0){
		                $(this).hide();
		               
		            }
		            else {
		                $(this).show();
		              
		            }
				
			}
		});
 }


 function popInvoice(){
	 
	$('#fromInvNo').val($('#fromInv').val());
	 $.getScript('/jewels/js/common/temp.js', function() {
     });
	
} 

function popCostingDt(){
	$("#costDtTabId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costingDt/listing.html?pInvNo="
						+ $('#fromInv').val()
			});

	
}

function popTransfer(e){
	displayDlg(
			e,
			'javascript:transferData();',
			'Transfer',
			'Do you want to Transfer  ?',
			'Continue');
}
function transferData(){
	$('#modalDialog').modal('hide');
	var data = $('#costDtTabId').bootstrapTable('getSelections');
	var ids = $.map(data, function(item) {
		return item.id;
	});
	if(ids.length<=0){
		displayMsg(this,null,'Please Select Record To Transfer ');
	}else if($('#toInv').val()=='' || $('#toInv').val()==null){
		displayMsg(this,null,'Please Select Invoice To Transfer ');
	}else{
	
		$.ajax({
		url:"/jewels/manufacturing/transactions/costing/transfer.html",
			type:"POST",
			data:{
				dtids:ids+'',
				fromInvoice:$('#fromInv').val(),
				toInvoice:$('#toInv').val()
			},
		success:function(data){
			if(data === '1'){
				displayInfoMsg(this,null,'Transfer Successfully');
				popCostingDt();
				$('#toInv').val('');
				
			}else{
				displayMsg(this,null,'Not Transfer ,Error ');
			}
			
		}
		
	})	
	}
}


</script>
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
