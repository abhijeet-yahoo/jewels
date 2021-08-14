<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Casting Tree Details</span>
	</div>
	
	<div class = "panel panel-body">

	<form:form commandName="castingMt"
		action="/jewels/manufacturing/transactions/castingMt/add.html"
		cssClass="form-horizontal castingMtForm">

		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Tree ${action}
				successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-3 col-sm-3" >
					<label for="inputLabel3" class=".col-lg-2 text-right">Date
						:</label>
				</div>
				<div class="col-lg-3 col-sm-3" >
					<label for="inputLabel3" class=".col-lg-2 text-right">Tree
						No :</label>
				</div>
				<div class="col-lg-3 col-sm-3" >
					<label for="inputLabel3" class=".col-lg-2 text-right">Purity
						:</label>
				</div>
				<div class="col-lg-3 col-sm-3" >
					<label for="inputLabel3" class=".col-lg-2 text-right">Color
						:</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-3 col-sm-3" >
					<form:input path="cDate" cssClass="form-control" />
					<form:errors path="cDate" />
				</div>
				<div class="col-lg-3 col-sm-3" >
					<form:input path="treeNo" cssClass="form-control" />
					<form:errors path="treeNo" />
				</div>
				<div class="col-lg-3 col-sm-3" >
					<form:select path="purity.id" class="form-control" onchange="javascript:getWaxConversion()" >
						<form:option value="" label=" Select Purity " />
						<form:options items="${purityMap}" />
					</form:select>
				</div>
				<div class="col-lg-3 col-sm-3" >
					<form:select path="color.id" class="form-control">
						<form:option value="" label=" Select Color " />
						<form:options items="${colorMap}" />
					</form:select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-xs-6">
					<div class="panel panel-primary"><!--  start of left panel -->
						<div class="panel-body" style="color:">

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-12" >
										<label for="inputLabel3" style="color: blue;"
											class=".col-lg-2 text-right">WAXTREE & REQ. WT
											INFORMATION </label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Total
											Wax Wt :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Base
											No :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Base
											Wt :</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<form:input path="waxWt" cssClass="form-control"
											onchange="javascript:getWaxTreeWtTemp()" />
										<form:errors path="waxWt" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:select path="baseNo" class="form-control"
											onchange="javascript:getBaseNo(this.value);getBaseWt(this.value)">
											<form:option value="" label="Select BaseNo(Wt)" />
											<form:options items="${baseMtMap}" />
										</form:select>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="baseWt" cssClass="form-control" />
										<form:errors path="baseWt" />
									</div>
								</div>
							</div>
							
								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>
							
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Wax
											Tree Wt :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Total
											Stone Wt :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Req
											Wt :</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<form:input path="waxTreeWt" cssClass="form-control" onchange="javascript:getReqWt()" />
										<form:errors path="waxTreeWt" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="stoneWt" cssClass="form-control"
											onchange="javascript:ChangeTreeWaxWt();getReqWt()" />
										<form:errors path="stoneWt" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="reqWt" cssClass="form-control" disabled="true" />
										<form:errors path="reqWt" />
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Metal Iss
											Wt :</label>
									</div>
									<!-- <div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Used Iss
											Wt :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Total Iss
											Wt :</label>
									</div> -->
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<form:input path="freshIssWt" cssClass="form-control" onchange="javascript:calCastLoss()"/>
										<form:errors path="freshIssWt" />
									</div>
									<%-- <div class="col-lg-4 col-sm-4" >
										<form:input path="usedIssWt" cssClass="form-control"
											onblur="javascript:setTotIssWt();" />
										<form:errors path="usedIssWt" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="totIssWt" cssClass="form-control" />
										<form:errors path="totIssWt" />
									</div> --%>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
						</div>
					</div>
				</div>
				<!-- end of left panel -->

				<div class="col-xs-6">
					<div class="panel panel-primary">	<!--  start of right panel -->
						<div class="panel-body">

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-12" >
										<label for="inputLabel3" style="color: blue;  "
											class=".col-lg-12 text-right">CASTING RECEIVED
											INFORMATION  </label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Casted
											Tree Wt :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Fallen
											Stone :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Fallen
											Stone Wt :</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<form:input path="treeWt" cssClass="form-control"
											onchange="javascript:calCastLoss()" autocomplete="off"/>
										<form:errors path="treeWt" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="fallenStone" cssClass="form-control" autocomplete="off" />
										<form:errors path="fallenStone" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="fallenStnWt" cssClass="form-control"
											onchange="javascript:calCastLoss()" autocomplete="off"/>
										<form:errors path="fallenStnWt" />
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Cast
											Loss :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">PcsWt
											& CompWt :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Stub
											Wt :</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<form:input path="castingLoss" cssClass="form-control" readonly="true"/>
										<form:errors path="castingLoss" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="pcsWt" cssClass="form-control" onchange="javascript:calCutLoss()" autocomplete="off"/>
										<form:errors path="pcsWt" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="stubWt" cssClass="form-control" onchange="javascript:calCutLoss()" autocomplete="off"/>
										<form:errors path="stubWt" />
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Cut
											Loss :</label>
									</div>
									<div class="col-lg-4 col-sm-4" >
										<label for="inputLabel4" class=".col-lg-2 text-right">Scrap
											Wt :</label>
									</div>

								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-4 col-sm-4" >
										<form:input path="cuttingLoss" cssClass="form-control" readonly="true"/>
										<form:errors path="cuttingLoss" />
									</div>
									<div class="col-lg-4 col-sm-4" >
										<form:input path="scrapWt" cssClass="form-control" />
										<form:errors path="scrapWt" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
						</div>
					</div> <!--  end of right panel -->
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-7">
				<input type="submit" value="Save" class="btn btn-primary"  id="castingMtSubmitBtn" /> <a
					class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/transactions/castingMt.html">Listing</a>
					<input type="button" value="Add Bag" class="btn btn-default"
									style="width: 100px;" onclick="javascript:popAddBag()">
				<form:input type="hidden" path="id" />
				<input type="hidden" id="waxConv" name="waxConv" /> 
				<input type="hidden" id="prevIssWt" name="prevIssWt" /> 
				<!-- <input type="hidden" id="prevUsedIssWt" name="prevUsedIssWt" /> --> 
				<input type="hidden" id="prevPcsWt" name="prevPcsWt" />
				<input type="hidden" id="freshIssWt" name="freshIssWt" />
				<input type="hidden" id="usedIssWt" name="usedIssWt" />
				<input type="hidden" id="pcsWt" name="pcsWt" />
				<input type="hidden" id="vReqWt" name="vReqWt" />
				<input type="hidden" id="prevKt" name="prevKt" />
				<input type="hidden" id="prevColor" name="prevColor" />
				
				<form:input type="hidden" path="uniqueId" />
			</div>
		</div>
	</form:form>
	
	
	
	</div>
</div>


<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						$(".castingMtForm")
								.validate(
										{
											rules : {
												treeNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/castingMtAvailable.html' />",
														type : "get",
														data : {
															cDate : function() {
																return $(
																		"#cDate")
																		.val();
															},
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},

												cDate : {
													required : true,
												},
												'purity.id' : {
													required : true,
												},
												'color.id' : {
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
											messages : {
												treeNo : {
													remote : "TreeNo already exists on the selected date"
												}
											}
										});

						$("#cDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						if (window.location.href.indexOf('edit') != -1) {
							var prev = $('#freshIssWt').val();
							$('#prevIssWt').val(prev);
							
							/* var uPrev = $('#usedIssWt').val();
							$('#prevUsedIssWt').val(uPrev); */
							
							var prevPcs = $('#pcsWt').val();
							$('#prevPcsWt').val(prevPcs);
							
							var tempKt = $('#purity\\.id').val();
							$('#prevKt').val(tempKt);
							
							var tempColor = $('#color\\.id').val();
							$('#prevColor').val(tempColor);
							
							
							
							getWaxConversion();
						}else{
							$("#cDate").val(new Date().toLocaleDateString('en-GB'));
							$("#treeNo").focus();
						}

					});

	function getWaxConversion() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/casting/purity/list.html' />?purityId='
							+ $('#purity\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#waxConv").val(data);
					}
				});
	}

	function getBaseNo(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/casting/baseNo/list.html' />?baseId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#baseNo").val(data);
					}
				});
	}

	function getBaseWt(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/casting/baseWt/list.html' />?baseId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						var baseWtt = data;
						$("#baseWt").val(baseWtt);
						var totWaxWt = $('#waxWt').val();
						var waxTreWt = totWaxWt - baseWtt;
						$("#waxTreeWt").val(waxTreWt.toFixed(3));
						$("#waxTreeWt").trigger('onchange');

					}
				});
	}

	function getWaxTreeWtTemp() {
		var totWaxWtTemp = $('#waxWt').val();
		var baseWtTemp = $("#baseWt").val();
		var waxTreWtTemp = totWaxWtTemp - baseWtTemp;
		$("#waxTreeWt").val(waxTreWtTemp.toFixed(3));
		$("#waxTreeWt").trigger('onchange');
	}

	function ChangeTreeWaxWt() {
		var totStoneWt = $('#stoneWt').val();
		var waxxTreeWt = $('#waxTreeWt').val();

		var calValue = totStoneWt / 5;
		var finalCalValue = waxxTreeWt - calValue;

		$("#waxTreeWt").val(finalCalValue.toFixed(3));
		
	}

	function getReqWt() {
		var waxConversion = $('#waxConv').val();
		var waxTreeWt = $('#waxTreeWt').val();
		var requiredWt = waxConversion * waxTreeWt;
		$('#reqWt').val(requiredWt.toFixed(3));
		$('#vReqWt').val($('#reqWt').val());
	}

	/* function calCastLos() {
		var totStoneWt = $('#stoneWt').val();
		var tempValue1 = totStoneWt / 5;
		var castTreeWt = $('#treeWt').val();
		var tempValue2 = castTreeWt - tempValue1;
		$('#castingLoss').val(tempValue2.toFixed(3));
	}
 */
	function calCastLoss() {
		var totStoneWt = $('#stoneWt').val();
		var tempValue1 = totStoneWt / 5;
		var castTreeWt = $('#treeWt').val();
		var tempValue2 = castTreeWt - tempValue1;
		var fallnStnWt = $('#fallenStnWt').val();
		var tempValue3 = fallnStnWt / 5;
		var tempValue4 = parseFloat(tempValue2) + parseFloat(tempValue3);
		var IssWt = $('#freshIssWt').val();
		if(IssWt<tempValue4){
			$('#castingMtSubmitBtn').attr('disabled', 'disabled');
			displayMsg(event, this,'Tree Wt Is Greater Than Iss Wt');
		}else{
			$('#castingMtSubmitBtn').removeAttr('disabled');
			var castLoss = IssWt - tempValue4;
			$('#castingLoss').val(castLoss.toFixed(3));	
		}
		
	}

	/* function callCutLos() {

		var castTreeWtt = $('#treeWt').val();
		var pcsWt = $('#pcsWt').val();
		var tempVar11 = castTreeWtt - pcsWt;
		$('#cuttingLoss').val(tempVar11.toFixed(3));
	} */

	function calCutLoss() {
		var castTreeWt = $('#treeWt').val();
		var stubWt = $('#stubWt').val();
		var pcsWt = $('#pcsWt').val();
		var tempVar1 = castTreeWt - stubWt - pcsWt;
		$('#cuttingLoss').val(tempVar1.toFixed(3));
	}

	
	/* function setTotIssWt(){
		var vFreshIssWt = $('#freshIssWt').val();
		var vUsedIssWt = $('#usedIssWt').val();
		
		var vTotIssWt = parseFloat(vFreshIssWt) + parseFloat(vUsedIssWt);
		$('#totIssWt').val(vTotIssWt.toFixed(3));
		
	} */

	$(document).on('submit', 'form#castingMt', function(e) {
		
		$('#castingMtSubmitBtn').attr('disabled', 'disabled');
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,

			success : function(data, textStatus, jqXHR) {

				//alert("in success" + data);

				var res = data;

				if (Number(res) == -1) {
					displayMsg(event, this,'Fresh Issue Wt stock not available');
				}else if (Number(res) == -2) {
					displayMsg(event, this,'Used Issue Wt stock not available');
				}else if (Number(res) == -3) {
					displayMsg(event, this,'PcsWt Stock not available');
				}else {
					window.location.href = data;
				}
				
				$('#castingMtSubmitBtn').removeAttr('disabled');

			},
			error : function(jqXHR, textStatus, errorThrown) {
				// alert("in error"+data);
			}

		});
		e.preventDefault();
	});
	
	
	function popAddBag(){
		
	var	vtree = ($('#id').val());
		
		if(vtree) {
			window.location.href = "/jewels/manufacturing/transactions/castingDt.html?vDt"+$('#cDate').val()+"vtree"+vtree;	
		}else{
			displayMsg(this, null, 'RECORD NOT SAVE, PLEASE SAVE RECORD !');
		
		}
	}


	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<!-- <script src="/WEB-INF/jsp/common/modal.jsp"></script> -->

