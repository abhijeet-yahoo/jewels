<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
	
	<div class="form-group">
		<form:form commandName="pdc" id="pdcSubmit"
				action="/jewels/manufacturing/transactions/pdc/add.html"
				cssClass="form-horizontal pdcForm">
				
				<div class="row">
				
				
				<div>&nbsp;</div>
				
					<label for="StyleNo" class="col-sm-2 control-label" >Style No : </label>
					<div class="col-sm-2">
						<form:input path="design.styleNo" cssClass="form-control" readonly="true"  />
						<form:errors path="design.styleNo" />
					</div>
							
							
					<label for="Purity" class="col-sm-1 control-label" >Purity : </label>
					<div class="col-sm-2">
						<form:select path="purity.id" class="form-control">
							<form:option value="" label=" Select Purity " />
							<form:options items="${purityMap}" />
						</form:select>
					</div>
				
					
					 <div class="form-group">
						<div class="col-lg-1">
							   <input type="submit" value="Save" class="btn btn-primary" />
							   <form:input type="hidden" path="id" />
						</div>
				    </div>
						
			
						
				</div>
		</form:form>
	</div>	




 <script type="text/javascript">
	
	
	$(document)
		.ready(
		function(e) {
			
			$(".pdcForm")
					.validate(
							{
								rules : {
							
								'purity.id' : {
									required : true,
									}
									
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

							
					
							});
	
			
			
			
		});
		
	
	
	</script>
	
	
	
	
	