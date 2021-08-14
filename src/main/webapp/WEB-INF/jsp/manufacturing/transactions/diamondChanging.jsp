<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/diamondChangingModal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/diamondChangingRetModal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">
		<span style="font-size: 18px;">Diamond Changing</span>
	</div>

	<div class="panel-body">

		<div class="col-xs-10">
		
		
				<div class="row">
					<div class="col-xs-12">
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Bag</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">TranDate</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Order
								No</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Client</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Style
								No</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Bag
								Pcs</label>
						</div>
						
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="bagMtName" name="bagMtName" class="form-control" onchange="javascript:pendingForApprovalPopup();">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="trandate" name="trandate" class="form-control">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="invNo" name="invNo" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="partyName" name="partyName" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="styleNo" name="styleNo" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="bagPcs" name="bagPcs" class="form-control" readonly="readonly">
						</div>
						
					</div>
				</div>
	
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Department</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Total Stone</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Total Carat</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
							<input type="text" name="vDepartment" id="vDepartment" class="form-control" readonly="readonly" />
						</div>
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalStones" id="vTotalStones" class="form-control" readonly="readonly" />
						</div>
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalCarats" id="vTotalCarats" class="form-control" readonly="readonly" />
						</div>
						
						<div class="col-sm-2 text-left">
							<button class="btn btn-danger" id="btnReturnId" onclick="javascript:returnSelected()">Return Selected</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
					<input type="hidden" id="orderDtId" name="orderDtId" />
				</div>
		
		
		</div> 



		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
			<div class="panel panel-primary" style=" height:170px;">
				<div class="panel-body">
					<div style="width: 150px; height: 50px" class="col-sm-4">
						<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png" data-lighter> 
							<img id="ordImgId" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" />
						</a>
					</div>
				</div>
			</div>
		</div>





		<div class="row" align="center">
			<div class="form-group">
				<div class="col-xs-3">
					<div id="msgId" style="display: none"><span style="font-size: 16px; color: red ">Ready Bag Transfer Pending</span></div>
				</div>
				
				
				
			</div>
		</div>


<div id="toolbarbrk" style="display: none">

<input type="button" id="transferBtnId" value="New Breakup" class="btn btn-info" onclick="javascript:goToNextPage()"/>
							<input type="hidden" id="tempDept" name="tempDept" />			


</div>


		<div class="form-group" id="dsPId">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="diaBagDtId" data-toggle="table" data-height="300" data-toolbar="#toolbarbrk">
								<thead>
									<tr class = "btn-primary">
										<th data-field="state"  data-checkbox="true">Select</th>
										<th data-field="bagSrNo">SrNo</th>
										<th data-field="partNm">Part No.</th>
										<th data-field="stoneType">Stone Type</th>
										<th data-field="shape" data-align="left">Shape</th>
										<th data-field="subShape">Sub Shape</th>
										<th data-field="quality">Quality</th>
										<th data-field="mm" data-align="right">Size/MM</th>
										<th data-field="sieve" data-align="right">Sieve</th>
										<th data-field="sizeGroup">Size Group</th>
										<th data-field="stones" data-align="right">Stone</th>
										<th data-field="carat" data-align="right">Carat</th>
										<th data-field="setting">Setting</th>
										<th data-field="setType">Set Type</th>
										<th data-field="centerStone" data-formatter="centerStoneFormatter">CenterStn</th>
										
										<c:if test="${companyName ne 'nuance'}">
										<th data-field="action1" data-align="center">Add</th>
										<th data-field="action2" data-align="center">Return</th>
										</c:if>
										
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>


	
	<%-- <div class="row" >
		<div class="form-group">
			<div class="col-xs-1" >
				<form:form commandName="diamondBagging" id="changingTransferrr"
					action="/jewels/manufacturing/transactions/return/diamondChanging/transfer.html"
					cssClass="form-horizontal transferOfChangeing">

					<table class="table">
						<tbody>
							<tr>
								<td class="col-xs-1" >
								<input type="submit" value="Return" class="btn btn-primary" id="returnSubmitBtn" /> 
									<form:input type="hidden" path="id" />
									<input type="hidden" name="tempStone" id="tempStone" /> 
									<input type="hidden" name="tempCarat" id="tempCarat" /> 
									<input type="hidden" name="pODIds" id="pODIds" />
									<input type="hidden" name="trfBagCarat" id="trfBagCarat" />
									<input type="hidden" name="trfBagStone" id="trfBagStone" />
									<input type="hidden" name="bagNm" id="bagNm" />
									<input type="hidden" name="employeeeId" id="employeeeId" />
									<input type="hidden" name="typeNm" id="typeNm" />
									<input type="hidden" name="cDeptId" id="cDeptId" />
								</td>
								<td class="col-xs-1" >
									
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div> --%>
	


	</div><!-- ending the panel body -->

</div><!-- ending the main panel -->

<script type="text/javascript">

var diaStockType;

	$(document)
			.ready(
					function(e) {
						$(".diaChang").validate(
								{
									rules : {
										styleNo : {
											required : true,
											minlength : 3
										},
										code : {
											required : true,
											minlength : 3
										}
									},
									highlight : function(element) {
										$(element).closest('.form-group')
												.removeClass('has-success')
												.addClass('has-error');
									},
									unhighlight : function(element) {
										$(element).closest('.form-group')
												.removeClass('has-error')
												.addClass('has-success');
									},
									messages : {
										name : {
											remote : ""
										}
									}
								});
						
					
						
						//alert($('#tempDept').val());
						
						
						$("#bagMtName")
								.autocomplete(
										{
											source : "<spring:url value='/manufacturing/transactions/jobBag/diamondChanging/list.html' />",
											minLength : 2
										});
						
						$("#trandate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
					
						$("#trandate").val('${currentDate}');
						
						
						if('${canEditTranddate}' === "false"){
							$("#trandate").attr("disabled","disabled");
						}
						
						diaStockType = '${diamondStockType}';

						//NewBreak Button hide Show
						if('${companyName}' != 'nuance'){
							$('#toolbarbrk').css('display', 'block');
								}
						
						
						if (window.location.href.indexOf('**%*%&') != -1) {
							var vUrl = window.location.href;
							var vBagNm = vUrl.substring(window.location.href.indexOf('=') + 1);
							$('#bagMtName').val(vBagNm);
							popDetails();
						}
								

		});
	
	
	
	
	
	

	function popDetails() {
		
		if($("#bagMtName").val()!=""){

			$
			 .ajax({
					url : "<spring:url value='/manufacturing/transactions/jobBag/diamondChanging/details.html?bagName=' />"+$('#bagMtName').val(),
					type : "GET",
					datatype:"JSON",
					success : function(data) {
						
						 $.each(JSON.parse(data), function(key,value){
						        if(key === 'image'){
						        	$('#ordImgId').attr('src', value);
									$('#oImgHRId').attr('href', value);
						        }else if(key === 'readyBagStatus'){
						        	if(value === '0'){
						        		$('#msgId').css('display','block');
						        	}else{
						        		$('#msgId').css('display','none');
						        	}
						        }else{
						        	$('#'+key).val(value)	
						        }
						    });
						  
						 	popImpStnAdj();
						
					
					},
					error : function(jqXHR, textStatus, errorThrown) {
						
					}
				});
		
		}
		
		
	}
	
	
	

	
	
	
	function popImpStnAdj() {
		$("#diaBagDtId")
				.bootstrapTable(
						'refresh',{
							url : "/jewels/manufacturing/transactions/impstnAdj/diamondChanging/listing.html?bagName="+$('#bagMtName').val()
						});
	}
	
	
	
	function popEmployee(){
		//$("#employeeId").html('');
		$.ajax({
			url : '<spring:url value='/manufacturing/transactions/changingDiamond/employee.html' />?deptNm='
					+ $('#vDepartment').val(),
			type : 'GET',
			success : function(data) {
				console.log(data);
				$("#employeeId").html(data);
			}
		});
	}
	

	
	
	
	
	$('#diaBagDtId').bootstrapTable({})
	.on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#diaBagDtId").bootstrapTable('getData'));

			var vStone = 0;
			var vCarat = 0.0;

			$.each(JSON.parse(data), function(idx, obj) {
				vStone += Number(obj.stones);

			});
			
			$.each(JSON.parse(data), function(idx, obj) {
				vCarat += Number(obj.carat);

			});

			$('#vTotalStones').val(" " + parseFloat(vStone));
			$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
			
		})
		
	
	
	var stnTblRow = -1;
	$('#diaBagDtId').bootstrapTable({})
			.on(
					'click-row.bs.table',

					function(e, row, $element) {

						
						stnTblRow = $element.attr('data-index');
							
							$('#tempStone').val('');
							$('#tempCarat').val('');
							
							$('#fldRetStone').val(jQuery.parseJSON(JSON.stringify(row)).retStones);
							$('#fldRetCarat').val(jQuery.parseJSON(JSON.stringify(row)).retCarat);

							$('#tempStone').val(jQuery.parseJSON(JSON.stringify(row)).stones);
							$('#tempCarat').val(jQuery.parseJSON(JSON.stringify(row)).carat);
							
							$("#changTypeId").val('');
							
							//$("#scUpdateId").css('display', 'block');
					
							$('#changTypeId').focus();

					});
	
	
	
	
	
	
	
	
	
	
	function validationStone(){
		
		if(Number($('#fldRetStone').val()) > Number($('#tempStone').val())){
			displayMsg(this, null, 'Return Stone Cannot be greater than Stone');
		}else{
			var temp = $('#tempCarat').val()/$('#tempStone').val();
			var temp2 = temp*$('#fldRetStone').val();
			$('#fldRetCarat').val(temp2.toFixed(3));
			$('#fldRetCarat').focus();
			updateRow();
		}
	}
	
	
	
	
	
	function validationCarat(){
		
		 if(Number($('#fldRetStone').val()) == 0 && Number($('#fldRetCarat').val()) != 0 ){
			displayMsg(this, null, 'Return Stone Cannot be zero');
		}else if(Number($('#fldRetStone').val()) != 0 && Number($('#fldRetCarat').val()) == 0 ){
			displayMsg(this, null, 'Return Carat Cannot be zero');
		}else {
			
			if(Number($('#fldRetStone').val()) > Number($('#tempStone').val()) ){
				displayMsg(this, null, 'Return Stone Cannot be greater than stone');
			}else if(Number($('#fldRetCarat').val()) > Number($('#tempCarat').val()) ){
				displayMsg(this, null, 'Return Carat Cannot be greater than carat');
			}else if(Number($('#fldRetCarat').val()) == Number($('#tempCarat').val()) ){
				if(Number($('#fldRetStone').val()) != Number($('#tempStone').val()) ){
					displayMsg(this, null, 'Not Valid Entry Check Entry');
				}else{
					updateRow();
				}
				
			}else{
				updateRow();
			}
			
		} 
		
	}
	
	
	
	
	
	function updateType(){
		
		var vType = $("#changTypeId :selected").text();
		
		if(vType == "- Select Type -"){
			vType = "";
		}
		
		$("#diaBagDtId").bootstrapTable('updateRow', {
			index : stnTblRow,
			row : {
				state : false,
				changingType : vType,
			}
		});
	
	}
	
	

	
	$(document).on(
			'submit',
			'form#changingTransferrr',
			function(e) {

				
				$('#returnSubmitBtn').attr('disabled', 'disabled');

				var data = $('#diaBagDtId').bootstrapTable('getSelections');

				var ids = $.map(data, function(item) {
					return item.id;
				});
				
				var vBagStone  = $.map(data, function(item) {
					return item.retStones;
				});
				
				var vBagCarat = $.map(data, function(item) {
					return item.retCarat;
				});
				
									
									
			
				
				
				var vDeptCombo = $('#vDepartment').val();
				 
				
				$('#cDeptId').val(vDeptCombo);
				$('#pODIds').val(ids);
				$('#trfBagCarat').val(vBagCarat);
				$('#trfBagStone').val(vBagStone);
				$('#bagNm').val($('#bagMtName').val());
				//$('#employeeeId').val(vEmployee);
				//$('#typeNm').val(vChangingType);

				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,

							success : function(data, textStatus, jqXHR) {
								
								
								//alert(data);
									
									if(Number(data) == 1){
										displayMsg(this, null, 'Data Transfered Sucessfully ');
										popImpStnAdj();
										//$("#scUpdateId").css('display', 'none');
									}else if(Number(data) == -2){
										displayMsg(this, null, 'Please select type ');
										//$("#scUpdateId").css('display', 'none');
										
									}else if(Number(data) == -3){
										displayMsg(this, null, 'Please select employee ');
										//$("#scUpdateId").css('display', 'none');
										
									}else if(Number(data) == -4){
										displayMsg(this, null, 'Record not saved');
										//$("#scUpdateId").css('display', 'none');	
									
									}else if(Number(data) == -5){
										displayMsg(this, null, 'Please select department');
										//$("#scUpdateId").css('display', 'none');
										
									}else{
										displayMsg(this, null, 'record not selected ');
										//$("#scUpdateId").css('display', 'none');
										
									}
									
									$('#returnSubmitBtn').removeAttr('disabled');
									
									
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
							}

						});
				
				e.preventDefault(); //STOP default action

			});

	
	
	
	
	
	
	
	
	function goToNextPage(){
	
		
		var vBagNm = $('#bagMtName').val();
		if (!vBagNm) {
			displayMsg(this, null, 'PLEASE SELECT THE BAG NO !');
		}else{
			window.location.href = "/jewels/manufacturing/transactions/diamondChangingNewBreakup.html?%%&*"+$('#bagMtName').val()+"_"+$('#vDepartment').val()
					+"tranDate="+$("#trandate").val()+"diaStockType="+diaStockType;
		}
		
	}
	
	
	
	function addChangingPage(tranDtId){
		$('#myDiamondChangingNewModal').modal('show');
		bringTranDtDetails(tranDtId)
		$('#modalTranDtId').val(tranDtId);
		$('#modalTranDate').val($('#trandate').val());
		
	}
	
	//---------------------ret-----------------//
	
	function popChangingReturn(tranDtId){
		$('#myDiamondChangingRetModal').modal('show');
		bringTranDtRetDetails(tranDtId)
		$('#modalTranDtRetId').val(tranDtId);
		$('#modalTranDate').val($('#trandate').val());
		popEmployee();
	}
	
	
	function centerStoneFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean"){
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}

		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';

	}
	
	function returnSelected(){
		
		
		
		$('#btnReturnId').attr('disabled', 'disabled');  
		
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
		
		var tblData=JSON.stringify($('#diaBagDtId').bootstrapTable('getSelections'));
		
		$.ajax({
			
			url:"/jewels/manufacturing/transactions/diamondchanging/returnselected.html",
			type:"POST",
			data : {vtblData : tblData,
					tranDate : $('#trandate').val()},
			success:function(data){
				$('#btnReturnId').removeAttr('disabled');
				$.unblockUI();
				
				popImpStnAdj();
				
			}
			
		});
		
	}


	function pendingForApprovalPopup(){

		$.ajax({
			url:"/jewels/manufacturing/transactions/transfer/pendingForApprovalPopup.html?bagNo="+$('#bagMtName').val(),
			type:'GET',
			success : function(data, textStatus, jqXHR) {

				if(data != "false"){
					$('#btnReturnId').hide();
					$('#toolbarbrk').css("display","none");
					displayMsg(event, this, data);
				
					}else{
						
						$('#btnReturnId').show();
						$('#toolbarbrk').css("display","block");
						popDetails();
						}
			}
			
		});
		
	}
	
	
</script>


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

