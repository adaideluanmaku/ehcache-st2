<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<fieldset>
<legend>数据对比</legend>
<form action="dbzong.action" method="post">
<input type="text" name="pass">
<input type="submit" value="对比">
</form>
</fieldset>
<c:out value="${err }"></c:out>


<form action="dbzong.action" method="post">
<input type="submit" value="对比">
</form>
</fieldset>
<c:out value="${webrsst }"></c:out>
</body>
</html>