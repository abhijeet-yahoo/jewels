<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<div class="panel panel-primary">
	
	
	<div class="panel-heading" id="multibagTransferDivId" style="display: none">
		<span style="font-size: 18px;">Multiple Bag Transfer</span>
	</div>
	
	<div class="panel-heading" id="multiReadybagTransferDivId" style="display: none">
		<span style="font-size: 18px;">Transfer For ReadyBag Issue</span>
	</div>
	


	<div class="panel-body">
		
		<form:form commandName="tranMt" id="multiBagTransferDivId"
					action="/jewels/manufacturing/transactions/transfer/add.html"
				cssClass="transferForm">

				<div class="col-xs-10">
				
				<div class="col-sm-2">
						<div class="form-group">
							<label class="control-label" for="trandate">TranDate</label>
								<form:input path="trandate" cssClass="form-control"  autocomplete="off"/>
								<form:errors path="trandate" />
						</div>
	
					</div>
				

				<div class="col-sm-3">

					<div class="form-group">
										
						<label class="control-label" for="dept">From Department</label>
							<form:select path="deptFrom.id" id="deptFroom"
								class="form-control" onChange="javascript:popMultiBag();">
								<form:option value="" label=" Select department " />
								<form:options items="${departmenttMap}" />
							</form:select>

						
					</div>

				</div>
				
				<div class="col-sm-3"  id="toDepartmentDivId" style="display: none">

							<div class="form-group">

								<label class="control-label" for="trfDept">To Department</label> 
								<form:select path="deptTo.id" id="deptToo" class="form-control">
											<form:option value="" label="- Select department -" />
											<form:options items="${departmentToMap}" />
										</form:select>

							</div>

						</div>
						
					<div class="col-sm-offset-1 col-sm-2">
					<label class="control-label" for="trfDept"></label> 
					<div class="form-group">
							<div>
								<input type="button" value="Transfer" onclick="javascript:popMultiBagTransfer()" id="multiTransferBtnId" class="btn btn-primary">
							</div>
						</div>
					
					</div>	
						
								
				</div>
				
		<!-- 		<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div> -->
				
			
				
				<!-- Hidden Field -->
				
						<input type="hidden" name="id"> 
						<input type="hidden" name="vBagNo" id="vBagNo" />


			<div class="row" id="tblBagDivId">
						
						<div class="col-md-10">
						


				<div class="table-responsive">
 					<table id="multiBagTblId" data-toggle="table" data-search="true" data-toolbar="toolbarDt" data-maintain-meta-data="true" data-height="450"  >
					<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="party" data-align="left" data-sortable="true" data-filter-control="input">Party</th>
								<th data-field="orderNo" data-align="left" data-sortable="true" data-filter-control="input">Order</th>
								<th data-field="refNo" data-align="left" data-sortable="true" data-filter-control="input">Ref</th>
								<th data-field="barcode" data-align="left" data-sortable="true" data-filter-control="input">Barcode</th>
								<th data-field="bagNo" data-align="left" data-sortable="true" data-filter-control="input">Bag</th>
								<th data-field="styleNo" data-align="left" data-sortable="true" data-filter-control="input">Style No</th>
								<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
								<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
							
								<c:if test="${trantype eq 'transfer'}">
								<th data-field="action1" data-align="left" data-sortable="true">LossBook</th>
								</c:if>

							</tr>
						</thead>


					</table>
				<div class="row">&nbsp;
				</div>	
									 
 				 <div align="right" style="padding-right: 10px; font-weight: bold;">
 				 Total Bags : <input type="text" id="vTotalBags" name="vTotalBags" 	maxlength="7" size="7" disabled="disabled" style="text-align: right;"/> 
						&nbsp;&nbsp;
				Total Qty : <input type="text" id="vTotalQty" name="vTotalQty"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
							&nbsp;&nbsp;
				Total Wt : <input type="text" id="vTotalWt" name="vTotalWt"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
																&nbsp;&nbsp;
				Sel Bags : <input type="text" id="vSelectBags" name="vSelectBags" 	maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
						&nbsp;&nbsp;
				Sel Qty : <input type="text" id="vSelectQty" name="vSelectQty"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>
							&nbsp;&nbsp;
			Sel Wt : <input type="text" id="vSelectWt" name="vSelectWt"  
									maxlength="7" size="7" disabled="disabled" style="text-align: right;"/>			
 				 </div>

				</div>
				
				
				
				
</div>

			<div id="odImgDivId" class="col-md-2 center-block">
					<div style="height:52px">&nbsp;</div>
					<div class="panel panel-primary" style="width:100%; height:245px">
						<div class="panel-body">
							<div style="width:80%; height:168px">
								<div class="row center-block">
									<span id='styleImgId'></span> 									
									<div id="tempImgDivId">
										
									</div>
								</div>
							</div>
							
							
						</div>
					</div>

</div>

			</div>
			
			



		</form:form>
				 
		</div>
	</div>		
	
	

<script type="text/javascript">


$(document)
.ready(
		function(e) {
			
			
			$("#trandate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
		
			$("#trandate").val('${currentDate}');
			
			
			if('${canEditTranddate}' === "false"){
				$("#trandate").attr("disabled","disabled");
			}

			if('${trantype}' === "transfer"){
					$('#multibagTransferDivId').css('display','block');
					$('#toDepartmentDivId').css('display','block');
					
				}

			if('${trantype}' === "trfForReadybagIss"){
				$('#multiReadybagTransferDivId').css('display','block');
				$('#toDepartmentDivId').css('display','none');
			}
			
			
			
			if (window.location.href.indexOf('dep') != -1) {
		 		

				var vUrl = window.location.href;
				
				var deptNm = vUrl.substring(window.location.href
						.indexOf('dep') + 3);
				

				
				$('#deptFroom').val(deptNm);
				$('#deptFroom').trigger('onchange');
				$('#deptToo').val();
				
				
				$('#multiBagBackBtnDivId').css('display','block');

				}
			
			
		});

function popMultiBag() {
	// alert("hello");
	$
			.ajax({
				url : '<spring:url value='/manufacturing/transactions/transfer/deptTo.html' />?departmentId='
						+ $('#deptFroom').val(),
				type : 'GET',
				success : function(data) {
					$("#deptToo").html(data);
					  
			popBagDt();
					

				}
			});
}




function popBagDt(){

	 var ab=document.getElementById("deptFroom");
	 var deptnm=ab.options[ab.selectedIndex].text;
	
	if('${trantype}' === "trfForReadybagIss" && deptnm.toUpperCase() === 'DIAMOND'){

		displayMsg(event, this,'Can not select Diamond  !');

		}else{

			if($('#deptFroom').val() !=''){
				
				$("#multiBagTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/multiBagTransfer/listing.html?departmentId="
								+ $('#deptFroom').val()+"&trantype="+'${trantype}',
						}); 
				}

			}
	

	
} 


function popMultiBagTransfer() {

	if('${trantype}' === "transfer"){
		
	
	var data = $('#multiBagTblId').bootstrapTable('getAllSelections');
	var ids = $.map(data, function(item) {
		return item.bagNo;
	});
	$('#vBagNo').val(ids);
	
	if($('#deptFroom').val() !='' && $('#deptToo').val() !='' && $('#vBagNo').val() ){
		
		$('#multiTransferBtnId').attr("disabled", true);
	
	$.ajax({
		url : "/jewels/manufacturing/transactions/multiBag/Transfer.html?deptFrom="+ $('#deptFroom').val() +"&deptTo="+$('#deptToo').val()
				+"&vBagNo="+$('#vBagNo').val()+"&tranDate="+$("#trandate").val()+"&trantype="+'${trantype}',
	
		type : "POST",
		success : function(data, textStatus, jqXHR) {
			
			
			if (Number(data) == 1) {

				displayInfoMsg(event, this,'data transfered sucessfully !');
				
			//	$('#deptFroom').val('');
				$('#deptToo').val('');
				$('#multiTransferBtnId').attr("disabled", false);
				
			}else{
				
			
				
			
			}			
			popBagDt();
			
			//$('#multiTransferBtnId').removeAttr('disabled');
			
		
			

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}

	});
	
	}

	}else{

		var data = $('#multiBagTblId').bootstrapTable('getAllSelections');
		var ids = $.map(data, function(item) {
			return item.bagNo;
		});
		$('#vBagNo').val(ids);

		if($('#deptFroom').val() !='' && $('#vBagNo').val() ){
			
			$('#multiTransferBtnId').attr("disabled", true);


			$.ajax({
				url : "/jewels/manufacturing/transactions/multiBag/Transfer.html?deptFrom="+ $('#deptFroom').val()
						+"&vBagNo="+$('#vBagNo').val()+"&tranDate="+$("#trandate").val()+"&trantype="+'${trantype}',
			
				type : "POST",
				success : function(data, textStatus, jqXHR) {
					
					
					if (Number(data) == 1) {

						displayInfoMsg(event, this,'data transfered sucessfully !');
						
					//	$('#deptFroom').val('');
						$('#deptToo').val('');
						$('#multiTransferBtnId').attr("disabled", false);
						
					}else{
						
					
						
					
					}			
					popBagDt();
					
					

				},
				error : function(jqXHR, textStatus, errorThrown) {

				}

			});


			}

		}	

	/* e.preventDefault(); */ //STOP default action
}





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
	$('#multiTransferBtnId').prop('disabled', true);
	
}

function popLossBook(){
	
	$('#multiBagTblId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
											
			var vBagNm = jQuery.parseJSON(JSON.stringify(row)).bagNo;
			
			 
			window.location.href = "/jewels/manufacturing/transactions/lossBook.html?dep"+$('#deptFroom').val()+"bg"+vBagNm+"mOpt2"+"&tranDate="+$("#trandate").val()
					+"&editTrandate="+'${canEditTranddate}';;
			})
	
	
	
}




$('#multiBagTblId').on('check.bs.table', function (e, row) {
	
	 popSelectedQty();
	
});



$('#multiBagTblId').on('check-all.bs.table', function (e, row) {
	
	 popSelectedQty();
	
});


$('#multiBagTblId').on('uncheck.bs.table', function (e, row) {
	
	 popSelectedQty();
	
	
});



$('#multiBagTblId').on('uncheck-all.bs.table', function (e, row) {
	
	 popSelectedQty();
	
});



$('#multiBagTblId').bootstrapTable({}).on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#multiBagTblId").bootstrapTable('getData'));
			var vBagPcs = 0.0;
			var vWt = 0.0;
			var i=0;
			$.each(JSON.parse(data), function(idx, obj) {
				i =i+1;
				vBagPcs		+= Number(obj.qty);
				vWt		+= Number(obj.grossWt);
			});
			
			$('#vTotalBags').val(i);
			$('#vTotalQty').val(vBagPcs.toFixed(2));
			$('#vTotalWt').val(Number(vWt).toFixed(3));
			
			$('#vSelectBags').val(0);
			$('#vSelectQty').val(0);
			$('#vSelectWt').val(0.0);
			
			
		});



function popSelectedQty(){
	
	
	var data = $('#multiBagTblId').bootstrapTable('getAllSelections');
	
	var vBagPcs = 0.0;
	var vWt = 0.0;
	var i=0;
	$.each(data, function(idx, obj) {
		i =i+1;
		vBagPcs		+= Number(obj.qty);
		vWt		+= Number(obj.grossWt);
	});
	
	$('#vSelectBags').val(i);
	$('#vSelectQty').val(Number(vBagPcs).toFixed(2));
	$('#vSelectWt').val(Number(vWt).toFixed(3));
	
	
}


var TableRow;
$('#multiBagTblId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			TableRow = $element.attr('data-index');
			var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
			
			$('#tempImgDivId').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivId').append(tempDiv);
			
		
		});



</script>	
	
	


<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />	
		