<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>List Act_Promos</title>
<style>
* {
	margin: 0;
	padding: 0;
}
body {
	font-family: 'Montserrat', sans-serif;
	margin: 0;
	padding: 0;
}
h1, h2 {
	
/* 	text-align: center; */
	text-transform: uppercase;
	letter-spacing: 2px;
	margin-bottom: 20px;
	margin-left: 14rem;
}

a {
	text-decoration: none;
	color: #007bff;
}

a:hover {
	text-decoration: underline;
}

table {
	width: 75%;
	border-collapse: collapse;
	background-color: #fff;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 15px 10px;
	text-align: center;
}

th {
	background-color: #007bff;
	color: #fff;
	text-transform: uppercase;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
}

td:last-child {
	text-align: center;
}

form {
	margin: 0;
}
	
</style>
</head>
<body>
	<h1>活動促銷列表</h1>
	<br>
	
	<table style="width:50%; text-align:center;">
		<tr>
			<th>促銷編號</th>
			<th>促銷標題</th>
			<th>促銷敘述</th>
			<th>促銷狀態</th>
			<th>促銷折扣</th>
			<th>促銷折價</th>
			<th>促銷時間起</th>
			<th>促銷時間迄</th>
		</tr>
		<c:forEach var="act_Promo" items="${act_PromoList}">
			<tr>
				<td>${act_Promo.promotion_id}</td>
				<td>${act_Promo.promotion_title}</td>
				<td>${act_Promo.promotion_content}</td>
				<td>${act_Promo.promotion_state}</td>
				<td>${act_Promo.promotion_discount}</td>
				<td>${act_Promo.promotion_coupon}</td>
				<td>${act_Promo.promotion_started}</td>
				<td>${act_Promo.promotion_end}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	
	<br><br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>