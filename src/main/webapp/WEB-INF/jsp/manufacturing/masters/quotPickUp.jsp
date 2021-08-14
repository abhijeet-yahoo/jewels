<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css" rel="stylesheet">
<script src="/jewels/js/bootstrap/lodash.min.js"></script>
 <script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script> 


<div class="panel panel-primary">
<div class="panel-heading" align="left">
		<span style="font-size: 15px;">Pick Up</span>
	</div>

<div class="panel-body" >
	
	<div class="form-group">
		<div class="container-fluid">
		
		
		
		<div class="row">
		<div class="col-sm-4">
			<div class="form-group">
			<label class="control-label text-right" >Party</label>
			
			<input type="text" id="partyNm" name="partyNm" class="form-control" onchange="javascript:popinvNo()" onblur="javascript:popInvAutoFill()" autocomplete="off">
			</div>
			
			
		</div>	
			
		<div class="col-sm-4" id="fromOrderDivId" style="display: none">
			<div class="form-group">
			    <label class="control-label text-right" >From Order xxxx</label>
				<input type="text" id="fromOrderNm" name="fromOrderNm" class="form-control" onchange="javascript:popDtDetails();notmatchstylewithorder()" autocomplete="off">
			</div>
			
			
		</div>	
			
		<div class="col-sm-4" id="fromQuotDivId" style="display: none">
			<div class="form-group">
			    <label class="control-label text-right" >From Quotation </label>
				<input type="text" id="fromQuotNm" name="fromQuotNm" class="form-control"  onchange="javascript:popDtDetails();notmatchstylewithorder()" autocomplete="off">
			</div>
			
			
		</div>
		
		<div class="col-sm-4" id="forQuotOrderDivId" style="display: none">
			<div class="form-group">
			    <label class="control-label text-right" >For Order xxxx</label>
				<input type="text" id="forOrderNm" name="forOrderNm" class="form-control"  onchange="javascript:popDtDetails();notmatchstylewithorder()" autocomplete="off">
			</div>
			
			
		</div>	
			
			
		
		</div>
				
		
		</div>
	</div>
	
	
	 <div class="form-group">
		    <div class="container-fluid">
		    
		<!--       <div class="pull-right search" style="padding-top: 0px; padding-right: 30px;padding-bottom: 0px">
				<input class="form-control" type="text" placeholder="Search" id="searchPickUpDt" /><br>
			</div> -->
		 
						 <table id="pickUpDtId" data-toggle="table"
									data-toolbar="#toolbar" data-pagination="true"
									data-side-pagination="server" data-search="true" 
									data-page-list="[5, 10, 20, 50, 100, 200]"   data-height="400">
							  <thead>
								<tr class="btn-primary">
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="barcode" data-align="left" data-sortable="true">Barcode</th>
									<th data-field="style" data-align="left" data-sortable="true">Style</th>
									<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
									<th data-field="color" data-align="left" data-sortable="true">Color</th>
									<th data-field="pcs" data-align="left" data-sortable="true">Pcs</th>
									<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
									<th data-field="netWt" data-align="left" data-sortable="true">Net Wt</th>
									<th data-field="adjustedQty" data-align="left" data-sortable="true">Adjusted Qty</th>
									<th data-field="balanceQty" data-align="left" data-sortable="true">Balance Qty</th>
									<th data-field="transferQty" data-align="left" data-sortable="true">Transfer Qty</th>
									
								</tr>
							</thead>
						</table>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
					<input type="button" value="Transfer" class="btn btn-primary" onclick="javascript:popTransferPickUp();"/>
					<input type="button" value="Back" class="btn btn-success" onclick="javascript:goToBack();"/>
					<input type="hidden" id="objMtInvId" name="objMtInvId" />
					
			</div>
	   </div>
	
	
  </div>

 <div style="display: none">
				<form:form target="_blank"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="genReportss"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>

</div>




<script type="text/javascript">

var partyId = "";
var mtId = "";
var orderTypeId="";



$(document).ready(
		function(e) {
			
			
			
			$('#partyNm').autocomplete({
				source: "/jewels/manufacturing/masters/party/autoFillList.html",
				minLength : 2,
				
		        change: function (event, ui) {
                    if (ui.item == null || ui.item == undefined) {
                        $("#partyNm").val("");
                        //$("#supcode").attr("disabled", false);
                    }
                    // else {
                    //     $("#supcode").attr("disabled", true);
                    // }
                }
				
			});
			
			
			
			 $('#fromQuotNm').autocomplete({
					source:"/jewels/manufacturing/transactions/quotation/invNoList.html?partyNm="+$('#partyNm').val(),
							
							
		            change: function (event, ui) {
	                    if (ui.item == null || ui.item == undefined) {
	                        $("#fromQuotNm").val("");
	                        //$("#supcode").attr("disabled", false);
	                       
	                    }
	                    // else {
	                    //     $("#supcode").attr("disabled", true);
	                    // }
	                }
							
							
				}); 
			
			
			 $('#fromOrderNm').autocomplete({
					source:"/jewels/manufacturing/masters/order/invNoList.html?partyNm="+$('#partyNm').val(),
							
							
		            change: function (event, ui) {
	                 if (ui.item == null || ui.item == undefined) {
	                     $("#fromOrderNm").val("");
	                     //$("#supcode").attr("disabled", false);
	                    
	                 }
	                 // else {
	                 //     $("#supcode").attr("disabled", true);
	                 // }
	             }
							
							
				}); 
			 
			 
			 
			 
			 
			 
			 $('#forOrderNm').autocomplete({
				 source:"/jewels/manufacturing/masters/order/invNoList.html?partyNm=",
							
							
		            change: function (event, ui) {
	                    if (ui.item == null || ui.item == undefined) {
	                        $("#forOrderNm").val("");
	                        //$("#supcode").attr("disabled", false);
	                       
	                    }
	                    // else {
	                    //     $("#supcode").attr("disabled", true);
	                    // }
	                }
							
							
				}); 

			
			
			if('${pickUpType}' == 'order'){
				
				$("#fromOrderDivId").css('display', 'block');
			    $("#fromQuotDivId").css('display', 'none');
			    $("#forQuotOrderDivId").css('display', 'none');
				
			}else if('${pickUpType}' == 'orderFromQuot'){
				
				$("#fromOrderDivId").css('display', 'none');
			    $("#fromQuotDivId").css('display', 'block');
			    $("#forQuotOrderDivId").css('display', 'none');

				
			}else{
				
				$("#fromOrderDivId").css('display', 'none');
			    $("#fromQuotDivId").css('display', 'block');
			    $("#forQuotOrderDivId").css('display', 'block');

				
				
			}
			
			
			
			 var tempUrl = window.location.href;
			  partyId= tempUrl.substring(tempUrl.indexOf("partyId=")+8,tempUrl.indexOf("&mtId="));
			  mtId = tempUrl.substring(tempUrl.indexOf("&mtId=")+6,tempUrl.indexOf("&pickUpType"));
				
			    
			    
 			 //	popMtListPickUp();
				
			//-----//--- inline editing -----// 
		 	 
			$("#pickUpDtId").on("click", "td:not(.active)", function () {
				 var column_num = parseInt( $(this).index());
				 colNumber = column_num;
			
					 if(column_num === 10){
						 var $this = $(this);
				   		 var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text(),width:55 });
				    	 $this.html($textbox);
				         $textbox.select();
				         $textbox.focus();
						 
					 }
				 
			});
			
			
			 $("#pickUpDtId").on("blur", "input:text", function () {    
				  var $this = $(this);
				  $this.parent().removeClass("active").text($this.val());
		
				  if(colNumber === 10){
					  
					
					  
					  if(!!$this.val()){
						  
						  if('${pickUpType}' =='orderFromQuot'){
							  
							  if(Number($this.val()) <= Number(tempBalQty)){
								  $("#pickUpDtId").bootstrapTable('updateRow', {
										index : xstnTblRow,
										row : { 
											state : true,
											transferQty : $this.val(),
										}
									});
							  }else{
								  
								  displayMsg(this,null,"Transfer Qty Can not Greater Than Balance Qty");
								  $("#pickUpDtId").bootstrapTable('updateRow', {
										index : xstnTblRow,
										row : { 
											transferQty : 0.0,
										}
									});
								  
								  
								  $("#pickUpDtId").bootstrapTable('updateRow', {
										index : xstnTblRow,
										row : { 
											transferQty : 0.0,
										}
									});
								  
								  
								  
							  }
							  
						  }else{
							  $("#pickUpDtId").bootstrapTable('updateRow', {
									index : xstnTblRow,
									row : { 
										state : true,
										transferQty : $this.val(),
									}
								});
						  }
						  
						
					  }else{
						
						  $("#pickUpDtId").bootstrapTable('updateRow', {
								index : xstnTblRow,
								row : { 
									transferQty : 0.0,
								}
							});
					  }
					  
				  }
			
			
			}); // ending on blur 
			
			
			//alert($('#tranType').val());
			
				$("#searchPickUp").on("keyup", function() {
					 var value = $(this).val();
				    
				    $("#pickUpDtId tr").each(function(index) {
				
				        if (index != 0) {
		
				            $row = $(this);
				            var id 	= $row.find('td:eq(0)').text();
				            var kt 	= $row.find('td:eq(1)').text();
				            
				            
				            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && kt.toLowerCase().indexOf(value.toLowerCase()) != 0 ){
				                $(this).hide();
				            }else {
				                $(this).show();
				            }
				        }
				    });
				});        
			
				// dt search 	
				
				
				
		});


/* function popListPickUp(){
	
	 
	 $("#pickUpMtTableId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/order/pickUp/listing.html?partyId="+PartyId
			});
	 
	 $("#pickUpDtId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/order/pickUpDt/listing.html?mtId="+0
			});
}     */  



		
/* function popMtListPickUp(){
		 
			 $("#pickUpMtTableId")
			.bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/masters/order/pickUp/listing.html?partyId="+partyId
				});
	
	} */
	

function getNumVal(dt) {
	if (typeof dt === 'undefined') {
	} else {
		dt = dt.substring(dt.indexOf("<strong>") + 8, dt
				.indexOf("</strong>"));
	}

	return dt;
}


var xstnTblRow = -1;
var tempBalQty = 0.0;
$('#pickUpDtId').bootstrapTable({}).on(
	'click-row.bs.table',
	function(e, row, $element) {
		xstnTblRow = $element.attr('data-index');
		tempBalQty = jQuery.parseJSON(JSON.stringify(row)).balanceQty;
	});
	

$('#pickUpMtTableId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			$('#objMtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
			popDtDetails();
	});



function popDtDetails(){
	
	if('${pickUpType}'=='order'){
		
		$('#objMtInvId').val($("#fromOrderNm").val());
		
	}else{
	
		$('#objMtInvId').val($("#fromQuotNm").val());
	}
	
	 

	$("#pickUpDtId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/manufacturing/masters/order/pickUpDt/listing.html?invNo="+$('#objMtInvId').val()+"&pickUpType=${pickUpType}&forOrder="+$('#forOrderNm').val(),
			});
	
	
	
}

function notmatchstylewithorder(){
	
	if($('#forOrderNm').val() !=''){
		openNotMatchOrder();
	}

	
}


function goToBack(){
	
	if('${pickUpType}'=='order' || '${pickUpType}'=='orderFromQuot'){
		
		if( mtId > 0){
			window.location.href="/jewels/manufacturing/masters/order/edit/"+mtId+".html"
		}
		
	}else{
		
		if( mtId > 0){
			window.location.href="/jewels/manufacturing/transactions/quotMt/edit/"+mtId+".html"
		}
		
	}
	
	
	
	
}

function popTransferPickUp(){
	
	var data = $('#pickUpDtId').bootstrapTable('getSelections');
	
	var ids = $.map(data, function (item) {
			return item.id;
	});
	
	var qty = $.map(data, function (item) {
		return item.transferQty;
  	}); 
	
	//var postData = $(this).serializeArray();
	
	var pType='${pickUpType}';
					
	$.ajax({ 
		url : "/jewels/manufacturing/masters/order/transfer.html",
		type : "POST",
		data : {
			pODIds :ids+"",
			pTransferQty:qty+"",
			mtId:mtId,
			partyId:partyId,
			pickUpType:pType,
			
		
		},
		
		 success : function(data, textStatus, jqXHR) {	
			 $.unblockUI();
			 if(data === '1'){
				 displayInfoMsg(this, null, 'Data Transfer Successfully');
				 popDtDetails();
				 popMtListPickUp();
			 }else if(data ==="-1"){
				 displayMsg(this, null, 'Item Not Selected ');
			 }else if(data ==="-2"){
				 displayMsg(this, null, 'Transfer Qty Can Not Be Zero ');
			 }else{
				 displayInfoMsg(this, null, 'Data Transfer Successfully');
				 popDtDetails();
				 popMtListPickUp();
				 mtId=data;
			 }
			
			 
	
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			
		} 
		
	});


	}
	
	
	function popinvNo(){
		 
		$('#fromQuotNm').val('');
		
		$('#fromOrderNm').val('');
	}
	
	
	function popInvAutoFill(){
		
		 $('#fromQuotNm').autocomplete({
				source:"/jewels/manufacturing/transactions/quotation/invNoList.html?partyNm="+$('#partyNm').val(),
						
						
	            change: function (event, ui) {
                    if (ui.item == null || ui.item == undefined) {
                        $("#fromQuotNm").val("");
                        //$("#supcode").attr("disabled", false);
                       
                    }
                    // else {
                    //     $("#supcode").attr("disabled", true);
                    // }
                }
						
						
			}); 
		 
		 
		 
		 
		 $('#fromOrderNm').autocomplete({
				source:"/jewels/manufacturing/masters/order/invNoList.html?partyNm="+$('#partyNm').val(),
						
						
	            change: function (event, ui) {
                 if (ui.item == null || ui.item == undefined) {
                     $("#fromOrderNm").val("");
                     //$("#supcode").attr("disabled", false);
                    
                 }
                 // else {
                 //     $("#supcode").attr("disabled", true);
                 // }
             }
						
						
			}); 

		
	}
	
	
	function openNotMatchOrder(){
		
		
		$.ajax({ 
			url : "/jewels/manufacturing/masters/order/mismatchOrderWithQuot.html?quotInvNo="+$("#fromQuotNm").val()+"&ordInvNo="+$("#forOrderNm").val(),
	/* 		type : "POST",
			data : {
				pODIds :ids+"",
				pTransferQty:qty+"",
				mtId:mtId,
				partyId:partyId,
				pickUpType:pType,
				
			
			}, */
			
			 success : function(data, textStatus, jqXHR) {	
				 
				 var tempVal = data.split("$");
				 
				  if(tempVal[0] === '-1'){
					 $('#timeValCommonPdf').val(tempVal[1]);
						$("#genReportss").trigger('click');
				 }
				 
		
			},
			error : function(jqXHR, textStatus,
					errorThrown) {
				
			} 
			
		});

	}
	
	
	/*  var $table = $('#pickUpDtId')
	  var itemselections = []

	  function responseHandler(res) {
		 
	    $.each(res.rows, function (i, row) {
	      row.state = $.inArray(row.id, itemselections) !== -1
	      
	    })
	    
	    return res
	  }
	 
	 
	 $(function() {
		    $table.on('check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table',
		      function (e, rowsAfter, rowsBefore) {
		        var rows = rowsAfter

		       if (e.type == 'uncheck-all') {
		          rows = rowsBefore
		        } 

		        var ids = $.map(!$.isArray(rows) ? [rows] : rows, function (row) {
		          return row.id
		        })

		        var func = $.inArray(e.type, ['check', 'check-all']) > -1 ? 'union' : 'difference'
		        itemselections = window._[func](itemselections, ids)
		      })
		  }) */
	
</script> 



<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/common/blockUserInterface.js"></script>



