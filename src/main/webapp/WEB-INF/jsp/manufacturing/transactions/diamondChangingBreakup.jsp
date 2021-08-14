<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/diamondChangingModal.jsp"%>

<div class="panel panel-primary" style="width: 100%">

	<div class="panel-body">

		<div class="form-group" id="dsPId">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">

						<div>
							<table id="diaChangStnId" data-toggle="table"
								data-toolbar="#toolbar" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300"
								data-striped="true">
								<thead>
									<tr class = "btn-primary">
										<th data-field="state" data-checkbox="true" ></th>
										<th data-field="inwardType" data-sortable="true">Inward</th>
										<th data-field="invNo" data-sortable="true">InvNo</th>
										<th data-field="stoneType" data-sortable="true">StoneType</th>
										<th data-field="shape" data-align="left">Shape</th>
										<th data-field="subShape" data-align="left">SubShape</th>
										<th data-field="quality" data-sortable="true">Quality</th>
										<th data-field="mm" data-sortable="true">Size</th>
										<th data-field="sieve" data-sortable="true">Sieve</th>
										<th data-field="sizeGroup" data-sortable="true">SizeGroup</th>
										<th data-field="rate" data-sortable="true">Rate</th>
										<th data-field="balStone" data-sortable="true">BalStone</th>
										<th data-field="balCarat" data-sortable="true">BalCarat</th>
										<th data-field="trfStone" data-sortable="true">TrfStone</th>
										<th data-field="trfCarat" data-sortable="true">TrfCarat</th>
										<th data-field="setting" data-sortable="true">Setting</th>
										<th data-field="settingType" data-sortable="true">Set Type</th>
									</tr>
								</thead>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div>





		<div id="scUpdateId" style="display: none">
			<div class="row">
				<div class="form-group">
					<div class="col-xs-12">
					<form:form commandName="diamondBagging" >
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th class="col-xs-4"></th>
									<th class="col-xs-1">Trf Stone :</th>
									<td class="col-xs-1"><input type="text" id="fldTrfStone" name="fldTrfStone" maxlength="7" size="7" class="form-control"/></td>
									<th class="col-xs-1">Trf Carat :</th>
									<td class="col-xs-1"><input type="text" id="fldTrfCarat" name="fldTrfCarat" maxlength="7" size="7" class="form-control"/></td>
									<td class="col-xs-2"> 
											<form:select path="setting.id" id="tSetting" class="form-control" onChange="javascript:updateSetting()">
												<form:option value="" label="- Select Setting -" />
												<form:options items="${settingMap}" />
												</form:select> 
									</td>
									<td class="col-xs-2"> 
											<form:select path="settingType.id" id="tSettingType" class="form-control" onChange="javascript:updateSettingType()">
												<form:option value="" label="- Select SettingType -" />
												<form:options items="${settingTypeMap}" />
												</form:select>
									</td>
									
								</tr>
							</tbody>
						</table>
					 </form:form>
					</div>
				</div>
			</div>
		</div> 





		<!-- <div class="row">
			<div class="form-group">
				<div class="col-xs-1">
					<table class="table">
						<tbody>
							<tr>
								<td class="col-xs-1">
									<a class="btn btn-primary"
										style="font-size: 15px" type="button" onClick="goToMainPage()">Back
									</a>
									<input type="hidden" name="tempCarat" id="tempCarat" /> 
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div> -->
		
		
		
		
		
		
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<form:form commandName="diamondBagging" id="transferToImpStnAdjj"
					action="/jewels/manufacturing/transactions/breakupImpStnAdj/fromStoneInwardDt.html"
					cssClass="form-horizontal transferForm">
	
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td class="col-xs-1">
									<input type="submit" value="Transfer" class="btn btn-primary" id="transferIdBtnSub" /> 
									<form:input type="hidden" path="id" />							
									<input type="hidden" name="pODIds" id="pODIds" />
									<input type="hidden" name="trfBalCarat" id="trfBalCarat" />
									<input type="hidden" name="trfBalStone" id="trfBalStone" />
									<input type="hidden" name="dbId" id="dbId" />
									<input type="hidden" name="cSetting" id="cSetting" />
									<input type="hidden" name="cSettingType" id="cSettingType" />
									<input type="hidden" name="tempDeptNm" id="tempDeptNm" />
								</td>
								<td class="col-xs-11">
									<a  class="btn btn-primary" 
										style="font-size: 15px" type="button" onClick="javascript:goToMainPage()" >Back
									</a>
									<input type="hidden" name="tempCarat" id="tempCarat" /> 
								</td>												
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
		
		
		
		
		
		
		
		
		




	</div>
	<!-- ending the panel body -->
</div>
<!-- ending the main panel -->




<script type="text/javascript">
	
	$(document)
			.ready(
					function(e) {

						if (window.location.href.indexOf('diamondChangingBreakup') != -1) {

							var vUrl = window.location.href;

							var vStrId = vUrl.substring(window.location.href.indexOf('diamondChangingBreakup') + 23);
							var vId = vStrId.split(".");
							popStoneBreakup(vId[0]);
							$('#dbId').val(vId[0]);
							
							var vDeptNm = vUrl.substring(window.location.href.indexOf('=') + 1);
							$('#tempDeptNm').val(vDeptNm);
							
						}

					});
	

	function popStoneBreakup(vSel) {
		
		//$('#pId').val(vSel);
		
		$("#diaChangStnId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/diamondChangingBreakup/stn/listing.html?idd="
									+ vSel
						});

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}

		return dt;
	}
	
	
	
	var stnTblRow = -1;
	$('#diaChangStnId').bootstrapTable({})
			.on(
					'click-row.bs.table',

					function(e, row, $element) {

						stnTblRow = $element.attr('data-index');
							
						
						
							$('#tempCarat').val('');
							
							$('#fldTrfStone').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).trfStone));
							$('#fldTrfCarat').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).trfCarat));
							
							 var vSetting = getNumVal(jQuery.parseJSON(JSON.stringify(row)).setting);
							 if(!vSetting){
								 $("#tSetting option:contains(" + '- Select Setting -' + ")").attr('selected', 'selected');
							 }else{
								 $("#tSetting option:contains(" + vSetting + ")").attr('selected', 'selected');
							 }
							
							 var vSettingType = getNumVal(jQuery.parseJSON(JSON.stringify(row)).settingType);
							
							
							 
							 if(!vSettingType){
								 $("#tSettingType option:contains(" + '- Select SettingType -' + ")").attr('selected', 'selected');
							 }else{
								 $("#tSettingType option:contains(" + vSettingType + ")").attr('selected', 'selected');
							 }
							
							
							
							$('#tempCarat').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).balCarat));
							
							$("#scUpdateId").css('display', 'block');
							$('#fldTrfStone').focus();
							$('#fldTrfStone').select();

					});
	


	$('#fldTrfCarat').on('blur', function(e) {
		validationCarat();
	});



	
	
	function validationCarat(){
		
		 if(Number($('#fldTrfStone').val()) == 0 && Number($('#fldTrfCarat').val()) != 0 ){
			displayMsg(this, null, 'Transfer Stone Cannot be zero');
		}else if(Number($('#fldTrfStone').val()) != 0 && Number($('#fldTrfCarat').val()) == 0 ){
			displayMsg(this, null, 'Transfer Carat Cannot be zero');
		}else {
			
			if(Number($('#fldTrfCarat').val()) > Number($('#tempCarat').val()) ){
				displayMsg(this, null, 'Transfer Carat Cannot be greater than carat');
			}else{
				updateRow();
			}
			
		} 
		
	}
	
	
	
	function updateRow(){
		
		$("#diaChangStnId").bootstrapTable('updateRow', {
			index : stnTblRow,
			row : {
				state : false,
				trfStone : $('#fldTrfStone').val(),
				trfCarat : $('#fldTrfCarat').val(),
	
			}
		});
		
		//$("#scUpdateId").css('display', 'none');
	}
	
	function updateSetting(){
		var vSetting = $("#tSetting :selected").text();
		
		if(vSetting == "- Select Setting -"){
			vSetting = "-";
		}
		
		$("#diaChangStnId").bootstrapTable('updateRow', {
			index : stnTblRow,
			row : {
				state : false,
				setting : vSetting,
			}
		});
	}
	
	
	
	function updateSettingType(){
		var vSettingType = $("#tSettingType :selected").text();
		
		//alert(vSettingType);
		
		if(vSettingType == "- Select SettingType -"){
			vSettingType = "-";
			//alert("in if");
		}
		
		$("#diaChangStnId").bootstrapTable('updateRow', {
			index : stnTblRow,
			row : {
				state : false,
				settingType : vSettingType,
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	$(document)
	.on(
			'submit',
			'form#transferToImpStnAdjj',
			function(e) {
				
				$('#transferIdBtnSub').attr('disabled', 'disabled');

				var data = $('#diaChangStnId').bootstrapTable('getSelections');
				
				var ids = $.map(data, function(item) {
					return item.id;
				});
				
				var vTrfStone  = $.map(data, function(item) {
					return item.trfStone;
				});
				
				var vBalCarat = $.map(data, function(item) {
					return item.trfCarat;
				});
				
				 var vSetting = $.map(data, function(item){
					return item.setting;
				});
				
				var vSettingType = $.map(data, function(item){
					return item.settingType;
				}); 
				

				$('#pODIds').val(ids);
				$('#trfBalStone').val(vTrfStone);
				$('#trfBalCarat').val(vBalCarat);
				$('#cSetting').val(vSetting);
				$('#cSettingType').val(vSettingType);
			

				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",	
							data : postData,
							
							success : function(data, textStatus, jqXHR) {
			
									if(data == -1){
										displayMsg(this, null, 'Record Not Selected ');
									}else if(data == 1){
										displayMsg(this, null, 'Data Transfered Sucessfully ');
										popStoneBreakup($('#dbId').val());
									}else if(data == -2){
										displayMsg(this, null, 'Please select the setting before transfer');
									}else if(data == -3){
										displayMsg(this, null, 'Please select the settingType before transfer');
									}else if(data == -4){
										displayMsg(this, null, 'Stock Not Available');
									}
									
									$('#transferIdBtnSub').removeAttr('disabled');
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
							}

						});
				
				e.preventDefault(); //STOP default action

			});

	
	
	
	
	
	function goToMainPage(){
			
			$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/fetchBagName/fromStnAdjId.html' />?idd='
						+ $('#dbId').val(),
				type : 'GET',
				success : function(data) {
					window.location.href = "/jewels/manufacturing/transactions/diamondChanging.html?**%*%&="
						+ data;
					
				}
			});
			
		}
		
	
	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

