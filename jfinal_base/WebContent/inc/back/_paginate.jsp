<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <c:set var="pageNumber" scope="request" value="${userList.pageNumber}" /> 
<c:set var="pageSize" scope="request" value="${userList.pageSize}" /> 
<c:set var="totalPage" scope="request" value="${userList.totalPage}" /> 
<c:set var="totalRow" scope="request" value="${userList.totalRow}" /> 
<c:set var="pageUrl" scope="request" value="index#w148" />
<c:set var="urlParm" scope="request" value="?page=1&key=test" /> --%>

<c:if test="${totalPage > 0 and pageNumber <= totalPage }">
	<c:set var="startPage" scope="request" value="${pageNumber - 4 }" />
	<c:set var="endPage" scope="request" value="${pageNumber + 4 }" />
	<c:choose>
 	<c:when test="${totalPage <= 10}">
		<c:set var="startPage" scope="request" value="1" />
		<c:set var="endPage" scope="request" value="${totalPage}" />
	</c:when>
	<c:when test="${(totalPage > 10) and (pageNumber <= 10)}">
		<c:set var="startPage" scope="request" value="1" />
		<c:set var="endPage" scope="request" value="10" />
	</c:when>
	<c:when test="${(totalPage > 10) and (pageNumber > 10) and ((totalPage - pageNumber) >= 10)}">
		<c:set var="startPage" scope="request" value="${pageNumber}" />
		<c:set var="endPage" scope="request" value="${pageNumber + 9}" />
	</c:when>
	<c:when test="${(totalPage > 10) and (pageNumber > 10) and ((totalPage - pageNumber) < 10)}">
		<c:set var="startPage" scope="request" value="${totalPage-totalPage%10+1}" />
		<c:set var="endPage" scope="request" value="${totalPage}" />
	</c:when>
	<c:otherwise>
	</c:otherwise>
	</c:choose>
	
<%-- 	<c:if test="${(totalPage - pageNumber) < 8 }">
		<c:set var="endPage" scope="request" value="${totalPage}" />
	</c:if> --%>
	
<div class="pull-right">
<div class=''>
<ul class="pagination" style='margin:0;'>
<c:choose>
	<c:when test="${pageNumber eq 1}">
		<li class="disabled previous"><a>首 页</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="${pageUrl}/1${urlParm}">首 页</a></li>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${pageNumber <= 1}">
		<li class="disabled previous"><a>上一页</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="${pageUrl}/${pageNumber-1}${urlParm}">上一页</a></li>
	</c:otherwise>
</c:choose>

<%-- <c:if test="${pageNumber > 8 }">
	<li><a href="${pageUrl}/1${urlParm}">1</a></li>
	<li><a href="${pageUrl}/2${urlParm}">2</a></li>
	<li><a class="gap">…</a></li>
</c:if>
 --%>
<c:forEach begin="${startPage}" end="${endPage}" step="1" var="i">
<c:choose>
	<c:when test="${pageNumber eq i }">
		<li class="active"><a>${i}</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="${pageUrl}/${i}${urlParm}">${i }</a></li>
	</c:otherwise>
</c:choose>
</c:forEach>

<%-- <c:choose>
<c:when test="${pageNumber <= 7 }">
	<li><a class="gap">…</a></li>
	<li><a href="${pageUrl}/${totalPage-1}${urlParm}">${totalPage - 1}</a></li>
	<li><a href="${pageUrl}/${totalPage}${urlParm}">${totalPage}</a></li>
</c:when>
<c:when test="${(totalPage-pageNumber) >= 7 }">
	<li><a class="gap">…</a></li>
	<li><a href="${pageUrl}/${totalPage-1}${urlParm}">${totalPage - 1}</a></li>
	<li><a href="${pageUrl}/${totalPage}${urlParm}">${totalPage}</a></li>
</c:when>
<c:otherwise>
</c:otherwise>
</c:choose> --%>

<c:choose>
	<c:when test="${pageNumber==totalPage || totalPage==0}">
		<li class="disabled next"><a>下页</a></li>
	</c:when>
	<c:when test="${pageNumber < totalPage}">
		<li><a href="${pageUrl}/${pageNumber+1}${urlParm}">下一页</a></li>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${pageNumber eq totalPage}">
		<li class="disabled next"><a>尾 页</a></li>
	</c:when>
	<c:otherwise>
		<li><a href="${pageUrl}/${totalPage}${urlParm}">尾 页</a></li>
	</c:otherwise>
</c:choose>
</ul>
</div>

<%-- <div class='col-md-4'>
<ul class='list-unstyled breadcrumb'>
<li> 
<span>共${totalPage}页/${totalRow}条数据 </span>

<span>选择:</span>
<select name="pageSelect" onchange="window.location.href=this.options[selectedIndex].value">
	<c:forEach var="i" begin="1" end="${totalPage}" step="1">
		<c:choose>
			<c:when test="${i==pageNumber}">
				<option value="${pageUrl}/${i}${urlParm}" selected>${i}</option>
			</c:when>
			<c:when test="${i!=pageNumber}">
				<option value="${pageUrl}/${i}${urlParm}">${i}</option>
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
</li>
</ul>
</div> --%>


</div>

<%-- 	<div class="col-md-12">
		<ul class="pagination">
			<c:choose>
				<c:when test="${pageNumber == 1}">
					<li class="disabled previous"><a>上一页</a></li>
				</c:when>			
				<c:otherwise>
					<li><a href="#" class="prev_page">上页</a></li>
				</c:otherwise>	
			</c:choose>
			
			<c:if test="${ currentPage > 8}">
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a class="gap">...</a></li>
			</c:if>
			
			<c:forEach var="name" items="1,2,3,4,5" varStatus="status">				
				<c:choose>
					<c:when test="${currentPage == status.index }">
						<li class="active"><a>1</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="#">1</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:if test="${(totalPage-currentPage) >= 8}">
				<li><a class="gap">...</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
			</c:if>
			
			<c:choose>
				<c:when test="${currentPage == totalPage }">
					<li class="disabled next"><a>下页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#" class="next_page" rel="next">下页</a></li>
				</c:otherwise>
			</c:choose>
	</ul>
	</div>	 --%>
</c:if>