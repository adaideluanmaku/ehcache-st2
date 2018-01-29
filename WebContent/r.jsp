<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ehcache</title>
</head>
<body>

<fieldset>
<legend>病人姓名</legend>
	<form action="jsonduibi.action" method="post">
		<table>
			<tr>
				<td>
					<p>断言的json</p>
				</td>
				<td>
					
				</td>
				<td>
					<p>响应的json</p>
				</td>
			</tr>
			<tr>
				<td>
					<textarea type="text" name="json" style="width:600px;height:200px;vtical-align:middle;"><c:out value="${json }"></c:out></textarea>
				</td>
				<td><input type="submit" value="对比"></td>
				<td>
					<textarea type="text" name="json1" style="width:600px;height:200px;vtical-align:middle;"><c:out value="${json1 }"></c:out></textarea>
				</td>
			</tr>
		</table>
	
	</form>
</fieldset>
<c:if test="${listerrsum==0 }" >
<c:out value="对比正确"></c:out><br>
</c:if>
<c:if test="${listerrsum==1 }" >
<c:forEach  var="listerr1" items="${listerr1}" varStatus="status">
<c:out value="${listerr1}"></c:out><br>
<c:if test="${status.count%2==0}">
<p>--------------------------------------------------</p>
</c:if>
</c:forEach>
</c:if>
</body>
</html>