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

	<div class="modal fade" id="myClientModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"onclick="javascript:popClientSave()"><span aria-hidden="true">&times;</span></button> 
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
	      	<a href="javascript:popClient(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
	        <button type="button" class="btn btn-primary" onclick="javascript:popClientSave()">Close</button>
	      </div>
	      
	    </div>
	  </div>
	</div>


	<!--------------------------------------------------- OrderType modal ------------------------------------------------------------------------------->

	<div class="modal fade" id="myOrderTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popOrderTypeSave()"><span aria-hidden="true">&times;</span></button> 
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
	      	<a href="javascript:popOrderType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	       <!--  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
	        <button type="button" class="btn btn-primary" onclick="javascript:popOrderTypeSave()">Close</button>
	      </div>
	      
	    </div>
	  </div>
	</div>


	<!--------------------------------------------------- Order modal --------------------------------------------------------------------------->

	<div class="modal fade" id="myOrderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"  onclick="javascript:popOrderSave()"><span aria-hidden="true">&times;</span></button> 
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
	      	<a href="javascript:popOrder(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
	        <button type="button" class="btn btn-primary" onclick="javascript:popOrderSave()">Close</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	<!--------------------------------------------------- Department modal --------------------------------------------------------------------->

	<div class="modal fade" id="myDepartmentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"  onclick="javascript:popDepartmentSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Department</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchDepartment" class="search form-control" placeholder="Department Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="departmentTableDivId">
				<table  id="departmentIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">DEPARTMENT</th>
						<input type="checkbox" id="balId" title="Balance" /> <strong>Balance </strong>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popDepartment(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
	        <button type="button" class="btn btn-primary" onclick="javascript:popDepartmentSave()">Close<!-- Save Changes --></button>
	      </div>	      
	    </div>
	  </div>
	</div>

<!--------------------------------------------------- Location modal --------------------------------------------------------------------->

<div class="modal fade" id="myLocationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"   >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popLocationSave()"><span aria-hidden="true">&times;</span></button> 
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
	       
			<div id="locationTableDivId">
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
	      	<a href="javascript:popLocation(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popLocationSave()">Close</button>
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


<!--------------------------------------------------- Metal modal --------------------------------------------------------------------->

<div class="modal fade" id="myMetalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"   >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popMetalSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Metal</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchMetal" class="search form-control" placeholder="Metal Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="metalTableDivId">
				<table  id="metalIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Metal</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popMetal(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popMetalSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>


<!--------------------------------------------------- Design modal --------------------------------------------------------------------->


<div class="modal fade" id="myDesignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false"   >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popDesignSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Style</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchStyle" class="search form-control" placeholder="Style Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="designTableDivId">
				<table  id="designIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="srNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Sr No</th>
						<th data-field="designNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Style</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popDesign(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popDesignSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	

<!--------------------------------------------------- Component modal --------------------------------------------------------------------->


<div class="modal fade" id="myComponentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popComponentSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Component</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchComponent" class="search form-control" placeholder="Component Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="componentTableDivId">
				<table  id="componentIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">COMPONENT</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popComponent(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popComponentSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>

<!--------------------------------------------------- Melting modal --------------------------------------------------------------------->


<div class="modal fade" id="myMeltingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popMeltingSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Melting</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchMelting" class="search form-control" placeholder="Melting Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="meltingTableDivId">
				<table  id="meltingIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Melting Invoice</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popMelting(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popMeltingSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>

<!--------------------------------------------------- Purity modal --------------------------------------------------------------------->


<div class="modal fade" id="myPurityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popPuritySave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Purity</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchPurity" class="search form-control" placeholder="Purity Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="purityTableDivId">
				<table  id="purityIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Purity</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popPurity(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popPuritySave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>


<!--------------------------------------------------- Color modal --------------------------------------------------------------------->

<div class="modal fade" id="myColorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popColorSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Color</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchColor" class="search form-control" placeholder="Color Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="colorTableDivId">
				<table  id="colorIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Color</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popColor(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popColorSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>



<!--------------------------------------------------- Category modal --------------------------------------------------------------------->

<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popCategorySave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Category</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchCategory" class="search form-control" placeholder="Category Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="categoryTableDivId">
				<table  id="categoryIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Category</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popCategory(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popCategorySave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>




<!--------------------------------------------------- SubCategory modal --------------------------------------------------------------------->


<div class="modal fade" id="mySubCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popSubCategorySave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Sub-Category</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchSubCategory" class="search form-control" placeholder="SubCategory Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="subCategoryTableDivId">
				<table  id="subCategoryIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="categName" data-align="left" data-sortable="true" style="font-weight: bolder;">Category</th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Sub Category</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popSubCategory(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popSubCategorySave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>


<!--------------------------------------------------- Version modal --------------------------------------------------------------------->


<div class="modal fade" id="myVersionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popVersionSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Version</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchSubCategory" class="search form-control" placeholder="Version Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="versionTableDivId">
				<table  id="versionIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="version" data-align="left" data-sortable="true" style="font-weight: bolder;">Version</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popVersion(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popVersionSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>

<!--------------------------------------------------- Concept modal --------------------------------------------------------------------->

<div class="modal fade" id="myConceptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popConceptSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Concept</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchConcept" class="search form-control" placeholder="Concept Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="conceptTableDivId">
				<table  id="conceptIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Concept</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popConcept(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popConceptSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>





<!--------------------------------------------------- Sub Concept modal --------------------------------------------------------------------->

<div class="modal fade" id="mySubConceptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popSubConceptSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">SubConcept</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchSubConcept" class="search form-control" placeholder="SubConcept Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="subConceptTableDivId">
				<table  id="subConceptIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="conceptName" data-align="left" data-sortable="true" style="font-weight: bolder;">Concept</th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Sub Concept</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popSubConcept(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popSubConceptSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>


<!--------------------------------------------------- Stone Type modal --------------------------------------------------------------------->

<div class="modal fade" id="myStoneTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popStoneTypeSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">StoneType</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchStoneType" class="search form-control" placeholder="StoneType Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="stoneTypeTableDivId">
				<table  id="stoneTypeIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">StoneType</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popStoneType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popStoneTypeSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>






<!--------------------------------------------------- Shape modal --------------------------------------------------------------------->


<div class="modal fade" id="myShapeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popShapeSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Shape</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchShape" class="search form-control" placeholder="Shape Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="shapeTableDivId">
				<table  id="shapeIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Shape</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popShape(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popShapeSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>



<!--------------------------------------------------- Quality modal --------------------------------------------------------------------->


<div class="modal fade" id="myQualityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popQualitySave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Quality</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchQuality" class="search form-control" placeholder="Quality Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="qualityTableDivId">
				<table  id="qualityIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Quality</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popQuality(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popQualitySave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>


<!--------------------------------------------------- Setting Type Modal --------------------------------------------------------------------->


<div class="modal fade" id="mySettingTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popSettingTypeSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Setting Type</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchSettingType" class="search form-control" placeholder="Setting Type Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="settingTypeTableDivId">
				<table  id="settingTypeIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Setting Type</th>
					 </tr>				 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popSettingType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popSettingTypeSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>



<!--------------------------------------------------- Metal-Inward modal --------------------------------------------------------------------->

<div class="modal fade" id="myMetalInwardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popMtlInwardInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Metal Inward</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchMtlInwardInvoice" class="search form-control" placeholder="Metal Inward Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="mtlInwardInvoiceTableDivId">
				<table  id="mtlInwardInvoiceIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<!-- <table id="mtlInwardInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250"> -->
					
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popMtlInwardInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popMtlInwardInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>



<!--------------------------------------------------- Metal-Outward modal --------------------------------------------------------------------->

<div class="modal fade" id="myMetalOutwardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popMtlOutwardInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Metal Outward</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchMtlOutwardInvoice" class="search form-control" placeholder=" Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="mtlOutwardInvoiceTableDivId">
				<table  id="mtlOutwardInvoiceIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<!-- <table id="mtlInwardInvoiceIdTbl" data-toggle="table"
								data-click-to-select="true" data-side-pagination="server"
								data-height="250"> -->
					
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popMtlOutwardInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popMtlOutwardInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>


<!--------------------------------------------------- Component-Inward modal --------------------------------------------------------------------->

<div class="modal fade" id="myCompInwardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popCompInwardInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Comp Inward</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchCompInwardInvoice" class="search form-control" placeholder="Comp Inward Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="compInwardInvoiceTableDivId">
				<table  id="compInwardInvoiceIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popCompInwardInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popCompInwardInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>


<!--------------------------------------------------- Component-Outward modal --------------------------------------------------------------------->

<div class="modal fade" id="myCompOutwardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popCompOutwardInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Comp Outward</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchCompOutwardInvoice" class="search form-control" placeholder="Comp Outward Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="compOutwardInvoiceTableDivId">
				<table  id="compOutwardInvoiceIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popCompOutwardInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popCompOutwardInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	
	
	<!--------------------------------------------------- 	Export Invoice modal --------------------------------------------------------------------->

<div class="modal fade" id="myExportInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popExportInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Export Invoice</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<!-- <div class="col-sm-4">		       	
		       		<input type="search" id="searchExportInvoice" class="search form-control" placeholder="Export Invoice Search " />		       		
		       	</div> -->
	       </div>		       
	       
			<div id="exportInvoiceTableDivId">
				<!-- <table  id="exportInvoiceIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead> -->	<table id="exportInvoiceIdTbl" data-toggle="table" data-side-pagination="server"	
								data-pagination="true" data-search="true"  data-striped="true"data-checkbox-header="true" data-height="350"
								data-click-to-select="true">
								<thead><!-- <label>Set No.</label> <input type="number" id="setNoId" value="0"> -->
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Export Invoice</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popExportInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popExportInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
		


	<!--------------------------------------------------- 	Export Invoice All modal --------------------------------------------------------------------->

	
<div class="modal fade" id="myExportInvoiceAllModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popExportInvoiceAllSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Export Invoice</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<!-- <div class="col-sm-4">		       	
		       		<input type="search" id="searchExportInvoice" class="search form-control" placeholder="Export Invoice Search " />		       		
		       	</div> -->
	       </div>		       
	       
			<div id="exportInvoiceAllTableDivId">
				<table id="exportInvoiceAllIdTbl" data-toggle="table" data-side-pagination="server"	data-search="true"  data-height="350"
								data-click-to-select="true">
				 
				
						<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Export Invoice All</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popExportInvoiceAll(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popExportInvoiceAllSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
		

	
	
	<!--------------------------------------------------- 	Export Invoice List / Export Style modal --------------------------------------------------------------------->

	
<div class="modal fade" id="myExportInvoiceListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popExportInvoiceListSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Export Style</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<!-- <div class="col-sm-4">		       	
		       		<input type="search" id="searchExportInvoice" class="search form-control" placeholder="Export Invoice Search " />		       		
		       	</div> -->
	       </div>		       
	       
			<div id="exportInvoiceListTableDivId">
			<!-- 	<table id="exportInvoiceListTbl" data-toggle="table" data-side-pagination="server"
				data-search="true"  data-height="350" data-click-to-select="true"> -->			
				<table id="exportInvoiceListTbl" data-toggle="table" data-search="true" data-side-pagination="server"
				  data-maintain-selected="true"	data-pagination="true" data-height="350" data-response-handler="responseHandler">
						<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No.</th>
							<th data-field="itemNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Barcode</th>
							<th data-field="ordRefNo" data-align="left"	data-sortable="true" style="font-weight: bolder;">RefNo.</th>
							<th data-field="style" data-align="left" data-sortable="true" style="font-weight: bolder;">Style No.</th>
							<th data-field="purity" data-align="left" data-sortable="true" style="font-weight: bolder;">Purity</th>
							<th data-field="color" data-align="left" data-sortable="true" style="font-weight: bolder;">Color</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popExportInvoiceList(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popExportInvoiceListSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
		



	
	<!--------------------------------------------------- 	Export Quality modal --------------------------------------------------------------------->

	
<div class="modal fade" id="myExportQualityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popExportQualitySave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Export Quality </h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchExportQuality" class="search form-control" placeholder="Export Quality Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="exportQualityTableDivId">
		 	<!-- <table id="exportQualityIdTbl" data-toggle="table" data-click-to-select="true" data-side-pagination="server"
						data-pagination="false" data-height="250"> -->
						<table id="exportQualityIdTbl" data-toggle="table"
										data-click-to-select="true" data-side-pagination="server"
										data-pagination="false" data-height="350">
									
						<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Quality</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popExportQuality(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popExportQualitySave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
		
	
	<!--------------------------------------------------- 	Export Size Group modal --------------------------------------------------------------------->

	
<div class="modal fade" id="myExportSizeGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popExportSizegroupSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Export Size Group </h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchExportSizegroup" class="search form-control" placeholder="SizeGoup Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="exportSizegroupTableDivId">
						<table id="exportSizegroupIdTbl" data-toggle="table" data-click-to-select="true" data-side-pagination="server"
						data-pagination="false" data-height="350">
									
						<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Size Group</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popExportSizegroup(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popExportSizegroupSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
		
	
	<!--------------------------------------------------- Quotation Invoice modal --------------------------------------------------------------------->

	<div class="modal fade" id="myQuotationInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popQuotationInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Quotation Invoice</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchQuotationInvoice" class="search form-control" placeholder="Quotation Invoice Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="quotationInvoiceTableDivId">
				<!-- <table  id="quotationInvoiceIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" > -->
					<table id="quotationInvoiceIdTbl" data-toggle="table" 
								data-side-pagination="server" data-pagination="true"  data-checkbox-header="false" 
								data-height="450"  data-single-select="true"  data-click-to-select="true">
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Quotation Invoice</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popQuotationInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popQuotationInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	
	
	
<!--------------------------------------------------- Quotation Details modal --------------------------------------------------------------------->

	<div class="modal fade" id="myQuotationInvoiceListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popQuotInvoiceListSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Quotation Details</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<!-- <div class="col-sm-4">		       	
		       		<input type="search" id="searchQuotationInvoice" class="search form-control" placeholder="Quotation Invoice Search " />		       		
		       	</div> -->
	       </div>		       
	       
			<div id="quotInvoiceListTableDivId">
					<!-- <table id="quotInvoiceListTbl" data-toggle="table" 
								data-side-pagination="server" data-pagination="true"  data-checkbox-header="false" 
								data-height="450"  data-single-select="true"  data-click-to-select="true"> -->
								 <table id="quotInvoiceListTbl" data-toggle="table"
								data-search="true" 
								data-side-pagination="server" data-maintain-selected="true"
								data-pagination="true" data-height="350"
								data-response-handler="responseHandler">
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
										<th data-field="barcode" data-align="left" data-sortable="true">Barcode</th>
										<th data-field="style" data-align="left" data-sortable="true">Style	No.</th>
										<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
										<th data-field="color" data-align="left" data-sortable="true">Color</th>
										<th data-field="pcs">Pcs</th>
										<th data-field="grossWt">Gross Wt.</th>
										<th data-field="netWt">Net Wt.</th>
										<th data-field="perPcFinalPrice">Per Pc Final Price</th>
										<th data-field="finalPrice">Final Price</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popQuotInvoiceList(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popQuotInvoiceListSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>	
		
		
		
	<!--------------------------------------------------- Bag modal --------------------------------------------------------------------->
	
	<div class="modal fade" id="myBagModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popBagSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Bag</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchBags" class="search form-control" placeholder="Bag Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="bagsTableDivId">
				<table  id="bagsIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Bag Name</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popBag(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popBagSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>

	<!--------------------------------------------------- Employee modal --------------------------------------------------------------------->

	<div class="modal fade" id="myEmployeeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popEmployeeSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Employee</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchEmployee" class="search form-control" placeholder="Employee Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="employeeTableDivId">
				<table  id="employeeIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Employee Name</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popEmployee(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popEmployeeSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
		
	
	<!--------------------------------------------------- Export Job Invoice modal --------------------------------------------------------------------->
	
		<div class="modal fade" id="myExportJobInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popExportJobInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">ExportJobInvoice</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchExportJobInvoice" class="search form-control" placeholder="Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="exportJobInvoiceTableDivId">
				<table  id="exportJobInvoiceIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" data-striped="true" data-height="350" >
					<thead>
					  <tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Export Job Invoice</th>
					</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popExportJobInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popExportJobInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	
		
		
	<!--------------------------------------------------- Inward Type modal --------------------------------------------------------------------->
	
		<div class="modal fade" id="myInwardTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popInwardTypeSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Inward Type</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchInwardType" class="search form-control" placeholder="Search InwardType " />		       		
		       	</div>
	       </div>		       
	       
			<div id="inwardTypeTableDivId">
				<table id="inwardTypeIdTbl" data-toggle="table"	data-click-to-select="true" data-side-pagination="server"
				data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Inward Type</th>
						</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popInwardType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popInwardTypeSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	
	<!--------------------------------------------------- Stone Invoice modal --------------------------------------------------------------------->
	
	<div class="modal fade" id="myStoneInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popStoneInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Stone Invoice</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchStoneInvoice" class="search form-control" placeholder=" StoneInvoice Search" />		       		
		       	</div>
	       </div>		       
	       
			<div id="stoneInvoiceTableDivId">
				 <table id="stoneInvoiceIdTbl" data-toggle="table"	data-click-to-select="true" data-side-pagination="server"
								data-height="350">
					<thead>
						<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No</th>
						</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popStoneInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popStoneInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	
	
		
<!---------------------------------------------------  Stone Purchase Invoice Modal --------------------------------------------------------------------->	


	<div class="modal fade" id="myStonePurcInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popStonePurcInvoiceSave()"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Stone Purchase Invoice</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchStonePurcInvoice" class="search form-control" placeholder="StonePurchase Search " />		       		
		       	</div>
	       </div>		       
	       
			<div id="stonePurcInvoiceTableDivId">
				  <table id="stonePurcInvoiceIdTbl" data-toggle="table"	data-click-to-select="true" data-side-pagination="server"
								 data-height="350">
					<thead>
						<tr>
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Invoice No</th>
						</tr>		 
					</thead>
				</table> 
			</div>	       
	      </div>	      
	      <div class="modal-footer">
	      	<a href="javascript:popStonePurcInvoice(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popStonePurcInvoiceSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>
	


<!--------------------------------------------------- Division Modal --------------------------------------------------------------------->


<div class="modal fade" id="myDivisionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popDivisionSave()"><span aria-hidden="true">&times;</span></button> 
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
	      	<a href="javascript:popDivision(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popDivisionSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>



<!--------------------------------------------------- Region Modal --------------------------------------------------------------------->


<div class="modal fade" id="myRegionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popRegionSave()"><span aria-hidden="true">&times;</span></button> 
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
	      	<a href="javascript:popRegion(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popRegionSave()">Close</button>
	      </div>	      
	    </div>
	  </div>
	</div>

	
	
<!--------------------------------------------------- customerType Modal --------------------------------------------------------------------->

<div class="modal fade" id="myCustomerTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popCustomerTypeSave()"><span aria-hidden="true">&times;</span></button> 
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
	      	<a href="javascript:popCustomerType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-primary" onclick="javascript:popCustomerTypeSave()">Close</button>
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
        $('#myClientModal').on('shown.bs.modal', function () {
            $clientTable.bootstrapTable('resetView');
        });
    }); 
    
	var clientStatus = "false";
	function popClient(val) {
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
			$('#clientTextBox').val('');
		}
		
	}
	
	
	 function popClientSave(){
		
		$("#myClientModal").modal("hide");
		var	data = $('#clientIdTbl').bootstrapTable('getSelections');
		var	clientNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = clientNm.toString().replace(/,/g, ", ");
		$('#clientTextBox').val(tempRes);
		
	} 
	 
	/*  ========================================== Order Type Fn ===================================================================== */
		
	var $orderTypeTable = $('#orderTypeIdTbl');
    $(function () {
        $('#myOrderTypeModal').on('shown.bs.modal', function () {
            $orderTypeTable.bootstrapTable('resetView');
        });
    }); 
	
	
  
	var orderTypeStatus = "false";
	function popOrderType(val) {
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
			$('#orderTypeTextBox').val('');
		}
		
	}
	
	 function popOrderTypeSave(){
			
			$("#myOrderTypeModal").modal("hide");
			var	data = $('#orderTypeIdTbl').bootstrapTable('getSelections');
			var	orderTypeNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = orderTypeNm.toString().replace(/,/g, ", ");
			$('#orderTypeTextBox').val(tempRes);
			
		}
	
		/*  ========================================== Order Fn ===================================================================== */
		
		var $orderTable = $('#orderIdTbl');
	    $(function () {
	        $('#myOrderModal').on('shown.bs.modal', function () {
	            $orderTable.bootstrapTable('resetView');
	           
	        });
	    }); 
		
		
		var orderStatus = "false";
		function popOrder(val) {
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
				$('#orderTextBox').val('');
			}
			
			//if(clientData != "" && orderTypeData != ""){
				/* $("#orderIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/order/report/listing.html?partyId="+clientIds+"&orderTypeId="
						+orderTypeIds+"&ordFlg="+orderFlg+"&fromDate="+$('#fromOrdDate').val()+"&toDate="+$('#toOrdDate').val()+"&divisionIds="+divisionIds,
				}); */
			
			}
		
		 function popOrderSave(){
				
				$("#myOrderModal").modal("hide");
				var	data = $('#orderIdTbl').bootstrapTable('getSelections');
				var	orderNm = $.map(data, function(item) {
						return item.invNo;
					});
				
				var tempRes = orderNm.toString().replace(/,/g, ", ");
				$('#orderTextBox').val(tempRes);
				
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
	        $('#myLocationModal').on('shown.bs.modal', function () {
	            $locationTable.bootstrapTable('resetView');
	        });
	    });
	    
	    
/* 	 	function popLocation() {
		$("#locationIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/department/listing.html?opt=3"
		});
		//$('#locationBtn').css('display','none');
		$('#locationTableDivId').css('display', 'block');
		$('#searchLocation').val('');
	} */
		
	var locationStatus = "false";
		function popLocation(val) {
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
				$('#locationTextBox').val('');
			}
			
		}
		function popLocationSave(){
			
			$("#myLocationModal").modal("hide");
			var	data = $('#locationIdTbl').bootstrapTable('getSelections');
			var	locationNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = locationNm.toString().replace(/,/g, ", ");
			$('#locationTextBox').val(tempRes);
			
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

	 
	 /*================================================== Metal Fn ===================================================================== */
	 
		var $metalTable = $('#metalIdTbl');
	    $(function () {
	        $('#myMetalModal').on('shown.bs.modal', function () {
	            $metalTable.bootstrapTable('resetView');
	        });
	    }); 
	    
	    
/* 	 	function popMetal() {

		$("#metalIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/metal/listing.html?opt=3"
		});

		//$('#metalBtn').css('display','none');
		$('#metalTableDivId').css('display', 'block');
		$('#searchMetal').val('');
	}
	  */
	 	var metalStatus = "false";
		function popMetal(val) {
			if(val === 0){
				if(metalStatus === 'false'){
					$("#metalIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/metal/listing.html?opt=3"
					});
					metalStatus = true;
				}
			}else{
				$("#metalIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/metal/listing.html?opt=3"
				});
				$('#metalTextBox').val('');
			}
			
		}
		
		function popMetalSave(){
			$("#myMetalModal").modal("hide");
			var	data = $('#metalIdTbl').bootstrapTable('getSelections');
			var	metalNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = metalNm.toString().replace(/,/g, ", ");
			$('#metalTextBox').val(tempRes);
			
		}
	
		 /*================================================== Design Fn ===================================================================== */
	
		 	 
		var $designTable = $('#designIdTbl');
	    $(function () {
	        $('#myDesignModal').on('shown.bs.modal', function () {
	            $designTable.bootstrapTable('resetView');
	        });
	    }); 
	    
	    var designStatus = "false";
		function popDesign(val) {
			
			var orderData = $('#orderIdTbl').bootstrapTable('getSelections');
			var orderIds = $.map(orderData, function(item) {
				return item.id;
			});
			
			if(val === 0){
				if(designStatus === 'false'){
					$("#designIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/orderWiseStyle/report/listing.html?orderId="
							+ orderIds,
					});
					designStatus = true;
				}
			}else{
				$("#designIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/orderWiseStyle/report/listing.html?orderId="
						+ orderIds,
				});
				$('#designTextBox').val('');
			}
			
		}
	    
/* 	    function popDesign() {
		var orderData = $('#orderIdTbl').bootstrapTable('getSelections');
		var orderIds = $.map(orderData, function(item) {
			return item.id;
		});

		$('#designIdTbl')
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/orderWiseStyle/report/listing.html?orderId="
									+ orderIds,
						});

		$('#designTableDivId').css('display', 'block');
		$('#searchStyle').val('');
	} */
	
	function popDesignSave(){
		$("#myDesignModal").modal("hide");
		var	data = $('#designIdTbl').bootstrapTable('getSelections');
		var	designNm = $.map(data, function(item) {
				return item.designNo;
			});
		
		var tempRes = designNm.toString().replace(/,/g, ", ");
		$('#designTextBox').val(tempRes);
		
	}
	 /*================================================== Component Fn ===================================================================== */
	
		var $componentTable = $('#componentIdTbl');
	    $(function () {
	        $('#myComponentModal').on('shown.bs.modal', function () {
	            $componentTable.bootstrapTable('resetView');
	        });
	    });  
	 
	 
/* 	 function popComponent() {
		$('#componentIdTbl').bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/component/listing.html?opt=2"
		});

		$('#componentTableDivId').css('display', 'block');
		$('#searchComponent').val('');
	}	 */ 
	 
    var componentStatus = "false";
	function popComponent(val) {
		
		if(val === 0){
			if(componentStatus === 'false'){
				$("#componentIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/component/listing.html?opt=2"
				});
				componentStatus = true;
			}
		}else{
			$("#componentIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/component/listing.html?opt=2"
			});
			$('#componentTextBox').val('');
		}
		
	}
	 
	function popComponentSave(){
		$("#myComponentModal").modal("hide");
		var	data = $('#componentIdTbl').bootstrapTable('getSelections');
		var	componentNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = componentNm.toString().replace(/,/g, ", ");
		$('#componentTextBox').val(tempRes);
		
	} 

	 
	 
	 
	 /*================================================== Melting Fn ===================================================================== */
	 
	 var $meltingTable = $('#meltingIdTbl');
	    $(function () {
	        $('#myMeltingModal').on('shown.bs.modal', function () {
	            $meltingTable.bootstrapTable('resetView');
	        });
	    });
		 
	/* 	function popMelting() {

			$("#meltingIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/meltingMt/listing.html?opt=0",
							});

			$('#meltingTableDivId').css('display', 'block');
			$('#searchMelting').val('');
		} */
		
	    var meltingStatus = "false";
		function popMelting(val) {
			if(val === 0){
				if(meltingStatus === 'false'){
					$("#meltingIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/transactions/meltingMt/listing.html?opt=0",
					});
					meltingStatus = true;
				}
			}else{
				$("#meltingIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/transactions/meltingMt/listing.html?opt=0",
				});
				$('#meltingTextBox').val('');
			}
			
		}
		
		function popMeltingSave(){
			$("#myMeltingModal").modal("hide");
			var	data = $('#meltingIdTbl').bootstrapTable('getSelections');
			var	meltingNm = $.map(data, function(item) {
					return item.invNo;
				});
			
			var tempRes = meltingNm.toString().replace(/,/g, ", ");
			$('#meltingTextBox').val(tempRes);
			
		} 
	 
		 /*================================================== Purity Fn ===================================================================== */
		
		var $purityTable = $('#purityIdTbl');
	    $(function () {
	        $('#myPurityModal').on('shown.bs.modal', function () {
	            $purityTable.bootstrapTable('resetView');
	        });
	    });
		
	/*  function popPurity() {
		$("#purityIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/purity/listing.html?opt=3",
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#purityTableDivId').css('display', 'block');
		$('#searchPurity').val('');
	} */
	
    var purityStatus = "false";
	function popPurity(val) {
		if(val === 0){
			if(purityStatus === 'false'){
				$("#purityIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/purity/listing.html?opt=3",
				});
				purityStatus = true;
			}
		}else{
			$("#purityIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/purity/listing.html?opt=3",
			});
			$('#purityTextBox').val('');
		}
		
	}
	
	function popPuritySave(){
		$("#myPurityModal").modal("hide");
		var	data = $('#purityIdTbl').bootstrapTable('getSelections');
		var	purityNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = purityNm.toString().replace(/,/g, ", ");
		$('#purityTextBox').val(tempRes);
		
	} 
		 
		 
	/*================================================== Color Fn ===================================================================== */	
	
		var $colorTable = $('#colorIdTbl');
	    $(function () {
	        $('#myColorModal').on('shown.bs.modal', function () {
	            $colorTable.bootstrapTable('resetView');
	        });
	    });
/* 		function popColor() {

			$("#colorIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/color/listing.html?opt=2",
			});

			//$('#stoneTypeBtn').css('display','none');
			$('#colorTableDivId').css('display', 'block');
			$('#searchColor').val('');
		} */

	    var colorStatus = "false";
		function popColor(val) {
			if(val === 0){
				if(colorStatus === 'false'){
					$("#colorIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/color/listing.html?opt=2",
					});
					colorStatus = true;
				}
			}else{
				$("#colorIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/color/listing.html?opt=2",
				});
				$('#colorTextBox').val('');
			}
			
		}
		
		function popColorSave(){
			$("#myColorModal").modal("hide");
			var	data = $('#colorIdTbl').bootstrapTable('getSelections');
			var	colorNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = colorNm.toString().replace(/,/g, ", ");
			$('#colorTextBox').val(tempRes);
			
		} 

		/*================================================== Category Fn ===================================================================== */	
		var $categoryTable = $('#categoryIdTbl');
	    $(function () {
	        $('#myCategoryModal').on('shown.bs.modal', function () {
	            $categoryTable.bootstrapTable('resetView');
	        });
	    });		
		
/* 		function popCategory() {

			$("#categoryIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
			});

			//$('#stoneTypeBtn').css('display','none');
			$('#categoryTableDivId').css('display', 'block');
			$('#searchCategory').val('');
		}	 */
   
	    var categoryStatus = "false";
		function popCategory(val) {
			if(val === 0){
				if(categoryStatus === 'false'){
					$("#categoryIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
					});
					categoryStatus = true;
				}
			}else{
				$("#categoryIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
				});
				$('#categoryTextBox').val('');
			}
			
		}
		
		function popCategorySave(){
			$("#myCategoryModal").modal("hide");
			var	data = $('#categoryIdTbl').bootstrapTable('getSelections');
			var	categoryNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = categoryNm.toString().replace(/,/g, ", ");
			$('#categoryTextBox').val(tempRes);
			
		} 

		/*================================================== Sub-Category Fn ===================================================================== */	
		
		
	var $subCategoryTable = $('#subCategoryIdTbl');
    $(function () {
        $('#mySubCategoryModal').on('shown.bs.modal', function () {
            $subCategoryTable.bootstrapTable('resetView');
        });
    });
		/* function popSubCategory() {

			$("#subCategoryIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
							});

			//$('#stoneTypeBtn').css('display','none');
			$('#subCategoryTableDivId').css('display', 'block');
			$('#searchSubCategory').val('');
		} */
		
		
		var subCategoryStatus = "false";
		function popSubCategory(val) {
			if(val === 0){
				if(subCategoryStatus === 'false'){
					$("#subCategoryIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
					});
					subCategoryStatus = true;
				}
			}else{
				$("#subCategoryIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
				});
				$('#subCategoryTextBox').val('');
			}
		}
		
		function popSubCategorySave(){	
			$("#mySubCategoryModal").modal("hide");
			var	data = $('#subCategoryIdTbl').bootstrapTable('getSelections');
			var	subCategoryNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = subCategoryNm.toString().replace(/,/g, ", ");
			$('#subCategoryTextBox').val(tempRes);
		}
		
		
		/*================================================== Version Fn ===================================================================== */		
				
	var $versionTable = $('#versionIdTbl');
    $(function () {
        $('#myVersionModal').on('shown.bs.modal', function () {
            $versionTable.bootstrapTable('resetView');
        });
    });	
		
	var versionStatus = "false";
	function popVersion(val) {
		if(val === 0){
			if(versionStatus === 'false'){
				$("#versionIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/version/reportList.html",
				});
				versionStatus = true;
			}
		}else{
			$("#versionIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/version/reportList.html",
			});
			$('#versionTextBox').val('');
		}
	}
	
	/* function popVersion() {

		$("#versionIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/version/reportList.html",
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#versionTableDivId').css('display', 'block');
		$('#searchVersion').val('');
	} */
		
	function popVersionSave(){	
		$("#myVersionModal").modal("hide");
		var	data = $('#versionIdTbl').bootstrapTable('getSelections');
		var	versionNm = $.map(data, function(item) {
				return item.version;
			});		
		var tempRes = versionNm.toString().replace(/,/g, ", ");
		$('#versionTextBox').val(tempRes);
	}
		
		
	/*================================================== Concept Fn ===================================================================== */			

					
	var $conceptTable = $('#conceptIdTbl');
    $(function () {
        $('#myConceptModal').on('shown.bs.modal', function () {
            $conceptTable.bootstrapTable('resetView');
        });
    });	
 			
	var conceptStatus = "false";
	function popConcept(val) {
		if(val === 0){
			if(conceptStatus === 'false'){
				$("#conceptIdTbl").bootstrapTable('refresh', {
					url : '/jewels/manufacturing/masters/concept/listing.html?opt=3',
				});
				conceptStatus = true;
			}
		}else{
			$("#conceptIdTbl").bootstrapTable('refresh', {
				url :'/jewels/manufacturing/masters/concept/listing.html?opt=3',
			});
			$('#conceptTextBox').val('');
		}			
	}
	
	function popConceptSave(){
		
		$("#myConceptModal").modal("hide");
		var	data = $('#conceptIdTbl').bootstrapTable('getSelections');
		var	conceptNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = conceptNm.toString().replace(/,/g, ", ");
		$('#conceptTextBox').val(tempRes);
	}
	
	
	/*================================================== Sub-Concept Fn ===================================================================== */	
	
	var $subConceptTable = $('#subConceptIdTbl');
    $(function () {
        $('#mySubConceptModal').on('shown.bs.modal', function () {
            $subConceptTable.bootstrapTable('resetView');
        });
    });	
       
/* 		function popSubConcept() {
		$("#subConceptIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/subConcept/listing.html?opt=3",
						});
		//$('#stoneTypeBtn').css('display','none');
		$('#subConceptTableDivId').css('display', 'block');
		$('#searchSubConcept').val('');
	}
	 */
		var subConceptStatus = "false";
		function popSubConcept(val) {
			if(val === 0){
				if(subConceptStatus === 'false'){
					$("#subConceptIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/subConcept/listing.html?opt=3",
					});
					subConceptStatus = true;
				}
			}else{
				$("#subConceptIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/subConcept/listing.html?opt=3",
				});
				$('#subConceptTextBox').val('');
			}			
		}
	
		function popSubConceptSave(){
			
			$("#mySubConceptModal").modal("hide");
			var	data = $('#subConceptIdTbl').bootstrapTable('getSelections');
			var	subConceptNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = subConceptNm.toString().replace(/,/g, ", ");
			$('#subConceptTextBox').val(tempRes);
		}
	
	
   /*================================================== StoneType Fn ===================================================================== */
   
   	var $stoneTypeTable = $('#stoneTypeIdTbl');
    $(function () {
        $('#myStoneTypeModal').on('shown.bs.modal', function () {
            $stoneTypeTable.bootstrapTable('resetView');
        });
    });	
   
/*    	function popStoneType() {
		$("#stoneTypeIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/stoneType/listing.html?opt=1"
		});

		//$('#stoneTypeBtn').css('display','none');
		$('#stoneTypeTableDivId').css('display', 'block');
		$('#searchStoneType').val('');
	}
    */
   
	var stoneTypeStatus = "false";
	function popStoneType(val) {
		if(val === 0){
			if(stoneTypeStatus === 'false'){
				$("#stoneTypeIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/stoneType/listing.html?opt=1"
				});
				stoneTypeStatus = true;
			}
		}else{
			$("#stoneTypeIdTbl").bootstrapTable('refresh', {
				url :"/jewels/manufacturing/masters/stoneType/listing.html?opt=1"
			});
			$('#stoneTypeTextBox').val('');
		}			
	}

	function popStoneTypeSave(){
		
		$("#myStoneTypeModal").modal("hide");
		var	data = $('#stoneTypeIdTbl').bootstrapTable('getSelections');
		var	stoneTypeNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = stoneTypeNm.toString().replace(/,/g, ", ");
		$('#stoneTypeTextBox').val(tempRes);
		
		
	}

   
   
	  /*================================================== Shape Fn ===================================================================== */
	  

	var $shapeTable = $('#shapeIdTbl');
    $(function () {
        $('#myShapeModal').on('shown.bs.modal', function () {
            $shapeTable.bootstrapTable('resetView');
        });
    });  
	  
/* 	function popShape() {

		$("#shapeIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/shape/listing.html?opt=3"
		});

		//$('#shapeBtn').css('display','none');
		$('#shapeTableDivId').css('display', 'block');
		$('#searchShape').val('');
	}  */
	  
	var shapeStatus = "false";
	function popShape(val) {
		if(val === 0){
			if(shapeStatus === 'false'){
				$("#shapeIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/shape/listing.html?opt=3"
				});
				shapeStatus = true;
			}
		}else{
			$("#shapeIdTbl").bootstrapTable('refresh', {
				url :"/jewels/manufacturing/masters/shape/listing.html?opt=3"
			});
			$('#shapeTextBox').val('');
		}			
	}

	function popShapeSave(){
		
		$("#myShapeModal").modal("hide");
		var	data = $('#shapeIdTbl').bootstrapTable('getSelections');
		var	shapeNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = shapeNm.toString().replace(/,/g, ", ");
		$('#shapeTextBox').val(tempRes);
	}
  

	  /*================================================== QUALITY Fn ===================================================================== */
	  
	var $qualityTable = $('#qualityIdTbl');
    $(function () {
        $('#myQualityModal').on('shown.bs.modal', function () {
            $qualityTable.bootstrapTable('resetView');
        });
    });  	  
	  
    /* 	function popQuality() {

		var shapeData = $('#shapeIdTbl').bootstrapTable('getSelections');
		var shapeIds = $.map(shapeData, function(item) {
			return item.id;
		});

		$("#qualityIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/quality/report/listing.html?shapeId="
									+ shapeIds
						});

		//$('#qualityBtn').css('display','none');
		$('#qualityTableDivId').css('display', 'block');
		$('#searchQuality').val('');
	} 
	  
 */	  
	var qualityStatus = "false";
	function popQuality(val) {
		
		var shapeData = $('#shapeIdTbl').bootstrapTable('getSelections');
		var shapeIds = $.map(shapeData, function(item) {
			return item.id;
		});
		
		if(val === 0){
			if(qualityStatus === 'false'){
				$("#qualityIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/quality/report/listing.html?shapeId="
						+ shapeIds
				});
				qualityStatus = true;
			}
		}else{
			$("#qualityIdTbl").bootstrapTable('refresh', {
				url :"/jewels/manufacturing/masters/quality/report/listing.html?shapeId="
					+ shapeIds
			});
			$('#qualityTextBox').val('');
		}	
		
		
		$("#qualityIdTbl").bootstrapTable('refresh', {
			url :"/jewels/manufacturing/masters/quality/report/listing.html?shapeId="
				+ shapeIds
		});
	}

	function popQualitySave(){
		
		$("#myQualityModal").modal("hide");
		var	data = $('#qualityIdTbl').bootstrapTable('getSelections');
		var	qualityNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = qualityNm.toString().replace(/,/g, ", ");
		$('#qualityTextBox').val(tempRes);
	}
  
	

	/*==================================================  SettingType Fn ===================================================================== */
   
	
	var $settingTypeTable = $('#settingTypeIdTbl');
	$(function() {
		$('#mySettingTypeModal').on('shown.bs.modal', function() {
			$settingTypeTable.bootstrapTable('resetView');
		});
	});

/* 	function popSettingType() {

		$("#settingTypeIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/settingType/listing.html?opt=3"
						});

		$('#settingTypeTableDivId').css('display', 'block');
		$('#searchSettingType').val('');
	} */
	
	var settingTypeStatus = "false";
	function popSettingType(val) {
		if(val === 0){
			if(settingTypeStatus === 'false'){
				$("#settingTypeIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/settingType/listing.html?opt=3"
				});
				settingTypeStatus = true;
			}
		}else{
			$("#settingTypeIdTbl").bootstrapTable('refresh', {
				url :"/jewels/manufacturing/masters/settingType/listing.html?opt=3"
			});
			$('#settingTypeTextBox').val('');
		}			
	}
	
	
	function popSettingTypeSave(){
		
		$("#mySettingTypeModal").modal("hide");
		var	data = $('#settingTypeIdTbl').bootstrapTable('getSelections');
		var	settingTypeNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = settingTypeNm.toString().replace(/,/g, ", ");
		$('#settingTypeTextBox').val(tempRes);
	}
	

	/*================================================== Division Fn ===================================================================== */
	
	var $divisionTable = $('#divisionIdTbl');
    $(function () {
        $('#myDivisionModal').on('shown.bs.modal', function () {
            $divisionTable.bootstrapTable('resetView');
        });
    }); 
	
	var divisionStatus = "false";
	function popDivision(val) {
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
			$('#divisionTextBox').val('');
		}			
	}


		
		function popDivisionSave(){
				
				$("#myDivisionModal").modal("hide");
				var	data = $('#divisionIdTbl').bootstrapTable('getSelections');
				var	divisionNm = $.map(data, function(item) {
						return item.name;
					});
				
				var tempRes = divisionNm.toString().replace(/,/g, ", ");
				$('#divisionTextBox').val(tempRes);
			}
		
		
		/*================================================== Region Fn ===================================================================== */
		
		var $regionTable = $('#regionIdTbl');
		$(function () {
		    $('#myRegionModal').on('shown.bs.modal', function () {
		        $regionTable.bootstrapTable('resetView');
		    });
		}); 
		
		var regionStatus = "false";
		function popRegion(val) {
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
				$('#regionTextBox').val('');
			}			
		}
		
		
		
		function popRegionSave(){
			
			$("#myRegionModal").modal("hide");
			var	data = $('#regionIdTbl').bootstrapTable('getSelections');
			var	regionNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = regionNm.toString().replace(/,/g, ", ");
			$('#regionTextBox').val(tempRes);
		}


/*================================================== CustomerType Fn ===================================================================== */
		
		var $customerTypeTable = $('#customerTypeIdTbl');
		$(function () {
		    $('#myCustomerTypeModal').on('shown.bs.modal', function () {
		        $customerTypeTable.bootstrapTable('resetView');
		    });
		}); 
		
		var customerTypeStatus = "false";
		function popCustomerType(val) {
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
		
		
		
		function popCustomerTypeSave(){
			
			$("#myCustomerTypeModal").modal("hide");
			var	data = $('#customerTypeIdTbl').bootstrapTable('getSelections');
			var	customerTypeNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = customerTypeNm.toString().replace(/,/g, ", ");
			$('#customerTypeTextBox').val(tempRes);
		}



/*================================================== Branch Fn ===================================================================== */
		
		var $branchMasterTable = $('#branchMasterIdTbl');
		$(function () {
		    $('#myBranchMasterModal').on('shown.bs.modal', function () {
		        $branchMasterTable.bootstrapTable('resetView');
		    });
		}); 
		
		var branchMasterStatus = "false";
		function popBranchMaster(val) {
			if(val === 0){
				if(branchMasterStatus === 'false'){
					$("#branchMasterIdTbl").bootstrapTable('refresh', {
						url :"/jewels/manufacturing/masters/branchMaster/listing.html?opt=2"
					});
					branchMasterStatus = true;
				}
			}else{
				$("#branchMasterIdTbl").bootstrapTable('refresh', {
					url :"/jewels/manufacturing/masters/branchMaster/listing.html?opt=2"
				});
				$('#branchMasterTextBox').val('');
			}			
		}
		
		
		
		function popBranchMasterSave(){
			
			$("#myBranchMasterModal").modal("hide");
			var	data = $('#branchMasterIdTbl').bootstrapTable('getSelections');
			var	branchMasterNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = branchMasterNm.toString().replace(/,/g, ", ");
			$('#branchMasterTextBox').val(tempRes);
		}


	/*================================================== Metal Inward In voice Fn ===================================================================== */
	
	var $mtlInwardInvoiceTable = $('#mtlInwardInvoiceIdTbl');
	$(function() {
		$('#myMetalInwardModal').on('shown.bs.modal', function() {
			$mtlInwardInvoiceTable.bootstrapTable('resetView');
		});
	});
	
	
	
/* 	function popMtlInwardInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#mtlInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/metalInwardMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#mtlInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/metalInwardMt/listing.html"
							});
		}

		//$('#orderBtn').css('display','none');
		$('#mtlInwardInvoiceTableDivId').css('display', 'block');
		$('#searchMtlInwardInvoice').val('');
	} */

	
	var mtlInwardInvoiceStatus = "false";
	function popMtlInwardInvoice(val) {
		
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;	
		});
		
			
		if(val === 0){
			if(mtlInwardInvoiceStatus === 'false'){
				if (Number(ids.length) > 0 || !!$('#fromBetDate').val()|| !!$('#toBetDate').val()) {
				
					$("#mtlInwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/metalInwardMt/partyWiseListing.html?partyIds="
												+ ids
												+ "&fromDate="
												+ $('#fromBetDate').val()
												+ "&toDate="
												+ $('#toBetDate').val(),
									});

				} else {
					
				
					$("#mtlInwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/metalInwardMt/listing.html"
									});
				}
				mtlInwardInvoiceStatus = true;
			}
		}else{
		
			
			$("#mtlInwardInvoiceIdTbl")
			.bootstrapTable('refresh',
					{
						url : "/jewels/manufacturing/transactions/metalInwardMt/listing.html"
					});
			$('#metalInwardTextBox').val('');
		}			
	}
	
	function popMtlInwardInvoiceSave(){
		
		$("#myMetalInwardModal").modal("hide");
		var	data = $('#mtlInwardInvoiceIdTbl').bootstrapTable('getSelections');
		var	mtlInwardInvoiceNm = $.map(data, function(item) {
				return item.invNo;
			});
		
		var tempRes = mtlInwardInvoiceNm.toString().replace(/,/g, ", ");
		$('#metalInwardTextBox').val(tempRes);
	}

	
	/*================================================== Metal Outward In voice Fn ===================================================================== */
	
	var $mtlOutwardInvoiceTable = $('#mtlOutwardInvoiceIdTbl');
	$(function() {
		$('#myMetalOutwardModal').on('shown.bs.modal', function() {
			$mtlOutwardInvoiceTable.bootstrapTable('resetView');
		});
	});
	
	
	var mtlOutwardInvoiceStatus = "false";
	function popMtlOutwardInvoice(val) {
		
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;	
		});
		
		console.log("Inside  of Function===========>>>>>>"+JSON.stringify(data));
		console.log("Inside  of Function===========>>>>>>"+ids);
		
		if(val === 0){
			if(mtlOutwardInvoiceStatus === 'false'){
				if (Number(ids.length) > 0 || !!$('#fromBetDate').val()|| !!$('#toBetDate').val()) {
					console.log("Inside  of if  condtn===========>>>>>>");
					$("#mtlOutwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/metalOutwardMt/partyWiseListing.html?partyIds="
												+ ids
												+ "&fromDate="
												+ $('#fromBetDate').val()
												+ "&toDate="
												+ $('#toBetDate').val(),
									});

				} else {
					
					console.log("Inside the else of if 1st condtn===========>>>>>>");
					$("#mtlOutwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/metalOutwardMt/listing.html"
									});
				}
				mtlOutwardInvoiceStatus = true;
			}
		}else{
			console.log("Inside the else of if 2nd condtn===========>>>>>>");
			
			$("#mtlOutwardInvoiceIdTbl")
			.bootstrapTable('refresh',
					{
						url : "/jewels/manufacturing/transactions/metalOutwardMt/listing.html"
					});
			$('#metalOutwardTextBox').val('');
		}			
	}
	
	function popMtlOutwardInvoiceSave(){
		
		$("#myMetalOutwardModal").modal("hide");
		var	data = $('#mtlOutwardInvoiceIdTbl').bootstrapTable('getSelections');
		var	mtlOutwardInvoiceNm = $.map(data, function(item) {
				return item.invNo;
			});
		
		var tempRes = mtlOutwardInvoiceNm.toString().replace(/,/g, ", ");
		$('#metalOutwardTextBox').val(tempRes);
	}


	
	/*================================================== Component Inward Fn ===================================================================== */	
	
	var $compInwardInvoiceTable = $('#compInwardInvoiceIdTbl');
	$(function() {
		$('#myCompInwardModal').on('shown.bs.modal', function() {
			$compInwardInvoiceTable.bootstrapTable('resetView');
		});
	});
	
	
	/* function popCompInwardInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#compInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compInwardMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#compInwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compInwardMt/listing.html"
							});
		}

		//$('#orderBtn').css('display','none');
		$('#compInwardInvoiceTableDivId').css('display', 'block');
		$('#searchCompInwardInvoice').val('');
	} */

	
	var compInwardInvoiceStatus = "false";
	function popCompInwardInvoice(val) {
		
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});
		
		if(val === 0){
			if(compInwardInvoiceStatus === 'false'){
				if (Number(ids.length) > 0 || !!$('#fromBetDate').val()|| !!$('#toBetDate').val()) {

					$("#compInwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/compInwardMt/partyWiseListing.html?partyIds="
											+ ids
											+ "&fromDate="
											+ $('#fromBetDate').val()
											+ "&toDate="
											+ $('#toBetDate').val(),
									});

				} /*  else {
					$("#compInwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/compInwardMt/listing.html"
									});
				}  */
				compInwardInvoiceStatus = true;
			}
		}else{
			$("#compInwardInvoiceIdTbl")
			.bootstrapTable('refresh',
					{
						url : "/jewels/manufacturing/transactions/compInwardMt/listing.html"
					});
			$('#metalInwardTextBox').val('');
		}			
	}
	
	
	function popCompInwardInvoiceSave(){
		
		$("#myCompInwardModal").modal("hide");
		var	data = $('#compInwardInvoiceIdTbl').bootstrapTable('getSelections');
		var	compInwardInvoiceNm = $.map(data, function(item) {
				return item.invNo;
			});
		
		var tempRes = compInwardInvoiceNm.toString().replace(/,/g, ", ");
		$('#compInwardTextBox').val(tempRes);
	}
	
	/*==================================================  Component Outward Fn ===================================================================== */	
	
	var $compOutwardInvoiceTable = $('#compOutwardInvoiceIdTbl');
	$(function() {
		$('#myCompOutwardModal').on('shown.bs.modal', function() {
			$compOutwardInvoiceTable.bootstrapTable('resetView');
		});
	});
	
/* 	function popCompOutwardInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {

			$("#compOutwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compOutwardMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#compOutwardInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/compOutwardMt/listing.html"
							});
		}

		//$('#orderBtn').css('display','none');
		$('#compOutwardInvoiceTableDivId').css('display', 'block');
		$('#searchCompOutwardInvoice').val('');
	}
 */
	
	var compOutwardInvoiceStatus = "false";
	function popCompoutwardInvoice(val) {
		
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});
		
		if(val === 0){
			if(compOutwardInvoiceStatus === 'false'){
				if (Number(ids.length) > 0 || !!$('#fromBetDate').val()|| !!$('#toBetDate').val()) {

					$("#compOutwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/compOutwardMt/partyWiseListing.html?partyIds="
											+ ids
											+ "&fromDate="
											+ $('#fromBetDate').val()
											+ "&toDate="
											+ $('#toBetDate').val(),
									});

				} else {
					$("#compOutwardInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/compOutwardMt/listing.html"
									});
				}
				compOutwardInvoiceStatus = true;
			}
		}else{
			$("#compOutwardInvoiceIdTbl")
			.bootstrapTable('refresh',
					{
						url : "/jewels/manufacturing/transactions/compOutwardMt/listing.html"
					});
			$('#compOutwardTextBox').val('');
		}			
	}
	
	
	
	function popCompOutwardInvoiceSave(){
		
		$("#myCompOutwardModal").modal("hide");
		var	data = $('#compOutwardInvoiceIdTbl').bootstrapTable('getSelections');
		var	compOutwardInvoiceNm = $.map(data, function(item) {
				return item.invNo;
			});
		
		var tempRes = compInwardInvoiceNm.toString().replace(/,/g, ", ");
		$('#compInwardTextBox').val(tempRes);
	}
	
	/*================================================== Export Invoice Fn ===================================================================== */
	
	var $exportInvoiceTable = $('#exportInvoiceIdTbl');
	$(function() {
		$('#myExportInvoiceModal').on('shown.bs.modal', function() {
			$exportInvoiceTable.bootstrapTable('resetView');
		});
	});
	
	
/* 	function popExportInvoice() {

		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#exportInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {
			$("#exportInvoiceIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/listing.html?opt=1"
							});
		}

	}

	 */
	
	 	var exportInvoiceStatus = "false";

/* 	 function popExportInvoice(val) {
			
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
			var ids = $.map(data, function(item) {
				return item.id;
			});
			
			if(val === 0){
				if(exportInvoiceStatus === 'false'){
					if (Number(ids.length) > 0 || !!$('#fromBetDate').val()	|| !!$('#toBetDate').val()) {

						$("#exportInvoiceIdTbl")
								.bootstrapTable(
										'refresh',
										{
				url : "/jewels/manufacturing/transactions/costingMt/partyWiseListing.html?partyIds="+ids+ "&fromDate="+ $('#fromBetDate').val()	+ "&toDate="+ $('#toBetDate').val(),
										});

					} else {
						$("#exportInvoiceIdTbl")
								.bootstrapTable(
										'refresh',
										{
											url : "/jewels/manufacturing/transactions/costingMt/listing.html?opt=1"
										});
					}
					exportInvoiceStatus = true;
				}
			}else{
				$("#exportInvoiceIdTbl")
				.bootstrapTable('refresh',
						{
							url :"/jewels/manufacturing/transactions/costingMt/listing.html?opt=1"
						});
				$('#exportInvoiceTextBox').val('');
			}			
		}
		
		
		 */
		
		


			function popExportInvoice() {

				var data = $('#clientIdTbl').bootstrapTable('getSelections');
				var ids = $.map(data, function(item) {
					return item.id;
				});

				if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
						|| !!$('#toBetDate').val()) {

					$("#exportInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/costingMt/partyWiseListing.html?partyIds="
												+ ids
												+ "&fromDate="
												+ $('#fromBetDate').val()
												+ "&toDate="
												+ $('#toBetDate').val(),
									});

				} else {
					$("#exportInvoiceIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/costingMt/listing.html?opt=1"
									});
				}

				//$('#orderBtn').css('display','none');
				$('#exportInvoiceTableDivId').css('display', 'block');
				/* $('#searchExportInvoice').val(''); */
				$('#exportInvoiceTextBox').val('');
			}

		 
		
		
		function popExportInvoiceSave(){
			
			$("#myExportInvoiceModal").modal("hide");
			var	data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
			var	exportInvoiceNm = $.map(data, function(item) {
					return item.invNo;
				});
			
			var tempRes = exportInvoiceNm.toString().replace(/,/g, ", ");
			$('#exportInvoiceTextBox').val(tempRes);
		}
		
	
	
	
	
	/*================================================== Quotation Invoice Fn ===================================================================== */
	
	var $quotationInvoiceTable = $('#quotationInvoiceIdTbl');
	$(function() {
		$('#myQuotationInvoiceModal').on('shown.bs.modal', function() {
			$quotationInvoiceTable.bootstrapTable('resetView');
		});
	});
	
/* 		function popQuotationInvoice() {

		$("#quotationInvoiceIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/quotationMt/report/listing.html"
						});

		//$('#orderBtn').css('display','none');
		$('#quotationInvoiceTableDivId').css('display', 'block');
		//$('#searchQuotationInvoice').val('');
	} */
	
	var quotationInvoiceStatus = "false";
	function popQuotationInvoice(val) {
		if(val === 0){
			if(quotationInvoiceStatus === 'false'){
				$("#quotationInvoiceIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/transactions/quotationMt/report/listing.html"
				});
				quotationInvoiceStatus = true;
			}
		}else{
			$("#quotationInvoiceIdTbl").bootstrapTable('refresh', {
				url :"/jewels/manufacturing/transactions/quotationMt/report/listing.html"
			});
			$('#quotationInvoiceTextBox').val('');
		}			
	}
	
	function popQuotationInvoiceSave(){
		
		$("#myQuotationInvoiceModal").modal("hide");
		var	data = $('#quotationInvoiceIdTbl').bootstrapTable('getSelections');
		var	quotationInvoiceNm = $.map(data, function(item) {
				return item.invNo;
			});
		
		var tempRes = quotationInvoiceNm.toString().replace(/,/g, ", ");
		$('#quotationInvoiceTextBox').val(tempRes);
		
		
	}
	
	
	/*================================================== Quotation Invoice List Fn ===================================================================== */	
	
	var $quotInvoiceListTable = $('#quotInvoiceListTbl');
	$(function() {
		$('#myQuotationInvoiceListModal').on('shown.bs.modal', function() {
			$quotInvoiceListTable.bootstrapTable('resetView');
		});
	});

/* 		function popQuotInvoiceList() {
		itemselections = [];
		var data = $('#quotationInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {

			$("#quotInvoiceListTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/quotDt/listing.html?mtId="
										+ ids
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	} */
	
		
		var quotationInvoiceListStatus = "false";
		function popQuotInvoiceList(val) {
			itemselections = [];
			var data = $('#quotationInvoiceIdTbl').bootstrapTable('getSelections');
			var ids = $.map(data, function(item) {
				return item.id;
			});
			
			
			if(val === 0){
				if(quotationInvoiceListStatus === 'false'){
					if (Number(ids.length) > 0) {					
					$("#quotationInvoiceIdTbl").bootstrapTable('refresh', {
						url :  "/jewels/manufacturing/transactions/quotDt/listing.html?mtId="
								+ ids
					});
					quotationInvoiceListStatus = true;
				}
					}
			} else 	{
				
					displayMsg(this, null,'Invoice Not Selected ,Please Select Invoice ');
	
				}
				
			$('#quotationInvoiceListTextBox').val('');		
		}
		
		
		function popQuotInvoiceListSave(){
			
			$("#myQuotationInvoiceListModal").modal("hide");
			var	data = $('#quotInvoiceListTbl').bootstrapTable('getSelections');
			var	quotationInvoiceListNm = $.map(data, function(item) {
					return item.style;
				});
			
			var tempRes = quotationInvoiceListNm.toString().replace(/,/g, ", ");
		/* 	$('#quotationInvoiceListTextBox').val(tempRes); */
			$('#chek12').val(tempRes);
			
		}

	
	
		/*================================================== Bag Fn ===================================================================== */
	
		
		var $bagTable = $('#bagsIdTbl');
	    $(function () {
	        $('#myBagModal').on('shown.bs.modal', function () {
	            $bagTable.bootstrapTable('resetView');
	        });
	    }); 
	    
/* 		function popBags() {

		var orderData = $('#orderIdTbl').bootstrapTable('getSelections');
		var orderIds = $.map(orderData, function(item) {
			return item.id;
		});

		$("#bagsIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/bagMt/report/listing.html?orderIds="
									+ orderIds
						});

		//$('#orderBtn').css('display','none');
		$('#bagsTableDivId').css('display', 'block');
		$('#searchBags').val('');
	} */
		
		
		var bagStatus = "false";
		function popBags(val) {
			
			var orderData = $('#orderIdTbl').bootstrapTable('getSelections');
			var orderIds = $.map(orderData, function(item) {
				return item.id;
			});
			
			if(val === 0){
				if(bagStatus === 'false'){
					$("#bagsIdTbl").bootstrapTable('refresh', {
						url :  "/jewels/manufacturing/transactions/bagMt/report/listing.html?orderIds="
							+ orderIds
					});
					bagStatus = true;
				}
			}else{
				$("#bagsIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/transactions/bagMt/report/listing.html?orderIds="
						+ orderIds
				});
				$('#bagTextBox').val('');
			}			
		}	
				
		function popBagSave(){
			
			$("#myBagModal").modal("hide");
			var	data = $('#bagsIdTbl').bootstrapTable('getSelections');
			var	bagsNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = bagsNm.toString().replace(/,/g, ", ");
			$('#bagTextBox').val(tempRes);	
		}
		
	/*================================================== Employee Fn ===================================================================== */
	
			
	var $employeeTable = $('#employeeIdTbl');
    $(function () {
        $('#myEmployeeModal').on('shown.bs.modal', function () {
            $employeeTable.bootstrapTable('resetView');
        });
    }); 
	
	
/* 		function popEmployee() {
		var deptData = $('#departmentIdTbl').bootstrapTable('getSelections');
		var deptIds = $.map(deptData, function(item) {
			return item.id;
		});

		$("#employeeIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/employee/report/listing.html?deptIds="
									+ deptIds
						});

		$('#employeeTableDivId').css('display', 'block');
		$('#searchEmployee').val('');
	}
 */
 
	var employeeStatus = "false";
	function popEmployee(val) {
		
		var deptData = $('#departmentIdTbl').bootstrapTable('getSelections');
		var deptIds = $.map(deptData, function(item) {
			return item.id;
		});
		
		if(val === 0){
			if(employeeStatus === 'false'){
				$("#employeeIdTbl").bootstrapTable('refresh', {
					url :  "/jewels/manufacturing/masters/employee/report/listing.html?deptIds="
						+ deptIds
				});
				employeeStatus = true;
			}
		}else{
			$("#employeeIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/employee/report/listing.html?deptIds="
					+ deptIds
			});
			$('#employeeTextBox').val('');
		}			
	}	

		function popEmployeeSave(){
			
			$("#myEmployeeModal").modal("hide");
			var	data = $('#employeeIdTbl').bootstrapTable('getSelections');
			var	employeeNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = employeeNm.toString().replace(/,/g, ", ");
			$('#employeeTextBox').val(tempRes);	
		}
	
	/*================================================== ExportJob Invoice Fn ===================================================================== */
	
				
	var $exportJobInvoiceTable = $('#exportJobInvoiceIdTbl');
    $(function () {
        $('#myExportJobInvoiceModal').on('shown.bs.modal', function () {
            $exportJobInvoiceTable.bootstrapTable('resetView');
        });
    }); 
	
	/* function popExportJobInvoice() {
		$("#exportJobInvoiceIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/costingJobMt/listing.html"
						});

		$('#exportJobInvoiceTableDivId').css('display', 'block');
		$('#searchExportJobInvoice').val('');
	}	 */
	
	var exportjobInvoiceStatus = "false";
	function popExportJobInvoice(val) {

		if(val === 0){
			if(exportjobInvoiceStatus === 'false'){
				$("#exportJobInvoiceIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/transactions/costingJobMt/listing.html"
				});
				exportjobInvoiceStatus = true;
			}
		}else{
			$("#exportJobInvoiceIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/transactions/costingJobMt/listing.html"
			});
			$('#exportJobInvoiceTextBox').val('');
		}			
	}	

	function popExportJobInvoiceSave(){
		
		$("#myExportJobInvoiceModal").modal("hide");
		var	data = $('#exportJobInvoiceIdTbl').bootstrapTable('getSelections');
		var	exportjobInvoiceNm = $.map(data, function(item) {
				return item.invNo;
			});
		
		var tempRes = exportjobInvoiceNm.toString().replace(/,/g, ", ");
		$('#exportJobInvoiceTextBox').val(tempRes);	
	}
	
	
		/*================================================== Export Invoice All Fn ===================================================================== */
	
	var $exportInvoiceAllTable = $('#exportInvoiceAllIdTbl');
    $(function () {
        $('#myExportInvoiceAllModal').on('shown.bs.modal', function () {
            $exportInvoiceAllTable.bootstrapTable('resetView');
        });
    }); 
	
		
/* 		
		function popExportInvoiceAll() {
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
				|| !!$('#toBetDate').val()) {

			$("#exportInvoiceAllIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/ExportInvoiceAll/partyWiseListing.html?partyIds="
										+ ids
										+ "&fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});

		} else {

			$("#exportInvoiceAllIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingMt/ExportInvoiceAll/partyWiseListing.html?fromDate="
										+ $('#fromBetDate').val()
										+ "&toDate="
										+ $('#toBetDate').val(),
							});
		}

		$('#exportInvoiceAllTableDivId').css('display', 'block');

	} */
	
		var exportInvoiceAllStatus = "false";
		function popExportInvoiceAll(val) {
			
			var data = $('#clientIdTbl').bootstrapTable('getSelections');
			var ids = $.map(data, function(item) {
				return item.id;
			});
			

			if(val === 0){
				
				if (Number(ids.length) > 0 || !!$('#fromBetDate').val()
						|| !!$('#toBetDate').val()) {

					$("#exportInvoiceAllIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/costingMt/ExportInvoiceAll/partyWiseListing.html?partyIds="
												+ ids
												+ "&fromDate="
												+ $('#fromBetDate').val()
												+ "&toDate="
												+ $('#toBetDate').val(),
									});

				} else {

					$("#exportInvoiceAllIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/costingMt/ExportInvoiceAll/partyWiseListing.html?fromDate="
												+ $('#fromBetDate').val()
												+ "&toDate="
												+ $('#toBetDate').val(),
									});
				}
				
				exportInvoiceAllStatus = true;
			}else{
				$("#exportJobInvoiceIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/transactions/costingMt/ExportInvoiceAll/partyWiseListing.html?fromDate="
						+ $('#fromBetDate').val()
						+ "&toDate="
						+ $('#toBetDate').val(),
				});
				$('#exportJobInvoiceTextBox').val('');
			}			
		}	
	
		function popExportInvoiceAllSave(){
			
			$("#myExportInvoiceAllModal").modal("hide");
			var	data = $('#exportInvoiceAllIdTbl').bootstrapTable('getSelections');
			var	exportInvoiceAllNm = $.map(data, function(item) {
					return item.invNo;
				});
			
			var tempRes = exportInvoiceAllNm.toString().replace(/,/g, ", ");
			$('#exportInvoiceAllTextBox').val(tempRes);	
		}
		
		

		
	/*================================================== Export Invoice List // Export Style Fn ===================================================================== */		
		
			
	var $exportInvoiceListTable = $('#exportInvoiceListTbl');
    $(function () {
        $('#myExportInvoiceListModal').on('shown.bs.modal', function () {
            $exportInvoiceListTable.bootstrapTable('resetView');
        });
    }); 
	
    
    
/* 		function popExportInvoiceList() {
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {
			$("#exportInvoiceListTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingDtItem/reportListing.html?mtIds="
										+ ids
										+ "&setNoId="
										+ $('#setNoId').val()
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	} */
		
		
	var exportInvoiceListStatus = "false";
	
	function popExportInvoiceList(val) {
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});
		
		if(val === 0){
						
			if(exportInvoiceListStatus === 'false'){
				
				if (Number(ids.length) > 0) {
					$("#exportInvoiceListTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/costingDtItem/reportListing.html?mtIds="
												+ ids
												+ "&setNoId="
												+ $('#setNoId').val()
									});

				} else {

					displayMsg(this, null,
							'Invoice Not Selected ,Please Select Invoice ');

				}
			}
			
			exportInvoiceListStatus = true;
		}else{
			$("#exportInvoiceListTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/transactions/costingDtItem/reportListing.html?mtIds="
					+ ids
					+ "&setNoId="
					+ $('#setNoId').val()
			});
			$('#exportInvoiceListTextBox').val('');
		}			
	}	
	
	
	
		
		function popExportInvoiceListSave(){
			
			$("#myExportInvoiceListModal").modal("hide");
			var	data = $('#exportInvoiceListTbl').bootstrapTable('getSelections');
			var	exportInvoiceListNm = $.map(data, function(item) {
					return item.style;
				});
			
			var tempRes = exportInvoiceListNm.toString().replace(/,/g, ", ");
			$('#exportInvoiceListTextBox').val(tempRes);	
		}	
		
		
		
		
		
/*================================================== Export Quality Fn ===================================================================== */		
 
 			
	var $exportQualityTable = $('#exportQualityIdTbl');
    $(function () {
        $('#myExportQualityModal').on('shown.bs.modal', function () {
            $exportQualityTable.bootstrapTable('resetView');
        });
    }); 
		
/*     function popExportQuality() {
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {
			$("#exportQualityIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingStnDtItem/exportQualityListing.html?mtIds="
										+ ids
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	} */
		
	
	var exportQualityStatus = "false";
	function popExportQuality(val) {
		
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});
		
		if(val === 0){
			
			if(exportQualityStatus === 'false'){
				
				if (Number(ids.length) > 0) {
					$("#exportQualityIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/costingStnDtItem/exportQualityListing.html?mtIds="
												+ ids
									});

				} else {

					displayMsg(this, null,
							'Invoice Not Selected ,Please Select Invoice ');

				}
				
				
				exportQualityStatus = true;
			}
		}else{
			$("#exportQualityIdTbl").bootstrapTable('refresh', {
				url :"/jewels/manufacturing/transactions/costingStnDtItem/exportQualityListing.html?mtIds="
					+ ids
			});
			$('#exportQualityTextBox').val('');
		}			
	}	

	
	function popExportQualitySave(){
		
		$("#myExportQualityModal").modal("hide");
		var	data = $('#exportQualityIdTbl').bootstrapTable('getSelections');
		var	exportQualityNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = exportQualityNm.toString().replace(/,/g, ", ");
		$('#exportQualityTextBox').val(tempRes);	
	}	
		

/*================================================== Export Size Group Fn ===================================================================== */
 
 	var $exportSizeGroupTable = $('#exportSizegroupIdTbl');
    $(function () {
        $('#myExportSizeGroupModal').on('shown.bs.modal', function () {
            $exportSizeGroupTable.bootstrapTable('resetView');
        });
    }); 
 	
 	/* function popExportSizegroup() {
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});

		if (Number(ids.length) > 0) {
			$("#exportSizegroupIdTbl")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/costingStnDtItem/exportSizegroupListing.html?mtIds="
										+ ids
							});

		} else {

			displayMsg(this, null,
					'Invoice Not Selected ,Please Select Invoice ');

		}

	}
  */
  
  var exportSizegroupStatus = "false";
	function popExportSizegroup(val) {
		
		itemselections = [];
		var data = $('#exportInvoiceIdTbl').bootstrapTable('getSelections');
		var ids = $.map(data, function(item) {
			return item.id;
		});
		
		if(val === 0){
			
			if(exportSizegroupStatus === 'false'){
				
				if (Number(ids.length) > 0) {
					$("#exportSizegroupIdTbl")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/costingStnDtItem/exportSizegroupListing.html?mtIds="
												+ ids
									});

				} else {

					displayMsg(this, null,
							'Invoice Not Selected ,Please Select Invoice ');

				}
 				
				exportSizegroupStatus = true;
			}
		}else{
			$("#exportSizegroupIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/transactions/costingStnDtItem/exportSizegroupListing.html?mtIds="
					+ ids
			});
			$('#exportSizeGroupTextBox').val('');
		}			
	}	
  
  
	function popExportSizegroupSave(){
		
		$("#myExportSizeGroupModal").modal("hide");
		var	data = $('#exportSizegroupIdTbl').bootstrapTable('getSelections');
		var	exportSizegroupNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = exportSizegroupNm.toString().replace(/,/g, ", ");
		$('#exportSizeGroupTextBox').val(tempRes);	
	}	
	
		
/*================================================== Inward Type Fn ===================================================================== */
	
	var $exportInwardTypeTable = $('#inwardTypeIdTbl');
    $(function () {
        $('#myInwardTypeModal').on('shown.bs.modal', function () {
            $exportInwardTypeTable.bootstrapTable('resetView');
        });
    });
	
	
/* 	function popInwardType() {
		$("#inwardTypeIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/inwardType/listing.html"
		});

		//$('#orderTypeBtn').css('display','none');
		$('#inwardTypeTableDivId').css('display', 'block');
		$('#searchInwardType').val('');
	} */
	
	var inwardTypeStatus = "false";
	function popInwardType(val) {
		if(val === 0){
			if(inwardTypeStatus === 'false'){
				$("#inwardTypeIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/inwardType/listing.html"
				});
				inwardTypeStatus = true;
			}
		}else{
			$("#inwardTypeIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/inwardType/listing.html"
			});
			$('#inwardTypeTextBox').val('');
		}
		
	}
	
	
	 function popInwardTypeSave(){
		
		$("#myInwardTypeModal").modal("hide");
		var	data = $('#inwardTypeIdTbl').bootstrapTable('getSelections');
		var	inwardTypeNm = $.map(data, function(item) {
				return item.name;
			});
		
		var tempRes = inwardTypeNm.toString().replace(/,/g, ", ");
		$('#inwardTypeTextBox').val(tempRes);
		
	} 
	 
	 
/*================================================== Stone invoice Fn ===================================================================== */
		
	var $stoneInvoiceTable = $('#stoneInvoiceIdTbl');
    $(function () {
        $('#myStoneInvoiceModal').on('shown.bs.modal', function () {
            $stoneInvoiceTable.bootstrapTable('resetView');
        });
    });
	
/* 	function popStoneInvoice() {

		var inwardTypeData = $('#inwardTypeIdTbl').bootstrapTable(
				'getSelections');
		var inwardTypeIds = $.map(inwardTypeData, function(item) {
			return item.id;
		});

		$("#stoneInvoiceIdTbl")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/stoneInwardMt/report/listing.html?inwardTypeId="
									+ inwardTypeIds,
						});

		//$('#orderBtn').css('display','none');
		$('#stoneInvoiceTableDivId').css('display', 'block');
		$('#searchStoneInvoice').val('');
	} */


	var stoneInvoiceStatus = "false";
	function popStoneInvoice(val) {
		
		var inwardTypeData = $('#inwardTypeIdTbl').bootstrapTable('getSelections');
		var inwardTypeIds = $.map(inwardTypeData, function(item) {return item.id;});
		
		if(val === 0){
			
			if(stoneInvoiceStatus === 'false'){
				
				$("#stoneInvoiceIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/transactions/stoneInwardMt/report/listing.html?inwardTypeId="
						+ inwardTypeIds,
				});
				stoneInvoiceStatus = true;
			}
			
		}else{
			
			$("#stoneInvoiceIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/transactions/stoneInwardMt/report/listing.html?inwardTypeId="
					+ inwardTypeIds,
			});
			$('#stoneInvoiceTextBox').val('');
		}
		
	}
	
	 function popStoneInvoiceSave(){
			
			$("#myStoneInvoiceModal").modal("hide");
			var	data = $('#stoneInvoiceIdTbl').bootstrapTable('getSelections');
			var	stoneInvoiceNm = $.map(data, function(item) {
					return item.invNo;
				});
			
			var tempRes = stoneInvoiceNm.toString().replace(/,/g, ", ");
			$('#stoneInvoiceTextBox').val(tempRes);
			
		} 
	
		
	
		/*================================================== Stone Purchase Invoice Fn ===================================================================== */
	
		
	var $stonePurcInvoiceTable = $('#stonePurcInvoiceIdTbl');
    $(function () {
        $('#myStonePurcInvoiceModal').on('shown.bs.modal', function () {
            $stonePurcInvoiceTable.bootstrapTable('resetView');
        });
    });
		
		
/* 	function popStonePurcInvoice() {		
		var inwardTypeData = $('#inwardTypeIdTbl').bootstrapTable('getSelections');
		var inwardTypeIds = $.map(inwardTypeData, function(item) {
			return item.id;
		});
		
		$("#stonePurcInvoiceIdTbl").bootstrapTable('refresh', {
			
			url : "/jewels/manufacturing/transactions/stonePurcMt/report/listing.html?inwardTypeId="+inwardTypeIds+"&fromDate="+$('#fromBetDate').val()+"&toDate="+$('#toBetDate').val(),					
					
		});
		
		//$('#orderBtn').css('display','none');
		$('#stonePurcInvoiceTableDivId').css('display','block');
		$('#searchStonePurcInvoice').val('');
	} */
	
		
	var stonePurcInvoiceStatus = "false";
	function  popStonePurcInvoice(val) {
		
		var inwardTypeData = $('#inwardTypeIdTbl').bootstrapTable('getSelections');
		var inwardTypeIds = $.map(inwardTypeData, function(item) {
			return item.id;
		});
		
		if(val === 0){
			
			if(stonePurcInvoiceStatus === 'false'){
				
				$("#stonePurcInvoiceIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/transactions/stonePurcMt/report/listing.html?inwardTypeId="
							+inwardTypeIds+"&fromDate="+$('#fromBetDate').val()+"&toDate="+$('#toBetDate').val(),
						
				});
				stonePurcInvoiceStatus = true;
			}
			
		}else{
			
			$("#stonePurcInvoiceIdTbl").bootstrapTable('refresh', {
				url :  "/jewels/manufacturing/transactions/stonePurcMt/report/listing.html?inwardTypeId="
						+inwardTypeIds+"&fromDate="
						+$('#fromBetDate').val()+"&toDate="
						+$('#toBetDate').val(),
			});
			$('#stonePurcInvoiceTextBox').val('');
		}
		
	}	
		
	 function popStonePurcInvoiceSave(){
			
			$("#myStonePurcInvoiceModal").modal("hide");
			var	data = $('#stonePurcInvoiceIdTbl').bootstrapTable('getSelections');
			var	stonePurcInvoiceNm = $.map(data, function(item) {
					return item.invNo;
				});
			
			var tempRes = stonePurcInvoiceNm.toString().replace(/,/g, ", ");
			$('#stoneInvoiceTextBox').val(tempRes);
			
		} 
		
		
		
		/*================================================== END of Functions ===================================================================== */	
		
		
		
		
</script>