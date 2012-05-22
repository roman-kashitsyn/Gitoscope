<%@ attribute name="projectName" required="false" rtexprvalue="true" %>
<%@ attribute name="styleClass" required="false" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
  <c:when test="${projectName != null}">
    <a href="<c:url value="/projects/${projectName}.git"/>" class="${styleClass}"><jsp:doBody/></a>
  </c:when>
  <c:otherwise>
    <a href="<c:url value="/projects"/>" class="${styleClass}"><jsp:doBody/></a>
  </c:otherwise>
</c:choose>
