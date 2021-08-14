<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@include file="/WEB-INF/jsp/common/modalStonePurchaseDt.jsp"%>


<div id="stonePurchaseDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Stone Purchase</span>
	</div>
	<div class="form-group">
		<form:form commandName="stonePurchaseMt" id="stonePurchaseMtDivId"
			action="/jewels/manufacturing/transactions/stonePurchaseMt/add.html"
			cssClass="form-horizontal stonePurchaseMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Stone ${action}
							successfully!</div>
					</div>
				</div>
			</c:if>

			<!-- Column Sizing -->
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							Date:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Be
							No:</label>
					</div>
					
						<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">BeDate:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:input path="invNo" cssClass="form-control" autocomplete="off"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control" />
						<form:errors path="invDate" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="beNo" cssClass="form-control" />
						<form:errors path="beNo" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:input path="beDate" cssClass="form-control" />
						<form:errors path="beDate" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
				
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Supplier:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">For Client:</label>
					</div>
					
							<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Type:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-12 text-right">Remarks:</label>
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="supplier.id" class="form-control">
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="party.id" class="form-control">
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					
					
						<div class="col-lg-3 col-sm-3">
						<form:select path="inwardType.id" class="form-control">
							<form:option value="" label=" Select Type " />
							<form:options items="${inwardTypeMap}" />
						</form:select>

					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:textarea path="remark" cssClass="form-control" autocomplete="off"/>
						<form:errors path="remark" />
					</div>
					
					
				</div>
			</div>
		
			<div class="form-group">
			<div class="col-xs-12">
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="stonePurchaseSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/stonePurchaseMt.html">Listing</a>
					<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="uniqueId" />
					<input type="hidden" name="vTranDate" id="vTranDate" />		
				</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>

<script type="text/javascript">

canViewFlag=false;
var mtid;
	$(document)
			.ready(
					function(e) {
						$(".stonePurchaseMtForm")
								.validate(
										{
											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/stonePurchaseMt/invoiceNoAvailable.html' />",
														type : "get",
														data : {

															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},
												invDate : {
													required : true,
												},
												'party.id' : {
													required : true,
												},
												'inwardType.id' : {
													required : true,
												},

											},

											messages : {
												invNo : {
													remote : "Invoice No already exists"
												}
											}

										});
						
						
			

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						$("#vTranDate").val($('#invDate').val())
						
						if('${canEditTranddate}' === "false"){
							$("#invDate").attr("disabled","disabled");
						}

						$("#beDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						if (window.location.href.indexOf('edit') != -1) {
							
							$("#stonePurchaseDtDiv").css('display', 'block');
							mtid="${mtid}";

							popStonePurchaseDt();
							totsummary();
							
						}
						else{
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

					

					});
	
	
	
	
	  $(document)
		.on(
			'submit',
			'form#stonePurchase',
			 function(e){
				 $("#stonePurchaseSubmitBtn").prop("disabled", true).addClass("disabled");
			});
	
	
	
	
	

	function popStonePurchaseDt() {
		$("#stoneIDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/stonePurchaseDt/listing.html?mtId="+$('#stonePurchaseMtDivId #id').val(),
						});
	}
	
	function editStonePurchaseDt(dtId) {
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/stonePurchaseDt/edit/"+ dtId + ".html",
			type : 'GET',
			dataType:"JSON",
			success : function(data) {
				
			if(data === -2){
				displayMsg(this,null,'Balance Adjusted, Can Not Edit');
			}else{	
			
				var validator = $( ".stonePurchaseDtForm" ).validate();
				validator.resetForm();
				
				$('#myStonePurchaseDtModal').modal('show');

				$.each(data,function(k,v){
					
					$('#stonePurchaseDtModalDivId  #'+k).val(v);
					
					if(k === 'quality'){
						$('#stonePurchaseDtModalDivId #shape\\.id').trigger('onchange');
						setTimeout(function() {

							$('#stonePurchaseDtModalDivId #quality\\.id').val(v);
						}, 600);

					}
					
					if(k === 'size'){
						
						$('#stonePurchaseDtModalDivId #shape\\.id').trigger('onchange');
						setTimeout(function() {

							$('#stonePurchaseDtModalDivId #size').val(v);
						}, 600);

					}
					
				
					
				});
			}
			}
		
		});	
	
		
	}

	
	
	function totsummary(){
	
	$('#stoneIDtId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#stoneIDtId").bootstrapTable('getData'));

				
				var vStones = 0.0;
				var vCarat = 0.0;
				var vAmount = 0.0;
				
				
				
				$.each(JSON.parse(data), function(idx, obj) {
					
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					vAmount		+= Number(obj.amount);  
					
					
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				$('#vTotalAmounts').val(" "+ parseFloat(vAmount).toFixed(2));
				
			});
	
	}
	

	
		
	
	
function deleteStonePurchaseDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteStonePurchaseDtData('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	}

	function deleteStonePurchaseDtData(dtId){

		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/stonePurchaseDt/delete/'+dtId+'.html',
			data: 'GET',
			success : function(data){
								
				if(Number(data) === -2){
					displayMsg(this,null,'Balance Adjusted, Can Not Delete');
					
				}else{
					popStonePurchaseDt();
					}
			}
			
		})
		
	}
	

	
	function goToStonePurchaseDtEntry() {
		$('#myStonePurchaseDtModal').modal('show');
		$('#vStnPurcMtId').val($('#stonePurchaseMtDivId #id').val());
	
	}
	
	
	

	
	
</script>
<div id="stonePurchaseDtDiv" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToStonePurchaseDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
						
					</div>
					<div>
						<table id="stoneIDtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" 
							data-height="350" data-striped="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="stoneType" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="subShape" data-align="left">SubShape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="stoneChart" data-sortable="true">Size</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<th data-field="diffCarat" data-sortable="true">Diff Carat</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="packetNo" data-sortable="true">Packet No</th>
									<th data-field="remark" data-sortable="true">Remark</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
					
					
					
				</div>
				
				
				
			</div>
		</div>
		
		<div>
		<span style="display:inline-block; width: 15cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />			
						&nbsp;&nbsp;								
					Total Amount : <input type="text" id="vTotalAmounts" name="vTotalAmounts" value="${totalAmounts}"  
									maxlength="7" size="7" disabled="disabled" />
					
					</div>
		

		<c:set var="option" value="User" />

		
	</div>


	
</div>



<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />


