<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>
<git:project-url projectName="${project.name}"><c:out value="${project.name}"/></git:project-url>
<pre>
<code>
<c:out value="${text}"/>
</code>
</pre>
