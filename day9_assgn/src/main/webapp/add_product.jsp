<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="process_form.jsp" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Enter Product Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Enter Product Category</td>
				<td><input type="text" name="category" /></td>
			</tr>
			<tr>
				<td>Enter Product Price</td>
				<td><input type="number" name="price" /></td>
			</tr>
			<tr>
				<td>Enter Expiry Date</td>
				<td><input type="date" name="expiryDate" /></td>
			</tr>
			<tr>
				<td>Enter Desc</td>
				<td><textarea rows="20" cols="40" name="description"></textarea>
			</tr>

			<tr>
				<td><input type="submit" value="Add New Product" /></td>
			</tr>
		</table>
	</form>

</body>
</html>