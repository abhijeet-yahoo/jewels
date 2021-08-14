<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<div class="col-xs-12">
		<div id="toolbarDS">
			<a class="btn btn-info" style="font-size: 15px" type="button"
				href="javascript:goToSType();"><span
				class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Details</a>
		</div>
		<div>
			<table id="stoneDId" data-toggle="table" data-toolbar="#toolbarDS"
				data-pagination="false" data-side-pagination="server"
				data-page-list="[5, 10, 20, 50, 100, 200]"
				data-height="380">
				<thead>
					<tr>
						<th data-field="stoneType" data-sortable="true">Stone Type</th>
						<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
						<th data-field="subShape" data-sortable="true">Sub Shape</th>
						<th data-field="quality" data-sortable="true">Quality</th>
						<th data-field="mm" data-sortable="true">Size/MM</th>
						<th data-field="sieve" data-sortable="true">Sieve</th>
						<th data-field="sizeGroup" data-sortable="true">Size Group</th>
						<th data-field="stones" data-sortable="true" 	>Stone</th>
						<th data-field="carat" data-sortable="true">Carat</th>
						<th data-field="setting" data-sortable="true">Setting</th>
						<th data-field="settype" data-sortable="true">Set Type</th>
						<th data-field="deactive" data-sortable="true">Status</th>
						<th data-field="action1" data-align="center">Edit</th>
						<th data-field="action2" data-align="center">Delete</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
