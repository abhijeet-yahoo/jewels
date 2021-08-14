
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="costLabDtItem" id="costLabDtItemEnt"
		action="/jewels/manufacturing/transactions/costLabDtItem/add.html"
		cssClass="form-horizontal costLabDtItemEntForm">

	<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-primary" class="panel-heading">
					<th>Metal</th>
					<th>Labour</th>
					<th>Rate</th>
					<th>Per Pcs</th>
					<th>Per Gram</th>
					<th>Percent</th>
					<th>Per Carat</th>
					<th>Lock</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<td>
							 <form:select path="metal.id" class="form-control"  onchange="javascript:popLabRateFromMasterItem()">
										<form:option value="" label="- Select Metal -" />
									<form:options items="${metalMap}" />
							</form:select>
						</td>
					<td>
						<form:select path="labourType.id" class="form-control" onchange="javascript:popLabRateFromMasterItem()">
							<form:option value="" label="- Select LabourType -" />
							<form:options items="${labourTypeMap}" />
						</form:select>
					</td>
					<td><form:input path="labourRate"  cssClass="form-control" onchange="javascript:lockLabRecord()"/></td>
					<td><form:checkbox path="perPcRate"  /></td>
					<td><form:checkbox path="perGramRate"  /></td>
					<td><form:checkbox path="percentage"  /></td>
					<td><form:checkbox path="perCaratRate" /></td>
					<td><form:checkbox path="rLock"  /></td>

				</tr>

				<tr>
					<td colspan="12">
								<input type="submit" value="Save" class="btn btn-primary" /> 
								<form:input type="hidden" path="id" id="costLabDtItemPk" />
								<input type="hidden" id="forLabCostMtItemId" name="forLabCostMtItemId" />
								<input type="hidden" id="forLabCostDtItemId" name="forLabCostDtItemId" /> 
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>
</div>
<script type="text/javascript">

$(document).ready(
		function(e){
			
			$(".costLabDtItemEntForm")
					.validate(
							{	
							  rules : {
									'labourType.id' : {
										required : true,
									},
									
									labourRateItem : {
										required : true,
										greaterThan : "0.0",
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
									labourRateItem : {
										greaterThan : "This field is required"
									}
								},
								

							});


		});
		
		
		
function popLabRateFromMasterItem(){
	
	
	if(!!$('#costLabDtItemEnt #metal\\.id').val() && !!$('#costLabDtItemEnt #labourType\\.id').val()){

				
		  $.ajax({
			  url:"/jewels/manufacturing/transactions/costLabDtItem/labRateFromMaster.html?metalId="+$('#costLabDtItemEnt #metal\\.id').val()
					  +"&labourId="+$('#costLabDtItemEnt #labourType\\.id').val()
					   +"&costLabDtItemId="+$('#costLabDtItemEnt #costLabDtItemPk').val()
					   +"&costDtItemId="+$('#costingDtItemPKId').val(),
			   type:"GET",
				datatype:"JSON",
			  success : function(data) {
				  
				  
				  $( '#costLabDtItemEnt #perCaratRate1').prop("checked", false );	
					
				  $( '#costLabDtItemEnt #perPcRate1').prop("checked", false );
				  $( '#costLabDtItemEnt #perGramRate1').prop("checked", false );
				  $( '#costLabDtItemEnt #percentage1').prop("checked", false );
				  			  
				  $.each(JSON.parse(data), function(key,value){
				
						
						if(key=='labourRate'){
							 $('#costLabDtItemEnt #labourRate').val(value);
						}
						
						if(key=='perPcRate'){
							if(value ==true){
								
								$( '#costLabDtItemEnt #perPcRate1').prop( "checked", true );	
							}
							 
							
						}
						
						if(key=='perGramRate'){
							if(value==true){
								$( '#costLabDtItemEnt #perGramRate1').prop( "checked", true );	
							}
							
							 
						}
						
						if(key=='percentage'){
							if(value==true){
								$( '#costLabDtItemEnt #percentage1').prop( "checked", true );	
							}
							
							
						}
						
						if(key=='perCaratRate'){
							if(value==true){
								$( '#costLabDtItemEnt #perCaratRate1').prop( "checked", true );	
							}
							 
						}
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
						
				    });
				
			 }
			  
		  });
	
	}
	
	
	
}

function lockLabRecord(){
	
	$( '#costLabDtItemEnt #rLock1').prop( "checked", true );	
}



</script>