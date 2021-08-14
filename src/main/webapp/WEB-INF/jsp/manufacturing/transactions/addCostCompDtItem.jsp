<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div class="form-group" id="costCompItemDivId">

	<form:form commandName="costCompDtItem" id="costCompDtItemEnt"
							action="/jewels/manufacturing/transactions/costCompDtItem/add.html"
							cssClass="form-horizontal costCompDtItemEntForm">
							
							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Component</th>
										<th>Purity</th>
										<th>Color</th>
										<th>MetalWt</th>  
										<th>Rate</th>
										<th>Qty</th>
										<th>Per Gram</th>
										
									</tr>
								</thead>
								  <tbody>
									<tr>
									    <td>
									    	<form:select path="component.id"  class="form-control" onchange="javascript:popCompRateFromMasterItem()">
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="purity.id"  class="form-control" onchange="javascript:popCompRateFromMasterItem()">
												<form:option value="" label="- Select Purity -" />
												<form:options items="${purityMap}" />
											</form:select>
										</td>
										<td>
											<form:select path="color.id"  class="form-control" onchange="javascript:popCompRateFromMasterItem()">
												<form:option value="" label="- Select Color -" />
												<form:options items="${colorMap}" />
											</form:select>
										</td>
										<td><form:input path="metalWt" id="metalWt" cssClass="form-control" /></td>
										<td><form:input path="compRate" id="compRate"  cssClass="form-control" /></td>
										<td><form:input path="compPcs" id="compPcs"  cssClass="form-control" /></td>
										<td><form:checkbox path="perGramRate"  /></td>
										
								
								   </tr>
								   	
								   	<tr>
								   		<td colspan="12">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" id="costCompDtItemPk" />
											<input type="hidden" id="forCompCostMtItemId" name="forCompCostMtItemId" />
											<input type="hidden" id="forCompCostDtItemId" name="forCompCostDtItemId" /> 
										</td>
								   	
								   	</tr>
								</tbody>
						    </table>
			
						</form:form>
					</div>
					
					
					
<script type="text/javascript">

$(document).ready(
		function(e){
			
			$(".costCompDtItemEntForm")
					.validate(
							{	
								 rules : {
										'component.id' : {
											required : true,
										},
										'purity.id' : {
											required : true,
										},
										'color.id' : {
											required : true,
										},
										compPcs : {
											required : true,
											greaterThan : "0,0",
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
									compPcs : {
										greaterThan : "This field is required"
									}
								},
								

							});


		});
		
		
function popCompRateFromMasterItem(){
	
	
	if(!!$('#costCompItemDivId #component\\.id').val() && !! $('#costCompItemDivId #purity\\.id').val() && !! $('#costCompItemDivId #color\\.id').val()){
		
		  $.ajax({
			  url:"/jewels/manufacturing/transactions/costCompDtItem/compRateFromMaster.html?componentId="+$('#costCompItemDivId #component\\.id').val()
					  +"&purityId="+$('#costCompItemDivId #purity\\.id').val()
					  +"&colorId="+$('#costCompItemDivId #color\\.id').val()
					  +"&costCompDtItemId="+$('#costCompDtItemPk').val()
					  +"&costMtId="+'${costMtId}',
			  type:'GET',
			  datatype:"JSON",
			  success : function(data) {
				  
				  $( '#costCompItemDivId #perGramRate1').prop("checked", false );
				  $.each(JSON.parse(data), function(key,value){
						
						
						if(key=='compRate'){
							 $('#costCompItemDivId #compRate').val(value);
						}
						
						
						
						if(key=='perGramRate'){
							if(value==true){
								$( '#costCompItemDivId #perGramRate1').prop( "checked", true );	
							}
							
							 
						}
						
						
				    });
					 
				
			 }
			  
		  });
	
	}
	
	
	
}



</script>					