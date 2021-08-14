<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalCosting.jsp"%>

<div class="panel panel-primary" style="height: 100%">

<div class="panel-body">


<div class="form-group">
<div class="col-sm-12">
<label class="col-sm-1  control-label">Invoice :</label>
<div class="col-sm-2">
	<input type="text" id="mtId" name="mtId" class="form-control"  onblur="javascript:popRefresh()" />
</div>

<div class="col-sm-1">
	<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myCostingDtModal" onclick="javascript:popCostingDt($('#mtId').val())" ></button>
	<input type="hidden"  name="costingJobId" id="costingJobId" /> 
</div>



</div>
			
			
</div>
<div class="col-sm-12">&nbsp;</div>
<div class="form-group">
<div class="col-sm-12">
	<input type="radio" id="xx" name="gender" value="Product" onclick="javascript:popDetail()"> Product Detail &nbsp;&nbsp;&nbsp;&nbsp;
  	<input type="radio" id="yy" name="gender" value="Stone" onclick="javascript:popStoneDetail()">Stone Detail<br>
</div>
</div>

<!-- Detail Table Listing -->
<div style="display: none" id="dtTableDivId">
	<table  class="table-responsive" id="dtTabId"
								data-toggle="table" data-toolbar="#toolbarDt"
								data-side-pagination="server" data-click-to-select="false"
								data-height="375" data-show-export="true">
								<thead>
									<tr class="btn-primary">
										<th data-field="productId" data-sortable="true">ProductId</th>
										<th data-field="productCode" data-sortable="false">Product_code</th>
										<th data-field="productName" data-sortable="false">ProductName</th>
										<th data-field="categoryName" data-sortable="false">CategoryName</th>
										<th data-field="subCategoryName" data-sortable="false">SubCategoryName</th>
										<th data-field="programName" data-sortable="false">ProgramName</th>
										<th data-field="noofPcsinpack" data-sortable="false">NoofPcsinpack</th>
										<th data-field="theam" data-sortable="false">Theam</th>
										<th data-field="collectionName" data-sortable="false">CollectionName</th>
										<th data-field="styleno" data-sortable="false">Styleno</th>
										<th data-field="styleName" data-sortable="false">StyleName</th>
										<th data-field="mRP" data-sortable="false">MRP</th>
										<th data-field="description" data-sortable="false">Description</th>
										<th data-field="shopFor" data-sortable="false">ShopFor</th>
										<th data-field="metalName" data-sortable="false">MetalName</th>
										<th data-field="metalPurityName" data-sortable="false">MetalPurityName</th>
										<th data-field="metalColorName" data-sortable="false">MetalColorName</th>
										<th data-field="weight" data-sortable="false">Weight</th>
										<th data-field="engraving" data-sortable="false">Engraving</th>
										<th data-field="productType" data-sortable="false">ProductType</th>
										<th data-field="instock" data-sortable="false">instock</th>
										
									</tr>
								</thead>
							</table>
	</div>


<!-- Stone Detail Table Listing -->

<div style="display: none" id="dtStnTableDivId">
	<table  class="table-responsive" id="dtStnTabId"
								data-toggle="table" data-toolbar="#toolbarDt"
								data-side-pagination="server" data-click-to-select="false"
								 data-height="375" data-show-export="true">
								<thead>
									<tr class="btn-primary">
										<th data-field="productCode" data-sortable="false">Product_code</th>
										<th data-field="stoneTypeName" data-sortable="false">StoneTypeName</th>
										<th data-field="stoneShapeName" data-sortable="false">StoneShapeName</th>
										<th data-field="subStoneShapeName" data-sortable="false">SubStoneShapeName</th>
										<th data-field="stoneQualityName" data-sortable="false">StoneQualityName</th>
										<th data-field="stonePcs" data-sortable="false">StonePcs</th>
										<th data-field="stoneCaret" data-sortable="false">StoneCaret</th>
										<th data-field="stoneSize" data-sortable="false">StoneSize</th>
										<th data-field="stoneSizeGroup" data-sortable="false">StoneSizeGroup</th>
																				
									</tr>
								</thead>
							</table>
	</div>
	



	
	
	
	


</div>

</div>


<script type="text/javascript">
var oldInv="";

$(document).ready(function(){
	 $('#mtId').autocomplete({
			source:"/jewels/manufacturing/transactions/costing/invNoList.html?fieldNm="+$('#mtId').val(),
		}); 
	 
	 
	 
	 
})

function popDetail(){
	
	
	$('#dtStnTableDivId').css('display','none');
	$('#dtTableDivId').css('display','block');
	
	
	
	
	$("#dtTabId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costingMt/dtListingExcel.html?invCond="
						+ $('#costingJobId').val()
			});
}

function popStoneDetail(){
	$('#dtTableDivId').css('display','none');
	$('#dtStnTableDivId').css('display','block');
	

	
	

	$("#dtStnTabId")
	.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/costingMt/dtStnListingExcel.html?invCond="
					+ $('#costingJobId').val()
			});
	
}

function popRefresh(){
	$('#dtTableDivId').css('display','none');
	$('#dtStnTableDivId').css('display','none');
	

	$('#xx').prop('checked', false);
	$('#yy').prop('checked', false);
	
	
}



function popCostingDt(val) {
	
	
	if(oldInv != val){
			
			$("#costingDtIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/transactions/costingDt/listing.html?pInvNo="+$('#mtId').val(),
			});
			
			$('#costingJobId').val('');	
			oldInv=val;
	}	
		
}





$('#myCostingDtModal').on('hidden.bs.modal', function () {
	  // do something…
	  data = $('#subCategoryIdTbl').bootstrapTable('getSelections');
					ids = $.map(data, function(item) {
						return item.id;
					});
					$('#pSubCategoryIds').val(ids);
	  
		
		var	data = $('#costingDtIdTbl').bootstrapTable('getSelections');
		var	jobNm = $.map(data, function(item) {
				return item.id;
			});
		
		
		$('#costingJobId').val(jobNm);
	  
	})






</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/export/bootstrap-table-export.js' />"></script>
<script src="<spring:url value='/js/export/FileSaver.min.js' />"></script>
<script src="<spring:url value='/js/export/jspdf.min.js' />"></script>
<script src="<spring:url value='/js/export/jspdf.plugin.autotable.js' />"></script>
<script src="<spring:url value='/js/export/tableExport.js' />"></script>
