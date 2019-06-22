<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"/>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

<c:set var="language" value="${not empty sessionScope.lang ? sessionScope.lang : 'en' }" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n" scope="session"/>

<header>
    <div class="container-fluid">
        <div class="row">
            <nav class="navbar bg-dark navbar-expand-md navbar-light col justify-content-between">
                <form action="app" class="text-light navbar-brand">
                    <input type="hidden" name="command" value="main_page"/>
                    <input type="submit" class="btn btn-info btn-md" value=<fmt:message key="home"/>>
                </form>


                <c:if test="${not empty sessionScope.userRole}">
                    <c:if test="${sessionScope.userRole == 'admin'}">
                        <form action="app" class="text-light navbar-brand">
                            <input type="hidden" name="command" value="users"/>
                            <input type="submit" class="btn btn-info btn-md" value="<fmt:message key="users"/>">
                        </form>
                        <form action="app" class="text-light navbar-brand">
                            <input type="hidden" name="command" value="questions"/>
                            <input type="submit" class="btn btn-info btn-md" value="<fmt:message key="questions"/>">
                        </form>
                        <form action="app" class="text-light navbar-brand">
                            <input type="hidden" name="command" value="add_question_page"/>
                            <input type="submit" class="btn btn-info btn-md" value="<fmt:message key="question.add"/>">
                        </form>
                    </c:if>
                </c:if>


                <h2 class="my-header-text"><fmt:message key="game.name"/></h2>
                <c:if test="${not empty sessionScope.user}">
                    <h6 class="nav-item text-light"><fmt:message key="welcome.user"/>
                            ${sessionScope.user}</h6>
                </c:if>
                <div class="navbar-nav">
                    <c:if test="${empty sessionScope.user}">
                        <form action="app" class="text-light navbar-brand">
                            <input type="hidden" name="command" value="login_page"/>
                            <input type="submit" class="btn btn-info btn-md" value="<fmt:message key="signin"/>">
                        </form>
                        <form action="app" class="text-light navbar-brand">
                            <input type="hidden" name="command" value="registration_page"/>
                            <input type="submit" class="btn btn-info btn-md" value="<fmt:message key="signup"/>">
                        </form>
                    </c:if>

                    <c:if test="${not empty sessionScope.user}">
                        <form action="app" class="text-light navbar-brand">
                            <input type="hidden" name="command" value="game"/>
                            <input type="submit" class="btn btn-info btn-md" value=<fmt:message key="game"/>>
                        </form>
                        <form action="app" class="text-light navbar-brand">
                            <input type="hidden" name="command" value="logout"/>
                            <input type="submit" class="btn btn-info btn-md" value="<fmt:message key="signout"/>">
                        </form>
                    </c:if>

                    <div class="nav-item">
                        <form action="app">
                            <input type="hidden" name="command" value="language"/>
                            <select class="input-group" name="lang" onchange="this.form.submit()">
                                <option value="en" ${sessionScope.lang == 'en' ? 'selected="selected"' : ''}>
                                    English
                                </option>
                                <option value="ru" ${sessionScope.lang == 'ru' ? 'selected="selected"' : ''}>
                                    Русский
                                </option>
                            </select>
                        </form>
                    </div>

                </div>
            </nav>
        </div>
    </div>
</header>