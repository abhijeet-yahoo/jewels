<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalDesign.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalAutoStyleGeneration.jsp"%>

<div id="designMainDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<div>
			<label class="col-lg-2 col-sm-2 text-left">
				<span style="font-size: 18px;">Design</span>
			</label>
			<div class="text-right">
				
				<a id="designBiodataBtn" class="btn btn-xs btn-info" style="font-size: 14px" type="button"
						onClick="javascript:designBiodataRpt()" href="javascript:void(0)">Design BioData</a>
				
				
				<c:choose>
					<c:when test="${canAdd}">
						<a class="btn btn-xs btn-warning" style="font-size: 14px" type="button" 
							href="/jewels/manufacturing/masters/design/add.html">New Design</a>
					</c:when>
					<c:when test="${stylenoautogeneration}">
						<a class="btn btn-xs btn-warning" style="font-size: 14px" type="button" 
							onClick="javascript:styleAutoNo()"><span
							class="glyphicon glyphicon-plus"></span>New Design</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-xs btn-warning" style="font-size: 14px" type="button"
							onClick="javascript:displayMsg(event, this)" href="javascript:void(0)">New Design</a>
					</c:otherwise>
					
					
				</c:choose>
				<a class="btn btn-xs btn-info" id="copyDesignId" style="font-size: 14px" type="button" data-toggle="modal" 
				    data-target="#myDesignCopyModal" href="#" >Copy</a>	
				
				
				<a id="designListBtn" class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/masters/design.html">Design Listing</a>
				
				<c:choose>
					<c:when test="${canAdd || canEdit || stylenoautogeneration}">
						<input type="button" value="Save" style="font-size: 14px" class="btn btn-xs btn-success" onClick="javascript:designSave();" />
					</c:when>
					<c:otherwise>
						<input type="button" value="Save" style="font-size: 14px" class="btn btn-xs btn-success" onClick="javascript:displayMsg(event, this)" />
					</c:otherwise>
				</c:choose>
				
		
				 
			</div>
		</div>
	</div>

	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#mainScreen"
				aria-controls="home" role="tab" data-toggle="tab">Main Screen</a></li>
			<!-- <li role="presentation"><a href="#designComponent" id="designComponentId"
				aria-controls="profile" role="tab" data-toggle="tab">Design Component</a></li>
			<li role="presentation"><a href="#designPurityWeight"
				aria-controls="profile" role="tab" data-toggle="tab">Design Purity Weight</a></li> -->
				<li role="presentation"><a href="#designPurityWeight"
				aria-controls="profile" role="tab" data-toggle="tab">Design Purity Weight</a></li>
			<li role="presentation"><a href="#uploadImages"
				aria-controls="profile" role="tab" data-toggle="tab">Upload Images</a></li>
			<li role="presentation"><a href="#designCost" id="designCostId"
				aria-controls="profile" role="tab" data-toggle="tab">Costing</a></li>
		</ul>
	
		<!-- Tab panes -->
		<div class="tab-content">
			
		
				
			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="addDesign.jsp"%>
			</div>
					
			
			
		<%-- 	<div role="tabpanel" class="tab-pane" id="disableDesignComponent">
				<%@include file="designComponent.jsp"%>
			</div>
			<div role="tabpanel" class="tab-pane" id="disableDesignPurityWeight">
				<%@include file="designPurityWeight.jsp"%>
			</div> --%>
			
			<div role="tabpanel" class="tab-pane" id="disableDesignPurityWeight">
				<%@include file="designPurityWeight.jsp"%>
			</div>
			<div role="tabpanel" class="tab-pane" id="disableUploadImages">
				<%@include file="designImages.jsp"%>
			</div>
			<div role="tabpanel" class="tab-pane" id="disableDesignCost">
				<%@include file="designCost.jsp"%>
			</div>
		</div>
	</div>
</div>
</div>

<script type="text/javascript">

	$(document).ready(function(e) {
	
		
		$('#designComponentId').on('click', function() {
			popDesignComponent();
		});
		
		$('#designCostId').on('click', function() {
			popDesignId();
			
		});
	});

	function styleAutoNo(){
		$('#myAutoStyleGenerationModal').modal('show');
	}
	
</script>