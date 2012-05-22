<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;encoding=utf-8"/>
		<title><spring:message code="welcome.title"/></title>
	</head>
	<body>
		<h1><spring:message code="welcome.message"/></h1>
		<a href="<c:url value="/project/list"/>"><spring:message code="welcome.viewProjectList"/></a>
	</body>
</html>
