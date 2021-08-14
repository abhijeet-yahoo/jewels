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
			<span style="font-size: 18px;">Component Transactions</span>
		</div>

		<div class="col-xs-12">
			<hr
				style="width: 100%; color: red; height: 2px; background-color: red;" />
		</div>

		<div class="panel-body">
			<div class="col-xs-10">

				<form:form commandName="compTran"
					action="/jewels/manufacturing/transactions/componentTransaction/add.html"
					cssClass="form-horizontal compTranForm">

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="form-group">
						<label for="createdDt" class="col-sm-4 control-label">Date
							:</label>
						<div class="col-sm-5">
							<form:input path="trandate" cssClass="form-control"/>
								<form:errors path="trandate" />
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
						<label for="component" class="col-sm-4 control-label">Component
							:</label>
						<div class="col-sm-5">
							<form:select path="component.id" class="form-control">
								<form:option value="" label="- Select To Location -" />
								<form:options items="${componentMap}" />
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
						<label for="pcs" class="col-sm-4 control-label">Quantity :</label>
						<div class="col-sm-5">
							<form:input path="pcs" cssClass="form-control" />
							<form:errors path="pcs" />
						</div>
					</div>

					<div class="form-group">
						<label for="metalWt" class="col-sm-4 control-label">Metal
							Wt :</label>
						<div class="col-sm-5">
							<form:input path="metalWt" cssClass="form-control" onblur="javascript:metalStockCheck()" />
							<form:errors path="metalWt" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="remark" class="col-sm-4 control-label">Remark :</label>
						<div class="col-sm-5">
							<form:textarea path="remark" cssClass="form-control" />
							<form:errors path="remark" />
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" value="Save" class="btn btn-primary" id="comTranBtnId" />
							<form:input type="hidden" path="id" />
							<input type="hidden" id="vTranDate" name="vTranDate" />
							<!-- <input type="hidden" id="cDate" name="cDate"> -->
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
				$(".compTranForm").validate(
						{

							rules : {
								'department.id' : {
									required : true,
								},
								'deptLocation.id' : {
									required : true,

								},
								'component.id' : {
									required : true,

								},
								'purity.id' : {
									required : true,

								},
								'color.id' : {
									required : true,

								},
								metalWt : {
									required : true,
									greaterThan : "0,0",
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
								metalWt : {
									greaterThan : "This field is required"
								}
							},

						});

				$("#createdDt").datepicker({
					dateFormat : 'dd/mm/yy'
				});

				$("#createdDt").val(new Date().toLocaleDateString('en-GB'));
				$("#department\\.id").focus();
				
				$("#trandate").datepicker({
					dateFormat : 'dd/mm/yy'
				});
				
				$("#trandate").val('${currentDate}');
				
				if('${canEditTranddate}' === "false"){
					$("#trandate").attr("disabled","disabled");
				}
				
				//alert($('#createdDt').val());

			});

	function fillToDepartment() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/componentTransaction/toDept.html' />?deptId='
							+ $('#department\\.id').val(),
					type : 'GET',
					success : function(data) {
						$('#deptLocation\\.id').html(data);
					}
				});
	}
	
	
	function metalStockCheck() {

		if ($('#metalWt').val() > 0) {
			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/componentTransaction/stock/metalWt.html' />?metalWt='
								+ $('#metalWt').val()
								+ "&purityId="
								+ $('#purity\\.id').val()
								+ "&colorId="
								+ $('#color\\.id').val()
								+ "&locationId="
								+ $('#department\\.id').val()
								+ "&componentId="
								+ $('#component\\.id').val(),
						type : 'GET',
						success : function(data) {
							var vPurity = $('#purity\\.id').find('option:selected').text();
							var vColor = $('#color\\.id').find('option:selected').text();
							var vComponent = $('#component\\.id').find('option:selected').text();

							var vfreshMetalWt = $('#metalWt').val()

							if (Number(data) < Number(vfreshMetalWt)) {
								displayMsg(this, null, '' + vPurity + " and "+ vColor + " "+ " and " + vComponent +" "+' METAL STOCK NOT AVAILABLE!');
							}
							if (data == -1) {
								displayMsg(this, null, '' + vPurity + " and "+ vColor + " "+ " and " + vComponent +" "+ ' METAL STOCK NOT AVAILABLE!');
							}

						}
					});
		}

	}
	
	
		$(document)
		.on(
				'submit',
				'form#compTran',
				function(e) {
				
					$('#comTranBtnId').attr('disabled', 'disabled');
					
					//	var aDate = $('#createdDt').val();
					//	$('#cDate').val(aDate);
						
						$('#vTranDate').val($('#trandate').val());
						
						//alert($('#cDate').val());
					
					
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");
						
						

						$
							.ajax({
								url : formURL,
								type : "POST",
								data : postData,

								success : function(data, textStatus, jqXHR) {
									//alert(data);
									
									if(data === '-1'){
										var vPurity = $('#purity\\.id').find('option:selected').text();
										var vColor = $('#color\\.id').find('option:selected').text();
										var vComponent = $('#component\\.id').find('option:selected').text();
										
										displayMsg(this, null, '' + vPurity + " and "+ vColor + " "+ " and " + vComponent +" "+' METAL STOCK NOT AVAILABLE!');
									}else{
										disMessComp(event,this);
										
									}
									
									$('#comTranBtnId').removeAttr('disabled');
									

								},
								error : function(jqXHR, textStatus, errorThrown) {
									alert("Error Occoured Please Contact Software Developer Team");

								}

							});
						
					e.preventDefault();
				
			});
		
		
		function disMessComp(e,id){
			
			displayConfirmation(
					e,
					'javascript:popReload();',
					'NOTIFICATION',
					'DATA TRANSFERED SUCESSFULLY!',
					'OK');
		}
		
		function popReload(){
			window.location.href = "/jewels/manufacturing/transactions/componentTransaction.html";
		}
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>


