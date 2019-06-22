<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en' }"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n"/>
<html>
<head>
    <title>Questions</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="../css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="../css/style.css" rel="stylesheet">
    <!-- MDBootstrap Datatables  -->
    <link href="../css/addons/datatables.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="common/header.jsp"/>
<div class="container">
    <h2><fmt:message key="questions"/></h2>
    <!--Questions List-->
    <form action="app" method="post" role="form">
        <input type="hidden" name="command" value="questions"/>

        <c:choose>
            <c:when test="${not empty questionsList}">

                <table id="questionTable" class="table table-striped table-bordered table-sm table-dark">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td><fmt:message key="question"/></td>
                        <td><fmt:message key="question.type"/></td>
                    </tr>
                    </thead>
                    <c:if test="${language == 'en'}">
                        <c:forEach var="question" items="${questionsList}">
                            <tr>
                                <td>${question.id}</td>
                                <td>${question.questionEn}</td>
                                <td>${question.questionType.type}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${language == 'ru'}">
                        <c:forEach var="question" items="${questionsList}">
                            <tr>
                                <td>${question.id}</td>
                                <td>${question.questionRu}</td>
                                <td>${question.questionType.type}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No questions found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>
<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="../js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="../js/mdb.min.js"></script>
<!-- MDBootstrap Datatables  -->
<script type="text/javascript" src="../js/addons/datatables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#questionTable').DataTable({
            "pagingType": "full_numbers"
        });
        $('.dataTables_length').addClass('bs-select');
    });
</script>
</body>
</html>
