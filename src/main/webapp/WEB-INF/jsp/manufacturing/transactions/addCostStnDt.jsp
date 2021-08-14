
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<div class="form-group">
			<form:form commandName="costStnDt" id="costStnDtEnt"
			cssClass="form-horizontal costStnDtEntForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
				<tr  class="panel-heading">
				<c:if test="${costingType eq 'merge'}">
					<th class="col-xs-2" >Setting</th>
					<th class="col-xs-2" >Setting Type</th>
					<th class="col-xs-10" ></th>
				</c:if>
				
				<c:if test="${costingType ne 'merge'}">
					<th class="col-xs-2" >Setting</th>
					<th class="col-xs-2" >Setting Type</th>
					<th class="col-xs-1" >StoneRate</th>
					<th class="col-xs-1" >HandlingRate</th>
					<th class="col-xs-1" >SettingRate</th>
					<th class="col-xs-1" >Center Stone</th>
					<th class="col-xs-4" ></th>
				</c:if>
					
					
				</tr>
				</thead>
					<tbody>
					<tr>
					
					<c:if test="${costingType eq 'merge'}">
						
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
						
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:saveCostStnDt()"/>
						</td>
						<td class="col-xs-4"></td>
					
					</c:if>
					
					<c:if test="${costingType ne 'merge'}">
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
						<td class="col-xs-1"><form:checkbox id ="centerStoneId" path="centerStone" width="3cm"></form:checkbox>
						<td class="col-xs-1">
							<input type="button" value="Save" class="btn btn-primary" onclick="javascript:saveCostStnDt()"/>
						</td>
						<td class="col-xs-5"></td>
					</c:if>	
					</tr>
				</tbody>
			</table>
			
			</form:form>
	 	</div>
	 	
<script type="text/javascript">
	$(document)
			.ready(
					function(e) {					
						
						
					});
			</script>



	 	