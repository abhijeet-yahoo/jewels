<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<style>
.ui-autocomplete{
z-index:1151 !important; 
}
</style>


<div class="modal fade" id="myHandlingMasterFl" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Handling Rate</h4>
			</div>

			<div class="modal-body">
			
			<form:form commandName="handlingMasterFl" id="handlingMasterFlFormId"
			action="/jewels/manufacturing/masters/handlingMasterFl/add.html"
			cssClass="form-horizontal handlingMasterFlForm">
			
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					

					<div class="form-group">
						<label for="fromDiaRate" class="col-sm-3 control-label">From Dia Rate</label>
						<div class="col-sm-6">
							<form:input path="fromDiaRate" cssClass="form-control" autocomplete="off"/>
							<form:errors path="fromDiaRate" />
						</div>
				</div>
				
				<div class="form-group">		
						<label for="toDiaRate" class="col-sm-3 control-label">To Dia Rate</label>
						<div class="col-sm-6">
							<form:input path="toDiaRate" cssClass="form-control" autocomplete="off"/>
							<form:errors path="toDiaRate" />
						</div>
					</div>
					
						<div class="form-group">
						<label for="rate" class="col-sm-3 control-label">Rate</label>
						<div class="col-sm-6">
							<form:input path="rate" cssClass="form-control"
								autocomplete="off" />
							<form:errors path="rate" />
						</div>
					</div>

				<div class="form-group">
						<label for="percentage" class="col-sm-3 control-label">Percentage</label>
						<div class="col-sm-6">
							<form:input path="percentage" cssClass="form-control"
								autocomplete="off" />
							<form:errors path="percentage" />
						</div>
					</div>
				

					<div>

						<form:hidden path="id" name="id"/>
						<input type="hidden" id="vPartyNm" name="vPartyNm" />
					</div>

				</form:form>	
	
	
			
			
			
			
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="javascript:handlingRateSave()">Save</button>
				
			</div>


		</div>


	</div>

</div>
