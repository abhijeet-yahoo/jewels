<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div role="tabpanel">
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home"
			aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
		<li role="presentation"><a href="#option1"
			aria-controls="profile" role="tab" data-toggle="tab">Option1</a></li>
		<li role="presentation"><a href="#option2"
			aria-controls="messages" role="tab" data-toggle="tab">Option2</a></li>
		<li role="presentation"><a href="#option3" aria-controls="images"
			role="tab" data-toggle="tab">Option3</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="home">
			<%@include file="design.jsp"%>
		</div>

		<div role="tabpanel" class="tab-pane" id="option1">Option1</div>
		<div role="tabpanel" class="tab-pane" id="option2">Option2</div>


		<div role="tabpanel" class="tab-pane" id="imagesTest">
			<div class="container">
				<%@include file="imageUpload.jsp"%>
			</div>
		</div>
	</div>
</div>