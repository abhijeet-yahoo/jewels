<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="row">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" id="tabdiv">
		<span style="font-size: 18px;margin-right:62em;">Setting Charge</span>
	</div>
	
	
	<div class="col-sm-6">
	
	<form:form commandName="settingCharge" id="settingChargeFormId"
		action="/jewels/manufacturing/masters/settingCharge/add.html"
		cssClass="form-horizontal settingChargeForm">

		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Setting Charge added ${action}
				successfully!</div>
		</c:if>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
       </div>

	<div class="form-group">
			<label for="name" class="col-sm-4 control-label" id="partLabelId">Party <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="party.id" class="form-control">
						<form:option value="" label="--- Select Party ---" />
						<form:options items="${partyMap}" />
				</form:select>
			</div>
		</div>
		
		   <div class="form-group">
			<label for="name" class="col-sm-4 control-label">Metal <span style="color: red;">*</span></label>
			<div class="col-sm-8">
				<form:select path="metal.id" class="form-control">
					<form:option value="" label="--- Select Metal ---" />
					<form:options items="${metalMap}" />
				</form:select>
			</div>
	   </div>
		
		<div class="form-group">
			<label for="name" class="col-sm-4 control-label">Stone Type <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="stoneType.id" class="form-control">
						<form:option value="" label="--- Select Stone Type ---" />
						<form:options items="${stoneTypeMap}" />
				</form:select>
			</div>
	</div>
	
	<div class="form-group">
			<label for="name" class="col-sm-4 control-label">Shape <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="shape.id" class="form-control" onchange="javascript:getQuality();">
						<form:option value="" label="--- Select Shape ---" />
						<form:options items="${shapeMap}" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-4 control-label">Setting <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="setting.id" class="form-control">
					<form:option value="" label="--- Select Setting ---" />
					<form:options items="${settingMap}" />
				</form:select>
			</div>
	     </div>
	     
	     <div class="form-group">
			<label for="name" class="col-sm-4 control-label">Setting Type <span style="color: red;">*</span> </label>
			<div class="col-sm-8">
				<form:select path="settingType.id" class="form-control">
					<form:option value="" label="--- Select Setting Type ---" />
					<form:options items="${settingTypeMap}" />
				</form:select>
			</div>
	     </div>
	     	<div class="form-group">
			<label for="name" class="col-sm-4 control-label">From Weight <span style="color: red;">*</span></label>
			<div class="col-sm-8">
				<form:input path="fromWeight" cssClass="form-control" />
				<form:errors path="fromWeight" />
			</div>
	   </div>
	   <div class="form-group">
			<label for="name" class="col-sm-4 control-label">To Weight <span style="color: red;">*</span></label>
			<div class="col-sm-8">
				<form:input path="toWeight" cssClass="form-control" />
				     <form:errors path="toWeight" />
			</div>
	   </div>
	     
      <div class="form-group">
		<label for="name" class="col-sm-4 control-label">Rate </label>
		<div class="col-sm-8">
			<form:input path="rate" cssClass="form-control" />
			<form:errors path="rate" />
		</div>
     </div>
     
    
	   <c:if test="${canEdit && canDelete}">
		<div class="form-group">
				<label for="name" class="col-sm-4 control-label">De-Active </label>
				<div class="col-sm-1" style="padding-right: 80px;">
					<form:checkbox path="deactive" value="0" />
				</div>
			</div>
		</c:if>	


	
		<div class="form-group">
		 <label for="name" class="col-sm-4 control-label">Quality Wise Rate</label>
			 <div class="col-sm-1" style="padding-right: 80px;">
				<form:checkbox path="qualityWiseRate" value="0" />
			</div>
	 	</div>
	
		
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-12" style="padding-left: 40px;">
				<c:if test="${canAdd || canEdit}">
					<input type="submit" value="Save" class="btn btn-primary" />

              </c:if>
                  <a id="listingId" class="btn btn-info" style="font-size: 14px" type="button"
						href="/jewels/manufacturing/masters/settingCharge.html">Setting Charge Listing</a>
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="uniqueId" />
					<input type="hidden" id="jData" name="jData" />
					<input type="hidden" id="setQrPkDeleteIds" name="setQrPkDeleteIds" />
				</div>
				
			</div>
		
	</form:form>
	
	        <input type="hidden" id="viewOnly" name="viewOnly" value="${canView}">
	        <input type="hidden" id="editRight" name="editRight" value="${editRight}">
	        
	
	</div>
	
	<div class="col-sm-6">
	
		
		
		<div id="setQRDivId" style="display: none">
		
			<div id="toolbar">
				<input type="button" id="settingQRBtnId" name="settingQRBtnId" value="Add" onclick="javascript:popAddSettingQR();" class="btn btn-primary"/>
			</div>
		
			<table data-toggle="table" id="settingQRTblId"
				data-toolbar="#toolbar" data-pagination="false"
				data-side-pagination="server"
				data-search="false"
				data-striped="true"  data-height="320">
				<thead>
					<tr class="btn-primary">
						<th data-field="quality" data-align="left">Quality</th>
						<th data-field="qualityRate" data-align="left">Rate</th>
						<th data-field="action1" data-align="center">Delete</th>
					</tr>
				</thead>
			</table>
			
			<div id="setQRAddDivId" style="display:none">
				<div class="form-group">
				
				
					<form:form commandName="settingQualityRate"
						cssClass="form-horizontal settingChargeQualityForm">
					
						<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-warning">
										<th>Quality</th>
										<th>Rate</th>
										<th></th>
										
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<div id="qualityId">
												<form:select path="quality.id" class="form-control">
												<form:option value="" label="- Select Quality -" />
												<form:options items="${qualityMap}" />
											</form:select>
											</div>
										</td>
										<td><form:input path="qualityRate" cssClass="form-control"/></td>
										<td><input type="button" id="saveSetQRId" name="saveSetQRId" value="Save" class="btn btn-primary" onclick="javascript:saveSettingChargeQR()"></td>
									</tr>
								</tbody>
							</table>
						
						</form:form>
				
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
						$(".settingChargeForm")
								.validate(
										{
											rules : {
												'party.id' : {
													required : true
												},
												'metal.id' : {
													required : true,
												},
												'stoneType.id' : {
													required : true
												},
												'shape.id' : {
													required : true
												},
												'setting.id' : {
													required : true
												},
												'settingType.id' : {
													required : true
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
											messages : {
												
											},
											
										});

						$("input:text:visible:first").focus();
						
						if (window.location.href.indexOf('edit') != -1) {
							setTimeout(function(){
								getQuality();
							}, 500);
							
							
							if($('#qualityWiseRate1').is(":checked")){
								$('#setQRDivId').css('display','block');
							    $("#settingQRTblId").bootstrapTable('resetView');
							    popSettingQR();
							}
							
						}
						
						
					});
	
	
	
	
	$(document)
	.on(
		'submit',
		'form#settingChargeFormId',
		 function(e){
			
			
			if($("#fromWeight").val() > 0 && $("#toWeight").val() > 0 && $("#rate").val() > 0){
				var paramQRData = JSON.stringify($("#settingQRTblId").bootstrapTable('getData'));
				$('#jData').val(paramQRData);
				$('#setQrPkDeleteIds').val(setQrPkVal);
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
						
						if(data == "-1"){
							displayMsg(this, null, 'Duplicate Entry Found');	
						}else if(data.indexOf("add") != -1){
							window.location.href = data;
						}else if(data === '-5'){
							displayMsg(this, null, 'ToWeight Always Greater Than FromWeight');
						}else{
							window.location.href = data;
							
						}
						
						
					},
					
					error : function(data, textStatus, jqXHR){
						alert("ERROR");
					}
						
				})
				
				
			}else{
				displayMsg(this, null, 'From Wt or To Wt or Rate can Not be zero');
			}
			
		

			e.preventDefault();
	
	})
	
	
	
	
	function getQuality(){
		if(!!$('#shape\\.id').val()){
			popQuality($('#shape\\.id').val())
		}
	}

	
	$('#qualityWiseRate1').on('click', function(){
	  	if(this.checked) {
	      $('#setQRDivId').css('display','block');
	      $("#settingQRTblId").bootstrapTable('resetView');
	    }else{
	    	$('#setQRDivId').css('display','none');
	    }
	});
	
	
	
	function popSettingQR(){
		if($('#id').val()){
			$("#settingQRTblId").bootstrapTable('refresh',{
				url : "/jewels/manufacturing/masters/settingQualityRate/listing.html?settingChargeId="+$('#id').val()
			});	
		}
	}
	
	
	function popAddSettingQR(){
		$('#setQRAddDivId').css('display','block');
		$('#quality\\.id').val('')
		$('#qualityRate').val('0');
	}
	
	
	
	var qualityNm = "";
	var setQrPkVal = "";
	var setQrPkId = "";
	$('#settingQRTblId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			 qualityNm = jQuery.parseJSON(JSON.stringify(row)).quality;
			 setQrPkId = jQuery.parseJSON(JSON.stringify(row)).id;
			 $('#setQRAddDivId').css('display','none');
		});
	
	
	function saveSettingChargeQR(){
		
		//check dublicate
		
	
		if(!!$('#quality\\.id').val() && Number($('#qualityRate').val()) > 0){
			
			var status = false;
			
			var tempData = $("#settingQRTblId").bootstrapTable('getData');
			$.each(tempData,function(key,val){
				$.each(val,function(k,v){
					if(k === 'quality'){
						if(v === $('#quality\\.id :selected').text()){
							status = true;
						}
					}
				});
			});
			
			
			if(status){
				displayMsg(this,null,"Cannot add as duplicate entry found");
				return;
			}else{
				$("#settingQRTblId").bootstrapTable('insertRow', {
		            index: 0,
		            row: {
		               id:'0',
		               quality:$('#quality\\.id :selected').text(),
		               qualityRate:$('#qualityRate').val(),
		               action1:'<a onclick="javascript:settingQRDelete(event, 0);" href="javascript:void(0);" class="btn btn-xs btn-danger triggerRemove1"><span class="glyphicon glyphicon-trash"></span>&nbsp;Delete</a>'
		             }
		          });
				
				
				$('#quality\\.id').val('')
				$('#qualityRate').val('0');
			}
			
			
			
			
		}else{
			displayMsg(this,null,"Quality and Rate is compulsary");
		}
	}
	
	
	function settingQRDelete(e,id){
		setTimeout(function(){
			 $('#settingQRTblId').bootstrapTable('remove', {
			        field: 'quality',
			        values: [qualityNm]
			    });
			 
			 if(Number(setQrPkId) !==  0){
				 if(setQrPkVal === ""){
					 setQrPkVal = setQrPkId;
				 }else{
					 setQrPkVal = setQrPkVal+","+setQrPkId;
				 }
			 }
			 
		}, 500);
	}
	
	
	
	
</script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/common/design.js"></script>