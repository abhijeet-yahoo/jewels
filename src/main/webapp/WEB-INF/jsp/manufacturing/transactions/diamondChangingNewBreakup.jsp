<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-body">

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>


		<div class="form-group">
			<form:form commandName="stoneInwardDt" id="stoneInwardDtt">

				<table class="line-items editable table table-bordered">
					<thead class="panel-heading">
						<tr class="btn-warning">
							<th>StoneType</th>
							<th>Shape</th>
							<th>Quality</th>
						
							<th style="display: none" id='sizeThId'>Size</th>
							<th>SizeGroup</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<div id="stoneTypeId">
									<form:select path="stoneType.id" class="form-control">
										<form:option value="" label=" SelectStoneType " />
										<form:options items="${stoneTypeMap}" />
									</form:select>
								</div>
							</td>
							<td><form:select path="shape.id" class="form-control"
									onchange="javascript:popQuality();popSize();popSizeGroup();">
									<form:option value="" label="- Select Shape -" />
									<form:options items="${shapeMap}" />
								</form:select></td>
							<td>
								<div id="qualityId">
									<form:select path="quality.id" class="form-control">
										<form:option value="" label="- Select Quality -" />
										<form:options items="${qualityMap}" />
									</form:select>
								</div>
							</td>
							<td style="display: none" id='sizeTdId'>
								<div id="sizeId">
									<form:select path="size" class="form-control">
										<form:option value="" label="- Select Size -" />
										<form:options items="${stoneChartMap}" />
									</form:select>
								</div>
							</td>
							<td>
								<div id="sizeGroupId">
									<form:select path="sizeGroup.id" class="form-control" onchange="javascript:popGroupwiseSize();">
										<form:option value="" label="- Select SizeGroup -" />
										<form:options items="${sizeGroupMap}" />
									</form:select>
								</div>
							</td>
							<td><input type="button" value="Fetch Data" class="btn btn-info" onclick="javascript:fetchData();">
									<input type="hidden" name="tempCarat" id="tempCarat" /> 
							</td>

						</tr>
					</tbody>
				</table>

			</form:form>
		</div>


				
		
		
		<div class="form-group" id="dsPId">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
					
						<div class="form-group col-sm-2">
						
							<label class="control-label" for="part">Part Name</label>
							<div id="partNmDivId">
							<select class="form-control"></select>
							</div>
					</div>
					
					<div id="groupWiseSizeDivId" style="display: none">
					<div class="form-group col-sm-2" >
							<label class="control-label" for="sise">Size</label>
							<div id="groupWiseSizeId">
							<select class="form-control"></select>
							</div>
					</div>
				</div>
						<div>
							<table id="diaChangNewStnId" data-toggle="table"
								data-toolbar="#toolbar" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300"
								data-striped="true">
								<thead>
									<tr class ="btn-primary">
										<th data-field="state" data-checkbox="true" ></th>
										<th data-field="serialNo" data-sortable="true">SrNo</th>
										<th data-field="stoneType" data-sortable="true">StoneType</th>
										<th data-field="shape" data-align="left">Shape</th>
										<th data-field="subShape" data-align="left">SubShape</th>
										<th data-field="quality" data-sortable="true">Quality</th>
										<th data-field="size" data-sortable="true">Size</th>
										<th data-field="sieve" data-sortable="true">Sieve</th>
										<th data-field="sizeGroup" data-sortable="true">SizeGroup</th>
										<th data-field="centerStone" data-formatter="centerStoneFormatter">CenterStone</th>
										<th data-field="balStone" data-sortable="true">BalStone</th>
										<th data-field="balCarat" data-sortable="true">BalCarat</th>
										<th data-field="trfStone" data-formatter="trfStoneFormatter">TrfStone</th>
										<th data-field="trfCarat" data-formatter="trfCaratFormatter">TrfCarat</th>
										<th data-field="setting" data-formatter="settingFormatterCombo">Setting</th>
										<th data-field="settingType" data-formatter="settingTypeFormatterCombo">Set Type</th>
									</tr>
								</thead>
							</table>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		
		
		
		<div class="row">
			<div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" id="transferBtnId" value="Transfer" onclick="javascript:trfData(event)" class="btn btn-primary">
				<a class="btn btn-info" onclick="javascript:goToMainPage();" style="font-size: 15px" type="button">Back</a>
				<input type="hidden" name="uBagNm" id="uBagNm" />
				<input type="hidden" name="tempDeptNm" id="tempDeptNm" />
				<input type="hidden" id="pPartNmId" name="pPartNmId">
				<input type="hidden" id="pTrandate" name="pTrandate">
				<input type="hidden" id="pSizeId" name="pSizeId">
			</div>
			
			
		</div>
		
		
	
		

	</div>
	<!-- ending the panel body -->
</div>
<!-- ending the main panel -->


<script type="text/javascript">
var vDiaStockType;

	$(document).ready(
			function(e) {
	
				if (window.location.href.indexOf('diamondChangingNewBreakup') != -1) {
					var vUrl = window.location.href;
					
					var vBagNm = vUrl.substring(window.location.href.indexOf('*') + 1,window.location.href.indexOf('_'));
					$('#uBagNm').val(vBagNm);
					var deptNm = vUrl.substring(window.location.href.indexOf('_') + 1,window.location.href.indexOf('tranDate'));
					
					$('#tempDeptNm').val(deptNm);
					
					var vTrandate = vUrl.substring(window.location.href.indexOf('tranDate')+9,window.location.href.indexOf('diaStockType'));
					
					$('#pTrandate').val(vTrandate);
					
					 vDiaStockType = vUrl.substring(window.location.href.indexOf('diaStockType')+13);
					
					
					if(vDiaStockType ==='sizeGroup'){
					
						$('#groupWiseSizeDivId').css('display','block');
					}else{
						$('#sizeTdId').css('display','block');
						$('#sizeThId').css('display','block');
						
					}
					
				}
	
				
				popPartNm();
				
			});

	
	
	function popPartNm(){

		$.ajax({
			url : '<spring:url value='/manufacturing/transactions/bag/partList.html' />?bagName='
					+ $('#uBagNm').val(),
			type : 'GET',
			success : function(data) {
				
				$("#partNmDivId").html(data);
			}
		});
	}


	function popQuality() {
			$.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/quality/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#qualityId").html(data);
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
						$("#sizeId").html(data);
						
					}
				});

	}

	function popSizeGroup() {
		
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/dc/sizeGroup/list.html' />?shapeId='
							+ $('#shape\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#sizeGroupId").html(data);
					}
				});

	}
	
	
	function popGroupwiseSize(){	
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/dc/groupWiseSize/list.html' />?sizeGroupId='
					+ $('#sizeGroup\\.id').val(),
			type : 'GET',
			success : function(data) {
				$("#groupWiseSizeId").html(data);
			}
		});
		
				fetchData();
		
	}
	
	
	function fetchData() {
	 	if(!$('#stoneType\\.id').val() ||  !$('#shape\\.id').val() || !$('#quality\\.id').val()){
			displayMsg(this, null, 'StoneType , Shape And Quality is compulsary to fetch data');
		} else{
			
			$("#diaChangNewStnId")
				.bootstrapTable('refresh',{
						url : "/jewels/manufacturing/transactions/dc/load/stoneInwardDt/data.html?stoneType="
							+ $('#stoneType\\.id').val()
							+"&shape="+$('#shape\\.id').val()
							+"&quality="+$('#quality\\.id').val()
							+"&size="+$("#size").val()
							+"&sizeGroup="+$("#sizeGroup\\.id").val()
							+"&bagNm="+$('#uBagNm').val()
			
					});	
			
		}  
		
	}
	
	
	
	
	

	
	
	function goToMainPage(){
		window.location.href = "/jewels/manufacturing/transactions/diamondChanging.html?**%*%&="+ $('#uBagNm').val();
	}
	
	
	
	//--------------flawless new changes ------//
	
	function centerStoneFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
		
	}
	
	
	
	
	var settingFlag = true;
	var settingData = '';
	var vData ='';
 	function settingFormatterCombo(value,row,index){
 			
 		var tempData =  '<select  class="form-control" aria-required="true" aria-invalid="false" style="width:120px" onchange="javascript:popSetting(this,'+index+')"><option value="">- Select Setting -</option>';
 		settingData = '';
 		if(settingFlag === true){
	 		vData ='';
	 			$.ajax({
	 				url:"/jewels/manufacturing/masters/settinglist.html",
	 				type:"GET",
	 				dataType:"JSON",
	 				success:function(data){
	  					vData = data;
	  				},
	 				async:false
	 			});
	 			
	 			settingFlag = false;
	 		}
	 		
	 		
	 		
			$.each(vData,function(key,val){
				
				 if(value === val){
					settingData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
				}else{
					settingData += "<option value='"+key+"'>"+val+"</option>";
				}
				
			});
	 
	 		return tempData+''+settingData+''+'</select>';
	} 

 	function popSetting(param,vidx){
 		
 		$('#diaChangNewStnId').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				setting : param.value,
			}
		});
 		
 		
 	}
 	
 	
 	
	
	var settingTypeFlag = true;
	var settingTypeData = '';
	var vSetTypeData ='';
 	function settingTypeFormatterCombo(value,row,index){
 				
 		var tempData =  '<select  class="form-control" aria-required="true" aria-invalid="false" style="width:145px" onchange="javascript:popSettingType(this,'+index+')"><option value="">- Select Set-Type -</option>';
 		
 		settingTypeData='';
	 		
	 		if(settingTypeFlag === true){
	 			vSetTypeData='';
	 			
	 			$.ajax({
	 				url:"/jewels/manufacturing/masters/settingTypeList.html",
	 				type:"GET",
	 				dataType:"JSON",
	 				success:function(data){
	 					
	 					vSetTypeData=data;
	 					
	 			
	 				},
	 				async:false
	 			});
	 			
	 			settingTypeFlag = false;
	 		}
	 		
	 		
			$.each(vSetTypeData,function(key,val){
						
						 if(value === val){
							settingTypeData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
						}else{
							settingTypeData += "<option value='"+key+"'>"+val+"</option>";
						}
					});
	 		
	 		
	 		
	 		return tempData+''+settingTypeData+''+'</select>';
	} 
 	
 	function popSettingType(param,vidx){
 		
 		$('#diaChangNewStnId').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				settingType : param.value,
			}
		});
 		
 	}
 	
 	
	
	function trfStoneFormatter(value,row,index){
		return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateTrfStone(this,'+index+')"  />';
	}
	
	function updateTrfStone(param,vidx){
 		
 		$('#diaChangNewStnId').bootstrapTable('updateRow', {
			index : Number(vidx),
			row : {
				trfStone : param.value,
			}
		});
 		
 	}
	
	
	function trfCaratFormatter(value,row,index){
		return '<input class="form-control data-input"  style="width:75px" value="'+ value+ '" onchange="javascript:updateTrfCarat(this,'+index+')"  />';
	}
	
	function updateTrfCarat(param,vidx){
		
	
			$('#diaChangNewStnId').bootstrapTable('updateRow', {
				index : Number(vidx),
				row : {
					trfCarat : param.value,
				}
			});
	
 		

		
 	}
	
	
	
	function trfData(e){
		displayDlg(e,'javascript:transferData();', 'Transfer Bags','Do you want to transfer?', 'Continue');
		
	}
	
	function transferData(){
	
		$('#transferBtnId').attr('disabled', 'disabled');
		
		var len = $("#diaChangNewStnId").bootstrapTable('getData').length;
		for(i=0;i<len;i++){
			$('#diaChangNewStnId  tbody tr').eq(i).removeAttr('style','color:red');
		}
		
		
		$("#modalDialog").modal("hide");


		
		if(Number($("#diaChangNewStnId").bootstrapTable('getSelections').length) > 0){
			
			
			
			
			var tempData = $('#diaChangNewStnId').bootstrapTable('getSelections');
			
			var validationFlag = false;
			if($('#partNmId').val()===''){
				validationFlag = true;
			}
			
			
			$('#pPartNmId').val($('#partNmId').val());
			
			if(vDiaStockType ==='sizeGroup'){
				
			if($('#sizeNm').val() === ''){
					validationFlag = true;
			}
			$('#pSizeId').val($('#sizeNm').val());
			}else{
				$('#pSizeId').val();
			}
			
						
			$.map(tempData, function(item) {
				
				if(item.trfStone === "" || item.trfStone <= '0'){
					validationFlag = true;
					$('#diaChangNewStnId  tbody tr').eq(Number(item.serialNo)).attr('style','color:red');
				}
				
				if(item.trfCarat === "" || item.trfCarat <= '0'){
					validationFlag = true;
					$('#diaChangNewStnId  tbody tr').eq(Number(item.serialNo)).attr('style','color:red');
				}
				
				if(item.setting === ""){
					validationFlag = true;
					$('#diaChangNewStnId  tbody tr').eq(Number(item.serialNo)).attr('style','color:red');
				}
				
				if(item.settingType === ""){
					validationFlag = true;
					$('#diaChangNewStnId  tbody tr').eq(Number(item.serialNo)).attr('style','color:red');
				}
				
				
			});
			
			
			if(validationFlag){
				displayMsg(this,null," Part No,Size, TrfStone,TrfCarat,Setting and SettingType Cannot be Blank");
				$('#transferBtnId').removeAttr('disabled');
				return;
			}
			
			
			 var data = JSON.stringify($("#diaChangNewStnId").bootstrapTable('getSelections'));
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/newBreakupImpStnAdj/frmStoneInwardDt.html",
				type : "POST",
				data : {
					pBagName : $('#uBagNm').val(),
					pPartId :$('#pPartNmId').val(),
					vTrandate : $('#pTrandate').val(),
					vSize : $('#pSizeId').val(),
					data : data,
				},
				success : function(data, textStatus, jqXHR) {
					
					 if(data.indexOf("Warning") != '-1'){
						var srNo = data.substring(data.indexOf("[")+1,data.indexOf("]"))
						var tempSrNo = srNo.split(",")
						for(i=0;i<=tempSrNo.length;i++){
							$('#diaBagDtId  tbody tr').eq(Number(tempSrNo[i])-1).attr('style','color:red');
						}
						
						displayMsg(this,null,data);
					}else{
					
						$('#sizeNm').val('');
						displayInfoMsg(this,null,data);
						fetchData();
					}
					
					 $('#transferBtnId').removeAttr('disabled');
				},
				error : function(jqXHR, textStatus, errorThrown) {
				}
			});
		}else{
			displayMsg(this,null,"Record Not Selected");
		}
		
		$('#transferBtnId').removeAttr('disabled');
		
	}
	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

