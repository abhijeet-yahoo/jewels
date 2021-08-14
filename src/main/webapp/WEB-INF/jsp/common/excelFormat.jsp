<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



	<!--------- labour modal --------->

	<div class="modal fade" id="labourFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Labour</h4>
	      </div>
	      
	      <div class="modal-body">
			       
 	       
			<div id="labourTableDivId">
				<table  id="labourFormatTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="60" >
					<thead>
					  <tr class="btn-primary">
						 <th data-field="party" style="font-weight: bolder;">Party</th>
						 <th data-field="category" style="font-weight: bolder;">Category</th>
						 <th data-field="labourType" style="font-weight: bolder;">Labour Type</th>
						 <th data-field="fromWt" style="font-weight: bolder;">From Wt</th>
						 <th data-field="toWt" style="font-weight: bolder;">To Wt</th>
						 <th data-field="rate" style="font-weight: bolder;">Rate</th>
						 <th data-field="perPcsRate" style="font-weight: bolder;">PerPcsRate</th>
						 <th data-field="perGramRate" style="font-weight: bolder;">PerGramRate</th>
						 <th data-field="percentage" style="font-weight: bolder;">Percentage</th>
						 <th data-field="defLabour" style="font-weight: bolder;">Def Labour</th>
						 <th data-field="metal" data-align="left" data-sortable="true">Metal</th>
						 <th data-field="perCaratRate" style="font-weight: bolder;">PerCaratRate</th>
						 <th data-field="purity" data-align="left" data-sortable="true">Purity</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	       <div class="modal-footer">
	       
  			<a class="btn btn-default" type="button"
				href="#" onclick="javascript:getLabourExcelFile()"><span
				class="glyphicon glyphicon-download"></span>&nbsp;Download Excel Format
			</a>
  			
  			
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	      
	      	      
	    </div>
	  </div>
	</div>
	
	
	
	
	
		<!--------- Finding Rate modal --------->

	<div class="modal fade" id="findingRateFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Finding Rate</h4>
	      </div>
	      
	      <div class="modal-body">
			       
 	       
			<div id="findingRateTableDivId">
				<table  id="findingRateTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="60" >
					<thead>
					  <tr class="btn-primary">
						 <th data-field="party" data-align="left" data-sortable="true">Party</th>
						<th data-field="component" data-align="left" data-sortable="true">Component</th>
						<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
						<th data-field="rate" data-sortable="true" data-align="left">Rate</th>
						<th data-field="perPcRate">PerPcRate</th>
						<th data-field="perGramRate">PerGramRate</th>
						<!-- <th data-field="deactive">Deactive</th> -->
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	       <div class="modal-footer">
	       
	        <a class="btn btn-default" type="button"
				href="#" onclick="javascript:getFindingRateExcelFile()"><span
				class="glyphicon glyphicon-download"></span>&nbsp;Download Excel Format
			</a>
	       
	       
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	      
	      	      
	    </div>
	  </div>
	</div>
	
	
	<!--------- Stone  Rate modal --------->

	<div class="modal fade" id="stoneRateFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Stone Rate</h4>
	      </div>
	      
	      <div class="modal-body">
			       
 	       
			<div id="stoneRateTableDivId">
				<table  id="stoneRateTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="60" >
					<thead>
					  <tr class="btn-primary">
					  	<th data-field="party" data-align="left" data-sortable="true">Party</th>
	                    <th data-field="stoneType" data-align="left" data-sortable="true">Stone Type</th>
						<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
						<th data-field="quality" data-align="left" data-sortable="true">Quality</th>
						<th data-field="sizeGroup" data-align="left" data-sortable="true">Size Group</th>
						<th data-field="stoneRate" data-sortable="false">PerCarat Rate</th>
						<th data-field="perPcRate" data-sortable="false">PerPc Rate</th>
						<th data-field="sieve" data-sortable="false">Sieve</th>
						
					</tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	       <div class="modal-footer">
	       
	        <a class="btn btn-default" type="button"
				href="#" onclick="javascript:getStoneRateExcelFile()"><span
				class="glyphicon glyphicon-download"></span>&nbsp;Download Excel Format
			</a>
	       
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	      
	      	      
	    </div>
	  </div>
	</div>
	
	
	
	
	<!--------- Setting Charge modal --------->

	<div class="modal fade" id="settingChargeFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Setting Charge</h4>
	      </div>
	      
	      <div class="modal-body">
			       
 	       
			<div id="settingChargeTableDivId">
				<table  id="settingChargeTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="60" >
					<thead>
					  <tr class="btn-primary">
						<th data-field="party" data-align="left">Party</th>
						<th data-field="stoneType" data-align="left">Stone Type</th>
						<th data-field="shape" data-align="left" >Shape</th>
						<th data-field="setting" data-align="left">Setting</th>
						<th data-field="settingType" data-align="left">Setting Type</th>
						<th data-field="rate" data-align="left">Rate</th>
						<th data-field="metal" data-align="left">Metal</th>
						<th data-field="fromWeight" data-align="left">From Wt</th>
						<th data-field="toWeight" data-align="left">To Wt</th>
						
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	       <div class="modal-footer">
	       
	         <a class="btn btn-default" type="button"
				href="#" onclick="javascript:getSettingChargeExcelFile()"><span
				class="glyphicon glyphicon-download"></span>&nbsp;Download Excel Format
			</a>
	       
	       
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	      
	      	      
	    </div>
	  </div>
	</div>
	
	
	
		
		<!--------- Quotation Dt modal --------->

	<div class="modal fade" id="quotDtFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Quotation Detail</h4>
	      </div>
	      
	      <div class="modal-body">
			       
 	       
			<div id="quotDtTableDivId">
				<table  id="quotDtTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="60" >
					<thead>
					  <tr class="btn-primary">
						 <th data-field="party" data-align="left" data-sortable="true">Style</th>
						<th data-field="component" data-align="left" data-sortable="true">KT</th>
						<th data-field="color" data-align="left" data-sortable="true">Color</th>
						<th data-field="qty" data-sortable="true" data-align="left">Qty</th>
						<th data-field="net" data-sortable="true" data-align="left">Net</th>
						<th data-field="ring" data-sortable="true" data-align="left">Ring</th>
						<th data-field="refOrder" data-sortable="true" data-align="left">Ref Order</th>
						<th data-field="stamp" data-sortable="true" data-align="left">Stamp</th>						
						<th data-field="remarks" data-sortable="true" data-align="left">Remarks</th>
						<th data-field="pO" data-sortable="true" data-align="left">PO</th>
						<th data-field="sK" data-sortable="true" data-align="left">SK</th>
						<th data-field="shape" data-sortable="true" data-align="left">Shape</th>
						<th data-field="quality" data-sortable="true" data-align="left">Quality</th>
						<th data-field="carat" data-sortable="true" data-align="left">Carat</th>
						<th data-field="rate" data-sortable="true" data-align="left">Rate</th>
						<th data-field="diaAmnt" data-sortable="true" data-align="left">DiaAmount</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	       <div class="modal-footer">
	       
	        <a class="btn btn-default" type="button"
				href="#" onclick="javascript:getQuotDtExcelFile()"><span
				class="glyphicon glyphicon-download"></span>&nbsp;Download Excel Format
			</a>
	            
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>     	      
	    </div>
	  </div>
	</div>
	
	
	
		
	
		<!--------- Order Dt modal --------->

	<div class="modal fade" id="orderDtFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Order Detail</h4>
	      </div>
	      
	      <div class="modal-body">	       
			<div id="orderDtTableDivId">
				<table  id="orderDtTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="60" >
					<thead>
					  <tr class="btn-primary">
						 <th data-field="party" data-align="left" data-sortable="true">Style</th>
						<th data-field="component" data-align="left" data-sortable="true">KT</th>
						<th data-field="color" data-align="left" data-sortable="true">Color</th>
						<th data-field="qty" data-sortable="true" data-align="left">Qty</th>
						<th data-field="net" data-sortable="true" data-align="left">Net</th>
						<th data-field="ring" data-sortable="true" data-align="left">Ring</th>
						<th data-field="refOrder" data-sortable="true" data-align="left">Ref Order</th>
						<th data-field="stamp" data-sortable="true" data-align="left">Stamp</th>						
						<th data-field="remarks" data-sortable="true" data-align="left">Remarks</th>
						<th data-field="pO" data-sortable="true" data-align="left">PO</th>
						<th data-field="sK" data-sortable="true" data-align="left">SK</th>
						<th data-field="shape" data-sortable="true" data-align="left">Shape</th>
						<th data-field="quality" data-sortable="true" data-align="left">Quality</th>
						<th data-field="carat" data-sortable="true" data-align="left">Carat</th>
						<th data-field="rate" data-sortable="true" data-align="left">Rate</th>
						<th data-field="diaAmnt" data-sortable="true" data-align="left">DiaAmount</th>
						<th data-field="barcode" data-sortable="true" data-align="left">Barcode</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	       <div class="modal-footer">
	       
	        <a class="btn btn-default" type="button"
				href="#" onclick="javascript:getOrderDtExcelFile()"><span
				class="glyphicon glyphicon-download"></span>&nbsp;Download Excel Format
			</a>
	            
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>     	      
	    </div>
	  </div>
	</div>
	
	
	
	
		<!--------- Design Mt modal --------->

	<div class="modal fade" id="designMtFormat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Design Detail</h4>
	      </div>
	      
	      <div class="modal-body">	       
			<div id="designMtTableDivId">
				<table  id="designMtTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="60" >
					<thead>
					  <tr class="btn-primary">
						 <th data-field="srNo" data-align="left" data-sortable="true">SR. NO.</th>
						<th data-field="group" data-align="left" data-sortable="true">Group</th>
						<th data-field="createDt" data-align="left" data-sortable="true">Create Date</th>
						<th data-field="designNo" data-sortable="true" data-align="left">Design No.</th>
						<th data-field="refNo" data-sortable="true" data-align="left">Refrence No.</th>
						<th data-field="styleNo" data-sortable="true" data-align="left">Style No.</th>
						<th data-field="version" data-sortable="true" data-align="left">Version</th>
						<th data-field="designer" data-sortable="true" data-align="left">Designer</th>						
						<th data-field="category" data-sortable="true" data-align="left">Category</th>
						<th data-field="subCategory" data-sortable="true" data-align="left">Sub Category</th>
						<th data-field="mareket" data-sortable="true" data-align="left">Market</th>
						<th data-field="productType" data-sortable="true" data-align="left">Product Type</th>
						
						<th data-field="collection" data-sortable="true" data-align="left">Collection</th>
						<th data-field="concept" data-sortable="true" data-align="left">Concept</th>
						<th data-field="subConcept" data-sortable="true" data-align="left">Sub Concept</th>
						<th data-field="size" data-sortable="true" data-align="left">Size</th>
						<th data-field="silverWt" data-sortable="true" data-align="left">Silver Wt.</th>
						<th data-field="cadDesigner" data-sortable="true" data-align="left">CAD Designer</th>
						<th data-field="castedPCS" data-sortable="true" data-align="left">Casted PCS</th>
						<th data-field="remarks" data-sortable="true" data-align="left">Remarks</th>
						<th data-field="stoneType" data-sortable="true" data-align="left">Stone Type</th>
						<th data-field="shape" data-sortable="true" data-align="left">Shape</th>
						<th data-field="quality" data-sortable="true" data-align="left">Quality</th>
						<th data-field="sizeMM" data-sortable="true" data-align="left">Size/MM</th>
						<th data-field="sieve" data-sortable="true" data-align="left">Sieve</th>
						<th data-field="sizeGroup" data-sortable="true" data-align="left">Size Group</th>
						<th data-field="stonePcs" data-sortable="true" data-align="left">Stone PCS</th>
						<th data-field="carat" data-sortable="true" data-align="left">Carat</th>
						<th data-field="setting" data-sortable="true" data-align="left">Setting</th>
						<th data-field="setType" data-sortable="true" data-align="left">Set Type</th>
						<th data-field="venodr" data-sortable="true" data-align="left">Venodr</th>
						<th data-field="venodrStyle" data-sortable="true" data-align="left">Venodr Style</th>
						
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	       <div class="modal-footer">
	       
	        <a class="btn btn-default" type="button"
				href="#" onclick="javascript:getDesignMtExcelFile()"><span
				class="glyphicon glyphicon-download"></span>&nbsp;Download Excel Format
			</a>
	            
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>     	      
	    </div>
	  </div>
	</div>
	
	
	
	
	
	

	
	<script type="text/javascript">
	
	
		
		var $labTable = $('#labourFormatTbl');
	    $(function () {
	        $('#labourFormat').on('shown.bs.modal', function () {
	            $labTable.bootstrapTable('resetView');
	        });
	    }); 
	    
	    
	    var $findRateTable = $('#findingRateTbl');
	    $(function () {
	        $('#findingRateFormat').on('shown.bs.modal', function () {
	            $findRateTable.bootstrapTable('resetView');
	        });
	    }); 
	    
	    
	    var $findStoneRateTable = $('#stoneRateTbl');
	    $(function () {
	        $('#stoneRateFormat').on('shown.bs.modal', function () {
	            $findStoneRateTable.bootstrapTable('resetView');
	        });
	    }); 
	    
	    var $findSettingChargeTable = $('#settingChargeTbl');
	    $(function () {
	        $('#settingChargeFormat').on('shown.bs.modal', function () {
	            $findSettingChargeTable.bootstrapTable('resetView');
	        });
	    }); 
		
	    var $quotDtTable = $('#quotDtTbl');
	    $(function () {
	        $('#quotDtFormat').on('shown.bs.modal', function () {
	            $quotDtTable.bootstrapTable('resetView');
	        });
	    }); 
	    
	    var $orderDtTable = $('#orderDtTbl');
	    $(function () {
	        $('#orderDtFormat').on('shown.bs.modal', function () {
	            $orderDtTable.bootstrapTable('resetView');
	        });
	    }); 
	
	    var $designMtTable = $('#designMtTbl');
	    $(function () {
	        $('#designMtFormat').on('shown.bs.modal', function () {
	            $orderDtTable.bootstrapTable('resetView');
	        });
	    }); 
	
	
	
	
	
	
	</script>