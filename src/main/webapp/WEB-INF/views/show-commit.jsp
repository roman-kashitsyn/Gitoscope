<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>

<h3><c:out value="${commit.shortMessage}"/></h3>
<br>
<table class="table">
  <tbody>
    <tr>
    <th>Author</th>
    <td><c:out value="${commit.author.name}"/></td>
  </tr>
  <tr>
    <th>Committer</th>
    <td><c:out value="${commit.committer.name}"/></td>
  </tr>
  <tr>
    <th>Commit</th>
    <td class="object-ref"><c:out value="${commit.id}"/></td>
  </tr>
  <tr>
    <th>Tree</th>
    <td class="object-ref">
      <git:tree-url projectName="${project.name}" treeId="${commit.treeId}">
        <c:out value="${commit.treeId}"/>
      </git:tree-url>
    </td>
  </tr>
  <c:forEach var="parent" items="${parents}">
  <tr>
    <th>Parent</th>
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

<hr>

<code>
  <c:out value="${commit.fullMessage}"/>
</code>

<hr>

<div>
  <git:project-url projectName="${project.name}">&lt; Back</git:project-url>
</div>
