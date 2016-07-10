<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<br/><div class="sabrosus" style="width:100%;<c:if test="${pageNumber == 1}">visibility:hidden</c:if>">
	<c:if test="${nowPage==1||pageNumber==1}">
	            <span class="disabled"> <<</span>
	            <span class="disabled"> <</span>
	</c:if>
	<c:if test="${nowPage>1}">
	    <c:choose>
	        <c:when test="${Keyword!=''}">
	            <a href="${hrefUrl}&page=1"><<</a>
	            <a href="${hrefUrl}&page=${nowPage-1}"><</a>
	        </c:when>
	        <c:otherwise>
	            <a href="${hrefUrl}&page=1"><<</a>
	            <a href="${hrefUrl}&page=${nowPage-1}"><</a>
	        </c:otherwise>
	    </c:choose>
	</c:if>
	
	<%           //根据关键字查询列表
	            String Keyword = request.getParameter("Keyword");
	            String hrefUrl = request.getAttribute("hrefUrl").toString();
	            if (Keyword != null) {
	                //Keyword = new String(Keyword.getBytes("ISO-8859-1"), "UTF-8");  //中文转码
	            }
	
	            //总页数
	            String str = request.getAttribute("pageNumber").toString();
	            int pageNumber = Integer.parseInt(str);
	
	            //当前页
	            String now = request.getAttribute("nowPage").toString();
	            int nowNumber = Integer.parseInt(now);
	
	            if(nowNumber <= 6 || pageNumber <= 10){
	            	for(int i=1;i<=10 && i<= pageNumber;i++){
	            		if(Keyword != null && Keyword != "") {
	            			if(i==nowNumber){
	                			out.println("<span class=\'current\'> "+i+"</span>");
	                		}else{
	                			out.println("<a href='"+hrefUrl+"&page=" + i + "' >" + i + "</a>");
	                		}
	            		}
	            		else {
	            			if(i==nowNumber){
	            				out.println("<span class=\'current\'> "+i+"</span>");
	                		}else{
	                			out.println("<a href='"+hrefUrl+"&page=" + i + "' >" + i + "</a>");
	                		}
	            		}
	            	}
	            }
	            
	            else if((pageNumber-nowNumber)<5){
	            	for(int i=(pageNumber-9);i<= pageNumber;i++){
	            		if(Keyword != null & Keyword != "") {
	            			if(i==nowNumber){
	            				out.println("<span class=\'current\'> "+i+"</span>");
	                		}else{
	                			out.println("<a href='"+hrefUrl+ "&page=" + i + "' >" + i + "</a>");
	                		}
	            		}
	            		else {
	            			if(i==nowNumber){
	            				out.println("<span class=\'current\'> "+i+"</span>");
	                		}else{
	                			out.println("<a href='"+hrefUrl+"&page=" + i + "' >" + i + "</a>");
	                		}
	            		}
	            	}
	            }
	            
	            else{
	            	for(int i=(nowNumber-5);i<(nowNumber+5) && i<= pageNumber;i++){
	            		if(Keyword != null & Keyword != "") {
	            			if(i==nowNumber){
	            				out.println("<span class=\'current\'> "+i+"</span>");
	                		}else{
	                			out.println("<a href='"+hrefUrl + "&page=" + i + "' >" + i + "</a>");
	                		}
	            		}
	            		else {
	            			if(i==nowNumber){
	            				out.println("<span class=\'current\'> "+i+"</span>");
	                		}else{
	                			out.println("<a href='"+hrefUrl+"&page=" + i + "' >" + i + "</a>");
	                		}
	            		}
	            	}                                	
	            }
	%>
	<c:if test="${nowPage!=pageNumber}">
	    <c:choose>
	        <c:when test="${Keyword!=''}">
	            <a href="${hrefUrl}&page=${nowPage+1}">></a>
	            <a href="${hrefUrl}&page=${pageNumber}">>></a>
	            ${nowPage}/${pageNumber}
	        </c:when>
	        <c:otherwise>
	            <a href="${hrefUrl}&page=${nowPage+1}">></a>
	            <a href="${hrefUrl}&page=${pageNumber}">>></a>
	            ${nowPage}/${pageNumber} 
	        </c:otherwise>
	    </c:choose>
	</c:if>
	
	<c:if test="${nowPage==pageNumber}">
	            <span class="disabled"> ></span>
	            <span class="disabled"> >></span>
	            ${pageNumber}/${pageNumber}
	</c:if>
</div>
	