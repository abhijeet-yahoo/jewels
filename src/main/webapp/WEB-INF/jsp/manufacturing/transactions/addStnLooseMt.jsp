<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalShowConversionDt.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div id="stnLooseDivId">
<div class="panel panel-primary" style="width: 100%">
		
		<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Loose Stone Inward </span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="looseStoneInwExcelPreviewBtn" onClick="javascript:looseStoneInwReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="looseStoneInwPreviewBtn" onClick="javascript:looseStoneInwReport(2);" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="stnLooseListingBtn"
					href="/jewels/manufacturing/transactions/stnLooseMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="stnLooseMtSubmitBtn"  onclick="javascript:stnLooseMtSave()" />
			

			</div>



		</div>

	</div>

	<div class="form-group">
		<form:form commandName="stnLooseMt" id="stnLooseMtId"
			action="/jewels/manufacturing/transactions/stnLooseMt/add.html"
			cssClass="form-horizontal stnLooseMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Loose Stone ${action}
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
						<label for="inputLabel3" class=".col-lg-2 text-right">Voucher
							No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Voucher
							Date:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Reference
							No:</label>
					</div>
					
						<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Reference Date:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:input path="invNo" cssClass="form-control" autocomplete="off" disabled="true"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control" disabled="true"/>
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
						<form:select path="supplier.id" class="form-control" required="true" >
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="party.id" class="form-control">
							<form:option value="" label=" Select Party " />
							<form:options items="${clientMap}" />
						</form:select>
					</div>
					
					
						<div class="col-lg-3 col-sm-3">
						<form:select path="inwardType.id" class="form-control" required="true">
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
					<!-- <input type="submit" value="Save" class="btn btn-primary" id="stnLooseMtSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/stnLooseMt.html">Listing</a> -->
					<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="srNo"/>
					<form:input type="hidden" path="invNo"/>
					<input type="hidden" name="vTranDate" id="vTranDate" />		
					
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


<div id="stnLooseDt" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:addeditStoneLooseDt(0);"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add
						</a>
					
					
					</div>
					<div>
						<table id="stoneLooseTblDtId" data-toggle="table" data-toolbar="#toolbar"
							data-pagination="false" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" 
							data-height="350" data-striped="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="action3" data-align="center">Conversion</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="stoneType" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="stoneChart" data-sortable="true">Size</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<!-- <th data-field="balStone" data-sortable="true">BalStone</th>
									<th data-field="balCarat" data-sortable="true">BalCarat</th> -->
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="lotNo" data-sortable="true">Packet No</th>
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
<div id="stoneLooseDtEntry" style="display: none">
<div id="rowId">			
</div>
</div>
		
	</div>


	

</div>

<script type="text/javascript">

canViewFlag=false;
var mtid;
	$(document)
			.ready(
					function(e) {
						
						
$('select').chosen();
						
						$.validator.setDefaults({ ignore: ":hidden:not(select)" });
					
											
					// validation of chosen on change
						if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}
						
						
						
						$(".stnLooseMtForm")
								.validate(
										{
											rules : {
									/* 			invNo : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/transactions/sInward/invoiceNoAvailable.html' />",
														type : "get",
														data : {

															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												}, */
												invDate : {
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

						

						$("#beDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						if (window.location.href.indexOf('edit') != -1) {

							$("#vTranDate").val($("#invDate").val());
							
							
							$("#stnLooseDt").css('display', 'block');
							mtid="${mtid}";
							
						}else if (window.location.href.indexOf('view') != -1) {
							canViewFlag=true;
							
							$("#stnLooseDt").css('display', 'block');
							$('#stnLooseDivId').find('input, textarea, select').attr('disabled','disabled');
							$('#stnLooseDt :input').attr('disabled',true);
							$('#stnLooseDt .btn').hide();
							$('#stnLooseMtSubmitBtn').hide();
							
						}
						else{
							$("#vTranDate").val('${currentDate}');	
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						popStnLooseDt();

					});
	
	
	
	
	  $(document)
		.on(
			'submit',
			'form#stnLooseMtId',
			 function(e){
				 $("#stnLooseMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});
	
	
	 function stnLooseMtSave(){
		 if($(".stnLooseMtForm").valid()){
				$("form#stnLooseMtId").submit();
				
			}
			
		 }
	
	

	function popStnLooseDt() {
		$("#stoneLooseTblDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/stnLooseDt/listing.html?mtId="
									+ mtid+"&canViewFlag="+canViewFlag,
						});
	}

	function popSubShape(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/subShape/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$('#stnLooseDtt #subShape\\.id').html(data);
						$('#stnLooseDtt #subShape\\.id').trigger('chosen:updated');
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
						$("#stnLooseDtt #quality\\.id").html(data);
						$('#stnLooseDtt #quality\\.id').trigger('chosen:updated');
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
						$('#stnLooseDtt #size').html(data);
						$('#stnLooseDtt #size').trigger('chosen:updated');
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
					'form#stnLooseDtt',
					function(e) {
						
						
						$('#stnDtBtnId').attr('disabled', 'disabled');
						
						$("#pInvNo").val($("#invNo").val());
						$('#sieve').val($('#vSieve').val());
						$('#sizeGroupStr').val($('#vSizeGroupStr').val());
						$('#vCarat').val($('#carat').val());  
						$('#vStone').val($('#stone').val());
						
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");
						
						
						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										
										if(data === '-11'){
											displayMsg(event, this, 'BalCarat cannot be greater than Carat');
										}else{	
													
											
											popStnLooseDt();

											$('form#stnLooseDtt select').val('').trigger('chosen:updated');
											$("#stone").val('0');
											$("#carat").val('0.0');
											$("#diffCarat").val('0.0');
											$('#rate').val('0.0');
											$('#aAmount').val('0.0');
											$('#vSieve').val('');
											$('#vSizeGroupStr').val('');
		
											if (data == -1) {
												$("#stoneInwardDtEntry").css('display', 'none');
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

	function addeditStoneLooseDt(dtId) {
		
		
		 $
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/stnLooseDt/addeditRights.html' />?mtId='+$('#id').val()+"&dtId="+dtId,
			type : 'GET',
			success : function(data) {
				
				
				if(data === "1"){
					
					$.ajax({
						url : "/jewels/manufacturing/transactions/stnLooset/addedit/"+ dtId + ".html",
						type : 'GET',
						success : function(data) {

							
							$("#stoneLooseDtEntry").css('display', 'block');
							$("#rowId").html(data);
							$('#stoneType\\.id').focus();
							$('#vSieve').val($('#sieve').val());
							
							$('#stnLooseDtt #stoneType\\.id').chosen();
							$('#stnLooseDtt #shape\\.id').chosen();
							$('#stnLooseDtt #quality\\.id').chosen();
							$('#stnLooseDtt #size').chosen();
							$('#stnLooseDtt #subShape\\.id').chosen();
							
						

						}
					});
					
				}else if(data === "-2"){
					displayMsg(event, this, 'Can Not Edit Against Order Entry');
				}else if(data === "-3"){
					
					displayMsg(event, this, 'Stock Adjusted Can Not Edit');
					
				}else{
					displayMsg(event, this,'BackDate Entry Not Allow, Contact Administrator');
				}

			}
		});  
		
	}
	

	
		
	
	
	
	function deleteLooseDt(dtId){
		
		
		$("#modalDialog").modal("hide");
						
						 $.ajax({
								url : "/jewels/manufacturing/transactions/stnLooseDt/delete/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										popStnLooseDt();
									}else if(data === "-2"){
										displayMsg(event, this, 'Can Not Delete, Conversion Done');
									}else if(data === "-3"){
										displayMsg(event, this, 'Can Not Delete, Record Adjusted');
									}
									else{
										displayMsg(event, this, 'Error Occured, Contact Admin');
										}
								}
							}); 
		
	}
	
	function deleteStoneLooseDt(e,dtId){

			
			displayDlg(
					e,
					'javascript:deleteLooseDt('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
				
  }
	
	
	function  popStoneInwCancelBtn() {
		$('#stoneLooseDtEntry').css('display','none');
	}

	function setAmount() {
		var vCarat = $('#carat').val();
		var rate = $('#rate').val();
		var vDiffCarat = $('#diffCarat').val();
		var amount = vCarat * rate;
		
		var vvCarat = vCarat * 1;
		var dcarat = vDiffCarat * 1;
		var vRate = rate * 1;
		
		$('#aAmount').val(amount.toFixed(2));
		$('#amount').val(amount.toFixed(2));
		$('#carat').val(vvCarat.toFixed(3));
		$('#rate').val(vRate.toFixed(2));
		$('#diffCarat').val(dcarat.toFixed(3));
	}

	

	

	
	$('#stoneLooseTblDtId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {

				var data = JSON.stringify($("#stoneLooseTblDtId").bootstrapTable('getData'));

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
	



	function stoneInwReport(val){
		
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
	

	function showConversionDt(e,dtId){

			$('#myShowConversionModal').modal('show');
			 
			 $('#myShowConversionModal').on('shown.bs.modal', function () {
				 $('#looseConvId').val(dtId);
				 popShowConversionDt(dtId);
		        });

		}

	function popShowConversionDt(dtId) {

		$("#looseConvTblDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/looseStkConversion/listing.html?dtId="
									+dtId
						});
	}
</script>


<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
