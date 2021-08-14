<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Bag Split</span>
	</div>
	
	
	
	<div class="panel-body">
		<div class="form-group">
			<form:form commandName="tranMt"
				action="<spring:url value='/manufacturing/transactions/transfer/add.html' />"
				cssClass="form-horizontal transferForm">

				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 text-right"></label> <label
						class="col-sm-2 text-right">Department:</label>
					<div class="col-sm-2">
						<form:select path="deptFrom.id" id="deptFroom"
							class="form-control" onChange="javascript:popDeptTo();">
							<form:option value="" label=" Select department " />
							<form:options items="${departmenttMap}" />
						</form:select>
					</div>
					<label class="col-sm-2 text-right">Bag No:</label>
					<div class="col-sm-2">
						<form:input path="bagMt.name" cssClass="form-control" onblur="javascript:popValidation();" />
						<form:errors path="bagMt.name" />
					</div>
				</div>


				<div class="col-xs-12">
					<hr
						style="width: 100%; color: red; height: 3px; background-color: darkblue;" />
				</div>


				<div class="col-xs-10">
					<!-- starting the first sepration div -->

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Party
									:</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Order
									No :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Order
									Date :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Order
									Type :</label>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderMt.party.name" cssClass="form-control"
									disabled="true" />
								<form:errors path="orderMt.party.name" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderMt.invNo" cssClass="form-control"
									disabled="true" />
								<form:errors path="orderMt.invNo" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderMt.invDate" cssClass="form-control"
									disabled="true" />
								<form:errors path="orderMt.invDate" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderMt.orderType.name"
									cssClass="form-control" disabled="true" />
								<form:errors path="orderMt.orderType.name" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Delivery
									Date :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Style
									No :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Purity
									:</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Color
									:</label>
							</div>

						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderMt.dispatchDate" cssClass="form-control"
									disabled="true" />
								<form:errors path="orderMt.dispatchDate" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderDt.design.styleNo" id="styleNoId"
									cssClass="form-control" disabled="true" />
								<form:errors path="orderDt.design.styleNo" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderDt.purity.name" id="purityNoId"
									cssClass="form-control" disabled="true" />
								<form:errors path="orderDt.purity.name" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="orderDt.color.name" id="colorNoId"
									cssClass="form-control" disabled="true" />
								<form:errors path="orderDt.color.name" />
							</div>

						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>


					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Qty
									:</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Rec
									Wt :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Iss
									Wt :</label>
							</div>
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Loss
									:</label>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<div class="col-lg-3 col-sm-3">
								<form:input path="pcs" cssClass="form-control" disabled="true" />
								<form:errors path="pcs" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="recWt" cssClass="form-control" disabled="true" />
								<form:errors path="recWt" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="issWt" cssClass="form-control"
									onChange="javascript:getLoss()" />
								<form:errors path="issWt" />
							</div>
							<div class="col-lg-3 col-sm-3">
								<form:input path="loss" cssClass="form-control" />
								<form:errors path="loss" />
							</div>
						</div>
					</div>
				</div>
				<!-- <ending the first 10 div> -->

				<div class="col-xs-2  center-block">
					<div class="panel panel-primary"
						style="width: 193px; height: 210px">
						<div class="panel-body">
							<div style="width: 160px; height: 142px">
								<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png"
									data-lighter> <img id="ordImgId" class="img-responsive"
									src="/jewels/uploads/manufacturing/blank.png" />
								</a>
							</div>
						</div>
					</div>
				</div>


			</form:form>
		</div>
	
	
	
	</div>
</div> <!-- ending the main panel -->



<script type="text/javascript">



	$(document).ready(function(e){
		
		
	$(".transferForm")
		.validate(
				{
					rules : {
						'deptFrom.id' : {
							required : true,

						},
					},
					highlight : function(element) {
						$(element).closest(
								'.form-group')
								.removeClass(
										'has-success')
								.addClass('has-error');
					},
					unhighlight : function(element) {
						$(element)
								.closest('.form-group')
								.removeClass(
										'has-error')
								.addClass('has-success');
					},

			});
		
		
		
		
		

	$("#bagMt\\.name")
			.autocomplete(
					{
						source : "<spring:url value='/manufacturing/transactions/splitQty/list.html' />",
						minLength : 2
					});
	
	});


	function popDeptTo() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/splitQty/deptTo.html' />?departmentId='
							+ $('#deptFroom').val(),
					type : 'GET',
					success : function(data) {
						$("#deptToo").html(data);
						$('#bagMt\\.name').val('');
					}
				});
	}
	
	
	
	
	function popValidation(){
		
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/splitQty/validateTranDtList.html' />?departmentId='
					+ $('#deptFroom').val()
					+"&bagName="+$('#bagMt\\.name').val(),
			type : 'GET',
			success : function(data) {
				
				if(data === '-2'){
					displayInfoMsg(event, this,'Bag Not Found On FLoor!');
				}else if(data === '-3'){
					displayMsg(event, this,'Return The Diamond!');
				}else{
					
				}
				
				
			}
		});
		
		
		
		
	}
	
	
	
	
	function displayBreakUp() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/splitQty/display.html' />?BagNo='
							+ $('#bagMt\\.name').val()
							+ "&departmentId="
							+ $('#deptFroom').val(),
					type : 'GET',
					success : function(data) {

						if (Number(data) != -1 && Number(data) != -2) {

							var temp = data.split("#");

							$('#orderMt\\.party\\.name').val(temp[0]);
							$('#orderMt\\.invNo').val(temp[1]);
							$('#orderMt\\.invDate').val(temp[2]);
							$('#orderMt\\.orderType\\.name').val(temp[3]);
							$('#orderMt\\.dispatchDate').val(temp[4]);
							$('#styleNoId').val(temp[5]);
							$('#purityNoId').val(temp[6]);
							$('#colorNoId').val(temp[7]);
							$('#pcs').val(temp[8]);
							$('#recWt').val(temp[9]);
							$('#ordImgId').attr('src', temp[10]);
							$('#oImgHRId').attr('href', temp[10]);
							
						} else {

							if (Number(data) == -1) {

								$('#orderMt\\.party\\.name').val('');
								$('#orderMt\\.invNo').val('');
								$('#orderMt\\.invDate').val('');
								$('#orderMt\\.orderType\\.name').val('');
								$('#orderMt\\.dispatchDate').val('');
								$('#styleNoId').val('');
								$('#purityNoId').val('');
								$('#colorNoId').val('');
								$('#pcs').val('0.0');
								$('#recWt').val('0.0');
							} else {
								alert("please select the department :-) ");
							}

						}

					}

				});

	}
	
	
	
	
	
	
	
</script>




<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />














