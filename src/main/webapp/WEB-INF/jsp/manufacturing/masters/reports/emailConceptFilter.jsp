<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100% ;height:500px">
	<div class="panel-heading">
		<div>
			<label class="col-lg-3 col-sm-3 text-left">
				<span style="font-size: 18px;">Email Concept Report</span>
			</label>
			<div class="text-right">
				<input type="button" value="Generate Report" id="genReportSubmitBtnId" class="btn btn-warning" onClick="javascript:generatereport() " />
				
				<!-- <input type="button" value="Save" style="font-size: 14px" class="btn btn-xs btn-warning" onClick="javascript:designSave();" /> --> 
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="row">
		<div class="col-sm-12">
				<div class="col-lg-1 col-sm-1">
					<label for="inputLabel4">Report&nbsp;Format&nbsp;:</label>
				</div>
				<div class="col-lg-3 col-sm-3" >
								<select id="reportFormat" class="form-control" style="background:#CEF87A">
									<option value="EmailConceptReport">Email Concept Report</option>
									<option value="EmailConceptSummary">Email Concept Summary</option>
									

								</select>
							</div>	
			</div>
			</div>
			<div class="row">&nbsp;</div>
		<div class="row">
		
			<div class="col-sm-12">
				<div class="col-lg-1 col-sm-1">
					<label for="inputLabel4">From&nbsp;Date&nbsp;:</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<input type="text" class="form-control" name="startDate"
						id="startDate" />
				</div>

			</div>
		</div>
		<div class="col-sm-12">&nbsp;</div>
		<div class="row">
			<div class="col-sm-12">

				<div class="col-lg-1 col-sm-1">
					<label for="inputLabel4">To&nbsp;Date&nbsp;:</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<input type="text" class="form-control" name="endDate" id="endDate" />
				</div>
			</div>
		</div>
		
		<div class="col-sm-12">
				<div class="radio">
						<label><input type="radio" name="choiceRd" id="choiceHold" value="Hold"><strong>Hold</strong></label> &nbsp;&nbsp;&nbsp;
						<label><input type="radio" name="choiceRd" id="choiceCancel" value="Cancel"><strong>Cancel</strong></label> &nbsp;&nbsp;&nbsp;
						<label><input type="radio" name="choiceRd" id="choiceModify" value="Modify"><strong>Modify</strong></label>&nbsp;&nbsp;&nbsp;
						<label><input type="radio" name="choiceRd" id="choiceVersion" value="Version"><strong>Version</strong></label>&nbsp;&nbsp;&nbsp;
						<label><input type="radio" name="choiceRd" id="choiceDone" value="Done"><strong>Done</strong></label>
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
								<input type="hidden" id="rptFormat" name="rptFormat" /> 
								
							</div>
						</div>
				</form:form>
			</div>



	</div>
	<div class="row" style="height: 175px">
	&nbsp;
	
	</div>
	<div class="col-sm-2">
						
						<input type="submit" value="Generate Report" id="genReportSubmitBtnId" class="btn btn-primary" onClick="javascript:generatereport()"/> 
						
						</div>



</div>
<script type="text/javascript">

$(document).ready(function(e){
	
	$("#startDate").datepicker({
		dateFormat : 'dd/mm/yy'
	});
	
	$("#endDate").datepicker({
		dateFormat : 'dd/mm/yy'
	});
	
	
	
})


function generatereport(){
	
	$('#rptFormat').val($('#reportFormat').val());
	
	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
	var rdChoice = $("input[type='radio'][name='choiceRd']:checked");
	$.ajax({
		url:"/jewels/manufacturing/masters/reports/generateEmailConceptReport.html?pStartDate="+$('#startDate').val()+"&pEndDate="+$('#endDate').val()+
				"&pChoice="+rdChoice.val()+"&pRptFormat="+$('#rptFormat').val(),
		type: "POST",
		success:function(data){
			$.unblockUI();
			if(data === "-1" || data === "-5"){
				displayMsg(this,null,'Error Occoured , Contact Admin');
				
			}else{
				/* window.open('/jewels/manufacturing/masters/reports/download/report.html?timeValCommonPdf='+data+'','_blank'); */
				$('#timeValCommonPdf').val(data);
				$("#genReportss").trigger('click');
			}
		}
	})
}

</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

