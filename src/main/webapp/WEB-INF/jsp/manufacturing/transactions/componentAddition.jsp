<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Component Addition</span>
	</div>
	

	<div class="panel-body">
		<div class="col-xs-10">

			<form:form commandName="tranMt"
				action="/jewels/manufacturing/transactions/componentAddition/add.html"
				cssClass="form-horizontal tranMtForm">

				
				<div class="row">
					<div class="col-xs-12">
						
					
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Bag
								No </label>
						</div>
						
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Tran Date
								</label>
						</div>
						
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Order No. </label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Order Date
								</label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Party
								</label>
						</div>
						
						
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						
						<div class="col-sm-2">
							<form:input path="bagMt.name" cssClass="form-control"
								onchange="javascript:popDetails();pendingForApprovalPopup();" />
							<form:errors path="bagMt.name" />
						</div>
						
						<div class="col-sm-2">
							<form:input path="trandate" cssClass="form-control"/>
								<form:errors path="trandate" />
						</div>
						
						<div class="col-sm-2">
							<form:input path="orderMt.invNo" cssClass="form-control"
								disabled="true" />
							<form:errors path="orderMt.invNo" />
						</div>
						<div class="col-sm-2">
							<form:input path="orderMt.invDate" cssClass="form-control"	disabled="true" />
							<form:errors path="orderMt.invDate" />
						</div>
						<div class="col-sm-2">
							<form:input path="orderMt.party.name" cssClass="form-control"
								disabled="true" />
							<form:errors path="orderMt.party.name" />
						</div>
						
						
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				<div class="row">
					<div class="col-xs-12">

						<!-- <div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Purity
								:</label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Color
								:</label>
						</div> -->
						
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Order
								Type </label>
						</div>
						
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Style
								No </label>
						</div>
						
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Gross
								Wt </label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Qty
								</label>
						</div>
						<div class="col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right" >Present Department</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<%-- <div class="col-sm-2">
							<form:input path="orderDt.purity.name" cssClass="form-control"
								disabled="true" />
							<form:errors path="orderDt.purity.name" />
						</div>
						<div class="col-sm-2">
							<form:input path="orderDt.color.name" cssClass="form-control"
								disabled="true" />
							<form:errors path="orderDt.color.name" />
						</div> --%>
						
						<div class="col-sm-2">
							<form:input path="orderMt.orderType.name" cssClass="form-control"
								disabled="true" />
							<form:errors path="orderMt.orderType.name" />
						</div>
						
						<div class="col-sm-2">
							<form:input path="orderDt.design.styleNo" cssClass="form-control"
								disabled="true" />
							<form:errors path="orderDt.design.styleNo" />
						</div>
						
						<div class="col-sm-2">
							<form:input path="recWt" cssClass="form-control" disabled="true" />
							<form:errors path="recWt" />
						</div>
						<div class="col-sm-2">
							<form:input path="pcs" cssClass="form-control" disabled="true" />
							<form:errors path="pcs" />
						</div>
						<div class="col-sm-2" >
							<form:input path="department.name" cssClass="form-control" style="background-color: white;color:red;font-weight: bold;" disabled="true" />
							<form:errors path="department.name" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

			</form:form>
		</div>

<!-- 		<div class="col-xs-2 center-block">
			<div class="panel panel-primary" style="width: 193px; height: 150px">
				<div class="panel-body">
					<div style="width: 160px; height: 142px">
						<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png"
							data-lighter> <img id="ordImgId" class="img-responsive"
							src="/jewels/uploads/manufacturing/blank.png" />
						</a>
					</div>
				</div>
			</div>
		</div> -->
		
		
			<div class="panel col-sm-2" style="height: 175px">
				<div class="panel-body">
				
				<div class="panel panel-default">
					<a id="oImgHRId" href="" data-lighter> <img id="ordImgId"
					class="img-responsive" style="height: 150px" 
					src="/jewels/uploads/manufacturing/blank.png" />
				</a>
				</div>
				</div>

			
			</div>


	</div>
</div>
<!-- ending the main panel -->


<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading" >
		<span style="font-size: 18px;">Detail</span>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div id="toolbar" style="display: block">
					<a class="btn btn-info" style="font-size: 15px" type="button"
						onclick="javascript:goToCompAdditionEntry(1);"> <span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Findings
					</a>
					
					<a class="btn btn-info" style="font-size: 15px" type="button"
						onclick="javascript:goToCompAdditionEntry(0);"> <span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Accessories
					</a>
				</div>
				<div>
					<table id="compAddTableId" data-toggle="table"
						data-toolbar="#toolbar" data-pagination="false"
						data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
						data-striped="true">
						<thead>
							<tr class="btn-primary">
								<th data-field="department" data-sortable="true">Department</th> 
								<th data-field="component" data-sortable="true">Component</th>
								<th data-field="partNm" data-sortable="true">Part No</th>
								<th data-field="purity" data-sortable="true">purity</th>
								<th data-field="color" data-sortable="true">Color</th>
								<th data-field="metalWt" data-sortable="true">MetalWt</th>
								<th data-field="qty" data-sortable="true">Qty</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="cAdditionEntry" style="display: none">
		<div id="rowId">
			<%-- <div class="form-group">
				<div class="form-group">
					<form:form commandName="compTran" id="compAdditionEntry"
						action="/jewels/manufacturing/transactions/componentAddition/add.html"
						cssClass="form-horizontal compAdditionEntryForm">

						<table class="line-items editable table table-bordered">
							<thead class="panel-heading">
								<tr class="btn-warning" class="panel-heading">
								
								<th id="partNmThId" style="display:  none;">Part Name</th>
									
									<th>Location</th>
									<th>Component</th>
									<th>Purity</th>
									<th>Color</th>
									<th>Weight</th>
									<th>Qty</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td id="partNmTdId" style="display: none;">
										<div id="partNmId" >
											<form:select path="partNm.id" class="form-control" onChange="javascript:getPartData();">
												<form:option value="" label="- Select Part -" />
												<form:options items="${partNmMap}" />
											</form:select>
										</div>
									</td>
								
									<td>
										<div id="locationId">
											<form:select path="department.id" class="form-control">
												<form:option value="" label="- Select Location -" />
												<form:options items="${departmentMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="componentId">
											<form:select path="component.id" class="form-control">
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="purityId">
											<form:select path="purity.id" class="form-control">
												<form:option value="" label="- Select Purity -" />
												<form:options items="${purityMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="colorId">
											<form:select path="color.id" class="form-control">
												<form:option value="" label="- Select Color -" />
												<form:options items="${colorMap}" />
											</form:select>
										</div>
									</td>
									<td><form:input path="metalWt" cssClass="form-control" autocomplete="off"
											onChange="javascript:getStock()" />
									</td>
									<td><form:input path="pcs" id="compAddPcs" cssClass="form-control" autocomplete="off" /></td>
								</tr>
								<tr>
									<td colspan="10">
										<input type="submit" value="Save" class="btn btn-primary" id="compAddBtn" />
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popCompAddCancelBtn()">
										<form:input type="hidden" path="id" /> 
										<input type="hidden" name="vBagNo" id="vBagNo" />
										<input type="hidden" name="vQty" id="vQty" />
										<input type="hidden" name="vPurityId" id="vPurityId" />
										<input type="hidden" name="vColorId" id="vColorId" />
										<input type="hidden" name="vPresentDept" id="vPresentDept" />
										<input type="hidden" id="findingFlg" name="findingFlg" />
										<input type="hidden" id="vTranDate" name="vTranDate" /></td>
										
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div> --%>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(document)
			.ready(
					function(e) {

	$('select').chosen();
						
						$.validator.setDefaults({ ignore: ":hidden:not(select)" });
					
											
						if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}
						
						


						
						$(".tranMtForm")
								.validate(
										{
											rules : {
												'bagMt.name' : {
													remote : {
														url : "<spring:url value='/manufacturing/transactions/componentAddition/bagNoAvailable.html' />",
														type : "get",
														data : {

														}
													}
												}
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
												'bagMt.name' : {
													remote : "Bag not found on floor"
												}
											}

										});

						$('#bagMt\\.name').focus();

						$("#bagMt\\.name")
								.autocomplete(
										{
											source : "<spring:url value='/manufacturing/transactions/compAdd/list.html' />",
											minLength : 2
										
										});
						
						
						$("#trandate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
						$("#trandate").val('${currentDate}');
						
						if('${canEditTranddate}' === "false"){
							$("#trandate").attr("disabled","disabled");
						}
						

					

						
					
					});


				

		

	/* function popDetails() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/componentAddition/displayBreakup.html' />?BagNo='
							+ $('#bagMt\\.name').val(),
					type : 'GET',
					success : function(data) {
						//alert(data);

						if (Number(data) != -1) {
							var temp = data.split("#");
							$('#orderMt\\.invNo').val(temp[0]);
							$('#orderMt\\.invDate').val(temp[1]);
							$('#orderMt\\.party\\.name').val(temp[2]);
							$('#orderMt\\.orderType\\.name').val(temp[3]);
							$('#orderDt\\.design\\.styleNo').val(temp[4]);
							$('#orderDt\\.purity\\.name').val(temp[5]);
							$('#orderDt\\.color\\.name').val(temp[6]);
							$('#recWt').val(temp[7]);
							$('#pcs').val(temp[8]);
							$('#department\\.name').val(temp[9]);
							$('#ordImgId').attr('src', temp[10]);
							$('#oImgHRId').attr('href', temp[10]);
						} else {
							//alert("No Such bag present in tranMt");
							$('#orderMt\\.invNo').val('');
							$('#orderMt\\.invDate').val('');
							$('#orderMt\\.party\\.name').val('');
							$('#orderMt\\.orderType\\.name').val('');
							$('#orderDt\\.design\\.styleNo').val('');
							$('#orderDt\\.purity\\.name').val('');
							$('#orderDt\\.color\\.name').val('');
							$('#recWt').val('0.0');
							$('#pcs').val('0.0');
							$('#department\\.name').val('');

						}

						//$('#bagMt\\.name').focus();

					}
				});

	} */

	
function popDetails() {
		
		if(!!$('#bagMt\\.name').val()){
			$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/metalAddition/displayBreakup.html' />?BagNo='
						+ $('#bagMt\\.name').val(),
				type : 'GET',
				success : function(data) {	
					
					if(data.indexOf("error") != '-1'){
						
						$('#orderMt\\.invNo').val('');
						$('#orderMt\\.invDate').val('');
						$('#orderMt\\.party\\.name').val('');
						$('#orderMt\\.orderType\\.name').val('');
						$('#orderDt\\.design\\.styleNo').val('');
					/* 	$('#orderDt\\.purity\\.name').val('');
						$('#orderDt\\.color\\.name').val(''); */
						$('#recWt').val('0.0');
						$('#pcs').val('0.0');
						$('#department\\.name').val('');
						
						displayMsg(this, null, data);
						
						
					}else{
						
						var temp = data.split("#");
						$('#orderMt\\.invNo').val(temp[0]);
						$('#orderMt\\.invDate').val(temp[1]);
						$('#orderMt\\.party\\.name').val(temp[2]);
						$('#orderMt\\.orderType\\.name').val(temp[3]);
						$('#orderDt\\.design\\.styleNo').val(temp[4]);
						/* $('#orderDt\\.purity\\.name').val(temp[5]);
						$('#orderDt\\.color\\.name').val(temp[6]); */
						$('#recWt').val(temp[5]);
						$('#pcs').val(temp[6]);
						$('#department\\.name').val(temp[7]);
						$('#ordImgId').attr('src', temp[8]);
						$('#oImgHRId').attr('href', temp[8]);
						
						popCompAdditionTable()
						
						
					}
					
					/* if(Number(data) != -1){			
						
						
						
					}else{
						//alert("No Such bag present in tranMt");
					
						
					} */
					
					//$('#bagMt\\.name').focus();

				}
			});
		}

		

	}
	
	
	$(document)
			.on(
					'submit',
					'form#compAdditionEntry',
					function(e) {
						
						$('#vPurityId').val($('#purity\\.id').val());
						$('#vColorId').val($('#color\\.id').val());
						$('#compAddBtn').attr('disabled', 'disabled');
						$('#vBagNo').val($('#bagMt\\.name').val());
						$('#vQty').val($('#compAddPcs').val());
						$('#vPresentDept').val($('#department\\.name').val());
						$('#vTranDate').val($('#trandate').val());
						
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
		
										if (data !== '1') {
											displayMsg(event, this,data);
										} else {
											
											$('#bagMt\\.name').trigger('onchange');
											popCompAdditionTable();
											
											$('form#compAdditionEntry input[type="text"],texatrea, select').val('');
											
											$('form#compAdditionEntry select').val('').trigger('chosen:updated');
											
											
											$('#metalWt').val('0.0');
											$('#compAddPcs').val('0');
											
										}
										
										
										$('#compAddBtn').removeAttr('disabled');

									},

									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						
						e.preventDefault(); //STOP default action
					
					});
	
	

	function getStock() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/componentAddition/stock.html' />?metalWt='
							+ $('#metalWt').val()
							+ "&locationId="
							+ $('#department\\.id').val()
							+ "&purityId="
							+ $('#purity\\.id').val()
							+ "&colorId="
							+ $('#color\\.id').val()
							+ "&componentId="
							+ $('#component\\.id').val(),
					type : 'GET',
					success : function(data) {

						var vMetalWt = $('#metalWt').val()

						if (data != -1) {
							if (Number(data) < Number(vMetalWt)) {
								displayMsg(event, this,'Stock Not found');
							}

						} else {
							displayMsg(event, this,'Stock Not found');
						}

		

					}
				});
	}
	

	function popCompAdditionTable() {

		$("#compAddTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/componentAddition/listing.html?BagNo="
									+ $('#bagMt\\.name').val()
						});
	}
	

	function goToCompAdditionEntry(tranId) {

		console.log('tranId    '+tranId);
		if (!$('#bagMt\\.name').val()) {
			displayMsg(this, null, 'please select the BagNo!');
		} else {
			if ($('#bagMt\\.name').val() != null
					&& !$('#orderMt\\.invNo').val()) {
				displayMsg(this, null, 'please select the right bag!');
			} else {


			

				$.ajax({
					url : "/jewels/manufacturing/transactions/componentAdditionDt/edit/"+ 0 + ".html",
					type : 'GET',
					success : function(data) {
						$("#cAdditionEntry").css('display', 'block');
						$("#rowId").html(data);


						
						if (tranId === 0) {

			/* 	$('#partNmThId').css('display', 'block');
				$('#partNmTdId').css('display', 'block'); */

				
				
				$('#findingFlg').val('false');
				getPartDetail();
				getChargeableList(false);
				
			} else if (tranId === 1) {


				
				$('#partNmThId').css('display', 'none');
				$('#partNmTdId').css('display', 'none');
				
				$('#findingFlg').val('true');
				getChargeableList(true);

			} 

		$("#partNm\\.id").focus();
			/* fillComboBox(); */



						
						$("#partNm\\.id").focus();


						
						
						
						$('html, body').animate({
							scrollTop : $("#cAdditionEntry").offset().top
						}, 50);
					}
				});




				
				
			}
		}

	}


/* 	function fillComboBox() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/compAddition/fillCombo.html' />?purity='
							+ $('#orderDt\\.purity\\.name').val()
							+ "&color="
							+ $('#orderDt\\.color\\.name').val(),
					type : 'GET',
					success : function(data) {
						//alert(data);
						var temp = data.split("_");
						$('#purity\\.id').val(temp[1]);
						$('#color\\.id').val(temp[2]);

					}
				});

	} */

	function popCompAddCancelBtn() {
		$('#cAdditionEntry').css('display', 'none');
	}

	
	function getPartDetail() {
		$

				.ajax({
					url : '<spring:url value='/manufacturing/transactions/tranMetalPart/listDropDown.html' />?BagNo='
							+ $('#bagMt\\.name').val(),
					type : 'GET',
					success : function(data) {

						$("#partNm\\.id").html(data);
						$("#partNm\\.id").trigger("chosen:updated");

					}
				});
		$("#purity\\.name").val('');
		$("#color\\.name").val('');
	}

	function getPartData() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/tranMetalPart/detail.html' />?BagNo='
							+ $('#bagMt\\.name').val()
							+ '&partNmId='
							+ $('#partNm\\.id').val(),
					type : 'GET',
					success : function(data) {

					
						$.each(JSON.parse(data), function(key, value) {
							$('#'+key).val(value);

						});

						$("#purity\\.id").trigger("chosen:updated");
						$("#color\\.id").trigger("chosen:updated");

					}
				});

	}



	function getChargeableList(chList){

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/compAddition/chargeableList.html' />?chargeable='
							+ chList,
					type : 'GET',
					success : function(data) {

						
						$("#component\\.id").html(data);

						$("#component\\.id").trigger("chosen:updated");
					}
				});

	}


	function pendingForApprovalPopup(){
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/transfer/pendingForApprovalPopup.html?bagNo="+$('#bagMt\\.name').val(),
			type:'GET',
			success : function(data, textStatus, jqXHR) {

				if(data != "false"){
					$('#toolbar').css("display","none");
					displayMsg(event, this, data);
				
					}else{
						$('#toolbar').css("display","block");
						}
			}
			
		});
		
	}
	
	

</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>