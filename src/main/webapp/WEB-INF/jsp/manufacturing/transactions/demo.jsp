

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>




<div class="form-group">
			<div class="form-group">
				<form:form commandName="demo" id="demo"
					action="/jewels/manufacturing/transactions/demo/add.html"
					cssClass="form-horizontal invoiceWoSoDetailsForm">
					<!-- this one --------------------------------------------------- -->

					<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="panel-heading">
								<th>StoneType</th>
								<th>Shape</th>
								<th>Quality</th>
								<th>Size</th>
								<th>Sieve</th>
								<th>SizeGroup</th>
								<th>Stone</th>
								<th>Carat</th>
								<th>Rate</th>
								<th>Amount</th>
								<!-- but on next row  <th>Remark</th>  -->
							</tr>
						</thead>
						<tbody>
							<tr>

								<td>
									<div id="stoneTypeId">
										<form:select path="stoneType.id" class="form-control">
											<form:option value="" label="- Select StoneType -" />
											<form:options items="${stoneTypeMap}" />
										</form:select>
									</div>
								</td>

								<td><form:select path="shape.id" class="form-control"
										onChange="javascript:popQuality(this.value);popStoneChart(this.value);popSizeGroup(this.value)">
										<form:option value="" label="- Select Shape -" />
										<form:options items="${shapeMap}" />
									</form:select></td>
								<td>
									<div id="qualityId">
										<form:select path="quality.id" class="form-control">
											<form:option value="" label="- Select Quality -" />
											<form:options items="${qualityMap}" />
										</form:select>
									</div>
								</td>
								<td>
									<div id="sizeId">
									<form:select path="size" class="form-control" 
									onChange="javascript:getSizeMMDetails">
										<form:option value="" label="- Select Size -" />
										<form:options items="${stoneChartMap}" />
									</form:select>
								</div>
							</td>
								<td><input type="text" id="vSieve" name="vSieve" class="form-control" disabled="true" /></td>
								<td><input type="text" id="vSizeGroupStr" name="vSizeGroupStr" class="form-control" disabled="true" /></td>
								<td><form:input path="stone" cssClass="form-control" /></td>
								<td><form:input path="carat" cssClass="form-control" /></td>
								<td><form:input path="rate" cssClass="form-control" /></td>
								<td><form:input path="amount" cssClass="form-control" /></td>
								
							</tr>
				
							<tr>
								<td colspan="10" >
									<input type="submit" value="Save" class="btn btn-primary"  /> 
									<form:input type="hidden" path="id" />
									<form:input type="hidden" path="sieve" />
									<form:input type="hidden" path="sizeGroupStr" />
									<input type="hidden" id="pInvNo" name="pInvNo" />
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>