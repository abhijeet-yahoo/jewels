<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="form-group">
	<div class="container-fluid">
		<div>
			<table id="dbDetailsId" data-toggle="table"
				data-toolbar="#toolbarOSDt" data-side-pagination="server"
				data-click-to-select="true"
				data-page-list="[5, 10, 20, 50, 100, 200]" data-height="280">
				<thead>
					<tr class="btn-primary">
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="invNo" data-align="left" data-sortable="true">Invoice No</th>
						<th data-field="bagName" data-align="left" data-sortable="true">Bag</th>
						<th data-field="qty" data-sortable="true">Pieces</th>
						<th data-field="shape" data-sortable="true">Shape</th>
						<th data-field="quality" data-sortable="true">Quality</th>
						<th data-field="mm" data-sortable="true">Size</th>
						<th data-field="sieve" data-sortable="true">Sieve</th>
						<th data-field="sizeGroup" data-sortable="true">Size Group</th>
						<th data-field="stones" data-sortable="true">Stones</th>
						<th data-field="carat" data-sortable="true">Carat</th>
						<th data-field="rate" data-sortable="true">Rate</th>
						<th data-field="setting" data-sortable="true">Setting</th>
						<th data-field="setType" data-sortable="true">Setting Type</th>
						<th data-field="bagSrno" data-sortable="true">Bag Srno</th>
						<th data-field="transferred" data-sortable="true">Transferred</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<div id="adjTblId" class="form-group">
	<table class="line-items editable table table-bordered">
		<tbody>
			<tr>
				<td colspan="12">
<!-- 
&nbsp;&nbsp;
<a onClick='javascript:adjDataDelete(event, this);' href='javascript:void(0);' class='btn btn-xs btn-danger' style="font-size: 15px">
<span class='glyphicon glyphicon-trash'></span>&nbsp;Delete selected item(s)&nbsp;</a>
<input type="button" value="Delete selected item(s)" class="btn btn-danger" id="transferBtnIdxx" disabled="true" onClick="javascript:adjDataDelete(event, this);"  />

					<input type="button" value="Delete selected item(s)" class="btn btn-danger" id="transferBtnIdxxx" onClick="javascript:adjDataDelete(event, this);" disabled="true" />
 -->
					<button id="delBtn" class="btn btn-danger" disabled onClick="javascript:adjDataDelete(event, this);" >
						Delete selected item(s)
					</button>
					<input type="button" value="Transfer" class="btn btn-primary" disabled id="transferBtnIdTwo"  onClick="javascript:trfData(event);" />
					<input type="hidden" name="vAdjDtIds" id="vAdjDtIds"  />
				</td>
			</tr>
		</tbody>
	</table>
</div>


<script type="text/javascript">
	$('#dbDetailsId').on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', function () {
		$('#delBtn').prop('disabled', (!$('#dbDetailsId').bootstrapTable('getSelections').length));
	});
</script>
