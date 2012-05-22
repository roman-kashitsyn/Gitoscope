<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
	</head>
	<body>
		<div class="search-form">
			<tiles:insertAttribute name="search-form"/>
		</div>
		<div class="search-result">
			<tiles:insertAttribute name="search-result"/>
		</div>
	</body>
</html>
