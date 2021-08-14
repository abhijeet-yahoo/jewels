<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Bag Search</span>
	</div>

	<div class="panel body" style="height: 443px">

		<div class="panel panel panel-default col-sm-12">

			<form:form commandName="bagMt">
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="form-group">
					<label class="col-sm-1 text-right control-label">Bag No:</label>
					<div class="col-sm-2">
						<form:input path="name" cssClass="form-control"
							onBlur="javascript:popDetails();pendingForApprovalPopup();" />
						<form:errors path="name" />
					</div>
				</div>

				<div class="col-xs-12">
					<hr
						style="width: 100%; color: red; height: 2px; background-color: red;" />
				</div>



				<div class="col-xs-10">



					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Order No:</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Client:</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Style No:</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Bag Pcs:</label>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderDt.orderMt.invNo" cssClass="form-control"
									disabled="true" />
								<form:errors path="orderDt.orderMt.invNo" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderDt.orderMt.party.name"
									cssClass="form-control" disabled="true" />
								<form:errors path="orderDt.orderMt.party.name" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderDt.design.styleNo"
									cssClass="form-control" disabled="true" />
								<form:errors path="orderDt.design.styleNo" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="qty" cssClass="form-control" disabled="true" />
								<form:errors path="qty" />
							</div>

						</div>
					</div>


					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>


					<div class="row">
						<div class="col-xs-12">


							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Current Department :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Gross Wt :</label>
							</div>

							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Total Stone :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-left"
									style="font-size: 13px;">Total Carat :</label>
							</div>



						</div>
					</div>


					<div class="row">
						<div class="col-xs-12">

							<div class="col-lg-3 col-sm-3">
								<input type="text" name="vDepartment" id="vDepartment"
									class="form-control" disabled="true" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<input type="text" name="vGrossWt" id="vGrossWt"
									class="form-control" disabled="true" />
							</div>


							<div class="col-lg-3 col-sm-3">
								<input type="text" name="vTotStone" id="vTotStone"
									class="form-control" disabled="true" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<input type="text" name="vTotCarat" id="vTotCarat"
									class="form-control" disabled="true" />
							</div>

						</div>
					</div>


				</div>

				<div id="odImgDivId"
					class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
					<div class="panel panel-primary" style="height: 170px">
						<div class="panel-body">
							<div style="width: 150px; height: 50px">
								<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png"
									data-lighter> <img id="ordImgId" class="img-responsive"
									src="/jewels/uploads/manufacturing/blank.png" />
								</a>
							</div>
						</div>
					</div>
				</div>


			</form:form>



	
<div class="panel panel panel-default col-sm-12">
				<div class="row" id="tblMetalDivId">

					<div id="toolbarDtMetal" align="left">
						<label> Metal Details:</label>
						
					</div>


					<div class="table-responsive">
						<table id="modalTranMetalTblId" data-toggle="table"
							data-toolbar="#toolbarDtMetal" data-side-pagination="server"
							data-striped="true" data-height="100px">

							<thead>
								<tr class="btn-primary">
									<th data-field="partNm" data-sortable="false">Part</th>
									<th data-field="purity" data-sortable="false">Purity</th>
									<th data-field="color" data-align="left">Color</th>
									<th data-field="qty" data-sortable="false">Qty</th>
									<th data-field="metalWt" data-sortable="false">Metal Wt</th>
								</tr>
							</thead>

						</table>
					</div>

				</div>
				</div>
			</div>
		</div>
	</div>



<script type="text/javascript">




$(document).ready(
		function(e) {
			
			
			
			$("#name").autocomplete(
					{
						source : "<spring:url value='/manufacturing/transactions/bagSearch/jobBag/list.html' />",
						minLength : 2
					});
			
			
			
			
		});





	function popDetails(){
		

		
		if ($("#name").val() != "") {

			$
					.ajax({
						url : "<spring:url value='/manufacturing/transactions/search/jobBag/details.html?bagName=' />"
								+ $('#name').val(),
						type : "GET",
						success : function(data, textStatus, jqXHR) {

							if(data.indexOf("Approval") != -1){
								displayMsg(this, null, data);
								}else{
							
							$.each(JSON.parse(data), function(idx, obj) {
								
								$('#orderDt\\.orderMt\\.invNo').val(obj.invNo);
								$('#orderDt\\.orderMt\\.party\\.name').val(obj.party);
								$('#orderDt\\.design\\.styleNo').val(obj.styleNo);
								$('#qty').val(obj.bagPcs);
								$('#orderDt\\.purity\\.name').val(obj.kt);
								$('#orderDt\\.color\\.name').val(obj.color);
								$('#vDepartment').val(obj.department);
								$('#vGrossWt').val(obj.recWt);
								$('#vTotStone').val(obj.stone);
								$('#vTotCarat').val(obj.carat);
								$('#ordImgId').attr('src', obj.image);
								$('#oImgHRId').attr('href', obj.image);
								getMetalData();
								
							});
							}
						},
						error : function(jqXHR, textStatus, errorThrown) {
						}
					});
			
		
		}
			
	}

	
	function getMetalData(){
		
	
		$("#modalTranMetalTblId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/transfer/tranMetal/modal/listing.html?BagNo="
						+ $('#name').val()
						+ "&deptName="
						+ $('#vDepartment').val(),
				}); 
	
	}


	function pendingForApprovalPopup(){

		if ($("#name").val() != "") {
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/transfer/pendingForApprovalPopup.html?bagNo="+$('#name').val(),
			type:'GET',
			success : function(data, textStatus, jqXHR) {

/* 				if(data != "false"){

					displayMsg(event, this, data);
					} */
			}
			
		});
		
	}
	}

</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/design.js' />"></script>
<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />
