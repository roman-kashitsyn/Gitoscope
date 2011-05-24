<%@ attribute name="projectName" required="true" rtexprvalue="true" %>
<%@ attribute name="commitId" required="true" rtexprvalue="true" %>
<%@ attribute name="styleClass" required="false" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/project/${projectName}.git/commit/${commitId}"/>" class="${styleClass}"><jsp:doBody/></a>
