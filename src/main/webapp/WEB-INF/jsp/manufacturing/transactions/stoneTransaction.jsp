<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div align="center">
	<div class="panel panel-primary" style="width: 50%">

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div align="center">
			<span style="font-size: 22px;">Stone Transaction</span>
		</div>

		<div class="col-xs-12">
			<hr
				style="width: 100%; color: red; height: 2px; background-color: red;" />
		</div>

		<div class="panel-body">
			<div class="col-xs-10">


				<form:form commandName="stoneTran" 
					action="/jewels/manufacturing/transactions/stoneTransactions.html"
					cssClass="form-horizontal stoneTranForm">

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>


					<div class="form-group">
						<label for="tranDate" class="col-sm-4 control-label">Date:</label>
						<div class="col-sm-5">
							<form:input path="tranDate" cssClass="form-control" />
							<form:errors path="tranDate" />
						</div>
					</div>

					<div class="form-group">
						<label for="deptLocation" class="col-sm-4 control-label">From
							Location :</label>
						<div class="col-sm-5">
							<form:select path="deptLocation.id" class="form-control"
							onChange="javascript:fillToDepartment();">
								<form:option value="" label="- Select From Location -" />
								<form:options items="${departmentMap}" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="department" class="col-sm-4 control-label">To
							Location :</label>
						<div class="col-sm-5">
							<form:select path="department.id" class="form-control">
								<form:option value="" label="- Select To Location -" />
								<form:options items="${departmentMap}" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="stoneType" class="col-sm-4 control-label">Stone Type :</label>
						<div class="col-sm-5">
							<form:select path="stoneType.id" class="form-control">
								<form:option value="" label="- Select Stone Type -" />
								<form:options items="${stoneTypeMap}" />
							</form:select>
						</div>
					</div>
				
					<div class="form-group">
						<label for="shape" class="col-sm-4 control-label">Shape :</label>
						<div class="col-sm-5">
							<form:select path="shape.id" class="form-control"
							onChange="javascript:popSubShape(this.value);popQuality(this.value);popStoneChart(this.value);">
								<form:option value="" label="- Select Shape -" />
								<form:options items="${shapeMap}" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="subShape" class="col-sm-4 control-label">SubShape :</label>
						<div class="col-sm-5">
							<form:select path="subShape.id" class="form-control">
								<form:option value="" label="- Select SubShape -" />
								<form:options items="${subShapeMap}" />
							</form:select>
						</div>
					</div>
	
					<div class="form-group">
						<label for="quality" class="col-sm-4 control-label">Quality :</label>
						<div class="col-sm-5">
							<form:select path="quality.id" class="form-control">
								<form:option value="" label="- Select Quality -" />
								<form:options items="${qualityMap}" />
							</form:select>
						</div>
					</div>
					
						<div class="form-group">
						<label for="size" class="col-sm-4 control-label">Size :</label>
						<div class="col-sm-5">
							<form:select path="size" class="form-control">
								<form:option value="" label="- Select Size -" />
								<form:options items="${stoneChartMap}" />
							</form:select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="stone" class="col-sm-4 control-label">Stone :</label>
						<div class="col-sm-5">
							<form:input path="stone" cssClass="form-control" autocomplete="off" />
							<form:errors path="stone" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="carat" class="col-sm-4 control-label">Carat :</label>
						<div class="col-sm-5">
							<form:input path="carat" cssClass="form-control" autocomplete="off"/>
							<form:errors path="carat" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="packetNo" class="col-sm-4 control-label">Packet No :</label>
						<div class="col-sm-5">
							<form:input path="packetNo" cssClass="form-control" autocomplete="off"/>
							<form:errors path="packetNo" />
						</div>
					</div>
					
			<%-- 		<div class="form-group">
						<label for="rate" class="col-sm-4 control-label">Rate :</label>
						<div class="col-sm-5">
							<form:input path="rate" cssClass="form-control" autocomplete="off"/>
							<form:errors path="rate" />
						</div>
					</div> --%>
					
						<div class="form-group">
						<label for="remark" class="col-sm-4 control-label">Remarks :</label>
						<div class="col-sm-8">
							<form:textarea path="remark" cssClass="form-control" autocomplete="off"/>
							<form:errors path="remark" />
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" value="Save" class="btn btn-primary" id="stonetranSubmitBtn" />
							<form:input type="hidden" path="id" />
						</div>
					</div>


				</form:form>
			</div>
		</div>
	</div>
	<!-- ending the main panel -->
</div>

<script type="text/javascript">
$(document).ready(
		function(e) {
			$(".stoneTranForm").validate(
					{

						rules : {
							'department.id' : {
								required : true,
							},
							'deptLocation.id' : {
								required : true,

							},

							'stoneType.id' : {
								required : true,

							},
							'shape.id' : {
								required : true,

							},
							'quality.id' : {
								required : true,

							},
							'size' : {
								required : true,

							},
							'stone' : {
								required : true,

							},
							'carat' : {
								required : true,
								greaterThan: '0.0',
								
							},
							'packetNo' : {
								required : true,

							}
						},
						highlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-success').addClass('has-error');
						},
						unhighlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-error').addClass('has-success');
						},
						messages : {
							carat : {
								greaterThan : "This field is required"
							}
						},
					
					});

			$("#tranDate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
			
			$("#tranDate").val('${currentDate}');
			
			if('${canEditTranddate}' === "false"){
				$("#tranDate").attr("disabled","disabled");
			}

			$("#tranDate").val(new Date().toLocaleDateString('en-GB'));
			$("#department\\.id").focus();

		});


function popSubShape(vSel) {

	$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/stoneTransactions/subShape/list.html' />?shapeId='
						+ vSel,
				type : 'GET',
				success : function(data) {
					$("#subShape\\.id").html(data);
				}
			});
}

function popQuality(vSel) {

	$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/stoneTransactions/quality/list.html' />?shapeId='
						+ vSel,
				type : 'GET',
				success : function(data) {
					$("#quality\\.id").html(data);
				}
			});
}

function popStoneChart(vSel) {

	$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/stoneTransactions/stoneChart/list.html' />?shapeId='
						+ vSel,
				type : 'GET',
				success : function(data) {
					$("#size").html(data);
				}
			});
}

function fillToDepartment() {
	$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/stoneTransactions/toDept.html' />?deptId='
						+ $('#deptLocation\\.id').val(),
				type : 'GET',
				success : function(data) {
					$('#department\\.id').html(data);
				}
			});
}




$(document).on('submit','form#stoneTran' , function(e) {
	

	var postData = $(this).serializeArray();
	var formURL = $(this).attr("action"); 
	
	 $.ajax({
			
			
			url : formURL,
			type : "POST",
			data : postData,
			
			success : function(data, textStatus, jqXHR) {
							
				if(data == '1'){
				disMess(event,this);
					
				}else{
					displayMsg(this, null, data);
					
					
				}
			},error : function(jqXHR, textStatus, errorThrown) {
			}
		

		});
		e.preventDefault();

});


function disMess(e,id){
	
	displayConfirmation(
			e,
			'javascript:popReload();',
			'NOTIFICATION',
			'DATA TRANSFERED SUCESSFULLY!',
			'OK');
}

function popReload(){
	window.location.href = "/jewels/manufacturing/transactions/stoneTransactions.html";
}


</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>