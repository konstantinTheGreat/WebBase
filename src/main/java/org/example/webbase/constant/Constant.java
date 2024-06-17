package org.example.webbase.constant;

import org.intellij.lang.annotations.Language;

public class Constant {
//    (public/private) static final TYPE NAME = VALUE;
    //private constructor

    @Language("SQL")
    public static final String CHECK_USER = "SELECT username, password FROM Users WHERE username = ?";
    @Language("SQL")
    public static final String DELETE_USER = "DELETE FROM users WHERE username = ?";
    @Language("SQL")
    public static final String UPLOAD_FILE_SQL = "UPDATE users SET profile_pic = ? WHERE username = ?";
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
    public static final String PROFILE_PAGE = "pages/profile/profile.jsp";
    public static final String MAIN_MENU = "/pages/profile/main_menu.jsp";
    public static final String CHANGE_PASSWORD_PAGE = "pages/profile/changePassword.jsp";
    public static final String OLD_PASSWORD = "old_password";
    public static final String NEW_PASSWORD = "new_password";
    public static final String ERROR_500_PAGE = "pages/error/error_500.jsp";
    public static final String COMMAND = "command";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String EMAIL_USERNAME = "konstantinthegreat1791@gmail.com";
    public static final String EMAIL_PASSWORD = "ajpq uodz ucvm idnk";
    @Language("SQL")
    public static final String GET_EMAIL_SQL = "SELECT email FROM Users WHERE username = ?";
    @Language("SQL")
    public static final String SELECT_PASSWORD = "SELECT password, salt FROM Users WHERE username = ?";
    @Language("SQL")
    public static final String ADD_USER = "INSERT INTO users (username, password, email, verification_code, salt) VALUES (?, ?, ?, ?, ?)";
    @Language("SQL")
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";
    public static final int EIGHT = 8;
    public static final String REQUIREMENTS_NOT_MET = "Requirements not met";
    public static final String INCORRECT_CODE = "Incorrect code";
    public static final String VERIFICATION_ERROR = "verification_error";
    public static final String DB_PASSWORD = "gustavofabelo2169";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/WebServer";
    public static final String USER = "user";
    public static final String DB_USER = "postgres";
    public static final String MAIN_GAME = "pages/game/mainGame.jsp";
    public static final String CURRENT_USER =  "currentUser";
    public static final String GET_USER_INFO = "SELECT userid, username, password, email, verification_code, salt, profile_pic, status FROM users WHERE username = ?";
    public static final String STATUS = "status";
}
