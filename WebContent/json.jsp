<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/ehcache-st2/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//下拉选中对比接口后，改变表单提交
	$("#duibiid").click(function(){
		var va=document.getElementById("duibiid").value
		if(va==1){
			$("#tijiao").attr("action","jsonduibi.action");
		}
		if(va==2){
			$("#tijiao").attr("action","jsonxiangxi.action");
		}
		if(va==3){
			$("#tijiao").attr("action","jsonchaxun.action");
		}
		if(va==4){
			$("#tijiao").attr("action","jmeter.action");
		}
	});
	
	//保持下拉单选中值
	$("#duibiid").val("${duibistate}");
	if("${duibistate}"==1){
		$("#tijiao").attr("action","jsonduibi.action");
	}
	if("${duibistate}"==2){
		$("#tijiao").attr("action","jsonxiangxi.action");
	}
	if("${duibistate}"==3){
		$("#tijiao").attr("action","jsonchaxun.action");
	}
	if("${duibistate}"==4){
		$("#tijiao").attr("action","jmeter.action");
	}
	
	//保持选中的审查模块
	$("#moduleidstrid").val("${moduleidstr}");
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ehcache</title>
</head>
<body>

<fieldset>
<legend>JSON对比</legend>
	<form id="tijiao" action="jsonduibi.action" method="post">
		<table>
			<tr>
				<td>
					可选中单个模块对比结果：
					<select id="moduleidstrid" name="moduleidstr">
					<option id="m1" value="-1">全部</option>
					<option id="m1" value="1">剂量范围</option>
					<option id="m2" value="2">肝损害剂量</option>
					<option id="m3" value="3">肾损害剂量</option>
					<option id="m4" value="4">相互作用</option>
					<option id="m5" value="5">体外配伍</option>
					<option id="m6" value="6">配伍浓度</option>
					<option id="m7" value="7">药物禁忌症</option>
					<option id="m8" value="8">不良反应</option>
					<option id="m9" value="9">超适应症</option>
					<option id="m10" value="10">儿童用药</option>
					<option id="m11" value="11">成人用药</option>
					<option id="m12" value="12">老人用药</option>
					<option id="m13" value="13">妊娠用药</option>
					<option id="m14" value="14">哺乳用药</option>
					<option id="m15" value="15">性别用药</option>
					<option id="m16" value="16">药物过敏</option>
					<option id="m17" value="17">给药途径</option>
					<option id="m18" value="18">重复用药</option>
					<option id="m19" value="19">越权用药</option>
					<option id="m20" value="20">围术期用药</option>
					<option id="m21" value="21">细菌耐药率</option>
					</select>
					
					<select id="duibiid" name="duibistate">
					<option id="d1" value="1">审查对比</option>
					<option id="d2" value="2">详细信息对比</option>
					<option id="d3" value="3">查询对比</option>
					<option id="d4" value="4">调用JMETER脚本测试所有案例</option>
					</select><br>
				</td>
			</tr>
			<tr>
				<td>
					<p>断言的json（请输入正确的数据）</p>
				</td>
				<td>
					
				</td>
				<td>
					<p>响应的json（请输入需要对比的数据）</p>
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
<c:if test="${listerrsum==2 }" >
<c:forEach  var="listerr1" items="${listerr1}" varStatus="status">
<c:out value="${listerr1}"></c:out><br>
</c:forEach>
</c:if>
</body>
</body>
</html>