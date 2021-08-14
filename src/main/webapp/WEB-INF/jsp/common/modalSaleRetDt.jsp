<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>



	<!--------- SaleRetDt modal --------->
	
	<div class="modal fade" id="mySaleRetDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Sales Return Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="saleRetDtDtModalDivId">
				<form:form commandName="saleRetDt" id="saleRetDtFormId"
					action="/jewels/marketing/transactions/saleRetDt/add.html"
					cssClass="form-horizontal saleRetDtForm">
				
					
					<div class="row">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-4 text-right">Style No</label>
						</div>
						
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-4 text-right">Barcode</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Qty</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Purity</label>
						</div>
						
					
						
						
					</div>
					<div class="row">
						<div class="col-lg-4 col-sm-4">
							<form:input path="design.mainStyleNo" cssClass="form-control" onchange="javascript:popOrderMetalsfromDesign();getOrderGrossWt();"/>
						</div>
						
						<div class="col-lg-4 col-sm-4">
							<form:input path="barcode" cssClass="form-control"  autocomplete="off"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="pcs" cssClass="form-control" autocomplete="off"/>
						</div>
					
						
						<div class="col-lg-2 col-sm-2">
									<div id="purityDivId">
								<form:select path="purity.id" class="form-control"  onchange="javascript:updateMetalPurity();getOrderGrossWt();">
									<form:option value="" label=" Select Purity " />
									<form:options items="${purityDtMap}" />
								</form:select>
							</div>
			
						</div>
					
						
					
						
						
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					
					<div class="row">
					
					<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Color</label>
						</div>
						
							<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Gross Wt.</label>
						</div>
					
					
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Net Wt</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Metal Rate</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Metal Value</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Stone Value</label>
						</div>
						
						
						
						
						
						
					</div>
					<div class="row">
					
						<div class="col-lg-2 col-sm-2">
									<div id="colorDivId">
								<form:select path="color.id" class="form-control"  onchange="javascript:updateMetalColor();">
									<form:option value="" label=" Select Color" />
									<form:options items="${colorDtMap}" />
								</form:select>
							</div>
						
							
						</div>
						
					
						<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="grossWt" cssClass="form-control"  autocomplete="off" />
						</div>
					
					
							<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="netWt" cssClass="form-control"  autocomplete="off" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="metalRate" cssClass="form-control"  autocomplete="off" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="metalValue" cssClass="form-control"  autocomplete="off" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="stoneValue" cssClass="form-control"  autocomplete="off" />
						</div>
						
					
						
						
						
				
					
					
						
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
				
					<div class="row">
					<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Labour Value</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Final Price</label>
						</div>
					
					<div class="col-lg-8 col-sm-8">
							<label for="inputLabel3" class=".col-lg-4 text-right">Remark</label>
						</div>
					</div>
					
				<div class="row">
					<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="labValue" cssClass="form-control"  autocomplete="off" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="finalPrice" cssClass="form-control"  autocomplete="off" />
						</div>
					<div class="col-lg-8 col-sm-8">
							<form:input type="textArea" path="remark" cssClass="form-control"  autocomplete="off" />
						</div>
					</div>
					
					
					
				
				
					<div class="row">
						<div class="col-lg-12">
							<form:input type="hidden" path="id" id="modalSalRetDtId"/>
							<input type="hidden" id="metalDtData" name="metalDtData" />
							<input type="hidden" id="vSaleRetMtId" name="vSaleRetMtId" />
						</div>
					</div>
					
				</form:form>
			</div>
				
			
			
					
			
			
					<div id="saleRetDtMetalTableDivId" style="display: none">
					
						<div class="row">
							<div class="col-sm-12 col-sm-offset-5"><span style="font-weight: bolder;color: red;text-decoration: underline;">Metal Details</span></div>
						</div>
						
						<div class="col-sm-12">&nbsp;</div>
					
						<table  id="saleRetDtMetalIdTbl" data-toggle="table" 
							data-click-to-select="false" data-side-pagination="server" 
							data-striped="true" data-height="250" >
							<thead>
							  <tr>
							  <!-- data-formatter="qtyFormatterCombo" -->
								<th data-field="partNm" data-sortable="false">Part</th>
								<th data-field="purity" data-formatter="purityFormatterCombo">Purity</th>
								<th data-field="color" data-formatter="colorFormatterCombo">Color</th>
								<th data-field="qty" data-sortable="false">Qty</th>
								<th data-field="metalWt" data-formatter="metalWtFormatterCombo">Finish Metal Wt</th>
								<th data-field="mainMetal" data-formatter="mainMetalFormatterCombo">Main Metal</th>
							 </tr>
							</thead>
						</table> 
					</div>
			
			
	       
	      </div>
	      
	      <div class="modal-footer">
	        	<input type="button" value="Save" class="btn btn-default" onclick="javascript:popSaleRetDtSave()" id="orderDtBtnId"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">
	
	var saleRetModeFlag = "";
		
	$(document).ready(function(){
		$("#design\\.mainStyleNo").autocomplete({
			source: "/jewels/manufacturing/masters/design/autoFillList.html?partyId="+$('#party\\.id').val(),
			minLength : 2
		});
		
		
		
	  $(".saleRetDtForm").validate({
			rules : {
				'design.mainStyleNo' : {
					required : true,
				},
				pcs:{
					required:true,
				},
			},highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
		});
		
	  $("#dueDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
	  
	  $("#deliveryDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
	  
	
	  

	});
	
	
	function popSaleRetDtSave(){
		$('select').prop('disabled',false);

		$('#metalDtData').val(JSON.stringify($('#saleRetDtMetalIdTbl').bootstrapTable('getData')));
		$('#vSaleRetMtId').val($('#id').val());
		
		var postData = $("#saleRetDtFormId").serializeArray();
	
		var formURL = $("#saleRetDtFormId").attr("action");
		
	//	 if($(".saleRetDtFormId").valid()){
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
						
					if(data === 'added'){
						displayInfoMsg(this,null,"Record Added Successfully !");
						
						var purityVal=$('#saleRetDtFormId #purity\\.id').val();
						var colorVal=$('#saleRetDtFormId #color\\.id').val();
						
						 $('#saleRetDtDtModalDivId input[type="text"],textarea').val('');
						 $('#saleRetDtDtModalDivId input[type="number"]').val('0');
						 $('#saleRetDtDtModalDivId').find('select').val('');
						 $('#modalSalRetDtId').val('');
						 $('#orderMetalTableDivId').css('display','none');
						 $('#orderDtFormId #purity\\.id').val(purityVal);
						 $('#orderDtFormId #color\\.id').val(colorVal);
						 popSaleRetDt(disableFlg);
						 
					}else if(data === 'updated'){
						 displayInfoMsg(this,null,"Record Updated Successfully !");
						 var purityVal=$('#saleRetDtFormId #purity\\.id').val();
							var colorVal=$('#saleRetDtFormId #color\\.id').val();
						 $('#saleRetDtDtModalDivId input[type="text"],textarea').val('');
						 $('#saleRetDtDtModalDivId input[type="number"]').val('0');
						 $('#saleRetDtDtModalDivId').find('select').val('');
						 $('#modalSalRetDtId').val('');
						 $('#myOrderDtModal').modal('hide');
						 $('#orderDtFormId #purity\\.id').val(purityVal);
						 $('#orderDtFormId #color\\.id').val(colorVal);
						 
						 popSaleRetDt(disableFlg);
						
					}else{
						displayMsg(this,null,data);
					}
					
				
				
					
				},
				error : function(data, textStatus, jqXHR) {
					displayMsg(this,null,"Error Occoured , Contact Admin !");
				}
		
			});
		
	//	 }
		
		
	}
	
	
	
	function popOrderMetalsfromDesign(){
		
		var tempFlag = false;
		$.ajax({
			url:"/jewels/manufacturing/masters/design/styleCheckValidation.html?mainStyleNo="+$('#design\\.mainStyleNo').val().trim(),
			type:"GET",
			success:function(data){
				if(data === '-1'){
					tempFlag = true;
					 $('#saleRetDtMetalTableDivId').css('display','block');
				}else{
					 $('#saleRetDtMetalTableDivId').css('display','none');
				}
				
			},
			async:false,
		
		});
		
		
		if(tempFlag){
			
								
				$("#saleRetDtMetalIdTbl").bootstrapTable('refresh',{
					url : "/jewels/manufacturing/masters/orderMetal/listing/fromDesign.html?mainStyleNo="+$('#design\\.mainStyleNo').val().trim()+
							"&purityId="+$('#purity\\.id').val()+"&colorId="+$('#color\\.id').val()
				});	
			
			
			
		}else{
			displayMsg(this,null,"Style No is incorrect");
			
			
		}
		
	}

	
	
	//-------------------------------Modal---------------------------------------//
	
	
	var purityFlag = true;
	var purityData = '';
	var vPurityData ='';
 	function purityFormatterCombo(value,row,index){
 		
 		if(row.flag=='true'){
 			return value;	
 			
 		}else{
 			
 			
 			
 			var vPartNm=row.partNm;
 	 		
 	 		var tempData =  '<select  class="form-control" aria-required="true" aria-invalid="false" style="width:120px" onchange="javascript:popPurity(this,\''+vPartNm+'\','+index+')"><option value="">- Select Purity -</option>';
 	 		
 	 		
 	 		purityData = '';
 	 		if(purityFlag === true){
 	 		 vPurityData ='';
 	 			$.ajax({
 	 				url:"/jewels/manufacturing/masters/puritylist.html",
 	 				type:"GET",
 	 				dataType:"JSON",
 	 				success:function(data){
 	 					vPurityData = data;
 	  				},
 	 				async:false
 	 			});
 	 			
 	 			purityFlag = false;
 	 		}
 	 		
 	 		
 			$.each(vPurityData,function(key,val){
 				
 				 if(value === val){
 					 purityData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 				}else{
 					purityData += "<option value='"+key+"'>"+val+"</option>";
 				}
 				
 			});
 	 		
 	 		return tempData+''+purityData+''+'</select>';
 			
 		}
 		
	}
 	
 	
 	
 
 	
 	function popPurity(param,partNm,vidx){
 
 			var vMetalWt = 0.0;
 			$.ajax({
 				url:"/jewels/manufacturing/masters/orderMetal/conversion.html?purityNm="+param.value+"&mainStyleNo="+$('#design\\.mainStyleNo').val().trim()
 						+"&partNm="+partNm,
 				type:"GET",
 				success:function(data){
 				
 					vMetalWt = data;
 					
 				
 				},
 				async:false,
 			})
 			
 			
 			$('#saleRetDtMetalIdTbl').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				purity : param.value,
				metalWt : vMetalWt
				
			}
		});
 			
 		 
 		var data = $('#saleRetDtMetalIdTbl').bootstrapTable('getData');
 		
 		 $.each(data,function(idx,obj){
 	
 			 if(obj.mainMetal=='true'){
				$("#saleRetDtFormId #purity\\.id option").filter(function() {
					   return this.text == obj.purity; 
				}).prop('selected', 'selected'); 
 				 
 			 }
 			 
 		 });
 		
 	}
 	
 	
 	
 	
 	
 	
	var colorFlag = true;
	var colorData = '';
	var vColorData ='';
 	function colorFormatterCombo(value,row,index){
 		
 		
		
 		if(row.flag=='true'){
 			return value;	
 			
 		}else{
 			var tempData =  '<select class="form-control" aria-required="true" aria-invalid="false" style="width:120px" onchange="javascript:popColor(this,'+index+')"><option value="">- Select Color -</option>';
 	 		
 	 		colorData = '';
 	 		if(colorFlag === true){
 	 		 vColorData ='';
 	 			$.ajax({
 	 				url:"/jewels/manufacturing/masters/colorlist.html",
 	 				type:"GET",
 	 				dataType:"JSON",
 	 				success:function(data){
 	 					vColorData = data;
 	  				},
 	 				async:false
 	 			});
 	 			
 	 			colorFlag = false;
 	 		}
 	 		
 	 		
 	 		
 			$.each(vColorData,function(key,val){
 				
 				 if(value === val){
 					 colorData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 				}else{
 					colorData += "<option value='"+key+"'>"+val+"</option>";
 				}
 				
 			});
 	 		
 	 		return tempData+''+colorData+''+'</select>';
 		}
 	 		
	} 
 	
 	
 	function popColor(param,vidx){
 		
 		 $('#saleRetDtMetalIdTbl').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				color : param.value,
			}
		});
 		 
 	
		 
		 var data = $('#saleRetDtMetalIdTbl').bootstrapTable('getData');
	 		
 		 $.each(data,function(idx,obj){
 	
 			 if(obj.mainMetal=='true'){
				$("#saleRetDtFormId #color\\.id option").filter(function() {
					   return this.text == obj.color; 
				}).prop('selected', 'selected'); 
 				 
 			 }
 			 
 		 });
 		
 		 
 		
 	}
	
 	
 	
	function qtyFormatterCombo(value,row,index){
 	
		return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateQty(this,'+index+')"  />';
	}
	
	function updateQty(param,vidx){
		
		$('#saleRetDtMetalIdTbl').bootstrapTable('updateRow', {
			index : Number(vidx),
			row : {
				qty : param.value,
			}
		});
 		
 	}
	
	
	
	
	function metalWtFormatterCombo(value,row,index){
		return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateMetalWt(this,'+index+')"  />';
	}
	
	function updateMetalWt(param,vidx){
 		$('#saleRetDtMetalIdTbl').bootstrapTable('updateRow', {
			index : Number(vidx),
			row : {
				metalWt : param.value,
			}
		});
 		
 	}
	
	
	
  function mainMetalFormatterCombo(value,row,index) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';

	}
  
  
  $('#mySaleRetDtModal').on('shown.bs.modal', function () {
		 $(document.documentElement).css('overflow', 'hidden');
		 $('#mySaleRetDtModal').show().css('overflow', 'auto');
	});


	
	 
	 $('#myOrderDtModal').on('hidden.bs.modal', function () {
		 $(document.documentElement).css('overflow', 'scroll');
		  colorFlag = true;
		  purityFlag = true;
		  $('#saleRetDtDtModalDivId input[type="text"],textarea').val('');
		  $('#saleRetDtDtModalDivId input[type="number"]').val('0');
		  $('#saleRetDtDtModalDivId').find('select').val('');
		  $('#modalSalRetDtId').val('');
		  modalModeFlag = "";
		  
		})
	
 
	 
	 
	 
	 
 function updateMetalColor(){
		 
	 var ab=document.getElementById("color.id");
	 var colorNm=ab.options[ab.selectedIndex].text;
	 
	 
	
	
	var data =$('#saleRetDtMetalIdTbl').bootstrapTable('getData');
	 $.each(data,function(idx,obj){
		  
		 
		 $('#saleRetDtMetalIdTbl').bootstrapTable('updateRow', {
				index : idx,
				row : {
					 color : colorNm,
					

				}
			}); 
		 
	 });
	 
	 }
 
 
	function updateMetalPurity(){
		
		 var ab=document.getElementById("purity.id");
		 var purityNm=ab.options[ab.selectedIndex].text;
		 
		
		
		
		 var data =JSON.stringify($('#saleRetDtMetalIdTbl').bootstrapTable('getData'));
		 $.each(JSON.parse(data),function(idx,obj){
	 
			 $('#saleRetDtMetalIdTbl').bootstrapTable('updateRow', {
					index :  Number(idx),
					row : {
						 purity : purityNm,
						

					}
				}); 
			 
		 });
		 

	}
	
	
	
	
	
	
	function getOrderGrossWt(){
		
		if($('#design\\.mainStyleNo').val().trim() != '' &&  $('#purity\\.id').val() != ''){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/design/getOrderGrossWt.html?mainStyleNo="+$('#design\\.mainStyleNo').val().trim()+"&purityId="+$('#purity\\.id').val(),
			type:"GET",
			dataType:"JSON",
			success:function(data){

			
		$.each(data, function(k, v) {
									if (k == "grossWt") {

										$('#grossWt').val(v);
									}
									if (k == "reqCarat") {
										$('#reqCarat').val(v);
									}

								});

							},
							async : false,
						});
			}
		}
	</script>
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	
	
	
	