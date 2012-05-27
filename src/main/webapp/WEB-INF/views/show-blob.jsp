<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="git" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" href="<c:url value='/resources/highlight/github.css'/>">

<h2>
  <git:project-url projectName="${project.name}"><c:out value="${project.name}"/></git:project-url>
</h2>

<hr>

<pre><code><c:out value="${text}"/></code></pre>

<script src="<c:url value='/resources/highlight/highlight.pack.js'/>"></script>
<script>
     hljs.initHighlightingOnLoad();
</script>
