<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<style>
.multiSearch {
    border-radius: 15px;
    border: 2px solid #000080;
    padding: 20px; 
    width: 150px;
    height: 15px;    
}
</style>

<div class="panel panel-primary" style="width: 100%">
	<c:set var="option" value="Client Style Labour" />

		<c:if test="${param.success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="alert alert-success">Labour Rate Added ${action}
				successfully!</div>
		</c:if>

	<div class="row">
		<h4 id="modelNameId" align="center" style="text-decoration: underline;"></h4>
	
		<div class="col-xs-12">

				<div>
					<div id="toolbar">
					  <div align="left">
							<c:choose>
				               <c:when test="${canAdd}">
								<a class="btn btn-info" style="font-size: 15px" type="button"
									href="#"  onclick="popAdd()"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour Rate</a>
									
									
								<a class="btn btn-warning" style="font-size: 15px" type="button"
									href="#" onclick="popExcel()"><span
									class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel</a>
								
							</c:when>
				                <c:otherwise>
								<a class="btn btn-info" style="font-size: 15px" type="button"
									onClick="javascript:displayMsg(event, this)"
									href="javascript:void(0)"><span
									class="glyphicon glyphicon-plus"></span>&nbsp;Add Labour Rate</a>
							</c:otherwise>
		                  </c:choose>
						
						<a class="btn btn-success" type="button" onclick="javascript:popMultiSearchLabour()">Advance Search</a>
						
						&nbsp;&nbsp;
						<a id="refreshId" href="/jewels/manufacturing/masters/clientStyleLab.html" class="glyphicon glyphicon-refresh"></a>
						</div>
						
						
						<div class="row" align="left" id="multiSearchLabourRateDivId" align="left" style="display: none; padding: 5px" >
						<div align="left" style="padding-left: 15px">
							<input type="text" id="partyName" name="partyName" class="multiSearch" size="10cm" placeholder="Party" onblur="javascript:popLabourChargeSearchListing()">
							<input type="text" id="designNm" name="designNm" class="multiSearch" size="10cm" placeholder="Style No" onblur="javascript:popLabourChargeSearchListing()">
							<input type="text" id="metalNm" name="metalNm" class="multiSearch" size="10cm" placeholder="Metal" onblur="javascript:popLabourChargeSearchListing()">
							<input type="text" id="labourTypeNm" name="labourTypeNm" class="multiSearch" size="10cm" placeholder="LabourType" onblur="javascript:popLabourChargeSearchListing()">
						</div>
						</div>
					</div>
					
					
					
					<div>
						<table id="labChargeId" data-toggle="table"
							data-toolbar="#toolbar" data-pagination="true"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
							data-striped="true" data-pagination="false" data-height="512">
							<thead>
								<tr class="btn-primary">
									<th data-field="party" data-align="left" data-sortable="true">Party</th>
									<th data-field="styleNo" data-align="left" data-sortable="true">Style No</th>
									<th data-field="metal" data-align="left" data-sortable="true">Metal</th>
									<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
									<th data-field="labourType" data-align="left" data-sortable="true">Labour Type</th>
									<th data-field="fromWeight" data-align="left" data-sortable="true">From Weight</th>
									<th data-field="toWeight" data-align="left" data-sortable="true">To Weight</th>
									<th data-field="rate" data-align="left" data-sortable="true">Rate</th>
									<th data-field="pcsRate" data-formatter="inputFormatter">Per Pcs Rate</th>
									<th data-field="gramRate" data-formatter="inputFormatter">Per Gram Rate</th>
									<th data-field="percent" data-formatter="inputFormatter">Percentage</th>
									<th data-field="perCarat" data-formatter="inputFormatter">Per Carat Rate</th>
									<th data-field="defLabour" data-formatter="inputFormatter">Default Labour</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
		
		</div>
	</div>
</div>


<script type="text/javascript">


var $table = $('#labChargeId');
function inputFormatter(value) {
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
		popLabourChargeListing();
	});


	function popLabourChargeListing(){
		$("#labChargeId")
			.bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/clientStyleLab/listing.html"
			});
	}

	function popAdd(){
		window.location.href="/jewels/manufacturing/masters/clientStyleLab/add.html";
	}

	function popExcel(){
		window.location.href="/jewels/manufacturing/masters/clientStyleLab/uploadExcel.html";
	}
	
	function popLabourChargeSearchListing(){
		
		if($('#partyName').val()!='' || $('#designNm').val()!='' || $('#metalNm').val()!='' || $('#purityNm').val()!='' || $('#labourTypeNm').val()!=''  ){
			$("#labChargeId")
			  .bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/clientStyleLab/customSearch/listing.html?partyName="+$('#partyName').val()+"&styleNo="+$('#designNm').val()
																					+"&metalNm="+$('#metalNm').val()+"&labourTypeNm="+$('#labourTypeNm').val(),
			});
		}else{
			popLabourChargeListing();
		}
		
		
	}
	
	function popMultiSearchLabour(){
	
		 $('#multiSearchLabourRateDivId').toggle("slide");
	}
	
	
</script>
