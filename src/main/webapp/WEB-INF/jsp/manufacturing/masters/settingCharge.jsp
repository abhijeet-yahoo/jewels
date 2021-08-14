<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<div class="panel-heading" >
		<span style="font-size: 18px;">Setting Charge</span>
	</div>

	<c:set var="option" value="Setting Charge" />

	<c:if test="${param.success eq true}">
		<div class="alert alert-success">Setting Charge ${action} successfully!</div>
	</c:if>

	<div class="row">
	<h4 id="modelNameId" align="center" style="text-decoration: underline;"></h4>
		<div class="col-xs-12">
			<!-- <div class="col-xs-2">
				<div>
					<table id="shapeId" data-toggle="table"
						data-url="/jewels/manufacturing/masters/shape/listing.html?opt=2"
						data-pagination="false" data-side-pagination="server"
						data-height="458" data-search="true">
						<thead>
							<tr class="btn-primary">
								<th data-field="name">Shape</th>
							</tr>
						</thead>
					</table>
				</div>
			</div> -->
			<div class="col-xs-12">
				<div>
					<div id="toolbar">
					    <div align="left">
							<c:choose>
					            <c:when test="${canAdd}">
									<a class="btn btn-info" style="font-size: 15px;" type="button"
										href="#"  onclick="popAdd()"><span
										class="glyphicon glyphicon-plus"></span>&nbsp;Add Setting Charge
									</a>
								
									<a class="btn btn-warning" style="font-size: 15px" type="button"
										href="#" onclick="popExcel()"><span
										class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel
									</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-info" style="font-size: 15px" type="button"
										onClick="javascript:displayMsg(event, this)"
										href="javascript:void(0)"><span
										class="glyphicon glyphicon-plus"></span>&nbsp;Add Setting Charge</a>
								</c:otherwise>
							</c:choose>
							
							<a class="btn btn-success" type="button" onclick="javascript:popMultiSearchSetting()">Advance Search</a>
							
							 &nbsp;&nbsp;
							<a id="refreshId" href="/jewels/manufacturing/masters/settingCharge.html" class="glyphicon glyphicon-refresh"></a>
						</div>
						
						<div class="row" align="left" id="multiSearchSettingRateDivId" align="left" style="display: none; padding: 5px" >
						<div align="left">
							<input type="text" id="partyName" name="partyName" class="multiSearch" size="10cm" placeholder="Party" onblur="javascript:popSettingChargeSearchListing()">
							<input type="text" id="metalNm" name="metalNm" class="multiSearch" size="10cm" placeholder="Metal" onblur="javascript:popSettingChargeSearchListing()">
							<input type="text" id="stoneTypeNm" name="stoneTypeNm" class="multiSearch" size="10cm" placeholder="StoneType" onblur="javascript:popSettingChargeSearchListing()">
							<input type="text" id="shapeNm" name="shapeNm" class="multiSearch" size="10cm" placeholder="Shape" onblur="javascript:popSettingChargeSearchListing()">
							<input type="text" id="settingNm" name="settingNm" class="multiSearch" size="10cm" placeholder="Setting" onblur="javascript:popSettingChargeSearchListing()">
							<input type="text" id="settingTypeNm" name="settingTypeNm" class="multiSearch" size="10cm" placeholder="Setting Type" onblur="javascript:popSettingChargeSearchListing()">
						</div>
						</div>
						
					</div>
					<div>
						<table id="settingRateId" data-toggle="table"
							data-toolbar="#toolbar" data-pagination="true"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-search="true"
							data-striped="true" data-pagination="true" data-height="512">
							<thead>
								<tr class="btn-primary">
									<th data-field="party" data-align="left" data-sortable="true">Party</th>
									<th data-field="metal" data-align="left" data-sortable="true">Metal</th>
									<th data-field="stoneType" data-align="left" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
									<th data-field="setting" data-align="left"data-sortable="true">Setting</th>
									<th data-field="settingType" data-align="left"data-sortable="true">Setting Type</th>
									<th data-field="fromWeight" data-align="left" data-sortable="true">From Weight</th>
									<th data-field="toWeight" data-align="left" data-sortable="true">To Weight</th>
									<th data-field="rate" data-align="left"data-sortable="true">Rate</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Deactivate</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>


			</div>
		</div>
	</div>
	
</div>			
			
							<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
			
<script type="text/javascript">
	
	$(document).ready(
			function(e) {
				
			if (window.location.href.indexOf('id') != -1) {	
				var abcx = window.location.href;
				var shapeId = abcx.substring(window.location.href.indexOf('id') + 3);
				getChild(shapeId);
			}
			
			popSettingChargeListing();

	  });
	
	
	
	
	
	
	function popAdd(){
		window.location.href="/jewels/manufacturing/masters/settingCharge/add.html";
	}

	function getChild(id) {
		$("#settingRateId")
				.bootstrapTable(
						'refresh',{
							url : "/jewels/manufacturing/masters/settingCharge/listing.html"
						});
		
		
		$("#shapeId").bootstrapTable({
			pagingType : "bootstrapPager",
			pagerSettings : {
				textboxWidth : 70,
				firstIcon : "",
				previousIcon : "glyphicon glyphicon-arrow-left",
				nextIcon : "glyphicon glyphicon-arrow-right",
				lastIcon : "",
				searchOnEnter : true,
				language : "Page ~ of ~ pages"
			}
		});
	}
	
	
	function popExcel(){
		window.location.href="/jewels/manufacturing/masters/settingCharge/uploadExcel.html";
	}
	
	
	function popSettingChargeListing(){
		$("#settingRateId")
			.bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/settingCharge/listing.html"
			});
	}
	
	
	function popSettingChargeSearchListing(){
		if($('#partyName').val()!='' || $('#stoneTypeNm').val()!='' || $('#metalNm').val()!='' || $('#shapeNm').val()!='' || $('#settingNm').val()!='' || $('#settingTypeNm').val()!=''  ){
			
			$("#settingRateId")
			  .bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/settingCharge/customSearch/listing.html?partyName="+$('#partyName').val()+"&metalNm="+$('#metalNm').val()
																					+"&stoneTypeNm="+$('#stoneTypeNm').val()+"&shapeNm="+$('#shapeNm').val()
																					+"&settingNm="+$('#settingNm').val()+"&settingTypeNm="+$('#settingTypeNm').val(),
			});
			
		}else{
			popSettingChargeListing();
		}
		
	}
	
	
	function popMultiSearchSetting(){
		
		 $('#multiSearchSettingRateDivId').toggle("slide");
	}

</script>			