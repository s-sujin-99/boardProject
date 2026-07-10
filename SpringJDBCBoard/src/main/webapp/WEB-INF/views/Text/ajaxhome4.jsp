<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#sendFile").on("change", function(event) {
			console.log("change");
			
			var files = event.target.files;
			var file = files[0];
			console.log(file);
			
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				type : "post",
				url : "/Text/gohome4",
				data : formData,
				dataType : 'text',
				processData : false,
				contentType : false,
				success : function(data) {
					alert(data);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>파일 업로드 Ajax</h1>

	<div>
		<input type="file" id="sendFile">
	</div>
</body>
</html>