<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<div id=stoneOutwardDivId>
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Stone Outward</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="stoneOutExcelPreviewBtn" onClick="javascript:stoneOutReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="stoneOutPreviewBtn" onClick="javascript:stoneOutReport(2);" />
						</div>

		</div>

	</div>
	<div class="form-group">
		<form:form commandName="stoneOutwardMt" id="stnOutward"
			action="/jewels/manufacturing/transactions/stoneOutwardMt/add.html"
			cssClass="form-horizontal stoneOutwardMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Stone Outward ${action}
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
						<label for="inputLabel3">Invoice No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3">Invoice Date:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" >Be No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" >Be Date:</label>
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:input path="invNo" cssClass="form-control" autocomplete="off" readonly="true"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control" />
						<form:errors path="invDate" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="beNo" cssClass="form-control" autocomplete="off"/>
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
						<label for="inputLabel3">For Client:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3">Type:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-12 text-right">Remarks:</label>
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					
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
						<form:textarea path="remark" cssClass="form-control" />
						<form:errors path="remark" />
					</div>
					
					
				</div>
			</div>
			<div class="form-group">
			<div class="col-xs-12">
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="stnOutwardSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/stoneOutwardMt.html">Listing</a>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="srNo" />
					<form:input type="hidden" path="uniqueId" />		
					<input type="hidden" name="xml" id="xml" value="${xml}"  />	
				</div>
			</div>	
			</div>
		</form:form>
		
		
		 <!-- Download Common pdf Report -->
		
			<div style="display: none">		
				<form:form target="_blank"  id="styleNotMatchForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="generateDataRpt"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
			
			
			
					 <!-- Download Common Excel Report -->
			 
			 <div style="display: none">
				<form:form 	action="/jewels/manufacturing/masters/reports/download/Common/excelReport.html"
						cssClass="form-horizontal">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Excel Report" class="btn btn-primary" id="generateExcelReportss"/>
							</div>
						</div>
						
					<input type='hidden' name='pFileName' id='pFileName'/>
						
				</form:form>
			 </div>
		
		
	</div>
</div>
</div>

<script type="text/javascript">

canViewFlag=false;
	$(document)
			.ready(
					function(e) {
						$(".stoneOutwardMtForm")
								.validate(
										{
											rules : {
												invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/sOutward/invoiceNoAvailable.html' />",
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
						
						if('${canEditTranddate}' === "false"){
							$("#invDate").attr("disabled","disabled");
						}

						$("#beDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						if (window.location.href.indexOf('edit') != -1) {
							$("#sOutwardDt").css('display', 'block');
							mtid="${mtid}";
						}else if (window.location.href.indexOf('view') != -1) {
							canViewFlag=true;
							
							$("#sOutwardDt").css('display', 'block');
							$('#stoneOutwardDivId').find('input, textarea, select').attr('disabled','disabled');
							$('#sOutwardDt :input').attr('disabled',true);
							$('#sOutwardDt .btn').hide();
							$('#stnOutwardSubmitBtn').hide();
							
						}else{
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						popStoneOutwardDt();

					});
	
	
	
	
	  $(document)
		.on(
			'submit',
			'form#stnOutward',
			 function(e){
				 $("#stnOutwardSubmitBtn").prop("disabled", true).addClass("disabled");
			});
	
	
	
	
	

	function popStoneOutwardDt() {
		$("#stoneIDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/stoneOutwardDt/listing.html?pInvNo="
									+ $('#invNo').val()+"&canViewFlag="+canViewFlag,
						});
	}

	function popSubShape(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/subShape/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#subShapeId").html(data);
					}
				});
	}

	function popQuality(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/quality/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#qualityId").html(data);
					}
				});
	}

	function popStoneChart(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/stoneChart/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeId").html(data);
					}
				});
	}

	function getSizeMMDetails() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/sizeMM/details.html' />?shapeId='
							+ $('#shape\\.id').val()
							+ '&size='
							+ $('#size').val(),
					type : 'GET',
					success : function(data) {
						fldData = data.split("_");
						$("#vSieve").val(fldData[0]);
						$("#vSizeGroupStr").val(fldData[1]);
					}
				});
	}

	function popSizeGroup(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/sizeGroup/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeGroupId").html(data);
					}
				});
	}

	
	$(document)
			.on(
					'submit',
					'form#stoneOutwardDtt',
					function(e) {
						
						
						$('#stnDtBtnId').attr('disabled', 'disabled');
						
						$("#pInvNo").val($("#invNo").val());
						$('#sieve').val($('#vSieve').val());
						$('#sizeGroupStr').val($('#vSizeGroupStr').val());
						$('#vCarat').val($('#carat').val());  
						$('#vStone').val($('#stone').val());
						
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");
						
						//$('form#stoneOutwardDtt input[type="text"],texatrea, select').val('');
						//$('form#stoneOutwardDtt select').val('');

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										
										
										if(data.indexOf("Error") != -1){
											displayMsg(this, null, data);
										}else{	
											
											popStoneOutwardDt();
											
											var vParty = $('#party\\.id').val();
											var vInwardType = $('#inwardType\\.id').val();
											
											$('form#stoneOutwardDtt input[type="text"],texatrea, select').val('');
											$('form#stoneOutwardDtt select').val('');
											
											$('#party\\.id').val(vParty);
											$('#inwardType\\.id').val(vInwardType);
		
											$("#stone").val('0');
											$("#carat").val('0.0');
											$("#diffCarat").val('0.0');
											$('#rate').val('0.0');
		
											if (data == -1) {
												
												$("#stoneOutwardDtEntry").css('display', 'none');
												$('#stoneOutwardDtEntryId').val('');
											} 
										
										}
										
										$('#stnDtBtnId').removeAttr('disabled');

									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});

	function editStoneOutwDt(dtId) {
		
		
		  $
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/stnOutward/editRights.html' />?mtId='+$('#id').val()+"&dtId="+dtId,
			type : 'GET',
			success : function(data) {
				
				
				if(data === "1"){
					
					$.ajax({
						url : "/jewels/manufacturing/transactions/stoneOutwardDt/edit/"
								+ dtId + ".html",
						type : 'GET',
						success : function(data) {
							$("#rowId").html(data);

							$("#stoneOutwardDtEntry").css('display', 'block');
							$('#stoneType\\.id').focus();

							$('#vSieve').val($('#sieve').val());
							

						}
					});
					
				}else{
					displayMsg(event, this,'Can Not Edit BackDate Entry, Contact Administrator');
				}

			}
		});  
		
	}
	

	
		
	
	
	
	function deleteDt(dtId){
		$("#modalDialog").modal("hide");

		 $.ajax({
			url : "/jewels/manufacturing/transactions/stoneOutwardDt/delete/"+ dtId + ".html",
			type : 'GET',
			success : function(data) {
				//alert(data);
				if(Number(data) == -1){
					displayMsg(event, this, 'You Can Not Delete This Record, Contact Administrator');
				}else if(Number(data) == -2){
					displayMsg(event, this, 'Can Not Delete Against Order Entry');
				}
				else{
					window.location.href = data;
				}

			}
		}); 
	}
	
	function deleteStoneOutwDt(e,dtId){

			
			displayDlg(
					e,
					'javascript:deleteDt('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
				
  }
	
	
	function  popStoneOutwCancelBtn() {
		$('#stoneOutwardDtEntry').css('display','none');
	}

	function setAmount() {
	
		var vCarat = $('#carat').val();
		var rate = $('#rate1').val();
		var vDiffCarat = $('#diffCarat').val();
		var amount = vCarat * rate;
		
		var vvCarat = vCarat * 1;
		var dcarat = vDiffCarat * 1;
		var vRate = rate * 1;
		
		$('#aAmount').val(amount.toFixed(2));
		$('#amount').val(amount.toFixed(2));
		$('#carat').val(vvCarat.toFixed(3));
		$('#rate1').val(vRate.toFixed(2));
		$('#diffCarat').val(dcarat.toFixed(3));

	}

	function goToStoneOutwardDtEntry() {
		
		
		
		  $
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/stnOutward/addRights.html' />?pInvNo='+ $('#invNo').val(),
				type : 'GET',
				success : function(data) {
					
					
					if(Number(data) == 1){
						
						$("#stoneOutwardDtEntry").css('display', 'block');
						
						$('#stoneType\\.id').attr('disabled',false);
						$('#shape\\.id').attr('disabled',false);
						$('#subShape\\.id').attr('disabled',false);
						$('#quality\\.id').attr('disabled',false);
						$('#size').attr('disabled',false);
						
						
						
						var vParty = $('#party\\.id').val();
						var vInwardType = $('#inwardType\\.id').val();
						
						$('form#stoneOutwardDtt input[type="text"],texatrea, select').val('');
						$('#stoneOutwardDtEntryId').val('');
						
						$('#party\\.id').val(vParty);
						$('#inwardType\\.id').val(vInwardType);
						
						$("#stone").val('0');
						$("#carat").val('0.0');
						$("#diffCarat").val('0.0');
						$('#rate').val('0.0');
						$('#stoneType\\.id').focus();
						
					}else{
						displayMsg(event, this, 'You cannot Add record In BackDate Invoice,Please contact Administrator');
					}

				}
			});  
		
		
		
		
		
	
		
		
		/* $('#balCaratDivId').css('display','none'); */
		
	}

	$(document).ready(function(e) {
		$(".stoneOutwardDttForm").validate({
			rules : {
				'stoneType.id' : {
					required : true,
				},
				'shape.id' : {
					required : true,
				},
				'quality.id' : {
					required : true,
				},
				size : {
					required : true,
				},
				carat : {
					required : true,
					greaterThan : "0,0",
				}

			},

			messages : {
				carat : {
					greaterThan : "This field is required"
				}
			},

		});
		
		totsummary();

	});
	
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


	function stoneOutReport(val){
	
		$
		.ajax({
			url : "/jewels/manufacturing/masters/reports/common/transactionReport.html?mtId="+mtid+"&xml="+ $('#xml').val()+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
	
				if(val === 2){
					$('#timeValCommonPdf').val(data);
					$("#generateDataRpt").trigger('click');
					
				}else{
					$('#pFileName').val(data);
					$("#generateExcelReportss").trigger('click');

					}	
				
			}
		});
	}
	
	
	

	function popAgainstOrderModal(){

	 window.location.href = "/jewels/manufacturing/transactions/stnOutwAgainstOrder.html?mtid="+mtid+"totalcarat"+$('#vTotalCarats').val()
			 +"totalstone"+$('#vTotalStones').val()+"mOpt=Outward";
		
	}
	
	
	
</script>
<div id="sOutwardDt" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:goToStoneOutwardDtEntry();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
						
					<!-- 	<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:popAgainstOrderModal();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Against Order
						</a> -->
						
					</div>
					<div>
						<table id="stoneIDtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" 
							data-height="350" data-striped="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="styleNo" data-sortable="true">Style No</th>
									<th data-field="refNo" data-align="left">Ref No</th>				
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
									<th data-field="balStone" data-sortable="true">BalStone</th>
									<th data-field="balCarat" data-sortable="true">BalCarat</th>
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


	<div id="stoneOutwardDtEntry" style="display: none">
		<div class="panel panel-primary" style="width: 100%">
			<div id="rowId">
				<div class="form-group">
					<div class="form-group">
						<form:form commandName="stoneOutwardDt" id="stoneOutwardDtt"
							action="/jewels/manufacturing/transactions/stoneOutwardDt/add.html"
							cssClass="form-horizontal stoneOutwardDttForm">

							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">StoneType:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Shape:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">SubShape:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Quality:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Size:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Sieve:</label>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-2 col-sm-2">
										<div id="stoneTypeId">
											<form:select path="stoneType.id" class="form-control"  disabled ="true">
												<form:option value="" label=" SelectStoneType " />
												<form:options items="${stoneTypeMap}" />
											</form:select>
										</div>
									</div>
									<div class="col-lg-2 col-sm-2">
										<form:select path="shape.id" class="form-control"
											onChange="javascript:popSubShape(this.value);popQuality(this.value);popStoneChart(this.value);popSizeGroup(this.value)" disabled="true">
											<form:option value="" label="- Select Shape -" />
											<form:options items="${shapeMap}" />
										</form:select>
									</div>
									<div class="col-lg-2 col-sm-2">
										<div id="subShapeId">
											<form:select path="subShape.id" class="form-control" disabled="true">
												<form:option value="" label="- Select SubShape -" />
												<form:options items="${subShapeMap}" />
											</form:select>
										</div>
									</div>
									<div class="col-lg-2 col-sm-2">
										<div id="qualityId">
											<form:select path="quality.id" class="form-control" disabled="true">
												<form:option value="" label="- Select Quality -" />
												<form:options items="${qualityMap}" />
											</form:select>
										</div>
									</div>
									<div class="col-lg-2 col-sm-2">
										<div id="sizeId">
											<form:select path="size" class="form-control"
												onChange="javascript:getSizeMMDetails()" disabled="true">
												<form:option value="" label="- Select Size -" />
												<form:options items="${stoneChartMap}" />
											</form:select>
										</div>
									</div>
									<div class="col-lg-2 col-sm-2">
										<input type="text" id="vSieve" name="vSieve"
											class="form-control" disabled="disabled" />
									</div>
								</div>
							</div>`
			
			
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">SizeGroup:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Stone:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Carat:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Diff Carat:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Rate:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Amount:</label>
									</div>
									
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-2 col-sm-2">
										<input type="text" id="vSizeGroupStr"
											name="vSizeGroupStr" class="form-control" disabled="disabled" />
									</div>
									<div class="col-lg-2 col-sm-2">
										<form:input path="stone" cssClass="form-control" />
									</div>
									<div class="col-lg-2 col-sm-2">
										<form:input path="carat" cssClass="form-control"
											onchange="javascript:setAmount()" />
									</div>
										<div class="col-lg-2 col-sm-2">
										<form:input path="diffCarat" cssClass="form-control"/>
									</div>
									<div class="col-lg-2 col-sm-2">
										<form:input path="rate1" cssClass="form-control"
											onchange="javascript:setAmount()" />
									</div>

									<div class="col-lg-2 col-sm-2">
										<!--  <input type="text" id="aAmount" class="form-control" disabled="true" />  -->
										<input type="text" id="aAmount" name="aAmount"
											class="form-control" disabled="disabled" />
									</div>

									
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">
						
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Packet No:</label>
									</div>
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
										<div class="col-lg-2 col-sm-2">
											<form:input path="packetNo" cssClass="form-control" />
										</div>
									<div class="col-lg-2 col-sm-2">
										<form:input path="remark" cssClass="form-control" />
									</div>
								</div>
							</div>

							<%-- <div class="row">
								<div class="col-xs-12">
									<div class="col-lg-2 col-sm-2">
										<label for="inputLabel3" class=".col-lg-2 text-right">Bal Carat:</label>
									</div>
									
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-2 col-sm-2">
										<form:input path="balCarat" cssClass="form-control" />
									</div>
								</div>
							</div> --%>

							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>

							<div class="form-group">
							<div class="col-xs-12">
								<div class="col-sm-4">
									<input type="submit" value="Save" class="btn btn-primary" id="stnDtBtnId"/>
									<input type="button" value="Close" class="btn btn-danger" onclick="javascript:popStoneOutwCancelBtn()">
									<form:input type="hidden" id="stoneOutwardDtEntryId" path="id" />
									<form:input type="hidden" path="sieve" />
									<input type="hidden" id="pInvNo" name="pInvNo" />
									<form:input type="hidden" path="department.id" />
									<form:input type="hidden" path="uniqueId" />
									<form:input type="hidden" path="amount" />
									<input type="hidden" id="prevCarat" name="prevCarat" />
									<input type="hidden" id="vCarat" name="vCarat" />
									<input type="hidden" id="vStone" name="vStone" />
									 <input type="hidden" id="sizeGroupStr" name="sizeGroupStr" /> 
								</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>



<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />


