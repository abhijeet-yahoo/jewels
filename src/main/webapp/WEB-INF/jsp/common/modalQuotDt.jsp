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



	<!--------- QuotDt modal --------->
	
	<div class="modal fade" id="myQuotDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Quotation Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="quotDtModalDivId">
				<form:form commandName="quotDt" id="quotDtFormId"
					action="/jewels/manufacturing/transactions/quotDt/add.html"
					cssClass="form-horizontal quotDtForm">
				
					
						<div class="row">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-4 text-right">Style No</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Qty</label>
						</div>
						
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Purity</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-4 text-right">Color</label>
						</div>
						
							<div class="col-lg-2 col-sm-2">
							<label for="inputLabel4" class=".col-lg-4 text-right">Gross Wt.</label>
						</div>	
						
						
					</div>
					<div class="row">
						<div class="col-lg-4 col-sm-4">
							<form:input path="design.mainStyleNo" cssClass="form-control" onchange="javascript:popQuotMetalsfromDesign();getQuotGrossWt();"/>
						</div>
						
							<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="pcs" cssClass="form-control"  autocomplete="off" />
						</div>
						
						<div class="col-lg-2 col-sm-2">
									<div id="purityDivId">
								<form:select path="purity.id" class="form-control"  onchange="javascript:updateMetalPurity();getQuotGrossWt();getStampFromMaster();">
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
						
						
						<div class="col-lg-2 col-sm-2">
							<form:input type="number" path="grossWt" cssClass="form-control"  autocomplete="off" />
						</div>
						
							
					
						
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					
					<div class="row">
					
					
						
				
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-4 text-right">Size</label>
						</div>
						
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-4 text-right">Ref No</label>
						</div>
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel4" class=".col-lg-4 text-right">Stamp No</label>
						</div>
						
					</div>
					<div class="row">
				
						
				
							
						<div class="col-lg-4 col-sm-4">
							<div id="productSizevId">
								<form:select path="productSize.id" class="form-control">
									<form:option value="" label=" Select Size " />
									<form:options items="${productSizeMap}" />
								</form:select>
							</div>
						</div>
						
						
						<div class="col-lg-4 col-sm-4">
							<form:input path="refNo" cssClass="form-control" autocomplete="off"/>
						</div>
					
						<div class="col-lg-4 col-sm-4">
							<form:input path="stampInst" cssClass="form-control" autocomplete="off"/>
						</div>
					
						
					</div>
					
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
						<div class="row">
						
							<div class="col-lg-4 col-sm-4">
							<label for="inputLabel4" class=".col-lg-4 text-right">Barcode</label>
						</div>
					
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel4" class=".col-lg-4 text-right">Dis Amt.</label>
						</div>
					</div>
					<div class="row">
					
					<div class="col-lg-4 col-sm-4">
							<form:input path="barcode" cssClass="form-control" autocomplete="off"/>
						</div>
						
						<div class="col-lg-4 col-sm-4">
							<form:input type="number"  path="discAmount" cssClass="form-control" autocomplete="off" />
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
							<form:input path="designRemark" cssClass="form-control" readonly="true"/>
						</div>
					</div>
					
				
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
				
					<div class="row">
						<div class="col-lg-12">
							<form:input type="hidden" path="id" id="modalQuotDtId"/>
							<input type="hidden" id="metalDtData" name="metalDtData" />
							<input type="hidden" id="vQuotMtId" name="vQuotMtId" />
						</div>
					</div>
					
				</form:form>
			</div>
				
			
			
					
			
			
					<div id="quotMetalTableDivId" style="display: none">
					
						<div class="row">
							<div class="col-sm-12 col-sm-offset-5"><span style="font-weight: bolder;color: red;text-decoration: underline;">Metal Details</span></div>
						</div>
						
						<div class="col-sm-12">&nbsp;</div>
					
						<table  id="quotMetalIdTbl" data-toggle="table" 
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
	        	<input type="button" value="Save" class="btn btn-default" onclick="javascript:popQuotDtSave()"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
		
		
	  $(".quotDtForm").validate({
			rules : {
				'design.mainStyleNo' : {
					required : true,
				},
				pcs:{
					required:true,
					greaterThan : "0,0",
				},
			},highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			
			messages : {
				pcs : {
					greaterThan : "This field is required"
				},
				
			},
		});
		
		
	});
	
	
	function popQuotDtSave(){
		
		$('select').prop('disabled',false);

		
		$('#metalDtData').val(JSON.stringify($('#quotMetalIdTbl').bootstrapTable('getData')));
		
		$('#vQuotMtId').val($('#quotMtId').val());
				
		var postData = $("#quotDtFormId").serializeArray();
		var formURL = $("#quotDtFormId").attr("action");
		
		 if($(".quotDtForm").valid()){
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
						
					if(data === 'added'){
						displayInfoMsg(this,null,"Record Added Successfully !");
						
						var purityVal=$('#quotDtFormId #purity\\.id').val();
						var colorVal=$('#quotDtFormId #color\\.id').val();
						
						 $('#quotDtModalDivId input[type="text"],textarea').val('');
						 $('#quotDtModalDivId input[type="number"]').val('0');
						 $('#quotDtModalDivId').find('select').val('');
						 $('#modalQuotDtId').val('');
						 $('#quotMetalTableDivId').css('display','none');
						 $('#quotDtFormId #purity\\.id').val(purityVal);
						 $('#quotDtFormId #color\\.id').val(colorVal);
						 popQuotationDetails();
					}else if(data === 'updated'){
						 displayInfoMsg(this,null,"Record Updated Successfully !");
						
						 var purityVal=$('#quotDtFormId #purity\\.id').val();
						var colorVal=$('#quotDtFormId #color\\.id').val();
						
						 $('#quotDtModalDivId input[type="text"],textarea').val('');
						 $('#quotDtModalDivId input[type="number"]').val('0');
						 $('#quotDtModalDivId').find('select').val('');
						 $('#modalQuotDtId').val('');
						 $('#myQuotDtModal').modal('hide');
						 $('#quotDtFormId #purity\\.id').val(purityVal);
						 $('#quotDtFormId #color\\.id').val(colorVal);
						 popQuotationDetails();
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
		if(!!$("#design\\.mainStyleNo").val()){
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/quotDt/designAllDetails.html?purityId="+$('#purity\\.id').val()
						+"&mainStyleNo="+$('#design\\.mainStyleNo').val().trim(),
				type : 'GET',
				success : function(data) {
					var tempVal = data.split("_");
					$("#productSizevId").html(tempVal[0]);
					$('#designRemark').val(tempVal[1]);
					$('#productSize\\.id').val(tempVal[2]);
					//popQuotMetals();
				}
			});
			
		}
	}
	
	
	function popQuotMetalsfromDesign(){
		//console.log("xxxxxxxxxxxxxxxx on changexxxxxxxxxxxxxxxxxxxxxxxxxxx");
		var tempFlag = false;
		$.ajax({
			url:"/jewels/manufacturing/masters/design/styleCheckValidation.html?mainStyleNo="+$('#design\\.mainStyleNo').val().trim(),
			type:"GET",
			success:function(data){
				if(data === '-1'){
					tempFlag = true;
					 $('#quotMetalTableDivId').css('display','block');
				}else{
					 $('#quotMetalTableDivId').css('display','none');
				}
				
			},
			async:false,
		
		});
		
		
		if(tempFlag){
			if(modelFlag){
				$("#quotMetalIdTbl").bootstrapTable('refresh',{
					url : "/jewels/manufacturing/transactions/quotMetal/listing/fromDesign.html?mainStyleNo="+$('#design\\.mainStyleNo').val().trim()+
					"&purityId="+$('#quotDtFormId #purity\\.id').val()+"&colorId="+$('#quotDtFormId #color\\.id').val()
				});	
			}else{
				 $("#quotMetalIdTbl").bootstrapTable('refresh',{
						url : "/jewels/manufacturing/transactions/quotMetal/listing/fromQuotDt.html?dtId="+modalQuotDtId
					});
			}
			
			popDesignDetails();
			
		}else{
			displayMsg(this,null,"Style No is incorrect");
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	//-------------------------------Modal---------------------------------------//
	
	
	var purityFlag = true;
	var purityData = '';
	var vPurityData ='';
 	function purityFormatterCombo(value,row,index){
		
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
 	
 	
	function popPurity(param,partNm,vidx){
			
 			
 			
 				var vMetalWt = 0.0;
 			$.ajax({
 				url:"/jewels/manufacturing/masters/quotMetal/conversion.html?purityNm="+param.value+"&mainStyleNo="+$('#design\\.mainStyleNo').val().trim()
 						+"&partNm="+partNm,
 				type:"GET",
 				success:function(data){
 				
 					vMetalWt = data;
 					
 				
 				},
 				async:false,
 			})
 			
 			
 						
 			
 			$('#quotMetalIdTbl').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				purity : param.value,
				metalWt : vMetalWt
				
			}
		});
 			
 			
 			
 			
 			
 		
 			
 			

 		 

 		 
 		 
 		var data = $('#quotMetalIdTbl').bootstrapTable('getData');
 		
 		
		 $.each(data,function(idx,obj){
			 	
 			 if(obj.mainMetal=='true'){
				$("#quotDtFormId #purity\\.id option").filter(function() {
					   return this.text == obj.purity; 
				}).prop('selected', 'selected'); 
 				 
 			 }
 			 
 		 });
 		
 		

 		

 		
 	}
 	
 	
 	
 	
 	
	
	var colorFlag = true;
	var colorData = '';
	var vColorData ='';
 	function colorFormatterCombo(value,row,index){
 		
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
 	
 	
 	
 	function popColor(param,vidx){
 		
		 $('#quotMetalIdTbl').bootstrapTable('updateRow', {
	 			index : Number(vidx),
				row : {
					color : param.value,
				}
			});
 		
		 
		 
		 
		 var data = $('#quotMetalIdTbl').bootstrapTable('getData');
	 		
 		 $.each(data,function(idx,obj){
 	
 			 if(obj.mainMetal=='true'){
				$("#quotDtFormId #color\\.id option").filter(function() {
					   return this.text == obj.color; 
				}).prop('selected', 'selected'); 
 				 
 			 }
 			 
 		 });
 	
 		 
 		
 		
 		
 	}
	
 	
 	
	function qtyFormatterCombo(value,row,index){
		return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateQty(this,'+index+')"  />';
	}
	
	function updateQty(param,vidx){
 		
		$('#quotMetalIdTbl').bootstrapTable('updateRow', {
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
 		
 		$('#quotMetalIdTbl').bootstrapTable('updateRow', {
 			index : Number(vidx),
			row : {
				metalWt : param.value,
			}
		});
 		
 	}
	
	
	
  function mainMetalFormatterCombo(value) {
		
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';

	}
  
  
  $('#myQuotDtModal').on('shown.bs.modal', function () {
		 $(document.documentElement).css('overflow', 'hidden');
		 $('#myQuotDtModal').show().css('overflow', 'auto');
	});


	
	 
	 $('#myQuotDtModal').on('hidden.bs.modal', function () {
		 $(document.documentElement).css('overflow', 'scroll');
		  colorFlag = true;
		  purityFlag = true;
		  $('#quotDtModalDivId input[type="text"],textarea').val('');
		  $('#quotDtModalDivId input[type="number"]').val('0');
		  $('#quotDtModalDivId').find('select').val('');
		  $('#modalQuotDtId').val('');
		  modalModeFlag = "";
		  
		})

	 
	 
	 function updateMetalColor(){
		 
		 var ab=document.getElementById("color.id");
		 var colorNm=ab.options[ab.selectedIndex].text;
		 
		 
		
		
		var data =JSON.stringify($('#quotMetalIdTbl').bootstrapTable('getData'));
		 $.each(JSON.parse(data),function(idx,obj){
			 			 
			 $('#quotMetalIdTbl').bootstrapTable('updateRow', {
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
			 
			 
			
			
			var data =JSON.stringify($('#quotMetalIdTbl').bootstrapTable('getData'));
			 $.each(JSON.parse(data),function(idx,obj){
				 
				 
				 $('#quotMetalIdTbl').bootstrapTable('updateRow', {
						index : idx,
						row : {
							 purity : purityNm,
							

						}
					}); 
				 
				//employeeFormatter(value);
				 
				 
				 
			 });
			 

		}


function getQuotGrossWt(){

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


function getStampFromMaster(){
	
	var ab = document.getElementById("purity.id");
	var purityNm = ab.options[ab.selectedIndex].text;
			
	$.ajax({
		url:"/jewels/manufacturing/masters/orderDt/getStampFromMaster.html?partyId="+ $('#party\\.id').val()+"&purityId="+ $('#purity\\.id').val(),
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


		
	</script>
	
	
	
	