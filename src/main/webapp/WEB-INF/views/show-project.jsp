<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>
<%@ page import="gitoscope.domain.Project" %>
<%@ page import="java.util.List" %>
<h1><c:out value="${project.name}"/></h1>
<table class="project-list">
<tbody>
	<tr>
		<th>Commit Date</th>
		<th>Author</th>
		<th>Message</th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach var="commit" items="${commits}">
	<tr>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${commit.when}"/></td>
		<td><c:out value="${commit.author.name}"/></td>
		<td>
			<git:commit-url projectName="${project.name}" commitId="${commit.id}">
				<c:out value="${commit.shortMessage}"/>
			</git:commit-url>
		</td>
		<td>
			<git:tree-url projectName="${project.name}" treeId="${commit.treeId}">| tree |</git:tree-url>
		</td>
	</tr>
	</c:forEach>
</tbody>
</table>
