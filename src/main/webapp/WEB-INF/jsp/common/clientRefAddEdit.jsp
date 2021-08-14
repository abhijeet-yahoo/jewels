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

<div class="modal fade" id="myClientRef" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Client Ref.</h4>
			</div>

			<div class="modal-body">
			
			<form:form commandName="clientRef" id="clientRefFormId"
			action="/jewels/manufacturing/masters/clientRef/add.html"
			cssClass="form-horizontal clientRefForm">
			
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>

					<div class="form-group">
						<label for="styleNo" class="col-sm-2 control-label">Style
							No.</label>
						<div class="col-sm-8">
							<form:input path="design.mainStyleNo" cssClass="form-control"
								autocomplete="off" />
							<form:errors path="design.mainStyleNo" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="purityNm" class="col-sm-2 control-label">Purity</label>
						<div class="col-sm-8">
							<form:select path="purity.id" class="form-control">
								<form:option value="" label="- Select Purity -" />
								<form:options items="${purityMap}" />
							</form:select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="finishWt" class="col-sm-2 control-label">Finish
							Wt.</label>
						<div class="col-sm-8">
							<form:input path="finishWt" type="number" cssClass="form-control" autocomplete="off"/>
							<form:errors path="finishWt" />
						</div>
					</div>

					<div class="form-group">
						<label for="caratWt" class="col-sm-2 control-label">Carat
							Wt.</label>
						<div class="col-sm-8">
							<form:input path="caratWt" type="number" cssClass="form-control" autocomplete="off"/>
							<form:errors path="caratWt" />
						</div>
					</div>

					<div class="form-group">
						<label for="y" class="col-sm-2 control-label">Y</label>
						<div class="col-sm-4">
							<form:input path="y" cssClass="form-control" autocomplete="off"/>
							<form:errors path="y" />
						</div>
						<label for="w" class="col-sm-2 control-label">W</label>
						<div class="col-sm-4">
							<form:input path="w" cssClass="form-control" autocomplete="off"/>
							<form:errors path="w" />
						</div>
					</div>


					<div class="form-group">
						<label for="p" class="col-sm-2 control-label">P</label>
						<div class="col-sm-4">
							<form:input path="p" cssClass="form-control" autocomplete="off"/>
							<form:errors path="p" />
						</div>
						<label for="wy" class="col-sm-2 control-label">WY</label>
						<div class="col-sm-4">
							<form:input path="wy" cssClass="form-control" autocomplete="off"/>
							<form:errors path="wy" />
						</div>
					</div>


					<div class="form-group">
						<label for="wp" class="col-sm-2 control-label">WP</label>
						<div class="col-sm-4">
							<form:input path="wp" cssClass="form-control" autocomplete="off"/>
							<form:errors path="wp" />
						</div>
						<label for="yw" class="col-sm-2 control-label">YW</label>
						<div class="col-sm-4">
							<form:input path="yw" cssClass="form-control" autocomplete="off"/>
							<form:errors path="yw" />
						</div>
					</div>

					<div class="form-group">
						<label for="yp" class="col-sm-2 control-label">YP</label>
						<div class="col-sm-4">
							<form:input path="yp" cssClass="form-control" autocomplete="off"/>
							<form:errors path="yp" />
						</div>
						<label for="py" class="col-sm-2 control-label">PY</label>
						<div class="col-sm-4">
							<form:input path="py" cssClass="form-control" autocomplete="off"/>
							<form:errors path="py" />
						</div>
					</div>

					<div class="form-group">
						<label for="pw" class="col-sm-2 control-label">PW</label>
						<div class="col-sm-4">
							<form:input path="pw" cssClass="form-control" autocomplete="off"/>
							<form:errors path="pw" />
						</div>
						<label for="tt" class="col-sm-2 control-label">TT</label>
						<div class="col-sm-4">
							<form:input path="tt" cssClass="form-control" autocomplete="off"/>
							<form:errors path="tt" />
						</div>
					</div>

					<div>

						<form:hidden path="id" name="id"/>
						<input type="hidden" id="vPartyNm" name="vPartyNm" />
					</div>

				</form:form>	
	
	
			
			
			
			
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="javascript:clientRefSave()">Save</button>
				
			</div>


		</div>


	</div>

</div>
