<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">

 	<div class="panel-heading">
		<span style="font-size: 18px;">Kt Conversion</span>
	</div>
	
	<div class="panel panel-body">
	
	
		<div class="form-group">
			<form:form commandName="ktConversionMt" id="ktConvMtForm"
				action="/jewels/manufacturing/transactions/ktConversionMt/add.html"
				cssClass="form-horizontal ktConversionMtForm">
				
				<c:if test="${success eq true}">
					<div class="alert alert-success">KtConversionMt ${action} successfully!</div>
				</c:if>
	
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
					
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Invoice No </label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Date </label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Metal Type </label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">KT </label>
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
							<form:input path="cDate" cssClass="form-control" autocomplete="off"/>
							<form:errors path="cDate" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:select path="metal.id" class="form-control" onChange="javascript:popReqPuritys(this.value);">
								<form:option value="" label=" Select Metal " />
								<form:options items="${metalMap}" />
							</form:select>
						</div>
						<div class="col-lg-3 col-sm-3" id="reqPurity">
							<form:select path="reqPurity.id" class="form-control" onChange="javascript:getReqConversion(this.value);">
								<form:option value="" label=" Select Purity " />
								<form:options items="${purityMap}" />
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
							<label for="inputLabel3" class=".col-lg-2 text-right">Color </label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">MetalWt </label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Req Pure </label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Req Alloy </label>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:select path="reqColor.id" class="form-control">
								<form:option value="" label=" Select Color " />
								<form:options items="${colorMap}" />
							</form:select>
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="reqMetalWt" cssClass="form-control" onblur=" javascript:popPureAndAlloy(this.value)" />
							<form:errors path="reqMetalWt" />
						</div>
						<div class="col-lg-3 col-sm-3">				
							<form:input  path="reqPure" cssClass="form-control" readOnly="true" />
							<form:errors path="reqPure" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input  path="reqAlloy" cssClass="form-control" readOnly="true" />
							<form:errors path="reqAlloy" />
						</div>
					</div>
				</div>
				
				
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Total Issue Wt :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Received Wt :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Loss :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Scrap Wt :</label>
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="totIssWt" cssClass="form-control" readonly="true"  />
							<form:errors path="totIssWt" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="recWt" cssClass="form-control"  onblur="javascript:popLoss();popRecWtValidation()" />
							<form:errors path="recWt" />
						</div>
						<div class="col-lg-3 col-sm-3">				
							<form:input path="loss" cssClass="form-control"  readOnly="true" />
							<form:errors path="loss" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="scrapWt" cssClass="form-control"   />
							<form:errors path="scrapWt" />
						</div>
					</div>
				</div>
				
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="form-group">
					<div class="col-sm-12"> 
						<div id="ktMtBtnDivId" class="col-sm-offset-4 col-sm-1">
							<input type="submit" value="Save" class="btn btn-primary" id="ktConvMtSubmitBtn" style="width: 2.2cm" />
							<form:input type="hidden" path="id" />
							<form:input type="hidden" path="invNo" />
							<form:input type="hidden" path="uniqueId" />
							<form:input type="hidden" path="srNo" />						
						</div>
						<div class="col-sm-5"></div> 
						<div class="col-sm-6">
							<a class="btn btn-info" style="font-size: 15px;width: 5cm" type="button"
							href="/jewels/manufacturing/transactions/ktConversionMt.html">KtConversionMt Listing</a>
						</div>
					</div>
				</div>
				
				
				
				<div class="row">
					<input type="hidden" id="purConv" name="purConv" />
					<input type="hidden" id="mtId" name="mtId" />
					<input type="hidden" id="issPurConv" name="issPurConv" />
				</div>
				
			
		</form:form>
	</div>
	
	
	<div id="issueReceiveDivId" style="display: none">
	

		<div class="row">
			<div class="form-group">
				<div class="col-xs-12">
					<span class="col-sm-12 label label-default text-right"
						style="font-size: 18px;">Issue</span>
				</div>
			</div>
		</div>	
	
	
		
		<div class="form-group">
		
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="ktConvDtId" data-toggle="table"
								data-toolbar="#toolbarIS" data-pagination="false"
								data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" 
								data-height="220">
								<thead>
									<tr>
										<th data-field="purity" data-align="left">Purity</th>
										<th data-field="color" data-sortable="true">Color</th>
										<th data-field="freshMetalWt" data-sortable="true">MetalWt</th>
										<!-- <th data-field="usedMetalWt" data-sortable="true">UsedMetalWt</th> -->
										<th data-field="pureWt" data-sortable="true">PureWt</th>
										<th data-field="action1" data-align="center">Edit</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			 </div>
			
			<div id="ktDtAddId">
				<table class="line-items editable table">
					<tr class="panel-heading">
						<th>
							<span style="display:inline-block; width: 0.1cm;"></span>
								<a class="btn btn-info" style="font-size: 15px" type="button" id="addBtn"  href="javascript:goToKtConvDtEntry();">
									<span class="glyphicon glyphicon-plus"></span>&nbsp;Add
								</a>
						</th>	
					</tr>
				</table>
			</div>
				
			<div id="ktConvDtEntId" style="display: none">
				<form:form commandName="ktConversionDt" id="ktConvDtFormId"
					action="/jewels/manufacturing/transactions/ktConversionDt/add.html"
					cssClass="form-horizontal ktConversionDtForm">
		
					<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="btn-warning" class="panel-heading">
								<th>Purity</th>
								<th>Color</th>
								<th>MetalWt</th>
								<th>PureWt</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>   
									<form:select path="issPurity.id" class="form-control" onchange="javascript:getIssPurityConversion();refreshData()">
										<form:option value="" label=" Select Purity " />
										<form:options items="${purityMapp}" />
									</form:select>
								</td>
								<td>
									<form:select path="issColor.id" class="form-control">
										<form:option value="" label=" Select Color " />
										<form:options items="${colorMap}" />
									</form:select>
								</td>
								<td><form:input path="issFreshMetalWt"  cssClass="form-control" onblur="javascript:calculatePureWt()" /></td>
								<%-- <td><form:input path="issUsedMetalWt"   cssClass="form-control" onblur="javascript:popValidation();calculatePureWt()" /></td> --%>
								<td><form:input path="pureWt" cssClass="form-control" readonly="true"/></td>
							</tr>
							<tr>
								<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="issueSubmitBtnId" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popKtConvIssCancelBtn()" />
										<form:input type="hidden" path="id" id="issKtDtId" />
										<form:input type="hidden" path="uniqueId" />
										<input type="hidden" id="vMtId" name="vMtId" />
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
			
			
		</div>
		
		
				
	
			
			
			
	  </div>


	</div>  <!-- ending the panel body -->

</div>  <!-- ending the main panel -->


<script type="text/javascript">

 $(document)
	.ready(
		function(e) {
			
			$(".ktConversionMtForm")
					.validate(
							{
								rules : {
									cDate : {
										required : true,
									},
									'metal.id' : {
										required : true,
									},
									'reqPurity.id' : {
										required : true,
									},
									'reqColor.id' : {
										required : true,
									},
									reqMetalWt : {
										required : true,
										greaterThan : "0,0",
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
									reqMetalWt : {
										greaterThan : "This field is required and it must be in positive"
									}  
								},

							});
			
			
			//-----------------------LOC---------------------------//
			
			
			
			
			$(".ktConversionDtForm")
					.validate(
							{
								rules : {
									'issPurity.id' : {
										required : true,
									},
									'issColor.id' : {
										required : true,
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
							});
			
			

			

			$("#cDate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
			
			if('${canEditTranddate}' === "false"){
				$("#cDate").attr("disabled","disabled");
			}

			
			if(window.location.href.indexOf("edit") != -1){
				
				$('#issueReceiveDivId').css('display','block');
				
				var tempUrl = window.location.href;
				var tempVal = tempUrl.substring(tempUrl.indexOf("edit")+5, tempUrl.indexOf(".html"));
				$('#mtId').val(tempVal);
				
				popKtConversionDt();
				getReqConversion($('#reqPurity\\.id').val());
				backDatedValidation(tempVal);
				
			}else{
				$("#cDate").val('${currentDate}');
				
				$("#cDate").val(
						new Date().toLocaleDateString('en-GB'));
				
			}
			
			
			
	});
 
 
 
	function popReqPuritys(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/ktConvMt/purityKt/reqPurity/list.html' />?metalId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$('#reqPurity').html(data);
					}
				});
	}


	function getReqConversion(vSel) {
		
			$
			 .ajax({
					url : '<spring:url value='/manufacturing/transactions/ktConvMt/pureId/reqConv/list.html' />?reqPurityId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$('#purConv').val(data);
					}

				});
	}
 
	
	
	function popPureAndAlloy(vSel) {

		var purityConversionn = $('#purConv').val();

		if (purityConversionn > 0 || purityConversionn == null) {

			var pure = vSel * purityConversionn;
			var roundPure = pure.toFixed(3);
			$('#reqPure').val(roundPure);

			var alloy = vSel - roundPure;
			var roundAlloy = alloy.toFixed(3);
			$('#reqAlloy').val(roundAlloy);
		} else {
			displayMsg(this, null, 'please select the other KT,the purutiConv value is not there!');
			$('#reqPure').val('0.0');
			$('#reqAlloy').val('0.0');
		}

	}
	
	

	$(document)
	 .on(
		'submit',
		'form#ktConvMtForm',
		 function(e){
			 $("#ktConvMtSubmitBtn").prop("disabled", true).addClass("disabled");
			
		});
	
	
	
	//----------------//---ISSUE---//-----------//
	
	
	function goToKtConvDtEntry(){
		$('#ktConvDtEntId').css('display','block');
		$('#issPurity\\.id').val('');
		$('#issColor\\.id').val('');
		$("#issFreshMetalWt").val('0.0');
		/* $("#issUsedMetalWt").val('0.0'); */
		$("#pureWt").val('0.0');
		$('#issKtDtId').val('');
		$('#issPurity\\.id').focus();
	}
	
	
	function popKtConvIssCancelBtn(){
		$('#ktConvDtEntId').css('display','none');
	}
	
	
	
	function popKtConversionDt() {

		$("#ktConvDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/ktConversionDt/listing.html?MtId="
									+$('#mtId').val(),
						});
		
		}

	
/* 	
	function popValidation(){
		
		if($("#issFreshMetalWt").val() > 0 && $("#issUsedMetalWt").val() > 0){
			displayMsg(event, this ,"Only One MetalWt can be entered at time (Either Fresh Or Used)!");	
			$("#issFreshMetalWt").val('0.0');
			$("#issUsedMetalWt").val('0.0');
		}
		
	} */
	
	
	
	function getIssPurityConversion() {
		
		if(!!$('#issPurity\\.id').val()){
			
			$
			 .ajax({
					url : '<spring:url value='/manufacturing/transactions/ktConvDt/issPurityConv.html' />?issPurityId='
							+ $('#issPurity\\.id').val(),
					type : 'GET',
					success : function(data) {
						$('#issPurConv').val(data);
						
					
						
						
						//calculatePureWt();
					}
	
				});
		}else{
			displayMsg(event, this,' Please Select Purity ');
		}
		
	}
	
	
	
	function calculatePureWt(){ 
		
		var tempPurConv = $('#issPurConv').val();	
		if(tempPurConv === "" || tempPurConv === null){
			tempPurConv = 0.0;
		}
		var vPureWt = 0.0;
		if($('#issFreshMetalWt').val() > 0){
			vPureWt = $('#issFreshMetalWt').val() * tempPurConv;
		}/* else{
			vPureWt = $('#issUsedMetalWt').val() * tempPurConv;
		} */
		
		$('#pureWt').val(vPureWt.toFixed(3));		
		
	}
	
	
	var updateStatus = false;
	
	$(document)
		.on(
			'submit',
			'form#ktConvDtFormId',
			function(e) {
				
				$('#issueSubmitBtnId').attr('disabled', 'disabled');
				
				$('#vMtId').val($('#mtId').val());
				
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
							
								
								if (data === "-1") {
									displayMsg(event, this,'Metal Stock Insufficient');
								}else if(data === "-2"){
									displayMsg(event, this,'UsedMetal Stock Insufficient');
								}else if(data === "-3"){
									displayMsg(event, this,'MetalWt or UsedMetalWt is compulsary');
								}else if(data === "-4"){
									displayMsg(event, this,'Only One MetalWt can be entered at time (Either Fresh Or Used)!');
								}else if(data === "-11"){
									displayMsg(event, this,'Cannot Issue More than Metal Wt');
								}else {
									popKtConversionDt();
								    
									$('#issPurity\\.id').val('');
									$('#issColor\\.id').val('');
									$("#issFreshMetalWt").val('0.0');
									/* $("#issUsedMetalWt").val('0.0'); */
									$("#pureWt").val('0.0');
									
									 if(data == "2"){
										$('#ktConvDtEntId').css('display','none');
									}
									
									 updateStatus = true;
									
								}
								
								$('#issueSubmitBtnId').removeAttr('disabled');
								
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
								
							}
						});
				e.preventDefault(); //STOP default action
			});
	
	
	
	
	function editKtConversionDt(dtId){
		
		
		$.ajax({
			url :"/jewels/manufacturing/transactions/ktConversionDt/editValidation.html?dtId="+dtId,
			type:'GET',
			success:function(data){
				
				if(data === 'true'){
					
					$.ajax({
						url :"/jewels/manufacturing/transactions/ktConversionDt/edit/"+dtId+".html",
						type:'GET',
						success:function(data){
							$('#ktConvDtEntId').html(data);
						 getIssPurityConversion();
							$('#ktConvDtEntId').css('display','block');
							$('#issPurity\\.id').focus();
							//alert($('#issKtDtId').val());
						}
					});
					
				}else{
					displayMsg(event, this, 'You cannot edit this record');
				}
				
			}
		})
		
		
		
	}
	
	
	$('#ktConvDtId').bootstrapTable({})
		.on('click-row.bs.table',
			function(e, row, $element) {
				$('#ktConvDtEntId').css('display','none');
			});
	
	
	/* 
	function deleteKtConversionDt(e,DtId){
		
		displayDlg(
				e,
				'javascript:doDelteKtDt('+DtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
	
	}
	
	
	function doDelteKtDt(vDtId){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/ktConversionDt/delete/"+vDtId+".html",
			type:"GET",
			success : function(data){
				popKtConversionDt();
			}
		});
		
	} */
	
	
		
	$('#ktConvDtId').bootstrapTable({})
		.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#ktConvDtId").bootstrapTable('getData'));

				var vFreshWt = 0.0;
				/* var vUsedWt = 0.0; */
				
				$.each(JSON.parse(data), function(idx, obj) {
					vFreshWt	+= Number(obj.freshMetalWt);
					/* vUsedWt		+= Number(obj.usedMetalWt); */
				});
				
				
				$('#totIssWt').val(vFreshWt.toFixed(3));
				popLoss();
				
				if(updateStatus === true){
					updateTotIssWt();
				}
				
			});
			
				
		
		//------------------//--Receive------------------//
		
		
		
		function popLoss(){
			$('#loss').val(($('#totIssWt').val()-$('#recWt').val()).toFixed(3));
		}
		
		
		 function popRecWtValidation(){
			
			if(Number($('#recWt').val()) > Number($('#totIssWt').val())){
				$('#ktConvMtSubmitBtn').attr('disabled', 'disabled');
				displayMsg(event, this,'Received Wt cannot be greater than Total Issue Wt');
			}else{
				$('#ktConvMtSubmitBtn').removeAttr('disabled');
			}
			
		}
		
		
		 function updateTotIssWt(){
			 
				$.ajax({
					url:"/jewels/manufacturing/transactions/ktConversionDt/totIssWtUpdate.html?mtId="+$('#mtId').val()
							+"&totIssWt="+$('#totIssWt').val(),
					type:"GET",
					success: function(data){
						updateStatus = false;
					}
				})
				
		}
 
		 
		//---------//-backDatedValidation----//
		
		function backDatedValidation(mtId){
			
			$.ajax({
				url:"/jewels/manufacturing/transactions/ktconvMtValid.html?MtId="+mtId,
				type:"GET",
				success: function(data){
					//alert(data);
					
					if(data === 'false'){
						$('#ktConvMtSubmitBtn').attr('disabled', 'disabled');
						$('#ktMtBtnDivId').css('display','none')
						
						$('#addBtn').attr('disabled', 'disabled');
						$('#ktDtAddId').css('display','none');
					}else{
						$('#ktConvMtSubmitBtn').removeAttr('disabled');
						$('#ktMtBtnDivId').css('display','block')
						
						$('#addBtn').removeAttr('disabled');
						$('#ktDtAddId').css('display','block');
					}
				}
			})
			
		}
		
		
		function refreshData(){
			
			$('#issColor\\.id').val('');
			$('#issFreshMetalWt').val(0.0);
			$('#pureWt').val(0.0);
			
		}
 	

</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>
