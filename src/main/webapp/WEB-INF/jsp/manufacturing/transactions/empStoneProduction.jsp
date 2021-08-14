<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalTransfer.jsp"%>


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Stone Production</span>
	</div>

	<div class="form-group">
		<form:form commandName="empStoneProduction"
			action="<spring:url value='/manufacturing/transactions/empStoneProduction/add.html' />"
			cssClass="form-horizontal empStoneProductionTransferForm">

			<c:set var="option" value="User" />

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 text-right"></label> <label
					class="col-sm-2 text-right">Department:</label>
				<div class="col-sm-2">
					<form:select path="department.id" class="form-control"
						onChange="javascript:popEmployee();">
						<form:option value="" label=" Select department " />
						<form:options items="${departmentMap}" />
					</form:select>
				</div>
				<label class="col-sm-2 text-right">Bag No:</label>
				<div class="col-sm-2">
					<form:input path="bagMt.name" cssClass="form-control" onblur="javascript:displayDetails();"/>
					<form:errors path="bagMt.name" />
				</div>
				
				<div>					
				 	<button class="glyphicon glyphicon-list btn-primary"  style="font-size: 20px" type="button" onClick="javascript:openEmpStoneProductionTab()"></button>
							
					</div>
				
			</div>

		</form:form>
	</div>

</div>
<!-- ending the first panel -->

<div class="panel panel-primary" style="width: 100%">

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="stoneProdDetailId" data-toggle="table"
						data-toolbar="#toolbar" data-pagination="false"
						data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350">
						<thead>
							<tr class="btn-primary">
								<th data-field="employee" data-align="left">Employee</th>
								<th data-field="stoneType" data-align="left">Stone Type</th>
								<th data-field="shape" data-align="left">Shape</th>
								<th data-field="quality">Quality</th>
								<th data-field="sizeGroup">Size Group</th>
								<th data-field="stones" data-align="right">Stone</th>
								<th data-field="carat" data-align="right">Carat</th>
								<th data-field="setting">Setting</th>
								<th data-field="setType" data-align="left">Set Type</th>
								<th data-field="rate">rate</th>
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			onclick="javaScript:displayDiamondBreakup()"> <span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add
		</a>
	</div>
	
 <div id="empStoneProcdEntryId" style="display: none">
  <div id="rowId">
	<div class="form-group">
		<form:form commandName="empStoneProduction" id="empStoneProdBrkEntry"
			action="/jewels/manufacturing/transactions/empStoneProduction/add.html"
			cssClass="form-horizontal empStoneProdBrkEntryForm">
			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning">
						<th>Employee</th>
						<th>Stone Type</th>
						<th>Shape</th>
						<th>Quality</th>
						<th>Size Group</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<form:select path="employee.id" class="form-control">
								<form:option value="" label="- Select Employee -" />
								<form:options items="${employeeMap}" />
							</form:select>
						</td>

						<td>
							<form:select path="stoneType.id" class="form-control" disabled="true" >
								<form:option value="" label="- Select StoneType -" />
								<form:options items="${stoneTypeMap}" />
							</form:select>
						</td>
						
						<td>
							<form:select path="shape.id" class="form-control" disabled="true" >
								<form:option value="" label="- Select Shape -" />
								<form:options items="${shapeMap}" />
							</form:select>
						</td>
						
						<td>
							<form:select path="quality.id" class="form-control" disabled="true" >
								<form:option value="" label="- Select Quality -" />
								<form:options items="${qualityMap}" />
							</form:select>
						</td>
						
						<td>
							<form:select path="sizeGroup.id" class="form-control" disabled="true" >
								<form:option value="" label="- Select SizeGroup -" />
								<form:options items="${sizeGroupMap}" />
							</form:select>
						</td>
						
						
					</tr>
					<tr class="btn-warning">
						<th>Stone</th>
						<th>Carat</th>
						<th>Setting</th>
						<th>Set Type</th>
						<th>rate</th>
					</tr>
					<tr>
						
						<td><form:input path="stone" cssClass="form-control" disabled="true" /></td>
						<td><form:input path="carat" cssClass="form-control" disabled="true" /></td>
						<td><form:select path="setting.id" class="form-control">
								<form:option value="" label="- Select Setting -" />
								<form:options items="${settingMap}" />
							</form:select>
						</td>
						<td><form:select path="settingType.id" class="form-control" onchange="javascript:calRate()">
								<form:option value="" label="- Select Setting Type -" />
								<form:options items="${settingTypeMap}" />
							</form:select>
						</td>
						<td><form:input path="rate" cssClass="form-control"  /></td>

					</tr>
					<tr>
						<td colspan="11">
							<input type="submit" value="Save" class="btn btn-primary" onclick="editEmpStnProd();" />
							<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popEmpStnCancelBtn()"/>  
								<form:input type="hidden" path="id" /> 
								<form:input type="hidden" path="stoneType.id" />
								<form:input type="hidden" path="shape.id" />
								<form:input type="hidden" path="quality.id" />
								<form:input type="hidden" path="size" />
								<form:input type="hidden" path="sieve" />
								<form:input type="hidden" path="sizeGroup.id" />
								<form:input type="hidden" path="stone" />
								<form:input type="hidden" path="carat" />
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
 </div>
 </div>
 
	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>


	<!-------------/////////////////////////////------------ diamond breakup listing ----------------------//////////////////-->


 <div id="empStoneListingId" style="display: none">
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 label label-default"
					style="font-size: 16px; background-color: blue; richness: inherit;">Diamond
					Breakup</span>
			</div>
		</div>
	</div>
	
	
	
	
	<div id="empStoneTrfEntryId" style="display: block">
	   <div class="row">
		<div class="form-group">
			<div class="col-xs-12">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td colspan="10">
									<input type="button" value="Transfer" id="trfBtnSubmit" class="btn btn-primary" onClick="javascript:trfData(event);" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	
	 <div class="row">
		 <div class="form-group">
			<div class="col-xs-12">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="col-sm-6"></th>
								<th class="col-sm-1">Total Stone : </th>
								<th class="col-sm-1"> <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
										maxlength="7" size="7" disabled="true" />
								</th>
								<th class="col-sm-1">Total Carat : </th>
								<th class="col-sm-1"> <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									   maxlength="7" size="7" disabled="true" />
								</th>
								<th class="col-sm-2"></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	

	<div class="form-group" >
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="diamondBreakupListId" data-toggle="table"
							data-toolbar="#toolbarDt" data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true">Select</th>
									<th data-field="employee" data-align="left">Employee</th>
									<th data-field="stoneType" data-align="left">Stone Type</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-align="left">Quality</th>
									<th data-field="sizeGroup">Size Group</th>
									<th data-field="stones" data-align="right">Stone</th>
									<th data-field="carat" data-align="right">Carat</th>
									<th data-field="setting">Setting</th>
									<th data-field="setType" data-align="left">Set Type</th>
									<th data-field="rate">rate</th>
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
	
   <div id="empStoneEntryId" style="display: none">
	<div class="form-group">
		<form:form commandName="tranDt" id="diamondBreakupEntry"
			cssClass="form-horizontal diamondBreakupEntryForm">
			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning">
						<th>Employee</th>
						<th>Stone Type</th>
						<th>Shape</th>
						<th>Quality</th>
						<th>Size Group</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<select id="employee.idd" name="employee.idd" class="form-control">
								<option value="">- Select Employee -</option>
							</select>
						</td>
						<td><form:input path="stoneType" cssClass="form-control" disabled="true" /></td>
						<td><form:input path="shape" cssClass="form-control" disabled="true" /></td>
						<td><form:input path="quality" cssClass="form-control" disabled="true" /></td>
						<td><form:input path="sizeGroup" cssClass="form-control" disabled="true" /></td>
						
					</tr>
					<tr class="btn-warning">
						<th>Stone</th>
						<th>Carat</th>
						<th>Setting</th>
						<th>Set Type</th>
						<th>rate</th>
					</tr>
					<tr>
						
						<td><form:input path="stone" id="tStone" cssClass="form-control"  /></td>
						<td><form:input path="carat" id="tCarat" cssClass="form-control"  /></td>
						<td><form:select path="setting.id" id="tSetting" class="form-control" onchange="javascript:popRate()">
								<form:option value="" label="- Select Setting -" />
								<form:options items="${settingMap}" />
							</form:select>
						</td>
						<td><form:select path="settingType.id" id="tSettingType" class="form-control" onchange="javascript:popRate()">
								<form:option value="" label="- Select Setting Type -" />
								<form:options items="${settingTypeMap}" />
							</form:select>
						</td>
						<td><input type="text" id="tRate" name="rate"  class="form-control" /></td>

					</tr>
					<tr>
						<td colspan="11">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:updateTable();"/>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
   </div>
   
  

</div>
<!-- ending the second panel -->

<script type="text/javascript">

	$(document)
			.ready(
					function(e) {
						
						$(".empStoneProductionTransferForm").validate({
							
							rules : {
								'department.id' : {
									required : true,

								},
								'bagMt.name' : {
									required : true,
									remote : {
										url : "<spring:url value='/manufacturing/transactions/empStnProduction/bagNoAvailable.html' />",
										type : "get",
										data : {

											'department.id' : function() {
												return $("#department\\.id").val();
											}

										}
									}

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
								'bagMt.name' : {
									remote : "Bag not found on floor"
								}
							}

						});

						$("#bagMt\\.name")
								.autocomplete(
										{
											source : "<spring:url value='/manufacturing/transactions/empStoneProd/list.html' />",
											minLength : 2
										});

					});
	
	
	
	function displayDetails() {
		
		 $("#empStoneListingId").css('display', 'none');
		 $("#empStoneTrfEntryId").css('display', 'none');
		
		var dept = $('#department\\.id').val();
		if(!dept){
			displayMsg(this, null, 'Please select the department');
		}else{
			
			$("#stoneProdDetailId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/empStoneProduction/listing.html?deptId="
										+ $("#department\\.id").val()
										+ "&bagNo="
										+ $('#bagMt\\.name').val(),
							});
		}
	}

	
	function editEmpStoneProduction(dtId){
		$.ajax({
			url : "/jewels/manufacturing/transactions/empStoneProduction/edit/" + dtId
					+ ".html",
			type : 'GET',
			success : function(data) {
				
				$("#empStoneProcdEntryId").css('display', 'block');
				$("#rowId").html(data);
			}
		});
		
	}
	
	function editEmpStnProd(){
		
		$(document)
		.on(
				'submit',
				'form#empStoneProdBrkEntry',
				function(e) {
						
					var postData = $(this).serializeArray();
					var formURL = $(this).attr("action");

					$
							.ajax({
								url : formURL,
								type : "POST",
								data : postData,
								success : function(data, textStatus, jqXHR) {
									//alert(data);
									//displayDetails();
									//$('form#empPcsProductionEntry input[type="text"],texatrea, select').val('');
									//$('#department\\.id').val($('#vDeptId').val());
									//alert("1");
									displayDetails();
									$("#empStoneProcdEntryId").css('display', 'none');
									
								},
								error : function(jqXHR, textStatus,
										errorThrown) {
								}
							});
					e.preventDefault(); //STOP default action
				});

		
	}
	
	
	function confirmDelete(e,empStnPrId){
		displayDlg(e, 'javascript:deleteEmpStoneProduction('+empStnPrId+');','Stone Production', 'Do You Want To Delete', 'Continue');
	}
	
	
	
	function deleteEmpStoneProduction(empStnPrId){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/empStoneProduction/delete/" + empStnPrId + ".html",
			type : 'GET',
			success : function(data) {
				
				//alert(data);
				
				if(data === '-1'){
					displayDetails();
				}
				
			}
		});
		
		
		
	}
	

	function displayDiamondBreakup() {
		
		
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/empStoneProduction/bagCheck.html' />?deptId='
					+ $("#department\\.id").val()
					+ "&bagNo="
					+ $('#bagMt\\.name').val(),
			type : 'GET',
			success : function(data) {
				
				//alert(data);
				
				if(data === '-1'){
					
					var data = JSON.stringify($("#stoneProdDetailId").bootstrapTable('getData'));
					var obj = JSON.parse(data);
					var length = Object.keys(obj).length;
					
					if(Number(length) > 0){
						 confirmFun(event);
					}else{
					
					    $("#empStoneListingId").css('display', 'block');
						$("#empStoneTrfEntryId").css('display', 'block');
						
						$("#diamondBreakupListId")
								.bootstrapTable(
										'refresh',
										{
											url : "/jewels/manufacturing/transactions/empStoneProduction/fromTranDt/listing.html?deptId="
													+ $("#department\\.id").val()
													+ "&bagNo="
													+ $('#bagMt\\.name').val(),
										});
					}
					 

				}else{
					
					displayMsg(event, this,'Bag not in '+ $('select[name=department\\.id] option:selected').text() +' department !');
					$("#empStoneListingId").css('display', 'none');
					$("#empStoneTrfEntryId").css('display', 'none');
				}
				
			}
		});
		 
	}
	
	function confirmFun(e){
		displayDlg(e, 'javascript:doProduction();', 'Stone Production', 'Stone production already done are u want to do again?', 'Continue');
	}
	
	function doProduction(){
		$("#modalDialog").modal("hide");
		$("#empStoneListingId").css('display', 'block');
		$("#empStoneTrfEntryId").css('display', 'block');
		//$("#empStoneListingId").css('display', 'block');
		
		$("#diamondBreakupListId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/empStoneProduction/fromTranDt/listing.html?deptId="
									+ $("#department\\.id").val()
									+ "&bagNo="
									+ $('#bagMt\\.name').val(),
						});
		
	}
	
	
	function popEmployee() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/empStoneProduction/employee/list.html' />?departmentId='
							+ $('#department\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#employee\\.idd").html(data);
						$('#bagMt\\.name').val('');
					}
				});

	}
	
	
	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt.indexOf("</strong>"));
		}

		return dt;
	}
	

	 var rowIndex = -1;	
	 $('#diamondBreakupListId').bootstrapTable({}).on('click-row.bs.table',
		function(e, row, $element){
		 
	
		 
		 $("#empStoneEntryId").css('display', 'block');
		  
		 rowIndex = $element.attr('data-index');
		
		 var vEmpId = jQuery.parseJSON(JSON.stringify(row)).employeeId;
	
		 if(!vEmpId){
			$('#employee\\.idd').val(''); 
		 }else{ 
		 	$('#employee\\.idd option[value="' + vEmpId +'"]').prop("selected", true);
		 	
		 }
		 		 
		 var vStoneType = jQuery.parseJSON(JSON.stringify(row)).stoneType;

		 $('#stoneType').val(vStoneType);
		
		 var vShape = jQuery.parseJSON(JSON.stringify(row)).shape;
		 $('#shape').val(vShape);
		 
		 var vQuality = jQuery.parseJSON(JSON.stringify(row)).quality;
		 $('#quality').val(vQuality);
		 
		 var vSizeGroup = jQuery.parseJSON(JSON.stringify(row)).sizeGroup;
		 $('#sizeGroup').val(vSizeGroup);
		 
		 var vStone = getNumVal(jQuery.parseJSON(JSON.stringify(row)).stones);
		 $('#tStone').val(vStone);
		 
		 var vCarat = getNumVal(jQuery.parseJSON(JSON.stringify(row)).carat);
		 $('#tCarat').val(vCarat);
		 
		// var vSetting = jQuery.parseJSON(JSON.stringify(row)).setting;
		 var vSettingId = jQuery.parseJSON(JSON.stringify(row)).settingId;
		 $('#tSetting option[value="' + vSettingId +'"]').prop("selected", true);
		
		/*  if(!!vSettingId){
			
		 }else{
			 $("#tSetting option:contains(" + vSetting + ")").attr('selected', 'selected');
		 } */
		 
		
		 
		 //var vSettingType = jQuery.parseJSON(JSON.stringify(row)).setType;
		 var vSetTypeId = jQuery.parseJSON(JSON.stringify(row)).setTypeId;
		 $('#tSettingType option[value="' + vSetTypeId +'"]').prop("selected", true);
		 
		/*  if(!!vSetTypeId){
			
		 }else{
		 
			 if(!vSettingType){
				 $("#tSettingType option:contains(" + '- Select Setting Type -' + ")").attr('selected', 'selected');
			 }else{
				 $("#tSettingType option:contains(" + vSettingType + ")").attr('selected', 'selected');
			 }
		 } */
		 var vRate = getNumVal(jQuery.parseJSON(JSON.stringify(row)).rate);
		 $('#tRate').val(vRate);
		  
		 $('#employee\\.idd').focus();
		 
	 });

	 
		function updateTable(){
			
			var vEmp = $("#employee\\.idd :selected").text();
			var vEmpId = $("#employee\\.idd").val();
			var vShp = $('#shape').val();
			
			alert('vEmp '+vEmp);
			alert('vEmpId '+vEmpId);
			
			alert('rowIndex '+rowIndex);
			
			 if(vEmp == '- Select Employee -'){
				 
					alert('in if');
				 vEmp = "";
				 
				 $("#diamondBreakupListId").bootstrapTable('updateRow',
							{
								index : rowIndex,
								row : {
									employee 	: vEmp,
									stones	 	: $('#tStone').val(),
									carat	 	: $('#tCarat').val(),
									setting  	: $("#tSetting :selected").text(),
									setType  	: $("#tSettingType :selected").text(),
									settingId	: $("#tSetting").val(),
									setTypeId	: $("#tSettingType").val(),
									rate 	 	: $('#tRate').val(),			
								}
							});
				}else{
					
					alert('in else');
				
				// if user need to change the particular employee
				
				$("#diamondBreakupListId").bootstrapTable('updateRow',
						{
							index : rowIndex,
							row : {
								employee 	: vEmp,
								employeeId	: vEmpId,
								stones	 	: $('#tStone').val(),
								carat	 	: $('#tCarat').val(),
								setting 	: $("#tSetting :selected").text(),
								setType  	: $("#tSettingType :selected").text(),
								settingId	: $("#tSetting").val(),
								setTypeId	: $("#tSettingType").val(),
								rate 		: $('#tRate').val(),			
							}
					});
				
				// if user need to change the all the  employee
				
				 setEmpOnShape(vEmp,vShp,vEmpId);
					
				
				}
			 
			 clearInput();
			
		}
		
		
		 function setEmpOnShape(empName,shapeNm,vEmpId){
			
			var tempShape = shapeNm;
			
			var shapeNm = $.map( $("#diamondBreakupListId").bootstrapTable('getData'), function (row) {
	            	return row.shape;
	        });
			
			var chkEmpNm = $.map( $("#diamondBreakupListId").bootstrapTable('getData'), function (row) {
					return row.employee;	
            	
        	});
			
			
			shapeNm = shapeNm+"";
			chkEmpNm = chkEmpNm+"";
			var tempShapeNm = shapeNm.split(",");
			var tempChkEmpNm = chkEmpNm.split(",");
			
			var rowInd = 0; 
			
			if(empName != '- Select Employee -'){
				
				for(var i=0;i<tempShapeNm.length;i++){
					
					var arrShapeNm = tempShapeNm[i];
					
					if(tempShape ===  arrShapeNm){
						
						
						if(!tempChkEmpNm[i]){
							$("#diamondBreakupListId").bootstrapTable('updateRow',
									{
										index : rowInd,
										row : {
											employee 	: empName,
											employeeId	: vEmpId,
											settingId	: $("#tSetting").val(),
											setTypeId	: $("#tSettingType").val(),
										}
									});
						}
					
					  }
					
					rowInd++;
					
				}
				
				
			}
			
			 
		
		} 
		
		
		
		
		
		
		
		function clearInput(){
			
			
			$('#employee\\.idd').val(''); 
			$('#stoneType').val('');
			$('#shape').val('');
			$('#quality').val('');
			$('#sieve').val('');
			$('#sizeGroup').val('');
			$('#tStone').val('');
			$('#tCarat').val('');
			$('#tSetting').val('');
			$('#tSettingType').val('');
			$('#tRate').val('');
			
			 $("#empStoneEntryId").css('display', 'none');
			 $("#empStoneTrfEntryId").css('display', 'block');
			
		}
		
		
		function trfData(e) {
			displayDlg(e, 'javascript:transferData();', 'Transfer Bags', 'Do you want to transfer?', 'Continue');
		}
	
		
		function transferData() {
			
			$('#trfBtnSubmit').attr('disabled', 'disabled');
			
			$("#modalDialog").modal("hide");

			var data = JSON.stringify($("#diamondBreakupListId").bootstrapTable('getData'));
			
			
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/empStoneProduction/transfer.html",
				type : "POST",
				data : {
					pBagName : $('#bagMt\\.name').val(),
					pDept : $('#department\\.id').val(),
					rowIndex : rowIndex,
					data : data
				},
				success : function(data, textStatus, jqXHR) {
					$("#empStoneListingId").css('display', 'none');
					//$("#empStoneTrfEntryId").css('display', 'none');
					displayDetails();
					
					$('#trfBtnSubmit').removeAttr('disabled');
					
				},
				error : function(jqXHR, textStatus, errorThrown) {
				}
			});
		}
		
		
		

		
		function popRate(){
			
			//alert("Setting == "+$('#tSetting').val());
			//alert("Setting Type == "+$('#tSettingType').val());
			//alert("stone type = "+$('#stoneType').val());
			//alert("shape = "+$('#shape').val());
			
			$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/empStoneProduction/changeRate.html' />?settingId='
						+ $('#tSetting').val()
						+"&setTypeId="+$('#tSettingType').val()
						+"&stoneTypeNm="+$('#stoneType').val()
						+"&shapeNm="+$('#shape').val(),
				type : 'GET',
				success : function(data) {
					
					if(data === '-1'){
						displayMsg(this, null, 'Rate not found,check the settingRate master or contact admin');
						$('#tRate').val(0.0);
					}else{
						$('#tRate').val(data);
					}
					
				}
			});
			
			
			
			
			
		}
		
		
		
		
		function popEmpStnCancelBtn(){
			$('#empStoneProcdEntryId').css('display','none');
		}
		
	
		
		
		$('#diamondBreakupListId').bootstrapTable({})
		.on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#diamondBreakupListId").bootstrapTable('getData'));
					
					var vStones = 0.0;
					var vCarat = 0.0;
					
					$.each(JSON.parse(data), function(idx, obj) {

						vStones += Number(obj.stones);
						vCarat += Number(obj.carat);
						
					});
					
					
					$('#vTotalStones').val(" " + vStones);
					$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
								
					
				});
		
		
		function openEmpStoneProductionTab(){
			
		
			
			if($('#department\\.id').val() !=''){
				
				$('#myTransferModal').modal('show');
				
				setTimeout(function(){
					
					
					$("#modalTransferTblId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/modalTransfer/listing.html?deptId="+$('#department\\.id').val()
										
							});

					
				}, 100);
							
				
			
						
				
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
				$('#bagMt\\.name').trigger('onblur');

				
			})
		
		
		
		
	
</script>


<script src="/jewels/js/common/tran.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



