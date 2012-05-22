<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
		<title>
			<c:choose>
				<c:when test="${title != null}"><c:out value="${title}"/></c:when>
				<c:otherwise><tiles:insertAttribute name="title"/></c:otherwise>
			</c:choose>
		</title>
	</head>
	<body>
		<tiles:insertAttribute name="content"/>
	</body>
</html>
