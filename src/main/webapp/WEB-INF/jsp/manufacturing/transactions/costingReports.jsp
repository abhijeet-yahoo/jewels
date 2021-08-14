<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ page import="net.sf.jasperreports.view.*"%>


<div class="panel-body">

	
	<form:form commandName="costingMt" id="costRep"
			action="/jewels/manufacturing/transactions/costing/reports.html"
			cssClass="form-horizontal orderFilter">
			
			
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
			
			
				<div class="row">
					<div class="col-xs-12">
					
					<div class="col-lg-2 col-sm-2">
						<input type="submit" value="cost" id="costBtn1" name="costBtn1" class="btn btn-primary" style="width: 2cm"/>
					</div>
					
					<div class="col-lg-2 col-sm-2"></div>
					
					<div class="col-lg-2 col-sm-2">
						<input type="submit" value="costDemo" id="costBtn1" name="costBtn1" class="btn btn-primary" style="width: 2.2cm"/>
					</div>
					
					
					
					</div>
						<input type="hidden" id="invCost" name="invCost" />
						<input type="hidden" id="btnName" name="btnName" />
				</div>
			
			
			
	</form:form>
	
	
	
	
	
	<form:form 
		action="/jewels/manufacturing/transactions/download/costingReports.html"
			cssClass="form-horizontal orderFilter">
			 <div style="display: none">
			 	<div class="form-group">
					<div class="col-lg-12 col-sm-12" style="text-align: center">
						<input type="submit" value="Generate Report" class="btn btn-primary" id="genCostingReport"/>
					</div>
				</div>
			</div>
	</form:form>
	

</div> <!-- ending the panel body -->



<script type="text/javascript">


	$(document).ready(function(){
		
		$("#invCost").val($("#invNo").val());
		
	})






	$(document)
	.on(
			'submit',
			'form#costRep',
			function(e){
		
				var vBtn = $(this).find("input[type=submit]:focus").val();
				$("#btnName").val(vBtn);
		
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
		
		
					$
					.ajax({
						url : formURL,
						type : "POST",
						data : postData,
			
						success : function(data, textStatus, jqXHR) {
							
							//alert(data);
							
					 	if(data == -1){
							$("#genCostingReport").trigger('click');
						 }else{
							 alert("Error , Contact admin");
						 } 
						
							
							
						},
						error : function(jqXHR, textStatus,
								errorThrown) {
			
						}
			
					});
			
			e.preventDefault();
					
	});
	



</script>



