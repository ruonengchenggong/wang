<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.beans.*"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Book book=(Book)request.getAttribute("book");
%>
<form action="StudentAction_update1">
<input type="hidden" name="bid" value="<%=book.getBid() %>"/>
书名:<input type="text" name="bname" value="<%=book.getBname() %>"/>
数量:<input type="text" name="number" value="<%=book.getNumber() %>" />

<input type="submit" value="修改" />
</form>
${msg }
${err }
</body>
</html>