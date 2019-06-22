package com.sydoruk1ua.mdmg.util;

public final class Validator {

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
            "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4]" +
            "[0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-" +
            "\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
    /**
     * Password must contain:
     * At least one upper case letter, (?=.*?[A-ZА-Я])
     * At least one lower case letter, (?=.*?[a-zа-я])
     * At least one digit, (?=.*?[0-9])
     * At least one special character, (?=.*?[#?!@$%^&*-])
     * Length must be between 6 and 45 characters .{6,45}
     */
    private static final String PASSWORD_REGEX = "^(?=.*?[A-ZА-Я])(?=.*?[a-zа-я])(?=.*?[0-9])" +
            "(?=.*?[#?!@$%^&*-]).{6,45}$";
    private static final String FIRST_NAME_REGEX = "[а-яА-ЯёЁa-zA-Z`-]{1,45}+";
    private static final String LAST_NAME_REGEX = "[а-яА-ЯёЁa-zA-Z`-]{1,45}+";

    private Validator() {
    }


    public static boolean isEmailValid(String email) {
        return email != null && email.matches(EMAIL_REGEX) && email.length() <= 45;
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public static boolean isRepeatPasswordValid(String password, String repeatPassword) {
        return password != null && password.equals(repeatPassword) && repeatPassword.matches(PASSWORD_REGEX);
    }

    public static boolean isFirstNameValid(String firstName) {
        return firstName != null && firstName.matches(FIRST_NAME_REGEX);
    }

    public static boolean isLastNameValid(String lastName) {
        return lastName != null && lastName.matches(LAST_NAME_REGEX);
    }

    public static boolean isQuestionTypeValid(String questionType) {
        return "single".equals(questionType) || "multi".equals(questionType);
    }

    public static boolean isFieldIsCorrectValid(String isCorrect) {
        return "no".equals(isCorrect) || "yes".equals(isCorrect);
    }

    public static boolean isQuestionEnValid(String questionEn) {
        return isValueValid(questionEn, 450);
    }

    public static boolean isQuestionRuValid(String questionRu) {
        return isValueValid(questionRu, 450);
    }

    public static boolean isQuestionPromptEnValid(String promptEn) {
        return isValueValid(promptEn, 200);
    }

    public static boolean isQuestionPromptRuValid(String promptRu) {
        return isValueValid(promptRu, 200);
    }

    public static boolean isMessageValid(String message) {
        return isValueValid(message, 200);
    }

    public static boolean isAnswerEnValid(String answerEn) {
        return isValueValid(answerEn, 200);
    }

    public static boolean isAnswerRuValid(String answerRu) {
        return isValueValid(answerRu, 200);
    }

    private static boolean isValueValid(String value, int length) {
        return value != null && !value.equals("") && value.length() <= length;
    }
}
