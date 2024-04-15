<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>Activity Promotion</title>
<style>
* {
	margin: 0;
	padding: 5;
}
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
}
h2 {
	
/* 	text-align: center; */
	text-transform: uppercase;
	letter-spacing: 2px;
	margin-bottom: 20px;
	text-transform: uppercase;
	letter-spacing: 2px;
}
label {
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
	color: #555;
}

input[type="text"], select {
	width: 30%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	margin-bottom: 10px;
	box-sizing: border-box;
}

input[type="submit"], a.button {
	background-color: #007bff;
	border: none;
	color: white;
	padding: 15px 30px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	cursor: pointer;
	border-radius: 5px;
	transition: background-color 0.3s ease;
}

input[type="submit"]:hover, a.button:hover {
	background-color: #007bff;
}

</style>
</head>
<body>
<h2>活動管理系統-活動促銷管理</h2>
<a href="${pageContext.request.contextPath}/act/act_promo.do?action=getAll">查詢所有活動促銷</a>
<br><br>
<hr>
<h3><b>複合查詢 (使用 Criteria Query)：</b></h3>
	<form action="${pageContext.request.contextPath}/act/act_promo.do" method="post">
	<p><label>活動促銷標題查詢：</label></p>
	<input type="text" name="promotion_title"><br>
	<p><label>活動促銷日期起訖:</label></p>
	<input type="date" name="promotion_started"> ～ <input type="date" name="promotion_end"><br>

	<p><input type="submit" value="送出"></p>
	<input type="hidden" name="action" value="compositeQuery">
	</form>
	<br> 
	<hr>

		<ul>
			<li><a href="<%=request.getContextPath()%>/act_Promo/addAct_Promos.jsp"
				class="button">新增活動</a></li>
		</ul>
</body>
</html>
