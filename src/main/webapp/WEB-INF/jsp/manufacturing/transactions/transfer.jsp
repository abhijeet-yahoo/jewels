<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalTransfer.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalBagSearch.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalBagSplit.jsp"%>

<!-- <link href="https://harvesthq.github.io/chosen/chosen.css" rel="stylesheet"/>
<script src="https://harvesthq.github.io/chosen/chosen.jquery.js"></script> -->



<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>


<style>
.flex, .flex > div[class*='col-'] {  
    display: -webkit-box;
    display: -moz-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    flex:1 0 auto;
}
</style>

<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Department Process Entry</span>
	</div>

	<div class="panel-body">
		
		<form:form commandName="tranMt" id="transferDump"
					action="/jewels/manufacturing/transactions/transfer/add.html"
				cssClass="transferForm">




			<div class="row">

			<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="trandate">TranDate</label>
								<form:input path="trandate" cssClass="form-control"  autocomplete="off"/>
								<form:errors path="trandate" />
						</div>
	
					</div>


				<div class="col-sm-3">

					<div class="form-group">
										
						<label class="control-label" for="dept">Department</label>
							<form:select path="deptFrom.id" id="deptFroom" 
								class="form-control" required="false" onChange="javascript:popDeptTo();">
								<form:option value="" label=" Select department " />
								<form:options items="${departmenttMap}" />
							</form:select>

						
					</div>

				</div>


				<div class="col-sm-3">

					<div class="form-group">

						<label class="control-label" for="bagNo">Bag No</label>
						
						<div class="input-group">
						<form:input path="bagMt.name" Class="form-control"
							onchange="javascript:pendingForApprovalPopup();" />
						<form:errors path="bagMt.name" />
						 <span class="input-group-btn">
                        <button type="button" class="btn btn-default glyphicon glyphicon-list"  onClick="javascript:openTransferTab()"></button>
                    </span>
						
						</div>

						
					</div>

				</div>









</div>

	<div class="row flex">
				<div class="panel panel panel-default col-sm-10">
					<div class="panel-body">
					<div class="row">

						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Party</label> <input
									type="text" id="partyName" name="partyName"
									class="form-control" readonly="readonly">

							</div>

						</div>


						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Order No</label> <input
									type="text" id="invNo" name="invNo" class="form-control"
									readonly="readonly">

							</div>

						</div>




						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Order Type</label> <input
									type="text" id="orderType" name="orderType"
									class="form-control" readonly="readonly">

							</div>

						</div>

						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Order Date</label> <input
									type="text" id="invDate" name="invDate" class="form-control"
									readonly="readonly">

							</div>

						</div>


						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Delivery Date</label> <input
									type="text" id="dispatchDate" name="dispatchDate"
									class="form-control" readonly="readonly">

							</div>

						</div>


						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Style No</label> <input
									type="text" id="styleNo" name="styleNo" class="form-control"
									readonly="readonly">

							</div>

						</div>

						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Qty </label> <input
									type="text" id="pcs" name="pcs" class="form-control"
									readonly="readonly">

							</div>

						</div>


						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="dept">Current Wt. </label> <input
									type="text" id="currentWt" name="currentWt"
									class="form-control" readonly="readonly">

							</div>

						</div>
						
						<div class="col-sm-2">

							<div class="form-group">

								<label class="control-label" for="stone">Stone </label>
								 <input
									type="text" id="vTotalStones" name="vTotalStones" class="form-control"
									readonly="readonly">
									 

							</div>
							
							
							

						</div>
						
						<div class="col-sm-2">
						
							<div class="form-group">

								<label class="control-label" for="stone">Carat </label>
								  <input
									type="text" id="vTotalCarats" name="vTotalCarats" class="form-control"
									readonly="readonly">

							</div>
							</div>
						
						
						

</div>


<div class="row">

	
				<div class="col-sm-8">
						
							<div class="form-group">

								<label class="control-label" for="remark">Remark </label>
								  <form:textarea path="remark" cssClass="form-control" />
									<form:errors path="remark" />

							</div>
							</div>
	

						<div class="col-sm-4">

							<div class="form-group">

								<label class="control-label" for="trfDept">Transfer Department</label> 
								<form:select path="deptTo.id" id="deptToo" class="form-control" required="true">
											<form:option value="" label="- Select department -" />
											<form:options items="${departmentToMap}" />
										</form:select>

							</div>

						</div>
						
						
						


</div>
					</div>

				</div>
				<!-- <ending the first 10 div> -->

				<div class="panel panel panel-default col-sm-2">
					<div class="panel-body">

						<div class="form-group">
							<a id="oImgTrfHRId" href="" data-lighter> <img id="ordImgTrfId"
								class="img-responsive"
								style="height: 150px; width: 150px"
								src="/jewels/uploads/manufacturing/blank.png" />
							</a>



						</div>






		<div class="form-group">
							<div>
								<input type="button" value="Split Bag" class="btn btn-primary"
									style="width: 150px;" onclick="javascript:bagSplit()">
							</div>
						</div>


					<div class="form-group">
							<div>
								<input type="button" value="Loss Book" class="btn btn-primary"
									style="width: 150px;" onclick="javascript:popLossBook()">
							</div>
						</div>

						<div class="form-group">
						<div>
								<input type="button" value="Bag History" class="btn btn-primary" id="bagHistoryDivId"
									style="width: 150px;" onclick="javascript:popBagHistory()">
							</div>
							

							
							
						</div>
						<div class="form-group">
							<div>
								<input type="button" value="Bag Search" class="btn btn-primary"
									style="width: 150px;" onClick="javascript:popBagSearch()">
							</div>
						</div>
							


						
						<div class="form-group">
							<div>
								<input type="submit" value="Transfer" class="btn btn-success"
									style="width: 150px;" id="tranSubmitBtn">
							</div>
						</div>
						<input type="hidden" name="id"> 
						<input type="hidden" name="vDepartmentId" id="vDepartmentId" />
						<input type="hidden" name="vBagNo" id="vBagNo" />
						<input type="hidden" name="vDepartmentToId" id="vDepartmentToId" />
						<input type="hidden" name="vBagQty" id="vBagQty" />
						<input type="hidden" name="vWeight" id="vWeight" />
						<input type="hidden" name="vRemark" id="vRemark" />
						<input type="hidden" name="vTranDate" id="vTranDate" />
					
						<input type="hidden" name="timeValBagHistoryPdf" id="timeValBagHistoryPdf" />
						<input type="hidden" name="xml" id="xml" value="${xml}"  />
					
						

					</div>

				</div>

			</div>
  				
    		</form:form>		
    		
    		<!-- Download BagHistory Pdf -->
  		
		<div style="display: none">		
				<form:form target="_blank"  id="bagHistoryForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="genBagHistoryRpt"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
		
	
		
<!-- 	<div class="panel">
	
	<div class="panel panel-body"> -->
	
<!-- 	<div class="row">
	
	<button class="btn btn-primary" id="btnMetalId" type="button" >Metal Details</button>
 	<button class="btn btn-primary " type="button"  id="btnStoneId"  onclick="javascript:popTranDt()" >Stone Details</button>
 
	
	</div> -->
	
 
		
		
<div class="row" id="tblMetalDivId" >
      
      <div id="toolbarDtMetal">
      <label>Metal Details:</label>
      </div>
      
      
      <div class="table-responsive">
		<table id="tranMetalTblId" data-toggle="table"
			data-toolbar="#toolbarDtMetal" 
			data-side-pagination="server"  data-striped="true"
			data-height="100px">
							
							<thead>
									<tr class="btn-primary">
										<th data-field="partNm" data-sortable="false">Part</th>
										<th data-field="purity" data-sortable="false">Purity</th>
										<th data-field="color" data-align="left">Color</th>
										<th data-field="qty" data-sortable="false">Qty</th>
										<th data-field="metalWt" data-sortable="false">Metal Wt</th>
									</tr>
								</thead>

  </table>
</div>
    
      
  
</div>




<div class="row" id="tblStoneDivId" >
      
    <div id="toolbarDt1">
      <label>Stone Details:</label>
      </div>
      
      
              <div class="table-responsive">
  		<table  id="tranDtTblId" data-toggle="table" data-side-pagination="server" 
  		data-toolbar="#toolbarDt1" data-pagination="false" data-striped="true"
  		data-height="550px" >
							 
								<thead>
									<tr class="btn-primary">
										<th data-field="partNm" data-sortable="false">Part</th>
										<th data-field="stoneType" data-sortable="true">StoneType</th>
										<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
										<th data-field="quality" data-align="left" data-sortable="true">Quality</th>
										<th data-field="size" data-align="left" data-sortable="true">Size</th>
										<th data-field="sieve" data-sortable="true">Sieve</th>
										<th data-field="sizeGroup" data-sortable="true">SizeGroup</th>
										<th data-field="stones" data-sortable="true">Stone</th>
										<th data-field="carat" data-sortable="true">Carat</th>
										<th data-field="setting" data-sortable="true">Setting</th>
										<th data-field="settingType" data-sortable="true">SettingType</th>
									</tr>
								</thead>
							</table>
							
			</div>	
      
  
</div>
























	
	
<!-- 	</div>
	
	
		
		
	
	
	
	</div>	
		 -->
 
	

	</div>




	</div>
	<!-- ending the panel body -->
<!-- ending the panel -->

<script type="text/javascript">
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
 					
		
						
						$("#trandate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
					
						$("#trandate").val('${currentDate}');
						
						
						if('${canEditTranddate}' === "false"){
							$("#trandate").attr("disabled","disabled");
						}
						
					 	if (window.location.href.indexOf('dep') != -1) {
					 		

							var vUrl = window.location.href;
							
							var deptNm = vUrl.substring(window.location.href
									.indexOf('dep') + 3, window.location.href
									.indexOf('bg'));
							

							
							$("#deptFroom").val(deptNm).trigger("chosen:updated");
							$('#deptFroom').trigger('onchange');
							

							
							var vBagNm = vUrl.substring(window.location.href
									.indexOf('bg') + 2);
							

							setTimeout(function(){
								
								$('#bagMt\\.name').val(vBagNm);
								$('#bagMt\\.name').trigger('onchange');
								
							} , 100)
							
							$('#backBtnDivId').css('display','block');

							}
						
						
						
						$(".transferForm")
								.validate(
										{
											
										
											rules : {
																														
											
											
											
												'bagMt.name' : {
													required : true,
											/* 		remote : {
														onchange: false,
														url : "<spring:url value='/manufacturing/transactions/transfer/bagNoAvailable.html' />",
														type : "get",
														data : {

															'deptFrom.id' : function() {
																return $(
																		"#deptFroom")
																		.val();
															}

														}
													} */

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
											  /* 'bagMt.name' : {
												  remote : "Bag not found on floor"
												}  */
											}

										});

						$("#bagMt\\.name")
								.autocomplete(
										{
											source : "<spring:url value='/manufacturing/transactions/transfer/list.html' />",
											minLength : 4,
											change: function( event, ui ) {
												
												validateBag();
											}
										});
						
						
			

						

					});
	
	
	function validateBag(){
		
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/transfer/bagNoAvailable.html' />?bagMt.name='
					+ $('#bagMt\\.name').val()
					+ "&deptFrom.id="
					+ $('#deptFroom').val(),
			type : 'GET',
			success : function(data) {
				if(data=='false'){
					
					var txtNm= $("#deptFroom option:selected").text();
					
					alert('Bag not In '+txtNm+' Department');
					$("#bagMt\\.name").val('');
				}
				
			}
		})
	}
	

	function displayBreakUp() {
	
			$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/transfer/display.html' />?BagNo='
							+ $('#bagMt\\.name').val()
							+ "&departmentId="
							+ $('#deptFroom').val(),
					type : 'GET',
					success : function(data) {

						if (Number(data) != -1 && Number(data) != -2) {

							var temp = data.split("#");
							$('#partyName').val(temp[0]);
							$('#invNo').val(temp[1]);
							$('#invDate').val(temp[2]);
							$('#orderType').val(temp[3]);
							$('#dispatchDate').val(temp[4]);
							$('#styleNo').val(temp[5]);
							$('#pcs').val(temp[6]);
							$('#currentWt').val(temp[7]);
																				
							$('#ordImgTrfId').attr('src', temp[8]);
							$('#oImgTrfHRId').attr('href', temp[8]);
							
							
							/* var tempEmpPcs = temp[9];
							var tempEmpStn = temp[10]; */
							
							$('#tranSubmitBtn').prop('disabled', false);
							
					/* 		if(Number(tempEmpPcs) != '-1'){
							
								if(Number(tempEmpPcs === '-2')){
									popUserInput(event, this);	
								}else if(Number(tempEmpPcs === '-4')){
									displayMsg(event, this,'Please Enter Production !');
									$('#tranSubmitBtn').prop('disabled', true);
								}
				
							}else{
								
								if(Number(tempEmpStn === '-2')){
									popUserInput(event, this);
								}else if(Number(tempEmpStn === '-4')){
									displayMsg(event, this,'Please Enter Production !');
									$('#tranSubmitBtn').prop('disabled', true);
								}
							} */
							
						
							
							
						} else {

							if (Number(data) == -1) {

								$('#partyName').val('');
								$('#invNo').val('');
								$('#invDate').val('');
								$('#orderType').val('');
								$('#dispatchDate').val('');
								$('#styleNo').val('');
								$('#pcs').val('0.0');
								$('#currentWt').val('0.0');
								
								/* $("#tranMetalTblId").html(""); */
								/* $("#tranDtTblId").html(""); */
								  
								
							
							} else {
								alert("please select the department");
							}

						}
						
						popTranDt();

					}

				});
				
			

	}

	/* function getLoss() {

		var vRecWt = $('#recWt').val();
		var vIssWt = $('#issWt').val();
		var vLoss = vRecWt - vIssWt;
		
		$('#loss').val(vLoss.toFixed(3));

	} */

	
	$(document).on('submit', 'form#transferDump', function(e) {
		
		$('#tranSubmitBtn').attr('disabled', 'disabled');
		
		
		var vDeptId = $('#deptFroom').val();
		$('#vDepartmentId').val(vDeptId);

		var vvBagNo = $('#bagMt\\.name').val();
		$('#vBagNo').val(vvBagNo);
		
		$('#vTranDate').val($('#trandate').val());

		var vDeptToId = $('#deptToo').val();
		$('#vDepartmentToId').val(vDeptToId);
		
		$('#vRemark').val($('#remark').val());
		
	
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		
		
	

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
	
			success : function(data, textStatus, jqXHR) {
					if (Number(data) == 1) {
					displayInfoMsg(event, this,'transfered sucessfully !');
					$('#partyName').val('');
					$('#invNo').val('');
					$('#invDate').val('');
					$('#orderType').val('');
					$('#dispatchDate').val('');
					$('#styleNo').val('');
					$('#pcs').val('0.0');
					$('#currentWt').val('0.0');
					$('#remark').val('');
					$('#deptToo').val('');
					$('#bagMt\\.name').val('');

					$('#deptToo').val('').trigger('chosen:updated');
					
					
					
					/* $("#tranMetalTblId").html(""); */
					//$("#tranDtTblId").html("");
					
				//	popTranDt();
					
					//$('#tranSubmitBtn').removeAttr('disabled');
					
					

				}else{
					
					
					
					if(Number(data) != '-1'){
						
						if(Number(data === '-2')){
							popUserInput(event, this);	
						}else if(Number(data === '-4')){
							displayMsg(event, this,'Please Enter Production !');
							$('#tranSubmitBtn').prop('disabled', true);
						}else{
							displayMsg(event, this,data);
						}
		
					}else{
						
						if(Number(data === '-2')){
							popUserInput(event, this);
						}else if(Number(data === '-4')){
							displayMsg(event, this,'Please Enter Production !');
							$('#tranSubmitBtn').prop('disabled', true);
						}else{
							displayMsg(event, this,data);
						}
					}
				}			
				popTranDt();
				
				$('#tranSubmitBtn').removeAttr('disabled');
				
			
				

			},
			error : function(jqXHR, textStatus, errorThrown) {

			}

		});

		e.preventDefault(); //STOP default action
	});
	
	function popDeptTo() {
		// alert("hello");
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/transfer/deptTo.html' />?departmentId='
							+ $('#deptFroom').val(),
					type : 'GET',
					success : function(data) {
						
						$("#deptToo").html(data);
						$("#deptToo").trigger("chosen:updated");
						
						$('#bagMt\\.name').val('');
						$('#partyName').val('');
						$('#invNo').val('');
						$('#invDate').val('');
						$('#orderType').val('');
						$('#dispatchDate').val('');
						$('#styleNo').val('');
						$('#pcs').val('0.0');
						$('#currentWt').val('0.0');
						$('#vTotalStones').val('');
						$('#vTotalCarats').val('');
						$('#issWt').val('0.0');
						$('#ordImgTrfId').attr('src', '');
						$('#oImgTrfHRId').attr('href', '');
					
					//$("#ordImgId").css('display', 'block');
						
					
					/* $("#tranMetalTblId").html(""); */
					/* $("#tranDtTblId").html(""); */
					  
				popTranDt();
						

					}
				});
	}
	
	function popTranDt() {
			$("#tranDtTblId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/transfer/tranDt/listing.html?BagNo="
									+ $('#bagMt\\.name').val()
									+ "&departmentId="
									+ $('#deptFroom').val(),
							}); 
			
			
			
			$("#tranMetalTblId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/transfer/tranMetal/listing.html?BagNo="
							+ $('#bagMt\\.name').val()
							+ "&departmentId="
							+ $('#deptFroom').val(),
					}); 
		     
	
		
		
		
		
		
	
		
	}
	
	
	
	$('#tranDtTblId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#tranDtTblId")
						.bootstrapTable('getData'));
				
				
				var vStones = 0.0;
				var vCarat = 0.0;
				
				$.each(JSON.parse(data), function(idx, obj) {

					vStones += Number(obj.stones);
					vCarat += Number(obj.carat);
					
					
				});
				
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
							
				
			});
	
	
	
	
	
	
	function popUserInput(e, id) {
		displayDlg(
				e,
				'javascript:enableDisableTransferBtn();',
				'Warning-Information',
				'Production Already Entered ? <br><strong>Press Continue To Enter Again</strong>',
				'Continue');
		
	}
	
	
	function enableDisableTransferBtn(){
		
		$("#modalDialog").modal("hide");
		//$('#tranSubmitBtn').removeAttr('disabled');
		$('#tranSubmitBtn').prop('disabled', true);
		
	}
	
function openTransferTab(){
	
		
		if($('#deptFroom').val() !=''){
			
			$('#myTransferModal').modal('show');
			
			setTimeout(function(){
				
				$("#modalTransferTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/modalTransfer/listing.html?deptId="+$('#deptFroom').val()
									
						});

				
			}, 200);
						
			
		
					
			
		}
		else{ 
			 displayMsg(this, null, 'Please Select Department');
		}
		
		
		
	}


$('#modalTransferTblId').bootstrapTable({}).on(
		'dbl-click-row.bs.table',
		function(e, row, $element) {
			
			var bgNm=jQuery.parseJSON(JSON.stringify(row)).bagNo;
			
			$('#bagMt\\.name').val(bgNm);
			
			$('#myTransferModal').modal('hide');
			/* $('#bagMt\\.name').focus(); */
					$('#bagMt\\.name').trigger('onchange');
			
		})
		
		
		function popBagSearch() {
	
		
	 	$('#myBagSearchModal').modal('show');
	 	
	 	$('#name').val($('#bagMt\\.name').val());	
	 	$('#name').trigger('onblur');
}
		
		
function popLossBook(){
	
	var vBagNm = $('#bagMt\\.name').val();
	if (!vBagNm) {
		displayMsg(this, null, 'PLEASE SELECT THE BAG NO !');
	}else{
		window.location.href = "/jewels/manufacturing/transactions/lossBook.html?dep"+$('#deptFroom').val()+"bg"+vBagNm+"mOpt1"+"&tranDate="+$("#trandate").val()
				+"&editTrandate="+'${canEditTranddate}';
	}

}


function bagSplit(){
	
	$('#myBagSplitModal').modal('show');
 	$('#bagId').val($('#bagMt\\.name').val());
 	$('#bagQty').val($('#pcs').val());
 	$('#vWeight').val($('#currentWt').val());
 	$('#splitWeight').val('');
 	$('#splitQty').val('');
	$('#splitBtnId').removeAttr('disabled', 'disabled');
 	
}
	

function popBagHistory(){
	

	
	$
	.ajax({
		url : "/jewels/manufacturing/transactions/bagHistory/report.html?bagNo="+$('#bagMt\\.name').val()+ "&xml="+ $('#xml').val(),
		type : 'POST',
		success : function(data, textStatus, jqXHR) {
			$('#timeValCommonPdf').val(data);
			$("#genBagHistoryRpt").trigger('click');
			
			
			
		}
	});
	
}



function bagRollBack(mtId){
	
	$.ajax({
		url:"/jewels/manufacturing/transactions/transfer/rollBack.html?mtId="+mtId,
		type:'GET',
		success : function(data, textStatus, jqXHR) {
		
		}
		
	});
	
}

function pendingForApprovalPopup(){
	
	$.ajax({
		url:"/jewels/manufacturing/transactions/transfer/pendingForApprovalPopup.html?bagNo="+$('#bagMt\\.name').val(),
		type:'GET',
		success : function(data, textStatus, jqXHR) {

			if(data != "false"){
				$('#bagMt\\.name').val('');
				displayMsg(event, this, data);
				}else{
					displayBreakUp();
					}
		}
		
	});
	
}
	
	
</script>


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet"  />

 








