<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Making Issue</span>
	</div>

	<div class="panel-body">
		<div class="form-group">
			<form:form commandName="makingMt"
				action="/jewels/manufacturing/transactions/makingMt/add.html"
				cssClass="form-horizontal makingMtForm">

				<c:if test="${param.success eq true}">
					<div class="alert alert-success">Making Added ${action} successfully!</div>
					</c:if>


				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Inv
								No :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Inv
								Date :</label>
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
							<form:input path="invNo" cssClass="form-control" disabled="true" />
							<form:errors path="invNo" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="invDate" cssClass="form-control" />
							<form:errors path="invDate" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:select path="purity.id" class="form-control">
								<form:option value="" label=" Select Purity " />
								<form:options items="${purityMap}" />
							</form:select>
						</div>
						<div class="col-lg-3 col-sm-3">
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
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Metal Wt
								:</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Loss
								:</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">ScrapWt
								:</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">ReturnMetal
								:</label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="freshIssWt" cssClass="form-control"
								onblur="javascript:totalLoss();" />
							<form:errors path="freshIssWt" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="loss" cssClass="form-control" readonly="true" />
							<form:errors path="loss" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="scrapWt" cssClass="form-control" />
							<form:errors path="scrapWt" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="returnMetal" cssClass="form-control" onblur="javascript:totalLoss();"/>
							<form:errors path="returnMetal" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
					
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel3" class=".col-lg-2 text-right">Remark
								:</label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
					
						<div class="col-lg-6 col-sm-6">
							<form:input path="remark" cssClass="form-control" />
							<form:errors path="remark" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="form-group" align="center">
					<div class="col-sm-offset-1 col-sm-9">
						<input type="submit" value="Save" id="makingMtSubmitBtn" class="btn btn-primary" /> <a
							class="btn btn-info" style="font-size: 15px" type="button"
							href="/jewels/manufacturing/transactions/makingMt.html">Making Listing</a>
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="uniqueId" />
						<form:input type="hidden" path="invNo" />
						<input type="hidden" name="prevFreshMetalWt" id="prevFreshMetalWt">
						<!-- <input type="hidden" name="prevUsedMetalWt" id="prevUsedMetalWt"> -->
						<input type="hidden" name="prevKt" id="prevKt">
						<input type="hidden" name="prevColor" id="prevColor">
						<!-- <input type="hidden" name="vTotIssWt" id="vTotIssWt"> -->
						<input type="hidden" name="vLoss" id="vLoss">
						<input type="hidden" name="vTranDate" id="vTranDate" />	
						
					</div>
				</div>


			</form:form>
		</div>



	<div id="dtMakingRec" style="display: none">

		<div class="panel panel-primary">
		
			<div   align="center">
				<span style="font-size: 18px;">Making Receive</span>
			</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="makingRecDtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
							data-striped="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="component" data-sortable="true">Component</th>
									<th data-field="quantity" data-align="left">Quantity</th>
									<th data-field="recWt" data-sortable="true">Weight</th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
			
			
			<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
			
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarxy">
					
						<span style="display:inline-block; width: 0.3cm;"></span>
							<a class="btn btn-info" style="font-size: 15px" type="button" onclick="javascript:goToMakingDtEntry();"> 
								<span class="glyphicon glyphicon-plus"></span>&nbsp;Add
							</a>
							
						<span style="display:inline-block; width: 22cm;"></span>
							<label>Total Qty : &nbsp;</label><input type="text" name="fldQuantity" id="fldQuantity" maxlength="7" size="7" disabled="disabled"/>
							<label>Total Wt : &nbsp;</label><input type="text" name="fldWeight" id="fldWeight"  maxlength="7" size="7" disabled="disabled"/>
						
					</div>
				</div>
			</div>
			
			<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
			
			
			
			
			
		<div id="dtMakingEntry" style="display: none">
			<div id="rowId">
				<div class="form-group">
					<div class="form-group">
						<form:form commandName="makingRecDt" id="makingRecDtEnt"
							action="/jewels/manufacturing/transactions/makingRecDt/add.html"
							cssClass="form-horizontal makingRecDtEntForm">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-warning" class="panel-heading">
										<th>Component</th>
										<th>Quantity</th>
										<th>Weight</th>  
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><form:select path="component.id" class="form-control" >
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</td>
										<td><form:input path="qty" cssClass="form-control" /></td>
										<td><form:input path="recWt" cssClass="form-control" onchange="javascript:validateLoss()"/></td>
									</tr>
								
									<tr id="saveBtnId" style="display: block">
										<td colspan="10">
										<input type="submit" value="Save" id="makingReceiveSubmitBtn" class="btn btn-primary" />
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMakingCancelBtn()">
											<form:input type="hidden" path="id" id="makRecDtId"/> 
											<form:input type="hidden" path="uniqueId" /> 
											<input type="hidden" id="pInvNo" name="pInvNo" />
											<input type="hidden" id="changedLoss" name="changedLoss" />
											<input type="hidden" id="changedIssueWt" name="changedIssueWt" />
											<input type="hidden" id="changeRetMetWt" name="changeRetMetWt" />
										</td>
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	




		</div> <!-- ending the  inside panel -->

		
</div>

		



	</div> <!-- ending the panel body -->
</div>
<!-- ending the main panel -->


<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						
						$(".makingMtForm")
								.validate(
										{

											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/makingMt/invoiceNoAvailable.html' />",
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
												
												freshIssWt : {
													required : true,
													greaterThan : "0.0",
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
												invNo : {
													remote : "Invoice No already exists"
												},
												freshIssWt : {
													greaterThan : "This field is required"
												}
											}

										});
						
						
						
						
						
				 $(".makingRecDtEntForm")
							
						.validate(
								{
									rules : {
										
										'component.id' : {
											required : true,
										},
										
										recWt : {
											required : true,
											greaterThan : "0.0",
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
										
										recWt : {
											greaterThan : "This field is required"
										}
									}

								});
						
				
						
						

					$("#invDate").datepicker({
						dateFormat : 'dd/mm/yy'
					});
					
					if('${canEditTranddate}' === "false"){
						$("#vTranDate").val('${currentDate}');
						$("#invDate").attr("disabled","disabled");
					}

						if (window.location.href.indexOf('edit') != -1) {

							if('${canEditTranddate}' === "false"){
								$("#vTranDate").val($("#invDate").val());
									
								}
							
							$("#dtMakingRec").css('display', 'block');

							var prevFreshMetal = $('#freshIssWt').val();
							$('#prevFreshMetalWt').val(prevFreshMetal);

							/* var prevUsedMetal = $('#usedIssWt').val();
							$('#prevUsedMetalWt').val(prevUsedMetal); */
							
							var tempKt = $('#purity\\.id').val();
							$('#prevKt').val(tempKt);
							
							var tempColor = $('#color\\.id').val();
							$('#prevColor').val(tempColor);
							
							popMakingRecDt();
							
							

						} else {
							$("#invDate").val('${currentDate}');
							
							$("#invDate").val(
									new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						//popMetalInwardDt();
						
						
						
						
						
						
						
						
						

					});
	
	
	
/* 
	function calTotIssWt() {
		var vFreshWt = $('#freshIssWt').val();
		var vUsedWt  = $('#usedIssWt').val();
		
		var total = "";
		if(!!vFreshWt){
			total = parseFloat(vFreshWt) + parseFloat(vUsedWt);
		}else{
			vFreshWt = 0.0;
			total = parseFloat(vFreshWt) + parseFloat(vUsedWt);
		}
		
		//var total = parseFloat($('#freshIssWt').val()) + parseFloat($('#usedIssWt').val());
		
		$('#totIssWt').val(total);
		
		totalLoss();
		
	} */
	

	/* function checkFreshIssWtStock() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/making/stock/freshMetalWt.html' />?purityId='
							+ $('#purity\\.id').val()
							+ "&colorId="
							+ $('#color\\.id').val(),
					type : 'GET',
					success : function(data) {
						var vPurity = $('#purity\\.id').find('option:selected').text();
						var vColor = $('#color\\.id').find('option:selected').text();

						var vFreshIssWt = $('#freshIssWt').val()

						if (Number(data) < Number(vFreshIssWt)) {
							displayMsg(this, null, '' + vPurity + " and "
									+ vColor + " " + 'STOCK NOT AVAILABLE!');
						} else if (data == -1) {
							displayMsg(this, null, '' + vPurity + " and "
									+ vColor + " " + 'STOCK NOT AVAILABLE!');
						} else {
							calTotIssWt();
						}

					}
				});
	}
	
	

	function checkUsedIssWtStock() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/making/stock/used/metalWt.html' />?purityId='
							+ $('#purity\\.id').val()
							+ "&colorId="
							+ $('#color\\.id').val(),
					type : 'GET',
					success : function(data) {
						var vPurity = $('#purity\\.id').find('option:selected')
								.text();
						var vColor = $('#color\\.id').find('option:selected')
								.text();

						var vUsedIssWt = $('#usedIssWt').val()

						if (Number(data) < Number(vUsedIssWt)) {
							displayMsg(this, null, '' + vPurity + " and "
									+ vColor + " " + 'STOCK NOT AVAILABLE!');
						} else if (data == -1) {
							displayMsg(this, null, '' + vPurity + " and "
									+ vColor + " " + 'STOCK NOT AVAILABLE!');
						} else {
							calTotIssWt();
						}

					}
				});

		} */
	
	
	

	$(document).on('submit', 'form#makingMt', function(e) {
		
		$('#makingMtSubmitBtn').attr('disabled', 'disabled');
		
		/* $('#vTotIssWt').val($('#totIssWt').val()); */
		$('#vLoss').val($('#loss').val());
		
		
		
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		
		
		

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,

			success : function(data, textStatus, jqXHR) {

				
				
				if(data == -1 ){
					displayMsg(this, null, 'Metal Stock Not Found');
				}else if(data == -3){
					displayMsg(this, null, 'Metal Stock Not Found');
				}else if(data == -5){
					displayMsg(this, null, 'Please check issue-receive entries');
				}else{
					window.location.href = data;
				}
				
				$('#makingMtSubmitBtn').removeAttr('disabled');

			},
			error : function(jqXHR, textStatus, errorThrown) {

			}

		});
		e.preventDefault();
	});
	
	
	

	
	function popMakingRecDt() {

			$("#makingRecDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/makingRecDt/listing.html?pInvNo="
									+ $('#invNo').val()
						});
		}
	
	
	
	
	function goToMakingDtEntry() {
		$("#dtMakingEntry").css('display', 'block');
		
		var vPurity = $('#purity\\.id').val();
		var vColor = $('#color\\.id').val();
		
		$( 'form#makingRecDtEnt input[type="text"],textarea, select').val('');
		$('form#makingRecDtEnt select').val('');
		
		$('#purity\\.id').val(vPurity);
		$('#color\\.id').val(vColor);
		
		$("#qty").val('0');
		$("#recWt").val('0.0');
		
		$("#component\\.id").focus();
		
		$('#makRecDtId').val('');
		
	}
	
	
	
	
	
	$(document)
	.on(
			'submit',
			'form#makingRecDtEnt',
			function(e) {
						
						$('#makingReceiveSubmitBtn').attr('disabled', 'disabled');		
				
						$("#pInvNo").val($("#invNo").val());
						//$("#changedLoss").val($("#loss").val());
						$("#changedIssueWt").val($("#freshIssWt").val());
						$("#changeRetMetWt").val($("#returnMetal").val());
						
						
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");
						
						
		
						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
		
										popMakingRecDt();
										
										//$( 'form#makingRecDtEnt input[type="text"],textarea, select').val('');
										//$('form#makingRecDtEnt select').val('');
										
										$("#component\\.id").val('');
										$("#qty").val('0');
										$("#recWt").val('0.0');
										
										prevWtt=0.0;
										$('#makingReceiveSubmitBtn').removeAttr('disabled');
										
										if (data == -1) {
											window.location.reload(true);
										}
										
		
									},
		
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						
						e.preventDefault(); //STOP default action
				
				
			});
	
	
	
	var prevWtt = 0;
	function editMakingRecDt(dtId) {
		$.ajax({
			url : "/jewels/manufacturing/transactions/makingRecDt/edit/"
					+ dtId + ".html",
			type : 'GET',
			success : function(data) {
				$("#rowId").html(data);
				$("#dtMakingEntry").css('display', 'block');
				$("#component\\.id").focus();
				totalLoss();
				 prevWtt = $('#recWt').val();
				
				
			}
		});
	}
	
	
	
	
	
	function totalLoss(){
		var data = JSON.stringify($("#makingRecDtId").bootstrapTable('getData'));

		var vWeight = 0.0;

		$.each(JSON.parse(data), function(idx, obj) {
			vWeight += Number(obj.recWt);

		});
		
		var totLoss = $('#freshIssWt').val()-$('#returnMetal').val()-vWeight;
		$('#loss').val(" " + parseFloat(totLoss).toFixed(3));
	}
	

	
	$('#makingRecDtId').bootstrapTable({})
	.on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#makingRecDtId").bootstrapTable('getData'));

			var vWeight = 0.0;
			var vQuantity = 0.0;
			
			$.each(JSON.parse(data), function(idx, obj) {
				vWeight += Number(obj.recWt);

			});
			
			$.each(JSON.parse(data), function(idx, obj) {
				vQuantity += Number(obj.quantity);

			});
			
			$('#fldWeight').val(vWeight.toFixed(3));
			$('#fldQuantity').val(vQuantity.toFixed(0));
			
			var totLoss = $('#freshIssWt').val()-$('#returnMetal').val()-vWeight;
			$('#loss').val(" " + parseFloat(totLoss).toFixed(3));
			
		})
	
	
	
	function totalUpdatedLoss(){
			var data = JSON.stringify($("#makingRecDtId").bootstrapTable('getData'));
			var vWeight = 0.0;
			$.each(JSON.parse(data), function(idx, obj) {
				vWeight += Number(obj.recWt);
	
			});
			var totLoss = $('#freshIssWt').val()-$('#returnMetal').val()-vWeight;
			/* var tempRecWt = (parseFloat(totLoss) + parseFloat(prevWtt)) - $('#recWt').val(); */ 
			var tempRecWt = (parseFloat(totLoss)+parseFloat(prevWtt)) - $('#recWt').val(); 
			$('#loss').val(" " +parseFloat(tempRecWt).toFixed(3));
			
			//prevWtt = $('#recWt').val();
			
			
			
			
	}
		
		
		
	function validateLoss(){
		
		console.log('loss  '+parseFloat($('#loss').val()));
		
		console.log('prevWtt  '+parseFloat(prevWtt));
		
		console.log('recWt  '+Number($('#recWt').val()));

		if((parseFloat($('#loss').val()) + parseFloat(prevWtt))<  Number($('#recWt').val())){
			$('#saveBtnId').css('display','none')
			displayMsg(this, null, 'Invalid Entry');
		}else{
		//	totalUpdatedLoss();
			$('#saveBtnId').css('display','block')
		}
		
	}
		
		
		
		
		
		$('#makingRecDtId').bootstrapTable({})
		.on(
				'click-row.bs.table',

				function(e, row, $element) {
					
					$('#dtMakingEntry').css('display','none');
					
				});
		
		
		
		function popMakingCancelBtn(){
			$('#dtMakingEntry').css('display','none');
		}
		
		
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>







