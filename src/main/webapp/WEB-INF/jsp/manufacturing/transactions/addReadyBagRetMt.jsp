<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>



<div id="readyBagDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Ready Bag Return</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="packMtExcelPreviewBtn" onClick="javascript:readyBagTrfListReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success"  onClick="javascript:readyBagTrfListReport(2);">
			 <span class="fa fa-file-pdf"></span>Preview</button>
			 
			<!-- <input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="readyBagRetPreviewBtn" onClick="javascript:readyBagTrfListReport(2);" /> -->
			
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="readyBagRetListBtnId"
					href="/jewels/manufacturing/transactions/readyBagRetMt.html">Listing</a> 
					
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="readyBagRetMtSubmitBtn"  onclick="javascript:readyBagRetMtSave()" />
			
			</div>

		</div>

	</div>
	
	
	<div class="form-group">
		<form:form commandName="readyBagRetMt" id="readyBagRetMtFormId"
			action="/jewels//manufacturing/transactions/readyBagRetMt/add.html"
			cssClass="form-horizontal readyBagRetMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success"> ${action}
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
						<label for="inputLabel3" class=".col-lg-2 text-right">Location:</label>
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
						<form:input path="invDate" cssClass="form-control"  disabled="true" />
						<form:errors path="invDate" />
					</div>
					
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="location.id" class="form-control" required="true">
							<form:option value="" label=" Select Location " />
							<form:options items="${locationMap}" />
						</form:select>
					</div>
					
				
				
				</div>
			</div>
				
	
		
			<div class="row">
			<div class="col-xs-12">
			
			
				<form:input type="hidden" path="id"/>
				<input type="hidden" id="pLocationIds" name="pLocationIds" />
				<input type="hidden" id="vTranDate" name="vTranDate" />
				
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
	
	
	
<div id="readyBagIssDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
					<div id="toolbar">
					
					<div class="row">
				
					<div class="col-sm-4">
					
					<a class="btn btn-info" style="font-size: 15px" type="button" id="readyBagPickupBtnId" 	onClick="javascript:openReadyBagReturnPage()"
					href="javascript:void(0)"><span
				></span>&nbsp;Pickup</a>
					</div>
					
					</div>
				
					
			
					
					</div>
					<div>
						<table id="readyBagRetTblId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-striped="true" data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
								<th data-field="stateRd" data-radio="true"></th>  
									
										<th data-field="partyCode" data-sortable="false" class="span5">Party</th>
										<th data-field="orderNo">Order No</th>
										<th data-field="styleNo">Style No</th>
										<th data-field="purity" data-sortable="false">Kt</th>
										<th data-field="color" data-sortable="false">Color</th>
										<th data-field="bagNm" data-sortable="false">Bag No</th>
										<th data-field="bagQty" data-sortable="false">Bag Qty</th>
										<th data-field="stone" data-sortable="false">Stone</th>
										<th data-field="carat" data-sortable="false">carat</th>
										
								</tr>
							</thead>
						</table>
						
					
					</div>
					
				
				
			</div>
			
			
			
		</div>
		

		
	

		
	</div>


	

</div>
	
	
	
	<script type="text/javascript">



	var canViewFlag=false;
	var disableFlg = false;
	var mtid;
	var mode;
	
		$(document)
				.ready(
						function(e) {

											
							

	$('#readyBagDivId #location\\.id').chosen();
	

							
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
							
							
							
							$(".saleMtForm")
									.validate(
											{
												rules : {
													
													invDate : {
														required : true,
													},
													
												

													'location.id' : {
														required : true,
													},
													
										
												},

												messages : {
													invNo :{
														remote :"Duplicate Invoice No"
													}
												}

											});
							
							
							
				

							$("#invDate").datepicker({
								dateFormat : 'dd/mm/yy'
							});
							
				

							if (window.location.href.indexOf('edit') != -1) {
								$("#vTranDate").val($("#invDate").val());
								$("#readyBagIssDtDivId").css('display', 'block');
								mtid="${mtid}";
								
							//	popReadyBagRetDtList();

								if(process('${currentDate}') > process($('#invDate').val())){

									disableFlg = true;
									popReadyBagRetDtList(disableFlg);
								//	$('#readyBagDivId .btn').hide();
								
								//	$('#readyBagDivId').find('input, textarea, select').attr('disabled','disabled');
							//	$('#readyBagRetListBtnId').show();
							//		$('#packMtExcelPreviewBtn').show();
									$('#readyBagRetMtSubmitBtn').hide();	
									$('#readyBagPickupBtnId').hide();
									
									
								}else{
									disableFlg = false;
									popReadyBagRetDtList(disableFlg);
									}
								
							}
							else{
								$("#vTranDate").val('${currentDate}');
								$("#invDate").val('${currentDate}');
								$("#invDate").val(new Date().toLocaleDateString('en-GB'));
								
							}


						});


		function process(date){
			   var parts = date.split("/");
			   return new Date(parts[2], parts[1] - 1, parts[0]);
			}

			var dtdata='';
				$('#readyBagRetTblId').bootstrapTable({}).on('load-success.bs.table',
					function(e, data) {
				   
				dtdata = $("#readyBagRetTblId").bootstrapTable('getData').length;
				
				 if(dtdata >0){
	
					    $('#pLocationIds').val($('#readyBagDivId #location\\.id').val());
						$('#readyBagDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
						}else{
							$('#readyBagDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
						}
					
				  })


		function openReadyBagReturnPage(){

			window.location.href = "/jewels/manufacturing/transactions/readyBagReturnList.html?mtId="+mtid;
			}

		function readyBagRetMtSave(){

			if($(".readyBagRetMtForm").valid()){
				$("form#readyBagRetMtFormId").submit();
				
			}
			}

		
		  $(document)
			.on(
				'submit',
				'form#readyBagRetMtFormId',
				 function(e){
					 $("#readyBagRetMtSubmitBtn").prop("disabled", true).addClass("disabled");
				});
		

		  function popReadyBagRetDtList(disableFlg) {
				$("#readyBagRetTblId")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/transactions/readyBagRetDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,
								});
			}

			function deleteReadyBagRetDt(e,dtId,approvalFlg){
				
				if(approvalFlg){
					displayDlg(
							e,
							'javascript:deleteDt('+dtId+');',
							'Delete',
							'Do you want to Delete  ?',
							'Continue');
					}else{
						alert("Cannot Delete");
						}
				
				
				}


			

	function deleteDt(dtId){
				
				$("#modalDialog").modal("hide");
								
								 $.ajax({
										url : "/jewels/manufacturing/transactions/readyBagRetDt/delete/"+ dtId + ".html",
										type : 'GET',
										success : function(data) {
											if(data === "1"){
												displayInfoMsg(event, this,'Delete sucessfully !');
												popReadyBagRetDtList();
											}else{
												displayMsg(event, this, data);
											}
										}
									}); 
				
			
			}



	function readyBagTrfListReport(val){
		
		
		$
		.ajax({
			url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=readyBagRet"+"&opt="+val,
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

	

	</script>
	
	
	
<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />