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
			<span style="font-size: 18px;">Metal Transactions</span>
		</div>

		<div class="col-xs-12">
			<hr
				style="width: 100%; color: red; height: 2px; background-color: red;" />
		</div>

		<div class="panel-body">
			<div class="col-xs-10">


				<form:form commandName="metalTran"
					action="/jewels/manufacturing/transactions/metalTransaction/add.html"
					cssClass="form-horizontal metalTranForm">

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
						<label for="department" class="col-sm-4 control-label">From
							Location :</label>
						<div class="col-sm-5">
							<form:select path="department.id" class="form-control"
								onChange="javascript:fillToDepartment();">
								<form:option value="" label="- Select From Location -" />
								<form:options items="${departmentMap}" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="deptLocation" class="col-sm-4 control-label">To
							Location :</label>
						<div class="col-sm-5">
							<form:select path="deptLocation.id" class="form-control">
								<form:option value="" label="- Select To Location -" />
								<form:options items="${departmentMapp}" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="purity" class="col-sm-4 control-label">KT :</label>
						<div class="col-sm-5">
							<form:select path="purity.id" class="form-control">
								<form:option value="" label="- Select KT -" />
								<form:options items="${purityMap}" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="color" class="col-sm-4 control-label">Color:</label>
						<div class="col-sm-5">
							<form:select path="color.id" class="form-control">
								<form:option value="" label="- Select Color -" />
								<form:options items="${colorMap}" />
							</form:select>
						</div>
					</div>

					<div class="form-group">
						<label for="metalWt" class="col-sm-4 control-label">Metal Wt :</label>
						<div class="col-sm-5">
							<form:input path="metalWt" cssClass="form-control"/>
							<form:errors path="metalWt" />
						</div>
					</div>

					<%-- <div class="form-group">
						<label for="uMetalWt" class="col-sm-4 control-label">Used
							Metal Wt :</label>
						<div class="col-sm-5">
							<form:input path="uMetalWt" cssClass="form-control"
								onblur="javascript:usedMetalStockCheck()" />
							<form:errors path="uMetalWt" />
						</div>
					</div> --%>

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" value="Save" class="btn btn-primary" id="metTranSubmitBtn" />
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
				$(".metalTranForm").validate(
						{

							rules : {
								'department.id' : {
									required : true,
								},
								'deptLocation.id' : {
									required : true,

								},
								'purity.id' : {
									required : true,

								},
								'color.id' : {
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
								name : {
									remote : "Color already exists"
								}
							}
						});

				$("#tranDate").datepicker({
					dateFormat : 'dd/mm/yy'
				});
				
				if('${canEditTranddate}' === "false"){
					$("#tranDate").attr("disabled","disabled");
				}
				
				$("#tranDate").val('${currentDate}');

				$("#tranDate").val(new Date().toLocaleDateString('en-GB'));
				$("#department\\.id").focus();

			});

	function fillToDepartment() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/metalTransaction/toDept.html' />?deptId='
							+ $('#department\\.id').val(),
					type : 'GET',
					success : function(data) {
						$('#deptLocation\\.id').html(data);
					}
				});
	}

	function freshMetalStockCheck() {

		if ($('#metalWt').val() > 0) {
			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/metalTransaction/stock/freshMetalWt.html' />?freshMetalWt='
								+ $('#metalWt').val()
								+ "&purityId="
								+ $('#purity\\.id').val()
								+ "&colorId="
								+ $('#color\\.id').val()
								+ "&locationId="
								+ $('#department\\.id').val(),
						type : 'GET',
						success : function(data) {
							var vPurity = $('#purity\\.id').find('option:selected').text();
							var vColor = $('#color\\.id').find('option:selected').text();

							var vfreshMetalWt = $('#metalWt').val()

							if (Number(data) < Number(vfreshMetalWt)) {
								displayMsg(this, null, '' + vPurity + " and  "+ vColor + " "+ 'METAL STOCK NOT AVAILABLE!');
							}
							if (data == -1) {
								displayMsg(this, null, '' + vPurity + " and "+ vColor + " "+ 'METAL STOCK NOT AVAILABLE!');
							}

						}
					});
		}

	}

	/* function usedMetalStockCheck() {

		if ($('#uMetalWt').val() > 0) {
			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/metalTransaction/stock/usedMetalWt.html' />?usedMetalWt='
								+ $('#uMetalWt').val()
								+ "&purityId="
								+ $('#purity\\.id').val()
								+ "&colorId="
								+ $('#color\\.id').val()
								+ "&locationId="
								+ $('#department\\.id').val(),
						type : 'GET',
						success : function(data) {
							var vPurity = $('#purity\\.id').find(
									'option:selected').text();
							var vColor = $('#color\\.id').find(
									'option:selected').text();

							var vUsedMetalWt = $('#uMetalWt').val()

							if (Number(data) < Number(vUsedMetalWt)) {
								displayMsg(this, null, '' + vPurity + " and "
										+ vColor + " "
										+ 'USED METAL STOCK NOT AVAILABLE!');
							}
							if (data == -1) {
								displayMsg(this, null, '' + vPurity + " and "
										+ vColor + " "
										+ 'USED METAL STOCK NOT AVAILABLE!');
							}

						}
					});
		}

	} */

	
	$(document)
			.on(
					'submit',
					'form#metalTran',
					function(e) {
						
						//metTranSubmitBtn
						
						//$("#metTranSubmitBtn").prop("disabled", true).addClass("disabled");
						
						$('#metTranSubmitBtn').attr('disabled',true);
						
						var vMetalWt = $('#metalWt').val();
						/* var vUMetalWt = $('#uMetalWt').val(); */
						
						if (Number(vMetalWt) <= 0.0) {
							displayMsg(event, this,'MetalWt cannot be empty!');
						} else {

							var postData = $(this).serializeArray();
							var formURL = $(this).attr("action");

							$
									.ajax({
										url : formURL,
										type : "POST",
										data : postData,

										success : function(data, textStatus,
												jqXHR) {
											//alert(data);

											temp = data.split("_");

											var vTemp = "";
											if (temp[0] == 1) {
												disMess(event,this);
											} else {
												if (temp[0] == -1) {
													vTemp = $('#purity\\.id')
															.find('option:selected').text()
															+ "-"
															+ $('#color\\.id').find('option:selected').text()
															+ " "
															+ "MetalWt Stock Not Available\n";
												} 

												if (temp[1] == -1) {
													vTemp = vTemp
															+ ","
															+ $('#purity\\.id').find('option:selected').text()
															+ "-"
															+ $('#color\\.id').find('option:selected').text()
															+ " "
															+ " MetalWt Stock Not Available\n";
												} 

												if (vTemp.length > 0) {
													displayMsg(this, null,vTemp + " " + '');
												}
											}
											
											$('#metTranSubmitBtn').removeAttr('disabled','disabled');


										},
										error : function(jqXHR, textStatus,
												errorThrown) {

										}

									});
							e.preventDefault();

						}
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
		window.location.href = "/jewels/manufacturing/transactions/metalTransaction.html";
	}
	
	
	
	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>