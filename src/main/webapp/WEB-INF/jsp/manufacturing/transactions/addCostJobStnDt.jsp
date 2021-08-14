
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<div class="form-group">
			<form:form commandName="costJobStnDt" id="costJobStnDtEnt"
			cssClass="form-horizontal costJobStnDtForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
					<th class="col-xs-2" >Setting</th>
					<th class="col-xs-2" >Setting Type</th>
					<th class="col-xs-1" >StoneRate</th>
					<th class="col-xs-1" >HandlingRate</th>
					<th class="col-xs-1" >SettingRate</th>
					<th class="col-xs-5" ></th>
				</tr>
				</thead>
					<tbody>
					<tr>
						 <td class="col-xs-2">
							<form:select path="setting.id" class="form-control" onchange="javascript:popStoneRateFromMaster()">
								<form:option value="" label="- Select Setting -" />
								<form:options items="${settingMap}" />
							</form:select>
						</td>
						<td class="col-xs-2">
							<form:select path="settingType.id" class="form-control" onchange="javascript:popStoneRateFromMaster()">
								<form:option value="" label="- Select SetType -" />
								<form:options items="${settingTypeMap}" />
							</form:select>
						</td>
						<td class="col-xs-1"><form:input path="stnRate"  cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="handlingRate"  cssClass="form-control" /></td>
						<td class="col-xs-1"><form:input path="setRate"  cssClass="form-control" /></td>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:saveCostJobStnDt()" />
						</td>
						<td class="col-xs-4"></td>
					</tr>
				</tbody>
			</table>
			
			</form:form>
	 	</div>
	 	
	 	
<script type="text/javascript">
	 

	 
</script>