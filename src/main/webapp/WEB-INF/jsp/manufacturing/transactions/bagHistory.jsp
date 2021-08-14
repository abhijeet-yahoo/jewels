<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div>

	<div>
		<table id="bagHistoryTabId" data-toggle="table"
			data-toolbar="#toolbar" data-pagination="false"
			data-side-pagination="server"
			data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350"
			data-striped="true">
			<thead>
				<tr class="btn-primary">
					<th data-field="orderNo" data-align="left" data-sortable="true">Order No</th>
					<th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th>
					<th data-field="date" data-align="left" data-sortable="true">Date</th>
					<th data-field="department" data-align="left" data-sortable="true">Department</th>
					<th data-field="pcs" data-sortable="true">PCS</th>
					<th data-field="recievewt" data-sortable="true">RecieveWt</th>
					<th data-field="issuewt" data-sortable="true">IssueWt</th>
					<th data-field="loss" data-sortable="true">Loss</th>
				</tr>
			</thead>
		</table>
	</div>
</div>




<script type="text/javascript">
	$(document)
			.ready(
					function() {

						if (window.location.href.indexOf("fromBagSearch") != -1) {

							var tempUrl = window.location.href;
							var tempId = tempUrl.substring(tempUrl.indexOf("bagHistory/") + 11, tempUrl.indexOf(".html"));
							popBagHistoryTab(tempId);
						}

					})

	function popBagHistoryTab(val) {
	
		$("#bagHistoryTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/bagHistory/listing.html?bagId="+val,

						});

	}
</script>









