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


<div class="modal fade" id="myClientStamp" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Client Stamp</h4>
			</div>

			<div class="modal-body">
			
			<form:form commandName="clientStamp" id="clientStampFormId"
			action="/jewels/manufacturing/masters/clientStamp/add.html"
			cssClass="form-horizontal clientStampForm">
			
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="form-group">
						<label for="Purity" class="col-sm-2 control-label">Purity</label>
						<div class="col-sm-6">
							<form:select path="purity.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Purity" />
							<form:options items="${purityMap}" />
						</form:select>		
						</div>
					</div>
					
						<div class="form-group">
						<label for="stampNm" class="col-sm-2 control-label">Stamp</label>
						<div class="col-sm-6">
							<form:input path="stampNm" cssClass="form-control"
								autocomplete="off" />
							<form:errors path="stampNm" />
						</div>
					</div>

					<div class="form-group">
						<label for="fromCts" class="col-sm-2 control-label">From Cts</label>
						<div class="col-sm-6">
							<form:input path="fromCts" cssClass="form-control" autocomplete="off"/>
							<form:errors path="fromCts" />
						</div>
				</div>
				
				<div class="form-group">		
						<label for="toCts" class="col-sm-2 control-label">To Cts</label>
						<div class="col-sm-6">
							<form:input path="toCts" cssClass="form-control" autocomplete="off"/>
							<form:errors path="toCts" />
						</div>
					</div>


				

					<div>

						<form:hidden path="id" name="id"/>
						<input type="hidden" id="vPartyNm" name="vPartyNm" />
					</div>

				</form:form>	
	
	
			
			
			
			
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="javascript:clientStampSave()">Save</button>
				
			</div>


		</div>


	</div>

</div>
