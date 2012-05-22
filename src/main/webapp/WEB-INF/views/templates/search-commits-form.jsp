<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form name="commit-search" method="GET" action="<c:url value="/project/${project.name}.git/commit/search.html"/>">
  <table class="commit-search">
    <tbody>
      <tr>
	<td>
	  <label for="author">
	    <spring:message code="commit.author"/>
	  </label>
	</td>
	<td>
	  <input name="author" value="${param.author}"/>
	</td>
      </tr>
      <tr>
	<td>
	  <label for="committer">
	    <spring:message code="commit.committer"/>
	  </label>
	</td>
	<td>
	  <input name="committer" value="${param.committer}"/>
	</td>
      </tr>
      <tr>
	<td>
	  <label for="message">
	    <spring:message code="commit.message"/>
	  </label>
	</td>
	<td>
	  <input name="message" value="${param.message}"/>
	</td>
      </tr>
      <tr>
	<td rowspan="2">
	  <input type="submit" name="<spring:message code="search.button"/>"/>
	</td>
      </tr>
    </tbody>
  </table>
</form>
