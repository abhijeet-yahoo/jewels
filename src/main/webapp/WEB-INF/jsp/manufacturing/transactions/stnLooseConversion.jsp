<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalStnLoosePickup.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>



<div id="stnLooseDivId">
<div class="panel panel-primary" style="width: 100%">
		
		<div class="panel-heading" >
			<span style="font-size: 18px;">Loose Stone Conversion</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					
					<div>
						<table id="stoneLoosePickupTblDtId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-striped="true" data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true">Select</th>
									<th data-field="action1" data-sortable="true">Convert</th>
									<th data-field="invNo" data-sortable="true">Voucher No</th>
									<th data-field="invDate" data-align="left">Voucher Date</th>
									<th data-field="supplier" data-sortable="true">Supplier</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="size" data-sortable="true">Size</th>
									<th data-field="sizeGroup" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									<th data-field="balstone" data-sortable="true">BalStone</th>
									<th data-field="balcarat" data-sortable="true">BalCarat</th>
									<th data-field="avgRate" data-sortable="true">Avg Rate</th>
									<th data-field="balAmount" data-sortable="true">Bal Amount</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			
<div>			
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Conversion Details
					</span>
			</div>
		</div>
	</div>
			
			
			
			<div class="row"  id="stnLooseConversionDtDivId" style="display:none">
				<div class="col-xs-12">
					
					<div>
						<table id="stnLoosepickDtTblDtId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="250" data-striped="true" data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="action2" data-sortable="true">Delete</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="size" data-sortable="true">Size</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									
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

$(document)
.ready(
		function(e) {

		//	$('select').chosen();
			

			$.validator.setDefaults({ ignore: ":hidden:not(select)" });
			
				if ($("select.form-control").length > 0) {
				    $("select.form-control").each(function() {
				        if ($(this).attr('required') !== undefined) {
				            $(this).on("change", function() {
				                $(this).valid();
				            });
				        }
				    });
				}

				
			popStnLoosePickDt();

		});



function popStnLoosePickDt() {
	$("#stoneLoosePickupTblDtId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/stnLoosePickup/listing.html"
								
					});
}


var dtTableRow;
 $('#stoneLoosePickupTblDtId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			dtTableRow = $element.attr('data-index');

			/* var balAmont = jQuery.parseJSON(JSON.stringify(row)).balAmount;
			var balcarat = jQuery.parseJSON(JSON.stringify(row)).balcarat;

			var avgRate = (balAmont / balcarat).toFixed(2); */
			
			
			$('#vshapeId').val(jQuery.parseJSON(JSON.stringify(row)).shape);
			$('#vqualityId').val(jQuery.parseJSON(JSON.stringify(row)).quality);
			$('#vsieve').val(jQuery.parseJSON(JSON.stringify(row)).sieve);
			$('#vsize').val(jQuery.parseJSON(JSON.stringify(row)).size);
		//	$('#vBalCarat').val(jQuery.parseJSON(JSON.stringify(row)).balcarat);
		//	$('#avgRate').val(avgRate);
		//	$('#balVal').val(balAmont);
			$('#vMtId').val(jQuery.parseJSON(JSON.stringify(row)).mtId);
			$('#vDtId').val(jQuery.parseJSON(JSON.stringify(row)).dtId);

			$('#stnLooseConversionDtDivId').css('display','block');

			popStnLooseConversionDt();
			getStnLooseBalanceStock();
		});


 function popSubShape(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/subShape/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$('#subShape\\.id').html(data);
						$('#subShape\\.id').trigger('chosen:updated');
					}
				});
	}

	function popQuality(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/quality/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#quality\\.id").html(data);
						$('#quality\\.id').trigger('chosen:updated');
					}
				});
	}

	function popStoneChart(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/stoneChart/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$('#size').html(data);
						$('#size').trigger('chosen:updated');
					}
				});
	}

	function getSizeMMDetails() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/sizeMM/details.html' />?shapeId='
							+ $('#shape\\.id').val()
							+ '&size='
							+ $('#size').val(),
					type : 'GET',
					success : function(data) {
						fldData = data.split("_");
						$("#vSieve").val(fldData[0]);
						$("#vSizeGroupStr").val(fldData[1]);
					}
				});
	}

	function popSizeGroup(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/sizeGroup/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeGroupId").html(data);
					}
				});
	}
	


function popStnLoosConversion(){
	$('#myStnLoosePickupModal').modal('show');
	 
	 $('#myStnLoosePickupModal').on('shown.bs.modal', function () {
		 popStnLooseConversionDt();
        });
}


function popStnLooseConversionDt() {

	$("#stnLoosepickDtTblDtId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/looseStkConversion/listing.html?dtId="
								+ $('#vDtId').val()
					});
}


function getStnLooseBalanceStock() {

	$.ajax({
		url:"/jewels/manufacturing/transactions/stnLooseDt/getLooseBalanceStock.html?dtId="+ $('#vDtId').val(),
		type :'GET',
		dataType:'JSON',
		success: function(data){
			
			if(data !== "-1"){
				
				$.each(data,function(k,v){
					$('#stnLooseDtDivId  #'+k).val(v);
				});
				
			}else{
				displayMsg(this,null,'Error Contact Admin');
			}
			
		}	
	})
}


function deleteStoneLooseConversionDt(e,dtId){
	displayDlg(
			e,
			'javascript:deleteStnLooseConvDt('+dtId+');',
			'Delete',
			'Do you want to Delete  ?',
			'Continue');
}


function deleteStnLooseConvDt(dtId){
	
	$("#modalDialog").modal("hide");
						
					 $.ajax({
							url : "/jewels/manufacturing/transactions/looseStkConversion/delete/"+ dtId + ".html",
							type : 'GET',
							success : function(data) {
								if(data === "1"){
									popStnLoosePickDt();
									popStnLooseConversionDt();
									getStnLooseBalanceStock();
								}else{
									displayMsg(this,null, 'Stock Adjusted Can Not Delete');
									
									}
							}
						}); 
	

}


</script>