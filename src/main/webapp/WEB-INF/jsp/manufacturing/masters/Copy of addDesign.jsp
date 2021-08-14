<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="container">
	<form:form commandName="design"
		action="/jewels/manufacturing/masters/design/add.html"
		cssClass="form-horizontal designForm">

		<c:if test="${success eq true}">
			<div class="alert alert-success">Design ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputEmail" class="col-md-4 control-label">Group:</label>
					<div class="col-md-8">
						<form:select path="Group.id" class="form-control">
							<form:option value="" label="--- Select Group ---" />
							<form:options items="${groupList}" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputPassword" class="col-md-4 control-label">Create
						Date:</label>
					<div class="col-md-8">
						<form:input path="cDate" cssClass="form-control" />
						<form:errors path="cDate" />
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Design
						No:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Style
						No:</label>
					<div class="col-md-8">
						<input class="form-control" id="styleNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Version:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Designero:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Category:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Sub
						Category:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Model/Maker:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Concept:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Sub
						Concept:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Sizer:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Size:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Wax
						Temp:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Wax
						Pressure:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Wax
						Weight:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Silver
						Weight:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">14K:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-4">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">Email
						Concept:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-5">
				<div class="form-group">
					<label for="inputLabel3" class="col-sm-4 control-label">Silver
						Model:</label>
					<div class="col-sm-2">
						<input class="form-control" id="designNo" type="text">
					</div>
					<label for="inputLabel3" class="col-sm-4 control-label">Rubber
						Mould:</label>
					<div class="col-sm-2">
						<input class="form-control" id="designNo" type="text">
					</div>
					<label for="inputLabel3" class="col-md-4 control-label">Blue,
						Black, Coco:</label>
					<div class="col-sm-2">
						<input class="form-control" id="designNo" type="text">
					</div>
					<label for="inputLabel3" class="col-md-4 control-label">Must
						Have:</label>
					<div class="col-sm-2">
						<input class="form-control" id="designNo" type="text">
					</div>
					<label for="inputLabel3" class="col-md-4 control-label">Blue,
						Black, Coco:</label>
					<div class="col-sm-2">
						<input class="form-control" id="designNo" type="text">
					</div>
					<label for="inputLabel3" class="col-md-4 control-label">Must
						Have:</label>
					<div class="col-sm-2">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-15">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">14K:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-lg-15">
				<div class="form-group">
					<label for="inputLabel3" class="col-md-4 control-label">14K:</label>
					<div class="col-md-8">
						<input class="form-control" id="designNo" type="text">
					</div>
				</div>
			</div>


		</div>
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-5">
				<input type="submit" value="Save" class="btn btn-primary" /> <a
					class="btn btn-info" style="font-size: 15px" type="button"
					href="/jewels/manufacturing/masters/design.html">Design Listing</a>
				<form:input type="hidden" path="id" />
			</div>
		</div>
	</form:form>
</div>

<div class="tag-box tag-box-v3 form-page">
	<div class="headline">
		<h3>Control Sizing</h3>
	</div>
	<h4>Control Sizing</h4>
	<p>
		Set heights using classes like
		<code>.input-lg</code>
		, and set widths using grid column classes like
		<code>.col-lg-*</code>
		.
	</p>
	<br>

	<!-- Height Sizing -->
	<input class="form-control input-lg" placeholder=".input-lg"
		type="text"> <input class="form-control"
		placeholder="Default input" type="text"> <input
		class="form-control input-sm" placeholder=".input-sm" type="text">

	<div class="margin-bottom-40"></div>

	<select class="form-control input-lg">
		<option>input-lg</option>
	</select> <select class="form-control">
		<option>form-control</option>
	</select> <select class="form-control input-sm">
		<option>input-sm</option>
	</select>
	<!-- Height Sizing -->

	<div class="margin-bottom-40"></div>

	<!-- Column Sizing -->
	<div class="row">
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
		<div class="col-lg-3">
			<label for="inputLabel3" class=".col-lg-3 text-justify">Sizer:</label>
		</div>
	</div>
	<div class="form-group">
		<div class="row">
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
			<div class="col-lg-3">
				<input class="form-control" placeholder=".col-lg-3" type="text">
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-4">
			<input class="form-control" placeholder=".col-lg-4" type="text">
		</div>
		<div class="col-lg-4">
			<input class="form-control" placeholder=".col-lg-4" type="text">
		</div>
		<div class="col-lg-4">
			<input class="form-control" placeholder=".col-lg-4" type="text">
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<input class="form-control" placeholder=".col-lg-6" type="text">
		</div>
		<div class="col-lg-6">
			<input class="form-control" placeholder=".col-lg-6" type="text">
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<input class="form-control" placeholder=".col-lg-12" type="text">
		</div>
	</div>
	<!-- End Column Sizing -->
</div>
<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".designForm").validate(
						{
							rules : {
								name : {
									required : true,
									minlength : 3
								},
								code : {
									required : true,
									minlength : 3
								}
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							},
							messages : {
								name : {
									remote : "Design already exists"
								}
							}
						});
			});
</script>

