<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Sign up</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/mdb.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/addons/datatables.min.css" rel="stylesheet">
</head>
<body class="hero-image">
<%--HEADER--%>
<jsp:include page="common/header.jsp"/>
<c:if test="${not empty requestScope.invalid_registration_data}">
    <div class="alert alert-danger" role="alert">${requestScope.invalid_registration_data}</div>
</c:if>
<c:if test="${not empty requestScope.user_exist_error}">
    <div class="alert alert-danger" role="alert">${requestScope.user_exist_error}</div>
</c:if>
<c:if test="${not empty requestScope.user_not_registered}">
    <div class="alert alert-danger" role="alert">${requestScope.user_not_registered}</div>
</c:if>
<div id="register">
    <div class="container">
        <div id="register-row" class="row justify-content-center align-items-center">
            <div id="register-column" class="col-md-6">
                <div id="register-box" class="col-md-12">
                    <form id="register-form" class="form" action="app" method="POST">
                        <input type="hidden" name="command" value="registration"/>
                        <h3 class="text-center text-info"><fmt:message key="signup"/></h3>
                        <div class="form-group">
                            <label for="first_name" class="text-info"><fmt:message key="first.name"/>:</label><br>
                            <input type="text" name="first_name" id="first_name" class="form-control" required
                                   pattern="[^-\s0-9`~!@#№$%^&*()_=+\\|\[\]{};:',.<>\/?]+">
                        </div>
                        <div class="form-group">
                            <label for="last_name" class="text-info"><fmt:message key="last.name"/>:</label><br>
                            <input type="text" name="last_name" id="last_name" class="form-control" required
                                   pattern="[^-\s0-9`~!@#№$%^&*()_=+\\|\[\]{};:',.<>\/?]+">
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info"><fmt:message key="email"/>:</label><br>
                            <input type="email" name="email" id="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info"><fmt:message key="password"/>:</label><br>
                            <input type="password" name="password" id="password" class="form-control" required
                                   pattern="^(?=.*?[A-ZА-Я])(?=.*?[a-zа-я])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,45}$"
                                   title="<fmt:message key="password.verification.message"/>">
                        </div>
                        <div class="form-group">
                            <label for="password_repeat" class="text-info"><fmt:message key="password.repeat"/>:</label><br>
                            <input type="password" name="password_repeat" id="password_repeat" class="form-control"
                                   required
                                   pattern="^(?=.*?[A-ZА-Я])(?=.*?[a-zа-я])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,45}$"
                                   title="<fmt:message key="password.verification.message"/>">
                        </div>

                        <input type="submit" name="submit" class="btn btn-info btn-md"
                               value="<fmt:message key="signup.enter"/>">
                        <input type="reset" name="reset" class="btn btn-danger btn-md"
                               value="<fmt:message key="reset"/>">

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--FOOTER--%>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
