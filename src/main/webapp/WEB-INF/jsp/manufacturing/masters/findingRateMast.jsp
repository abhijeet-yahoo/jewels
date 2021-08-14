<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<style>
.multiSearch {
    border-radius: 15px;
    border: 2px solid #000080;
    padding: 20px; 
    width: 150px;
    height: 15px;    
}
</style>

<c:set var="option" value="findingRateMast" />

<c:if test="${param.success eq true}">
	<div class="alert alert-success">Finding Rate updated ${action} successfully!</div>
</c:if>


<div>
	<h4 id="modelNameId" align="center" style="text-decoration: underline;"></h4>
	
	<div id="toolbar">
		<c:choose>
				<c:when test="${canAdd}">
				<a class="btn btn-info" style="font-size: 15px;" type="button"
					href="#"  onclick="popAdd()"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Finding Rate
				</a>
				
	                <a class="btn btn-warning" style="font-size: 15px" type="button"
						href="#" onclick="popExcel()"><span
						class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel</a>
		      </c:when>
		      
		      <c:otherwise>
					<a class="btn btn-info" style="font-size: 15px" type="button"
						onClick="javascript:displayMsg(event, this)"
						href="javascript:void(0)"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Finding Rate</a>
				</c:otherwise>
		 </c:choose>    
		 
		 <a class="btn btn-success" type="button" onclick="javascript:popMultiSearchFinding()">Advance Search</a>
		 
		 <div align="left">
			
							<div class="row" id="multiSearchFindingRateDivId" align="left" style="display: none; padding: 5px" >
							<input type="text" id="partyName" name="partyName"  class="multiSearch" size="10cm"  placeholder="Party" onchange="javascript:popFindingRateSearchListing()">
							<input type="text" id="purityName" name="purityName" class="multiSearch" size="10cm"  placeholder="Purity" onchange="javascript:popFindingRateSearchListing()">
							<input type="text" id="findingName" name="findingName" class="multiSearch" size="10cm" placeholder="Findings" onchange="javascript:popFindingRateSearchListing()">
							</div>
						</div>

						<input type="hidden" id="departmentId" name="departmentId">
		    
	</div>
	
	
	<div id="clientDivListId">
		<div>
			<table id="findingRateId" data-toggle="table"
				data-toolbar="#toolbar" data-pagination="true"
				data-click-to-select="true"
				data-side-pagination="server"
				data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
				data-striped="true"  data-height="512" >
				<thead>
					<tr class="btn-primary">
						<th data-field="party" data-align="left" data-sortable="true">Party</th>
						<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
						<th data-field="component" data-align="left" data-sortable="true">Finding</th>
						<th data-field="perGramRate" data-formatter="inputLabFormatter">Per Gram Rate</th>
						<th data-field="perPcRate" data-formatter="inputLabFormatter">Per Pc Rate</th>
						<th data-field="rate" data-sortable="true" data-align="left">Rate</th>
						<th data-field="action1" data-align="center">Edit</th>
						<th data-field="action2" data-align="center">Delete</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	

	
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>



<script type="text/javascript">

//check-box-listing -----//
var $table = $('#findingRateId');
function inputLabFormatter(value) {
	var booleanValue;
	if (typeof (value) === "boolean") {
		booleanValue = (value);
	} else {
		booleanValue = (value == 'true');
	}

	var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
	return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
}


$(document).ready(function(e){
	popFindingRateListing();
});


function popFindingRateListing(){
	$("#findingRateId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/findingRateMast/listing.html"
		});
}

function popAdd(){
	window.location.href="/jewels/manufacturing/masters/findingRateMast/add.html";
}

function popExcel(){
	window.location.href="/jewels/manufacturing/masters/findingRate/uploadExcel.html";
}


function popFindingRateSearchListing(){
	
	if($('#partyName').val()!='' || $('#purityName').val()!='' || $('#findingName').val()!='' ){
		$("#findingRateId")
		  .bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/findingRate/customSearch/listing.html?partyCode="+$('#partyName').val()+"&purityName="+$('#purityName').val()
																				+"&findingName="+$('#findingName').val(),
		});
	}else{
		popFindingRateListing();
	}
	
	
}


function popMultiSearchFinding(){

	 $('#multiSearchFindingRateDivId').toggle("slide");
}


</script>

 


