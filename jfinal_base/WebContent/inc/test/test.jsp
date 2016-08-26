<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/cktag" %>
<%@include file="head.jsp" %>

<!-- list -->
<div>
	<table>
	<c:forEach items="${list }" var="t">
		<tr>
			<td>name</td><td>${t.name }</td>
			<td>age</td><td>${t.age }</td>
		</tr>
	</c:forEach>
	</table>
</div>
<!-- save -->
<div>
	<form action="test/save">
	<table>
		<tr>
			<td>name</td><td><input name="test.name"/></td>
			<td>age</td><td><input name="test.age"/></td>
			<td>保存</td><td><input type="submit"/></td>
		</tr>
	</table>
	</form>
</div>


<%@include file="footer.jsp" %>
