<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalAutoStyleGeneration.jsp"%>

<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script>
 <script src="/jewels/bootstrap-table-master/dist/extensions/auto-refresh/bootstrap-table-auto-refresh.min.js"></script>

<c:set var="option" value="Design" />
<c:set var="optionText" value="Delete" />

<style>
.multiSearch {
    border-radius: 15px;
    border: 2px solid #000080;
    padding: 20px; 
    width: 150px;
    height: 15px;    
}
</style>

<div class="container-fluid">
	<div id="toolbar">
		
	
			<c:if test="${canAdd}">	
		<c:choose>
			<c:when test="${!stylenoautogeneration}">
				
				<a class="btn btn-primary" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/design/add.html"><span
					class="glyphicon glyphicon-plus"></span> New</a>
			</c:when>
				<c:when test="${stylenoautogeneration}">
				<a class="btn btn-primary" style="font-size: 15px" type="button"
					onClick="javascript:styleAutoNo()"><span
					class="glyphicon glyphicon-plus"></span> New</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-primary" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span> New</a>
			</c:otherwise>
		</c:choose>
		</c:if>
						<a class="btn btn-info" type="button" onclick="javascript:popMultiSearch()">Advance Search</a>
					
		
					<div class="row" id="multiSearchDesignDivId" align="left" style="display: none; padding: 5px" >
					<input type="text" id="designNo" name="designNo" class="multiSearch" size="10cm" placeholder="Design" onblur="javascript:popDesignSearchListing()"  autocomplete="off">
					<input type="text" id="designDate" name="designDate" class="multiSearch" size="10cm" placeholder="Design Date" onblur="javascript:popDesignSearchListing()"  autocomplete="off">
					<input type="text" id="styleNo" name="styleNo" class="multiSearch" size="10cm" placeholder="Style" onblur="javascript:popDesignSearchListing()" autocomplete="off">
					<input type="text" id="versionNo" name="versionNo" class="multiSearch" size="10cm"  placeholder="Version" onblur="javascript:popDesignSearchListing()"  autocomplete="off">
					<input type="text" id="reffNo" name="refNo" class="multiSearch" size="10cm" placeholder="Ref No" onblur="javascript:popDesignSearchListing()"  autocomplete="off">
					<input type="text" id="categoryNm" name="categoryNm" class="multiSearch" size="10cm" placeholder="Category" onblur="javascript:popDesignSearchListing()"  autocomplete="off">
					<input type="text" id="subCategoryNm" name="subCategoryNm" class="multiSearch" size="10cm" placeholder="Sub Category" onblur="javascript:popDesignSearchListing()"  autocomplete="off">
					<input type="text" id="conceptNm" name="conceptNm" class="multiSearch" size="10cm" placeholder="Concept" onblur="javascript:popDesignSearchListing()"  autocomplete="off">
					</div>
					
					
					<a class="btn btn-warning" style="font-size: 15px" type="button"
									href="#" onclick="popDesignExcel()"><span
									class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel</a>
							
				</div>
		

	
	
	<div class="table-responsive">
		<table id="designId" data-toggle="table" data-show-export="true"
			data-toolbar="#toolbar" data-pagination="true" data-page-list="[10, 20, 50, 100, 200, 500]"
			data-side-pagination="server" data-search="true"  data-height="550">
			<thead>
				<tr>
					<th data-field="designNo" data-sortable="true">Design No</th>
					<th data-field="designDate" data-sortable="true">Design Date</th>
					<th data-field="styleNo"  data-sortable="true">Style No</th>
					<th data-field="version" data-sortable="true">Version</th>
					<th data-field="mainStyleNo" data-sortable="true">Main Style No</th>
					<th data-field="refNo" data-sortable="true">Ref. No</th>
					<th data-field="category" data-sortable="true">Category</th>
					<th data-field="subCategory" data-sortable="true">Sub Category</th>
					<th data-field="concept" data-sortable="true">Concept</th>
					<th data-field="size" data-sortable="true">Size</th>
					<th data-field="uploadImage" data-align="center">Image</th>
					<th data-field="action1" data-align="center">Edit</th>
					<th data-field="action2" data-align="center">Delete</th>
					<th data-field="action3" data-align="center">View</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<script type="text/javascript">

$(document).ready(function(e){	
	popDesignListing();
});

function popDesignListing(){
	
	$("#designId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/design/listing.html"
		});
}


function popDesignSearchListing(){
	
	if($('#styleNo').val()!='' || $('#versionNo').val()!='' || $('#designNo').val()!='' || $('#reffNo').val()!='' 
			|| $('#categoryNm').val()!='' || $('#subCategoryNm').val()!='' || $('#conceptNm').val()!='' || $('#designDate').val() ){
		
		$("#designId")
		  .bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/design/customSearch/listing.html?styleNo="+$('#styleNo').val()+"&versionNo="+$('#versionNo').val()
																				+"&designNo="+$('#designNo').val()+"&reffNo="+$('#reffNo').val()+"&categoryNm="+$('#categoryNm').val()
																				+"&subCategoryNm="+$('#subCategoryNm').val()+"&conceptNm="+$('#conceptNm').val()+"&designDate="+$('#designDate').val(),
		});
		
		
	}else{
		popDesignListing();
		
		
	}
	
}


function popMultiSearch(){

	 $('#multiSearchDesignDivId').toggle("slide");
}


function popDesignExcel(){
	window.location.href="/jewels/manufacturing/masters/design/uploadExcel.html";
}

function styleAutoNo(){
	$('#myAutoStyleGenerationModal').modal('show');

}

</script>


<script src="/jewels/js/lighter/jquery.lighter.js"
	type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet"
	type="text/css" />

<script src="/jewels/js/common/design.js"></script>
	
	

	
	