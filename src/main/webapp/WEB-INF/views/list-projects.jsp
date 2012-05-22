<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>
<%@ page import="gitoscope.domain.Project" %>
<%@ page import="java.util.List" %>
<table class="project-list">
<tbody>
	<tr>
		<th>Name</th>
		<th>Last Modified</th>
	</tr>
	<c:forEach var="project" items="${projectList}">
	<tr>
		<td><git:project-url projectName="${project.name}"><c:out value="${project.name}"/></git:project-url>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${project.lastModified}"/></td>
	</tr>
	</c:forEach>
</tbody>
</table>
