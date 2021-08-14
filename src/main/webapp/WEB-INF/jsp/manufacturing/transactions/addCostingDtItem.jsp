<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
				<form:form commandName="costingDtItem" id="costingDtItemEnt"
					cssClass="form-horizontal costingDtItemEntForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
					<th class="col-xs-2" >Per Pc Disc Amt</th>
					<th class="col-xs-1" ></th>
					<th class="col-xs-3" ></th>
				</tr>
				</thead>
					<tbody>
					<tr>
						<td class="col-xs-2"><form:input path="perPcDiscAmount"  id="perPcDiscAmount" placeholder="0.0" cssClass="form-control" /></td>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:popCostDtItemSave()"/> 
						</td>
						<td class="col-xs-3"></td>
					</tr>
				</tbody>
			</table>
			
			</form:form>
		 </div>