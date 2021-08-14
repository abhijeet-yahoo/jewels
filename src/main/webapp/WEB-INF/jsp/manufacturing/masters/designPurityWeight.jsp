<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>

<c:set var="balance" value="20000.2309" />



<%-- <div class="bs-example">
<label class="control-label" for="purity">Total Wax Wt : ${design.waxWt}</label>
	<table class="table table-bordered">
		<thead>
			<tr class="btn-primary">
				<th class="text-right" width="10%">Serial No.</th>
				<th width="20%">Purity</th>
				<th width="20%">Wax Weight Conversion</th>
				<th width="20%">Purity Weight</th> 
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${purityWeightMap}" varStatus="count">
				<tr>
					<td class="text-right">${count.count}</td>
					<td><c:out value="${entry.key}" /></td>
					<td><c:out value="${entry.value}" /></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${entry.value * design.waxWt}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>



 --%><div class="bs-example">
					<table class="table table-bordered" id="designPurityTblId" data-toggle="table">
						<thead>
							<tr>
								<th data-field="purityNm" data-sortable="false">Purity</th>
								<th data-field="waxWtConv" data-sortable="false">Wax Weight Conversion</th>
								<th data-field="purityWt" data-align="left">Purity Weight</th>
								<th data-field="expNetWt" data-sortable="false">Exp. Net Wt</th>
								
						
							
							</tr>
						</thead>
					</table>
				</div>
