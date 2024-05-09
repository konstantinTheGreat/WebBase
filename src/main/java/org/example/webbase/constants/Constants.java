package org.example.webbase.constants;

import org.intellij.lang.annotations.Language;

public class Constants {
//    (public/private) static final TYPE NAME = VALUE;
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String VERIFICATION_CODE = "verification_code";
    public static final String VERIFICATION_PAGE = "pages/auth/verification.jsp";
    public static final String SIGNUP_PAGE = "pages/auth/signUp.jsp";
    public static final String SUCCESS_PAGE = "pages/auth/success.jsp";
    public static final String CURRENT_PAGE = "current_page";
    public static final String SIGNUP_ERROR = "signup_error";
    public static final String LOGIN_ERROR = "login_error";
    public static final String INCORRECT_EMAIL_MESSAGE = "incorrect login or password";
    public static final String INCORRECT_LOGIN_MESSAGE = "incorrect email";
    public static final String LOGIN_PAGE = "pages/auth/index.jsp";
    public static final String PROFILE_PAGE = "pages/profilePages/main.jsp";
    public static final String CHANGE_PASSWORD_PAGE = "pages/profilePages/changePassword.jsp";
    public static final String OLD_PASSWORD = "old_password";
    public static final String NEW_PASSWORD = "new_password";
    public static final String ERROR_500_PAGE = "pages/error/error_500.jsp";
    public static final String COMMAND = "command";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String EMAIL_USERNAME = "konstantinthegreat1791@gmail.com";
    public static final String EMAIL_PASSWORD = "ajpq uodz ucvm idnk";
    @Language("SQL")
    public static final String GET_EMAIL_SQL = "SELECT email FROM Users WHERE username = ?";
}
