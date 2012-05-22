<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>
<table class="table table-condensed">
  <thead>
    <tr>
      <th>Name</th>
      <th>Size (bytes)</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="obj" items="${objList}">
    <tr>
      <td class="file-name">
        <c:choose>
          <c:when test="${obj.tree}">
          <i class="icon-folder-open"></i>
          <git:tree-url projectName="${project.name}" treeId="${obj.id}">
            <c:out value="${obj.name}"/>
          </git:tree-url>
          </c:when>
          <c:otherwise>
            <i class="icon-file"></i>
            <git:blob-url projectName="${project.name}" blobId="${obj.id}">
              <c:out value="${obj.name}"/>
            </git:blob-url>
          </c:otherwise>
        </c:choose>
      </td>
      <td class="number"><c:out value="${obj.sizeInBytes}"/></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
