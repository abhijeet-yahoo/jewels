<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<div>
			<label class="col-lg-2 col-sm-2 text-left">
				<span style="font-size: 18px;">Email Concept</span>
			</label>
			<div class="text-right">
			
				<a
					class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/emailConcept.html">EmailConcept
					Listing</a>
					
				<input type="button" value="Save" style="font-size: 14px" class="btn btn-xs btn-warning" onClick="javascript:emailConceptSave();" /> 
			</div>
		</div>
	</div>
	
	
	
	
	
	<div role="tabpanel">
	
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>

			<li role="presentation"><a href="#uploadImages"
				aria-controls="profile" role="tab" data-toggle="tab">Upload Images</a></li>
		</ul>
	
	
		<!-- Tab panes -->
		<div class="tab-content">
			
			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addEmailConcept.jsp"%>
			</div>
			
			<div role="tabpanel" class="tab-pane" id="disableEmailUploadImages">
				<%@include file="emailConceptImages.jsp"%>
			</div>
		</div>
		
		
	</div>
	
	
	
	</div> <!-- ending the main panel -->