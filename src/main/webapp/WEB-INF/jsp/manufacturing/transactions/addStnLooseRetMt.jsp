<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>
<%@include file="/WEB-INF/jsp/common/modalStnLoosePickupForStnLooseReturn.jsp"%>


<div id="stnLooseRetDivId">
<div class="panel panel-primary" style="width: 100%">
		
		<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Loose Stone Return </span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="looseStoneInwRetExcelPreviewBtn" onClick="javascript:looseStoneInwRetReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="looseStoneInwRetPreviewBtn" onClick="javascript:looseStoneInwRetReport(2);" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="stnLooseListingBtn"
					href="/jewels/manufacturing/transactions/stnLooseRetMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="stnLooseRetMtSubmitBtn"  onclick="javascript:stnLooseMtSave()" />
			

			</div>



		</div>

	</div>

	<div class="form-group">
		<form:form commandName="stnLooseRetMt" id="stnLooseRetMtId"
			action="/jewels/manufacturing/transactions/stnLooseRetMt/add.html"
			cssClass="form-horizontal stnLooseRetMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Loose Stone Return ${action} successfully!</div>
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


<div id="stnLooseRetDt" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:stnLooseDtPickup(0);"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Pickup
						</a>
					
					
					</div>
					<div>
						<table id="stoneLooseRetTblDtId" data-toggle="table" data-toolbar="#toolbar"
							data-height="350" data-striped="true">
							<thead>
								<tr class="btn-primary">
									
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="stoneType" data-sortable="true">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="stoneChart" data-sortable="true">Size</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
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
						
						
						
						$(".stnLooseRetMtForm")
								.validate(
										{
											rules : {
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
							
							
							$("#stnLooseRetDt").css('display', 'block');
							mtid="${mtid}";
							
						}else if (window.location.href.indexOf('view') != -1) {
							canViewFlag=true;
							
							$("#stnLooseRetDt").css('display', 'block');
							$('#stnLooseRetDivId').find('input, textarea, select').attr('disabled','disabled');
							$('#stnLooseRetDt :input').attr('disabled',true);
							$('#stnLooseRetDt .btn').hide();
							$('#stnLooseMtSubmitBtn').hide();
							
						}
						else{
							$("#vTranDate").val('${currentDate}');	
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
							$("#invNo").focus();
						}

						popStnLooseRetDt();

					});
	
	
	
	
	  $(document)
		.on(
			'submit',
			'form#stnLooseRetMtId',
			 function(e){
				 $("#stnLooseMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});
	
	
	 function stnLooseMtSave(){
		 if($(".stnLooseRetMtForm").valid()){
				$("form#stnLooseRetMtId").submit();
				
			}
			
		 }
	
	

	function popStnLooseRetDt() {
		$("#stoneLooseRetTblDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/stnLooseRetDt/listing.html?mtId="
									+ mtid+"&canViewFlag="+canViewFlag,
						});
	}

		
	
	function deleteLooseRetDt(dtId){
		
		
		$("#modalDialog").modal("hide");
						
						 $.ajax({
								url : "/jewels/manufacturing/transactions/stnLooseRetDt/delete/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										popStnLooseRetDt();
									}else if(data === "-1"){
										displayMsg(this, null, "Back Date Entry,You cannot delete this record ,Please Contact Administrator to Delete");
									}else{
										displayMsg(event, this, 'Error Occured, Contact Admin');
										}
								}
							}); 
		
	}
	
	function deleteStoneLooseRetDt(e,dtId){

			
			displayDlg(
					e,
					'javascript:deleteLooseRetDt('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
				
  }
	
	

	
	$('#stoneLooseRetTblDtId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {

				var data = JSON.stringify($("#stoneLooseRetTblDtId").bootstrapTable('getData'));

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


	function popStnLoosePickupPartyWiseList() {

		$("#stnLooseMtTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/stnLooseMt/partyWiseListing.html?partyId="
									+$('#supplier\\.id').val()
						});
	}



	function stnLooseDtPickup(){

		$('#myStnLoosePickupModalForReturn').modal('show');
		 
		 $('#myStnLoosePickupModalForReturn').on('shown.bs.modal', function () {
			 popStnLoosePickupPartyWiseList();
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
