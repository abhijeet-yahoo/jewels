 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- @ include file="/WEB-INF/layout/taglib.jsp" --%>

<style>
input[type=file] {
	float: left;
}
</style>

<div class="container-fluid">
	<h4>Upload image(s) for Design</h4>
	<hr />

	<form action="javascript:void(0)">
		<button class="btn btn-sm btn-info upload" type="submit">Upload
			File</button>
		<input type="file" name="file" id="file" />
		<input type="hidden" name="image" value="${styleNo}_1" />

		<p />

		<div class="progress progress-striped active">
			<div class="progress-bar" style="width: 0%"></div>
		</div>
	</form>
	<form action="javascript:void(0)">
		<button class="btn btn-sm btn-info upload" type="submit">Upload
			File</button>
		<input type="file" name="file" id="file" />
		<input type="hidden" name="image" value="${styleNo}_2" />

		<p />

		<div class="progress progress-striped active">
			<div class="progress-bar" style="width: 0%"></div>
		</div>
	</form>
	<form action="javascript:void(0)">
		<button class="btn btn-sm btn-info upload" type="submit">Upload
			File</button>
		<input type="file" name="file" id="file" />
		<input type="hidden" name="image" value="${styleNo}_3" />

		<p />

		<div class="progress progress-striped active">
			<div class="progress-bar" style="width: 0%"></div>
		</div>
	</form>

	<div class="container">
		<div class="row">
			<div class="col-md-3 col-sm-4 col-xs-6">
				<img class="img-responsive"
					src="" />
			</div>
			<div class="col-md-3 col-sm-4 col-xs-6">
				<img class="img-responsive"
					src="" />
			</div>
			<div class="col-md-3 col-sm-4 col-xs-6">
				<img class="img-responsive"
					src="" />
			</div>
		</div>
	</div>
</div>

<script>
	$(document).on('submit', 'form', function(e) {
		e.preventDefault();
		$form = $(this);
		uploadImage($form);
	});

	function uploadImage($form) {
		$form.find('.progress-bar').width('0%');

		var fdata = new FormData($form[0]);
		var request = new XMLHttpRequest();

		request.upload.addEventListener('progress', function(e) {
			var percent = Math.round((e.loaded / e.total) * 100);
			console.log(e.loaded + " " + e.total + " " + percent);
			$form.find('.progress-bar').width(percent + '%')
					.html(percent + '%');
		});

		request.addEventListener('load', function(e) {
			$form.find('.progress-bar').addClass('.progress-bar-danger').html(
					'uploaded completed...........');
		});

		request.open('post', '/manufacturing/masters/design/image/upload.html');
		request.send(fdata);
	}
</script>