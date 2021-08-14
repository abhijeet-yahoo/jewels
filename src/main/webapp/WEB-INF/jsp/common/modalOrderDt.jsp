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



	<!--------- OrderDt modal --------->
	
	<div class="modal fade" id="myOrderDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Order Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="orderDtModalDivId">
				<form:form commandName="orderDt" id="orderDtFormId"
					action="/jewels/manufacturing/masters/orderDt/add.html"
					cssClass="form-horizontal orderDtForm">
				
					
					<div class="row">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-4 text-right">Style No</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Qty</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Cancel Qty</label>
						</div>
						
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Purity</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Color</label>
						</div>
						
						
						
						
					</div>
					<div class="row">
						<div class="col-lg-4 col-sm-4">
							<form:input path="design.mainStyleNo" cssClass="form-control" onchange="javascript:popOrderMetalsfromDesign();getOrderGrossWt();getStampFromMaster()"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="pcs" cssClass="form-control" onchange="javascript:updatePcs();" autocomplete="off"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="cancelQty" cssClass="form-control"  autocomplete="off"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
									<div id="purityDivId">
								<form:select path="purity.id" class="form-control"  onchange="javascript:updateMetalPurity();getStampFromMaster();getOrderGrossWt();">
									<form:option value="" label=" Select Purity " />
									<form:options items="${purityMap}" />
								</form:select>
							</div>
			
						</div>
						<div class="col-lg-2 col-sm-2">
									<div id="colorDivId">
								<form:select path="color.id" class="form-control"  onchange="javascript:updateMetalColor();">
									<form:option value="" label=" Select Color" />
									<form:options items="${colorMap}" />
								</form:select>
							</div>
						
							
						</div>
						
						
						
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					
					<div class="row">
					
					<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Gross Wt.</label>
						</div>
					
					<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Due Date</label>
						</div>
						
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Size</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Ref No</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Item</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Ord Ref</label>
						</div>
						
					</div>
					<div class="row">
					
					<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="grossWt" cssClass="form-control"  autocomplete="off" />
						</div>
					
					<div class="col-lg-2 col-sm-2">
					<form:input path="dueDate" cssClass="form-control" autocomplete="off"/>
					<form:errors path="dueDate" />
				</div>
					
					
						
						<div class="col-lg-2 col-sm-2">
							<div id="productSizevId">
								<form:select path="productSize.id" class="form-control">
									<form:option value="" label=" Select Size " />
									<form:options items="${productSizeMap}" />
								</form:select>
							</div>
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="refNo" cssClass="form-control" autocomplete="off"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="item" cssClass="form-control" autocomplete="off"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="ordRef" cssClass="form-control" autocomplete="off"/>
						</div>
						
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					
					
					
					
					
					
					
						<div class="row">
						
						
					
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel4" class=".col-lg-4 text-right">Stamp No</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Req Cts</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Discount %</label>
						</div>
						
							<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Barcode</label>
						</div>

						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Revi.Deliv. Dt</label>
						</div>


						
						
					</div>
					<div class="row">
						<div class="col-lg-4 col-sm-4">
							<form:input path="stampInst" cssClass="form-control" autocomplete="off" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="reqCarat" cssClass="form-control" onchange="javascript:getStampFromMaster();" />
						</div>
						
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="discPercent" cssClass="form-control" autocomplete="off"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="barcode" cssClass="form-control" autocomplete="off"/>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<form:input path="deliveryDate" cssClass="form-control" autocomplete="off"/>
						</div>
						
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					
					
					
					
					<div class="row">
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel6" class=".col-lg-6 text-right">Item Remark</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel6" class=".col-lg-6 text-right">Style Master Remark</label>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 col-sm-6">
							<form:textarea path="remark" cssClass="form-control" />
						</div>
						<div class="col-lg-6 col-sm-6">
							<form:textarea path="designRemark" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					
				
				
					<div class="row">
						<div class="col-lg-12">
							<form:input type="hidden" path="id" id="modalOrderDtId"/>
							<input type="hidden" id="metalDtData" name="metalDtData" />
							<input type="hidden" id="vOrderMtId" name="vOrderMtId" />
						</div>
					</div>
					
				</form:form>
			</div>
				
			
			
					
			
			
					<div id="orderMetalTableDivId" style="display: none">
					
						<div class="row">
							<div class="col-sm-12 col-sm-offset-5"><span style="font-weight: bolder;color: red;text-decoration: underline;">Metal Details</span></div>
						</div>
						
						<div class="col-sm-12">&nbsp;</div>
					
						<table  id="orderMetalIdTbl" data-toggle="table" 
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
	        	<input type="button" value="Save" class="btn btn-default" onclick="javascript:popOrderDtSave()" id="orderDtBtnId"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<input type="hidden" id="prodDt" name="prodDt">
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">
	
	var modalModeFlag = "";
		
	$(document).ready(function(){
		$("#design\\.mainStyleNo").autocomplete({
			source: "/jewels/manufacturing/masters/design/autoFillList.html?partyId="+$('#party\\.id').val(),
			minLength : 2
		});
		
		
		
	  $(".orderDtForm").validate({
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
	
	
	function popOrderDtSave(){
		$('select').prop('disabled',false);

		$('#metalDtData').val(JSON.stringify($('#orderMetalIdTbl').bootstrapTable('getData')));
		$('#vOrderMtId').val($('#orderMtId').val());
		
		var postData = $("#orderDtFormId").serializeArray();
	
		var formURL = $("#orderDtFormId").attr("action");
		
		 if($(".orderDtForm").valid()){
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
						
					if(data === 'added'){
						displayInfoMsg(this,null,"Record Added Successfully !");
						
						var purityVal=$('#orderDtFormId #purity\\.id').val();
						var colorVal=$('#orderDtFormId #color\\.id').val();
						
						 $('#orderDtModalDivId input[type="text"]').val('');
						 $('#orderDtModalDivId input[type="number"]').val('0');
						 $('#orderDtModalDivId').find('select').val('');
						 $('#orderDtModalDivId').find('textarea').val('');
						 $('#modalOrderDtId').val('');
						 $('#orderMetalTableDivId').css('display','none');
						 $('#orderDtFormId #purity\\.id').val(purityVal);
						 $('#orderDtFormId #color\\.id').val(colorVal);
						 popOrderDetails('add');
						 getOrdDtTotQty();
					}else if(data === 'updated'){
						 displayInfoMsg(this,null,"Record Updated Successfully !");
						 var purityVal=$('#orderDtFormId #purity\\.id').val();
							var colorVal=$('#orderDtFormId #color\\.id').val();
						 $('#orderDtModalDivId input[type="text"]').val('');
						 $('#orderDtModalDivId input[type="number"]').val('0');
						 $('#orderDtModalDivId').find('select').val('');
						 $('#orderDtModalDivId').find('textarea').val('');
						 $('#modalOrderDtId').val('');
						 $('#myOrderDtModal').modal('hide');
						 $('#orderDtFormId #purity\\.id').val(purityVal);
						 $('#orderDtFormId #color\\.id').val(colorVal);
						 
						 popOrderDetails('edit');
						 getOrdDtTotQty();
					}else{
						displayMsg(this,null,data);
					}
					
				
				
					
				},
				error : function(data, textStatus, jqXHR) {
					displayMsg(this,null,"Error Occoured , Contact Admin !");
				}
		
			});
		
		 }
		
		
	}
	
	
	function popDesignDetails(){
		
		
		//alert('xxx   '+$('#prodDt').val());
		if(!!$("#design\\.mainStyleNo").val()){
			
			$.ajax({
				url : "/jewels/manufacturing/masters/orderDt/designAllDetails.html?purityId="+$('#purity\\.id').val()
						+"&mainStyleNo="+$('#design\\.mainStyleNo').val().trim(),
				type : 'GET',
				success : function(data) {
					var tempVal = data.split("_");
					$("#productSizevId").html(tempVal[0]);
					$('#designRemark').val(tempVal[1]);
					
					if(tempVal[2] !='null'){
						
						$('#productSize\\.id').val(tempVal[2]);	
					}
					
					//popOrderMetals();
				}
			});
			
			
			  $("#dueDate").val($('#prodDt').val());
		}
	}
	
	
	function popOrderMetalsfromDesign(){
		
		var tempFlag = false;
		$.ajax({
			url:"/jewels/manufacturing/masters/design/styleCheckValidation.html?mainStyleNo="+$('#design\\.mainStyleNo').val().trim(),
			type:"GET",
			success:function(data){
				if(data === '-1'){
					tempFlag = true;
					 $('#orderMetalTableDivId').css('display','block');
				}else{
					 $('#orderMetalTableDivId').css('display','none');
				}
				
			},
			async:false,
		
		});
		
		
		if(tempFlag){
			if(modelFlag){
								
				$("#orderMetalIdTbl").bootstrapTable('refresh',{
					url : "/jewels/manufacturing/masters/orderMetal/listing/fromDesign.html?mainStyleNo="+$('#design\\.mainStyleNo').val().trim()+
							"&purityId="+$('#orderDtFormId #purity\\.id').val()+"&colorId="+$('#orderDtFormId #color\\.id').val()
				});	
			}else{
				 $("#orderMetalIdTbl").bootstrapTable('refresh',{
						url : "/jewels/manufacturing/masters/orderMetal/listing/fromOrderDt.html?dtId="+modalOrderDtId
					});
			}
			
			popDesignDetails();
			
		}else{
			displayMsg(this,null,"Style No is incorrect");
			
			
		}
		
		
		
		
	}
	
	
	
	$('#orderMetalIdTbl').bootstrapTable({}).on('load-success.bs.table',
			function(e, data) {
			
/* 				var data = JSON.stringify($("#orderMetalIdTbl").bootstrapTable('getData'));
			
				
				var i=0;
				$.each(JSON.parse(data), function(idx, obj) {
					
					
								
					if(obj.flag == 'true'){
						
						$('#purity'+i).attr('disabled','disabled');
						$('#color'+i).attr('disabled','disabled');
						
						
					}
					
					Number(i++);
					
				}); */
				
			});
		
	
	
	
	
	
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
 		
 		
 		
 		
 		
 		
 		
 	/* 	
 		if(purityFlag === true){
 			purityData = '';
 			$.ajax({
 				url:"/jewels/manufacturing/masters/puritylist.html",
 				type:"GET",
 				dataType:"JSON",
 				success:function(data){
 					$.each(data,function(key,val){
 					 if(value === val){
 							if(modalModeFlag === 'add'){
 								if(!!$('#purity\\.id').val()){
 									purityData += "<option value='"+key+"'>"+val+"</option>";
 								}else{
 									purityData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 								}
 							}else{
 								purityData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 							}
 							 
 							 //purityData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 						}else{
 							purityData += "<option value='"+key+"'>"+val+"</option>";
 						}
 						
 					});
 				},
 				async:false
 			});
 			
 			purityFlag = false;
 		}else{
			 setTimeout(function(){
				    if(modalModeFlag === 'add'){
						if(!!$('#purity\\.id').val()){
						}else{
							$("#"+tempId+" option[value='"+ value +"']").attr('selected', 'selected');
						}
					}else{
						$("#"+tempId+" option[value='"+ value +"']").attr('selected', 'selected');	
					}
				 
	 				
	 			}, 2);
	 			
	 		}
 		
 		
 		if(modalModeFlag === 'add'){
 			setTimeout(function(){
				if(!!$('#purity\\.id').val()){
					$("#"+tempId+" option[value='"+ $("#purity\\.id option:selected").text() +"']").attr('selected', 'selected').trigger('change');
					
				}
 			}, 8);
 		}
 		
 		
 		return tempData+''+purityData+''+'</select>'; */
	}
 	
 	
 	
 
 	
 	function popPurity(param,partNm,vidx){
 		
 			/* 		var data = $('#orderMetalIdTbl').bootstrapTable('getData');
 			var returnedData = $.grep(data, function(element, index) {
				return element.srNo == tempId;
 				
 			});
 			
 			
 */ 	
 
 
 
			/*  $('#orderMetalIdTbl').bootstrapTable('updateRow', {
					index : Number(rowIdx),
					 row : {
						 purity : param.value,
					} 
				});
 
  */
 
 
 
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
 			
 			
 		 
 		
			
 			
 			$('#orderMetalIdTbl').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				purity : param.value,
				metalWt : vMetalWt
				
			}
		});
 			
 			

 		 

 		 
 		 
 		var data = $('#orderMetalIdTbl').bootstrapTable('getData');
 		
 		 $.each(data,function(idx,obj){
 	
 			 if(obj.mainMetal=='true'){
				$("#orderDtFormId #purity\\.id option").filter(function() {
					   return this.text == obj.purity; 
				}).prop('selected', 'selected'); 
 				 
 			 }
 			 
 		 });
 		
 		
 		 
 	/* 	 $.grep(data, function(element, index) {
 			if(element.mainMetal ==='true' && element.purity=== $('#'+param.id).val()){
 				 	 var text1 = $('#'+param.id).val();
 						$("#orderDtFormId #purity\\.id option").filter(function() {
 							   return this.text == text1; 
 						}).prop('selected', 'selected'); 
 						
 					
 					
 		 				}
 		 				
 		 				
 		 			});
 		
 		$('#'+param.id).val(param.value); */
 		
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
 	 		
	
 		
 		
 		
 		
 	/* 	if(colorFlag === true){
 			colorData = '';
 			$.ajax({
 				url:"/jewels/manufacturing/masters/colorlist.html",
 				type:"GET",
 				dataType:"JSON",
 				success:function(data){
 					$.each(data,function(key,val){
 						 if(value === val){
 							if(modalModeFlag === 'add'){
 								if(!!$('#color\\.id').val()){
 									colorData += "<option value='"+key+"'>"+val+"</option>";
 								}else{
 									colorData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 								}
 							}else{
 								colorData += "<option value='"+key+"' selected='selected'>"+val+"</option>";
 							}
 							
 						}else{
 							colorData += "<option value='"+key+"'>"+val+"</option>";
 						}
 						
 					});
 				},
 				async:false
 			});
 			
 			
 			colorFlag = false;
 		}else{
 			 setTimeout(function(){
 				//$("#"+tempId+" option[value='"+ value +"']").attr('selected', 'selected');
 				
 				 if(modalModeFlag === 'add'){
						if(!!$('#color\\.id').val()){
						}else{
							$("#"+tempId+" option[value='"+ value +"']").attr('selected', 'selected');
						}
					}else{
						$("#"+tempId+" option[value='"+ value +"']").attr('selected', 'selected');	
					}
 				
 				
 			}, 2);
 			
 		}
 		
 		
 		if(modalModeFlag === 'add'){
 			setTimeout(function(){
 				if(!!$('#color\\.id').val()){
 					$("#"+tempId+" option[value='"+ $("#color\\.id option:selected").text() +"']").attr('selected', 'selected').trigger('change');
 				}
 			}, 8);
 		}
 		
 		return tempData+''+colorData+''+'</select>'; */
 		
	} 
 	
 	
 	function popColor(param,vidx){
 		
 		 $('#orderMetalIdTbl').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				color : param.value,
			}
		});
 		 
 		/* var data = $('#orderMetalIdTbl').bootstrapTable('getData');
 		 
		 $.grep(data, function(element, index) {
		if(element.mainMetal ==='true' && element.color=== $('#'+param.id).val()){
			 	 var text1 = $('#'+param.id).val();
					$("#orderDtFormId #color\\.id option").filter(function() {
						   return this.text == text1; 
					}).prop('selected', 'selected'); 
					
				
				
	 				}
	 				
	 				
	 			});
		  */
		 
		 var data = $('#orderMetalIdTbl').bootstrapTable('getData');
	 		
 		 $.each(data,function(idx,obj){
 	
 			 if(obj.mainMetal=='true'){
				$("#orderDtFormId #color\\.id option").filter(function() {
					   return this.text == obj.color; 
				}).prop('selected', 'selected'); 
 				 
 			 }
 			 
 		 });
 		
 		 
 		 
 		
 		
 		
 	}
	
 	
 	
	function qtyFormatterCombo(value,row,index){
 	
		return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateQty(this,'+index+')"  />';
	}
	
	function updateQty(param,vidx){
		
		$('#orderMetalIdTbl').bootstrapTable('updateRow', {
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
 		$('#orderMetalIdTbl').bootstrapTable('updateRow', {
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
  
  
  $('#myOrderDtModal').on('shown.bs.modal', function () {
		 $(document.documentElement).css('overflow', 'hidden');
		 $('#myOrderDtModal').show().css('overflow', 'auto');
	});


	
	 
	 $('#myOrderDtModal').on('hidden.bs.modal', function () {
		 $(document.documentElement).css('overflow', 'scroll');
		  colorFlag = true;
		  purityFlag = true;
		  $('#orderDtModalDivId input[type="text"]').val('');
		  $('#orderDtModalDivId input[type="number"]').val('0');
		  $('#orderDtModalDivId').find('select').val('');
		  $('#orderDtModalDivId').find('textarea').val('');
		  $('#modalOrderDtId').val('');
		  modalModeFlag = "";
		  
		})
	
 
	 
	 
	 
	 
 function updateMetalColor(){
		 
	 var ab=document.getElementById("color.id");
	 var colorNm=ab.options[ab.selectedIndex].text;
	 
	 
	
	
	var data =$('#orderMetalIdTbl').bootstrapTable('getData');
	 $.each(data,function(idx,obj){
		  
		 
		 $('#orderMetalIdTbl').bootstrapTable('updateRow', {
				index : idx,
				row : {
					 color : colorNm,
					

				}
			}); 
		 
		//employeeFormatter(value);
		 
		 
		 
	 });
	 
	 }
 
 
	function updateMetalPurity(){
		
		 var ab=document.getElementById("purity.id");
		 var purityNm=ab.options[ab.selectedIndex].text;
		 
		
		
		
		 var data =JSON.stringify($('#orderMetalIdTbl').bootstrapTable('getData'));
		 $.each(JSON.parse(data),function(idx,obj){
	 
			 $('#orderMetalIdTbl').bootstrapTable('updateRow', {
					index :  Number(idx),
					row : {
						 purity : purityNm,
						

					}
				}); 
			 
			//employeeFormatter(value);
			 
			 
			 
		 });
		 

	}
	
	function updatePcs(){
		
		
	
		if(!modelFlag){
			
			$.ajax({
				url:"/jewels/manufacturing/masters/orderDt/updateQty.html?orderDtId="+ $('#orderDtPKId').val()+ "&newPcs="
				+ $('#pcs').val(),
				type:"GET",
				success:function(data){
					
					
					if(data === ""){
						/* $('#pcs').val($('#pcs').val()); */
					}else{
						displayMsg(this, null, 'Bag Already Generated, Qty Can not be Reduced');
						$('#pcs').val(data);	
						
					}
					
				},
				async:false,
			}); 
			
		}
		

	}
	
	

	function getStampFromMaster(){
		
		var ab = document.getElementById("purity.id");
		var purityNm = ab.options[ab.selectedIndex].text;
				
		$.ajax({
			url:"/jewels/manufacturing/masters/orderDt/getStampFromMaster.html?partyId="+ $('#party\\.id').val()+"&purityId="+ $('#purity\\.id').val()
					+"&reqCts="+ $('#reqCarat').val(),
			type:"GET",
			success:function(data){
			
				if(data != "-1"){
					$('#stampInst').val(data);	
				 }else{
				
						if(purityNm !='Select Purity'){
							$('#stampInst').val(purityNm);	
						}else{
							$('#stampInst').val('');
						}
							
					
						
					
				}
				
				
			},
			async:false,
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

								//console.log('data    '+JSON.stringify(data));

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
	
	
	
	