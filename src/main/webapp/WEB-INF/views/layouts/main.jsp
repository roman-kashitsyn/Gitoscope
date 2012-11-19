<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>">
    <link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <style type="text/css">
    body {
      padding-top: 60px;
      padding-bottom: 40px;
    }
    </style>
    <title>
      <c:choose>
        <c:when test="${title != null}"><c:out value="${title}"/></c:when>
        <c:otherwise><tiles:insertAttribute name="title"/></c:otherwise>
      </c:choose>
    </title>
  </head>
  <body>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Gitoscope</a>
            <div class="nav-collapse">
              <ul class="nav">
                <li><git:project-url>Projects</git:project-url></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </div>
      </div>
    <div class="container">
      <tiles:insertAttribute name="content"/>
    </div>
    <script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>"></script>
  </body>
</html>
