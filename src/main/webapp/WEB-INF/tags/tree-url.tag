<%@ attribute name="projectName" required="true" rtexprvalue="true" %>
<%@ attribute name="treeId" required="true" rtexprvalue="true" %>
<%@ attribute name="styleClass" required="false" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/project/${projectName}.git/tree/${treeId}"/>" class="${styleClass}"><jsp:doBody/></a>
