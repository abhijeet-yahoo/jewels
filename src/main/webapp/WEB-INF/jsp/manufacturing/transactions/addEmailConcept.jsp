<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<c:if test="${success eq true}">
	<div class="col-xs-12">
		<div class="alert alert-success">Email Concept ${action} successfully!</div>
	</div>
</c:if>

	
 <div class="panel-body">

	<div class="form-group">
		<form:form commandName="emailConcept" 
			action="/jewels/manufacturing/transactions/emailConcept/add.html"
			cssClass="form-horizontal emailConceptForm">

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="center-block">
				<div class="col-xs-12">
					<div class="col-xs-9">
						<div class="panel panel-primary"> <!--  start of left panel -->
							
							<div class="panel-body" style="height:443px">

								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>
								<div class="form-group">
									<label for="emailConIdMax" class="col-sm-2 control-label">Id :</label>
									<div class="col-sm-4">
										<form:input path="emailConIdMax" cssClass="form-control" />
										<form:errors path="emailConIdMax" />
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">E - Concept Nm :</label>
									<div class="col-sm-4">
										<form:input path="name" cssClass="form-control" />
										<form:errors path="name" />
									</div>
									
									
									<label for="version" class="col-sm-2 control-label">Version :</label>
									<div class="col-sm-2">
										<form:input path="version" cssClass="form-control" onChange="javascript:getDesignImg();" />
										<form:errors path="version" />
									</div>
									
									<div class="col-sm-2"> </div>
									
									
									
									
								</div>
								<div class="form-group">
									<label for="eDate" class="col-sm-2 control-label">Date :</label>
									<div class="col-sm-4">
										<form:input path="eDate" cssClass="form-control" />
										<form:errors path="eDate" />
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Party
										:</label>
									<div class="col-sm-4">
										<form:select path="Party.id" class="form-control">
											<form:option value="" label=" Select Party " />
											<form:options items="${partyMap}" />
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Category
										:</label>
									<div class="col-sm-4">
											<form:select path="Category.id" class="form-control">
												<form:option value="" label=" Select Category " />
												<form:options items="${categoryMap}" />
											</form:select>
									</div>
								</div>
								<div class="form-group">
									<label for="remark" class="col-sm-2 control-label">Remark :</label>
									<div class="col-sm-10">
										<form:input path="remark" cssClass="form-control" />
										<form:errors path="remark" />
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>

								<div class="row">
									<div class="col-xs-12">

										<div class="col-lg-1">
											<label></label>
										</div>

										<div class="col-lg-1">
											<label></label>
										</div>

										<div class="col-lg-2">
											<div class="checkbox">
												<form:checkbox path="hold" value="2" />
												<strong>Hold</strong>
											</div>
										</div>
										<div class="col-lg-2">
											<div class="checkbox">
												<form:checkbox path="cancel" value="2" />
												<strong>Cancel</strong>
											</div>
										</div>
										<div class="col-lg-1">
											<div class="checkbox">
												<form:checkbox path="done" value="2" />
												<strong>Done</strong>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12">

										<div class="col-lg-1">
											<label></label>
										</div>
										<div class="col-lg-1">
											<label></label>
										</div>

										<div class="col-lg-2">
											<div class="checkbox">
												<form:checkbox path="modify" value="1" />
												<strong>Modify</strong>
											</div>
										</div>
										<div class="col-lg-2">
											<div class="checkbox">
												<form:checkbox path="versionFlg" value="1" />
												<strong>Version</strong>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>
								
							</div>
						</div>
						
						
					</div> <!--  end of  left panel -->

					<div class="col-xs-3">
						<div class="panel panel-primary">
							<div class="panel-body" style="height:443px">
								<div class="row center-block">
								<p style="height:10px"><strong>Email Concept Image</strong></p>
									<a id="dlOrdImg1Id" href="javascript:void(0);" data-lighter>
										<img id="ordImg1" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" style="height:170px;width: 100%"/>
									</a>
									<div style="height:8px"></div>
									<br>
									<div id="styImgId" style="display: none;">
										<p style="height:10px"><strong>Refrence Image</strong></p>
										<a id="dlOrdImg2Id" data-lighter>
											<img id="ordImg2" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" style="height:170px;width: 100%"/>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
	 			</div>
			</div>
			<!--  end of row -->
			
			
			<div class="form-group">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="col-sm-7">
					<input type="submit" value="Save" class="btn btn-primary" /> <a
						class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/emailConcept.html">EmailConcept Listing</a>
					<form:input type="hidden" path="id" />
					<input type="hidden" id="designImg" name="designImg" value="${designImg}" />
					<input type="hidden" id="emailImage" name="emailImage" 	value="${emailImg}" />
					<input type="hidden" id="cadImage" name="cadImage" 		value="${cadImg}" />
					<input type="hidden" id="roughImage" name="roughImage" 	value="${roughImg}" />
					<input type="hidden" id="designImage" name="designImage" 	value="${designImg}" />
				</div>
			</div>
		
		</form:form>
	</div> 
	
	
	</div> <!-- ending the panel body -->
	


<script type="text/javascript">
	
	$(document).ready(function(e) {
		
		$(".emailConceptForm")
			.validate(
			{
				rules : {
					'Category.id' : {
						required : true,
					},
					version : {
						required : true,
					},
					eDate : {
						required : true,
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
			});
			
			$("#eDate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
			
			
			
			if (window.location.href.indexOf('edit') != -1) {
				$('#ordImg1').attr('src', '/jewels/uploads/manufacturing/emailConcept/emailImage/'+$('#emailImage').val());
				$('#dlOrdImg1Id').attr('href', '/jewels/uploads/manufacturing/emailConcept/emailImage/'+$('#emailImage').val());
				
				getDesignImg();
			}
			
			$("#"+ document.querySelector("#disableEmailUploadImages").id).attr("id", "uploadImages");
			
			
	});


	
	
	function getDesignImg() {
		
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/design/image/version.html?styleNo="+$('#name').val()
					+"&version="+$('#version').val(),
			type : 'GET',
			success : function(data, textStatus, jqXHR) {
				
				
				$('#dlOrdImg2Id').attr('href', "");

				$("#styImgId").css('display','none'); 
				
				if (data.indexOf('blank') == -1) {
					$("#styImgId").css('display','block'); 
					$('#dlOrdImg2Id').attr('href', data);
				}

				$('#ordImg2').attr('src', data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}
	
	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">
<script src="/jewels/js/jquery/jquery-ui.min.js"> </script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />
<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>





