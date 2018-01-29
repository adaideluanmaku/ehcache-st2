<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript">
function copy(num)
{
var numid=document.getElementById(num);
numid.select(); // 选择对象
document.execCommand("Copy"); // 执行浏览器复制命令
//alert("已复制好，可贴粘。");
}
 
 </script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ehcache</title>
</head>
<body>
<fieldset>
<legend>sql</legend>
	<form action="name.action" method="post">
		<textarea name="sql" type="text" style="width:600px;height:100px;vtical-align:middle;"><c:out value="${sql }"></c:out></textarea>
		<input type="submit" value="提交">
	</form>
<p>输入范例</p>
<p>select gatherbaseinfo from sa_gather_info where gatherbaseinfo like '%特殊字符%' and gatherbaseinfo like '%哺乳用药0%' and inserttime>'2016-04-22 15:50:00' order by inserttime asc</p>
	
</fieldset>
<c:forEach  var="listname" items="${listname1}" varStatus="status">
<c:out value="${status.count }"></c:out>：<textarea id="${status.count }"><c:out value="${listname}"></c:out></textarea>
<input type="button" onClick="copy(${status.count })" value="点击复制名字" /><br>
</c:forEach>
</body>
</html>