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

	<!--------- category modal --------->

	<div class="modal fade" id="myCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
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
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">CATEGORY</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popCategory(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popCategSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>



 
 	<!-------- design group modal -------->

	<div class="modal fade" id="myDesignGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">DesignGroup</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchDesignGroup" class="search form-control" placeholder="DesignGrp Search " />
		       	</div>
	       </div>		       
	       
			<div id="designGroupTableDivId">
				<table  id="designGroupIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">DESIGN GROUP</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popDesignGroup(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popDesignGrpSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>



		<!-------- sub category modal -------->

	<div class="modal fade" id="mySubCategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">SubCategory</h4>
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
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="categName" data-align="left" data-sortable="true" style="font-weight: bolder;">CATEGORY</th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">SUB CATEGORY</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popSubCategory(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popSubCategSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
		<!-------- collection modal -------->

	<div class="modal fade" id="myCollectionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Collection</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchCollection" class="search form-control" placeholder="Collection Search " />
		       	</div>
	       </div>		       
	       
			<div id="collectionTableDivId">
				<table  id="collectionIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">COLLECTION</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popCollection(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popCollectionSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	<!-------- concept modal -------->

	<div class="modal fade" id="myConceptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
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
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">CONCEPT</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popConcept(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popConceptSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<!-------- subconcept modal -------->

	<div class="modal fade" id="mySubConceptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
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
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350">
						<thead>
							<tr >
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="conceptName" data-align="left" data-sortable="true" style="font-weight: bolder;">CONCEPT</th>
								<th data-field="name" data-align="left" data-sortable="true">SUB CONCEPT</th>
							</tr>
						</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popSubConcept(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popSubConceptSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	<!-------- StoneType modal -------->

	<div class="modal fade" id="myStoneTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
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
				<table id="stoneTypeIdTbl" data-toggle="table"
					data-click-to-select="true" data-side-pagination="server"
					data-striped="true" data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">STONE TYPE</th>
						</tr>
					</thead>
				</table>
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popStoneType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popStoneTypeSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<!-------- Look Master modal -------->

	<div class="modal fade" id="myLookMasterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Look</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchLook" class="search form-control" placeholder="Look Search " />
		       	</div>
	       </div>		       
	       
			<div id="lookTableDivId">
				<table id="lookIdTbl" data-toggle="table"
					data-click-to-select="true" data-side-pagination="server"
					data-striped="true" data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Look</th>
						</tr>
					</thead>
				</table>
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popStoneType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popLookSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<!-------- Pattern Master modal -------->

	<div class="modal fade" id="myPatternMasterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Pattern</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchLook" class="search form-control" placeholder="Pattern Search " />
		       	</div>
	       </div>		       
	       
			<div id="patternTableDivId">
				<table id="patternIdTbl" data-toggle="table"
					data-click-to-select="true" data-side-pagination="server"
					data-striped="true" data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Pattern</th>
						</tr>
					</thead>
				</table>
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popStoneType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popPatternSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	<!-------- Shape modal -------->

	<div class="modal fade" id="myShapeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
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
	       
			<div id="shapeTableDivId" >
				<table id="shapeIdTbl" data-toggle="table"
					data-click-to-select="true" data-side-pagination="server"
					data-striped="true" data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">SHAPE</th>
						</tr>
					</thead>
				</table>
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popShape(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popShapeSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	

	
	<!-------- SizeGroup modal -------->

	<div class="modal fade" id="mySizeGroupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">SizeGroup</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchSizeGroup" class="search form-control" placeholder="SizeGroup Search " />
		       	</div>
	       </div>		       
	       
			<div id="sizeGroupTableDivId" >
				<table id="sizeGroupIdTbl" data-toggle="table"
					data-click-to-select="true" data-side-pagination="server"
					data-striped="true" data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="shapeName" data-align="left" data-sortable="true" style="font-weight: bolder;">SHAPE</th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">SIZE GROUP</th>
						</tr>
					</thead>
				</table>
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popSizeGroup(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popSizeGroupSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	<!-------- SettingType modal -------->

	<div class="modal fade" id="mySettingTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">SettingType</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchSettingType" class="search form-control" placeholder="SettingType Search " />
		       	</div>
	       </div>		       
	       
			<div id="settingTypeTableDivId" >
				<table id="settingTypeIdTbl" data-toggle="table"
					data-click-to-select="true" data-side-pagination="server"
					data-striped="true" data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">SETTING TYPE</th>
						</tr>
					</thead>
				</table>
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popSettingType(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popSettingTypeSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
		<!--------- Client modal --------->

	<div class="modal fade" id="myClientModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
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
	       
			<div id="partyTableDivId">
				<table  id="clientIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="code" data-align="left" data-sortable="true" style="font-weight: bolder;">Client</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popParty(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popClientSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<!-- modalDesignCopy -->
	
		<div class="modal fade" id="myDesignCopyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	          
	      <div class="modal-body">

				<div class="row">
					<div class="col-sm-12">
						<label class="col-sm-3">New Design No:</label>

						<div class="col-sm-4">
							<input type="text" id="designNoId" name="designNo"
								class="form-control" />
						</div>
					</div>
					
					<div class="col-sm-12">&nbsp;</div>
					
					<div class="col-sm-12">
						<label class="col-sm-3">New Style No :</label>

						<div class="col-sm-4">
							<input type="text" id="styleNoId" name="styleNo"
								class="form-control" />
						</div>
					</div>
					<div class="col-sm-12">&nbsp;</div>
					
					<div class="col-sm-12">
						<label class="col-sm-3">Version    :</label>

						<div class="col-sm-4">
							<input type="text" id="versionId" name="versionNo"
								class="form-control" />
						</div>
					</div>
				</div>
	
	       
	      </div>
	      <!-- 
	      <div class="row">
			<div class="col-xs-12">&nbsp;</div>
          </div> -->
	      <div class="modal-footer1" >
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popDesignCopySave()">Save</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	





	<script type="text/javascript">
	
	
	var $catTable = $('#categoryIdTbl');
    $(function () {
        $('#myCategoryModal').on('shown.bs.modal', function () {
            $catTable.bootstrapTable('resetView');
        });
    }); 
    
    
    var $desGrpTable = $('#designGroupIdTbl');
    $(function () {
        $('#myDesignGroupModal').on('shown.bs.modal', function () {
            $desGrpTable.bootstrapTable('resetView');
        });
    }); 
    
    
    var $subCatTable = $('#subCategoryIdTbl');
    $(function () {
        $('#mySubCategoryModal').on('shown.bs.modal', function () {
            $subCatTable.bootstrapTable('resetView');
        });
    }); 
    
    var $lookTable = $('#lookIdTbl');
    $(function () {
        $('#myLookMasterModal').on('shown.bs.modal', function () {
            $lookTable.bootstrapTable('resetView');
        });
    }); 
    
    var $patternTable = $('#patternIdTbl');
    $(function () {
        $('#myPatternMasterModal').on('shown.bs.modal', function () {
            $patternTable.bootstrapTable('resetView');
        });
    }); 
    
    var $conceptTable = $('#conceptIdTbl');
    $(function () {
        $('#myConceptModal').on('shown.bs.modal', function () {
            $conceptTable.bootstrapTable('resetView');
        });
    }); 
    
    
    var $collectionTable = $('#collectionIdTbl');
    $(function () {
        $('#myCollectionModal').on('shown.bs.modal', function () {
            $collectionTable.bootstrapTable('resetView');
        });
    });
    
    
    var $subConTable = $('#subConceptIdTbl');
    $(function () {
        $('#mySubConceptModal').on('shown.bs.modal', function () {
            $subConTable.bootstrapTable('resetView');
        });
    }); 
    
    
    var $stoneTypeTable = $('#stoneTypeIdTbl');
    $(function () {
        $('#myStoneTypeModal').on('shown.bs.modal', function () {
            $stoneTypeTable.bootstrapTable('resetView');
        });
    }); 
    
    
    var $shapeTable = $('#shapeIdTbl');
    $(function () {
        $('#myShapeModal').on('shown.bs.modal', function () {
            $shapeTable.bootstrapTable('resetView');
        });
    }); 
    
    var $sizeGroupTable = $('#sizeGroupIdTbl');
    $(function () {
        $('#mySizeGroupModal').on('shown.bs.modal', function () {
            $sizeGroupTable.bootstrapTable('resetView');
        });
    }); 
    
    var $settingTypeTable = $('#settingTypeIdTbl');
    $(function () {
        $('#mySettingTypeModal').on('shown.bs.modal', function () {
            $settingTypeTable.bootstrapTable('resetView');
        });
    }); 
    
    
    var $clientTable = $('#clientIdTbl');
    $(function () {
        $('#myClientModal').on('shown.bs.modal', function () {
            $clientTable.bootstrapTable('resetView');
        });
    });
    
	
	</script>



