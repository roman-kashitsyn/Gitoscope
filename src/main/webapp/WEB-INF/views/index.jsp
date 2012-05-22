<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;encoding=utf-8">
    <title><spring:message code="welcome.title"/></title>
    <link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <style>
    body { margin-top: 20px; }
    </style>
  </head>
  <body>
    <div class="container">
      <h1 class="hero-unit"><spring:message code="welcome.message"/></h1>
      <p>
        <git:project-url><spring:message code="welcome.viewProjectList"/></git:project-url>
      </p>
    </div>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script src="<c:url value='/resources/bootstrap/css/bootstrap.min.js'/>"></script>
  </body>
</html>
