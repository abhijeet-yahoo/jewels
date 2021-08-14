<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalTransfer.jsp"%>



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



	
<form:form commandName="lossBook" id="lossBookForm"
			action="/jewels/manufacturing/transactions/lossBook/add.html"
			cssClass="lossBookTransferTab">
<div class="panel panel-primary">
	<div class="panel-heading">
		<span style="font-weight: bold;">Loss / Production</span>
	</div>

	<div class=panel-body>
	

	<div class="row">


			<div class="col-sm-10">
			<div class="row">
			
				<div class="form-group col-sm-4">
						
							<label class="control-label" for="trandate">TranDate</label>
								<form:input path="trandate" cssClass="form-control"  autocomplete="off"/>
								<form:errors path="trandate" />
						
					</div>
			
			<div class="form-group col-sm-4">

									<label class="control-label" for="dept">Department</label>
									<form:select path="department.id" class="form-control" onchange="javascript:popEmployee();">
										<form:option value="" label=" Select department " />
										<form:options items="${departmentMap}" />
									</form:select>


					</div>
					
						<div class="form-group col-sm-4">

									<label class="control-label" for="bagNo">Bag No</label>

									<div class="input-group">
										<form:input path="bagMt.name" cssClass="form-control"
											onchange="javascript:pendingForApprovalPopup();" />
										<form:errors path="bagMt.name" />
										<span class="input-group-btn">
											<button type="button"
												class="btn btn-default glyphicon glyphicon-list"
												onClick="javascript:openLossBookTab()"></button>
										</span>

									</div>


								</div>
								
								<div class="form-group col-sm-4">

									<label class="control-label" for="style">Style No.</label>
									<input type="text" id="styleNo" class="form-control" readonly="readonly" name="styleNo">


					</div>

					<div class="form-group col-sm-4">
						
							<label class="control-label" for="part">Part Name</label>
							<div id="partNmDivId">
							<form:select path="partNm.id" class="form-control"
								onchange="javascript:popGetPartDetail();">
								<form:option value="" label=" Select Part Name " />
								<form:options items="${partNmMap}" />
							</form:select>


						</div>


					</div>


					<div class="form-group col-sm-2">

						<label class="control-label" for="purity">Purity</label>
						<form:input path="purity.name" cssClass="form-control"
							readonly="true" />
						<form:errors path="purity.name" />


					</div>
					
					
					<div class="form-group col-sm-2">

						<label class="control-label" for="partWt">Part Weight</label>
						<form:input path="partWt" cssClass="form-control"
							readonly="true" />
						<form:errors path="partWt" />


					</div>
					
					<div class="form-group col-sm-2">

						<label class="control-label" for="bagPcs">Bag Qty</label>
						<input type="text" id="bagPcs" name="bagPcs"class="form-control" readonly="readonly" />
		
					</div>
					
					
							<div class="form-group col-sm-2" id="prodQtyDivId">

						<label class="control-label" for="prodQty">Prod. Qty</label>
						<form:input path="qty" cssClass="form-control" onchange="javascript:splitBagDetail()" autocomplete="off"/>
						<form:errors path="qty" />
		
					</div>
					


			
					
					
					
					<div class="form-group col-sm-2">

						<label class="control-label" for="grossWt">Gross Weight</label>
						<form:input path="grossWt" cssClass="form-control"
							readonly="true" />
						<form:errors path="grossWt" />


					</div>
					
								
					<div class="form-group col-sm-2">

						<label class="control-label" for="currentWt">Current Weight</label>
						<form:input path="currentWt" cssClass="form-control" onchange="calculateLoss()" autocomplete="off"/>
						<form:errors path="currentWt" />


					</div>
					
							<div class="form-group col-sm-2">

						<label class="control-label" for="loss">Loss Weight</label>
						<form:input path="loss" cssClass="form-control"
							readonly="true" />
						<form:errors path="loss" />


					</div>


						<div class="form-group col-sm-2">

							<label class="control-label" for="lossInPerc">Loss % On
								In Wt</label>
							<form:input path="lossPercOnIn" cssClass="form-control"
								readonly="true" />
							<form:errors path="lossPercOnIn" />


						</div>



						<div class="form-group col-sm-2">

						<label class="control-label" for="lossOutPerc">Loss % On Out Wt</label>
						<form:input path="lossPercOnOut" cssClass="form-control"
							readonly="true" />
						<form:errors path="lossPercOnOut" />


					</div>
					
					
					<div class="form-group col-sm-2">
						<label class="control-label" for="extraWt">Extra Wt</label>
						<form:input path="extraWt" cssClass="form-control"
							readonly="true" />
						<form:errors path="extraWt" />


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
					
							<div class="form-group col-sm-4">

						<label class="control-label" for="labour">Labour Type</label>
						<div id="prodLabourDivId">
							<form:select path="prodLabourType.id" class="form-control">
								<form:option value="" label=" Select ProdLabour " />
								<form:options items="${prodLabourMap}" />
							</form:select>

						</div>


					</div>


					<div class="form-group col-sm-2">

						<label class="control-label" for="labour">Employee</label>
						<div id="employeeDivId">
							<form:select path="employee.id" class="form-control"
							onchange="javascript:popUpdateEmployeeInTable()">
								<form:option value="" label=" Select Worker " />
								<form:options items="${employeeMap}" />
							</form:select>

						</div>


					</div>
					
					<div class="col-sm-4">
						
							<div class="form-group">

								<label class="control-label" for="remark">Remark </label>
								 <!--  <input type="text" id="remark" name="remark" class="form-control"/> -->
								  <textarea rows="2" cols="2" id="remark" name="remark" class="form-control"></textarea>
							</div>
							</div>


				<div class="form-group col-sm-4" id="deptToDivId" style="display: none">
					<label class="control-label" for="lossOutPerc">Transfer Department</label>
				<div id="deptToId">
					<select class="form-control">
							<option value="">Select Department</option>
							</select>
				</div>
				
						
							
							</div>


				</div>
				
						
				
						
								
								
								
								
		
				
				</div>	
				
			<div class="panel col-sm-2" style="height: 175px">
				<div class="panel-body">
				
				<div class="panel panel-default">
					<a id="oImgHRId" href="" data-lighter> <img id="ordImgId"
					class="img-responsive" style="height: 150px" 
					src="/jewels/uploads/manufacturing/blank.png" />
				</a>
				</div>
				</div>

			
			</div>
			
			</div>
			
					



<div class="row">
<div class="col-sm-1">
<input type="submit" value="Save" class="btn btn-primary"
			id="lossBookSaveBtnId" />
</div>			


<div class="col-sm-2" >
<input type="button" value="Save & Transfer" class="btn btn-primary"
			id="lossBookSaveTrfBtnId" onclick="jacascript:saveloss()" style="display: none" />
</div>			



<div class="col-sm-1" id="backBtnDivId" style="display: none">
<input type="button" value="Back" class="btn btn-primary" onClick="javascript:lossBackBtn()">
</div>			
	
	
		
			
			
<form:hidden path="id"/>
<input type="hidden" id="empStoneTblData" name="empStoneTblData">
<input type="hidden" id="vRemark" name="vRemark">
<input type="hidden" id="toDeptNm" name="toDeptNm">
<input type="hidden" id="tranDate" name="tranDate">
<input type="hidden" id="saveTrfFlg" name="saveTrfFlg">
</div>


</div>

</div>




</form:form>




<div id="previosProdBtnDivId" style="display: none;">

	<div class="form-group">
		<div>
			<input type="button" value="Display Previous Production"
				class="btn btn-info" style="width: 200px;"
				onClick="javascript:stonePreviousProdList()">
		</div>
	</div>
</div>




<div id="displayPreviosProdDivId" style="display: none;">		
						
						<div class="panel panel-primary">
	<div class="panel-heading">
		<span>Display Previous Production</span>
	</div>

		<div class="form-group" >
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="stonePreviousProdTblId" data-toggle="table"
								data-toolbar="#toolbarDt" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
								<thead>
									<tr>
									
									<th data-field="employee" data-align="left">Employee</th>
									<th data-field="partNm" data-align="left">Part No.</th>
									<th data-field="stoneType" data-align="left">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-align="left">Quality</th>
									<th data-field="sieve"  data-align="left">Sieve</th>
									<th data-field="size" data-align="left">Size</th>
									<th data-field="sizeGroup" data-align="left">Size Group</th>
									<th data-field="stones" data-align="left">Stone</th>
									<th data-field="carat" data-align="left">Carat</th>
									<th data-field="setting" data-align="left">Setting</th>
									<th data-field="setType" data-align="left">Set Type</th>
									<th data-field="rate" data-align="left">Rate</th>
								
									
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
  </div>

 </div>



<!-- lossBook End -->
 <div id="stoneProductionDivId" style="display: none">

<div class="panel panel-primary">
<div class="panel-heading">
		<span>Stone Production</span>
</div>

	<div class="form-group" >
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="stoneProductionTblId" data-toggle="table"
							data-toolbar="#toolbarDt" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true">Select</th>
									<th data-field="employee" data-formatter="employeeFormatter">Employee</th>
									<th data-field="partNm" data-align="left">Part No.</th>
									<th data-field="stoneType" data-align="left">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									
									<th data-field="quality" data-align="left">Quality</th>
									<th data-field="sieve" id="sieveId" data-align="left">Sieve</th>
									<th data-field="size" data-align="left">Size</th>
									
									<th data-field="sizeGroup">Size Group</th>
									<th data-field="stone" data-formatter="stoneFormatter">Stone</th>
									<th data-field="carat" data-formatter="caratFormatter">Carat</th>
									<th data-field="setting"  data-formatter="settingFormatterCombo">Setting</th>
									<th data-field="setType" data-formatter="settingTypeFormatterCombo">Set Type</th>
									<th data-field="rate">Rate</th>
									<th data-field="action1"> Split </th>
									<!-- <th data-field="employeeId">empId</th> -->
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
  </div>
</div>

<script type="text/javascript">

var saveTrfFlg=false;
var vOpt=0;
var mOpt=0


	$(document)
			.ready(
					function(e) {				
						
						$("#trandate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
						$('#loss').val("0");
						
		 	if (window.location.href.indexOf('dep') != -1) {
						var vUrl = window.location.href;
				
						
						
						var deptNm = vUrl.substring(window.location.href
								.indexOf('dep') + 3, window.location.href
								.indexOf('bg'));
						
						$('#department\\.id').val(deptNm);
						
						$('#department\\.id').trigger('onchange');

						
						var vBagNm = vUrl.substring(window.location.href
								.indexOf('bg') + 2, window.location.href.indexOf('mOpt'));
						
						
						vOpt = vUrl.substring(window.location.href
								.indexOf('mOpt') + 4);
						
						
						var vx = vUrl.substring(window.location.href.indexOf('mOpt') + 4);
						
						mOpt = vx.substring(0,1);
						
						var vTrandate = vUrl.substring(window.location.href
								.indexOf('tranDate') + 9, window.location.href.indexOf('&editTrandate'));
						
					 	var canEdit = vUrl.substring(window.location.href
								.indexOf('&editTrandate') + 14); 
						
						
					
						$('#bagMt\\.name').val(vBagNm);
						$('#bagMt\\.name').trigger('onchange');
						
						$("#trandate").val(vTrandate);
						if(canEdit != 'true'){
							$("#trandate").attr('disabled','disabled');	
						}
						
						
						
						$('#backBtnDivId').css('display','block');
						$('#deptToDivId').css('display','block');
						$('#lossBookSaveTrfBtnId').css('display','block');
						
						$('#prodQtyDivId').css('display','none');
						
						
						
					
						}else{
							
						
						
							$("#trandate").val('${currentDate}');
							
							
							if('${canEditTranddate}' === "false"){
								$("#trandate").attr("disabled","disabled");
							}
											
						}
		 	
				
						 	
						 
						 	

						$(".lossBookTransferTab")
								.validate(
										{
											rules : {
												'department.id' : {
													required : true,

												},
												
												'partNm.id' : {
													required : true,

												},
												
											'currentWt' :{
													required : true,
												},
												
										'bagMt.name' : {
													required : true,
													/* 		remote : {
														url : "<spring:url value='/manufacturing/transactions/lossBook/bagNoAvailable.html' />",
														type : "get",
														data : {

															'department.id' : function() {
																return $('#department\\.id').val();
															}

														}
													}*/

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
/* 												'bagMt.name' : {
													remote : "Bag not found on floor"
												} */
											}

										});

						$("#bagMt\\.name")
								.autocomplete(
										{
											source : "<spring:url value='/manufacturing/transactions/lossBook/list.html' />",
											minLength : 4,
											change: function( event, ui ) {
												
												validateBag();
											}
										});

					});
					
					
					
function validateBag(){
	$
	.ajax({
		url : '<spring:url value='/manufacturing/transactions/lossBook/bagNoAvailable.html' />?bagMt.name='
				+ $('#bagMt\\.name').val()
				+ "&department.id="
				+ $('#department\\.id').val(),
		type : 'GET',
		success : function(data) {
			if(data=='false'){
				
				var txtNm= $("#department\\.id option:selected").text();
				
				alert('Bag not In '+txtNm+' Department');
				$("#bagMt\\.name").val('');
			}
			
		}
	})
}				

	function openLossBookTab() {

		if ($('#department\\.id').val() != '') {

			$('#myTransferModal').modal('show');

			setTimeout(
					function() {

						$("#modalTransferTblId")
								.bootstrapTable(
										'refresh',
										{
											url : "/jewels/manufacturing/transactions/modalTransfer/listing.html?deptId="
													+ $('#department\\.id')
															.val()

										});

					}, 100);

		} else {
			displayMsg(this, null, 'Please Select Department');
		}

	}


	$('#modalTransferTblId').bootstrapTable({}).on('dbl-click-row.bs.table',
			function(e, row, $element) {

				var bgNm = jQuery.parseJSON(JSON.stringify(row)).bagNo;

				$('#bagMt\\.name').val(bgNm);

				$('#myTransferModal').modal('hide');
				$('#bagMt\\.name').trigger('onchange');

			})

	function popPartNm() {

		var vDeptId = $('#department\\.id').val();
		var vBagNo = $('#bagMt\\.name').val();

		if (vDeptId && vBagNo == '') {
			displayMsg(this, null, 'Please Select Department and Bag Name');
		} else {

			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/lossBook/partNm/list.html' />?departmentId='
								+ vDeptId + "&bagNo=" + vBagNo,
						type : 'GET',
						success : function(data) {
							
							var vPart ='';
							$.each(JSON.parse(data), function(key, value) {
								if(key ==='partData'){
									$("#partNmDivId").html(value);		
									
								}else if(key === 'defPart'){
									
									vPart =value;
								
									
								}else if(key === 'imgPath'){
									$('#ordImgId').attr('src', value);
									$('#oImgHRId').attr('href', value);
									
								}else if(key ==='styleNo'){
									$("#styleNo").val(value);
								}else if(key ==='totCarat'){
									$("#vTotalCarats").val(value);
								}else if(key ==='totStone'){
									$("#vTotalStones").val(value);
								}
								else if(key ==='stonProd'){
									
									if(value === true){
										$('#stoneProductionDivId').css('display','block');
										$('#previosProdBtnDivId').css('display','block');
										
										popLossBookTblList();
									}else{
										$('#stoneProductionDivId').css('display','none');
										$('#previosProdBtnDivId').css('display','none');
										$('#displayPreviosProdDivId').css('display', 'none');
									}
									
								}
								
								
							});
							
							
							
							
							$('#purity\\.name').val('');
							$('#partWt').val('');
							$('#prodLabourType\\.id').val('');
							
							
							$('#employee\\.id').val('');
							
							$('#grossWt').val('');
							$('#currentWt').val('');
							$('#loss').val('');
							$('#lossPercOnIn').val('');
							$('#lossPercOnOut').val('');
							
							$('#partNm\\.id').val(vPart);
							$('#partNm\\.id').trigger('onchange');
							
							$('#currentWt').focus();

							
						}
					});
		}
	}

	function popGetProdLabourType() {

		var vDeptId = $('#department\\.id').val();
		var vBagNo = $('#bagMt\\.name').val();
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/lossBook/prodLabour/details.html' />?departmentId='
							+ vDeptId + "&bagNo=" + vBagNo,
					type : 'GET',
					success : function(data) {

						$("#prodLabourDivId").html(data);

					}
				});
	}

	function popGetPartDetail() {
		

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/lossBook/partNm/detail.html' />?bagNo='
							+ $('#bagMt\\.name').val()
							+ '&partNmId='
							+ $('#partNm\\.id').val(),
					type : 'GET',
					success : function(data) {
						$.each(JSON.parse(data), function(key, value) {

							$('#' + key).val(value)
							if(key ==='bagPcs'){
								$('#qty').val(value)
								
							}

						});

					}
				});

	}

	function popEmployee() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/lossBook/employee/list.html' />?departmentId='
							+ $('#department\\.id').val(),

					type : 'GET',
					success : function(data) {
						$("#employeeDivId").html(data);
						
					}
				});
		
		
		
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/transfer/deptTo.html' />?departmentId='
					+ $('#department\\.id').val()+"&dropDownId=todeptId",

			type : 'GET',
			success : function(data) {
				$("#deptToId").html(data);
				
			}
		});
		
		
		
		$('#bagMt\\.name').val('');
		$('#styleNo').val('');
		$('#partNm\\.id').val('');
		$('#purity\\.name').val('');
		$('#partWt').val('');
		$('#prodLabourType\\.id').val('');
		
		
		$('#employee\\.id').val('');
		
		$('#grossWt').val('');
		$('#currentWt').val('');
		$('#loss').val('');
		$('#lossPercOnIn').val('');
		$('#lossPercOnOut').val('');
		
		

		
		
	}

	function popLossBookTblList() {
		$("#stoneProductionTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/empStoneProduction/fromTranDt/listing.html?deptId="
									+ $("#department\\.id").val()
									+ "&bagNo="
									+ $('#bagMt\\.name').val()
									+ "&splitQty="
									+ $('#qty').val(),

						});

	}
	
	var settingSrNo = 0;
	var settingFlag = true;
	var settingData = '';
	var settingJsonData='';
 	function settingFormatterCombo(value){
 		
 		var tempId = 'setting'+Number(settingSrNo++);
 		
 		var tempData =  '<select id='+tempId+' name='+tempId+' class="form-control" aria-required="true" aria-invalid="false" style="width:120px" onchange="javascript:popSetting(this)"><option value="">- Select Setting -</option>';
 		
 		if(settingFlag === true){
 			$.ajax({
 				url:"/jewels/manufacturing/masters/settinglist.html",
 				type:"GET",
 				dataType:"JSON",
 				success:function(data){
 					settingJsonData =data;
 			
 				},
 				async:false
 			});
 			
 			settingFlag = false;
 		}
 		
 		
 		settingData='';
		$.each(settingJsonData,function(key,val){
			 if(value === val){
				settingData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
			}else{
				settingData += "<option value='"+key+"'>"+val+"</option>";
			}
			
		});
 		
 		return tempData+''+settingData+''+'</select>';
	} 

 	function popSetting(param){
 		setZero();
 		var tempId = param.id.substring(7);
 		 $('#stoneProductionTblId').bootstrapTable('updateRow', {
			index : Number(tempId),
			row : {
				setting : param.value,
			}
		});
 		
 		$('#'+param.id).val(param.value);
 		
 	}
	
	
	var settingTypeSrNo = 0;
	var settingTypeFlag = true;
	var settingTypeData = '';
	var settingTypeJsonData='';
 	function settingTypeFormatterCombo(value){
 		 		
 		var tempId = 'settingType'+Number(settingTypeSrNo++);
 		
 		var tempData =  '<select id='+tempId+' name='+tempId+' class="form-control" aria-required="true" aria-invalid="false" style="width:145px" onchange="javascript:popSettingType(this)"><option value="">- Select Set-Type -</option>';
 		
 		if(settingTypeFlag === true){
 			$.ajax({
 				url:"/jewels/manufacturing/masters/settingTypeList.html",
 				type:"GET",
 				dataType:"JSON",
 				success:function(data){
 					settingTypeJsonData=data;
 				},
 				async:false
 			});
 			
 			settingTypeFlag = false;
 		}
 		
 		settingTypeData='';
 		$.each(settingTypeJsonData,function(key,val){
			 if(value === val){
				settingTypeData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
				}else{
					settingTypeData += "<option value='"+key+"'>"+val+"</option>";
				}
		});
 		
 		return tempData+''+settingTypeData+''+'</select>';
	} 
 	
 	function popSettingType(param){
 		setZero();
 		var tempId = param.id.substring(11);
 		$('#stoneProductionTblId').bootstrapTable('updateRow', {
			index : Number(tempId),
			row : {
				setType : param.value,
			}
		});
 		
 		$('#'+param.id).val(param.value);
 		
 	}
 	
 	var caratSrNo = 0;
	function caratFormatter(value){
		
		
		var tempId = 'carat' + Number(caratSrNo++);
		return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateCarat(this)"  />';
	}
	
	function updateCarat(param){
		
		
		setZero();
 		var tempId = param.id.substring(5);
 		$('#stoneProductionTblId').bootstrapTable('updateRow', {
			index : Number(tempId),
			row : {
				carat : param.value,
			}
		});
 		
 		$('#'+param.id).val(param.value);
 		
 		
 		
 	}
	
	var stoneSrNo = 0;
	function stoneFormatter(value){
 				
		var tempId = 'stone' + Number(stoneSrNo++);
		return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateStone(this)"  />';
		
	}
	
	function updateStone(param){
		
		
		setZero();
 		var tempId = param.id.substring(5);
 				
 		$('#stoneProductionTblId').bootstrapTable('updateRow', {
			index : Number(tempId),
			row : {
				stone : param.value,
			}
		});
 		
 		$('#'+param.id).val(param.value);
 		
 		 		
 	}
	
	
	
	var empSrNo = 0;
	var empFlag = true;
	var empDataJson='';
	var empData = '';
	function employeeFormatter(value){
		

	
 		var tempId = 'employeee'+Number(empSrNo++);
 		
 		var tempData =  '<select id='+tempId+' name='+tempId+' class="form-control" aria-required="true" aria-invalid="false" style="width:120px" onchange="javascript:popEmployeeList(this)"><option value="">- Select Employee -</option>';
 		
 		if(empFlag === true){
 			$.ajax({
 				url:"/jewels/manufacturing/masters/employeeList.html?depts="+ $('#department\\.id')
				.val(),
 				type:"GET",
 				dataType:"JSON",
 				success:function(data){
 					empDataJson =data;
 										
 				},
 				async:false
 			});
 			
 			empFlag = false;
 			
 		}
 		
 		
 		empData="";
 		$.each(empDataJson,function(key,val){
			 if(value === val){
				empData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
			}else{
				empData += "<option value='"+key+"'>"+val+"</option>";
				
			}
			
		});
 		
 		
 		return tempData+''+empData+''+'</select>';
 		
	} 

 	function popEmployeeList(param){
 	
 		setZero();
 		var tempId = param.id.substring(9);
 		
 		 $('#stoneProductionTblId').bootstrapTable('updateRow', {
			index : Number(tempId),
			row : {
				employee : param.value,
			}
		});
 		
 		$('#'+param.id).val(param.value);
 	
 		
 	}
 	
 	function calculateLoss(){


 	$('#currentWt').val(Math.round($('#currentWt').val()*1000)/1000);
 	
 	var tempLoss=0.0;
 	var lossExtra;
 		$.ajax({
			url:"/jewels/manufacturing/transactions/lossValidation.html?depts="+ $('#department\\.id').val(),
			type:"GET",
			success:function(data){
				
				tempdata = data.split("^");
				
				var lossPerc=tempdata[0];
				var extraWttt=tempdata[1];
				
				lossExtra  =($('#grossWt').val()*lossPerc)/100;
				
				lossExtra=lossExtra.toFixed(3);
				
						
			tempLoss=Math.round(($('#grossWt').val()-$('#currentWt').val())*1000)/1000;
			$('#extraWt').val(0.0);
			if(tempLoss < 0){
	 			
	 			
	 			if('${adminRightsMap}' === 'true'){
	 				
	 				var losss = (tempLoss * -1);
		 			var extraAddWt =(losss/$('#grossWt').val())*100;
		 			extraAddWt=extraAddWt.toFixed(3);
		 			$('#extraWt').val(extraAddWt);
	 				
	 				if(Number(extraAddWt) > extraWttt){
		 				$('#lossBookSaveBtnId').attr('disabled', 'disabled');
			 			$('#lossBookSaveTrfBtnId').attr('disabled', 'disabled');
			 			
			 			displayMsg(this,null,'Can not transfer with excess wt');
			 			
		 			}else{
		 				
		 				$('#lossBookSaveBtnId').removeAttr('disabled');
			 			$('#lossBookSaveTrfBtnId').removeAttr('disabled');
		 			}
	 			}else{
	 				
	 				
			 			$('#lossBookSaveBtnId').attr('disabled', 'disabled');
			 			$('#lossBookSaveTrfBtnId').attr('disabled', 'disabled');
			 			displayMsg(this,null,'Current Wt Can Not Be Greater Than Gross Wt,Contact Admin');
			 			
			 		
		 			
	 			}
	 			
	 			
	 			
	 			
	 			
	 		}else{

	 		
	 			if('${adminRightsMap}' === 'false'){
			 		
			 		if(tempLoss>lossExtra){
			 			
			 			$('#lossBookSaveBtnId').attr('disabled', 'disabled');
			 			$('#lossBookSaveTrfBtnId').attr('disabled', 'disabled');
			 			displayMsg(this,null,'Loss is greater than allow %');
			 			
			 		}else{
			 			
			 			
			 			$('#lossBookSaveBtnId').removeAttr('disabled');
			 			$('#lossBookSaveTrfBtnId').removeAttr('disabled');
			 		}
			 		
			 	}else{
			 		$('#lossBookSaveBtnId').removeAttr('disabled');
		 			$('#lossBookSaveTrfBtnId').removeAttr('disabled');
			 	}
	 			
	 	  	}
					 		
		 		if(Number(tempLoss)>Number($('#partWt').val())){
		 			displayMsg(this,null,'Invalid Wt ,Loss Is Greater Than Part Wt');
		 			$('#currentWt').val('');
		 			$('#loss').val('');
		 			$('#lossPercOnIn').val('');
		 			$('#lossPercOnOut').val('');
		 			
		 		}else{
		 			
		 			$('#loss').val(tempLoss);
		 			
		 			var xx =(Number(tempLoss)/Number($('#grossWt').val()))*100;
		 			if(xx>0){
		 			
		 				var inPerc  = Math.round(Number(xx)*100)/100;
		 				$('#lossPercOnIn').val(inPerc);
		 				
		 			}else{
		 				$('#lossPercOnIn').val('0.0');
		 			}
		 					 			
		 			var yy =(Number(tempLoss)/Number($('#currentWt').val()))*100;
		 			if(yy>0){
		 				var outPerc= Math.round(Number(yy)*100)/100;
		 				$('#lossPercOnOut').val(outPerc);
		 			}else{
		 				$('#lossPercOnOut').val('0.0');
		 			}
		 			
		 			
		 		/* 	var inPerc=Math.round((Number(tempLoss)/Number($('#grossWt').val())*100)*100)/100;
		 			var outPerc=Math.round((Number(tempLoss)/Number($('#currentWt').val())*100)*100)/100; */		 			
		 			
		 		}		
			},
			async:false
		});	
 		
 	}
 	
 	
 	
 	
 	
 	
 	
 	function popSplitRecords(){
 	 		
		setZero();
 		
 		
 		
 		var tblRow='';
 	 	$('#stoneProductionTblId').bootstrapTable({}).on(
 	 			'click-row.bs.table',
 	 			function(e, row, $element) {
 	 				 
 	 				tblRow = row;
 	 		 		tblRow.id=0;		
 	 			});
 		
 	setTimeout(function(){
 		
 		
 		$("#stoneProductionTblId").bootstrapTable('insertRow', {
            index: 0,
            
            row: {
               
                stone: jQuery.parseJSON(JSON.stringify(tblRow)).stone,
                carat: jQuery.parseJSON(JSON.stringify(tblRow)).carat,
                partNm:jQuery.parseJSON(JSON.stringify(tblRow)).partNm,
                bagSrNo: jQuery.parseJSON(JSON.stringify(tblRow)).bagSrNo,
                employee: jQuery.parseJSON(JSON.stringify(tblRow)).employee,
                settingId: jQuery.parseJSON(JSON.stringify(tblRow)).settingId,
                setTypeId: jQuery.parseJSON(JSON.stringify(tblRow)).setTypeId,
                stoneType: jQuery.parseJSON(JSON.stringify(tblRow)).stoneType,
                shape: jQuery.parseJSON(JSON.stringify(tblRow)).shape,
                quality: jQuery.parseJSON(JSON.stringify(tblRow)).quality,
                sizeGroup: jQuery.parseJSON(JSON.stringify(tblRow)).sizeGroup,
                stone: jQuery.parseJSON(JSON.stringify(tblRow)).stone,
                carat: jQuery.parseJSON(JSON.stringify(tblRow)).carat,
                setting: jQuery.parseJSON(JSON.stringify(tblRow)).setting,
                setType: jQuery.parseJSON(JSON.stringify(tblRow)).setType,
                sieve: jQuery.parseJSON(JSON.stringify(tblRow)).sieve,
                size: jQuery.parseJSON(JSON.stringify(tblRow)).size,
                rate: jQuery.parseJSON(JSON.stringify(tblRow)).rate,
                tranMtId: jQuery.parseJSON(JSON.stringify(tblRow)).tranMtId,
                action1: jQuery.parseJSON(JSON.stringify(tblRow)).action1,
                
                
            } 
          });

 		
 	}, 50)
 	 	
 		 	
 		
 		
 	}
 	
 	
 	function setZero(){
 		
 		empSrNo = 0;
 		stoneSrNo = 0;
 		caratSrNo = 0;
 		settingTypeSrNo = 0;
 		
 		settingSrNo = 0;
 		
 		
 	}
 	
 	 	
 	function popUpdateEmployeeInTable(){
 		
 		
 		
 		 var ab=document.getElementById("employee.id");
		 var taxNm=ab.options[ab.selectedIndex].text;
 		
 		
 		var data =JSON.stringify($('#stoneProductionTblId').bootstrapTable('getData'));
 		 $.each(JSON.parse(data),function(idx,obj){
 			 			 
 			 $('#stoneProductionTblId').bootstrapTable('updateRow', {
 					index : idx,
 					row : {
 						 employee : taxNm,
 						

 					}
 				}); 
 			 
 			//employeeFormatter(value);
 			 
 			 
 			 
 		 });
 		 
 
 	}
 	
 	
	$(document).on('submit', 'form#lossBookForm', function(e) {
		
		$('#lossBookSaveBtnId').attr('disabled', 'disabled');
		
		$('#vRemark').val($('#remark').val());
				
		
			var ab=document.getElementById("todeptId");
		 var taxNm=ab.options[ab.selectedIndex].text;
		
		$('#toDeptNm').val(taxNm);
	
		$('#tranDate').val($('#trandate').val());
		$('#saveTrfFlg').val(saveTrfFlg);
		
		
		if ($('#stoneProductionTblId').bootstrapTable('getSelections').length > 0) {
			var data = JSON.stringify($("#stoneProductionTblId").bootstrapTable('getSelections'));
			$('#empStoneTblData').val(data);
		}
		
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");
		
		$.ajax({
			
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {

				$('#lossBookSaveBtnId').removeAttr('disabled');
				
			/* 	if(saveTrfFlg == true){
					
					if(data ==='1'){
						savetrf();
						$('#lossBookSaveTrfBtnId').removeAttr('disabled');
						$('#partNm\\.id').trigger('onchange');
						
						$('#currentWt').val('');
			 			$('#loss').val('');
			 			$('#lossPercOnIn').val('');
			 			$('#lossPercOnOut').val('');
			 			$('#prodLabourType\\.id').val('');
			 			$('#employee\\.id').val('');
			 					 			
			 			popLossBookTblList();
			 			
			 			stonePreviousProdList();
						
						
					}else{
						displayMsg(this,null,data);
						$('#lossBookSaveTrfBtnId').removeAttr('disabled');
					}
					
					
					
				} */
				if(saveTrfFlg==true){
					if(data ==='1'){
						displayInfoMsg(event, this,'Data Transfered Sucessfully !');
						saveTrfFlg=false;
						
						$('#bagMt\\.name').val('');
						$('#styleNo').val('');
						$('#partNm\\.id').val('');
						$('#purity\\.name').val('');
						$('#partWt').val('');
						$('#bagPcs').val('');
						$('#qty').val('');
						$('#prodLabourType\\.id').val('');
						$('#employee.id').val('');
						$('#grossWt').val('');
						$('#currentWt').val('');
						$('#loss').val('');
						$('#lossPercOnIn').val('');
						$('#lossPercOnOut').val('');
						$('#remark').val('');
						
						//$('#partNm\\.id').trigger('onchange');
						
						$('#currentWt').val('');
			 			$('#loss').val('');
			 			$('#lossPercOnIn').val('');
			 			$('#lossPercOnOut').val('');
			 			$('#prodLabourType\\.id').val('');
			 			$('#employee\\.id').val('');
			 					 			
			 			//popLossBookTblList();
			 			
			 			//stonePreviousProdList();
			 			$('#previosProdBtnDivId').css('display','none');
			 			$('#stoneProductionDivId').css('display','none');
			 			$('#displayPreviosProdDivId').css('display', 'none');
					}else{
							
							 if(Number(data === '-5')){
									displayMsg(event, this,'Can Not Transfer To Rejection From LossBook !');
									
								}
								
							else if(Number(data === '-4')){
									displayMsg(event, this,'Please Enter Production !');
									
								}else{
									displayMsg(event, this,data);
								}
				
							
					}
					$('#lossBookSaveTrfBtnId').removeAttr('disabled');
				}else{
					if(data ==='1'){
						displayInfoMsg(this,null,'Loss Booked Successfully');
						
					/* 	document.location.reload(true); */
					
						$('#currentWt').val('');
						$('#bagPcs').val('');
						$('#qty').val('');
			 			$('#loss').val('');
			 			$('#lossPercOnIn').val('');
			 			$('#lossPercOnOut').val('');
			 			$('#prodLabourType\\.id').val('');
			 			$('#employee\\.id').val('');
					
						$('#partNm\\.id').trigger('onchange');
					
						
			 					 			
			 			popLossBookTblList();
			 			
			 			//stonePreviousProdList();
			 			
			 			$('#displayPreviosProdDivId').css('display', 'none');
						
					}else{
						displayMsg(this,null,data);
					}
				}
		
				
				
	
				
			}
			
			
			
		});
		
		e.preventDefault(); //STOP default action
		
	})
	
	
	
	function stonePreviousProdList() {

		$('#displayPreviosProdDivId').css('display', 'block');

		var dept = $('#department\\.id').val();
		
		var vBag =$('#bagMt\\.name').val();
		
		if (!dept && !vBag) {
			displayMsg(this, null, 'Department Or Bag Not Selected');
		} else {

			$("#stonePreviousProdTblId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/empStoneProduction/listing.html?deptId="
										+ dept
										+ "&bagNo="
										+ $('#bagMt\\.name').val(),
							});
		}

	}

	function lossBackBtn() {
			
		if(mOpt=='1')
				window.location.href = "/jewels/manufacturing/transactions/transfer.html?dep"+ $('#department\\.id').val()+"bg"+$('#bagMt\\.name').val();
		else 
			window.location.href = "/jewels/manufacturing/transactions/multiBagTransfer.html?dep"+ $('#department\\.id').val();
	}
	
	
	function savetrf(){
		
		$('#vRemark').val($('#remark').val());
				
		var postData={'vDepartmentId': $('#department\\.id').val(),
				'vBagNo':$('#bagMt\\.name').val(),
				'vDepartmentToId':$('#todeptId').val(),
				'vRemark': $('#vRemark').val(),
				'tranDate' : $('#trandate').val()};
		
		
		
	$.ajax({
			
			url : "/jewels/manufacturing/transactions/transfer/trfSave.html",
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				
				
				
				if (Number(data) == 1) {
					displayInfoMsg(event, this,'Data Transfered Sucessfully !');
					saveTrfFlg=false;
					
					$('#bagMt\\.name').val('');
					$('#styleNo').val('');
					$('#partNm\\.id').val('');
					$('#purity\\.name').val('');
					$('#partWt').val('');
					$('#prodLabourType\\.id').val('');
					$('#employee.id').val('');
					$('#grossWt').val('');
					$('#currentWt').val('');
					$('#loss').val('');
					$('#lossPercOnIn').val('');
					$('#lossPercOnOut').val('');
					$('#remark').val('');
					
					
				
				}else{
					if(Number(data) != '-1'){
						
					 if(Number(data === '-5')){
							displayMsg(event, this,'Can Not Transfer To Rejection From LossBook !');
							
						}
						
					else if(Number(data === '-4')){
							displayMsg(event, this,'Please Enter Production !');
							
						}
					else if(data.indexOf("Error")){
						displayMsg(event, this,'Please Enter Remark !');
						$('#lossBookSaveTrfBtnId').removeAttr('disabled');
						
					}
					else{
							displayMsg(event, this,data);
						}
		
					}else{
						
					 if(Number(data === '-4')){
							displayMsg(event, this,'Please Enter Production !');
					}else if(Number(data === '-1')){
						displayMsg(event, this,'Please Select Transfer Department or Contact Admin !');
					}else{
							displayMsg(event, this,data);
						}
					}
				}			
				
				
				$('#lossBookSaveTrfBtnId').removeAttr('disabled');
				
			}
			
			
			
		});

		
		
	}

	
	function saveloss(){
		if($('#todeptId').val()!==''){
			
			$('#lossBookSaveTrfBtnId').attr('disabled', 'disabled');
			saveTrfFlg=true;
			$("form#lossBookForm").submit();
			
		}else{
			displayMsg(event, this,'Transfer Department Not Selected');
		}
		
		
		
	}
	
	
	
	function splitBagDetail(){
		var bagQty=$('#bagPcs').val();
		var splitQty =$('#qty').val();
		
		if(Number(splitQty) > Number(bagQty)){
			displayMsg(event, this,'Split Qty Is Greater Than Bag Qty ,Can Not Split Production');
			$('#qty').val(bagQty);
		}else{
			var partWt=$('#partWt').val()/bagQty;
			var grosswt= $('#grossWt').val()/bagQty;
			
			$('#partWt').val(partWt*splitQty);
			$('#grossWt').val(grosswt*splitQty);
			popLossBookTblList();
			
			
		}
	}


	function pendingForApprovalPopup(){
		
		$.ajax({
			url:"/jewels/manufacturing/transactions/transfer/pendingForApprovalPopup.html?bagNo="+$('#bagMt\\.name').val(),
			type:'GET',
			success : function(data, textStatus, jqXHR) {

				if(data != "false"){

					displayMsg(event, this, data);
					}else{
						popPartNm();
						popGetProdLabourType();
						}
			}
			
		});
		
	}

 	
</script>

<script src="/jewels/js/common/tran.js"></script>
<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">
<script src="/jewels/js/jquery/jquery-ui.min.js"></script>
<script src="/jewels/js/common/common.js"></script>
<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<!-- <script src="/jewels/js/bootstrap/bootstrap-table.js"></script> -->



