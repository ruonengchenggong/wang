<%@page import="com.util.HibUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,com.beans.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border="1" width="80%">
		<tr>
			<th>Id</th>
			<th>书名</th>
			<th>数量</th>
			<th>操作</th>
		</tr>
		<s:iterator value="#bList" var="b">
			<tr>
				<td>${b.bid}</td>
				<td>${b.bname}</td>
				<td>${b.number}</td>
				<td><a
					href="${pageContext.request.contextPath}/StudentAction_update?bid=${b.bid}">修改</a></td>
			</tr>
		</s:iterator>
	</table>
	${msg }
</body>
</html>