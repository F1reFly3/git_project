<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Game</title>
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
<%--HEADER--%>
<jsp:include page="common/header.jsp"/>
<c:if test="${not empty requestScope.invalid_message_format}">
    <div class="alert alert-danger" role="alert">${requestScope.invalid_message_format}</div>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-1-2">
            Here should be: <br>
            For Judge: Question, prompt, answer, time start <br>
            For user: only question should be visible.
        </div>
        <div class="col-1-2">
            Here should be score for Experts and Viewers
        </div>
    </div>
    <div class="row">
        <div class="col-1-3">
            <c:set var="count_messages" value="${fn:length(messageList)}"/>
            <p><textarea name="textarea" cols="100" rows="50">
                <c:forEach var="i" begin="1" end="${count_messages}">
                    <c:set var="message" value="${messageList[count_messages-i]}"/>
                    <c:out value="${message.userEmail} : ${message.value}"/>
                </c:forEach>
   </textarea></p>

        </div>
        <div class="col-1-4">

        </div>
        <div class="col-1-4">
            <button class="open-button" onclick="openForm()"><fmt:message key="chat"/></button>
            <div class="form-popup" id="myForm">
                <form action="app" class="form-container">
                    <input type="hidden" name="command" value="game"/>
                    <h1><fmt:message key="chat"/></h1>
                    <input type="hidden" name="nickname" id="nickname" value="${sessionScope.user}"/>
                    <label for="msg"><b><fmt:message key="message"/></b></label>
                    <textarea id="msg" placeholder="<fmt:message key="type.message.placeholder"/>"
                              name="msg" required maxlength="200"></textarea>

                    <button type="submit" class="btn"><fmt:message key="send.message"/></button>
                    <button type="button" class="btn cancel" onclick="closeForm()"><fmt:message
                            key="close.chat"/></button>
                </form>
            </div>
        </div>
    </div>
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
    function openForm() {
        document.getElementById("myForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }

    function update() {
        $.get("app?command=reloadchat");
    }

    /*  setInterval(function () {
          update();
      }, 3000);*/
</script>
</body>
</html>
