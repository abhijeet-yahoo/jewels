<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">


	<div class="panel-heading">
		<span style="font-size: 22px; font-style:inherit;">Stone Conversion</span>
	</div>
	<div class="panel-body">

		<div class="row">
			<div class="col-xs-12">
				<div class="col-xs-6">
					<div class="panel panel-primary">
						<!--  start of left panel -->
						 <div class="panel-body" style="height: 500px">

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-12">

										<label for="inputLabel3" style="color: blue; font-size: 20px"
											class=".col-lg-2 text-right" >Stone Conversion </label>


									</div>
								</div>
							</div>
							<hr>


							<form:form commandName="stoneTran" id="stnTranIdOne"
								action="/jewels/manufacturing/transactions/stoneConversion.html"
								cssClass="form-horizontal stoneConversionForm" >
								
					
								<div class="form-group">
									<label for="location" class="col-sm-3 control-label"">Location
										:</label>
									<div class="col-sm-5">
											<form:select path="deptLocation.id" class="form-control" required="true">
							<form:option value="" label=" Select Location " />
							<form:options items="${locationStnMap}" />
						</form:select>
									</div>
								</div>
					

						<div class="form-group">
									<label for="tranDate" class="col-sm-3 control-label"">Tran Date
										:</label>
									<div class="col-sm-5">
										<form:input path="tranDate" cssClass="form-control" />
						<form:errors path="tranDate" />
									</div>
								</div>


								<div class="form-group">
									<label for="stoneType" class="col-sm-3 control-label"">StoneType
										:</label>
									<div class="col-sm-5">
										<form:select path="stoneType.id" class="form-control">
											<form:option value="" label=" Select StoneType " />
											<form:options items="${stoneTypeMap}" />
										</form:select>
									</div>
								</div>

								<div class="form-group">
									<label for="shape" class="col-sm-3 control-label">Shape
										:</label>
									<div class="col-sm-5">
										<form:select path="shape.id" class="form-control"
											onchange="javascript:popQuality();popSize();popSieve();">
											<form:option value="" label="- Select Shape -" />
											<form:options items="${shapeMap}" />
										</form:select>
									</div>
								</div>

								<div class="form-group">
									<label for="quality" class="col-sm-3 control-label">Quality
										:</label>
									<div class="col-sm-5" id="qualityFrom">
										<form:select path="quality.id" class="form-control">
											<form:option value="" label="- Select Quality -" />
											<form:options items="${qualityMap}" />
										</form:select>
									</div>
								</div>


								<%-- <div class="form-group">
									<label for="size" class="col-sm-3 control-label">Size :</label>
									<div class="col-sm-5" id="sizeId">
										<form:select path="size" class="form-control">
											<form:option value="" label="- Select Size -" />
											<form:options items="${stoneChartMap}" />
										</form:select>
									</div>
								</div> --%>
								
								<div class="form-group">
									<label for="sieve" class="col-sm-3 control-label">Sieve :</label>
									<div class="col-sm-5" id="sieveId">
										<form:select path="sieve" class="form-control">
											<form:option value="" label="- Select Sieve -" />
											<%-- <form:options items="${stoneChartMap}" /> --%>
										</form:select>
									</div>
								</div>

								<div class="form-group">
								<div class="col-sm-5" align="center">
									<input type="button" value="Get Stock" class="btn btn-info"
										onclick="javascript:getStock();">
										
								</div>
								
							 <label class="col-sm-3 control-label">Available Stock :</label>
								
								<div class="col-sm-3" id="availableStkDivId">
										 <form:input path="balCarat" cssClass="form-control" id="availableStkId" readonly="true"/> 
									</div> 
									
								</div>
								
									<div class="form-group">
																
																
									<label class="col-sm-3 control-label">Stone value :</label>
								
								<div class="col-sm-3">
								<input type="text" id="stnVal" class="form-control" value="0.0" readonly="readonly">
										  
									</div>
									
									<label class="col-sm-3 control-label">Avg Rate :</label>
								
								<div class="col-sm-3">
									<form:input path="rate" cssClass="form-control" id="avgRate" readonly="true"/> 

										  
									</div>
									
									</div>
									
									
								
								<hr>

							
								

							</form:form>
							
								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>



						</div>

				
					</div>
				</div>

				<div class="col-xs-6" >
					<div class="panel panel-primary">
						<!--  start of right panel -->
					  <div class="panel-body" style="height: 500px">

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-12">
										<label for="inputLabel3" style="color: blue;  font-size: 20px""
											class=".col-lg-12 text-right">Change quality </label>
									</div>
								</div>
							</div>

							<hr>
							
							<form:form commandName="stoneTran" id="stnTranIdTwo"
								action="/jewels/manufacturing/transactions/stoneConversion.html"
								cssClass="form-horizontal stoneConversionForm2">
								
								
								<div class="form-group">
									<label for="shape" class="col-sm-3 control-label">Shape
										:</label>
									<div class="col-sm-5">
										<form:select path="shape.id" class="form-control"
											onchange="javascript:popQualityTwo();popSizeTwo();popSieveTwo();">
											<form:option value="" label="- Select Shape -" />
											<form:options items="${shapeMap}" />
										</form:select>
									</div>
								</div>


								<div class="form-group">
									<label for="quality" class="col-sm-3 control-label">Quality
										:</label>
									<div class="col-sm-5" id="qualityIdTo">
										<form:select path="quality.id" class="form-control" id="qualityTo">
											<form:option value="" label="- Select Quality -" />
											<form:options items="${qualityMap}" />
										</form:select>
									</div>
								</div>


								<%-- <div class="form-group">
									<label for="size" class="col-sm-3 control-label">Size :</label>
									<div class="col-sm-5"  id="sizeIdTo">
										<form:select path="size" class="form-control" id="sizeTo">
											<form:option value="" label="- Select Size -" />
											<form:options items="${stoneChartMap}" />
										</form:select>
									</div>
								</div> --%>
								
								<div class="form-group">
									<label for="sieve" class="col-sm-3 control-label">Sieve :</label>
									<div class="col-sm-5" id="sieveTwoId">
										<form:select path="sieve" class="form-control" id="sieveIdTo">
											<form:option value="" label="- Select Sieve -" />
											<%-- <form:options items="${stoneChartMap}" /> --%>
										</form:select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">TrfStone :</label>

									<div class="col-sm-5">
									
										
										<input type="text" id="trfStone" name = "trfStone" class="form-control" value="0" autocomplete="off"/>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">TrfCarat :</label>

									<div class="col-sm-5">
										<%-- <form:input path="tranType" cssClass="form-control" autocomplete="off" /> --%>
										
										<input type="text" id="trfCarat" name = "trfCarat" class="form-control" onchange="javascript:calculateValue()" autocomplete="off"/>
									</div>
								</div>
								
										<div class="form-group">
									<label for="stoneRate" class="col-sm-3 control-label"">Rate
										:</label>
									<div class="col-sm-5">
										<form:input path="rate" cssClass="form-control" onchange="javascript:calculateValue()" />
						<form:errors path="rate" />
									</div>
								</div>
								
								<div class="form-group">
															
									<label class="col-sm-3 control-label">Stone value :</label>
								
								<div class="col-sm-5">
								<input type="text" id="stnValTrf" class="form-control" value="0.0" readonly="readonly">
										  
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-5" align="center">
										<input type="submit" value="Save" class="btn btn-info" id="stnTranIdTwoBtnId"/>
										<a href="<spring:url value='/manufacturing/transactions/stoneCOnversion.html' />"></a>
										
										
										
										
										<input type="hidden" name="vStoneTypeIdFrom" id="vStoneTypeIdFrom" />
										<input type="hidden" name="vSizeIdFrom" id="vSizeIdFrom" />
										<input type="hidden" name="vSieveFrom" id="vSieveFrom" /> 
										
										<input type="hidden" name="vQualityIdFrom" id="vQualityIdFrom" />
										<input type="hidden" name="vshapeIdFrom" id="vshapeIdFrom" /> 
										<input type="hidden" name="vtrfCarat" id="vtrfCarat" /> 
										<input type="hidden" name="vtrfStone" id="vtrfStone" /> 
										<input type="hidden" name="vtranDate" id="vtranDate" />
										<input type="hidden" name="vStnBalVal" id="vStnBalVal" />
										<input type="hidden" name="vLocationId" id="vLocationId" />
										 
										
										
									</div>
								</div>

<hr>
							</form:form>
							
								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>

						</div>
					</div>
				</div>
				
			</div>
		</div>

	</div>


</div>


 <script type="text/javascript">
 

 $(document)
	.ready(
			function(e) {
				$(".stoneConversionForm2")
						.validate(
								{
									rules : {
										'quality.id' : {
											required : true
										},
										'size' : {
											required : true
										},
										
										'rate' : {
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
										rate : {
											greaterThan : "This field is required"
										},
									
									},
									
								});

				$("#tranDate").datepicker({
					dateFormat : 'dd/mm/yy'
				});
				
				
				
				$("#tranDate").val(new Date().toLocaleDateString('en-GB'));
				$("#tranDate").focus();
				
				
			});
 
 
 
 function popQuality() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/quality/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#qualityIdTo").html(data);
						$("#qualityFrom").html(data);
						
						$('#stnTranIdTwo #shape\\.id').val($('#shape\\.id').val());
					}
				});

	}

	function popSize() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/size/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#sizeIdTo").html(data);
						$("#sizeId").html(data);
						
					}
				});

	}

	function popSieve() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/sieve/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#sieveIdTo").html(data);
						$("#sieveId").html(data);
						
					}
				});

	}
	
	
	 function popQualityTwo() {
			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/dc/quality/list.html' />?shapeId='
								+ $('#stnTranIdTwo #shape\\.id').val(),
						type : 'GET',
						success : function(data) {
							$("#qualityIdTo").html(data);
							
						}
					});

		}

		function popSizeTwo() {

			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/dc/size/list.html' />?shapeId='
								+ $('#stnTranIdTwo #shape\\.id').val(),
						type : 'GET',
						success : function(data) {
							$("#sizeIdTo").html(data);
							
						}
					});

		}

		function popSieveTwo() {

			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/dc/sieve/list.html' />?shapeId='
								+ $('#stnTranIdTwo #shape\\.id').val(),
						type : 'GET',
						success : function(data) {
							$("#sieveIdTo").html(data);
							
						}
					});

		}
	
	
	
	
 	function getStock() {
		

		if (!$('#stoneType\\.id').val() || !$('#shape\\.id').val() || !$('#quality\\.id').val() || !$('#deptLocation\\.id').val()) {
				displayMsg(this, null,
						'Location,StoneType , Shape , Quality  is compulsary to get stock');
			} else {

				$
						.ajax({
							url : "/jewels/manufacturing/transactions/stoneConversion/available/stock.html?stoneType="
									+ $('#stoneType\\.id').val()
									+ "&shape="
									+ $('#shape\\.id').val()
									+ "&quality="
									+ $('#quality\\.id').val()
									+ "&sieve="
									+ $("#sieve").val()
									+ "&locationId="
									+ $("#deptLocation\\.id").val(),

							success : function(data) {

								//$("#availableStkId").val(data);
								
								
								
								$.each(JSON.parse(data),function(k,v){
									
									
									$('#'+k).val(v);
									
								});

							}

						});

			}

		}

		$(document)
				.on(
						'submit',
						'form#stnTranIdTwo',
						function(e) {

							
							 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });	
														
							$('#stnTranIdTwoBtnId').attr('disabled', 'disabled');
							
							$('#vStoneTypeIdFrom').val($('#stoneType\\.id').val());
							$('#vSieveFrom').val($('#sieve').val());
							$('#vshapeIdFrom').val($('#shape\\.id').val());
							$('#vQualityIdFrom').val($('#quality\\.id').val());
							$('#vtrfCarat').val($('#trfCarat').val());
							$('#vtrfStone').val($('#trfStone').val());
							$('#vtranDate').val($('#tranDate').val());
							$('#vLocationId').val($('#deptLocation\\.id').val());
							
							
							
							
							
																		
							if(Number($('#stnValTrf').val()) > Number($('#stnVal').val())){
								
								
								$.unblockUI();

								displayMsg(this, null,	'Stone Value greater Than Balance Stone Value, Can Not Transfer');
								$('#stnTranIdTwoBtnId').removeAttr('disabled');
								
							}else{ 
								
								if(Number($('#availableStkId').val())==Number($('#vtrfCarat').val()) && Number($('#rate').val()) != Number($('#avgRate').val()) ){
									
									$.unblockUI();

									displayMsg(this, null,	'Stone Value Mismatch , Can Not Transfer');
									$('#stnTranIdTwoBtnId').removeAttr('disabled');
									
								}else{
									
									if ($('#vtrfCarat').val() > 0) {

										var postData = $(this).serializeArray();

										var formURL = $(this).attr("action");

										$
												.ajax({

													url : formURL,
													type : "POST",
													data : postData,
													success : function(data,textStatus, jqXHR) {
														$.unblockUI();
														$('#stnTranIdTwoBtnId').removeAttr('disabled');
														if (data === '1') {
															//$('#stnTranIdTwo #shape\\.id').val($('#stnTranIdOne #shape\\.id').val());
														displayInfoMsg(this, null, " Save Successfully")
															getStock();
														$('#stnTranIdTwo #size').val("");
															$('#stnTranIdTwo').find("input[type=text], textarea").val("");
															$('#stnTranIdTwo #trfStone').val(0);
															
															

														} else {
															displayMsg(this, null, data);
															
														}

													},
													error : function(jqXHR, textStatus,
															errorThrown) {
														$.unblockUI();
													}
												});

									} else {
										$.unblockUI();

										displayMsg(this, null,	'Transfer Carat Zero, Can Not Transfer');
										$('#stnTranIdTwoBtnId').removeAttr('disabled');

									}
									
								}
								
								
								
							
								
							}

							

							e.preventDefault(); //STOP default action
							$('#stnTranIdTwoBtnId').removeAttr('disabled');
						});
		
		
		
		function calculateValue(){
			
			
			
			if(Number($('#availableStkId').val()) == Number($('#trfCarat').val())){
				
				$('#stnTranIdTwo #stnValTrf').val($('#stnTranIdOne #stnVal').val());
				
				$('#stnTranIdTwo #rate').val($('#stnTranIdOne #avgRate').val());
				
				$('#stnTranIdTwo #rate').attr("readonly", true); 
				
				
				
				//$('#stnTranIdTwo #rate').attr('readonly','readonly');
				
				
				
				
			}else{
				
				$('#stnTranIdTwo #rate').attr("readonly", false); 
				
				
				var stnval=$('#stnTranIdTwo #trfCarat').val()*$('#stnTranIdTwo #rate').val();
				
				
				$('#stnTranIdTwo #stnValTrf').val(Math.round((stnval + Number.EPSILON) * 100) / 100);
				
			}
			
			
			
			
		}
		
	</script> 
	
	
	<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>