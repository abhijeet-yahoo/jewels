<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<style>
.multiSearch {
    border-radius: 15px;
    border: 2px solid #000080;
    padding: 20px; 
    width: 150px;
    height: 15px;    
}
</style>


<c:set var="option" value="stoneRateMast" />

<c:if test="${param.success eq true}">
			<div class="alert alert-success">Stone Rate Mast Updated ${action}
				successfully!</div>
</c:if>


<div>
	<h4 id="modelNameId" align="center" style="text-decoration: underline;"></h4>
	
	
	

	<div id="toolbar">
	  <div align="left">
	<c:choose>
			<c:when test="${canAdd}">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			href="#"  onclick="popAdd()"><span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Rate</a>
			
		<a class="btn btn-warning" style="font-size: 15px" type="button"
			href="#" onclick="popExcel()"><span
			class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel</a>	
		</c:when>
		<c:otherwise>
				<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:displayMsg(event, this)"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Rate</a>
			</c:otherwise>
		</c:choose>
		
		<a class="btn btn-success" type="button" onclick="javascript:popMultiSearchStone()">Advance Search</a>
		
		 &nbsp;&nbsp;
		<a id="refreshId" href="/jewels/manufacturing/masters/stoneRateMast.html" class="glyphicon glyphicon-refresh"></a>
		</div>
		
		
	<div class="row" align="left" id="multiSearchStoneRateDivId" align="left" style="display: none; padding: 5px" >	
	<div align="left">
	<input type="text" style="height: 25px" id="partyName" name="partyName" class="multiSearch" size="10cm"  placeholder="Party" onblur="javascript:popStoneRateSearchListing()">
	<input type="text" style="height: 25px" id="stoneTypeNm" name="stoneTypeNm" class="multiSearch" size="10cm" placeholder="StoneType" onblur="javascript:popStoneRateSearchListing()">
	<input type="text" style="height: 25px" id="shapeNm" name="shapeNm" class="multiSearch" size="10cm" placeholder="Shape" onblur="javascript:popStoneRateSearchListing()">
	<input type="text" style="height: 25px" id="qualityNm" name="qualityNm" class="multiSearch" size="10cm"  placeholder="Quality" onblur="javascript:popStoneRateSearchListing()">
	<input type="text" style="height: 25px" id="sizeGroupNm" name="sizeGroupNm"  class="multiSearch" size="10cm" placeholder="Size Group" onblur="javascript:popStoneRateSearchListing()">
	</div>
	</div>	
		
	</div>
	
	
			
	
	
	<div>
		<table id="stoneRateTableId" data-toggle="table"
			data-toolbar="#toolbar" data-pagination="true"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"  
			data-striped="true"  data-height="512" >
			<thead>
				<tr class="btn-primary">
                        <th data-field="party" data-align="left" data-sortable="true">Party</th>
	                    <th data-field="stoneType" data-align="left" data-sortable="true">Stone Type</th>
						<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
						<th data-field="quality" data-align="left" data-sortable="true">Quality</th>
						<th data-field="sizeGroup" data-align="left" data-sortable="true">Size Group</th>
						 <th data-field="sieve" data-align="left" data-sortable="true">Sieve</th>
						<th data-field="stoneRate" data-sortable="false">Per Carat Rate</th>
						<th data-field="perPcRate" data-sortable="false">Per Pc Rate</th>
						<th data-field="action1" data-align="center">Edit</th>
						<th data-field="action2" data-align="center">Delete</th>
				</tr>
			</thead>
		</table>
	</div>
	
</div>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<script type="text/javascript">


$(document).ready(function(e){
	popStoneRateListing();
});


function popStoneRateListing(){
	$("#stoneRateTableId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/stoneRateMast/listing.html"
		});
}

function popAdd(){
	window.location.href="/jewels/manufacturing/masters/stoneRateMast/add.html";
}

function popExcel(){
	window.location.href="/jewels/manufacturing/masters/stoneRateMast/uploadExcel.html";
}


function popStoneRateSearchListing(){
	if($('#partyName').val()!='' || $('#stoneTypeNm').val()!='' || $('#shapeNm').val()!='' || $('#qualityNm').val()!='' || $('#sizeGroupNm').val()!='' ){
	
		$("#stoneRateTableId")
		  .bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/stoneRateMast/customSearch/listing.html?partyName="+$('#partyName').val()+"&stoneTypeNm="+$('#stoneTypeNm').val()
																				+"&shapeNm="+$('#shapeNm').val()+"&qualityNm="+$('#qualityNm').val()+"&sizeGroupNm="+$('#sizeGroupNm').val(),
		});
	}else{
		popStoneRateListing();
	}
	
}


function popMultiSearchStone(){
	
	 $('#multiSearchStoneRateDivId').toggle("slide");
}


</script>

