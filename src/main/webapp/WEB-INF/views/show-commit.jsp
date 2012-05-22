<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>
<h3><c:out value="${commit.shortMessage}"/></h3>
<hr/>
<table>
<tbody>
	<tr>
		<td>Author</td>
		<td><c:out value="${commit.author.name}"/></td>
	</tr>
	<tr>
		<td>Committer</td>
		<td><c:out value="${commit.committer.name}"/></td>
	</tr>
	<tr>
		<td>Commit</td>
		<td class="object-ref"><c:out value="${commit.id}"/></td>
	</tr>
	<tr>
		<td>Tree</td>
		<td class="object-ref">
			<git:tree-url projectName="${project.name}" treeId="${commit.treeId}">
				<c:out value="${commit.treeId}"/>
			</git:tree-url>
		</td>
	</tr>
	<c:forEach var="parent" items="${parents}">
	<tr>
		<td>Parent</td>
		<td>
			<git:commit-url projectName="${project.name}" commitId="${parent.id}" styleClass="object-ref">
				<c:out value="${parent.id}"/>
			</git:commit-url>
		</td>
	</tr>
	</c:forEach>
	<tr>
	</tr>
</tbody>
</table>
<hr/>
<div class="full-message">
<c:out value="${commit.fullMessage}"/>
</div>
<div style="padding-top: 10px;">
<git:project-url projectName="${project.name}">&lt; Back</git:project-url>
</div>
