<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Add Question</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/mdb.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/addons/datatables.min.css" rel="stylesheet">
</head>
<body>
<%--HADER--%>
<jsp:include page="common/header.jsp"/>
<c:set var="question_type_param" value="${not empty sessionScope.question_type ? sessionScope.question_type
 : 'single' }" scope="session"/>

<c:if test="${not empty requestScope.question_added}">
    <div class="alert alert-info" role="alert">${requestScope.question_added}</div>
</c:if>

<c:if test="${not empty requestScope.database_error}">
    <div class="alert alert-danger" role="alert">${requestScope.database_error}</div>
</c:if>

<%--Should fix multi and single before uncomment this block <c:if test="${not empty requestScope.invalid_question_data}">
    <div class="alert alert-danger" role="alert">${requestScope.invalid_question_data}</div>
</c:if>

<c:if test="${not empty requestScope.invalid_answer_data}">
    <div class="alert alert-danger" role="alert">${requestScope.invalid_answer_data}</div>
</c:if>--%>

</div>
<div class="container">
    <div class="row justify-content-center align-items-center">
        <div id="question-column" class="col-md-6">
            <div id="question-box" class="col-md-12">
                <form action="app" method="POST" class="form" style="width: 150%">
                    <input type="hidden" name="command" value="add_question"/>
                    <h3 class="text-center text-info"><fmt:message key="question.add"/></h3>
                    <div class="form-group">

                        <label for="question_type" class="text-danger">Type of question</label>
                        <select id="question_type" name="question_type" onchange="this.form.submit()">
                            <option value="single" ${sessionScope.question_type == 'single' ? 'selected="selected"' : ''}>
                                single
                            </option>
                            <option value="multi" ${sessionScope.question_type == 'multi' ? 'selected="selected"' : ''}>
                                multi
                            </option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="question_en" class="text-info">Question_en</label>
                        <textarea id="question_en" name="question_en" class="form-control" rows="5" required
                                  placeholder="Write something.." maxlength="450"></textarea>
                        <div class="text-right" id="count_message_qen"></div>
                    </div>

                    <div class="form-group">
                        <label for="question_ru" class="text-info">Question_ru</label>
                        <textarea id="question_ru" name="question_ru" class="form-control" rows="5" required
                                  placeholder="Write something.." maxlength="450"></textarea>
                        <div class="text-right" id="count_message_qru"></div>
                    </div>

                    <div class="form-group">
                        <label for="prompt_en" class="text-info">Prompt_en</label>
                        <textarea id="prompt_en" name="prompt_en" class="form-control" rows="3" required
                                  placeholder="Write something.." maxlength="200"></textarea>
                        <div class="text-right" id="count_message_pen"></div>
                    </div>

                    <div class="form-group">
                        <label for="prompt_ru" class="text-info">Prompt_ru</label>
                        <textarea id="prompt_ru" name="prompt_ru" class="form-control" rows="3" required
                                  placeholder="Write something.." maxlength="200"></textarea>
                        <div class="text-right" id="count_message_pru"></div>
                    </div>

                    <div class="form-group">
                        <c:if test="${question_type_param == 'single'}">
                            <div class="form-group">
                                <label for="answer_en" class="text-info">Answer_en</label>
                                <textarea id="answer_en" name="answer_en" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                                <div class="text-right" id="count_message_aen"></div>
                            </div>
                            <div class="form-group">
                                <label for="answer_ru" class="text-info">Answer_ru</label>
                                <textarea id="answer_ru" name="answer_ru" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                                <div class="text-right" id="count_message_aru"></div>
                            </div>
                        </c:if>
                        <c:if test="${question_type_param == 'multi'}">
                            <div class="form-group">
                                <label for="answer_en_a" class="text-my-a">Answer_en Variant A:</label>
                                <textarea id="answer_en_a" name="answer_en_a" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                                <div class="text-right">
                                    <label for="is_answer_correct_a"><fmt:message key="answer.correct"/></label>
                                    <input id="is_answer_correct_a" name="is_answer_correct_a" type="checkbox"
                                           value="yes">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="answer_ru_a" class="text-my-a">Answer_ru Variant A:</label>
                                <textarea id="answer_ru_a" name="answer_ru_a" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="answer_en_b" class="text-my-b">Answer_en Variant B:</label>
                                <textarea id="answer_en_b" name="answer_en_b" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                                <div class="text-right">
                                    <label for="is_answer_correct_b"><fmt:message key="answer.correct"/></label>
                                    <input id="is_answer_correct_b" name="is_answer_correct_b" type="checkbox"
                                           value="yes">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="answer_ru_b" class="text-my-b">Answer_ru Variant B:</label>
                                <textarea id="answer_ru_b" name="answer_ru_b" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="answer_en_c" class="text-my-c">Answer_en Variant C:</label>
                                <textarea id="answer_en_c" name="answer_en_c" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                                <div class="text-right">
                                    <label for="is_answer_correct_c"><fmt:message key="answer.correct"/></label>
                                    <input id="is_answer_correct_c" name="is_answer_correct_c" type="checkbox"
                                           value="yes">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="answer_ru_c" class="text-my-c">Answer_ru Variant C:</label>
                                <textarea id="answer_ru_c" name="answer_ru_c" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="answer_en_d" class="text-my-d">Answer_en Variant D:</label>
                                <textarea id="answer_en_d" name="answer_en_d" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                                <div class="text-right">
                                    <label for="is_answer_correct_d"><fmt:message key="answer.correct"/></label>
                                    <input id="is_answer_correct_d" name="is_answer_correct_d" type="checkbox"
                                           value="yes">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="answer_ru_d" class="text-my-d">Answer_ru Variant D:</label>
                                <textarea id="answer_ru_d" name="answer_ru_d" class="form-control" rows="3" required
                                          placeholder="Write something.." maxlength="200"></textarea>
                            </div>
                        </c:if>
                    </div>
                    <input type="submit" class="btn btn-info btn-md" value="Save">

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
    var text_max_qen = 450;
    $('#count_message_qen').html(text_max_qen + ' remaining');

    $('#question_en').keyup(function () {
        var text_length = $('#question_en').val().length;
        var text_remaining = text_max_qen - text_length;

        $('#count_message_qen').html(text_remaining + ' remaining');
    });
</script>

<script>
    var text_max_qru = 450;
    $('#count_message_qru').html(text_max_qru + ' remaining');

    $('#question_ru').keyup(function () {
        var text_length = $('#question_ru').val().length;
        var text_remaining = text_max_qru - text_length;

        $('#count_message_qru').html(text_remaining + ' remaining');
    });
</script>
<script>
    var text_max_pen = 200;
    $('#count_message_pen').html(text_max_pen + ' remaining');

    $('#prompt_en').keyup(function () {
        var text_length = $('#prompt_en').val().length;
        var text_remaining = text_max_pen - text_length;

        $('#count_message_pen').html(text_remaining + ' remaining');
    });
</script>
<script>
    var text_max_pru = 200;
    $('#count_message_pru').html(text_max_pru + ' remaining');

    $('#prompt_ru').keyup(function () {
        var text_length = $('#prompt_ru').val().length;
        var text_remaining = text_max_pru - text_length;

        $('#count_message_pru').html(text_remaining + ' remaining');
    });
</script>
</body>
</html>
