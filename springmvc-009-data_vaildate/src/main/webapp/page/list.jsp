<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="/springmvc-009-data_vaildate/">
<meta charset="UTF-8">
<title>员工信息列表</title>
<style type="text/css">
*{padding:0px; margin:0px;}
#empinfo{border-collapse: 0px; border-spacing: 0px; width:800px; margin:80px auto; text-align:center;}
#empinfo caption{font-size:30px; font-weight: bold;}
#empinfo td, #empinfo th{height:30px;}
#empinfo #addEmp{float:right; margin-right:60px;}
</style>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function delEmp(obj, id){
		if (confirm("是否确定删除？")){
			$.post("emp/emp/" + id,   //请求URI
					{"_method":"DELETE"}, //请求参数
					function (data){  //回调函数
						if(data){
							//删除所在有行
							$("#empinfo")[0].deleteRow(obj.parentNode.parentNode.rowIndex)
						}
					}, "json"); //返回数据的格式
		}
	}
</script>
</head>
<body>
	<table id="empinfo" border="1">
		<caption>员工信息列表</caption>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>邮箱</th>
			<th>性别</th>
			<th>部门</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${emps }" var="emp">
			<tr>
				<td>${emp.id}</td>
				<td>${emp.lastName }</td>
				<td>${emp.email }</td>
				<td>${emp.gender }</td>
				<td>${emp.department.departmentName }</td>
				<td><a href="emp/emp/${emp.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="delEmp(this, ${emp.id})">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" ><a href="emp/emp" id="addEmp">添加员工</a></td>
		</tr>
	</table>
</body>
</html>