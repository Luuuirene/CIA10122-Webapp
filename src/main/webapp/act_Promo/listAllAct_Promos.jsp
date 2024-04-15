<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>ListAll Act_Promos</title>
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
	
	text-align: center;
	text-transform: uppercase;
	letter-spacing: 2px;
	margin-bottom: 20px;
	margin-right: 15rem;
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
	<c:if test="${act_Promo_PageQty > 0}">
  		<b><font color=orange>第${currentPage}/${act_Promo_PageQty}頁</font></b>
	</c:if>
	
	<br>
	
	<table >
		<tr>
			<th>促銷編號</th>
			<th>促銷標題</th>
			<th>促銷敘述</th>
			<th>促銷狀態</th>
			<th>促銷折扣</th>
			<th>促銷折價</th>
			<th>促銷時間起</th>
			<th>促銷時間迄</th>
			<th>修改</th>
			<th>刪除</th>
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
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act_promo.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="promotion_id"  value="${act_PromoVO.promotion_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act_promo.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="promotion_id"  value="${act_PromoVO.promotion_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/act/act_promo.do?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/act/act_promo.do?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= act_Promo_PageQty}">
		<a href="${pageContext.request.contextPath}/act/act_promo.do?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != act_Promo_PageQty}">
		<a href="${pageContext.request.contextPath}/act/act_promo.do?action=getAll&page=${act_Promo_PageQty}">至最後一頁</a>&nbsp;
	</c:if>
	<br>
	
	<br><br>
	
	<a href="${pageContext.request.contextPath}/index.jsp">回首頁</a>	
</body>
</html>