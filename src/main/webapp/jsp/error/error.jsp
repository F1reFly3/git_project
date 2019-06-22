<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>"/>
    <script src="<c:url value="/js/bootstrap.js"/>"></script>
</head>
<body>
<%--HEADER--%>
<jsp:include page="/jsp/common/header.jsp"/>
<img src="${pageContext.request.contextPath}/img/error.png" style="width: 100%; height: 100%;">

</body>
</html>
