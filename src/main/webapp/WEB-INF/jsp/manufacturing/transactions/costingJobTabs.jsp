<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">

	 <div class="panel-heading" style="text-align: center;"> 
	 
	 <div>
		<label class="col-lg-2 col-sm-2 text-left">
			<span style="font-size: 18px;">Costing Job</span>
		</label>
		<div class="text-right">
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/costingJobMt.html">Job Costing
					Listing</a>
				<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-warning" id="costJobMtSubMitBtn" onClick="javascript:costingJobSave();"/> 
			</div>
		
		
		
	 </div>
	 	
	</div> 
	
	
	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
			
			<li role="presentation"><a href="#changeLabourJob" id="cJobLabour"
				aria-controls="profile" role="tab" data-toggle="tab">Change Handling</a></li>
				
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
 			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addCostingJobMt.jsp"%>
			</div>
 			<div role="tabpanel" class="tab-pane" id="disableChangeLabourJob">
				<%@include file="changeLabourJob.jsp"%>
			</div>	
			
		</div>
	</div>
	

	
	</div> <!-- ending the main panel -->