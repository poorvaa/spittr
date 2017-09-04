<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spittle</title>
</head>
<body>
<div class="spittleView">
<div class="spittleMessage"><c:out value="${spittle.message}" /></div>
<div>
<span class="spittleTime"><c:out value="${spittle.time}" /></span>
</div>
</div>
</body>
</html>