<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>






<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>


<div class="modal fade" id="multiComponentDeductionModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>

				<h4 class="modal-title" id="myModalLabel">Multicomponent Deduction</h4>
			</div>



			<div class="modal-body">


				<form:form commandName="compTran" id="multiComponentDeductionId"
				action="/jewels/manufacturing/transactions/multiComponentDeduction/add.html"
					cssClass="multiComponentFormT">


					<div class="row" id="xyz">
					
		
						<div class="form-group  col-sm-4">

							<label class="control-label" for="location">Location</label>
							<div id="deptDivId">
								<form:select path="department.name" class="form-control" >
									<form:option value="" label=" Select Location " />
									<form:options items="${deptMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group  col-sm-4">

							<label class="control-label" for="comp">Component</label>
							<div id="compDivId">
								<form:select path="component.id" class="form-control">
									<form:option value="" label=" Select Component " />
									<form:options items="${componentMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group  col-sm-4">

							<label class="control-label" for="purity">Purity</label>
							<div id="purityDivId">
								<form:select path="purity.id" class="form-control">
									<form:option value="" label=" Select Purity " />
									<form:options items="${purityMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group  col-sm-4">

							<label class="control-label" for="part">Color</label>
							<div id="colorDivId">
								<form:select path="color.id" class="form-control">
									<form:option value="" label=" Select Color " />
									<form:options items="${colorMap}" />
								</form:select>


							</div>


						</div>

						<div class="form-group col-sm-4">

							<label class="control-label" for="metalWt">Weight</label>
							<form:input path="metalWt" cssClass="form-control"
								autocomplete="off" />
							<form:errors path="metalWt" />


						</div>

						<div class="form-group col-sm-4">

							<label class="control-label" for="qty">Qty</label>
							<form:input path="pcs"  cssClass="form-control"
								autocomplete="off" />
							<form:errors path="pcs" />


						</div>
					</div>

					<div class="form-group">
						<div>
							<input type="submit" value="Save" class="btn btn-primary"
								style="width: 100px;" id="multiCompSaveBtn"> 
								<input type="hidden" id=vvBagNo name="vvBagNo" />
								<input type="hidden" id="vFindingFlg" name="vFindingFlg" />
						</div>


					</div>
				</form:form>
			</div>


			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>


			</div>


		</div>


	</div>
</div>


