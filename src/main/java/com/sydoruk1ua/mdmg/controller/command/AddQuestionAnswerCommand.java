package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.model.entity.Answer;
import com.sydoruk1ua.mdmg.model.entity.Question;
import com.sydoruk1ua.mdmg.model.entity.QuestionType;
import com.sydoruk1ua.mdmg.model.service.AnswerService;
import com.sydoruk1ua.mdmg.model.service.QuestionService;
import com.sydoruk1ua.mdmg.util.AttributeManager;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import com.sydoruk1ua.mdmg.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sydoruk1ua.mdmg.util.Validator.*;

public class AddQuestionAnswerCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddQuestionAnswerCommand.class);
    //Messages
    private static final String ERROR_MORE_THAN_ONE_CORRECT_ANSWER = "error.more.than.one.correct.answer";
    private static final String ERROR_INVALID_QUESTION = "error.invalid.question";
    private static final String ERROR_INVALID_QUESTION_PROMPT = "error.invalid.question.prompt";
    private static final String ERROR_INVALID_QUESTION_TYPE = "error.invalid.question.type";
    private static final String ERROR_INVALID_ANSWER = "error.invalid.answer";
    private static final String ERROR_ADDING_ANSWER = "error.adding.answer";
    private static final String ERROR_ADDING_QUESTION = "error.adding.question";
    private static final String INFO_QUESTION_WAS_ADDED = "info.question.was.added";
    //Path
    private static final String QUESTION_ADD_PAGE_PATH = "question.add.page.path";
    //Attributes
    private static final String INVALID_ANSWER_DATA = "invalid.answer.data";
    private static final String INVALID_QUESTION_DATA = "invalid.question.data";
    private static final String DATABASE_ERROR = "database.error";
    private static final String QUESTION_ADDED = "question.added";
    private static final String QUESTION_TYPE = "question.type";
    //Param
    private static final String MULTI = "multi";
    private static final String SINGLE = "single";

    private final QuestionService questionService;
    private final AnswerService answerService;

    public AddQuestionAnswerCommand(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String questionTypeParam = request.getParameter("question_type");

        if (MULTI.equals(questionTypeParam)) {
            request.getSession().setAttribute(AttributeManager.getProperty(QUESTION_TYPE), MULTI);
        } else {
            request.getSession().setAttribute(AttributeManager.getProperty(QUESTION_TYPE), SINGLE);
        }

        Optional<Question> optionalQuestion = getValidOptionalQuestion(request);
        if (optionalQuestion.isPresent()) {
            Optional<Integer> optionalQuestionId = questionService.create(optionalQuestion.get());
            if (optionalQuestionId.isPresent()) {
                Integer questionId = optionalQuestionId.get();
                List<Answer> answers = getListOfAnswers(questionId, optionalQuestion.get(), request);
                if (!answers.isEmpty()) {
                    if (answerService.create(answers)) {
                        request.setAttribute(AttributeManager.getProperty(QUESTION_ADDED),
                                MessageManager.getProperty(INFO_QUESTION_WAS_ADDED));

                        return ConfigurationManager.getProperty(QUESTION_ADD_PAGE_PATH);
                    } else {
                        request.setAttribute(AttributeManager.getProperty(DATABASE_ERROR),
                                MessageManager.getProperty(ERROR_ADDING_ANSWER));

                        return ConfigurationManager.getProperty(QUESTION_ADD_PAGE_PATH);
                    }
                }
            } else {
                request.setAttribute(AttributeManager.getProperty(DATABASE_ERROR),
                        MessageManager.getProperty(ERROR_ADDING_QUESTION));

                return ConfigurationManager.getProperty(QUESTION_ADD_PAGE_PATH);
            }
        }
        return ConfigurationManager.getProperty(QUESTION_ADD_PAGE_PATH);
    }

    private Optional<Question> getValidOptionalQuestion(HttpServletRequest request) {
        String questionType = request.getParameter("question_type");
        String questionEn = request.getParameter("question_en");
        String questionRu = request.getParameter("question_ru");
        String promptEn = request.getParameter("prompt_en");
        String promptRu = request.getParameter("prompt_ru");

        if (!isQuestionTypeValid(questionType)) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_QUESTION_TYPE) + " " + questionType);
            request.setAttribute(AttributeManager.getProperty(INVALID_QUESTION_DATA),
                    MessageManager.getProperty(ERROR_INVALID_QUESTION_TYPE));
            return Optional.empty();
        }

        if (!isQuestionEnValid(questionEn)) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_QUESTION) + " " + questionEn);
            request.setAttribute(AttributeManager.getProperty(INVALID_QUESTION_DATA),
                    MessageManager.getProperty(ERROR_INVALID_QUESTION));
            return Optional.empty();
        }
        if (!isQuestionRuValid(questionRu)) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_QUESTION) + " " + questionRu);
            request.setAttribute(AttributeManager.getProperty(INVALID_QUESTION_DATA),
                    MessageManager.getProperty(ERROR_INVALID_QUESTION));
            return Optional.empty();
        }
        if (!isQuestionPromptEnValid(promptEn)) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_QUESTION_PROMPT) + " " + promptEn);
            request.setAttribute(AttributeManager.getProperty(INVALID_QUESTION_DATA),
                    MessageManager.getProperty(ERROR_INVALID_QUESTION_PROMPT));
            return Optional.empty();
        }
        if (!isQuestionPromptRuValid(promptRu)) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_QUESTION_PROMPT) + " " + promptRu);
            request.setAttribute(AttributeManager.getProperty(INVALID_QUESTION_DATA),
                    MessageManager.getProperty(ERROR_INVALID_QUESTION_PROMPT));
            return Optional.empty();
        }

        return Optional.ofNullable(Question.builder()
                .withQuestionType(new QuestionType(questionType))
                .withQuestionEn(questionEn)
                .withQuestionRu(questionRu)
                .withPromptEn(promptEn)
                .withPromptRu(promptRu)
                .build());
    }

    private List<Answer> getListOfAnswers(Integer questionId, Question question, HttpServletRequest request) {
        List<Answer> answers = new ArrayList<>();
        String questionType = question.getQuestionType().getType();
        if (questionType.equals(SINGLE)) {
            return getOneAnswer(questionId, request, answers);
        } else if (questionType.equals(MULTI)) {
            return getFewAnswers(questionId, request, answers);
        }

        return answers;
    }

    private List<Answer> getFewAnswers(Integer questionId, HttpServletRequest request, List<Answer> answers) {
        //There is can be only one correct answer among others
        int count = 0;
        String isCorrectA = getCorrectAnswerValue(request.getParameter("is_answer_correct_a"));
        Answer answerA = Answer.builder()
                .withQuestionId(questionId)
                .withAnswerEn(request.getParameter("answer_en_a"))
                .withAnswerRu(request.getParameter("answer_ru_a"))
                .withIsCorrect(isCorrectA)
                .build();
        if (isAnswerValid(answerA, request)) {
            if (answerA.getCorrect().equals("yes")) {
                count++;
            }
            answers.add(answerA);
        } else {
            return new ArrayList<>();
        }
        String isCorrectB = getCorrectAnswerValue(request.getParameter("is_answer_correct_b"));
        Answer answerB = Answer.builder()
                .withQuestionId(questionId)
                .withAnswerEn(request.getParameter("answer_en_b"))
                .withAnswerRu(request.getParameter("answer_ru_b"))
                .withIsCorrect(isCorrectB)
                .build();
        if (isAnswerValid(answerB, request)) {
            if (answerB.getCorrect().equals("yes")) {
                count++;
            }
            answers.add(answerB);
        } else {
            return new ArrayList<>();
        }
        String isCorrectC = getCorrectAnswerValue(request.getParameter("is_answer_correct_c"));
        Answer answerC = Answer.builder()
                .withQuestionId(questionId)
                .withAnswerEn(request.getParameter("answer_en_c"))
                .withAnswerRu(request.getParameter("answer_ru_c"))
                .withIsCorrect(isCorrectC)
                .build();
        if (isAnswerValid(answerC, request)) {
            if (answerC.getCorrect().equals("yes")) {
                count++;
            }
            answers.add(answerC);
        } else {
            return new ArrayList<>();
        }
        String isCorrectD = getCorrectAnswerValue(request.getParameter("is_answer_correct_d"));
        Answer answerD = Answer.builder()
                .withQuestionId(questionId)
                .withAnswerEn(request.getParameter("answer_en_d"))
                .withAnswerRu(request.getParameter("answer_ru_d"))
                .withIsCorrect(isCorrectD)
                .build();
        if (isAnswerValid(answerD, request)) {
            if (answerD.getCorrect().equals("yes")) {
                count++;
            }
            answers.add(answerD);
        } else {
            return new ArrayList<>();
        }
        if (count > 1) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_ANSWER));
            request.setAttribute(AttributeManager.getProperty(INVALID_ANSWER_DATA),
                    MessageManager.getProperty(ERROR_MORE_THAN_ONE_CORRECT_ANSWER));
            return new ArrayList<>();
        }
        return answers;
    }

    private List<Answer> getOneAnswer(Integer questionId, HttpServletRequest request, List<Answer> answers) {
        Answer answer = Answer.builder()
                .withQuestionId(questionId)
                .withAnswerEn(request.getParameter("answer_en"))
                .withAnswerRu(request.getParameter("answer_ru"))
                .withIsCorrect("yes")
                .build();
        if (isAnswerValid(answer, request)) {
            answers.add(answer);
            return answers;
        } else {
            return new ArrayList<>();
        }
    }

    private boolean isAnswerValid(Answer answer, HttpServletRequest request) {
        if (!isAnswerEnValid(answer.getAnswerEn())) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_ANSWER) + " " + answer.getAnswerEn());
            request.setAttribute(AttributeManager.getProperty(INVALID_ANSWER_DATA),
                    MessageManager.getProperty(ERROR_INVALID_ANSWER) + answer.getAnswerEn());
            return false;
        }
        if (!isAnswerRuValid(answer.getAnswerRu())) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_ANSWER) + " " + answer.getAnswerRu());
            request.setAttribute(AttributeManager.getProperty(INVALID_ANSWER_DATA),
                    MessageManager.getProperty(ERROR_INVALID_ANSWER) + answer.getAnswerRu());
            return false;
        }
        if (!isFieldIsCorrectValid(answer.getCorrect())) {
            LOGGER.debug(MessageManager.getProperty(ERROR_INVALID_ANSWER) + " " + answer.getCorrect());
            request.setAttribute(AttributeManager.getProperty(INVALID_ANSWER_DATA),
                    MessageManager.getProperty(ERROR_INVALID_ANSWER) + answer.getCorrect());
            return false;
        }
        return true;
    }

    private String getCorrectAnswerValue(String isCorrectParam) {
        return isCorrectParam == null ? "no" : isCorrectParam;
    }
}
