
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>



	  <div class="form-group">
			<form:form commandName="costingJobDt" id="costingJobDtEnt"
			cssClass="form-horizontal costingJobDtForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
					<th class="col-xs-1" >GrossWt</th>
					<th class="col-xs-1" >Other</th>
					<th class="col-xs-10" ></th>
				</tr>
				</thead>
					<tbody>
					<tr>
						<td class="col-xs-1"><form:input path="grossWt" id="costJobDtGrossWt" cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="other"  cssClass="form-control" /></td>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:saveCostingJobDt()"/> 
						</td>
						<td class="col-xs-10"></td>
					</tr>
				</tbody>
			</table>
			
			</form:form>
		</div>







<script type="text/javascript">




</script>